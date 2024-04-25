package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.common.enums.discover.BannerType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.topnetwork.wallet.common.enums.discover.LabelType;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.GetGroupsResult;
import com.topnetwork.wallet.result.wallet.discover.GetLabelsResult;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiscoverTest extends TestHeader {

    @Test
    public void test_get_recommend_games() throws Exception {

        RecommendGamesParam param = new RecommendGamesParam();
        param.setLanguage(LanguageEnum.EN);
        param.setVersion("1.5.0");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/discover/get_recommend_games", param);
    }

    @Test
    public void test_get_recommend_games_v3() throws Exception {

        RecommendGamesV3Param param = new RecommendGamesV3Param();
        param.setLanguage(LanguageEnum.EN);
        param.setVersion("1.4.0");
        param.setRegion(RegionEnum.COMMON);
        param.setUid("dsdsfdsfdf433");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/v3/app/discover/get_recommend_list", param);
    }

    @Test
    public void test_get_discover_games_page_v3() throws Exception {
        RecommendPageV3Param param = new RecommendPageV3Param();
        param.setLanguage(LanguageEnum.CN);
        param.setClassifyId(1L);
        param.setPageIndex(1);
        param.setPageSize(8);
        param.setVersion("1.4.4");
        param.setRegion(RegionEnum.COMMON);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/v3/app/discover/get_discover_all", param);
    }

    @Test
    public void test_add_used_history() throws Exception {
        AddUsedHistoryParam param = new AddUsedHistoryParam();
        param.setUid("dsdsfdsfdf");
        param.setDappId(12L);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v3/app/discover/add_used_history", param);
    }


    @Test
    public void test_get_discover_games_page() throws Exception {

        RecommendPageParam param = new RecommendPageParam();
        param.setLanguage(null);
        param.setClassifyId(1l);
        param.setPageIndex(1);
        param.setPageSize(8);
        param.setVersion("1.4.1");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/discover/get_discover_games_page", param);
    }

    @Test
    public void test_get_discover_switch() throws Exception {

        DiscoverSwitchParam param = new DiscoverSwitchParam();
        param.setVersion("1.4.2");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/discover/get_discover_switch", param);
    }


    @Test
    public void test_get_banners() throws Exception {

        GetBannersParam param = new GetBannersParam();
        param.setLanguage(LanguageEnum.CN);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/discover/get_banners", param);
    }

    @Test
    public void test_get_banners_v3() throws Exception {

        GetBannersV3Param param = new GetBannersV3Param();
        param.setLanguage(LanguageEnum.CN);
        param.setVersion("1.6.1");
        param.setRegion(RegionEnum.COMMON);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/v3/app/discover/get_banners", param);
    }

    @Test
    public void test_get_banners_v4() throws Exception {

        GetBannersV4Param param = new GetBannersV4Param();
        param.setLanguage(LanguageEnum.CN);
        param.setVersion("1.6.2");
        param.setRegion(RegionEnum.COMMON);
        param.setUid("bbb0");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/v4/app/discover/get_banners", param);
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


}
