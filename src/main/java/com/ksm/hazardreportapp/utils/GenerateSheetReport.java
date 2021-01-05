/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.utils;

import com.ksm.hazardreportapp.entities.Reports;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author kelvi
 */
public class GenerateSheetReport {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Reports> reports;

    public GenerateSheetReport(List<Reports> reports) {
        this.reports = reports;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Reports");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Report ID", style);
        createCell(row, 1, "Date", style);
        createCell(row, 2, "Originator", style);
        createCell(row, 3, "Description", style);
        createCell(row, 4, "Location", style);
        createCell(row, 5, "Priority", style);
        createCell(row, 6, "Current Status", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Reports report : reports) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            String htmlString = report.getDescription().replaceAll("\\<.*?\\>", "");
            createCell(row, columnCount++, report.getId(), style);
            createCell(row, columnCount++, report.getDate().toString(), style);
            createCell(row, columnCount++, report.getOriginator().getName(), style);
            createCell(row, columnCount++, htmlString, style);
            createCell(row, columnCount++, report.getRoom().getName() + " - " + report.getRoom().getFloor().getName(), style);
            createCell(row, columnCount++, report.getPriority().getName(), style);
            createCell(row, columnCount++, report.getCurrentStatus().getName(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
