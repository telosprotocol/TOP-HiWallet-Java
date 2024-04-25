package com.topnetwork.wallet.result.wallet.discover;

import com.base.core.head.converter.UploadKeyArrayConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @ClassName DappI18nResult
 * @Description
 * @Author bran
 * @Date 2020/5/26 18:41
 */
public class DappI18nResult extends ProI18nResult{

    @Schema(title="图片介绍")
    @PropertyConverter(UploadKeyArrayConverterEditor.class)
    private String[] imageUrl;
    @Schema(title="文字简介")
    private String desc;
    @Schema(title="文字详细描述")
    private String detail;

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
