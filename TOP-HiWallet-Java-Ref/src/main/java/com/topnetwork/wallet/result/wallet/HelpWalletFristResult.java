package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class HelpWalletFristResult {

    @Schema(title = "二级目录id中文名", required = true)
    private String title;
    @Schema(title = "二级目录id英文名", required = true)
    private String titleEn;
    @Schema(title = "帮助列表", required = true)
    private List<Help> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public List<Help> getList() {
        return list;
    }

    public void setList(List<Help> list) {
        this.list = list;
    }

    public static class Help {

        @Schema(title = "帮助ID", required = true)
        private Long id;
        @Schema(title = "帮助标题中文名", required = true)
        private String title;
        @Schema(title = "帮助标题英文名", required = true)
        private String englishTitle;
        @Schema(title = "一级目录id", required = true)
        private Long name;
        @Schema(title = "二级目录id", required = true)
        private Long type;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getEnglishTitle() {
            return englishTitle;
        }

        public void setEnglishTitle(String englishTitle) {
            this.englishTitle = englishTitle;
        }

        public Long getName() {
            return name;
        }

        public void setName(Long name) {
            this.name = name;
        }

        public Long getType() {
            return type;
        }

        public void setType(Long type) {
            this.type = type;
        }
    }
}
