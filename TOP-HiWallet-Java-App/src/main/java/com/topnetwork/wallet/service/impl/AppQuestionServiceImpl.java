package com.topnetwork.wallet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.base.core.mvc.business.CommonBusiness;
import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.wallet.common.enums.DifficultyLevel;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.dao.AppActivityQuestionDao;
import com.topnetwork.wallet.entity.AppActivityAnswer;
import com.topnetwork.wallet.entity.AppActivityQuestion;
import com.topnetwork.wallet.param.wallet.activity.AnswerAddParam;
import com.topnetwork.wallet.param.wallet.activity.QuestionAddParam;
import com.topnetwork.wallet.param.wallet.activity.QuestionDetailParam;
import com.topnetwork.wallet.param.wallet.activity.QuestionListParam;
import com.topnetwork.wallet.param.wallet.activity.QuestionPageParam;
import com.topnetwork.wallet.param.wallet.activity.QuestionUpdParam;
import com.topnetwork.wallet.result.wallet.activity.AnswerDetailResult;
import com.topnetwork.wallet.result.wallet.activity.AnswerResult;
import com.topnetwork.wallet.result.wallet.activity.QuestionDetailResult;
import com.topnetwork.wallet.result.wallet.activity.QuestionListResult;
import com.topnetwork.wallet.result.wallet.activity.QuestionPageResult;
import com.topnetwork.wallet.service.AppActivityAnswerService;
import com.topnetwork.wallet.service.AppQuestionService;

