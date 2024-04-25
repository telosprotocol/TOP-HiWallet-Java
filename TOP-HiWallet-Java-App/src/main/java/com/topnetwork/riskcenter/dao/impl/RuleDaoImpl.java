package com.topnetwork.riskcenter.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.riskcenter.dao.RuleDao;
import com.topnetwork.riskcenter.entity.Rule;

@Repository("ruleDao")
public class RuleDaoImpl extends SqlBaseDaoImpl<Rule,Long>implements RuleDao {

	public RuleDaoImpl() {
		super(Rule.class);
	}

}
