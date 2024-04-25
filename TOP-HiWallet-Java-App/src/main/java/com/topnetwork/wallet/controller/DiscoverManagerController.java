package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.*;
import com.topnetwork.wallet.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DiscoverManagerController
 * @Description
 * @Author bran
 * @Date 2020/5/21 11:36
 */
@RestController
@RequestMapping
@Tag(name = "新版发现管理后台api")
public class DiscoverManagerController extends BaseController {

    @Autowired
    private AppDiscoverGroupService appDiscoverGroupService;
    @Autowired
    private AppDiscoverLabelService appDiscoverLabelService;


    @Operation(summary= "0、查询钱包支持语言")
    @AuthenticationCheck
    @GetMapping(value = "/discover_new/get_language")
    public ResultResponse<List<LanguageEnum>> getLanguage() {
        return response(appDiscoverBannerNewService.getLanguage());
    }

    @Autowired
    private AppDiscoverBannerNewService appDiscoverBannerNewService;

    @Operation(summary= "1、查找banner列表接口-分页 get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_new/get_banner_list")
    public PageResponse<List<GetBannerListResult>> getBannerList(@ValidRequestParam GetBannerListParam param) {
        return response(appDiscoverBannerNewService.getBannerList(param));
    }

    @Operation(summary= "2、查找banner详情接口- get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_new/get_banner_detail")
    public ResultResponse<GetBannerDetailResult> getBannerDetail(@ValidRequestParam GetBannerDetailParam param) {
        return response(appDiscoverBannerNewService.getBannerDetail(param));
    }

    @Operation(summary= "3、添加banner- post")
    @AuthenticationCheck
    @PostMapping(value = "/discover_new/add_banner")
    public BaseResponse addBanner(@RequestBody BannerNewAddParam param) {
        appDiscoverBannerNewService.addBanner(param);
        return response();
    }

    @Operation(summary= "4、修改banner- put")
    @AuthenticationCheck
    @PutMapping(value = "/discover_new/update_banner")
    public BaseResponse updateBanner(@RequestBody BannerNewUpdParam param) {
        appDiscoverBannerNewService.updateBanner(param);
        return response();
    }

    @Operation(summary= "5、banner国际化信息获取- get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_new/get_banner_i18n")
    public ResultResponse<List<BannerI18nResult>> getBannerI18n(@ValidRequestParam GetBannerDetailParam param) {
        return response(appDiscoverBannerNewService.getBannerI18n(param));
    }

    @Operation(summary= "6、banner国际化编辑- post")
    @AuthenticationCheck
    @PostMapping(value = "/discover_new/edit_banner_i18n")
    public BaseResponse editBannerI18n(@RequestBody EditBannerI18nParam param) {
        appDiscoverBannerNewService.editBannerI18n(param);
        return response();
    }

    @Operation(summary= "7、查找应用列表接口-分页 get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_new/get_app_page")
    public PageResponse<List<GetAppListResult>> getAppPage(@ValidRequestParam GetAppListParam param) {
        return response(appDiscoverBannerNewService.getAppList(param));
    }

    @Operation(summary= "7.5、查找应用列表接口 get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_new/get_app_list")
    public ResultResponse<List<GetAppListResult>> getAppList(@ValidRequestParam GetAppListAllParam param) {
        return response(appDiscoverBannerNewService.getAppListAll(param));
    }

    @Operation(summary= "8、查找所有分组-get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_group/get_all_group")
    public ResultResponse<List<GetAllGroupResult>> getAllGroup(@ValidRequestParam GetAllGroupParam param) {
        return response(appDiscoverGroupService.getAllGroup(param));
    }

    @Autowired
    private AppDiscoverProService appDiscoverProService;

    @Operation(summary= "9、查找专业内容详情-get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_group/get_pro_detail")
    public ResultResponse<GetAppDetailBaseResult> getProDetail(@ValidRequestParam GetAppDetailBaseParam param) {
        return response(appDiscoverProService.getProDetail(param));
    }


