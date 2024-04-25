package com.topnetwork.wallet;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.topnetwork.common.HttpServer;
import com.topnetwork.common.TestHeader;
import com.topnetwork.common.HttpServer.Result;

import com.gitee.magic.core.json.JsonObject;

public class HelpTest  extends TestHeader {

    @Test
    public void test_findHelpList() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("pageIndex", 1);
        content.put("pageSize", 10);
        content.put("category1Id", 1);
        content.put("category2Id", 7);
        content.put("language", 1);
        content.put("title", "对");
        HttpServer server = new HttpServer();
        server.postRequest("/help/find_help_list", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_findHelpDetail() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("id", 624262676452737024L);
        HttpServer server = new HttpServer();
        server.postRequest("/help/find_help_detail", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_updateHelp() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("id", 623975611618033664L);
        content.put("stCategoryId", 1);
        content.put("ndCategoryId", 7);
        content.put("language", 1);
        content.put("title", "对");
        content.put("content", "第三方递四方速递");
        HttpServer server = new HttpServer();
        server.postRequest("/help/update_help", loginaccessid, loginaccesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }


    @Test
    public void test_addHelp() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("stCategoryId", 1);
        content.put("ndCategoryId", 7);
        content.put("language", 1);
        content.put("title", "对2");
        content.put("content", "第三方递四方速递qqqq");
        HttpServer server = new HttpServer();
        server.postRequest("/help/add_help", loginaccessid, loginaccesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_delHelp() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("id", 623975611618033664L);
        HttpServer server = new HttpServer();
        server.postRequest("/help/del_help", loginaccessid, loginaccesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    //find_fist_help_for_wallet

    @Test
    public void test_find_fist_help_for_wallet() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("id", 1);
        HttpServer server = new HttpServer();
        server.postRequest("/help/find_fist_help_for_wallet", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }
    @Test
    public void test_find_sec_help_for_wallet() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("id", 624262765963378688L);
        HttpServer server = new HttpServer();
        server.postRequest("/help/find_sec_help_for_wallet", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_search() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("pageIndex", 1);
        content.put("pageSize", 10);
        content.put("key", "什么");
        HttpServer server = new HttpServer();
        server.postRequest("/help/search", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_find_category_list() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("level", 1);
//        content.put("pageSize", 10);
//        content.put("data.key", "什么");
        HttpServer server = new HttpServer();
        server.postRequest("/help_category/find_category_list", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

}
