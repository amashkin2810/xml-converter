
package com.mysite.web;

import java.util.List; 

import java.io.File;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

import com.mysite.service.ConverterService;

/**
 * 
 *
 */

//@RestController
@Controller
//@CrossOrigin(origins = "http://localhost:9000")
public class FileController {

	private ConverterService ConverterService;

	@Autowired
	void setConverterService(ConverterService ConverterService) {
		this.ConverterService = ConverterService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)

	public String index () throws Exception {
			
		return "/index";
	}

	/**
	 * @return - list csv files found in the directory
	 */
	@RequestMapping(value = "/listCsvFiles", method = RequestMethod.GET)

	public String home(Model uiModel) throws Exception {
		Collection<File> listFiles = ConverterService.findCsvFiles();

		uiModel.addAttribute("listFiles", listFiles);

		return "/listCsvFiles";
	}

	/**
	 * @return the contents of the file
	 */
	@RequestMapping(value = "/{fileShow}", method = RequestMethod.GET, produces = "application/json")
	public String fileShow(@RequestParam("fileShow") String fileShow, Model uiModel) throws Exception {

		List<String> showFile = ConverterService.readCsvFile(fileShow);

		uiModel.addAttribute("showFile", showFile);
		return "/ContentsCsvFile";
	}

}
