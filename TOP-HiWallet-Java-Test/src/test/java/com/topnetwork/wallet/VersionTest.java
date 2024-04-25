package com.topnetwork.wallet;

import com.topnetwork.common.HttpServer;
import com.topnetwork.common.TestHeader;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.common.enums.VersionManagerStatus;
import com.topnetwork.wallet.param.wallet.VersionAddParam;
import com.topnetwork.wallet.param.wallet.VersionDelParam;
import com.topnetwork.wallet.param.wallet.VersionListParam;
import com.topnetwork.wallet.param.wallet.config.AppConfigParam;
import com.topnetwork.wallet.param.wallet.v3.VersionCheckParam;
import com.topnetwork.wallet.result.wallet.VersionListResult;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.gitee.magic.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class VersionTest extends TestHeader {

    @Test
    public void test_check_update() throws Exception {

        VersionCheckParam param = new VersionCheckParam();
        param.setVersion("2.1.2");
        param.setRegion(RegionEnum.COMMON);
        param.setLanguage(LanguageEnum.CN);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v4/app/check_update", param);
    }

    @Test
    public void test_check_update_v4() throws Exception {

        VersionCheckParam param = new VersionCheckParam();
        param.setVersion("1.10.0");
        param.setRegion(RegionEnum.OVERSEAS);
        param.setLanguage(LanguageEnum.CN);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v4/app/check_update", param);
    }

    @Test
    public void test_find_app_config() throws Exception {

        AppConfigParam param = new AppConfigParam();
        param.setVersion("1.4.3");
        param.setRegion(RegionEnum.COMMON);
        param.setLanguage(LanguageEnum.CN);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/config/find_app_config", param);
    }

    @Test
    public void test_add_version_update() throws Exception {
        VersionAddParam param = new VersionAddParam();
        param.setCanUpdate(true);
        param.setDescription("多少给多少");
        param.setTitle("aaa");
        param.setEnglishTitle("aaa");
        param.setDiscover(true);
        param.setDownloadUrl("https;//aaaa.com");
        param.setEnglishDescription("asasd");
        param.setIsForcedUpdates(0);
        param.setType(VersionManagerStatus.FRAME);
        param.setPlatform(PlatformEnum.IOS);
        param.setRegion(RegionEnum.COMMON);
        param.setStaking(true);
        param.setVersion("1.8.0");
        param.setApproved(false);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/version/add_version_update", param);
    }

    @Test
    public void test_update_app_version() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("description", "aaa");
        content.put("isForcedUpdates", 0);
        content.put("id", 624604166173491200L);
        HttpServer server = new HttpServer();
        server.postRequest("/version/update_app_version", loginaccessid, loginaccesskey, content, new HttpServer.Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_del_app_version() throws Exception {

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("id", 624570427565408256L);
        HttpServer server = new HttpServer();
        server.postRequest("/version/del_app_version", loginaccessid, loginaccesskey, content, new HttpServer.Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_find_app_version_list() throws Exception {
        VersionListParam param = new VersionListParam();
        param.setVersion(null);
        param.setPageIndex(1);
        param.setPageSize(10);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityListResult("/version/find_app_version_list", param, VersionListResult.class);
    }

    @Test
    public void test_find_app_version_detail() throws Exception {
        VersionDelParam param = new VersionDelParam();
        param.setId(0L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityResult("/version/find_app_version_detail", param, VersionListResult.class);
    }
}
