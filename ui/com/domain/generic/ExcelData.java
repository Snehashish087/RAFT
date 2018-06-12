package com.domain.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData
{
	public static String getData(String PATH,String SheetNumber,int row_Num,int col_Num) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
			FileInputStream fis=new FileInputStream(new File(PATH));
			String data=WorkbookFactory.create(fis).getSheet(SheetNumber).getRow(row_Num).getCell(col_Num).toString();
			return data;
	}
}
