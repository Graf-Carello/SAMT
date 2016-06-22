package at.fh.swenga.samt.report;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import at.fh.swenga.samt.model.ProjectModel;

public class ExcelEmployeeReportView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<ProjectModel> projects = (List<ProjectModel>) model.get("projects");

		// create a worksheet
		CreationHelper createHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("Homework Report");
		Footer footer = sheet.getFooter();
		footer.setRight("Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages());

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.DARK_RED.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		// create a new row in the worksheet
		Row headerRow = sheet.createRow(0);

		// create a new cell in the row
		Cell cell0 = headerRow.createCell(0);
		cell0.setCellValue("ID");
		cell0.setCellStyle(style);

		// create a new cell in the row
		Cell cell1 = headerRow.createCell(1);
		cell1.setCellValue("Project Name");
		cell1.setCellStyle(style);

		// create a new cell in the row
		Cell cell2 = headerRow.createCell(2);
		cell2.setCellValue("Deadline  ");
		cell2.setCellStyle(style);

		// create a new cell in the row
		Cell cell3 = headerRow.createCell(3);
		cell3.setCellValue("Progress");
		cell3.setCellStyle(style);

		// create a new cell in the row
		Cell cell4 = headerRow.createCell(4);
		cell4.setCellValue("Course");
		cell4.setCellStyle(style);

		// create a new cell in the row
		Cell cell5 = headerRow.createCell(5);
		cell5.setCellValue("Days left");
		cell5.setCellStyle(style);

		// create multiple rows with project data
		int rowNum = 1;
		for (ProjectModel project : projects) {
			// create the row data
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(project.getId());
			row.createCell(1).setCellValue(project.getProjectName());

			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));

			cell2 = row.createCell(2);
			cell2.setCellValue(new Date());
			cell2.setCellStyle(cellStyle);
			cell2.setCellValue(project.getDeadline());

			cell3 = row.createCell(3);
			cell3.setCellValue(0.123);
			CellStyle stylePercent = workbook.createCellStyle();
			stylePercent.setDataFormat(workbook.createDataFormat().getFormat("0 %"));
			cell3.setCellStyle(stylePercent);

			double progressValue = (double) (project.getProgress());
			double progressPercent = progressValue / 100;
			cell3.setCellValue(progressPercent);

			row.createCell(4).setCellValue(project.getCourse());

			Date deadline = project.getDeadline();
			Date now = new Date();
			long diff = deadline.getTime() - now.getTime();
			long diffDays = diff / (60 * 60 * 1000 * 24);

			row.createCell(5).setCellValue(diffDays);

		}

		// adjust column width to fit the content
		sheet.autoSizeColumn((short) 0);
		sheet.autoSizeColumn((short) 1);
		sheet.autoSizeColumn((short) 2);
		sheet.autoSizeColumn((short) 3);
		sheet.autoSizeColumn((short) 4);
		sheet.autoSizeColumn((short) 5);

	}

}
