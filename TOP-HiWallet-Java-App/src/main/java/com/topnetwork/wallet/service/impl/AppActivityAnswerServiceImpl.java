package com.topnetwork.wallet.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppActivityAnswerDao;
import com.topnetwork.wallet.entity.AppActivityAnswer;
import com.topnetwork.wallet.service.AppActivityAnswerService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;

import java.util.List;

@Service("appActivityAnswerService")
@Transactional
public class AppActivityAnswerServiceImpl extends SqlBaseServiceImpl<AppActivityAnswer, Long>
        implements AppActivityAnswerService {

    @SuppressWarnings("unused")
    private AppActivityAnswerDao appActivityAnswerDao;

    public AppActivityAnswerServiceImpl(@Qualifier("appActivityAnswerDao") AppActivityAnswerDao appActivityAnswerDao) {
        super(appActivityAnswerDao);
        this.appActivityAnswerDao = appActivityAnswerDao;
    }

    @Override
    public List<AppActivityAnswer> getByQid(Long qid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("qid", qid);
        wrapper.orderByAsc("type");
        return appActivityAnswerDao.queryForList(wrapper);
    }

    @Override
    public void deleteByQuestionId(Long questionId) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("qid", questionId);
        appActivityAnswerDao.executeUpdate(wrapper);
    }
}