    @Operation(summary= "10、修改专业内容- put")
    @AuthenticationCheck
    @PutMapping(value = "/discover_new/update_pro_app")
    public BaseResponse updateProApp(@RequestBody UpdateProAppParam param) {
        appDiscoverProService.updateProApp(param);
        return response();
    }

    @Operation(summary= "11、专业内容国际化信息获取- get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_new/get_pro_i18n")
    public ResultResponse<List<ProI18nResult>> getProI18n(@ValidRequestParam GetAppDetailBaseParam param) {
        return response(appDiscoverProService.getProI18n(param));
    }

    @Operation(summary= "12、专业内容国际化编辑- post")
    @AuthenticationCheck
    @PostMapping(value = "/discover_new/edit_pro_i18n")
    public BaseResponse editProI18n(@RequestBody EditProI18nParam param) {
        appDiscoverProService.editProI18n(param);
        return response();
    }


    @Operation(summary= "13、查找所有标签-get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_label/get_all_label")
    public ResultResponse<List<GetAllLabelResult>> getAllLabel(@ValidRequestParam GetAllLabelParam param) {
        return response(appDiscoverLabelService.getAllLabel(param));
    }

    @Autowired
    private AppDiscoverDappService appDiscoverDappService;

    /**
     * dapp
     */
    @Operation(summary= "14、查找dapp内容详情-get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_group/get_dapp_detail")
    public ResultResponse<GetDappDetailResult> getDappDetail(@ValidRequestParam GetAppDetailBaseParam param) {
        return response(appDiscoverDappService.getDappDetail(param));
    }

    @Operation(summary= "15、dapp内容增加- post")
    @AuthenticationCheck
    @PutMapping(value = "/discover_new/add_dapp")
    public BaseResponse addDapp(@RequestBody AddDappParam param) {
        appDiscoverDappService.addDapp(param);
        return response();
    }

    @Operation(summary= "16、dapp内容修改- put")
    @AuthenticationCheck
    @PutMapping(value = "/discover_new/update_dapp")
    public BaseResponse updateDapp(@RequestBody UpdateDappParam param) {
        appDiscoverDappService.updateDapp(param);
        return response();
    }

    @Operation(summary= "17、dapp国际化信息获取- get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_new/get_dapp_i18n")
    public ResultResponse<List<DappI18nResult>> getDappI18n(@ValidRequestParam GetAppDetailBaseParam param) {
        return response(appDiscoverDappService.getDappI18n(param));
    }

    @Operation(summary= "18、dapp内容国际化编辑- post")
    @AuthenticationCheck
    @PostMapping(value = "/discover_new/edit_dapp_i18n")
    public BaseResponse editDappI18n(@RequestBody EditDappI18nParam param) {
        appDiscoverDappService.editDappI18n(param);
        return response();
    }

    @Autowired
    private AppDiscoverAppService appDiscoverAppService;

    /**
     * app
     */
    @Operation(summary= "19、查找app内容详情-get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_group/get_app_detail")
    public ResultResponse<GetManAppDetailResult> getAppDetail(@ValidRequestParam GetAppDetailBaseParam param) {
        return response(appDiscoverAppService.getAppDetail(param));
    }

    @Operation(summary= "20、app内容增加- post")
    @AuthenticationCheck
    @PutMapping(value = "/discover_new/add_app")
    public BaseResponse addApp(@RequestBody AddAppParam param) {
        appDiscoverAppService.addApp(param);
        return response();
    }

    @Operation(summary= "21、app内容修改- put")
    @AuthenticationCheck
    @PutMapping(value = "/discover_new/update_app")
    public BaseResponse updateApp(@RequestBody UpdateAppParam param) {
        appDiscoverAppService.updateApp(param);
        return response();
    }

    @Operation(summary= "22、app国际化信息获取- get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_new/get_app_i18n")
    public ResultResponse<List<AppI18nResult>> getAppI18n(@ValidRequestParam GetAppDetailBaseParam param) {
        return response(appDiscoverAppService.getAppI18n(param));
    }

