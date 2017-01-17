package org.apache.maven.statement_processor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import net.sf.jsefa.csv.CsvDeserializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;

public class CSVReader {

	public static List<Statement> readFile(String filePath) {
		List<Statement> res = new ArrayList<Statement>();
		
		CsvConfiguration config = new CsvConfiguration();
		config.setFieldDelimiter(',');
		config.getSimpleTypeConverterProvider().registerConverterType(BigDecimal.class, BigDecimalConverter.class);
		config.setLineFilter(new HeaderAndFooterFilter(1, false, true));
		
		try {
			CsvDeserializer deserializer = CsvIOFactory.createFactory(config, Statement.class).createDeserializer();
			deserializer.open(new FileReader(filePath));
			
			while (deserializer.hasNext()) {
				Statement s = deserializer.next();
				res.add(s);
			}
			
			deserializer.close(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
}
