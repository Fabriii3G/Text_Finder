package Parsers;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import java.io.IOException;
public class DOCXParser implements Parser {
    File DOCX;
    String DOCXPath;
    public DOCXParser(File DOCXFile){
        this.DOCX = DOCXFile;
        this.DOCXPath = this.DOCX.getPath();
    }
    @Override
    public String parser() {
        try {
            FileInputStream fis = new FileInputStream(this.DOCX);
            XWPFDocument document = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            String parsedText = extractor.getText();
            fis.close();
            System.out.println(parsedText);
            return parsedText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
