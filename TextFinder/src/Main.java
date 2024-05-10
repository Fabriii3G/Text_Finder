import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import Controller.*;
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
        this.BubbleSort1();
        this.BubbleSort2();
        this.printFiles(SearchFiles);
    }

    public void BubbleSort1(){
        int n = SearchFiles.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (SearchFiles.get(j).getName().compareTo(SearchFiles.get(j + 1).getName()) > 0) {
                    File temp = SearchFiles.get(j);
                    SearchFiles.set(j, SearchFiles.get(j + 1));
                    SearchFiles.set(j + 1, temp);
                }
            }
        }
    }
    public void BubbleSort2(){
        int n = searchResultsModel.getSize();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (searchResultsModel.getElementAt(j).compareTo(searchResultsModel.getElementAt(j + 1)) > 0) {
                    String temp = searchResultsModel.getElementAt(j);
                    searchResultsModel.set(j, searchResultsModel.getElementAt(j + 1));
                    searchResultsModel.set(j + 1, temp);
                }
            }
        }
    }

    public void QuickSort(){
        QuickSort1(SearchFiles, 0, SearchFiles.size() - 1);
        QuickSort2(searchResultsModel, 0, searchResultsModel.size() - 1);
    }
    public void QuickSort1(ArrayList<File> fileList, int low, int high){
        if (low < high) {
            int pi = Partition(SearchFiles, low, high);
            QuickSort1(fileList, low, pi - 1);
            QuickSort1(fileList, pi + 1, high);
        }
    }

    public int Partition(ArrayList<File> fileList, int low, int high) {
        String pivot = fileList.get(high).getName();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (fileList.get(j).getName().compareTo(pivot) < 0) {
                i++;

                File temp = fileList.get(i);
                fileList.set(i, fileList.get(j));
                fileList.set(j, temp);
            }
        }
        File temp = fileList.get(i + 1);
        fileList.set(i + 1, fileList.get(high));
        fileList.set(high, temp);
        return i + 1;
    }
    public void QuickSort2(DefaultListModel<String> listModel, int low, int high) {
        if (low < high) {
            int pi = Partition(listModel, low, high);

            QuickSort2(listModel, low, pi - 1);
            QuickSort2(listModel, pi + 1, high);
        }
    }
    public int Partition(DefaultListModel<String> listModel, int low, int high) {
        String pivot = listModel.getElementAt(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (listModel.getElementAt(j).compareTo(pivot) < 0) {
                i++;

                String temp = listModel.getElementAt(i);
                listModel.set(i, listModel.getElementAt(j));
                listModel.set(j, temp);
            }
        }
        String temp = listModel.getElementAt(i + 1);
        listModel.set(i + 1, listModel.getElementAt(high));
        listModel.set(high, temp);
        return i + 1;
    }

    public void RadixSort(){

    }

    public void RadixSort1(){

    }
    public void RadixSort2(){

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
