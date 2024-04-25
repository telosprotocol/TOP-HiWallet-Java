package com.topnetwork.defi;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.defi.ProblemTypeEnum;
import com.topnetwork.wallet.param.defi.problem.ProblemAddParam;
import com.topnetwork.wallet.param.defi.problem.ProblemBatchAddParam;
import com.topnetwork.wallet.param.defi.problem.ProblemIdParam;
import com.topnetwork.wallet.param.defi.problem.ProblemQueryParam;

import com.gitee.magic.framework.base.rest.RestfulTemplate;

public class ProblemTest extends TestHeader {

	@Test
    public void test_problem_addOrUpdate() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
        ProblemBatchAddParam param = new ProblemBatchAddParam();
        List<ProblemAddParam> problemENs = new ArrayList<ProblemAddParam>();
        ProblemAddParam cnp = new ProblemAddParam();
        cnp.setSort(1);
        cnp.setAnswer("什么是只能合约？");
        cnp.setQuestion("智能合约（英语：Smart contract ）是一种旨在以信息化方式传播、验证或执行合同的计算机协议。智能合约允许在没有第三方的情况下进行可信交易，这些交易可追踪且不可逆转。智能合约概念于1995年由Nick Szabo首次提出");
        problemENs.add(cnp);
        ProblemAddParam cnp2 = new ProblemAddParam();
        cnp2.setSort(2);
        cnp2.setAnswer("什么是只能合约22？");
        cnp2.setQuestion("2222智能合约（英语：Smart contract ）是一种旨在以信息化方式传播、验证或执行合同的计算机协议。智能合约允许在没有第三方的情况下进行可信交易，这些交易可追踪且不可逆转。智能合约概念于1995年由Nick Szabo首次提出");
        problemENs.add(cnp2);
        List<ProblemAddParam> problemCNs = new ArrayList<ProblemAddParam>();
        ProblemAddParam enp = new ProblemAddParam();
        enp.setSort(1);
        enp.setAnswer("What is a contract");
        enp.setQuestion("Smart contract (English: smart contract) is a kind of computer protocol which aims to spread, verify or execute the contract in an information way. Smart contracts allow trusted transactions without a third party, which are traceable and irreversible. The concept of smart contract was first proposed by Nick Szabo in 1995");
        problemCNs.add(enp);
        ProblemAddParam enp2 = new ProblemAddParam();
        enp2.setSort(2);
        enp2.setAnswer("What is a contract2222");
        enp2.setQuestion("22222Smart contract (English: smart contract) is a kind of computer protocol which aims to spread, verify or execute the contract in an information way. Smart contracts allow trusted transactions without a third party, which are traceable and irreversible. The concept of smart contract was first proposed by Nick Szabo in 1995");
        problemCNs.add(enp2);
        
        param.setProblemCNs(problemCNs);
        param.setProblemENs(problemENs);
        template.postForEntityBase("//manager/problem/batchSave", param);
    }
	
	@Test
    public void test_problem_page() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
        ProblemQueryParam param = new ProblemQueryParam();
        param.setLanguage(LanguageEnum.EN);
        param.setProblemType(ProblemTypeEnum.COMMON);
        template.getForEntityBase("/problem/page",param);
    }
	
	@Test
    public void test_problem_page_h5() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST);
        ProblemQueryParam param = new ProblemQueryParam();
        param.setProblemType(ProblemTypeEnum.COMMON);
        param.setLanguage(LanguageEnum.EN);
        template.getForEntityBase("/problem/page", param);
    }
	
	@Test
    public void test_problem_del() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
        ProblemIdParam param = new ProblemIdParam();
        param.setProblemId(798616550184685568l);
        template.deleteForEntityBase("/manager/problem", param);
    }
	
}
