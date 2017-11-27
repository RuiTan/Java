package com.tanrui;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class StudentList {
    public static class Students {
        public Students(){
            exam_num = 0;
            name = null;
            sex = false;
            age = 0;
            type = null;
            next = null;
        }
        public Students(int exam_num, String name, boolean sex, int age, String type) {
            this.exam_num = exam_num;
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.type = type;
            this.next = null;
        }
        public Students(Students students){
            this.exam_num = students.exam_num;
            this.name = students.name;
            this.sex = students.sex;
            this.age = students.age;
            this.type = students.type;
            this.next = next;
        }
        private Students next;
        private int exam_num;
        private String name;
        private boolean sex;
        private int age;
        private String type;
        public int getExam_num() {
            return exam_num;
        }
        public void setExam_num(int exam_num) {
            this.exam_num = exam_num;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSex() {
            return sex?"male":"female";
        }
        public void setSex(boolean sex) {
            this.sex = sex;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String toString(){
            return "考号：" + exam_num + " 姓名：" + name + " 性别：" + getSex() + " 年龄：" + age + " 报考类型：" + type;
        }
    };
    private LinkedList<Students> studentList;
    private Students first;
    public StudentList(){
        this.studentList = new LinkedList<Students>();
        first = null;
    }
    public StudentList(LinkedList studentList) {
        this.studentList = (LinkedList<Students>) studentList.clone();
        this.first = this.studentList.getFirst();
        Students temp = first;
        for (int i = 1; i < this.studentList.size(); i++){
            temp.next = this.studentList.get(i);
            temp = temp.next;
        }
    }
    public int getListSize(){return studentList.size();}
    public void insertStudent(BufferedReader fileReader) throws IOException {
        String studentInfo = fileReader.readLine();
        while (studentInfo != null){
            String []infos = studentInfo.split(" ");
            int num = Integer.parseInt(infos[0]);
            String name = infos[1];
            Boolean sex = infos[2].equals("male");
            int age = Integer.parseInt(infos[3]);
            String type = infos[4];
            Students students = new Students(num, name, sex, age, type) ;
            insertStudent(students);
            studentInfo = fileReader.readLine();
        }
    }
    public void insertStudent(Students students){
        if (isExist(students.exam_num)){
           System.out.printf("考号为%s的考生已经存在！%n", students.exam_num);
           return;
        }
        if (first == null){
            first = new Students(students.exam_num, students.name, students.sex, students.age, students.type);
            studentList.add(first);
        }else {
            Students temp = first;
            while (temp.next!=null){
                temp = temp.next;
            }
            temp.next = new Students(students.exam_num, students.name, students.sex, students.age, students.type);
            studentList.add(temp.next);
        }
    }
    public boolean isExist(int num){
        Students temp = first;
        while (temp != null){
            if (temp.exam_num == num){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    public void printList(){
        Students temp = first;
        while (temp != null){
            System.out.printf("考号：%d 姓名：%s 性别：%s 年龄：%d 报考类型：%s%n", temp.exam_num, temp.name, temp.sex?"male":"female", temp.age, temp.type);
            temp = temp.next;
        }
    }
    public void printListWithoutFirst(){
        studentList.forEach(temp->{
            System.out.printf("考号：%d 姓名：%s 性别：%s 年龄：%d 报考类型：%s%n", temp.exam_num, temp.name, temp.sex?"male":"female", temp.age, temp.type);
        });
    }
    public Students searchByNum(int num){
        Students temp = first;
        while (temp != null){
            if (temp.getExam_num() == num){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    public LinkedList<Students> searchByName(String name){
        LinkedList<Students> students = new LinkedList<>();
        int index = 0;
        Students temp = first;
        while (temp != null){
            if (temp.getName().equals(name)){
                students.add(new Students(temp));
            }
            temp = temp.next;
        }
        return students;
    }
    public LinkedList<Students> searchBySex(boolean sex){
        LinkedList<Students> students = new LinkedList<>();
        Students temp = first;
        while (temp != null){
            if (temp.sex == sex){
                students.add(new Students(temp));
            }
            temp = temp.next;
        }
        return students;
    }

    public LinkedList<Students> searchByAge(int age){
        LinkedList<Students> students = new LinkedList<>();
        Students temp = first;
        while (temp != null){
            if (temp.age == age){
                students.add(new Students(temp));
            }
            temp = temp.next;
        }
        return students;
    }
    public LinkedList<Students> searchByType(String type){
        LinkedList<Students> students = new LinkedList<>();
        Students temp = first;
        while (temp!=null){
            if (temp.type.equals(type)){
                students.add(new Students(temp));
            }
            temp = temp.next;
        }
        return students;
    }

    public boolean deleteByNum(int num){
        if (!isExist(num)) return false;
        else    {
            Students temp = first.next, tempPre = first;
            int index = 0;
            if (tempPre.exam_num == num){
                studentList.remove(0);
                first = temp;
                return true;
            }
            while (temp != null){
                if (temp.exam_num == num){
                    tempPre.next = temp.next;
                    break;
                }
                tempPre = temp;
                temp = temp.next;
                index++;
            }
            studentList.remove(++index);
            return true;
        }
    }
}
