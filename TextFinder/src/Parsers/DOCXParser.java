package Parsers;
import java.io.File;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.IOException;
public class DOCXParser implements Parser {
    File DOCX;
    String DOCXPath;
    public DOCXParser(File DOCXFile){
        this.DOCX = DOCXFile;
        this.DOCXPath = this.DOCX.getPath();
    }
    @Override
    public void parser(){
        try {
            OPCPackage opcPackage = OPCPackage.open(this.DOCX);
            XWPFDocument xwpfDocument = new XWPFDocument(opcPackage);
            for (XWPFParagraph paragraph : xwpfDocument.getParagraphs()){
                System.out.println(paragraph.getText());
            }
            opcPackage.close();
        } catch (IOException e){
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
