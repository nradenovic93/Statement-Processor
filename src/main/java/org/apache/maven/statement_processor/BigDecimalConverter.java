package org.apache.maven.statement_processor;

import java.math.BigDecimal;

import net.sf.jsefa.common.converter.SimpleTypeConverter;

public class BigDecimalConverter implements SimpleTypeConverter {

	private static final BigDecimalConverter INSTANCE = new BigDecimalConverter();

	public static BigDecimalConverter create() {
		return INSTANCE;
	}

	private BigDecimalConverter() {
	}

	public Object fromString(String s) {
		return new BigDecimal(s);
	}

	public String toString(Object d) {
		return d.toString();
	}

}