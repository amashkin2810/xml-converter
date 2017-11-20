package com.mysite.service;

import java.io.File; 
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;


public interface FileTransformService {
	
	/**
	 * @param file
	 *            - collection of files to convert
	 * @param outputPath
	 *            - path for converted files
	 * @return - number of converted files
	 */	    
	public int convert(Collection<File>files, File outputPath) throws IOException, ParserConfigurationException, SAXException, TransformerFactoryConfigurationError, TransformerException, Exception;

	/**
	 * @return the numberConvertedFiles
	 */
	
	public int getNumberConvertedFiles();
}
