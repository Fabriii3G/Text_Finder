import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import Controller.*;
import DataStructures.SortingAlgorithms;

public class Main extends JFrame {
    private JPanel MainPanel;
    private JTextField ToSearch;
    private JButton SearchButton;
    private JButton AddButton;
    private JLabel MainLabel;
    private JButton RemoveButton;
    private JButton IndexingButton;
    private File selectedFile;
    private File[] selectedFiles;
    private FileController controller;
    private JList ListOfFiles;
    private JList list1;
    private JButton openButton;
    private JScrollPane FilePanel;
    private JLabel PageLabel;
    private JLabel SortLabel;
    private JButton QuickSortButton;
    private JButton BubbleSortButton;
    private JButton RadixSortButton;
    private DefaultListModel<String> Model;
    private DefaultListModel<String> searchResultsModel;
    private ArrayList<File> Files = new ArrayList<>();
    private ArrayList<File> SearchFiles = new ArrayList<>();
    private SortingAlgorithms sortingAlgorithms;

    public Main(){
        setContentPane(MainPanel);
        setTitle("Text Finder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1300, 900);
        setLocationRelativeTo(null);
        Model = new DefaultListModel();
        ListOfFiles.setModel(Model);
        setVisible(true);
        searchResultsModel = new DefaultListModel<>();
        list1.setModel(searchResultsModel);
        AddButton.addActionListener(e -> AddToLib());
        RemoveButton.addActionListener(e -> RemoveFromLib());
        SearchButton.addActionListener(e -> SearchText());
        IndexingButton.addActionListener(e -> LibIndexing());
        openButton.addActionListener(e -> openFile());
        QuickSortButton.addActionListener(e -> QuickSort());
        BubbleSortButton.addActionListener(e -> BubbleSort());
        RadixSortButton.addActionListener(e -> RadixSort());
        FilePanel.setVisible(false);
    }
    public void openFile(){
        FilePanel.setBounds(345, 60, 297, 591);
        FilePanel.setSize(600, 800);
        FilePanel.setVisible(true);
        int index = list1.getSelectedIndex();
        File file = SearchFiles.get(index);
        String name = file.getName();
        if(name.endsWith(".pdf")){
            this.controller.OpenPDF(file, FilePanel);
        }else if(name.endsWith(".txt")){
            this.controller.OpenTXT(file, FilePanel);
        }else{
            this.controller.OpenDOCX(file, FilePanel);
        }
    }
    public void AddToLib() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = fileChooser.getSelectedFile();
            AddToLibAux(this.selectedFile);
            }
        }
    public void AddToLibAux(File file){
        if (file.isFile()){
            Model.addElement(file.getName());
            Files.add(file);
        }else{
            this.selectedFiles = file.listFiles();
            for (File file1 : this.selectedFiles){
                Model.addElement(file1.getName());
                Files.add(file1);
            }
        }
    }
    public void RemoveFromLib(){
        int index = ListOfFiles.getSelectedIndex();
        Files.remove(index);
        Model.remove(index);
    }
    public void LibIndexing(){
        this.controller = new FileController(this.Files);
    }
    public void SearchText(){
        this.searchResultsModel.clear();
        this.SearchFiles.clear();
        String search = ToSearch.getText();
        this.controller.search(this.Files, search, this.searchResultsModel, this.SearchFiles);
    }

    public void BubbleSort(){
        SortingAlgorithms sorting = new SortingAlgorithms(SearchFiles, searchResultsModel);
        sorting.BubbleSort1();
        sorting.BubbleSort2();
        this.printFiles(SearchFiles);
    }
    public void QuickSort(){
        SortingAlgorithms sorting = new SortingAlgorithms(SearchFiles, searchResultsModel);
        sorting.QuickSort1(SearchFiles, 0, SearchFiles.size() - 1);
        sorting.QuickSort2(searchResultsModel, 0, searchResultsModel.size() - 1);
        this.printFiles(SearchFiles);
    }
    public void RadixSort(){
        SortingAlgorithms sorting = new SortingAlgorithms(SearchFiles, searchResultsModel);
        sorting.RadixSort1();
        sorting.RadixSort2();
    }
    public void printFiles(ArrayList<File> files) {
        for (File file : files) {
            System.out.println(file.getName() + " - Fecha de creación: " + file.lastModified());
        }
        System.out.println();
    }
    public static void main(String[] args) {
        new Main();
    }
}
