package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.param.wallet.*;
import com.topnetwork.wallet.param.wallet.v1.CoinHotV1Param;
import com.topnetwork.wallet.param.wallet.v1.CoinSpeciesDetailV1Param;
import com.topnetwork.wallet.param.wallet.v1.CoinSpeciesV1Param;
import com.topnetwork.wallet.param.wallet.v2.CoinHotParam;
import com.topnetwork.wallet.param.wallet.v2.CoinSpeciesDetailParam;
import com.topnetwork.wallet.param.wallet.v2.CoinSpeciesParam;
import com.topnetwork.wallet.result.wallet.CoinDetailListResult;
import com.topnetwork.wallet.result.wallet.CoinMainListResult;
import com.topnetwork.wallet.result.wallet.CoinSpeciesDetailResult;
import com.topnetwork.wallet.result.wallet.CoinSpeciesResult;
import com.topnetwork.wallet.service.CoinSpeciesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName CoinController
 * @Description
 * @Author bran
 * @Date 2020/4/24 11:53
 */
@RestController
@RequestMapping
@Tag(name = "币种查询管理api")
public class CoinController extends BaseController {

    @Autowired
    private CoinSpeciesService coinSpeciesService;

    @Operation(summary= "钱包搜索币种信息")
    @SecretKeyAuthorityCheck
    @PostMapping("/v2/app/find_coin_species")
    public PageResponse<List<CoinSpeciesResult>> findCoinSpecies(@RequestBody CoinSpeciesParam param) {
        return response(coinSpeciesService.findCoinSpeciesByWalletCoinSpecies(param));
    }

    @Operation(summary= "钱包根据英文名获取币种简介")
    @SecretKeyAuthorityCheck
    @PostMapping("/v2/app/find_coin_species_by_englishName")
    public ResultResponse<CoinSpeciesDetailResult> findCoinDetail(@RequestBody CoinSpeciesDetailParam param) {
        return response(coinSpeciesService.findCoinSpeciesByEnglishName(param));
    }

    @Operation(summary= "钱包获取热门币种信息")
    @SecretKeyAuthorityCheck
    @PostMapping("/v2/app/find_hot_coin_species")
    public PageResponse<List<CoinSpeciesResult>> findHotCoinSpecies(@RequestBody CoinHotParam param) {
        return response(coinSpeciesService.findHotCoinSpecies(param));
    }


    @Deprecated
    @Operation(summary= "钱包搜索币种信息")
    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/find_coin_species")
    public PageResponse<List<CoinSpeciesResult>> findCoinSpecies(@RequestBody CoinSpeciesV1Param param) {
        CoinSpeciesParam coinSpeciesParamV2 = new CoinSpeciesParam();
        BeanUtils.copyProperties(param, coinSpeciesParamV2);
        if (param.getLanguage() == 0) {
            coinSpeciesParamV2.setLanguage(LanguageEnum.CN);
        } else {
            coinSpeciesParamV2.setLanguage(LanguageEnum.EN);
        }
        return response(coinSpeciesService.findCoinSpeciesByWalletCoinSpecies(coinSpeciesParamV2));
    }

    @Deprecated
    @Operation(summary= "钱包根据英文名获取币种简介")
    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/find_coin_species_by_englishName")
    public ResultResponse<CoinSpeciesDetailResult> findCoinDetail(@RequestBody CoinSpeciesDetailV1Param param) {
        CoinSpeciesDetailParam coinSpeciesDetailParamV2 = new CoinSpeciesDetailParam();
        BeanUtils.copyProperties(param, coinSpeciesDetailParamV2);
        if (param.getLanguage() == 0) {
            coinSpeciesDetailParamV2.setLanguage(LanguageEnum.CN);
        } else {
            coinSpeciesDetailParamV2.setLanguage(LanguageEnum.EN);
        }
        return response(coinSpeciesService.findCoinSpeciesByEnglishName(coinSpeciesDetailParamV2));
    }

    @Deprecated
    @Operation(summary= "钱包获取热门币种信息")
    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/find_hot_coin_species")
    public PageResponse<List<CoinSpeciesResult>> findHotCoinSpecies(@RequestBody CoinHotV1Param param) {
        CoinHotParam coinHotParamV2 = new CoinHotParam();
        BeanUtils.copyProperties(param, coinHotParamV2);
        if (param.getLanguage() == 0) {
            coinHotParamV2.setLanguage(LanguageEnum.CN);
        } else {
            coinHotParamV2.setLanguage(LanguageEnum.EN);
        }
        return response(coinSpeciesService.findHotCoinSpecies(coinHotParamV2));
    }

    /**
     * manager
     */

    @Operation(summary= "管理后台获取主币列表")
    @AuthenticationCheck
    @PostMapping("/coin/find_main_coin_list")
    public PageResponse<List<CoinMainListResult>> findMainCoinList(@RequestBody CoinMainListParam param) {
        return response(coinSpeciesService.findMainCoinList(param));
    }

    @Operation(summary= "管理后台获取主币下拉列表")
    @AuthenticationCheck
    @PostMapping("/coin/find_main_coin_select")
    public ResultResponse<List<CoinMainListResult>> findMainCoinSelect() {
        return response(coinSpeciesService.findMainCoinSelect());
    }

    @Operation(summary= "管理后台获取代币列表")
    @AuthenticationCheck
    @PostMapping("/coin/find_token_list")
    public PageResponse<List<CoinMainListResult>> findTokenList(@RequestBody CoinTokenListParam param) {
        return response(coinSpeciesService.findTokenList(param));
    }

    @Operation(summary= "管理后台获币详情")
    @AuthenticationCheck
    @PostMapping("/coin/find_coin_detail")
    public ResultResponse<CoinDetailListResult> findCoinDetail(@RequestBody CoinDetailListParam param) {
        return response(coinSpeciesService.findCoinDetail(param));
    }

    @Operation(summary= "管理后台删除币种")
    @AuthenticationCheck
    @PostMapping("/coin/del_coin_detail")
    public BaseResponse delCoinDetail(@RequestBody CoinDetailListParam param) {
        coinSpeciesService.delCoinDetail(param);
        return response();
    }

    @Operation(summary= "管理后台增加币种")
    @AuthenticationCheck
    @PostMapping("/coin/add_coin_species")
    public BaseResponse addCoinSpecies(@RequestBody CoinAddParam param) {
        User user = getHttp().getCache(User.class);
        coinSpeciesService.addCoinSpecies(param, user);
        return response();
    }

    @Operation(summary= "管理后台修改币种")
    @AuthenticationCheck
    @PostMapping("/coin/upd_coin_species")
    public BaseResponse updCoinSpecies(@RequestBody CoinUpdParam param) {
        User user = getHttp().getCache(User.class);
        coinSpeciesService.updCoinSpecies(param, user);
        return response();
    }


    @Operation(summary= "查询主链列表")
    @AuthenticationCheck
    @GetMapping(value = "/coin/get_chain_type")
    public ResultResponse<List<ChainTypeEnum>> getChainType() {
        return response(coinSpeciesService.getChainType());
    }
}
