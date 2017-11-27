package com.tanrui;

import javafx.util.Pair;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WordsSystem system = new WordsSystem();
       for (int i = 1; i < 4; i++){
           system.openFile("infile" + i + ".txt");
       }
       for(Pair<String, WordsSystem.WordFile> file : system.getSystem()){
            System.out.println(file.getKey());
            System.out.println(file.getValue().locateWordInFile("a").toString());
            System.out.println(file.toString());
       }
    }
}
