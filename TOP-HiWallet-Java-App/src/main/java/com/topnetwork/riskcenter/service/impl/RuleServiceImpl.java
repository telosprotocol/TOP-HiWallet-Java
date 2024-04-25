package com.topnetwork.riskcenter.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.riskcenter.dao.RuleDao;
import com.topnetwork.riskcenter.entity.Rule;
import com.topnetwork.riskcenter.service.RuleService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.List;

@Service("ruleService")
public class RuleServiceImpl extends SqlBaseServiceImpl<Rule, Long>
        implements RuleService {

    @SuppressWarnings("unused")
    private RuleDao ruleDao;

    public RuleServiceImpl(@Qualifier("ruleDao") RuleDao ruleDao) {
        super(ruleDao);
        this.ruleDao = ruleDao;
    }

    @Override
    public List<Rule> findAllAvailable() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("disable", "false");
        return ruleDao.queryForList(wrapper);
    }
}
