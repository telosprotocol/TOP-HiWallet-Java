package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppMessageLog;

public interface AppMessageLogService extends SqlBaseService<AppMessageLog, Long> {

    void saveLog(String mobile, String message, LanguageEnum languageEnum);
}
