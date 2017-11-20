package com.mysite.service;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

/**
 * 
 *
 */

@Service("ConverterService")

public class ConverterServiceImpl implements ConverterService {

	final Logger logger = LoggerFactory.getLogger(ConverterServiceImpl.class);

	private File inputPath;
	private File outputPath;
	private File inputPathArch;
	private FileTransformService fileTransformService;
	private FileHandlerService fileHandlerService;
	private int numberConvertedFiles;
	private String ext;

	ConverterServiceImpl() {
	}

	/**
	 * input directory for xml files
	 *
	 */
	@Autowired
	public void InputPath(@Value("${input.path}") File inputPath) {
		this.inputPath = inputPath;
	}

	/**
	 * output directory for csv files
	 *
	 */
	@Autowired
	public void OutputPath(@Value("${output.path}") File outputPath) {
		this.outputPath = outputPath;
	}

	/**
	 * input archive directory for xml files
	 *
	 */
	@Autowired
	public void InputArchPath(@Value("${inputArch.path}") File inputPathArch) {
		this.inputPathArch = inputPathArch;
	}

	@Autowired
	@Qualifier("xml2CsvTrasform")
	public void setConverterService(FileTransformService fileTransformService) {
		this.fileTransformService = fileTransformService;
	}

	@Autowired
	@Qualifier("fileHandlerServiceImpl")
	public void setDirectoryScannerService(FileHandlerService fileHandlerService) {
		this.fileHandlerService = fileHandlerService;
	}

	/**
	 *
	 * scheduled scans the directory and file conversions
	 *
	 */
	@Override
	@Scheduled(cron = "${cron.expression}")
	public void startConversionFiles() throws IOException, ParserConfigurationException, SAXException,
			TransformerFactoryConfigurationError, TransformerException, Exception {
		ext = "*.xml";
		Collection<File> listFiles = fileHandlerService.scanDirectory(inputPath, ext);
		if (!listFiles.isEmpty()) {
			fileHandlerService.copyFile(listFiles, inputPathArch);
			logger.info("file conversions started");
			fileTransformService.convert(listFiles, outputPath);
			this.numberConvertedFiles = fileTransformService.getNumberConvertedFiles();
			logger.info("number of converted files = " + this.numberConvertedFiles);
			logger.info("file conversions completed successfully");
			fileHandlerService.deleteFile(listFiles);
		}
	}

	/**
	 *
	 * finds csv files in directory
	 *
	 * @param outputPath
	 *            - scanned directory
	 * @param ext
	 *            - file extension
	 * @return - list of files found in the directory
	 */
	@Override
	public Collection<File> findCsvFiles() throws Exception {
		ext = "*.csv";
		return fileHandlerService.scanDirectory(outputPath, ext);
	}

	/**
	 * 
	 * reads the contents of the file
	 * 
	 * @param fileShow
	 *            - the name of the file to read
	 * 
	 * @return the contents of the file
	 */
	@Override
	public List<String> readCsvFile(String fileShow) throws IOException {

		return fileHandlerService.readFile(fileShow);

	}

}
