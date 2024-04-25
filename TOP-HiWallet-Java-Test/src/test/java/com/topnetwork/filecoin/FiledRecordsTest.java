package com.topnetwork.filecoin;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.changelly.ExchangeStatus;
import com.topnetwork.wallet.common.enums.changelly.GetListTypeEnum;
import com.topnetwork.wallet.common.enums.filecoin.SourceEnum;
import com.topnetwork.wallet.param.filecoin.AddRecordParam;
import com.topnetwork.wallet.param.wallet.changelly.*;
import com.topnetwork.wallet.result.changelly.*;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @ClassName FiledRecordsTest
 * @Description
 * @Author bran
 * @Date 2020/7/15 16:47
 */
public class FiledRecordsTest extends TestHeader {

    @Test
    public void test_filecoin_records_add() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        AddRecordParam param = new AddRecordParam();
        param.setAmount(100L);
        param.setEmail("bran.yan@topnetwork.org");
        param.setEndTime(1594798811000L);
        param.setMobile("15737978239");
        param.setPayAddress("0xf1745E42b9800a2bc851e659a258dC1Cd6FE1E27");
        param.setReceiveAddress("0xf1745E42b9800a2bc851e659a258dC1Cd6FE1E27");
        param.setStartTime(1592206811000L);
        param.setSource(SourceEnum.MOBILE);
        param.setStakingAddress(null);
        param.setWechat("258");
        template.postForEntityBase("/filecoin_records/add", param);
    }

    @Test
    public void test_filecoin_save_cid() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/filecoin/save/dgdsfgdsg/aaa/1024?fileName=dgsg", null);
    }
}
