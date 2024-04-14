import javax.swing.*;
import java.io.File;

public class Main extends JFrame {
    private JLabel Label1;
    private JPanel MainPanel;

    public Main(){
        setContentPane(MainPanel);
        setTitle("Text Finder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        File document = new File("C:\\Users\\XPC\\OneDrive - Estudiantes ITCR\\Escritorio\\Text_Finder\\TextFinder\\hola.pdf");
        PDFParser PDF = new PDFParser(document);
        PDF.parser();
        File docxDocument = new File("C:\\Users\\XPC\\OneDrive - Estudiantes ITCR\\Escritorio\\Text_Finder\\TextFinder\\hola.docx");
        DOCXParser DOCX = new DOCXParser(docxDocument);
        DOCX.parser();
    }

    public static void main(String[] args) {
        new Main();
    }
}
