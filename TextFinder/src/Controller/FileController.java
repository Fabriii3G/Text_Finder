package Controller;
import java.io.*;
import java.util.ArrayList;
import Parsers.*;
import DataStructures.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;

public class FileController {
    public File file;
    public File fileDOCX;
    public File fileTXT;
    public File filePDF;
    public String name;
    public Parser parser;
    public AVLTree avlTree = new AVLTree();
    public FileController(ArrayList<File> files) {
        for (File file : files){
            FileControllerAux(file);
        }
    }
    public void FileControllerAux(File file) {
        this.file = file;
        this.name = this.file.getName();
        if (name.endsWith(".docx")) {
            this.fileDOCX = file;
            parser = new DOCXParser(this.fileDOCX);
            ToAVLTree(parser.parser(), name);
        } else if (name.endsWith(".pdf")) {
            this.filePDF = file;
            parser = new PDFParser(this.filePDF);
            ToAVLTree(parser.parser(), name);
        } else {
            this.fileTXT = file;
            parser = new TXTParser(this.fileTXT);
            ToAVLTree(parser.parser(), name);
        }
    }

    public String highlightWord(String text, String word) {
        return text.replaceAll("(?i)" + word, "<u><b>$0</b></u>");
    }


    public void OpenPDF(File file, JScrollPane scroll, JTextField toSearch) {
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        scroll.setViewportView(textPane);
        parser = new PDFParser(file);
        String text = parser.parser();
        text = text.replaceAll("\n", "<br>");
        text = highlightWord(text, toSearch.getText());
        String style = "<style>.highlight { color: red; }</style>";
        text = text.replaceAll(toSearch.getText(), "<span class='highlight'>" + toSearch.getText() + "</span>");
        textPane.setContentType("text/html");
        textPane.setText("<html><head>" + style + "</head><body>" + text + "</body></html>");
    }

    public void OpenTXT(File file, JScrollPane scroll, JTextField toSearch) {
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        scroll.setViewportView(textPane);
        parser = new TXTParser(file);
        String text = parser.parser();
        text = text.replaceAll("\n", "<br>");
        text = highlightWord(text, toSearch.getText());
        String style = "<style>.highlight { color: red; }</style>";
        text = text.replaceAll(toSearch.getText(), "<span class='highlight'>" + toSearch.getText() + "</span>");
        textPane.setContentType("text/html");
        textPane.setText("<html><head>" + style + "</head><body>" + text + "</body></html>");
    }

    public void OpenDOCX(File file, JScrollPane scroll, JTextField toSearch) {
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        scroll.setViewportView(textPane);
        parser = new DOCXParser(file);
        String text = parser.parser();
        text = text.replaceAll("\n", "<br>");
        text = highlightWord(text, toSearch.getText());
        String style = "<style>.highlight { color: red; }</style>";
        text = text.replaceAll(toSearch.getText(), "<span class='highlight'>" + toSearch.getText() + "</span>");
        textPane.setContentType("text/html");
        textPane.setText("<html><head>" + style + "</head><body>" + text + "</body></html>");
    }

    public void ToAVLTree(String text, String Doc){
        if (text != null && !text.isEmpty()) {
            String[] words = text.split("\\s+");
            int i = 1;
            for (String word : words) {
                avlTree.insert(word, i, Doc);
                System.out.println(word);
                i++;
            }
            avlTree.printTree();
        } else {
            System.out.println("El texto parseado está vacío o es nulo.");
        }
    }

    public void search(ArrayList<File> files, String word, DefaultListModel<String> model, ArrayList<File> SearchFiles){
        for (File file1 : files ) {
            this.search_aux(file1, word, model, SearchFiles);
        }
    }
    public void search_aux(File file, String word, DefaultListModel<String> searchResultsModel, ArrayList<File> SearchFiles) {
        String name = file.getName();
        if (name.endsWith(".docx")) {
            DOCXParser docxParser = new DOCXParser(file);
            String text = docxParser.parser();
            if (text.contains(word)) {
                searchResultsModel.addElement(name);
                SearchFiles.add(file);
            }
        } else if (name.endsWith(".pdf")) {
            PDFParser pdfParser = new PDFParser(file);
            String text = pdfParser.parser();
            if (text.contains(word)) {
                searchResultsModel.addElement(name);
                SearchFiles.add(file);
            }
        } else if (name.endsWith(".txt")) {
            TXTParser txtParser = new TXTParser(file);
            String text = txtParser.parser();
            if (text.contains(word)) {
                searchResultsModel.addElement(name);
                SearchFiles.add(file);
            }
        } else {
            searchResultsModel.addElement("No está en la biblioteca");
        }
    }
}

