package Controller;
import java.io.File;
import java.util.ArrayList;
import Parsers.*;
import DataStructures.*;
import javax.swing.DefaultListModel;


public class FileController {
    public File file;
    //public File files[];
    public File fileDOCX;
    public File fileTXT;
    public File filePDF;
    public String name;
    public Parser parser;
    public AVLTree avlTree = new AVLTree();
    public ArrayList<File> files;



    public FileController(ArrayList<File> files) {
        this.files = files;
        indexFiles();
    }

    public void indexFiles() {
        for (File file : files) {
            FileControllerAux(file);
        }
    }

    public void FileControllerAux(File file) {
        String name = file.getName();
        if (name.endsWith(".docx")) {
            DOCXParser docxParser = new DOCXParser(file);
            ToAVLTree(docxParser.parser());
        } else if (name.endsWith(".pdf")) {
            PDFParser pdfParser = new PDFParser(file);
            ToAVLTree(pdfParser.parser());
        } else if (name.endsWith(".txt")) {
            TXTParser txtParser = new TXTParser(file);
            ToAVLTree(txtParser.parser());
        }
    }
    public void ToAVLTree(String text){
        if (text != null && !text.isEmpty()) {
            String[] words = text.split("\\s+");
            for (String word : words) {
                avlTree.insert(word);
                System.out.println(word);
            }
            avlTree.printTree();
        } else {
            System.out.println("El texto parseado está vacío o es nulo.");
        }
    }
    public void search(ArrayList<File> files, String word, DefaultListModel<String> model) {
        for (File file : files) {
            searchFile(file, word, model);
        }
    }

    public void searchFile(File file, String word, DefaultListModel<String> searchResultsModel) {
        String name = file.getName();
        if (name.endsWith(".docx")) {
            DOCXParser docxParser = new DOCXParser(file);
            String text = docxParser.parser();
            if (text.contains(word)) {
                searchResultsModel.addElement("Se ha encontrado en: " + file.getName());
            }
        } else if (name.endsWith(".pdf")) {
            PDFParser pdfParser = new PDFParser(file);
            String text = pdfParser.parser();
            if (text.contains(word)) {
                searchResultsModel.addElement("Se ha encontrado en: " + file.getName());
            }
        } else if (name.endsWith(".txt")) {
            TXTParser txtParser = new TXTParser(file);
            String text = txtParser.parser();
            if (text.contains(word)) {
                searchResultsModel.addElement("Se ha encontrado en: " + file.getName());
            }
        } else {
            searchResultsModel.addElement("No se encuentra");
        }
    }
}

