package org.apache.maven.statement_processor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.jsefa.xml.XmlDeserializer;
import net.sf.jsefa.xml.XmlIOFactory;
import net.sf.jsefa.xml.config.XmlConfiguration;

public class XMLReader {

	public static List<Statement> readFile(String filePath) {
		List<Statement> res = new ArrayList<Statement>();
		
		XmlConfiguration config = new XmlConfiguration();
		config.getSimpleTypeConverterProvider().registerConverterType(BigDecimal.class, BigDecimalConverter.class);
		
		try {
			XmlDeserializer deserializer = XmlIOFactory.createFactory(config, Statement.class).createDeserializer();
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
