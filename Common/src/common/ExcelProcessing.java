package common;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelProcessing {
	private String filepath;
	private String Sheetname;

	public ExcelProcessing(String fPath, String shName) {
		filepath = fPath;
		Sheetname = shName;
	}

	public String[][] readDataFromExcel(int sRow, int eRow, int sCol, int eCol) {
		String[][] data = new String[eRow - sRow + 1][eCol - sCol + 1];
		try {
			FileInputStream input = new FileInputStream(filepath);
			XSSFWorkbook wb = new XSSFWorkbook(input);

			XSSFSheet sh = wb.getSheet(Sheetname);
			int stRow = 0;
			int stCol;
			for (int intR = sRow; intR <= eRow; intR++) {
				XSSFRow row = sh.getRow(intR);
				stCol = 0;
				for (int intC = sCol; intC <= eCol; intC++) {
					data[stRow][stCol] = row.getCell(intC).toString();
					stCol++;
				}
				stRow++;
			}
			wb.close();
		} catch (Exception e) {
			CustomReporter.MessageLogger(e.getMessage(), CustomReporter.status.Error);
		}
		return data;
	}

	public void writeDataToExcel(int sRow, int eRow, int sCol, int eCol, String[][] value) {
		try {
			FileInputStream input = new FileInputStream(filepath);
			XSSFWorkbook wb = new XSSFWorkbook(input);
			if (wb.getSheet(Sheetname) == null) {
				wb.createSheet(Sheetname);
			}

			XSSFSheet sh = wb.getSheet(Sheetname);
			FileOutputStream webdata = new FileOutputStream(filepath);
			try {
				int stRow = 0;
				int stCol = 0;
				for (int intR = sRow; intR <= eRow; intR++) {
					XSSFRow row = sh.getRow(intR);

					stCol = 0;
					for (int intC = sCol; intC <= eCol; intC++) {
						if (value[stRow][stCol] != null) {
							row.createCell(intC).setCellValue(value[stRow][stCol].toString());
						}
						stCol++;
					}
					stRow++;
				}
			} catch (Exception e) {
				CustomReporter.MessageLogger(e.getMessage(), CustomReporter.status.Error);
			}
			wb.write(webdata);
			wb.close();
		} catch (Exception e) {
			CustomReporter.MessageLogger(e.getMessage(), CustomReporter.status.Error);
		}
	}

	public String[][] findCell(String sData, int iSearchCol, int sCol, int eCol) {
		String[][] data = new String[1][eCol - sCol + 1];
		try {
			FileInputStream input = new FileInputStream(filepath);
			XSSFWorkbook wb = new XSSFWorkbook(input);
			XSSFSheet sh = wb.getSheet(Sheetname);
			int intR;
			int stCol = 0;
			for (intR = 0; intR <= 1000; intR++) {
				XSSFRow row = sh.getRow(intR);
				if (row.getCell(iSearchCol).toString().equalsIgnoreCase(sData)) {
					for (int intC = sCol; intC <= eCol; intC++) {
						if (row.getCell(intC).toString() != null) {
							data[0][stCol] = row.getCell(intC).toString();
						}
						stCol++;
					}
					break;
				}
			}
			wb.close();
		} catch (Exception e) {
			CustomReporter.MessageLogger(e.getMessage(), CustomReporter.status.Error);
		}
		return data;
	}
}
