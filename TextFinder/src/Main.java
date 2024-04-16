import javax.swing.*;
import java.io.File;
import Parsers.*;


public class Main extends JFrame {
    private JPanel MainPanel;
    private JTextField ToSearch;
    private JLabel SearchLabel;
    private JButton SearchButton;
    private JLabel MainLabel;


    public Main(){
        setContentPane(MainPanel);
        setTitle("Text Finder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        SearchButton.addActionListener(e -> SearchTextFieldController());
        File pdfDocument = new File("C:\\Users\\XPC\\OneDrive - Estudiantes ITCR\\Escritorio\\Text_Finder\\TextFinder\\hola.pdf");
        PDFParser PDF = new PDFParser(pdfDocument);
        PDF.parser();
        File docxDocument = new File("C:\\Users\\XPC\\OneDrive - Estudiantes ITCR\\Escritorio\\Text_Finder\\TextFinder\\hola.docx");
        DOCXParser DOCX = new DOCXParser(docxDocument);
        DOCX.parser();
        File txtDocument =  new File("C:\\Users\\XPC\\OneDrive - Estudiantes ITCR\\Escritorio\\Text_Finder\\TextFinder\\hola.txt");
        TXTParser TXT = new TXTParser(txtDocument);
        TXT.parser();
    }

    public void SearchTextFieldController(){
    }

    public static void main(String[] args) {
        new Main();
    }
}
