package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.common.enums.discover.*;
import com.topnetwork.wallet.param.wallet.config.AppConfigParam;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.*;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DiscoverNewTest extends TestHeader {


    @Test
    public void test_get_recommend_games() throws Exception {

        GetBannersV4Param param = new GetBannersV4Param();
        param.setLanguage(LanguageEnum.EN);
        param.setVersion("1.11.0");
        param.setRegion(RegionEnum.DOMESTIC);
        param.setUid("b430f59d8b862829e2b16efc670a30c8e349b280f6cedd4fade257bb165b08a2");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/v1/app/discover_new/get_recommend_list", param);
    }

    @Test
    public void test_get_language() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityBase("/discover_new/get_language", null);
    }

    @Test
    public void test_find_app_config() throws Exception {

        AppConfigParam param = new AppConfigParam();
        param.setLanguage(LanguageEnum.CN);
        param.setVersion("1.8.0");
        param.setRegion(RegionEnum.COMMON);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/config/find_app_config", param);
    }


    @Test
    public void test_add_used_history() throws Exception {
        AddNewUsedHistoryParam param = new AddNewUsedHistoryParam();
        param.setUid("dsdsfdsfdf");
        param.setDappId(5415616L);
        param.setLanguage(LanguageEnum.CN);
        param.setType(BannerNewType.APP);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/discover_new/add_used_history", param);
    }

    @Test
    public void test_get_banners() throws Exception {

        GetBannersV4Param param = new GetBannersV4Param();
        param.setLanguage(LanguageEnum.CN);
        param.setVersion("1.8.0");
        param.setRegion(RegionEnum.COMMON);
        param.setUid("bbb0");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/v1/app/discover_new/get_banners", param);
    }

    @Test
    public void test_get_discover_games_page() throws Exception {
        RecommendPageNewParam param = new RecommendPageNewParam();
        param.setLanguage(LanguageEnum.CN);
        param.setGroupId(54543443L);
        param.setPageIndex(1);
        param.setPageSize(8);
        param.setVersion("1.4.4");
        param.setUid("dsdsfdsfdf");
        param.setRegion(RegionEnum.COMMON);
        RestfulTemplate template = new RestfulTemplate(HOST, clientaccessid, clientaccesskey);
        template.getForEntityBase("/v1/app/discover_new/get_discover_all", param);
    }

    @Test
    public void test_get_app_detail() throws Exception {

        GetAppDetailParam param = new GetAppDetailParam();
        param.setLanguage(LanguageEnum.CN);
        param.setDappId(5415616L);
        param.setType(BannerNewType.APP);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/v1/app/discover_new/get_app_detail", param);
    }

    @Test
    public void test_update_banner() throws Exception {

        BannerUpdateParam param = new BannerUpdateParam();
        param.setId(1l);
        param.setActionUrl("www.baidu.com");
        param.setBannerOrder(0);
        param.setBannerShow(true);
        param.setBannerUrl("2ef6034bab04706dfc6535425e4cd1a4");
        param.setChainType(ChainTypeEnum.TOP);
        param.setEndTime(new Date("Thu Mar 26 10:09:24 CST 2020"));
        param.setEnglishBannerUrl("11d7b9d7e09c9ec1912b3751a430448b");
        param.setEnglishTitle("dsfs");
        param.setStartTime(new Date("Fri Mar 06 10:09:21 CST 2020"));
        param.setTargetId(233l);
        param.setTitle("水电费收费");
        param.setType(BannerType.HTML);
        RestfulTemplate template = new RestfulTemplate(HOST, "052da2118cb7260e31a4d0721224f2ff", "ZDM0OTlmMjFmNDJhZjZiYWNjODllNzI2N2IxODI0MDI=");
        template.postForEntityBase("/discover_banner/update_banner", param);
    }

    @Test
    public void test_add_group() throws Exception {

        AddGroupParam param = new AddGroupParam();
        param.setLanguage(LanguageEnum.CN);
        param.setGroupOrder(0);
        param.setGroupShow(true);
        param.setName("测试");
        param.setShowType(DappShowType.examine);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/discover_group/add_group", param);
    }

    @Test
    public void test_upd_group() throws Exception {

        UpdateGroupParam param = new UpdateGroupParam();
        param.setLanguage(LanguageEnum.CN);
        param.setGroupOrder(0);
        param.setGroupShow(true);
        param.setName("测试");
        param.setId(712373697305903104L);
        param.setShowType(DappShowType.all);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.putForEntityBase("/discover_group/update_group", param);
    }

    @Test
    public void test_get_group() throws Exception {

        GetGroupsParam param = new GetGroupsParam();
        param.setLanguage(LanguageEnum.CN);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityPage("/discover_group/get_groups", param, GetGroupsResult.class);
    }


    @Test
    public void test_add_label() throws Exception {

        AddLabelParam param = new AddLabelParam();
        param.setLabelType(LabelType.APPLICATION);
        List<AddLabelI18nParam> list = new ArrayList<>();
        AddLabelI18nParam addLabelI18nParam = new AddLabelI18nParam();
        addLabelI18nParam.setLanguage(LanguageEnum.CN);
        addLabelI18nParam.setName("dsfgd");
        list.add(addLabelI18nParam);
        param.setNames(list);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/discover_label/add_label", param);
    }

    @Test
    public void test_upd_label() throws Exception {

        UpdateLabelParam param = new UpdateLabelParam();
        param.setLabelType(LabelType.APPLICATION);
        param.setLabelId(712380813974437888L);
        List<UpdLabelI18nParam> list = new ArrayList<>();
        UpdLabelI18nParam addLabelI18nParam = new UpdLabelI18nParam();
        addLabelI18nParam.setLanguage(LanguageEnum.CN);
        addLabelI18nParam.setName("测试");
        addLabelI18nParam.setI18nId(712380814112849920L);
        list.add(addLabelI18nParam);
        param.setNames(list);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.putForEntityBase("/discover_label/update_label", param);
    }

    @Test
    public void test_get_labels() throws Exception {

        GetLabelsParam param = new GetLabelsParam();
        param.setLabelType(LabelType.APPLICATION);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityPage("/discover_label/get_labels", param, GetLabelsResult.class);
    }

    @Test
    public void test_get_app_list() throws Exception {

        GetAppListParam param = new GetAppListParam();
        param.setAppType(AppType.DAPP);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityPage("/discover_new/get_app_list", param, GetLabelsResult.class);
    }


    @Test
    public void test_get_all_group() throws Exception {

        GetAllGroupParam param = new GetAllGroupParam();
        param.setLanguage(LanguageEnum.CN);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityListResult("/discover_group/get_all_group", param, GetLabelsResult.class);
    }

    @Test
    public void test_get_all_label() throws Exception {

        GetAllLabelParam param = new GetAllLabelParam();
        param.setLabelType(LabelType.DAPP);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityListResult("/discover_label/get_all_label", param, GetLabelsResult.class);
    }

    @Test
    public void test_get_pro_i18n() throws Exception {

        GetAppDetailBaseParam param = new GetAppDetailBaseParam();
        param.setId(5616556165165L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityListResult("/discover_new/get_pro_i18n", param, ProI18nResult.class);
    }

    @Test
    public void test_get_dapp_i18n() throws Exception {

        GetAppDetailBaseParam param = new GetAppDetailBaseParam();
        param.setId(15615616516L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityListResult("/discover_new/get_dapp_i18n", param, DappI18nResult.class);
    }

    @Test
    public void test_edit_dapp_i18n() throws Exception {

        EditDappI18nParam param = new EditDappI18nParam();
        List<GroupI18nParam> groups = new ArrayList<>();
        GroupI18nParam groupI18nParam = new GroupI18nParam();
        groupI18nParam.setGroupId(54543443L);
        groupI18nParam.setShowOrder(4);
        groupI18nParam.setShowToHome(true);
        groups.add(groupI18nParam);
        List<DappI18nParam> i18n = new ArrayList<>();
        DappI18nParam dappI18nParam = new DappI18nParam();
        dappI18nParam.setDesc("一元夺宝");
        dappI18nParam.setDetail("赢取奖励");
        dappI18nParam.setImageUrl(null);
        dappI18nParam.setGroups(groups);
        dappI18nParam.setI18nId(4344542543L);
        dappI18nParam.setLanguage(LanguageEnum.CN);
        dappI18nParam.setSubTitle("一元夺宝");
        dappI18nParam.setTitle("一元夺宝");
        i18n.add(dappI18nParam);
        param.setI18n(i18n);
        param.setAppId(15615616516L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/discover_new/edit_dapp_i18n", param);
    }

    @Test
    public void test_get_banner_list() throws Exception {

        GetBannerListParam param = new GetBannerListParam();
        param.setPageIndex(1);
        param.setPageSize(10);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityPage("/discover_new/get_banner_list", param, GetBannerListResult.class);
    }

    @Test
    public void test_add_dapp() throws Exception {

        AddDappParam param = new AddDappParam();
        param.setAppShow(true);
        param.setChainType(ChainTypeEnum.ETH);
        param.setImageUrl("8291917c5dc98cbde7d9f59798aae6cb");
        AddDappLabelParam addDappLabelParam = new AddDappLabelParam();
        addDappLabelParam.setLabelId(320330303L);
        param.setLabels(Arrays.asList(addDappLabelParam));
        param.setTitle("测试url");
        param.setUrl("hs://www.baidu.com?param=aaa");
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.putForEntityBase("/discover_new/add_dapp", param);
    }


}
