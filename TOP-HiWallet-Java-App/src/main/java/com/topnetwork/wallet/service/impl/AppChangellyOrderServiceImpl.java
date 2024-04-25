package com.topnetwork.wallet.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.core.mvc.business.CommonBusiness;
import com.topnetwork.changelly.ChangellyService;
import com.topnetwork.changelly.GetAmountResult;
import com.topnetwork.changelly.GetTransactionsResult;
import com.topnetwork.changelly.TransactionInfo;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.changelly.ExchangeStatus;
import com.topnetwork.wallet.common.enums.changelly.OrderStatusEnum;
import com.topnetwork.wallet.dao.AppChangellyOrderDao;
import com.topnetwork.wallet.entity.AppChangellyOrder;
import com.topnetwork.wallet.param.wallet.changelly.CreateTransactionParam;
import com.topnetwork.wallet.param.wallet.changelly.GetOrderDetailParam;
import com.topnetwork.wallet.param.wallet.changelly.GetOrderPageParam;
import com.topnetwork.wallet.param.wallet.changelly.GetPairInfoParam;
import com.topnetwork.wallet.param.wallet.changelly.ReportTransResultParam;
import com.topnetwork.wallet.result.changelly.CreateTransactionResult;
import com.topnetwork.wallet.result.changelly.GetOrderDetailResult;
import com.topnetwork.wallet.result.changelly.GetOrderPageResult;
import com.topnetwork.wallet.result.changelly.GetPairInfoResult;
import com.topnetwork.wallet.service.AppChangellyCurrencyService;
import com.topnetwork.wallet.service.AppChangellyOrderService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;

