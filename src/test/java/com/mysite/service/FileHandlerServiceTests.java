package com.mysite.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysite.service.FileHandlerServiceImpl;

public class FileHandlerServiceTests {
	@Autowired
	protected FileHandlerServiceImpl fileHandlerServiceImpl;
	@Autowired
	File inputPath = new File("c://from1C_test//");

	@Test
	public void scanDirectoryTest() throws IOException {
		fileHandlerServiceImpl = new FileHandlerServiceImpl();

		String ext = "*.xml";
		Collection<File> listFileXml = fileHandlerServiceImpl.scanDirectory(inputPath, ext);

		assertThat(listFileXml.size()).isEqualTo(0);

	}

	@Test
	public void scanDirectoryTest2() throws IOException {
		fileHandlerServiceImpl = new FileHandlerServiceImpl();
		File f = new File("c://from1C_test//1.xml");
		f.createNewFile();

		String ext = "*.xml";
		Collection<File> listFileXml = fileHandlerServiceImpl.scanDirectory(inputPath, ext);

		assertThat(listFileXml.size()).isEqualTo(1);
		for (File myFile : new File(inputPath.toString()).listFiles())
			if (myFile.isFile())
				myFile.delete();

	}

	@Test
	public void scanDirectoryTest3() throws IOException {
		fileHandlerServiceImpl = new FileHandlerServiceImpl();

		File f = new File("c://from1C_test//1.xml");
		f.createNewFile();
		File f2 = new File("c://from1C_test//2.xml");
		f2.createNewFile();
		String ext = "*.xml";
		Collection<File> listFileXml = fileHandlerServiceImpl.scanDirectory(inputPath, ext);

		assertThat(listFileXml.size()).isEqualTo(2);
		for (File myFile : new File(inputPath.toString()).listFiles())
			if (myFile.isFile())
				myFile.delete();

	}

	@Test
	public void scanDirectoryTest4() throws IOException {
		fileHandlerServiceImpl = new FileHandlerServiceImpl();

		File f = new File("c://from1C_test//1.xml");
		f.createNewFile();
		File f2 = new File("c://from1C_test//2.txt");
		f2.createNewFile();
		String ext = "*.xml";
		Collection<File> listFileXml = fileHandlerServiceImpl.scanDirectory(inputPath, ext);
		assertThat(listFileXml.size()).isEqualTo(1);
		for (File myFile : new File(inputPath.toString()).listFiles())
			if (myFile.isFile())
				myFile.delete();

	}

}
