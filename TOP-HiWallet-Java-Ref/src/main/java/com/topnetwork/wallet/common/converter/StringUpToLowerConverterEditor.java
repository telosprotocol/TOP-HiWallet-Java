package com.topnetwork.wallet.common.converter;

import com.gitee.magic.core.converter.AbstractConverterEditor;

/**
 * 将进来的字符串转小写，将出去的字符串转大写
 * @author jode
 * @date Oct 29, 2020
 */
public class StringUpToLowerConverterEditor extends AbstractConverterEditor<String> {

	public StringUpToLowerConverterEditor(Class<?> prototype) {
		super(prototype);
	}

	@Override
	public String converter() {
		if(getValue()==null) {
			return null;
		}
		return String.valueOf(getValue());
	}

	@Override
	public void restore(Object value) {
		if(value!=null) {
			String val = String.valueOf(value).toLowerCase();
			setValue(val);
		}
		
	}

	@Override
	public Object getSource() {
		if(converter()==null) {
			return null;
		}
		return String.valueOf(converter()).toUpperCase();
	}
	
	
	
}
