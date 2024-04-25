package com.topnetwork.wallet.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppInviteCodeDao;
import com.topnetwork.wallet.entity.AppInviteCode;
import com.topnetwork.wallet.service.AppInviteCodeService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service("appInviteCodeService")
@Transactional
public class AppInviteCodeServiceImpl extends SqlBaseServiceImpl<AppInviteCode, Long>
        implements AppInviteCodeService {

    @SuppressWarnings("unused")
    private AppInviteCodeDao appInviteCodeDao;

    public AppInviteCodeServiceImpl(@Qualifier("appInviteCodeDao") AppInviteCodeDao appInviteCodeDao) {
        super(appInviteCodeDao);
        this.appInviteCodeDao = appInviteCodeDao;
    }

    @Override
    public String getByUid(String uid) {
        Date date = new Date();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", uid).gt("expirationTime", date);
        AppInviteCode inviteCode = get(appInviteCodeDao.queryForList(wrapper));
        if (inviteCode == null) {
            inviteCode = new AppInviteCode();
            inviteCode.setUid(uid);
            inviteCode.setCode(getCode());
            inviteCode.setCreateTime(date);
            inviteCode.setExpirationTime(getExpirationTime(date));
            save(inviteCode);
        }
        return inviteCode.getCode();
    }

    @Override
    public AppInviteCode getByInviteCode(String code) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("code", code);
        AppInviteCode inviteCode = get(appInviteCodeDao.queryForList(wrapper));
        return inviteCode;
    }

    private Date getExpirationTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 20);
        return calendar.getTime();
    }

    private String getCode() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
