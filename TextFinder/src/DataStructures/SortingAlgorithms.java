package DataStructures;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class SortingAlgorithms {
    private DefaultListModel<String> searchResultsModel;
    private ArrayList<File> SearchFiles;

    public SortingAlgorithms(ArrayList<File> Files, DefaultListModel<String> Model){
        this.searchResultsModel = Model;
        this.SearchFiles = Files;
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
    public void RadixSort1(){

    }
    public void RadixSort2(){

    }
}
