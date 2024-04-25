package com.topnetwork.wallet.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.common.utils.GenerateRadomStr;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.dao.DefiInviteDao;
import com.topnetwork.wallet.entity.DefiInvite;
import com.topnetwork.wallet.param.wallet.defi.BindDefiInviteParam;
import com.topnetwork.wallet.param.wallet.defi.GenerateDefiInviteParam;
import com.topnetwork.wallet.result.defi.DefiInviteResult;
import com.topnetwork.wallet.service.DefiInviteService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("defiInviteService")
public class DefiInviteServiceImpl extends SqlBaseServiceImpl<DefiInvite,Long> 
implements DefiInviteService {

	@SuppressWarnings("unused")
	private DefiInviteDao defiInviteDao;
	
	public DefiInviteServiceImpl(@Qualifier("defiInviteDao")DefiInviteDao defiInviteDao) {
		super(defiInviteDao);
		this.defiInviteDao=defiInviteDao;
	}

	@Override
	public DefiInviteResult generate(GenerateDefiInviteParam params) {
		DefiInvite defiInvite = loadByEthAddress(params.getEthAddress());
		DefiInviteResult result = new DefiInviteResult();
		if(!ObjectUtils.isEmpty(defiInvite)) {
			BeanUtils.copyProperties(defiInvite, result);
			return result;
		}
		String inviteCode = GenerateRadomStr.generateRandomStr(4);
		inviteCode = verifyInviteCode(inviteCode);
		defiInvite = new DefiInvite();
		defiInvite.setEthAddress(params.getEthAddress());
		defiInvite.setInviteCode(inviteCode);
		save(defiInvite);
		BeanUtils.copyProperties(defiInvite, result);
		return result;
	}
	
	private String verifyInviteCode(String inviteCode) {
		DefiInvite defiInvite = loadByInviteCode(inviteCode);
		if(ObjectUtils.isEmpty(defiInvite)) {
			return inviteCode;
		}else {//存在重新生成，再验证
			inviteCode = GenerateRadomStr.generateRandomStr(4);
			verifyInviteCode(inviteCode);
		}
		return null;
	}
	
	private DefiInvite loadByEthAddress(String ethAddress) {
		QueryWrapper wrapper = new QueryWrapper();
		wrapper.eq("ethAddress", ethAddress);
		return get(defiInviteDao.queryForList(wrapper));
	}
	
	private DefiInvite loadByInviteCode(String inviteCode) {
		QueryWrapper wrapper = new QueryWrapper();
		wrapper.eq("inviteCode", inviteCode);
		return get(defiInviteDao.queryForList(wrapper));
	}
	
	@Override
	public void bind(BindDefiInviteParam params) {
		DefiInvite defiInvite = loadByEthAddress(params.getEthAddress());
		if(ObjectUtils.isEmpty(defiInvite)) {
			throw new BusinessException(CodeRes.CODE_24000);
		}else {
			if(defiInvite.getInviteCode().equals(params.getBindInviteCode().toLowerCase())) {
				throw new BusinessException(CodeRes.CODE_24000);
			}
			DefiInvite dbdi = loadByInviteCode(params.getBindInviteCode());
			if(ObjectUtils.isEmpty(dbdi)) {
				throw new BusinessException(CodeRes.CODE_24000);
			}
			if(ObjectUtils.isEmpty(defiInvite.getBindInviteCode())) {
				defiInvite.setBindInviteCode(params.getBindInviteCode());
				save(defiInvite);
			}else {				
				throw new BusinessException(CodeRes.CODE_24001);
			}
		}
		
	}

}
