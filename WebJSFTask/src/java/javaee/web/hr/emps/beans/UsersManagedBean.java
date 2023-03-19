/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.web.hr.emps.beans;

import java.util.ArrayList;
import javaee.web.hr.depts.services.UserServices;
import javaee.web.hr.entity.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Administrator
 */

@ManagedBean(name = "UsersManagedBean")
@RequestScoped
public class UsersManagedBean {
  
    private ArrayList<Employee> usersList = new ArrayList();
    private Employee user = new Employee();

    public UsersManagedBean() {

        usersList = UserServices.getAllUsers();
    }

    public ArrayList<Employee> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<Employee> usersList) {
        this.usersList = usersList;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }
  
    public void deleteUser(int userId){
         
         UserServices.deleteUser(userId);
    }

    public void editUser(int userId){

        UserServices.getUserById(userId);
    }

    public void saveUser(){

        if(user.getEmployeeId() != 0){

            UserServices.updateUser(user);

        }else {

            UserServices.insertUser(user);
        }
        usersList = UserServices.getAllUsers();
    }
    
    
    
}
