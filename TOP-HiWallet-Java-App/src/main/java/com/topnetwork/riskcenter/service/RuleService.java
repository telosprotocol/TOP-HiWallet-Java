package com.topnetwork.riskcenter.service;

import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.riskcenter.entity.Rule;

import java.util.List;

public interface RuleService extends SqlBaseService<Rule,Long> {

    List<Rule> findAllAvailable();
}
