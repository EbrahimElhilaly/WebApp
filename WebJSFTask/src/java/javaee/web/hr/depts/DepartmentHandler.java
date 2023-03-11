/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.web.hr.depts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaee.web.hr.entity.Department;
import javaee.webstore.hibernate.util.HibernateUtil;
import javaee.webstore.jdbc.dbconnection.DBConnectionFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Administrator
 */
public class DepartmentHandler {
     
     public static Session openSession(){
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();
        return session;
    
    }
    
    public static void closeSession(Session session) {
        session.close();
     
    }
    
    public static int insertDepartment(Department dept){
    
        Session session = openSession();
        session.beginTransaction();
        
        session.save(dept);
        
        session.getTransaction().commit();

        closeSession(session);
        
        return 1;
        
    }
    
    public static int updateDepartment(Department dept) {
        Session session = openSession();
        session.beginTransaction();

        session.update(dept);
        
        session.getTransaction().commit();
        closeSession(session);
        return 1;
    }
    
    public static Department getDepartmentById(int deptId) {
        Session session = openSession();

        Department dept = (Department) session.get(Department.class, deptId);

        closeSession(session);

        return dept;
    }
  
    public static int deleteDepartment(int deptId){
        
        Session session = openSession();
        session.beginTransaction();

        session.delete(getDepartmentById(deptId));
        
        session.getTransaction().commit();
        closeSession(session);
        
        return 1;
    }
    
    public static ArrayList<Department> getAllDepartment() {
       
        Session session = openSession();
        Criteria userCriteria = session.createCriteria(Department.class);

        ArrayList<Department> list = (ArrayList<Department>) userCriteria.list();

        closeSession(session);
        return list;
    }
    
    
//------------------------------------------------------------------------------
//    public static ArrayList<Category> getAllCategoriesJdbc(){
//
//        Connection conn = DBConnectionFactory.getConnection();
//
//        ArrayList<Category> categoriesList = new ArrayList();
//        String sql = "SELECT CATEGORY_ID, CATEGORY_NAME"
//                + " FROM CATEGORY"
//                + " ORDER BY CATEGORY_ID ASC";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                int categoryId = rs.getInt("CATEGORY_ID");
//                String categoryName = rs.getString("CATEGORY_NAME");
//
//                Category category = new Category(categoryId, categoryName);
//                categoriesList.add(category);
//            }
//
//            pstmt.close();
//            //  conn.close();
//
//        } catch (SQLException ex) {
//            
//        }
//
//        return categoriesList;
//    }
//
//    public static int createNewCategoryJdbc(Category category) {
//        Connection conn = DBConnectionFactory.getConnection();
//
//        String sql = "INSERT INTO CATEGORY"
//                + " (CATEGORY_NAME)"
//                + " VALUES(?)";
//
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, category.getCategoryName());
//
//            pstmt.executeUpdate();
//            conn.commit();
//            pstmt.close();
//            //  conn.close();
//
//        } catch (SQLException ex) {
//            return -1;
//        }
//
//        return 1;
//    }
//
//    public static int updateCategoryJdbc(Category category) {
//        Connection conn = DBConnectionFactory.getConnection();
//
//        String sql = "UPDATE CATEGORY"
//                + "  SET CATEGORY_NAME = ?"
//                + " WHERE CATEGORY_ID = ?";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, category.getCategoryName());
//            pstmt.setInt(2, category.getCategoryId());
//
//            pstmt.executeUpdate();
//            conn.commit();
//            pstmt.close();
//            //  conn.close();
//
//        } catch (SQLException ex) {
//            return -1;
//        }
//
//        return 1;
//    }
//
//    public static Category getCategoryByIdJdbc(int categoryId) {
//        Connection conn = DBConnectionFactory.getConnection();
//
//        Category category = null;
//        String sql = "SELECT CATEGORY_ID, CATEGORY_NAME"
//                + " FROM CATEGORY"
//                + " WHERE CATEGORY_ID = ?";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, categoryId);
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//
//                String categoryName = rs.getString("CATEGORY_NAME");
//                category = new Category(categoryId, categoryName);
//
//            }
//
//            pstmt.close();
//            //  conn.close();
//
//        } catch (SQLException ex) {
//        }
//
//        return category;
//
//    }
//
//    public static int deleteCategoryJdbc(int categoryId) {
//        Connection conn = DBConnectionFactory.getConnection();
//
//        String sql = "DELETE CATEGORY"
//                + " WHERE CATEGORY_ID = ?";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, categoryId);
//
//            pstmt.executeUpdate();
//            conn.commit();
//            pstmt.close();
//            //  conn.close();
//
//        } catch (SQLException ex) {
//            return -1;
//        }
//
//        return 1;
//    }
    
    
    public static void main(String[] args) {
        System.out.println(getAllDepartment().size());
    }
}
