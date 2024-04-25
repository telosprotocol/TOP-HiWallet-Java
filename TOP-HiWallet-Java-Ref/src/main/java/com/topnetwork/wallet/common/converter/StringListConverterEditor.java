package com.topnetwork.wallet.common.converter;

import com.gitee.magic.core.converter.AbstractConverterEditor;

import java.util.Arrays;
import java.util.List;

/**
 */
public class StringListConverterEditor extends AbstractConverterEditor<List<String>> {

	public StringListConverterEditor(Class<?> prototype) {
		super(prototype);
	}

	@Override
	public List<String> converter() {
		if(getValue()==null) {
			return null;
		}
		return (List<String>)getValue();
	}

	@Override
	public void restore(Object value) {
		if(value!=null) {
			String val = String.valueOf(value);
			List<String> list = Arrays.asList(val.split(","));
			setValue(list);
		}
		
	}

	@Override
	public List<String> getSource() {
		if(converter()==null) {
			return null;
		}
		return converter();
	}
	
	
	
}
