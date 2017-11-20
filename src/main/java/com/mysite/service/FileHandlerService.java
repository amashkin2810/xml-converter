package com.mysite.service;

import java.io.File; 
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface FileHandlerService {

	void copyFile(Collection<File> listFile, File inputPathArch) throws IOException;

	void deleteFile(Collection<File> listFile) ;

	Collection<File> scanDirectory(File directory, String ext) throws IOException;

	List<String> readFile(String fileShow) throws IOException;

}
