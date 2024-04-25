//package com.topnetwork.wallet.service.impl;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import com.common.bean.PhoneCodeRedisData;
//import com.base.core.mvc.business.CommonBusiness;
//import com.common.utils.DateUtils;
//import com.common.utils.JasyptUtils;
//import com.base.core.context.utils.LoggerUtils;
//import com.common.utils.PhoneCodeUtils;
//import com.base.system.service.ConfigureService;
//import com.topnetwork.common.service.RedisLock;
//import com.topnetwork.common.service.SecurityService;
//import com.topnetwork.ethexchange.enums.Status;
//import com.topnetwork.ethexchange.enums.TransactionTypeEnum;
//import com.topnetwork.ethexchange.param.BatchTransferParam;
//import com.topnetwork.ethexchange.param.NotifyParam;
//import com.topnetwork.ethexchange.param.WithdrawItem;
//import com.topnetwork.ethexchange.param.WithdrawOrderParam;
//import com.topnetwork.system.entity.User;
//import com.topnetwork.wallet.common.CodeRes;
//import com.topnetwork.wallet.common.enums.LanguageEnum;
//import com.topnetwork.wallet.common.enums.RewardFromType;
//import com.topnetwork.wallet.common.enums.withdraw.OrderStatus;
//import com.topnetwork.wallet.dao.AppWithdrawOrderDao;
//import com.topnetwork.wallet.entity.AppActivityReward;
//import com.topnetwork.wallet.entity.AppUser;
//import com.topnetwork.wallet.entity.AppUserBalance;
//import com.topnetwork.wallet.entity.AppWithdrawOrder;
//import com.topnetwork.wallet.param.wallet.withdraw.ApprovedOrdersParam;
//import com.topnetwork.wallet.param.wallet.withdraw.ConfirmAirdropParam;
//import com.topnetwork.wallet.param.wallet.withdraw.GetAirdropDetailParam;
//import com.topnetwork.wallet.param.wallet.withdraw.GetAirdropPageParam;
//import com.topnetwork.wallet.param.wallet.withdraw.GetOrderDetailParam;
//import com.topnetwork.wallet.param.wallet.withdraw.GetOrderPageParam;
//import com.topnetwork.wallet.param.wallet.withdraw.PhoneCodeParam;
//import com.topnetwork.wallet.result.wallet.invite.PhoneCodeResult;
//import com.topnetwork.wallet.result.wallet.withdraw.AirdropDetailResult;
//import com.topnetwork.wallet.result.wallet.withdraw.AirdropSimpleResult;
//import com.topnetwork.wallet.result.wallet.withdraw.GetAirdropInfoResult;
//import com.topnetwork.wallet.result.wallet.withdraw.GetAirdropStatusResult;
//import com.topnetwork.wallet.result.wallet.withdraw.GetOrderDetailResult;
//import com.topnetwork.wallet.result.wallet.withdraw.GetOrderPageResult;
//import com.topnetwork.wallet.service.AppActivityRewardService;
//import com.topnetwork.wallet.service.AppMessageLogService;
//import com.topnetwork.wallet.service.AppUserBalanceService;
//import com.topnetwork.wallet.service.AppUserService;
//import com.topnetwork.wallet.service.AppWithdrawOrderService;
//
//import com.gitee.magic.framework.head.exception.BusinessException;
//import start.framework.commons.rest.RESTfulTemplate;
//import com.gitee.magic.framework.base.result.PageResponse;
//import com.gitee.magic.framework.base.result.QueryResult;
//import com.base.core.framework.sql.service.impl.SQLBaseServiceImpl;
//import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
//import com.gitee.magic.core.json.JSONObject;
//import com.gitee.magic.core.utils.StackTraceInfo;
//
//@Service("appWithdrawOrderService")
//@Transactional
//public class AppWithdrawOrderServiceImpl extends SQLBaseServiceImpl<AppWithdrawOrder, Long>
//        implements AppWithdrawOrderService {
//
//    @SuppressWarnings("unused")
//    private AppWithdrawOrderDao appWithdrawOrderDao;
//    @Autowired
//    private RedisTemplate<String, PhoneCodeRedisData> redisTemplate;
//    @Autowired
//    private ConfigureService configureService;
//    @Autowired
//    private AppUserBalanceService appUserBalanceService;
//    @Autowired
//    private AppActivityRewardService appActivityRewardService;
//    @Autowired
//    private SecurityService securityService;
//    @Autowired
//    private AppMessageLogService appMessageLogService;
//
//
//    @Value("${remote.pay.host}")
//    private String HOST;
//    @Value("${remote.pay.accessid}")
//    private String accessid;
//    @Value("${remote.pay.accesskey}")
//    private String accesskey;
//    @Value("${remote.pay.withdraw.url}")
//    private String withdrawUrl;
//    ExecutorService service = Executors.newFixedThreadPool(20);
//
//    public AppWithdrawOrderServiceImpl(@Qualifier("appWithdrawOrderDao") AppWithdrawOrderDao appWithdrawOrderDao) {
//        super(appWithdrawOrderDao);
//        this.appWithdrawOrderDao = appWithdrawOrderDao;
//    }
//
//    @Override
//    public QueryResult<List<GetOrderPageResult>> getOrderPage(GetOrderPageParam param) {
//        if ((param.getEndTime() == null && param.getStartTime() != null)
//                || (param.getEndTime() != null && param.getStartTime() == null)) {
//            throw new BusinessException(CodeRes.CODE_14024);
//        }
//        if ((param.getEndTime() != null && param.getStartTime() != null) && (param.getEndTime() < param.getStartTime())) {
//            throw new BusinessException(CodeRes.CODE_14024);
//        }
//        Map<String, Object> params = CommonBusiness.httpPageParam(param);
//        QueryResult<List<GetOrderPageResult>> queryResult = new QueryResult<>();
//        PageResponse.PageInfo pageInfo = new PageResponse.PageInfo();
//        String fragmentSql = getFragmentSql(param);
//        String countSql = "SELECT COUNT(1) FROM wal_app_withdraw_order o LEFT JOIN wal_app_user u ON o.userId = u.id LEFT JOIN sys_user su ON o.reviewUserId = su.id " + fragmentSql;
//        pageInfo.setTotalCount(appWithdrawOrderDao.queryForInt(countSql, null));
//        queryResult.setPageInfo(pageInfo);
//        if (pageInfo.getTotalCount() > 0) {
//            String selectSql = "SELECT o.batchId,o.id as orderId,u.uid,o.tranId,o.amount,o.fee,o.address,u.mobile as phone,o.created_date AS startDate,o.`status`,o.reviewTime AS endDate,su.`name` " +
//                    " FROM wal_app_withdraw_order o LEFT JOIN wal_app_user u ON o.userId = u.id LEFT JOIN sys_user su ON o.reviewUserId = su.id "
//                    + fragmentSql
//                    + " ORDER BY o.created_date DESC "
//                    + " LIMIT " + params.get("pageIndex") + "," + params.get("pageSize");
//            List<GetOrderPageResult> list = queryForMap(GetOrderPageResult.class, selectSql);
//            for (GetOrderPageResult getOrderPageResult : list) {
//                getOrderPageResult.setCreateTime(getOrderPageResult.getStartDate().getTime());
//                getOrderPageResult.setEndTime(getOrderPageResult.getEndDate() == null ? null : getOrderPageResult.getEndDate().getTime());
//                getOrderPageResult.setStartDate(null);
//                getOrderPageResult.setEndDate(null);
//            }
//            queryResult.setResult(list);
//        } else {
//            queryResult.setResult(new ArrayList<>());
//        }
//        return queryResult;
//    }
//
//    private String getFragmentSql(GetOrderPageParam param) {
//        String fragmentSql = " WHERE 1=1 ";
//        if (!StringUtils.isEmpty(param.getUid())) {
//            fragmentSql = fragmentSql + "AND u.uid='" + param.getUid().trim() + "' ";
//        }
//        if (!StringUtils.isEmpty(param.getTranId())) {
//            fragmentSql = fragmentSql + "AND o.tranId='" + param.getTranId().trim() + "' ";
//        }
//        if (param.getStatus() != null) {
//            fragmentSql = fragmentSql + "AND o.`status`='" + param.getStatus().toString() + "' ";
//        }
//        if (param.getEndTime() != null && param.getStartTime() != null) {
//            fragmentSql = fragmentSql + "AND o.`created_date` BETWEEN '" + DateUtils.format(new Date(param.getStartTime()), DateUtils.yyyyMMddHHmmss_F) + "' AND '" + DateUtils.format(new Date(param.getEndTime()), DateUtils.yyyyMMddHHmmss_F) + "' ";
//        }
//        return fragmentSql;
//    }
//
//    private String getInSql(List<Long> param) {
//
//        if (param.size() <= 0) {
//            throw new BusinessException(CodeRes.CODE_21014);
//        }
//
//        StringBuilder ids = new StringBuilder();
//        for (Long aLong : param) {
//            if (StringUtils.isEmpty(ids.toString())) {
//                ids.append(aLong);
//            } else {
//                ids.append(",").append(aLong);
//            }
//        }
//        String selectSql = "SELECT * FROM wal_app_withdraw_order WHERE id in(" + ids.toString() + ")";
//        return selectSql;
//    }
//
//    @Override
//    public List<Long> approvedOrders(ApprovedOrdersParam param, User user) {
//        List<AppWithdrawOrder> list = queryForMap(AppWithdrawOrder.class, getInSql(param.getOrderId()));
//        String batchId = UUID.randomUUID().toString();
//        List<AppWithdrawOrder> send = new ArrayList<>();
//        for (AppWithdrawOrder appWithdrawOrder : list) {
//            if (OrderStatus.review.equals(appWithdrawOrder.getStatus()) && StringUtils.isEmpty(appWithdrawOrder.getBatchId())) {
//                appWithdrawOrder.setBatchId(batchId);
//                appWithdrawOrder.setModifiedDate(new Date());
//                appWithdrawOrder.setReviewTime(new Date());
//                appWithdrawOrder.setModifiedDate(new Date());
//                appWithdrawOrder.setStatus(OrderStatus.processing);
//                appWithdrawOrder.setReviewUserId(user.getId());
//                send.add(appWithdrawOrder);
//            }
//        }
//        /**
//         * 2020/08/12
//         * 改为手动转账
//         */
////        sendWithdrawList(send, batchId);
//        saveBatch(send);
//        return new ArrayList<>();
//    }
//
//    /**
//     * 批量转账
//     *
//     * @param send
//     * @param batchId
//     */
//    private void sendWithdrawList(List<AppWithdrawOrder> send, String batchId) {
//        BatchTransferParam param = new BatchTransferParam();
//        param.setClentOrderId(batchId);
//        List<WithdrawItem> items = new ArrayList<>();
//        for (AppWithdrawOrder appWithdrawOrder : send) {
//            WithdrawItem withdrawItem = new WithdrawItem();
//            withdrawItem.setAmount(appWithdrawOrder.getAmount());
//            withdrawItem.setToAddress(appWithdrawOrder.getAddress());
//            items.add(withdrawItem);
//        }
//        param.setItems(items);
//        param.setTokenId("TOP");
//        param.setUserId(JasyptUtils.getMD5(batchId));
//        RESTfulTemplate resTfulTemplate = new RESTfulTemplate(HOST, accessid, accesskey);
//        resTfulTemplate.postForEntityBase(withdrawUrl, param);
//    }
//
//    /**
//     * 单笔转账
//     *
//     * @param appWithdrawOrder
//     */
//    public void asyncSendWithdraw(AppWithdrawOrder appWithdrawOrder, User user, CountDownLatch latch, List<Long> ids) {
//        service.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //更新订单信息
//                    appWithdrawOrder.setReviewTime(new Date());
//                    appWithdrawOrder.setModifiedDate(new Date());
//                    appWithdrawOrder.setStatus(OrderStatus.processing);
//                    appWithdrawOrder.setReviewUserId(user.getId());
//
//                    //发送远程提现请求
//                    WithdrawOrderParam withdrawOrderParam = new WithdrawOrderParam();
//                    withdrawOrderParam.setAmount(appWithdrawOrder.getAmount());
//                    withdrawOrderParam.setClentOrderId(appWithdrawOrder.getTranId());
//                    withdrawOrderParam.setToAddress(appWithdrawOrder.getAddress());
//                    withdrawOrderParam.setTokenId("TOP");
//                    withdrawOrderParam.setUserId(JasyptUtils.getMD5(appWithdrawOrder.getTranId()));
//                    RESTfulTemplate resTfulTemplate = new RESTfulTemplate(HOST, accessid, accesskey);
//                    resTfulTemplate.postForEntityBase(withdrawUrl, withdrawOrderParam);
//                    save(appWithdrawOrder);
//                } catch (Exception e) {
//                    ids.add(appWithdrawOrder.getId());
//                    LoggerUtils.printErrorLog(StackTraceInfo.getTraceInfo(), new BusinessException(CodeRes.CODE_21020),
//                            "订单远程提币调用出错，tranId为：" + appWithdrawOrder.getTranId());
//                    throw new BusinessException(CodeRes.CODE_21020);
//                } finally {
//                    latch.countDown();
//                }
//
//            }
//        });
//    }
//
//
//    @Override
//    public void rejectedOrders(ApprovedOrdersParam param, User user) {
//        List<AppWithdrawOrder> list = queryForMap(AppWithdrawOrder.class, getInSql(param.getOrderId()));
//        List<AppWithdrawOrder> updateList = new ArrayList<>();
//        for (AppWithdrawOrder appWithdrawOrder : list) {
//            if (OrderStatus.review.equals(appWithdrawOrder.getStatus())) {
//                appWithdrawOrder.setReviewTime(new Date());
//                appWithdrawOrder.setModifiedDate(new Date());
//                appWithdrawOrder.setEndTime(new Date());
//                appWithdrawOrder.setStatus(OrderStatus.rejected);
//                appWithdrawOrder.setReviewUserId(user.getId());
//                updateList.add(appWithdrawOrder);
//            }
//        }
//        //更新提币订单状态
//        saveBatch(updateList);
//        //释放冻结金额
//        appUserBalanceService.unfreezeOnly(updateList);
//    }
//
//    @Autowired
//    private RedisLock redisLock;
//
//    @Override
//    public void notifyCallback(NotifyParam notifyParam) {
//
//        String key = "notify_callback_order" + notifyParam.getClientOrderId();
//        boolean lock = redisLock.lock(key);
//        // 执行逻辑操作
//        if (lock) {
//            TransactionTypeEnum transactionType = notifyParam.getTransactionType();
//            if (TransactionTypeEnum.WITHDRAW.equals(transactionType)) {
//                List<AppWithdrawOrder> appWithdrawBatch = getByBatchId(notifyParam.getClientOrderId());
//                if (appWithdrawBatch == null || appWithdrawBatch.size() <= 0) {
//                    //如果为空就是找不到.报错
//                    LoggerUtils.printErrorLog(StackTraceInfo.getTraceInfo(), new BusinessException(CodeRes.CODE_21019),
//                            "未发现提币请求订单，BatchId为：" + notifyParam.getClientOrderId());
//                    redisLock.delete(key);
//                    throw new BusinessException(CodeRes.CODE_21019);
//                }
//                for (AppWithdrawOrder appWithdrawOrder : appWithdrawBatch) {
//                    if (!OrderStatus.processing.equals(appWithdrawOrder.getStatus())) {
//                        continue;
//                    }
//                    if (notifyParam.getStatus().equals(Status.FAIL)) {
//                        appWithdrawOrder.setModifiedDate(new Date());
//                        appWithdrawOrder.setEndTime(new Date());
//                        appWithdrawOrder.setStatus(OrderStatus.failed);
//                        //释放冻结金额
//                        appUserBalanceService.unfreezeOnly(appWithdrawOrder);
//                    }
//                    if (notifyParam.getStatus().equals(Status.SUCCESS)) {
//                        appWithdrawOrder.setModifiedDate(new Date());
//                        appWithdrawOrder.setEndTime(new Date());
//                        appWithdrawOrder.setStatus(OrderStatus.success);
//                        //释放冻结金额
//                        appUserBalanceService.unfreezeOnly(appWithdrawOrder);
//                        // 扣币、增加个人中心提币记录
//                        appActivityRewardService.addWithdrawRecord(appWithdrawOrder);
//                    }
//                    save(appWithdrawOrder);
//                }
//            }
//            redisLock.delete(key);
//        } else {
//            throw new BusinessException(CodeRes.CODE_429);
//        }
//    }
//
//    private List<AppWithdrawOrder> getByBatchId(String batchId) {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("batchId", batchId);
//        return appWithdrawOrderDao.queryForList(queryWrapper);
//    }
//
//    private AppWithdrawOrder getById(Long id) {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("id", id);
//        return get(appWithdrawOrderDao.queryForList(queryWrapper));
//    }
//
//    private AppWithdrawOrder getByTranId(String tranId) {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("tranId", tranId);
//        return get(appWithdrawOrderDao.queryForList(queryWrapper));
//
//    }
//
//    @Override
//    public GetAirdropStatusResult getWithdrawStatus(AppUser appUser) {
//        JSONObject withdrawConfig = (JSONObject) configureService.getValueByCode("withdrawConfig");
//        Boolean withdrawSwitch = Boolean.valueOf(withdrawConfig.get("switch").toString());
//        GetAirdropStatusResult getAirdropStatusResult = new GetAirdropStatusResult();
//        getAirdropStatusResult.setAllow(withdrawSwitch);
//        getAirdropStatusResult.setPhoneBind(!StringUtils.isEmpty(appUser.getMobile()));
//        return getAirdropStatusResult;
//    }
//
//    @Override
//    public GetAirdropInfoResult getWithdrawInfo(AppUser appUser) {
//
//        JSONObject withdrawConfig = (JSONObject) configureService.getValueByCode("withdrawConfig");
//        Boolean withdrawSwitch = Boolean.valueOf(withdrawConfig.get("switch").toString());
//        GetAirdropInfoResult getAirdropInfoResult = new GetAirdropInfoResult();
//        AppUserBalance topBalance = appUserBalanceService.getTopBalance(appUser.getId());
//        getAirdropInfoResult.setAvailableAmount(topBalance.getBalance().subtract(topBalance.getFreezeBalance()));
//        getAirdropInfoResult.setFee(new BigDecimal(withdrawConfig.get("fee").toString()));
//        getAirdropInfoResult.setMaxAmount(new BigDecimal(withdrawConfig.get("maxAmount").toString()));
//        getAirdropInfoResult.setMinAmount(new BigDecimal(withdrawConfig.get("minAmount").toString()));
//        if (StringUtils.isEmpty(appUser.getMobile()) || !withdrawSwitch) {
//            throw new BusinessException(CodeRes.CODE_21017);
//        }
//        getAirdropInfoResult.setPhone(PhoneCodeUtils.getPrivateMobile(appUser.getCountryCode(), appUser.getMobile()));
//        return getAirdropInfoResult;
//    }
//
//    @Override
//    public PhoneCodeResult getPhoneCode(PhoneCodeParam param, AppUser appUser) {
//
//        securityService.check(param.getCode(), param.getXaxis(), param.getYaxis());
//
//        return sendCode(appUser.getCountryCode(), appUser.getMobile(), param.getLanguage());
//    }
//
//    private PhoneCodeResult sendCode(Integer countryCode, String mobile, LanguageEnum languageEnum) {
//        String code = PhoneCodeUtils.getCode();
//        PhoneCodeResult phoneCodeResult = PhoneCodeUtils.sendCode(countryCode, mobile, code, languageEnum, redisTemplate);
//        appMessageLogService.saveLog(countryCode + mobile, code, languageEnum);
//        return phoneCodeResult;
//    }
//
//    @Override
//    public AirdropDetailResult confirmAirdrop(ConfirmAirdropParam param, AppUser appUser) {
//
//
//        JSONObject withdrawConfig = (JSONObject) configureService.getValueByCode("withdrawConfig");
//        boolean withdrawSwitch = Boolean.parseBoolean(withdrawConfig.get("switch").toString());
//        if (!withdrawSwitch) {
//            throw new BusinessException(CodeRes.CODE_21017);
//        }
//        checkDecimal(param.getAmount(), 2);
//        PhoneCodeUtils.verifyPhoneCode(appUser.getMobile(), param.getCode(), redisTemplate);
//        BigDecimal minAmount = new BigDecimal(withdrawConfig.get("minAmount").toString());
//        BigDecimal maxAmount = new BigDecimal(withdrawConfig.get("maxAmount").toString());
//        Integer maxTimes = Integer.valueOf(withdrawConfig.get("maxTimes").toString());
//        BigDecimal fee = new BigDecimal(withdrawConfig.get("fee").toString());
//        if (param.getAmount().compareTo(maxAmount) > 0 || param.getAmount().compareTo(minAmount) < 0 || param.getAmount().compareTo(fee) < 0) {
//            throw new BusinessException(CodeRes.CODE_21015);
//        }
//        Integer times = countByUserId(appUser.getId());
//        if (times >= maxTimes) {
//            throw new BusinessException(CodeRes.CODE_21018);
//        }
//
//        AppWithdrawOrder appWithdrawOrder = new AppWithdrawOrder();
//        appWithdrawOrder.setAddress(param.getAddress().trim());
//        appWithdrawOrder.setAmount(param.getAmount().subtract(fee));
//        appWithdrawOrder.setEndTime(null);
//        appWithdrawOrder.setFee(fee);
//        appWithdrawOrder.setReviewTime(null);
//        appWithdrawOrder.setReviewUserId(null);
//        appWithdrawOrder.setStatus(OrderStatus.review);
//        appWithdrawOrder.setTranId(UUID.randomUUID().toString());
//        appWithdrawOrder.setUserId(appUser.getId());
//        appWithdrawOrder.setCreatedDate(new Date());
//        appWithdrawOrder.setModifiedDate(new Date());
//        save(appWithdrawOrder);
//
//        //冻结提现金额
//        appUserBalanceService.freeze(appUser.getId(), param.getAmount());
//
//        return tran(appWithdrawOrder);
//    }
//
//    private static void checkDecimal(BigDecimal bigDecimal, int size) {
//        String string = bigDecimal.stripTrailingZeros().toPlainString();
//        int index = string.indexOf(".");
//        int decimal = index < 0 ? 0 : string.length() - index - 1;
//        if (decimal > size) {
//            throw new BusinessException(CodeRes.CODE_21015);
//        }
//    }
//
//    private AirdropDetailResult tran(AppWithdrawOrder appWithdrawOrder) {
//        AirdropDetailResult airdropDetailResult = new AirdropDetailResult();
//        airdropDetailResult.setAddress(appWithdrawOrder.getAddress());
//        airdropDetailResult.setEndTime(appWithdrawOrder.getEndTime() == null ? null : appWithdrawOrder.getEndTime().getTime());
//        airdropDetailResult.setFee(appWithdrawOrder.getFee());
//        airdropDetailResult.setAmount(appWithdrawOrder.getAmount());
//        airdropDetailResult.setCreateTime(appWithdrawOrder.getCreatedDate().getTime());
//        airdropDetailResult.setStatus(appWithdrawOrder.getStatus());
//        airdropDetailResult.setTranId(appWithdrawOrder.getTranId());
//        return airdropDetailResult;
//    }
//
//    private Integer countByUserId(Long id) {
//        String sqlCount = "SELECT COUNT(1) FROM wal_app_withdraw_order WHERE userId=" + id + "  AND `status` != 'failed' AND `status` != 'rejected' AND created_date BETWEEN '" + DateUtils.format(DateUtils.getDayStart(new Date()), DateUtils.yyyyMMddHHmmss_F) + "' AND '" + DateUtils.format(DateUtils.getDayEnd(new Date()), DateUtils.yyyyMMddHHmmss_F) + "'";
//        return appWithdrawOrderDao.queryForInt(sqlCount);
//    }
//
//    @Override
//    public QueryResult<List<AirdropSimpleResult>> getAirdropPage(AppUser appUser, GetAirdropPageParam param) {
//
//        Map<String, Object> params = CommonBusiness.httpPageParam(param);
//        QueryResult<List<AirdropSimpleResult>> queryResult = new QueryResult<>();
//        PageResponse.PageInfo pageInfo = new PageResponse.PageInfo();
//        String fragmentSql = " WHERE o.userId=" + appUser.getId();
//        String countSql = "SELECT COUNT(1) FROM wal_app_withdraw_order o " + fragmentSql;
//        pageInfo.setTotalCount(appWithdrawOrderDao.queryForInt(countSql, null));
//        queryResult.setPageInfo(pageInfo);
//        List<AirdropSimpleResult> results = new ArrayList<>();
//        if (pageInfo.getTotalCount() > 0) {
//            String selectSql = "SELECT o.tranId,o.amount, o.created_date ,o.`status` " +
//                    " FROM wal_app_withdraw_order o" + fragmentSql
//                    + " ORDER BY o.created_date DESC "
//                    + " LIMIT " + params.get("pageIndex") + "," + params.get("pageSize");
//            List<AppWithdrawOrder> list = queryForMap(AppWithdrawOrder.class, selectSql);
//            for (AppWithdrawOrder appWithdrawOrder : list) {
//                AirdropSimpleResult airdropSimpleResult = new AirdropSimpleResult();
//                airdropSimpleResult.setAmount(appWithdrawOrder.getAmount());
//                airdropSimpleResult.setCreateTime(appWithdrawOrder.getCreatedDate().getTime());
//                airdropSimpleResult.setStatus(appWithdrawOrder.getStatus());
//                airdropSimpleResult.setTranId(appWithdrawOrder.getTranId());
//                results.add(airdropSimpleResult);
//            }
//        }
//        queryResult.setResult(results);
//
//        return queryResult;
//    }
//
//    @Override
//    public AirdropDetailResult getAirdropDetail(AppUser appUser, GetAirdropDetailParam param) {
//
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("tranId", param.getTranId());
//        AppWithdrawOrder appWithdrawOrder = get(appWithdrawOrderDao.queryForList(wrapper));
//
//        if (appWithdrawOrder == null) {
//            throw new BusinessException(CodeRes.CODE_21014);
//        }
//        AirdropDetailResult airdropDetailResult = new AirdropDetailResult();
//        airdropDetailResult.setTranId(appWithdrawOrder.getTranId());
//        airdropDetailResult.setStatus(appWithdrawOrder.getStatus());
//        airdropDetailResult.setCreateTime(appWithdrawOrder.getCreatedDate().getTime());
//        airdropDetailResult.setAmount(appWithdrawOrder.getAmount());
//        airdropDetailResult.setFee(appWithdrawOrder.getFee());
//        airdropDetailResult.setEndTime(appWithdrawOrder.getEndTime() == null ? null : appWithdrawOrder.getEndTime().getTime());
//        airdropDetailResult.setAddress(appWithdrawOrder.getAddress());
//
//        return airdropDetailResult;
//    }
//
//    @Autowired
//    private AppUserService appUserService;
//
//    @Override
//    public GetOrderDetailResult getOrderDetail(GetOrderDetailParam param) {
//        AppWithdrawOrder byId = getById(param.getOrderId());
//        if (byId == null) {
//            throw new BusinessException(CodeRes.CODE_21014);
//        }
//        GetOrderDetailResult getOrderDetailResult = new GetOrderDetailResult();
//        AppUser appUser = appUserService.findById(byId.getUserId());
//        getOrderDetailResult.setCreateTime(appUser.getCreateTime().getTime());
//        getOrderDetailResult.setUserSize(appUserBalanceService.countSameBalance(appUser));
//        List<AppActivityReward> rewardList = appActivityRewardService.get7DaysReceiveReward(byId.getUserId());
//
//        setGetOrderDetailResult(rewardList, getOrderDetailResult);
//        return getOrderDetailResult;
//    }
//
//    private void setGetOrderDetailResult(List<AppActivityReward> rewardList, GetOrderDetailResult getOrderDetailResult) {
//
//        BigDecimal airDropAmount = BigDecimal.ZERO;
//        long airDropTimes = 0L;
//        BigDecimal rewardAmount = BigDecimal.ZERO;
//        long rewardTimes = 0L;
//        for (AppActivityReward appActivityReward : rewardList) {
//            if (RewardFromType.AIRDROP.equals(appActivityReward.getSource())) {
//                airDropTimes += 1;
//                airDropAmount = airDropAmount.add(appActivityReward.getAmount());
//            } else {
//                rewardTimes += 1;
//                rewardAmount = rewardAmount.add(appActivityReward.getAmount());
//            }
//        }
//        getOrderDetailResult.setAirDropAmount(airDropAmount);
//        getOrderDetailResult.setAirDropTimes(airDropTimes);
//        getOrderDetailResult.setRewardAmount(rewardAmount);
//        getOrderDetailResult.setRewardTimes(rewardTimes);
//    }
//}
