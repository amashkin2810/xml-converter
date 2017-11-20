package com.mysite.service;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * 
 * 
 */
@Component

public class FileHandlerServiceImpl implements FileHandlerService {

	final Logger logger = LoggerFactory.getLogger(FileHandlerServiceImpl.class);
	private String inputPath;
	private File directory;

	public FileHandlerServiceImpl() {

	}

	/**
	 *
	 * scans a directory for files by extension
	 *
	 * @param directory
	 *            - scanned directory
	 * @param ext
	 *            - file extension
	 * @return - list of files found in the directory
	 */

	@Override
	public Collection<File> scanDirectory(File directory, String ext) throws IOException {

		this.directory = directory;
		this.inputPath = this.directory.toString();

		Collection<File> listFile = new ArrayList<>();

		try {
			DirectoryStream<Path> dirstrm = Files.newDirectoryStream(Paths.get(inputPath), ext);
			for (Path entry : dirstrm) {
				BasicFileAttributes attribs = Files.readAttributes(entry, BasicFileAttributes.class);
				if (attribs.isRegularFile()) {
					listFile.add(entry.toFile());
				}
			}

		} catch (IOException ex) {

			logger.error("scanDirectory error", ex);
		}
		return listFile;
	}

	/**
	 *
	 * copying files from the list in the archive
	 *
	 * @param listFile
	 *            - list of files to copy
	 * @param inputPathArch
	 *            - archive directory
	 */

	@Override
	public void copyFile(Collection<File> listFile, File inputPathArch) throws IOException {
		final char pathSeparator = 92;
		LocalDateTime dateTime = LocalDateTime.now();
		String localDateNow = dateTime.format(DateTimeFormatter.BASIC_ISO_DATE).toString();
		try {
			if (!listFile.isEmpty()) {
				for (File files : listFile) {
					String fullPath = files.toString();
					int sep = fullPath.lastIndexOf(pathSeparator);
					String fileName = fullPath.substring(sep + 1, (fullPath.length()));
					Path pathSource = Paths.get(files.toString());
					Path pathDestinationFull = Paths
							.get(inputPathArch.toString() + pathSeparator + localDateNow + pathSeparator + fileName);
					Path pathDestination = Paths.get(inputPathArch.toString() + pathSeparator + localDateNow);

					if (!pathDestination.toFile().exists()) {
						new File(pathDestination.toString()).mkdir();
					}
					Files.copy(pathSource, pathDestinationFull, StandardCopyOption.REPLACE_EXISTING);

				}

			}
		} catch (IOException ex) {

			logger.error("copyFile error", ex);
		}
	}

	/**
	 *
	 * deleting files list
	 *
	 * @param listFile
	 *            - list of files to remove
	 */
	@Override
	public void deleteFile(Collection<File> listFile) {

		for (File files : listFile) {

			File file = new File(files.toString());
			if (file.exists()) {
				file.delete();
			}
		}

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
	public List<String> readFile(String fileShow) throws IOException {

		List<String> file = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileShow), "Cp1251"));
			file = new ArrayList<>();
			String line;

			while ((line = br.readLine()) != null) {
				file.add(line);
			}

		} catch (IOException ex) {

			logger.error("readFile error", ex);
		}

		finally {
			br.close();
		}
		return file;
	}

}
