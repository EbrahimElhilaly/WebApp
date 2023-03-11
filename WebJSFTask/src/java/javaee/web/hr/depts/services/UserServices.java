/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.web.hr.depts.services;

import java.io.IOException;
import java.util.ArrayList;
import javaee.web.hr.emps.EmployeeHandler;
import javaee.web.hr.entity.Employee;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;

/**
 *
 * @author Administrator
 */
public class UserServices {
    
    public static int insertUser(Employee user){
    
        EmployeeHandler.insertEmployee(user);
        
        return 1;
        
    }
    
    public static int updateUser(Employee user) {
      
        EmployeeHandler.updateEmployee(user);
        
        return 1;
    }
    
    public static Employee getUserById(int userId) {
      
           return EmployeeHandler.getEmployeeById(userId);

    }
  
    public static int deleteUser(int userId){
        
            EmployeeHandler.deleteEmployee(userId);
        return 1;
    }
    
    public static ArrayList<Employee> getAllUsers() {
       
          
        
        return EmployeeHandler.getAllEmployees();
        
    }
    
    
}
