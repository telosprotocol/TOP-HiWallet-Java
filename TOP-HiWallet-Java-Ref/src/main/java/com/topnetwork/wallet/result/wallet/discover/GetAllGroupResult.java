package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetAllGroupResult
 * @Description
 * @Author bran
 * @Date 2020/5/26 10:52
 */
public class GetAllGroupResult {

    @Schema(title="分组id")
    private Long groupId;
    @Schema(title="分组名称")
    private String name;
    @Schema(title="语言")
    private LanguageEnum language;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
