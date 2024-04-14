import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import Parsers.*;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;

public class Main extends JFrame {
    private JPanel MainPanel;
    private JTextField ToSearch;
    private JLabel SearchLabel;
    private JButton SearchButton;

    public Main(){
        setContentPane(MainPanel);
        setTitle("Text Finder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        File pdfDocument = new File("C:\\Users\\XPC\\OneDrive - Estudiantes ITCR\\Escritorio\\Text_Finder\\TextFinder\\hola.pdf");
        PDFParser PDF = new PDFParser(pdfDocument);
        PDF.parser();
        File docxDocument = new File("C:\\Users\\XPC\\OneDrive - Estudiantes ITCR\\Escritorio\\Text_Finder\\TextFinder\\hola.docx");
        DOCXParser DOCX = new DOCXParser(docxDocument);
        DOCX.parser();
        File txtDocument =  new File("C:\\Users\\XPC\\OneDrive - Estudiantes ITCR\\Escritorio\\Text_Finder\\TextFinder\\hola.txt");
        TXTParser TXT = new TXTParser(txtDocument);
        TXT.parser();
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchTextFieldController();
            }
        });
    }

    public void SearchTextFieldController(){
        String Search = ToSearch.getText();
        if(!Search.isEmpty()){
            System.out.println(Search);
        }else{
            return;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
