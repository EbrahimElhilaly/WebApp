/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.web.hr.emps;

import javaee.web.hr.entity.Employee;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaee.webstore.hibernate.util.HibernateUtil;
import javaee.web.hr.depts.DepartmentHandler;
import javaee.webstore.jdbc.dbconnection.DBConnectionFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Administrator
 */
public class EmployeeHandler {
    
     public static Session openSession(){
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();
        return session;
    
    }
    
    public static void closeSession(Session session) {
        session.close();
     
    }
    
    public static int insertEmployee(Employee emp){
    
        Session session = openSession();
        session.beginTransaction();
        
        session.save(emp);
        
        session.getTransaction().commit();

        closeSession(session);
        
        return 1;
        
    }
    
    public static int updateEmployee(Employee emp) {
        Session session = openSession();
        session.beginTransaction();

        session.update(emp);
        
        session.getTransaction().commit();
        closeSession(session);
        return 1;
    }
    
    public static Employee getEmployeeById(int empId) {
        Session session = openSession();

        Employee book = (Employee) session.get(Employee.class, empId);

        closeSession(session);

        return book;
    }
  
    public static int deleteEmployee(int employeeId){
        
        Session session = openSession();
        session.beginTransaction();

        session.delete(getEmployeeById(employeeId));
        
        session.getTransaction().commit();
        closeSession(session);
        
        return 1;
    }
    
    public static ArrayList<Employee> getAllEmployees() {
       
        org.hibernate.Session session = openSession();
        Criteria userCriteria = session.createCriteria(Employee.class);

        ArrayList<Employee> usersList = (ArrayList<Employee>) userCriteria.list();

        closeSession(session);
        return usersList;
    }
    
    //--------------------------------------------------------------------------
    
    
//    public static ArrayList<Employee> getAllEmployeesJdbc() throws IOException {
//
//        Connection conn = DBConnectionFactory.getConnection();
//
//        ArrayList<Employee> empsList = new ArrayList();
//        String sql = "SELECT BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, "
//                + " BOOK_DESCRIPTION, BOOK_ISBN, BOOK_IMAGE, BOOK_PRICE,"
//                + " BOOK_PUBLISH_DATE, LAST_UPDATE_TIME, CATEGORY_ID"
//                + " FROM BOOK"
//                + " ORDER BY BOOK_ID ASC";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                int bookId = rs.getInt("BOOK_ID");
//                String bookTitle = rs.getString("BOOK_TITLE");
//                String bookAuthor = rs.getString("BOOK_AUTHOR");
//                String bookDescription = rs.getString("BOOK_DESCRIPTION");
//                String bookISBN = rs.getString("BOOK_ISBN");
//                
//                
//                InputStream in = rs.getBinaryStream("BOOK_IMAGE");
//                 byte[] bookImage = new byte[0];
//              if(in != null){  
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                int next = in.read();
//                while (next > -1) {
//                    bos.write(next);
//                    next = in.read();
//                }
//                bos.flush();
//                bookImage = bos.toByteArray();
//                    System.out.println("boook image length : "+ bookImage.length);
//                bos.close();
//              }
//                
//                double bookPrice = rs.getDouble("BOOK_PRICE");
//                Date bookPublishDate = rs.getDate("BOOK_PUBLISH_DATE");
//                Date bookLastUpdateTime = rs.getDate("LAST_UPDATE_TIME");
//                int bookCategoryId = rs.getInt("CATEGORY_ID");
//
//                Book book = new Book(bookId, bookTitle, bookAuthor, bookDescription, bookImage, bookISBN, bookPrice, bookPublishDate, bookLastUpdateTime, DepartmentHandler.getCategoryById(bookCategoryId));
//                booksList.add(book);
//            }
//
//            pstmt.close();
//            //  conn.close();
//
//        } catch (SQLException ex) {
//        }
//
//        return booksList;
//    }
//
//   public static Book getBookByIdJdbc(int bookId) throws IOException {
//
//     Connection conn = DBConnectionFactory.getConnection();
//
//      Book book = null;
//        String sql = "SELECT BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, "
//                + " BOOK_DESCRIPTION, BOOK_ISBN, BOOK_IMAGE, BOOK_PRICE,"
//                + " BOOK_PUBLISH_DATE, LAST_UPDATE_TIME, CATEGORY_ID"
//                + " FROM BOOK"
//                + " WHERE BOOK_ID = ?";
//        
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//              pstmt.setInt(1, bookId);
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//               
//                String bookTitle = rs.getString("BOOK_TITLE");
//                String bookAuthor = rs.getString("BOOK_AUTHOR");
//                String bookDescription = rs.getString("BOOK_DESCRIPTION");
//                String bookISBN = rs.getString("BOOK_ISBN");
//                
//                
//                InputStream in = rs.getBinaryStream("BOOK_IMAGE");
//                 byte[] bookImage = new byte[0];
//              if(in != null){  
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                int next = in.read();*
//                while (next > -1) {
//                    bos.write(next);
//                    next = in.read();
//                }
//                bos.flush();
//                bookImage = bos.toByteArray();
//                    System.out.println("boook image length : "+ bookImage.length);
//                bos.close();
//              }
//                
//                double bookPrice = rs.getDouble("BOOK_PRICE");
//                Date bookPublishDate = rs.getDate("BOOK_PUBLISH_DATE");
//                Date bookLastUpdateTime = rs.getDate("LAST_UPDATE_TIME");
//                int bookCategoryId = rs.getInt("CATEGORY_ID");
//                    System.out.println("Book description : "+bookDescription);
//                 book = new Book(bookId, bookTitle, bookAuthor, bookDescription, bookImage, bookISBN, bookPrice, bookPublishDate, bookLastUpdateTime, DepartmentHandler.getCategoryById(bookCategoryId));
//               
//            }
//
//				pstmt.close();
//            //  conn.close();
//
//        } catch (SQLException ex) {
//        }
//
//        return book;
//    }
//    
   //-----------TEST------------------------------------------------------------
   
   public static void main(String[] args) {
        
       System.out.println(getAllEmployees().size());
      
       
        
    }    
    
}
