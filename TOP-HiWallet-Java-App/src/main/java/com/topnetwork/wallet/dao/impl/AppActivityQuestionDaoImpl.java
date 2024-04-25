package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppActivityQuestionDao;
import com.topnetwork.wallet.entity.AppActivityQuestion;

@Repository("appActivityQuestionDao")
public class AppActivityQuestionDaoImpl extends SqlBaseDaoImpl<AppActivityQuestion, Long> implements AppActivityQuestionDao {

    public AppActivityQuestionDaoImpl() {
        super(AppActivityQuestion.class);
    }

    @Override
    public Long saveForId(AppActivityQuestion appActivityQuestion) {
        Long id = getSnowflakeIdWorkerNextId();
        appActivityQuestion.setId(id);
        long i = persist(appActivityQuestion);
        return i > 0 ? id : null;
    }
}
