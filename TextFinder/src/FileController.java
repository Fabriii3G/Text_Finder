import java.io.File;
import Parsers.*;
import DataStructures.*;

public class FileController {
    File file;
    File files[];
    File fileDOCX;
    File fileTXT;
    File filePDF;
    String name;
    Parser parser;
    AVLTree avlTree = new AVLTree();
    public FileController(File file) {
        this.file = file;
        this.name = this.file.getName();
        if (name.endsWith(".docx")) {
            this.fileDOCX = file;
            parser = new DOCXParser(this.fileDOCX);
            SinglyLinkedList ocurrences = new SinglyLinkedList();
            ToAVLTree(parser.parser(), ocurrences);
        } else if (name.endsWith(".pdf")) {
            this.filePDF = file;
            parser = new PDFParser(this.filePDF);
            SinglyLinkedList ocurrences = new SinglyLinkedList();
            ToAVLTree(parser.parser(), ocurrences);
        } else {
            this.fileTXT = file;
            parser = new TXTParser(this.fileTXT);
            SinglyLinkedList ocurrences = new SinglyLinkedList();
            ToAVLTree(parser.parser(), ocurrences);
        }
    }
    public void ToAVLTree(String text, SinglyLinkedList ocurrences){
        if (text != null && !text.isEmpty()) {
            // Dividir el texto en palabras usando espacios como delimitador
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

