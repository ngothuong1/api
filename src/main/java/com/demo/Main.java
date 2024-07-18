package com.demo;
import java.sql.*;
import java.util.Scanner;

import com.demo.dao.studentdao;
import com.demo.entity.student;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
      studentdao dao = new studentdao();
        Scanner sc = new Scanner(System.in);
        int choose = 0;
        while (true) {
            System.out.println("\n\n\n\n1.List Student");
            System.out.println("2.Add Student");
            System.out.println("3.Update Student");
            System.out.println("4.Delete Student");
            System.out.println("5.Exit\n\n");
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    dao.findAll().forEach(student -> System.out.println(student));
                    break;
                case 2:
                    student st = new student();
                    System.out.println("Enter Student ID");
                    int id = sc.nextInt();
                    System.out.println("Enter Student Name");
                    String name = sc.next();
                    System.out.println("Enter Student Age");
                    String age = sc.next();
                    if(dao.add(st)){
                        System.out.println("Student Added Successfully");
                    }else {
                        System.out.println("Student Not Added Successfully");
                    }
                    break;
                case 3:
                    System.out.println("Enter Student ID");
                    student s = dao.findById(Integer.parseInt(sc.nextLine()));
                    if(s==null){
                        System.out.println("Student Not Found");
                        break;
                    }
                    while (true) {
                        System.out.println(s);
                        System.out.println("1.Update Student Name");
                        System.out.println("2.Update Student Age");
                        System.out.println("3. Save");
                        int choice = sc.nextInt();
                        if (choice == 1) {
                            System.out.println("Enter Student Name:");
                            name = sc.nextLine();
                        }
                        if (choice == 2) {
                            System.out.println("Enter Student Age:");
                            age = sc.nextLine();
                        }
                        if (choice == 3) {
                            dao.update(s);
                            System.out.println("Save");
                        }
                    }
                case 4:
                    System.out.println("Enter Student ID");
                    if(dao.delete(Integer.parseInt(sc.nextLine()))){
                        System.out.println("Student Deleted Successfully");
                    }else {
                        System.out.println("Student Not Deleted Successfully");
                    }
                    break;
            }
        }
    }
}