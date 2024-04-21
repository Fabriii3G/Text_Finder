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
    public SinglyLinkedList ocurrences = new SinglyLinkedList();
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
            ToAVLTree(parser.parser(), ocurrences);
        } else if (name.endsWith(".pdf")) {
            this.filePDF = file;
            parser = new PDFParser(this.filePDF);
            ToAVLTree(parser.parser(), ocurrences);
        } else {
            this.fileTXT = file;
            parser = new TXTParser(this.fileTXT);
            ToAVLTree(parser.parser(), ocurrences);
        }
    }
    public void ToAVLTree(String text, SinglyLinkedList ocurrences){
        if (text != null && !text.isEmpty()) {
            String[] words = text.split("\\s+");
            for (String word : words) {
                avlTree.insert(word, ocurrences);
                System.out.println(word);
            }
            avlTree.printTree();
        } else {
            System.out.println("El texto parseado está vacío o es nulo.");
        }
    }
}

