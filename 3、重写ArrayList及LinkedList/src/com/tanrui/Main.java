package com.tanrui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            String readLine = bufferedReader.readLine();
            String []nums = readLine.split(" ");
//            ArrayList<Integer> arrayList = new ArrayList<>();
//            for (String num : nums){
//                arrayList.add(Integer.parseInt(num));
//            }
//            System.out.println(arrayList.toString());
//            arrayList.remove(4);
//            System.out.println(arrayList.toString());
//            Iterator iterator = arrayList.iterator();
//            int flag = arrayList.size();
//            for (int i = 0; i < flag; i++){
//                System.out.print(iterator.next());
//                System.out.print(iterator.hasNext());
//                iterator.remove();
//            }
//            System.out.println(arrayList.toString());
            LinkedList<Integer> linkedList = new LinkedList<>();
            for (String num : nums)
                linkedList.add(Integer.parseInt(num));
            System.out.println(linkedList.toString());
            linkedList.remove(2);
            System.out.println(linkedList.toString());
            Iterator iterator = linkedList.iterator();
            for (int i = 0; i < linkedList.size(); i++) {
                Integer data = (Integer) iterator.next();
                iterator.remove();
                System.out.print(data.toString() + ' ');
            }
            System.out.println();
            System.out.println(linkedList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
