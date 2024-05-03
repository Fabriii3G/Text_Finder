import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
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
    private DefaultListModel<String> addedFilesModel;
    private DefaultListModel<String> searchResultsModel;
    private ArrayList<File> Files = new ArrayList<>();

    public Main(){
        setContentPane(MainPanel);
        setTitle("Text Finder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1300, 900);
        setLocationRelativeTo(null);
        addedFilesModel = new DefaultListModel();
        ListOfFiles.setModel(addedFilesModel);
        setVisible(true);
        searchResultsModel = new DefaultListModel<>();
        this.list1.setModel(searchResultsModel);
        setVisible(true);
        AddButton.addActionListener(e -> AddToLib());
        RemoveButton.addActionListener(e -> RemoveFromLib());
        SearchButton.addActionListener(e -> SearchText());
        IndexingButton.addActionListener(e -> LibIndexing());
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
            addedFilesModel.addElement(file.getName());
            Files.add(file);
        }else{
            this.selectedFiles = file.listFiles();
            for (File file1 : this.selectedFiles){
                addedFilesModel.addElement(file1.getName());
                Files.add(file1);
            }
        }
    }
    public void RemoveFromLib(){
        int index = ListOfFiles.getSelectedIndex();
        Files.remove(index);
        addedFilesModel.remove(index);
    }
    public void LibIndexing(){
        this.controller = new FileController(this.Files);
    }
    //Aun no funciona como deberia
    public void SearchText(){
        String search = ToSearch.getText();
        this.controller.search(this.Files, search, searchResultsModel);
    }
    public static void main(String[] args) {
        new Main();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
