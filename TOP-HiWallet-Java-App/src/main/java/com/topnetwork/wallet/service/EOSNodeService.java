package com.topnetwork.wallet.service;

import com.topnetwork.wallet.entity.EOSNode;
import com.topnetwork.wallet.param.wallet.EOSAddNodeParam;
import com.topnetwork.wallet.param.wallet.EOSDelNodeParam;
import com.topnetwork.wallet.param.wallet.EOSGetNodesParam;
import com.topnetwork.wallet.param.wallet.EOSUpdNodeParam;
import com.topnetwork.wallet.param.wallet.v1.GetEOSNodeParam;
import com.topnetwork.wallet.result.wallet.EOSManageNodeResult;
import com.topnetwork.wallet.result.wallet.GetEOSNodeResult;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;

import java.util.List;

public interface EOSNodeService extends SqlBaseService<EOSNode, Long> {

    QueryResult<List<GetEOSNodeResult>> getEosNodes(GetEOSNodeParam param);

    QueryResult<List<EOSManageNodeResult>> getEosNodes(EOSGetNodesParam param);

    void addEosNode(EOSAddNodeParam param);

    void deleteEosNode(EOSDelNodeParam param);

    void updateEosNode(EOSUpdNodeParam param);
}
