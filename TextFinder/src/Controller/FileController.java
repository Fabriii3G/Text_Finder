package Controller;
import java.io.File;
import java.util.ArrayList;
import Parsers.*;
import DataStructures.*;

public class FileController {
    public File file;
    public File files[];
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
            ToAVLTree(parser.parser());
        } else if (name.endsWith(".pdf")) {
            this.filePDF = file;
            parser = new PDFParser(this.filePDF);
            ToAVLTree(parser.parser());
        } else {
            this.fileTXT = file;
            parser = new TXTParser(this.fileTXT);
            ToAVLTree(parser.parser());
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
    public void search(ArrayList<File> files, String word){
        for (File file1 : files ) {
            this.search_aux(file1, word);
        }
    }
    public void search_aux(File file, String word) {
        String name = file.getName();
        if (name.endsWith(".docx")) {
            DOCXParser docxParser = new DOCXParser(file);
            String text = docxParser.parser();
            if (text.contains(word)) {
                System.out.println("Se ha encontrado en:" + name);
            }
        } else if (name.endsWith(".pdf")) {
            PDFParser pdfParser = new PDFParser(file);
            String text = pdfParser.parser();
            if (text.contains(word)) {
                System.out.println("Se ha encontrado en:" + name);
            }
        } else if (name.endsWith(".txt")) {
            TXTParser txtParser = new TXTParser(file);
            String text = txtParser.parser();
            if (text.contains(word)) {
                System.out.println("Se ha encontrado en:" + name);
            }
        } else {
            System.out.println("No se encuentra");
        }
    }
}

