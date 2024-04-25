package com.topnetwork.wallet.result.wallet.discover;

import java.io.Serializable;

import com.base.core.head.converter.UploadKeyArrayConverterEditor;
import com.base.core.head.converter.UploadKeyConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @ClassName AppInfoResult
 * @Description
 * @Author bran
 * @Date 2020/5/21 13:46
 */
public class AppInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "标签数组")
    private String[] tips;
    @Schema(title = "描述图片数组")
    @PropertyConverter(UploadKeyArrayConverterEditor.class)
    private String[] image;
    @Schema(title = "详细描述")
    private String detail;
    @Schema(title = "应用图标")
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String iconUrl;
    @Schema(title = "简略描述")
    private String desc;
    @Schema(title = "下载地址")
    private String url;
    @Schema(title = "备用下载地址")
    private String subUrl;

    public String[] getTips() {
        return tips;
    }

    public void setTips(String[] tips) {
        this.tips = tips;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubUrl() {
        return subUrl;
    }

    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
