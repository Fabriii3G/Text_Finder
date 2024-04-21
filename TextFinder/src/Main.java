import javax.swing.*;
import java.io.File;

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
    private DefaultListModel<String> Model;


    public Main(){
        setContentPane(MainPanel);
        setTitle("Text Finder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1300, 900);
        setLocationRelativeTo(null);
        Model = new DefaultListModel();
        ListOfFiles.setModel(Model);
        setVisible(true);
        System.out.println(AddButton.getBounds());
        AddButton.addActionListener(e -> AddToLib());
        RemoveButton.addActionListener(e -> RemoveFromLib());
        SearchButton.addActionListener(e -> SearchText());
        IndexingButton.addActionListener(e -> LibraryIndexing());
    }

    public void AddToLib() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = fileChooser.getSelectedFile();
            if (this.selectedFile.isFile()){
                this.controller = new FileController(this.selectedFile);
                AddToLibAux(this.selectedFile);
            }else{
                this.selectedFiles = this.selectedFile.listFiles();
                for (File file : this.selectedFiles){
                    this.controller = new FileController(file);
                    AddToLibAux(file);
                    }
                }
            }
        }
    public void AddToLibAux(File file){
        Model.addElement(file.getName());
    }
    public void SearchText(){
        String search = ToSearch.getText();
        this.controller.parser.Search(search);
    }
    public void RemoveFromLib(){
        int index = ListOfFiles.getSelectedIndex();
        Model.remove(index);
    }
    public void LibraryIndexing(){
        for (int i = 0; i < this.selectedFiles.length; i++) {
            File element = this.selectedFiles[i];
            System.out.println("Elemento en el índice " + i + ": " + element.toURI().toString());
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}
