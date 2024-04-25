package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.param.wallet.ResourceAddParam;
import com.topnetwork.wallet.param.wallet.ResourceListParam;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

public class ResourceTest extends TestHeader {

    @Test
    public void test_find_resource_list() throws Exception {
        ResourceListParam param = new ResourceListParam();
        param.setPageIndex(1);
        param.setPageSize(5);
        RestfulTemplate template = new RestfulTemplate(HOST, "dfe090d9ceafc352dea726ca390f7d79", "NjI0ODgxNzZhODIxZDdjYWJjZTIyNzcyMmJmNTU1YTY=");
        template.postForEntityBase("/resource/find_resource_list", param);
    }

    @Test
    public void test_add_resource() throws Exception {
        ResourceAddParam param = new ResourceAddParam();
        param.setName("test");
        param.setRegion(RegionEnum.COMMON);
        param.setUrl("https://www.baidu.com");
        RestfulTemplate template = new RestfulTemplate(HOST, "dfe090d9ceafc352dea726ca390f7d79", "NjI0ODgxNzZhODIxZDdjYWJjZTIyNzcyMmJmNTU1YTY=");
        template.postForEntityBase("/resource/add_resource", param);
    }
}
