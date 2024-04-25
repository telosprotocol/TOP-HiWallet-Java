package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.param.wallet.EOSAddNodeParam;
import com.topnetwork.wallet.param.wallet.EOSDelNodeParam;
import com.topnetwork.wallet.param.wallet.EOSGetNodesParam;
import com.topnetwork.wallet.param.wallet.EOSUpdNodeParam;
import com.topnetwork.wallet.param.wallet.v1.GetEOSNodeParam;
import com.topnetwork.wallet.result.wallet.EOSManageNodeResult;
import com.topnetwork.wallet.result.wallet.GetEOSNodeResult;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

/**
 * @ClassName EOSTest
 * @Description
 * @Author bran
 * @Date 2020/4/18 14:07
 */
public class EOSTest extends TestHeader {

    @Test
    public void test_add_eos_node() throws Exception {
        EOSAddNodeParam param = new EOSAddNodeParam();
        param.setComment("测试");
        param.setDisable(false);
        param.setLocation("USA");
        param.setNodeName("测试node");
        param.setNodeOrder(0);
        param.setNodeUrl("https://www.baidu.com");
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/eos/add_eos_node", param);

    }

    @Test
    public void test_del_eos_node() throws Exception {

        EOSDelNodeParam param = new EOSDelNodeParam();
        param.setId(701074413335871488L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.deleteForEntityBase("/eos/delete_eos_node", param);

    }

    @Test
    public void test_upd_eos_node() throws Exception {

        EOSUpdNodeParam param = new EOSUpdNodeParam();
        param.setId(701075663003582464L);
        param.setComment("测试");
        param.setDisable(false);
        param.setLocation("USA");
        param.setNodeName("测试node");
        param.setNodeOrder(0);
        param.setNodeUrl("https://www.baidu.com");
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.putForEntityBase("/eos/update_eos_node", param);

    }

    @Test
    public void test_find_eos_node_wallet() throws Exception {

        GetEOSNodeParam param = new GetEOSNodeParam();
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityPage("/v1/app/eos/get_eos_nodes", param, GetEOSNodeResult.class);

    }

    @Test
    public void test_find_eos_node_manager() throws Exception {

        EOSGetNodesParam param = new EOSGetNodesParam();
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityPage("/eos/get_eos_nodes", param, EOSManageNodeResult.class);

    }

}
