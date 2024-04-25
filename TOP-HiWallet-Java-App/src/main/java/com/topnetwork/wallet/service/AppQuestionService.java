package com.topnetwork.wallet.service;

import com.topnetwork.wallet.param.wallet.activity.*;
import com.topnetwork.wallet.result.wallet.activity.QuestionDetailResult;
import com.topnetwork.wallet.result.wallet.activity.QuestionListResult;
import com.topnetwork.wallet.result.wallet.activity.QuestionPageResult;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppActivityQuestion;

import java.util.List;

public interface AppQuestionService extends SqlBaseService<AppActivityQuestion,Long> {

    AppActivityQuestion findById(Long qid);

    List<QuestionListResult> getQuestionList(QuestionListParam param);

    QueryResult<List<QuestionPageResult>> findQuestionPage(QuestionPageParam param);

    QuestionDetailResult findQuestionDetail(QuestionDetailParam param);

    void addQuestion(QuestionAddParam param);

    void updateQuestion(QuestionUpdParam param);
}
