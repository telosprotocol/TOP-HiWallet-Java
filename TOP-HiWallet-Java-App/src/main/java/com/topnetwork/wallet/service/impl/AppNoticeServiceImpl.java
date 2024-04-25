package com.topnetwork.wallet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.topnetwork.jpush.PushMessage;
import com.topnetwork.jpush.PushService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.param.wallet.v2.NoticeDetailV2Param;
import com.topnetwork.wallet.param.wallet.v2.NoticeListV2Param;
import com.topnetwork.wallet.result.wallet.NoticeDetailV2Result;
import com.topnetwork.wallet.result.wallet.NoticeListV2Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.core.mvc.business.CommonBusiness;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.dao.AppNoticeDao;
import com.topnetwork.wallet.entity.AppNotice;
import com.topnetwork.wallet.param.wallet.NoticeAddParam;
import com.topnetwork.wallet.param.wallet.NoticeDelParam;
import com.topnetwork.wallet.param.wallet.NoticeDetailParam;
import com.topnetwork.wallet.param.wallet.NoticeListParam;
import com.topnetwork.wallet.param.wallet.NoticeUpdParam;
import com.topnetwork.wallet.result.wallet.NoticeDetailResult;
import com.topnetwork.wallet.result.wallet.NoticeListResult;
import com.topnetwork.wallet.service.AppNoticeService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;

@Service("appNoticeService")
@Transactional
public class AppNoticeServiceImpl extends SqlBaseServiceImpl<AppNotice, Long>
        implements AppNoticeService {

    private AppNoticeDao appNoticeDao;

    @Autowired
    private PushService pushService;

    public AppNoticeServiceImpl(@Qualifier("appNoticeDao") AppNoticeDao appNoticeDao) {
        super(appNoticeDao);
        this.appNoticeDao = appNoticeDao;
    }


    @Override
    public QueryResult<List<NoticeListResult>> findNoticeList(NoticeListParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        return queryForPage(NoticeListResult.class, "findNoticeList", params);
    }

    @Override
    public NoticeDetailResult findNoticeDetail(NoticeDetailParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getId());
        AppNotice appNotice = get(appNoticeDao.queryForList(wrapper));
        NoticeDetailResult noticeDetailResult = new NoticeDetailResult();
        BeanUtils.copyProperties(appNotice, noticeDetailResult);
        return noticeDetailResult;
    }

    @Override
    public NoticeDetailV2Result findNoticeDetail(NoticeDetailV2Param param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getId());
        AppNotice appNotice = get(appNoticeDao.queryForList(wrapper));

        if (appNotice == null) {
            throw new BusinessException(CodeRes.CODE_14001);
        }

        NoticeDetailV2Result noticeDetailResult = new NoticeDetailV2Result();
        BeanUtils.copyProperties(appNotice, noticeDetailResult);
        if (param.getLanguage().equals(LanguageEnum.CN)) {
            noticeDetailResult.setContent(appNotice.getContent());
            noticeDetailResult.setTitle(appNotice.getTitle());
        } else {
            noticeDetailResult.setContent(appNotice.getEnglishContent());
            noticeDetailResult.setTitle(appNotice.getEnglishTitle());
        }
        return noticeDetailResult;
    }

    @Override
    public void delNotice(NoticeDelParam param) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("id", param.getId());
        appNoticeDao.executeUpdate(wrapper);
    }

    @Override
    public void updateNotice(NoticeUpdParam param, User user) {
        AppNotice notice = new AppNotice();
        notice.setId(param.getId());
        notice.setContent(param.getContent());
        notice.setEnglishContent(param.getEnglishContent());
        notice.setEnglishTitle(param.getEnglishTitle());
        notice.setTitle(param.getTitle());
        notice.setStartTime(param.getStartTime() == null ? new Date() : param.getStartTime());
        notice.setEndTime(param.getEndTime() == null ? new Date() : param.getEndTime());
        notice.setUpdateId(user.getId());
        notice.setUpdateTime(new Date());
        save(notice);
    }

    @Override
    public void addNotice(NoticeAddParam param, User user) {
        AppNotice notice = new AppNotice();
        notice.setContent(param.getContent());
        notice.setEnglishContent(param.getEnglishContent());
        notice.setTitle(param.getTitle());
        notice.setEnglishTitle(param.getEnglishTitle());
        notice.setStartTime(param.getStartTime() == null ? new Date() : param.getStartTime());
        notice.setEndTime(param.getEndTime() == null ? new Date() : param.getEndTime());
        notice.setCreateId(user.getId());
        notice.setCreateTime(new Date());
        notice.setNoticeType(param.getNoticeType());
        notice.setUrl(param.getUrl());
        save(notice);
        PushMessage pushMessage = new PushMessage();
        BeanUtils.copyProperties(notice, pushMessage);
        pushService.broadcastPush(pushMessage);
    }

    @Override
    public NoticeListV2Result findNoticeListForApp(NoticeListV2Param param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.gt("createTime", param.getDate());
        List<AppNotice> list = appNoticeDao.queryForList(wrapper);
        NoticeListV2Result noticeListV2Result = new NoticeListV2Result();
        List<NoticeListV2Result.Extras> extras = new ArrayList<>();
        for (AppNotice appNotice : list) {
            NoticeListV2Result.Extras extras1 = new NoticeListV2Result.Extras();
            extras1.setId(appNotice.getId());
            extras1.setType(appNotice.getNoticeType());
            extras1.setUrl(appNotice.getUrl());
            extras1.setTime(appNotice.getCreateTime().getTime());
            if (param.getLanguage().equals(LanguageEnum.CN)) {
                extras1.setDesc(appNotice.getContent());
                extras1.setTitle(appNotice.getTitle());
            } else {
                extras1.setDesc(appNotice.getEnglishContent());
                extras1.setTitle(appNotice.getEnglishTitle());
            }
            extras.add(extras1);
        }
        noticeListV2Result.setNotice(extras);
        return noticeListV2Result;
    }
}
