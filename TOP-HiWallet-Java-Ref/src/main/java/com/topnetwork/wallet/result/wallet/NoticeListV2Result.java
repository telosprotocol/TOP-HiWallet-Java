package com.topnetwork.wallet.result.wallet;

import com.topnetwork.wallet.common.enums.PushType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class NoticeListV2Result {

    @Schema(title = "推送列表")
    private List<Extras> notice;

    public List<Extras> getNotice() {
        return notice;
    }

    public void setNotice(List<Extras> notice) {
        this.notice = notice;
    }

    public static class Extras {
        @Schema(title = "id")
        private Long id;
        @Schema(title = "推送类型")
        private PushType type;
        @Schema(title = "标题")
        private String title;
        @Schema(title = "描述")
        private String desc;
        @Schema(title = "活动链接")
        private String url;
        @Schema(title = "推送时间")
        private Long time;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public PushType getType() {
            return type;
        }

        public void setType(PushType type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }
    }
}
