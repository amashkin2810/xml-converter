package com.mysite.service;

import java.io.File; 
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@Component

public class Xml2CsvTrasform implements FileTransformService {

	final Logger logger = LoggerFactory.getLogger(Xml2CsvTrasform.class);
	private int numberConvertedFiles;
	private File outputPath;

	public Xml2CsvTrasform() {

	}

	/**
	 * @param file
	 *            - collection of files to convert
	 * @param outputPath
	 *            - path for converted files
	 * @return - number of converted files
	 */
	@Override
	public int convert(Collection<File> file, File outputPath) throws IOException, ParserConfigurationException,
			SAXException, TransformerFactoryConfigurationError, TransformerException {
		final char pathSeparator = 92;
		this.outputPath = outputPath;
		File stylesheet = new File("./src/main/resources/style.xsl");

		if (!outputPath.exists()) {
			new File(outputPath.toString()).mkdir();
		}
		if (!file.isEmpty()) {

			numberConvertedFiles = 0;
			try {
				for (File files : file) {
					if (files.length() != 0) {
						String fullPath = files.toString();
						int sep = fullPath.lastIndexOf(pathSeparator);
						File fileIn = files;
						File xmlSource = new File(String.valueOf(fileIn));
						String fileName = fullPath.substring(sep + 1, (fullPath.length() - 3)) + "csv";
						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = factory.newDocumentBuilder();
						Document document = builder.parse(xmlSource);
						StreamSource stylesource = new StreamSource(stylesheet);
						Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
						Source source = new DOMSource(document);
						Result outputTarget = new StreamResult(
								new File(this.outputPath.toString() + pathSeparator + fileName));
						transformer.transform(source, outputTarget);
						numberConvertedFiles++;
					}
				}
			} catch (Exception ex) {

				logger.error("Convert error", ex);
			}

		}

		return numberConvertedFiles;

	}

	/**
	 * @return the numberConvertedFiles
	 */
	public int getNumberConvertedFiles() {

		return numberConvertedFiles;
	}

}
