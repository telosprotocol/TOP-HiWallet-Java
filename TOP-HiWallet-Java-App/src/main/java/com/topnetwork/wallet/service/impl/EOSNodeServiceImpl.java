package com.topnetwork.wallet.service.impl;

import com.base.core.mvc.business.CommonBusiness;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.dao.EOSNodeDao;
import com.topnetwork.wallet.entity.EOSNode;
import com.topnetwork.wallet.param.wallet.EOSAddNodeParam;
import com.topnetwork.wallet.param.wallet.EOSDelNodeParam;
import com.topnetwork.wallet.param.wallet.EOSGetNodesParam;
import com.topnetwork.wallet.param.wallet.EOSUpdNodeParam;
import com.topnetwork.wallet.param.wallet.v1.GetEOSNodeParam;
import com.topnetwork.wallet.result.wallet.EOSManageNodeResult;
import com.topnetwork.wallet.result.wallet.GetEOSNodeResult;
import com.topnetwork.wallet.service.EOSNodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("eosNodeService")
@Transactional
public class EOSNodeServiceImpl extends SqlBaseServiceImpl<EOSNode, Long>
        implements EOSNodeService {

    @SuppressWarnings("unused")
    private EOSNodeDao eosNodeDao;

    public EOSNodeServiceImpl(@Qualifier("eosNodeDao") EOSNodeDao eosNodeDao) {
        super(eosNodeDao);
        this.eosNodeDao = eosNodeDao;
    }

    @Override
    public QueryResult<List<GetEOSNodeResult>> getEosNodes(GetEOSNodeParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        return queryForPage(GetEOSNodeResult.class, "getEosNodesForWallet", params);
    }

    @Override
    public QueryResult<List<EOSManageNodeResult>> getEosNodes(EOSGetNodesParam param) {
        QueryResult<List<EOSManageNodeResult>> result = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<EOSNode>> database = queryForPage(EOSNode.class, "getEosNodesForManager", params);
        if(ObjectUtils.isEmpty(database)) {
        	return result;
        }
        result.setPageInfo(database.getPageInfo());
        result.setResult(tran(database.getResult()));
        return result;
    }


    @Override
    public void addEosNode(EOSAddNodeParam param) {
        EOSNode eosNode = new EOSNode();
        BeanUtils.copyProperties(param, eosNode);
        eosNode.setCreateTime(new Date());
        save(eosNode);
    }

    @Override
    public void deleteEosNode(EOSDelNodeParam param) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("id", param.getId());
        eosNodeDao.executeUpdate(wrapper);
    }

    @Override
    public void updateEosNode(EOSUpdNodeParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getId());
        EOSNode eosNode = get(eosNodeDao.queryForList(wrapper));
        if (eosNode == null) {
            throw new BusinessException(CodeRes.CODE_17006);
        }
        Date createTime = eosNode.getCreateTime();
        BeanUtils.copyProperties(param, eosNode);
        eosNode.setCreateTime(createTime);
        save(eosNode);
    }

    private List<EOSManageNodeResult> tran(List<EOSNode> result) {

        List<EOSManageNodeResult> list = new ArrayList<>();
        EOSManageNodeResult eosManagNodeResult = new EOSManageNodeResult();
        for (EOSNode eosNode : result) {
            eosManagNodeResult.setComment(eosNode.getComment());
            eosManagNodeResult.setCreateTime(eosNode.getCreateTime().getTime());
            eosManagNodeResult.setDisable(eosNode.getDisable());
            eosManagNodeResult.setId(eosNode.getId());
            eosManagNodeResult.setLocation(eosNode.getLocation());
            eosManagNodeResult.setNodeName(eosNode.getNodeName());
            eosManagNodeResult.setNodeOrder(eosNode.getNodeOrder());
            eosManagNodeResult.setNodeUrl(eosNode.getNodeUrl());
            list.add(eosManagNodeResult);
        }
        return list;
    }
}
