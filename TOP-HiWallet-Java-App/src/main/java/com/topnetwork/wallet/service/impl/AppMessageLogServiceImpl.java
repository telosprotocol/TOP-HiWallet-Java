package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppMessageLogDao;
import com.topnetwork.wallet.entity.AppMessageLog;
import com.topnetwork.wallet.service.AppMessageLogService;

import java.util.Date;

@Service("appMessageLogService")
@Transactional
public class AppMessageLogServiceImpl extends SqlBaseServiceImpl<AppMessageLog, Long>
        implements AppMessageLogService {

    @SuppressWarnings("unused")
    private AppMessageLogDao appMessageLogDao;

    public AppMessageLogServiceImpl(@Qualifier("appMessageLogDao") AppMessageLogDao appMessageLogDao) {
        super(appMessageLogDao);
        this.appMessageLogDao = appMessageLogDao;
    }

    @Override
    public void saveLog(String mobile, String message, LanguageEnum languageEnum) {
        AppMessageLog appMessageLog = new AppMessageLog();
        appMessageLog.setLanguage(languageEnum);
        appMessageLog.setMessage(message);
        appMessageLog.setMobile(mobile);
        appMessageLog.setCreatedDate(new Date());
        appMessageLog.setModifiedDate(new Date());
        save(appMessageLog);
    }
}