@Service("appChangellyOrderService")
@Transactional
public class AppChangellyOrderServiceImpl extends SqlBaseServiceImpl<AppChangellyOrder, Long>
        implements AppChangellyOrderService {

    @SuppressWarnings("unused")
    private AppChangellyOrderDao appChangellyOrderDao;

    public AppChangellyOrderServiceImpl(@Qualifier("appChangellyOrderDao") AppChangellyOrderDao appChangellyOrderDao) {
        super(appChangellyOrderDao);
        this.appChangellyOrderDao = appChangellyOrderDao;
    }

    @Autowired
    private ChangellyService changellyService;
    @Autowired
    private AppChangellyCurrencyService appChangellyCurrencyService;

    @Override
    public CreateTransactionResult createOrder(CreateTransactionParam param) {

        if (param.getFrom().equalsIgnoreCase(param.getTo())) {
            throw new BusinessException(CodeRes.CODE_21004);
        }

        //获取当前币对最新汇率与最大最小卖出值
        GetPairInfoParam pairInfoParam = new GetPairInfoParam();
        pairInfoParam.setTo(param.getTo());
        pairInfoParam.setFrom(param.getFrom());
        GetPairInfoResult pairInfo = appChangellyCurrencyService.getPairInfo(pairInfoParam);
        if (pairInfo.getMaxAmount().compareTo(param.getAmount()) < 0 || pairInfo.getMinAmount().compareTo(param.getAmount()) > 0) {
            throw new BusinessException(CodeRes.CODE_21006);
        }

        //发送交易
        com.topnetwork.changelly.CreateTransactionResult transaction = changellyService.createTransaction(param.getFrom(), param.getTo(), param.getAmount(), param.getToAddress(), param.getFromAddress());

        //获取期望买入值
        GetAmountResult exchangeAmount = changellyService.getExchangeAmount(param.getFrom(), param.getTo(), param.getAmount());

        //保存订单信息
        AppChangellyOrder appChangellyOrder = new AppChangellyOrder();
        appChangellyOrder.setAmountExpectedTo(exchangeAmount.getResult());
        appChangellyOrder.setAmountFrom(param.getAmount());
        appChangellyOrder.setCurrencyFrom(param.getFrom().toUpperCase());
        appChangellyOrder.setCurrencyTo(param.getTo().toUpperCase());
        appChangellyOrder.setPayAddress(param.getFromAddress());
        appChangellyOrder.setPayinAddress(transaction.getResult().getPayinAddress());
        appChangellyOrder.setPayoutAddress(transaction.getResult().getPayoutAddress());
        appChangellyOrder.setRate(pairInfo.getRate());
        appChangellyOrder.setStatus(OrderStatusEnum.waiting);
        appChangellyOrder.setDisable(false);
        appChangellyOrder.setCreatedDate(new Date());
        appChangellyOrder.setTranId(transaction.getResult().getId());
        appChangellyOrder.setTrackUrl("https://changelly.com/track/" + transaction.getResult().getId());
        appChangellyOrder.setUid(param.getUid());
        save(appChangellyOrder);

        //封装返回数据
        CreateTransactionResult createTransactionResult = new CreateTransactionResult();
        createTransactionResult.setAmountExpectedFrom(new BigDecimal(transaction.getResult().getAmountExpectedFrom()));
        createTransactionResult.setExchangeId(transaction.getResult().getId());
        createTransactionResult.setPayinAddress(transaction.getResult().getPayinAddress());
        return createTransactionResult;
    }

    @Override
    public QueryResult<List<GetOrderPageResult>> getOrderPage(GetOrderPageParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<GetOrderPageResult>> queryResult = new QueryResult<>();
        PageResponse.PageInfo pageInfo = new PageResponse.PageInfo();
        String sqlCount = "SELECT COUNT(1) FROM wal_app_changelly_order WHERE uid='" + param.getUid() + "'  AND `disable` = 'false' ";
        pageInfo.setTotalCount(appChangellyOrderDao.queryForInt(sqlCount));
        queryResult.setPageInfo(pageInfo);
        if (pageInfo.getTotalCount() > 0) {
            String sqlList = "SELECT * " +
                    "FROM wal_app_changelly_order changelly " +
                    "WHERE changelly.uid='" + param.getUid() + "' AND changelly.`disable` = 'false' " +
                    "ORDER BY changelly.id DESC " +
                    "LIMIT " + params.get("pageIndex") + "," + params.get("pageSize");
            List<AppChangellyOrder> list = appChangellyOrderDao.queryForList(sqlList);
            List<GetOrderPageResult> results = new ArrayList<>();
            for (AppChangellyOrder appChangellyOrder : list) {
                GetOrderPageResult getOrderPageResult = new GetOrderPageResult();
                getOrderPageResult.setCreateTime(appChangellyOrder.getCreatedDate().getTime());
                getOrderPageResult.setExchangeId(appChangellyOrder.getTranId());
                getOrderPageResult.setFrom(appChangellyOrder.getCurrencyFrom().toUpperCase());
                getOrderPageResult.setFromAmount(appChangellyOrder.getAmountFrom());
                getOrderPageResult.setStatus(appChangellyOrder.getStatus());
                getOrderPageResult.setTo(appChangellyOrder.getCurrencyTo().toUpperCase());

                if (OrderStatusEnum.finished.equals(appChangellyOrder.getStatus())) {
                    getOrderPageResult.setToAmount(appChangellyOrder.getAmountTo());
                } else {
                    getOrderPageResult.setToAmount(appChangellyOrder.getAmountExpectedTo());
                }
                results.add(getOrderPageResult);
            }
            queryResult.setResult(results);
        } else {
            queryResult.setResult(new ArrayList<>());
        }
        return queryResult;
    }

    @Override
    public GetOrderDetailResult getOrderDetail(GetOrderDetailParam param) {
        AppChangellyOrder appChangellyOrder = getById(param.getExchangeId());
        if (appChangellyOrder == null) {
            throw new BusinessException(CodeRes.CODE_21007);
        }
        GetOrderDetailResult getOrderDetailResult = new GetOrderDetailResult();
        getOrderDetailResult.setFromAddress(appChangellyOrder.getPayAddress());
        getOrderDetailResult.setFrom(appChangellyOrder.getCurrencyFrom().toUpperCase());
        getOrderDetailResult.setRate(appChangellyOrder.getRate());
        getOrderDetailResult.setToAddress(appChangellyOrder.getPayoutAddress());
        getOrderDetailResult.setTo(appChangellyOrder.getCurrencyTo().toUpperCase());
        getOrderDetailResult.setCreateTime(appChangellyOrder.getCreatedDate().getTime());
        getOrderDetailResult.setExchangeId(appChangellyOrder.getTranId());
        getOrderDetailResult.setFromAmount(appChangellyOrder.getAmountFrom());
        getOrderDetailResult.setStatus(appChangellyOrder.getStatus());
        getOrderDetailResult.setTrackUrl(appChangellyOrder.getTrackUrl());
        if (OrderStatusEnum.finished.equals(appChangellyOrder.getStatus())) {
            getOrderDetailResult.setToAmount(appChangellyOrder.getAmountTo());
        } else {
            getOrderDetailResult.setToAmount(appChangellyOrder.getAmountExpectedTo());
        }
        return getOrderDetailResult;
    }

    @Override
    public void reportTransferResults(ReportTransResultParam param) {
        AppChangellyOrder appChangellyOrder = getById(param.getExchangeId());
        if (appChangellyOrder == null) {
            throw new BusinessException(CodeRes.CODE_21007);
        }
        if (ExchangeStatus.cancel.equals(param.getStatus())) {
            appChangellyOrder.setDisable(true);
            save(appChangellyOrder);
        }
        if (ExchangeStatus.failed.equals(param.getStatus())) {
            appChangellyOrder.setStatus(OrderStatusEnum.failed);
            save(appChangellyOrder);
        }
    }

    ExecutorService service = Executors.newFixedThreadPool(20);

    @Override
    public void updateChangellyOrder() {
        String sqlList = "SELECT * " +
                "FROM wal_app_changelly_order changelly " +
                "WHERE changelly.`status`='waiting' AND changelly.`disable` = 'false' ";
        List<AppChangellyOrder> list = appChangellyOrderDao.queryForList(sqlList);
        for (AppChangellyOrder appChangellyOrder : list) {
            if (OrderStatusEnum.waiting.equals(appChangellyOrder.getStatus())) {
                service.submit(new UpdateChangelly(appChangellyOrder));
            }
        }
    }

    public class UpdateChangelly implements Runnable {
        private AppChangellyOrder appChangellyOrder;

        public UpdateChangelly(AppChangellyOrder appChangellyOrder) {
            this.appChangellyOrder = appChangellyOrder;
        }

        @Override
        public void run() {
            GetTransactionsResult transactions = changellyService.getTransactions(appChangellyOrder.getTranId());
            TransactionInfo transactionInfo = transactions.getResult().get(0);
            if ("failed,refunded,overdue,expired".contains(transactionInfo.getStatus())) {
                appChangellyOrder.setStatus(OrderStatusEnum.failed);
            }
            if ("finished".equalsIgnoreCase(transactionInfo.getStatus())) {
                appChangellyOrder.setStatus(OrderStatusEnum.finished);
                appChangellyOrder.setRate(new BigDecimal("1").divide(new BigDecimal(transactionInfo.getRate()), 8, RoundingMode.HALF_DOWN));
            }
            appChangellyOrder.setRefundHash(transactionInfo.getRefundHash());
            appChangellyOrder.setPayoutHash(transactionInfo.getPayoutHash());
            appChangellyOrder.setPayinHash(transactionInfo.getPayinHash());
            appChangellyOrder.setMoneySent(transactionInfo.getMoneySent());
            appChangellyOrder.setMoneyReceived(transactionInfo.getMoneyReceived());
            appChangellyOrder.setModifiedDate(new Date());
            appChangellyOrder.setAmountTo(new BigDecimal(transactionInfo.getAmountTo()));
            save(appChangellyOrder);
        }
    }

    private void deleteOrder(String exchangeId) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("tranId", exchangeId);
        appChangellyOrderDao.executeUpdate(wrapper);
    }

    private AppChangellyOrder getById(String exchangeId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("tranId", exchangeId).eq("disable", "false");
        return get(appChangellyOrderDao.queryForList(wrapper));
    }
}
