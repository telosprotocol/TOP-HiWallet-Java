package com.topnetwork.wallet.common.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.gitee.magic.core.converter.AbstractConverterEditor;

/**
 * 大数据类型转换bigdecimal
 * @author jode
 * @date Dec 3, 2020
 */
public class NumberToBigdecimalConverterEditor extends AbstractConverterEditor<BigDecimal> {

	public NumberToBigdecimalConverterEditor(Class<?> prototype) {
		super(prototype);
	}

	@Override
	public BigDecimal converter() {
		if(getValue()==null) {
			return null;
		}
//		return new BigDecimal(getValue().toString());
		return new BigDecimal(new BigDecimal(getValue().toString()).setScale(6, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
	}

	@Override
	public void restore(Object value) {
		if(value!=null) {
			setValue(new BigDecimal(value.toString()).setScale(6, RoundingMode.HALF_UP));
		}
	}
	
	@Override
	public BigDecimal getSource() {
		if(converter()==null) {
			return null;
		}
		return converter();
	}
	
}
