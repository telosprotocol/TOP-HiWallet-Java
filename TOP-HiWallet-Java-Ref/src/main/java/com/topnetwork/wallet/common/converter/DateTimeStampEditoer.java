package com.topnetwork.wallet.common.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gitee.magic.converter.DateConverterEditor;
import com.gitee.magic.core.converter.AbstractConverterEditor;
import com.gitee.magic.core.exception.ApplicationException;
import com.gitee.magic.framework.head.converter.DateTimeConverterEditor;

public class DateTimeStampEditoer extends AbstractConverterEditor<String> {

	public DateTimeStampEditoer(Class<?> prototype) {
		super(prototype);
	}

	private String pattern = "yyyyMMddHHmmss";

	@Override
	public void restore(Object value) {
		if (value != null) {
			setValue(DateTimeConverterEditor.restoreValue(value,pattern));
		}
	}

	@Override
	public String converter() {
		if (getValue() == null) {
			return null;
		}
		Date date=(Date)getValue();
		return date.getTime()+"";
	}
	
	@Override
	public Object getSource() {
		return converter();
	}
	
	public static Object restoreValue(Object value,String format) {
		Class<?> type=value.getClass();
		if(type.equals(String.class)) {
			SimpleDateFormat sd = new SimpleDateFormat(format);
			try {
				return sd.parse(String.valueOf(value));
			} catch (ParseException e) {
				throw new ApplicationException(e);
			}
		}else {
			return DateConverterEditor.restoreDataValue(value);
		}
	}

}
