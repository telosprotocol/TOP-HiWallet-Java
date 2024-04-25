package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.topnetwork.wallet.param.wallet.home.*;
import com.topnetwork.wallet.result.wallet.discover.GetNewBannersResult;
import com.topnetwork.wallet.result.wallet.home.AppListResult;
import com.topnetwork.wallet.result.wallet.home.AppSelectListResult;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;


/**
 * @ClassName HomeTest
 * @Description
 * @Author bran
 * @Date 2020/69/24 19:47
 */
public class HomeTest extends TestHeader {

    @Test
    public void test_get_app_list() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityListResult("/home/get_app_list", null, AppListResult.class);
    }

    @Test
    public void test_get_select_app_list() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        GetSelectAppParam param = new GetSelectAppParam();
        param.setAppType(AppType.DAPP);
        template.getForEntityListResult("/home/get_select_app_list", param, AppSelectListResult.class);
    }

    @Test
    public void test_add_app() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        AddAppParam param = new AddAppParam();
        param.setAppType(AppType.DAPP);
        param.setAppId(2132172L);
        param.setAppOrder(4);
        param.setShowType(DappShowType.normal);
        template.postForEntityBase("/home/add_app", param);
    }

    @Test
    public void test_add_del() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        DelAppParam param = new DelAppParam();
        param.setId(759077706733191168L);
        template.deleteForEntityBase("/home/del_app", param);
    }

    @Test
    public void test_upd_app() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        UpdAppParam param = new UpdAppParam();
        param.setAppType(AppType.DAPP);
        param.setAppId(2132172L);
        param.setAppOrder(4);
        param.setShowType(DappShowType.normal);
        param.setId(759077706733191168L);
        template.putForEntityBase("/home/upd_app", param);
    }

    @Test
    public void test_get_app_list2() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        GetAppListParam param = new GetAppListParam();
        param.setLanguage(LanguageEnum.CN);
        param.setRegion(RegionEnum.OVERSEAS);
        param.setVersion("1.12.0.apk");
        template.getForEntityListResult("/v1/app/home/get_app_list", param, GetNewBannersResult.class);
    }
}
