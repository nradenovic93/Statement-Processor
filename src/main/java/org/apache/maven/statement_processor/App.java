package org.apache.maven.statement_processor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class App 
{

	public static void main( String[] args )
	{
		// Read CSV and XML files from resources
		List<Statement> statements = readFolder("src//main//resources");

		// Validate all statements and print
		validate(statements);
	}

	private static List<Statement> validate(List<Statement> statements) {
		List<Statement> res = new ArrayList<Statement>();
		
		HashSet<Statement> set = new HashSet<Statement>();

		for (Statement statement : statements) {
			if (set.add(statement) == false)
				print(statement, "Transaction reference not unique");
			
			if (!statement.validateBalance())
				print(statement, "Incorrect end balance");
		}

		return new ArrayList<Statement>(res);
	}

	private static List<Statement> readFolder(String folderPath) {
		List<Statement> res = new ArrayList<Statement>();

		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				res.addAll(readFile(listOfFiles[i].getPath()));
			}
		}

		return res;
	}

	private static List<Statement> readFile(String filePath) {
		String ext = FilenameUtils.getExtension(filePath);

		if(ext.matches("(.*)csv"))
			return CSVReader.readFile(filePath);
		else if(ext.matches("(.*)xml"))
			return XMLReader.readFile(filePath);

		return new ArrayList<Statement>();
	}
	
	private static void print(Statement statement, String error) {
	    System.out.println(statement + ": " + error);
	}
}
