package org.apache.maven.statement_processor;

import java.math.BigDecimal;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import net.sf.jsefa.xml.annotation.XmlAttribute;
import net.sf.jsefa.xml.annotation.XmlDataType;
import net.sf.jsefa.xml.annotation.XmlElement;

@CsvDataType()
@XmlDataType(defaultElementName = "record")
public class Statement {

	@CsvField(pos = 1)
	@XmlAttribute(name = "reference")
	Integer reference;
	
	@CsvField(pos = 2)
	@XmlElement(pos = 1)
	String accountNumber;
	
	@CsvField(pos = 3)
	@XmlElement(pos = 2)
	String description;
	
	@CsvField(pos = 4)
	@XmlElement(pos = 3)
	BigDecimal startBalance;
	
	@CsvField(pos = 5)
	@XmlElement(pos = 4)
	BigDecimal mutation;
	
	@CsvField(pos = 6)
	@XmlElement(pos = 5)
	BigDecimal endBalance;
	
	public Statement(Integer reference, String accountNumber, String description, BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance) {
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.description = description;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.endBalance = endBalance;
	}
	
	public Statement() {
	}
	
	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	public BigDecimal getMutation() {
		return mutation;
	}

	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}
	
	public boolean validateBalance() {
		return (startBalance.add(mutation)).equals(endBalance);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
        if (!(o instanceof Statement)) {
            return false;
        }

        Statement otherStatement = (Statement) o;

        return reference.intValue() == otherStatement.reference.intValue();
	}
	
	@Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + reference.hashCode();
        return result;
    }

	@Override
	public String toString() {
		return "{" + reference + "," + accountNumber + "," + description + "," + startBalance + "," + mutation + "," + endBalance + "}";
	}
}
