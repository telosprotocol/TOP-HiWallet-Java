package com.topnetwork.wallet.param.wallet.top;


import com.gitee.magic.core.valid.annotation.number.IntegerValid;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName TransactionListParam
 * @Description
 * @Author bran
 * @Date 2020/10/12 14:47
 */
public class TransactionListParam extends TopBaseParam{

	@Schema(title = "当前页数(默认:1)",
            example = "1"
    )
    @IntegerValid(
            min = 1,
            message = "10005-分页索引有误"
    )
    private int pageIndex = 1;
    @Schema(title = "分页大小(默认:8)",
            example = "8"
    )
    @IntegerValid(
            min = 1,
            message = "10006-分页大小有误"
    )
    private int pageSize = 8;


    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int index() {
        return (this.getPageIndex() - 1) * this.getPageSize();
    }
}
