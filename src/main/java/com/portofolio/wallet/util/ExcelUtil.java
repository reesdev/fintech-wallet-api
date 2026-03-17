package com.portofolio.wallet.util;

import com.portofolio.wallet.entity.Transaction;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExcelUtil {

    public static byte[] generateTransactionExcel(List<Transaction> transactions) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Transactions");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Type");
            header.createCell(1).setCellValue("Amount");
            header.createCell(2).setCellValue("Reference ID");
            header.createCell(3).setCellValue("Created At");

            int rowIdx = 1;

            for (int i = 0; i < transactions.size(); i++) {
                Transaction tx = transactions.get(i);
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(tx.getType());
                row.createCell(1).setCellValue(tx.getAmount().doubleValue());
                row.createCell(2).setCellValue(tx.getReferenceId());
                row.createCell(3).setCellValue(tx.getCreatedAt().toString());
            }

            workbook.write(out);
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Excel");
        }
    }
}
