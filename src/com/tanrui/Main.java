package com.tanrui;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String []args){
        StudentList studentList = new StudentList();
        studentList.insertStudent(new StudentList.Students(1,"tanrui",true,19,"SSE"));
        studentList.insertStudent(new StudentList.Students(2,"liyang",true,20,"Car"));
        studentList.insertStudent(new StudentList.Students(2,"liyang",true,20,"Car"));
        System.out.println(studentList.isExist(1));
        studentList.printList();
        System.out.println(studentList.searchByNum(1).toString());
        System.out.println(studentList.searchByName("liyang").toString());
    }
}
