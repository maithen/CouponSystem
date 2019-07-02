package com.coupon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.util.converter.LocalDateStringConverter;

public class DateAdapter extends XmlAdapter<String, LocalDate>{
	
	@Override
    public LocalDate unmarshal(String v) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy");
    	return  LocalDate.parse(v,formatter);
        
    }
 

	@Override
	public String marshal(LocalDate v) throws Exception {
		// TODO Auto-generated method stub
		return v.toString();
	}
 
}