    @Operation(summary= "23、app内容国际化编辑- post")
    @AuthenticationCheck
    @PostMapping(value = "/discover_new/edit_app_i18n")
    public BaseResponse editAppI18n(@RequestBody EditAppI18nParam param) {
        appDiscoverAppService.editAppI18n(param);
        return response();
    }

    @Autowired
    private AppDiscoverHtmlService appDiscoverHtmlService;

    /**
     * html
     */
    @Operation(summary= "24、查找html内容详情-get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_group/get_html_detail")
    public ResultResponse<GetHtmlDetailResult> getHtmlDetail(@ValidRequestParam GetAppDetailBaseParam param) {
        return response(appDiscoverHtmlService.getHtmlDetail(param));
    }

    @Operation(summary= "25、HTML内容增加- post")
    @AuthenticationCheck
    @PutMapping(value = "/discover_new/add_html")
    public BaseResponse addHtml(@RequestBody AddHtmlParam param) {
        appDiscoverHtmlService.addHtml(param);
        return response();
    }

    @Operation(summary= "26、HTML内容修改- put")
    @AuthenticationCheck
    @PutMapping(value = "/discover_new/update_html")
    public BaseResponse updateHtml(@RequestBody UpdateHtmlParam param) {
        appDiscoverHtmlService.updateHtml(param);
        return response();
    }

    @Operation(summary= "27、html国际化信息获取- get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_new/get_html_i18n")
    public ResultResponse<List<HtmlI18nResult>> getHtmlI18n(@ValidRequestParam GetAppDetailBaseParam param) {
        return response(appDiscoverHtmlService.getHtmlI18n(param));
    }

    @Operation(summary= "28、html内容国际化编辑- post")
    @AuthenticationCheck
    @PostMapping(value = "/discover_new/edit_html_i18n")
    public BaseResponse editHtmlI18n(@RequestBody EditHtmlI18nParam param) {
        appDiscoverHtmlService.editHtmlI18n(param);
        return response();
    }

    @Operation(summary= "29、分组管理-获取分组列表-分页 get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_group/get_groups")
    public PageResponse<List<GetGroupsResult>> getGroups(@ValidRequestParam GetGroupsParam param) {
        return response(appDiscoverGroupService.getGroups(param));
    }


    @Operation(summary= "30、分组管理-新增 post")
    @AuthenticationCheck
    @PostMapping(value = "/discover_group/add_group")
    public BaseResponse addGroup(@RequestBody AddGroupParam param) {
        appDiscoverGroupService.addGroup(param);
        return response();
    }

    @Operation(summary= "31、分组管理-编辑 put")
    @AuthenticationCheck
    @PutMapping(value = "/discover_group/update_group")
    public BaseResponse updateGroup(@RequestBody UpdateGroupParam param) {
        appDiscoverGroupService.updateGroup(param);
        return response();
    }

    @Operation(summary= "32、标签管理-获取标签列表-分页 get")
    @AuthenticationCheck
    @GetMapping(value = "/discover_label/get_labels")
    public PageResponse<List<GetLabelsResult>> getLabels(@ValidRequestParam GetLabelsParam param) {
        return response(appDiscoverLabelService.getLabels(param));
    }

    @Operation(summary= "33、标签管理-新增 post")
    @AuthenticationCheck
    @PostMapping(value = "/discover_label/add_label")
    public BaseResponse addLabel(@RequestBody AddLabelParam param) {
        appDiscoverLabelService.addLabel(param);
        return response();
    }

    @Operation(summary= "34、标签管理-编辑 put")
    @AuthenticationCheck
    @PutMapping(value = "/discover_label/update_label")
    public BaseResponse updateLabel(@RequestBody UpdateLabelParam param) {
        appDiscoverLabelService.updateLabel(param);
        return response();
    }

}
