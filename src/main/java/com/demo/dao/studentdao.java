package com.demo.dao;

import com.demo.entity.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class studentdao {
    private static String URL="jdbc:mysql://localhost:3307/studentdb?user=root";

    private static String USER="root";

    private static String PASSWORD="123456";
    private static Connection connection=getConnection();

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public studentdao() {
    }

    /**
     * select
     * @return
     */
    public List<student> findAll(){
        List<student> list=new ArrayList<>();
        try {
            ResultSet resultSet =connection.createStatement().executeQuery("select * from student");
            while(resultSet.next()){
                student student=new student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                list.add(student);
            }
            resultSet.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * get student by id
     * @param id
     * @return result
     */
    public student findById(int id){
        for (student student : findAll()) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;

    }

    /**
     * add
     * @param student
     * @return
     */

    public boolean add(student student){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into student values(?,?,?)");
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setString(2,student.getName());
            preparedStatement.setInt(3,student.getAge());
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * update
     * @param student
     * @return
     */

    public boolean update(student student){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update student set name=?,age=? where id=?");
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2,student.getAge());
            preparedStatement.setInt(3,student.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * delete
     * @param id
     * @return
     */

    public boolean delete(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from student where id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        studentdao dao=new studentdao();
        /**
         * get student by id
         */
//        student student=dao.findById(1);
        /**
         * get all
         */

        for(student student:dao.findAll()){
            System.out.println(student);
        }

        /**
         * add
         */
//        student student=new student();
//        student.setId(3);
//        student.setName("Tu Anh");
//        student.setAge(20);
//        if(dao.add(student)){
//            System.out.println(student);
//        }else {
//            System.out.println("Add failed");
//        }

        /**
         * update
         */
        student ngothuong=dao.findById(1);
        if(ngothuong!=null){
            ngothuong.setAge(23);
        }
        if(dao.update(ngothuong)){
            System.out.println("Update success");
        }else {
            System.out.println("Update failed");
        }

        /**
         * delete
         */
//        if(dao.delete("2")){
//            System.out.println("Delete success");
//        }else {
//            System.out.println("Delete failed");
//        }
    }
}
