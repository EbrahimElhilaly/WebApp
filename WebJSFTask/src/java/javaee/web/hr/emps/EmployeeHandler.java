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
import javaee.HR.hibernate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
    

   
   public static void main(String[] args) {
        
       System.out.println(getAllEmployees().size());
      
       
        
    }    
    
}
