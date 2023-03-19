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
import javaee.HR.hibernate.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
    

    
    
    public static void main(String[] args) {
        System.out.println(getAllDepartment().size());
    }
}
