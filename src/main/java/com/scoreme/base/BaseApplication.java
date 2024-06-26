package com.scoreme.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@SuppressWarnings({ "deprecation", "unused" })
@SpringBootApplication
public class BaseApplication {

	public static void main(String[] args) throws JRException {
		SpringApplication.run(BaseApplication.class, args);
		String filePath = "D:\\VS Folders\\jproject\\base\\src\\main\\resources\\templates\\n" + //
				"utritionreport.jrxml";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("firstName", "John");
		parameters.put("lastName", "Smith");
		parameters.put("dob", "14/04/2004");
		parameters.put("age", 20);

		JasperReport report = JasperCompileManager.compileReport(filePath);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
		// JasperExportManager.exportReportToPdfFile(print,
		// 		"D:\\VS Folders\\jproject\\base\\src\\main\\resources\\static\\nutritionreport.pdf");

		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "D:\\VS Folders\\jproject\\base\\src\\main\\resources\\static\\nutritionreport.xlsx");
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);

		exporter.exportReport();

		System.out.println("Report generated successfully...");
	}

}
