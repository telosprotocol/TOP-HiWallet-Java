package com.topnetwork.wallet.param.defi;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.TimeStampConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.valid.annotation.BooleanValid;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.LongValid;

public class ProductPublishManageParam{
	
	@Schema(title =  "产品ID",required = true)
	@LongValid
	@NotNull
	private Long productId;
	@Schema(title = "发布状态",required = true)
	@NotNull
	@BooleanValid
	private Boolean publish;
	
	@Schema(title = "发布时间(不用传)",required = false)
	@PropertyConverter(TimeStampConverterEditor.class)
	private Date publishTime;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Boolean getPublish() {
		return publish;
	}

	public void setPublish(Boolean publish) {
		this.publish = publish;
	}

	public Date getPublishTime() {
		if(publish) {
			return new Date();
		}
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	
}
