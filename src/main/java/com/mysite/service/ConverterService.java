package com.mysite.service;

import java.util.List; 
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

public interface ConverterService {

	public Collection<File> findCsvFiles() throws Exception;

	public void startConversionFiles() throws IOException, ParserConfigurationException, SAXException,
			TransformerFactoryConfigurationError, TransformerException, Exception;

	public List<String> readCsvFile(String fileShow) throws IOException;

}
