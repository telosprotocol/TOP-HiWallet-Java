package com.topnetwork.wallet;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.topnetwork.wallet.common.enums.PushType;
import com.topnetwork.wallet.param.wallet.NoticeAddParam;
import org.junit.Test;

import com.topnetwork.common.HttpServer;
import com.topnetwork.common.TestHeader;
import com.topnetwork.common.HttpServer.Result;

import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.gitee.magic.framework.head.utils.TimeUtils;
import com.gitee.magic.core.json.JsonObject;

public class NoticeTest extends TestHeader {

    @Test
    public void test_find_notice_list() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("pageIndex", 1);
        content.put("pageSize", 10);
        content.put("title", 1);
        HttpServer server = new HttpServer();
        server.postRequest("/notice/find_notice_list", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_find_notice_detail() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("id", 1);
        HttpServer server = new HttpServer();
        server.postRequest("/notice/find_notice_detail", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_del_notice() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("id", 1);
        HttpServer server = new HttpServer();
        server.postRequest("/notice/del_notice", "0b4a2e8682ca47bc0d3d11d91cd09496", "I3UE2eVwwoXtn5iO23ADfMzZQFgN/XY8xxu+la/dc5qmxJIyt0ofXA==", content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }


    @Test
    public void test_add_notice() throws Exception {
//        for(int i =0;i<100;i++){
        NoticeAddParam param = new NoticeAddParam();
        param.setContent("参与staking，赢取36.5%的年化收益！最高还能获得3.1%的额外收益！");
        param.setEndTime(new Date());
        param.setEnglishContent("Join and keep staking get up to 3.1% total extra bonus!");
        param.setEnglishTitle("TOP Staking Is Available on HiWallet Now !");
        param.setNoticeType(PushType.ACTIVITY);
        param.setTitle("TOP Staking 上线了！(测试)");
        param.setStartTime(new Date());
//        param.setUrl("https://www.topstaking.io/");
        param.setUrl("http://47.114.128.142:3000/");
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/notice/add_notice", param);
//        }

    }

    @Test
    public void test_update_notice() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("id", 624302816545996800L);
        content.put("startTime", "20190919174414");
        content.put("endTime", "20190919174414");
        content.put("title", "对3");
        content.put("content", "第三方递四方速递qqqq");
        HttpServer server = new HttpServer();
        server.postRequest("/notice/update_notice", loginaccessid, loginaccesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    //
}
