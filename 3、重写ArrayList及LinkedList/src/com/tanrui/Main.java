package com.tanrui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Main {
    public static void main(String[] args){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            String readLine = bufferedReader.readLine();
            String []nums = readLine.split(" ");

//            测试ArrayList
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

//            测试LinkedList
//            LinkedList<Integer> linkedList = new LinkedList<>();
//            for (String num : nums)
//                linkedList.add(Integer.parseInt(num));
//            System.out.println(linkedList.toString());
//            linkedList.remove(2);
//            System.out.println(linkedList.toString());
//            Iterator iterator = linkedList.iterator();
//            for (int i = 0; i < linkedList.size(); i++) {
//                Integer data = (Integer) iterator.next();
//                iterator.remove();
//                System.out.print(data.toString() + ' ');
//            }
//            System.out.println();
//            System.out.println(linkedList.toString());

//            java自带排序器，需自行制定比较方法
//            java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
//            for (String num : nums){
//                list.add(Integer.parseInt(num));
//            }
//            Collections.sort(list, new Comparator<Integer>() {
//                @Override
//                public int compare(Integer o1, Integer o2) {
//                    if (o1 > o2)
//                        return 1;
//                    else if(o1 < o2)
//                        return -1;
//                    else
//                        return 0;
//                }
//            });
//            System.out.println(list.toArray());

//          AVL二叉搜索树
            AvlTree<Integer> avlTree = new AvlTree<>();
            for (String num : nums){
                avlTree.insert(Integer.parseInt(num));
            }
            System.out.println(avlTree.toString());
            avlTree.insert(5);
            System.out.println(avlTree.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
