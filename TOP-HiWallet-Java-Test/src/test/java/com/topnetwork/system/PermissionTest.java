package com.topnetwork.system;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.topnetwork.common.HttpServer;
import com.topnetwork.common.TestHeader;

public class PermissionTest extends TestHeader {

    @Test
    public void test_sys_permission_all_list() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        HttpServer server = new HttpServer();
        server.postRequest("/permission/all_list", manageraccessid, manageraccesskey, content);
    }

}
