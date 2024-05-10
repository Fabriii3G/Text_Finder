package DataStructures;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class SortingAlgorithms {
    private DefaultListModel<String> searchResultsModel;
    private ArrayList<File> SearchFiles;

    public SortingAlgorithms(ArrayList<File> Files, DefaultListModel<String> Model) {
        this.searchResultsModel = Model;
        this.SearchFiles = Files;
    }

    // Ordenar por fecha del archivo
    public void BubbleSort() {
        int n = SearchFiles.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (SearchFiles.get(j).lastModified() > SearchFiles.get(j + 1).lastModified()) {
                    File temp = SearchFiles.get(j);
                    SearchFiles.set(j, SearchFiles.get(j + 1));
                    SearchFiles.set(j + 1, temp);
                }
            }
        }
    }
    // Ordenar por nombre del archivo
    public void QuickSort(ArrayList<File> fileList, int low, int high) {
        if (low < high) {
            int pi = Partition(SearchFiles, low, high);
            QuickSort(fileList, low, pi - 1);
            QuickSort(fileList, pi + 1, high);
        }
    }
    private int Partition(ArrayList<File> fileList, int low, int high) {
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
    // Ordenar por tamano de archivo
    public void RadixSort(){
        long max = GetMax(SearchFiles);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(SearchFiles, exp);
        }
    }
    private void countSort(ArrayList<File> files, int exp) {
        int n = files.size();
        ArrayList<File> output = new ArrayList<>(n);
        int[] count = new int[10];
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            int digit = (int) ((files.get(i).length() / exp) % 10);
            count[digit]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int digit = (int) ((files.get(i).length() / exp) % 10);
            output.set(count[digit] - 1, files.get(i));
            count[digit]--;
        }
        for (int i = 0; i < n; i++) {
            files.set(i, output.get(i));
        }
    }

    private long GetMax(ArrayList<File> files){
        long max = files.get(0).length();
        for (int i = 1; i < files.size(); i++) {
            if (files.get(i).length() > max) {
                max = files.get(i).length();
            }
        }
        return max;
    }

    // Actualizar el list model
    public void RefreshModel() {
        searchResultsModel.clear();
        for (File file : SearchFiles) {
            String name = file.getName();
            searchResultsModel.addElement(name);
        }
    }
}
