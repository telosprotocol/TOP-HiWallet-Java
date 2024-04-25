package com.topnetwork.wallet.dao;

import com.base.core.framework.sql.dao.SqlBaseDao;
import com.topnetwork.wallet.entity.AppActivityQuestion;

public interface AppActivityQuestionDao extends SqlBaseDao<AppActivityQuestion,Long> {

    Long saveForId(AppActivityQuestion appActivityQuestion);
}
