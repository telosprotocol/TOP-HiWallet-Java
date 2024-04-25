package com.topnetwork.wallet.service;

import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.param.wallet.v2.NoticeDetailV2Param;
import com.topnetwork.wallet.param.wallet.v2.NoticeListV2Param;
import com.topnetwork.wallet.result.wallet.NoticeDetailV2Result;
import com.topnetwork.wallet.result.wallet.NoticeListV2Result;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppNotice;
import com.topnetwork.wallet.param.wallet.*;
import com.topnetwork.wallet.result.wallet.NoticeDetailResult;
import com.topnetwork.wallet.result.wallet.NoticeListResult;

import java.util.List;

public interface AppNoticeService extends SqlBaseService<AppNotice, Long> {

    QueryResult<List<NoticeListResult>> findNoticeList(NoticeListParam param);

    NoticeDetailResult findNoticeDetail(NoticeDetailParam param);

    NoticeDetailV2Result findNoticeDetail(NoticeDetailV2Param param);

    void delNotice(NoticeDelParam param);

    void updateNotice(NoticeUpdParam param, User user);

    void addNotice(NoticeAddParam param, User user);

    NoticeListV2Result findNoticeListForApp(NoticeListV2Param param);
}
