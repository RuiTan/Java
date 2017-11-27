package com.tanrui;


import java.io.*;
import java.util.Scanner;
import java.util.zip.Inflater;

import static java.lang.System.in;

public class Main {
    public static void main(String []args) throws IOException {
        System.out.println("当前考试系统情况如下：");
        StudentList studentList = new StudentList();
        String file_name = "student.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file_name));
        studentList.insertStudent(bufferedReader);
        bufferedReader.close();
        studentList.printList();
        int operate;
        Scanner scanner = new Scanner(in);
        while (true){
            System.out.println("请输入你要进行的操作（1为插入，2为删除，3为查找，4为修改，5为统计，6为输出当前系统，0为退出）：");
            operate = scanner.nextInt();
            switch (operate) {
                case 1:{
                    int num, age;
                    String name, type;
                    boolean sex;
                    System.out.println("请输入你要插入的考生的信息：");
                    String line;

                    byte []b = new byte[1024];
                    int n = in.read(b);//返回的是读取的字节数量
                    line = new String(b, 0, n-1);//从控制台读入一行的方法，先创建字节数组使其足够大满足需求，

                    String []infos = line.split(" ");
                    num = Integer.parseInt(infos[0]);
                    name = infos[1];
                    sex = infos[2]=="male";
                    age = Integer.parseInt(infos[3]);
                    type = infos[4];
                    studentList.insertStudent(new StudentList.Students(num, name, sex, age, type));
                    break;
                }
                case 2:{
                    int num;
                    System.out.println("请输入你要删除的考生号码：");
                    num = scanner.nextInt();
                    if (studentList.deleteByNum(num)){
                        System.out.println("删除成功！");
                    }else {
                        System.out.println("删除失败！");
                    }
                    break;
                }
                case 3:{
                    int num;
                    System.out.println("请输入你要查找的考生的号码：");
                    num = scanner.nextInt();
                    StudentList.Students students = studentList.searchByNum(num);
                    if(students == null){
                        System.out.println("查找失败！");
                    }else{
                        System.out.println(students.toString());
                        System.out.println("查找成功！");
                    }
                    break;
                }
                case 4:{
                    int num;
                    System.out.println("请输入你要修改信息的考生的号码：");
                    num = scanner.nextInt();
                    StudentList.Students students = studentList.searchByNum(num);
                    if (students == null){
                        System.out.println("系统中没有该考生的信息！");
                    }else{
                        System.out.println("请输入该考生修改后的信息（姓名、性别、年纪、报考专业）：");
                        String name, type;
                        int age;
                        boolean sex;
                        String line;
                        byte []b = new byte[1024];
                        int n = in.read(b);
                        line = new String(b,0,n-1);
                        String []infos = line.split(" ");
                        name = infos[0];
                        sex = infos[1]=="male";
                        age = Integer.parseInt(infos[2]);
                        type = infos[3];
                        studentList.deleteByNum(students.getExam_num());
                        studentList.insertStudent(new StudentList.Students(students.getExam_num(), name, sex, age, type));
                        break;
                    }
                }
                case 5:{
                    System.out.println("请输入要统计的信息（1为姓名，2为性别，3为年龄，4为报考类型）");
                    int info;
                    info = scanner.nextInt();
                    switch (info){
                        case 1:{
                            System.out.println("请输入要统计的姓名：");
                            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));
                            String name = bufferedReader1.readLine();
                            StudentList studentList1 = new StudentList(studentList.searchByName(name));
                            if (studentList1.getListSize() == 0){
                                System.out.println("系统中没有该类考生！");
                                break;
                            }
                            System.out.println("姓名为"+name+"的考生有以下：");
                            studentList1.printList();
                            System.out.println();
//                            bufferedReader1.close();
                            break;
                        }
                        case 2:{
                            System.out.println("请输入要统计的性别(1为男性，0为女性)：");
                            boolean sex = scanner.nextInt() == 1;
                            StudentList studentList1 = new StudentList(studentList.searchBySex(sex));
                            if (studentList1.getListSize() == 0){
                                System.out.println("系统中没有该类考生！");
                                break;
                            }
                            System.out.println("性别为"+(sex?"male":"female")+"的考生有以下：");
                            studentList1.printList();
                            System.out.println();
                            break;
                        }
                        case 3:{
                            System.out.println("请输入要统计的年龄：");
                            int age = scanner.nextInt();
                            StudentList studentList1 = new StudentList(studentList.searchByAge(age));
                            if (studentList1.getListSize() == 0){
                                System.out.println("系统中没有该类考生！");
                                break;
                            }
                            System.out.println("年纪为"+age+"的考生有以下：");
                            studentList1.printList();
                            System.out.println();
                            break;
                        }
                        case 4:{
                            System.out.println("请输入要统计的报考类型：");
                            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));
                            String type = bufferedReader1.readLine();
                            StudentList studentList1 = new StudentList(studentList.searchByType(type));
                            if (studentList1.getListSize() == 0){
                                System.out.println("系统中没有该类考生！");
                                break;
                            }
                            System.out.println("报考类型为"+type+"的考生有以下：");
                            studentList1.printList();
                            System.out.println();
                            break;
                        }
                        default:{
                            break;
                        }
                    }
                    break;
                }
                case 6:{
                    studentList.printListWithoutFirst();
                    break;
                }
                case 0:{
                    return;
                }
                default:{
                    continue;
                }
            }
        }
    }
}
