package com.topnetwork.wallet.common.converter;

import java.math.BigDecimal;

import com.gitee.magic.core.converter.AbstractConverterEditor;

public class BigDecimalConverter extends AbstractConverterEditor<String>  {
	  
	  public BigDecimalConverter(Class<?> prototype) {
	    super(prototype);
	  }

	  @Override
	  public void restore(Object value) {
	    if(value!=null) {
	      setValue(new BigDecimal(String.valueOf(value)));
	    }
	  }

	  @Override
	  public String converter() {
	    if(getValue()==null) {
	      return null;
	    }
	    BigDecimal val=(BigDecimal)getValue();
	    return val.toPlainString();
	  }

	  @Override
	  public Object getSource() {
	    return converter();
	  }
	  
	}