import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("appQuestionService")
@Transactional
public class AppQuestionServiceImpl extends SqlBaseServiceImpl<AppActivityQuestion, Long>
        implements AppQuestionService {

    @SuppressWarnings("unused")
    private AppActivityQuestionDao appActivityQuestionDao;
    @Autowired
    private AppActivityAnswerService appActivityAnswerService;

    @Autowired
    private ConfigureService configureService;

    public AppQuestionServiceImpl(@Qualifier("appActivityQuestionDao") AppActivityQuestionDao appActivityQuestionDao) {
        super(appActivityQuestionDao);
        this.appActivityQuestionDao = appActivityQuestionDao;
    }

    @Override
    public AppActivityQuestion findById(Long qid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", qid);
        return get(appActivityQuestionDao.queryForList(wrapper));
    }

    @Override
    public List<QuestionListResult> getQuestionList(QuestionListParam param) {

        List<QuestionListResult> listResults = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        List<AppActivityQuestion> appActivityQuestionList = appActivityQuestionDao.queryForList(wrapper);
        if (appActivityQuestionList == null) {
            return listResults;
        }
        Integer simpleQuestionSize = getQuestionSize("simpleQuestionSize");
        Integer mediumQuestionSize = getQuestionSize("mediumQuestionSize");
        Integer difficultyQuestionSize = getQuestionSize("difficultyQuestionSize");
        List<AppActivityQuestion> appActivityList = new ArrayList<>();
        if (appActivityQuestionList.size() <= (simpleQuestionSize + mediumQuestionSize + difficultyQuestionSize)) {
            appActivityList.addAll(appActivityQuestionList);
        } else {
            List<AppActivityQuestion> simpleList = new ArrayList<>();
            List<AppActivityQuestion> mediumList = new ArrayList<>();
            List<AppActivityQuestion> difficultyList = new ArrayList<>();
            for (AppActivityQuestion appActivityQuestion : appActivityQuestionList) {
                if (appActivityQuestion.getDifficultyLevel().equals(DifficultyLevel.SIMPLE)) {
                    simpleList.add(appActivityQuestion);
                }
                if (appActivityQuestion.getDifficultyLevel().equals(DifficultyLevel.MEDIUM)) {
                    mediumList.add(appActivityQuestion);
                }
                if (appActivityQuestion.getDifficultyLevel().equals(DifficultyLevel.DIFFICULTY)) {
                    difficultyList.add(appActivityQuestion);
                }
            }
            setQuestionList(appActivityList, simpleList, simpleQuestionSize);
            setQuestionList(appActivityList, mediumList, mediumQuestionSize);
            setQuestionList(appActivityList, difficultyList, difficultyQuestionSize);

        }


        for (AppActivityQuestion appActivityQuestion : appActivityList) {
            QuestionListResult questionListResult = new QuestionListResult();
            questionListResult.setQid(appActivityQuestion.getId());
            if (param.getLanguage().equals(LanguageEnum.CN)) {
                questionListResult.setQuestion(appActivityQuestion.getQuestion());
            } else {
                questionListResult.setQuestion(appActivityQuestion.getEnglishQuestion());
            }
            questionListResult.setAnswer(appActivityQuestion.getAnswer());
            List<AppActivityAnswer> list = appActivityAnswerService.getByQid(appActivityQuestion.getId());
            List<AnswerResult> answerList = new ArrayList<>();

            for (AppActivityAnswer appActivityAnswer : list) {
                AnswerResult answerResult = new AnswerResult();
                if (param.getLanguage().equals(LanguageEnum.CN)) {
                    answerResult.setDesc(appActivityAnswer.getChineseDesc());
                } else {
                    answerResult.setDesc(appActivityAnswer.getEnglishDesc());
                }
                answerResult.setType(appActivityAnswer.getType());
                answerList.add(answerResult);
            }
            questionListResult.setAnswerList(answerList);
            listResults.add(questionListResult);
        }

        return listResults;
    }

    @Override
    public QuestionDetailResult findQuestionDetail(QuestionDetailParam param) {
        QuestionDetailResult questionDetailResult = new QuestionDetailResult();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getQuestionId());
        AppActivityQuestion appActivityQuestion = get(appActivityQuestionDao.queryForList(wrapper));
        if (appActivityQuestion == null) {
            return questionDetailResult;
        }
        List<AppActivityAnswer> list = appActivityAnswerService.getByQid(appActivityQuestion.getId());
        List<AnswerDetailResult> answerList = new ArrayList<>();

        for (AppActivityAnswer appActivityAnswer : list) {
            AnswerDetailResult answerResult = new AnswerDetailResult();
            answerResult.setDesc(appActivityAnswer.getChineseDesc());
            answerResult.setAnswerId(appActivityAnswer.getId());
            answerResult.setEnglishDesc(appActivityAnswer.getEnglishDesc());
            answerResult.setType(appActivityAnswer.getType());
            answerList.add(answerResult);
        }
        questionDetailResult.setAnswerList(answerList);
        questionDetailResult.setAnswer(appActivityQuestion.getAnswer());
        questionDetailResult.setDifficultyLevel(appActivityQuestion.getDifficultyLevel());
        questionDetailResult.setEnglishQuestion(appActivityQuestion.getEnglishQuestion());
        questionDetailResult.setQuestion(appActivityQuestion.getQuestion());
        questionDetailResult.setQuestionId(appActivityQuestion.getId());
        return questionDetailResult;
    }

    @Override
    public QueryResult<List<QuestionPageResult>> findQuestionPage(QuestionPageParam param) {
        QueryResult<List<QuestionPageResult>> queryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppActivityQuestion>> queryResultSource = queryForPage(AppActivityQuestion.class, "findQuestionPage", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return queryResult;
        }
        List<AppActivityQuestion> list = queryResultSource.getResult();
        List<QuestionPageResult> results = new ArrayList<>();
        for (AppActivityQuestion appActivityQuestion : list) {
            QuestionPageResult questionPageResult = new QuestionPageResult();
            questionPageResult.setAnswer(appActivityQuestion.getAnswer());
            questionPageResult.setDifficultyLevel(appActivityQuestion.getDifficultyLevel());
            questionPageResult.setQid(appActivityQuestion.getId());
            questionPageResult.setQuestion(appActivityQuestion.getQuestion());
            questionPageResult.setUpdateTime(appActivityQuestion.getUpdateTime() == null ? null : appActivityQuestion.getUpdateTime().getTime());
            results.add(questionPageResult);
        }
        queryResult.setPageInfo(queryResultSource.getPageInfo());
        queryResult.setResult(results);
        return queryResult;
    }

    @Override
    public void addQuestion(QuestionAddParam param) {
        AppActivityQuestion appActivityQuestion = new AppActivityQuestion();
        appActivityQuestion.setAnswer(param.getAnswer());
        appActivityQuestion.setCreateTime(new Date());
        appActivityQuestion.setDifficultyLevel(param.getDifficultyLevel());
        appActivityQuestion.setEnglishQuestion(param.getEnglishQuestion());
        appActivityQuestion.setQuestion(param.getQuestion());
        Long questionId = saveForId(appActivityQuestion);
        if (questionId == null) {
            return;
        }
        saveAnswer(param.getAnswerList(), questionId);
    }

    @Override
    public void updateQuestion(QuestionUpdParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getQuestionId());
        AppActivityQuestion appActivityQuestion = get(appActivityQuestionDao.queryForList(wrapper));
        if (appActivityQuestion == null) {
            return;
        }
        appActivityQuestion.setAnswer(param.getAnswer());
        appActivityQuestion.setUpdateTime(new Date());
        appActivityQuestion.setDifficultyLevel(param.getDifficultyLevel());
        appActivityQuestion.setEnglishQuestion(param.getEnglishQuestion());
        appActivityQuestion.setQuestion(param.getQuestion());
        save(appActivityQuestion);
        appActivityAnswerService.deleteByQuestionId(param.getQuestionId());
        saveAnswer(param.getAnswerList(), param.getQuestionId());
    }

    private void saveAnswer(List<AnswerAddParam> list, Long questionId) {
        List<AppActivityAnswer> saveData = new ArrayList<>();
        for (AnswerAddParam answerAddParam : list) {
            AppActivityAnswer appActivityAnswer = new AppActivityAnswer();
            appActivityAnswer.setCreateTime(new Date());
            appActivityAnswer.setChineseDesc(answerAddParam.getDesc());
            appActivityAnswer.setEnglishDesc(answerAddParam.getEnglishDesc());
            appActivityAnswer.setQid(questionId);
            appActivityAnswer.setType(answerAddParam.getType());
            saveData.add(appActivityAnswer);
        }
        appActivityAnswerService.saveBatch(saveData);
    }

    private Long saveForId(AppActivityQuestion appActivityQuestion) {
        return appActivityQuestionDao.saveForId(appActivityQuestion);
    }

    private void setQuestionList(List<AppActivityQuestion> returnList, List<AppActivityQuestion> simpleList, Integer simpleQuestionSize) {

        if (simpleList.size() <= simpleQuestionSize) {
            returnList.addAll(simpleList);
        } else {
            for (int i = 0; i < simpleQuestionSize; i++) {
                int index = getAmount(0, simpleList.size());
                returnList.add(simpleList.get((index)));
                simpleList.remove(index);
            }
        }
    }

    private int getAmount(int minAmount, int maxAmount) {
        Random random = new Random();
        Integer i = random.nextInt(maxAmount) % (maxAmount - minAmount + 1) + minAmount;
        return i;
    }

    private Integer getQuestionSize(String key) {
        String multiple = String.valueOf(configureService.getValue(key));
        return Integer.valueOf(multiple);
    }
}
