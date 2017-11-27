package com.tanrui;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;

public class WordsSystem {
    public class Word{
        public Word(){
            word = null;
            count = 0;
        };
        public Word(String word){
            this.word = word;
            this.count = 1;
        }
        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
        public String getWord() {
            return word;
        }
        public void setWord(String word) {
            this.word = word;
        }
        public int getCount() {
            return count;
        }
        public void setCount(int count) {
            this.count = count;
        }
        private String word;
        private int count;
    }
    public class WordFile{
        private String fileName;
        private BufferedReader fileBuffer;
        public WordFile(String fileName){
            this.fileName = fileName;
        }
        public String toString(){
            StringBuilder toString = null;
            try {
                fileBuffer = new BufferedReader(new FileReader(fileName));
                try {
                    String line = fileBuffer.readLine();
                    toString = new StringBuilder(line);
                    while (line != null){
                        toString.append(line);
                        line = fileBuffer.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return toString.toString();
        }
        public int countWordInFile(String word){
            int count = 0;
            try {
                fileBuffer = new BufferedReader(new FileReader(fileName));
                String line = fileBuffer.readLine();
                while (line != null){
                    count += countWordInLine(word, line);
                    line = fileBuffer.readLine();
                }
                fileBuffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return count;
        }
        public ArrayList<Pair<Integer, ArrayList<Integer>>> locateWordInFile(String word){
            ArrayList<Pair<Integer, ArrayList<Integer>>> coordinate = new ArrayList<>();
            int row = 0;
            String line = null;
            try {
                fileBuffer = new BufferedReader(new FileReader(fileName));
                line = fileBuffer.readLine();
                while (line != null){
                    coordinate.add(new Pair<>(row++, locateWordInLine(word, line)));
                    line = fileBuffer.readLine();
                }
                fileBuffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return coordinate;
        }
        private ArrayList<Integer> locateWordInLine(String word, String line){
            ArrayList<Integer> locate = new ArrayList<>();
            int index = line.indexOf(word, 0);
            while (index != -1){
                locate.add(index);
                index = line.indexOf(word, index+1);
            }
            return locate;
        }
        private int countWordInLine(String word, String line){
            int count = 0, index = line.indexOf(word, 0);
            while (index != -1){
                count++;
                index = line.indexOf(word, index+1);
            }
            return count;
        }
    }
    private ArrayList<Pair<String, WordFile>> system;
    public ArrayList<Pair<String, WordFile>> getSystem() {
        return system;
    }
    public int systemSize(){return system.size();}
    public WordsSystem(){
        system = new ArrayList<>();
    };
    public WordFile fileExist(String fileName){
        for (Pair<String, WordFile> file:system) {
            if(file.getKey().equals(fileName)){
                return file.getValue();
            }
        }
        return null;
    }
    public WordFile closeFile(String fileName){
        int index = 0;
        for (Pair<String, WordFile> existFile:system) {
            if (existFile.getKey().equals(fileName)){
                try {
                    existFile.getValue().fileBuffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            index++;
        }
        if (index == system.size()){
            return null;
        }else {
            system.remove(index);
        }
        return system.get(index).getValue();
    }
    public void openFile(String fileName) {
        WordFile existFile = fileExist(fileName);
        if (existFile == null){
            WordFile file = new WordFile(fileName);
            system.add(new Pair<>(fileName, file));
        }
    }
}
