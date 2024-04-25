package com.topnetwork.defi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.base.core.head.ao.IDAO;
import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.bean.IncreaseInterestBean;
import com.topnetwork.wallet.common.enums.CurrencyEnum;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.filecoin.SourceEnum;
import com.topnetwork.wallet.param.defi.DefiLoginParam;
import com.topnetwork.wallet.param.defi.ProductIdLanguageParam;
import com.topnetwork.wallet.param.defi.ProductLanguageByAddParam;
import com.topnetwork.wallet.param.defi.ProductLanguageUpdateParam;
import com.topnetwork.wallet.param.defi.ProductPageParam;
import com.topnetwork.wallet.param.defi.ProductPublishManageParam;
import com.topnetwork.wallet.param.defi.ProductUpdateParam;
import com.topnetwork.wallet.param.defi.problem.ProblemAddParam;

public class ProductTest extends TestHeader {

	@Test
    public void test_defilogin() throws Exception {
        DefiLoginParam param = new DefiLoginParam();
        param.setAddress("0x305edE830906691e819c5f76405d2B186330D8D6");
        param.setSource(SourceEnum.MOBILE.name());
        RestfulTemplate template = new RestfulTemplate(HOST);
        template.postForEntityBase("/v1/defi/login", param);
    }
	
	@Test
    public void test_product_add() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
        ProductUpdateParam param = new ProductUpdateParam();
        param.setCurrency(CurrencyEnum.USDT);
        param.setMinRate(0.15F);
        param.setMaxRate(0.20F);
        param.setInvestAmount(new BigDecimal("1"));
        param.setTotalAmount(new BigDecimal("1500"));
        param.setStartDate(new Date());
        param.setRaiseDay(1);
        param.setInvestDay(2);
        param.setRevertDay(1);
        param.setPublish(true);
        param.setAuditReportUrl("http://www.baidu.com");
        List<IncreaseInterestBean> bbs=new ArrayList<>();
        IncreaseInterestBean b=new IncreaseInterestBean();
        b.setMin(new BigDecimal(100000L));
        b.setMax(new BigDecimal(500000L));
        b.setInterest(new BigDecimal(0.01F));
        bbs.add(b);
        b=new IncreaseInterestBean();
        b.setMin(new BigDecimal(4000000L));
        b.setMax(new BigDecimal(8000000L));
        b.setInterest(new BigDecimal(0.5F));
        bbs.add(b);
        param.setIncreaseInterests(bbs);
        param.setContractAddress("0x629B969632821E3e60b5669d684DF8087350443f");
        param.setSort(1);
        template.postForEntityBase("/product", param);
    }
	
	@Test
    public void test_product_update() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
        ProductPublishManageParam param = new ProductPublishManageParam();
        param.setProductId(789444937174294528L);
        param.setPublish(true);
        template.postForEntityBase("/product/publish", param);
    }
	
	@Test
    public void test_product_page() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
        ProductPageParam param = new ProductPageParam();
        param.setLanguage(LanguageEnum.CN);
        template.getForEntityBase("/product/page", param);
    }
	
	@Test
    public void test_product_detail() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
        ProductIdLanguageParam param = new ProductIdLanguageParam();
        param.setId(797405109247119360L);
        param.setLanguage(LanguageEnum.DE);
        template.getForEntityBase("/product/detail", param);
    }
	
	@Test
    public void test_add_language() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
        ProductLanguageByAddParam p = new ProductLanguageByAddParam();
        p.setProductId(799378397791993856L);
        ProductLanguageUpdateParam param = new ProductLanguageUpdateParam();
        param.setLanguageId(799391343247065089l);
        param.setLanguage(LanguageEnum.CN);
        param.setDescription("产品详情-43243232");
        param.setIntroduce("产品简介-001222");
        param.setTags(new String[] {"保本代投好"});
        param.setName("comp-USDT无风险保本代投产品");
        param.setLogo("0bf6db40fbd33f1a2dfc1b892bf1eb7f");
        param.setAuditReportImage("0bf6db40fbd33f1a2dfc1b892bf1eb7f");
        
        param.setAdvantageImages(new String[] {"0bf6db40fbd33f1a2dfc1b892bf1eb7f","0bf6db40fbd33f1a2dfc1b892bf1eb7f"});
        param.setCapitalFlowImage("0bf6db40fbd33f1a2dfc1b892bf1eb7f");
        param.setCapitalSecurityExplain("这里是资金安全说明");
        param.setManagementFeeExplain("发文分趣味");
        param.setManagementFeeText("阿 v 的我反胃啊分为二");
        param.setMiningStrategyExplain("为非文人风骨玩儿够温柔个人分");
        param.setRedeemRuleExplain("大哥热隔热和他和日本 v 分为 v 测完11111");
        param.setRiskExplain("二哥我给热外国人我国 v");
        param.setRiskManagementExplain("俄氛围感染天我给他和他和");
        param.setRedeemFeeText("是乏味啊去11111");
        
        ProductLanguageUpdateParam paramEN = new ProductLanguageUpdateParam();
        paramEN.setLanguageId(799391343247065088L);
        paramEN.setLanguage(LanguageEnum.EN);
        paramEN.setDescription("eglish-2342342");
        paramEN.setIntroduce("eglish intrac-001222");
        paramEN.setTags(new String[] {"good"});
        paramEN.setName("comp-USDT eglish -001");
        paramEN.setLogo("0bf6db40fbd33f1a2dfc1b892bf1eb7f");
        paramEN.setAuditReportImage("cc39bdfff7ae275bb91a94718b2848cd");
        
        paramEN.setAdvantageImages(new String[] {"0bf6db40fbd33f1a2dfc1b892bf1eb7f","0bf6db40fbd33f1a2dfc1b892bf1eb7f"});
        paramEN.setCapitalFlowImage("0bf6db40fbd33f1a2dfc1b892bf1eb7f");
        paramEN.setCapitalSecurityExplain("3eq3eq23rq34q3r34ergrfbgtht");
        paramEN.setManagementFeeExplain("faefdawehrtyjenbrghtbergt");
        paramEN.setManagementFeeText("ferterrtgherherhtgeter");
        paramEN.setMiningStrategyExplain("rwewrqwergergwererwgwrgerwg");
        paramEN.setRedeemRuleExplain("rqerqgregwergwergterter");
        paramEN.setRiskExplain("eqe3q3eqterterwterwtew");
        paramEN.setRiskManagementExplain("q3eq23eq3twertertwertewrt");
        paramEN.setRedeemFeeText("defawefaefaeadfaefgtrytr5");
        
        List<ProblemAddParam> problemCNs = new ArrayList<ProblemAddParam>();
        ProblemAddParam cnp = new ProblemAddParam();
        cnp.setSort(1);
        cnp.setAnswer("什么是只能合约？");
        cnp.setQuestion("智能合约（英语：Smart contract ）是一种旨在以信息化方式传播、验证或执行合同的计算机协议。智能合约允许在没有第三方的情况下进行可信交易，这些交易可追踪且不可逆转。智能合约概念于1995年由Nick Szabo首次提出");
        problemCNs.add(cnp);
        ProblemAddParam cnp2 = new ProblemAddParam();
        cnp2.setSort(2);
        cnp2.setAnswer("什么是只能合约22？");
        cnp2.setQuestion("2222智能合约（英语：Smart contract ）是一种旨在以信息化方式传播、验证或执行合同的计算机协议。智能合约允许在没有第三方的情况下进行可信交易，这些交易可追踪且不可逆转。智能合约概念于1995年由Nick Szabo首次提出");
        problemCNs.add(cnp2);
        param.setProblems(problemCNs);
        ProblemAddParam enp = new ProblemAddParam();
        enp.setSort(1);
        List<ProblemAddParam> problemENs = new ArrayList<ProblemAddParam>();
        enp.setAnswer("What is a contract");
        enp.setQuestion("Smart contract (English: smart contract) is a kind of computer protocol which aims to spread, verify or execute the contract in an information way. Smart contracts allow trusted transactions without a third party, which are traceable and irreversible. The concept of smart contract was first proposed by Nick Szabo in 1995");
        problemENs.add(enp);
        ProblemAddParam enp2 = new ProblemAddParam();
        enp2.setSort(2);
        enp2.setAnswer("What is a contract2222");
        enp2.setQuestion("22222Smart contract (English: smart contract) is a kind of computer protocol which aims to spread, verify or execute the contract in an information way. Smart contracts allow trusted transactions without a third party, which are traceable and irreversible. The concept of smart contract was first proposed by Nick Szabo in 1995");
        problemENs.add(enp2);
        paramEN.setProblems(problemENs);
        
        List<ProductLanguageUpdateParam> ps = new ArrayList<>();
        ps.add(paramEN);
        ps.add(param);
        p.setLanguages(ps);
        template.postForEntityBase("/product/language", p);
    }
	
	@Test
	public void test_language_list() throws Exception {
		RestfulTemplate template = new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
		IDAO param = new IDAO();
		param.setId(797405109247119360L);
		template.getForEntityBase("/product/language/list",param);
	}
	
}
