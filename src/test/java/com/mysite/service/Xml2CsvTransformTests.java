package com.mysite.service;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import com.mysite.service.Xml2CsvTrasform;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Xml2CsvTransformTests {

	private Xml2CsvTrasform xml2CsvTrasform;
	File outputPath = new File("c:\\111");

	@Before
	public void beforeTest() throws IOException {

		File file = new File("c:\\from1C_test\\1.xml");
		file.createNewFile();

	}

	@After
	public void afterTest() {
		File file = new File("c:\\from1C_test\\1.xml");
		if (file.exists()) {
			file.delete();
		}
	}

	@Test

	public void fileZeroLengthTest() throws Exception {

		Collection<File> file = new ArrayList<>();
		file.add(new File("c:\\from1C_test\\1.xml"));

		xml2CsvTrasform = new Xml2CsvTrasform();

		xml2CsvTrasform.convert(file, this.outputPath);

		assertThat(file.size()).isEqualTo(1);
		assertThat(file.size()).isNotEqualTo(0);

	}

}
