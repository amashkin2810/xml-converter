package com.mysite.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import com.mysite.service.ConverterService;

public class FileControllerTest {

	private final Collection<File> listFiles = new ArrayList<File>();
	private final List<String> listFiles2 = new ArrayList<String>();

	@Before
	public void initFiles() {
		File file = new File("c:\\from1C_test\\1.xml");
		listFiles.add(file);
	}

	@Test
	public void testList() throws Exception {
		ConverterService converterService = mock(ConverterService.class);
		when(converterService.findCsvFiles()).thenReturn(listFiles);

		FileController fileController = new FileController();

		ReflectionTestUtils.setField(fileController, "ConverterService", converterService);

		ExtendedModelMap uiModel = new ExtendedModelMap();
		uiModel.addAttribute("listFiles", fileController.home(uiModel));

		assertEquals(1, uiModel.size());
		assertEquals(1, listFiles.size());
	}

	@Test
	public void testFileShow() throws Exception {
		ConverterService converterService = mock(ConverterService.class);
		String file2 = "c:\\from1C_test\\1.xml";
		when(converterService.readCsvFile(file2)).thenReturn(listFiles2);

		FileController fileController = new FileController();

		ReflectionTestUtils.setField(fileController, "ConverterService", converterService);

		ExtendedModelMap uiModel2 = new ExtendedModelMap();
		uiModel2.addAttribute("showFile", fileController.fileShow(file2, uiModel2));

		assertEquals(1, uiModel2.size());
		assertEquals(0, listFiles2.size());
	}

}
