package Parsers;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
public class PDFParser implements Parser {
    File PDF;
    String PDFPath;
    String parsedText;
    public PDFParser(File PdfFile){
        this.PDF = PdfFile;
        this.PDFPath = this.PDF.getPath();
        this.parsedText = parser();
    }
    @Override
    public String parser() {
        try {
            PDDocument document = PDDocument.load(new File(this.PDFPath));
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String ParsedText = pdfTextStripper.getText(document);
            document.close();
            return ParsedText;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}