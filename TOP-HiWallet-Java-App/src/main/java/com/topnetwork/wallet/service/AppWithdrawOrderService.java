//package com.topnetwork.wallet.service;
//
//import com.topnetwork.ethexchange.param.NotifyParam;
//import com.topnetwork.system.entity.User;
//import com.topnetwork.wallet.entity.AppUser;
//import com.topnetwork.wallet.param.wallet.withdraw.*;
//import com.topnetwork.wallet.result.wallet.withdraw.*;
//import com.topnetwork.wallet.result.wallet.invite.PhoneCodeResult;
//import com.gitee.magic.framework.base.result.QueryResult;
//import com.base.core.framework.sql.service.SQLBaseService;
//import com.topnetwork.wallet.entity.AppWithdrawOrder;
//
//import java.util.List;
//
//public interface AppWithdrawOrderService extends SQLBaseService<AppWithdrawOrder, Long> {
//
//    QueryResult<List<GetOrderPageResult>> getOrderPage(GetOrderPageParam param);
//
//    List<Long> approvedOrders(ApprovedOrdersParam param, User user);
//
//    void rejectedOrders(ApprovedOrdersParam param, User user);
//
//    GetAirdropStatusResult getWithdrawStatus(AppUser appUser);
//
//    GetAirdropInfoResult getWithdrawInfo(AppUser appUser);
//
//    PhoneCodeResult getPhoneCode(PhoneCodeParam param, AppUser appUser);
//
//    AirdropDetailResult confirmAirdrop(ConfirmAirdropParam param, AppUser appUser);
//
//    QueryResult<List<AirdropSimpleResult>> getAirdropPage(AppUser appUser, GetAirdropPageParam param);
//
//    AirdropDetailResult getAirdropDetail(AppUser appUser, GetAirdropDetailParam param);
//
//    void notifyCallback(NotifyParam notifyParam);
//
//    GetOrderDetailResult getOrderDetail(GetOrderDetailParam param);
//}
