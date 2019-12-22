package com.example.demo.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;

//import static java.sql.Types.BOOLEAN;
//import static java.sql.Types.NUMERIC;
//import static org.apache.poi.ss.usermodel.DataValidationConstraint.ValidationType.FORMULA;
//import static org.apache.xmlbeans.impl.piccolo.xml.Piccolo.STRING;

@Service
public class Converter {

    private final static int  NUMERIC = 0;
    private static String firstInputQuery = "insert into tarif values ( ";
    private static String secondInputQuery = ",64,0,1, ";

    public File createFile(String name,String path) throws IOException {

        String fileSeparator = System.getProperty("file.separator");

        //absolute file name with path
        String absoluteFilePath = fileSeparator+"Users"+fileSeparator+"pankaj"+fileSeparator+"file.txt";
        File file = new File(absoluteFilePath);
        if(file.createNewFile()){
            System.out.println(absoluteFilePath+" File Created");
        }else System.out.println("File "+absoluteFilePath+" already exists");

        //file name only
        file = new File("file.txt");
        if(file.createNewFile()){
            System.out.println("file.txt File Created in Project root directory");
        }else System.out.println("File file.txt already exists in the project root directory");

        //relative path
        String relativePath = "tmp"+fileSeparator+"file.txt";
        file = new File(relativePath);
        if(file.createNewFile()){
            System.out.println(relativePath+" File Created in Project root directory");
        }else System.out.println("File "+relativePath+" already exists in the project root directory");

        return file;
    }

    public static void convertingExcelFile(){
        StringBuilder sb = new StringBuilder();

        try {
            String excelFilePath = "C:\\Users\\stojk\\OneDrive\\Desktop\\Book1.xlsx"; //D:\\resources\\myExcel.xlsx
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            File file = new File("C:\\Users\\stojk\\OneDrive\\Desktop\\writedatasamp.txt");
            Iterator<org.apache.poi.ss.usermodel.Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                sb.append(firstInputQuery);
                while (cellIterator.hasNext()) {
                    org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        /*case STRING:
                            System.out.print(cell.getStringCellValue());
                            sb.append(cell.getStringCellValue() + ", ");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue());
                            sb.append(String.valueOf(cell.getBooleanCellValue() + ", "));
                            break;*/
                        case NUMERIC:

                            System.out.print(cell.getNumericCellValue());
                            sb.append(String.valueOf(cell.getNumericCellValue() + ", "));
                            if( cell.getColumnIndex() == 0 ){ //za godine
                                sb.append(secondInputQuery);
                            }
                            if( cell.getColumnIndex() == 2 ){ //za godine
                                sb.append(");");
                            }
                            break;
                       /* case FORMULA:
                            System.out.print(cell.getCellFormula());
                            sb.append(String.valueOf(cell.getCellFormula() + ", "));
                            break;*/
                        default :
                            System.out.print(cell.getStringCellValue());
                            sb.append(cell.getStringCellValue() + ", ");
                    }
                    System.out.print(" - ");
                }
                System.out.println();
                sb.append("\n");
            }
            workbook.close();
            inputStream.close();
            Path path = Paths.get("C:\\Users\\stojk\\OneDrive\\Desktop\\writedatasamp.txt");
            Files.write(path, Arrays.asList(sb.toString()));

        }catch(Exception e) {
            e.printStackTrace();
        }
}

}


