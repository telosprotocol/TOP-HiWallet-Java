package com.topnetwork.wallet.service;

import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppActivityAnswer;

import java.util.List;

public interface AppActivityAnswerService extends SqlBaseService<AppActivityAnswer,Long> {

    List<AppActivityAnswer> getByQid(Long id);

    void deleteByQuestionId(Long questionId);
}
