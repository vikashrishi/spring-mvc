package com.example;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WrittingDataToExcel {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			
		    Class.forName("org.postgresql.Driver").newInstance();
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/demo-database", "postgres", "admin");
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("Select * from person");

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Excel Sheet");
			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell((short) 0).setCellValue("Email");
			rowhead.createCell((short) 1).setCellValue("First_Name");
			rowhead.createCell((short) 2).setCellValue("Last_Name");
			rowhead.createCell((short) 3).setCellValue("Person_Id");
			rowhead.createCell((short) 4).setCellValue("Age");

			int index = 1;
			while (rs.next()) {

				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(rs.getString(1));
				row.createCell((short) 1).setCellValue(rs.getString(2));
				row.createCell((short) 2).setCellValue(rs.getString(3));
				row.createCell((short) 3).setCellValue(rs.getInt(4));
				row.createCell((short) 4).setCellValue(rs.getInt(5));
				index++;
			}
			FileOutputStream fileOut = new FileOutputStream("E:\\excelFile.xls");
			wb.write(fileOut);
			fileOut.close();
			System.out.println("Data is saved in excel file.");
			rs.close();
			connection.close();
		} catch (Exception e) {
		}
    }
}

