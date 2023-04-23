/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lenovo
 */
public abstract class AbstractDao {
        public static Connection connect() throws Exception, SQLException{
       //  Class.forName("com.mysql.jdbc.Driver");
         
         String url ="jdbc:mysql://localhost:3306/resume";
         String username ="root";
         String password = "15466451";
         Connection c =DriverManager.getConnection(url,username,password);
         return c;
    
    }
     private static EntityManagerFactory emf = null;
     
     public EntityManager em(){
     if(emf==null){
     emf = Persistence.createEntityManagerFactory("reumeappPU");
     }
     EntityManager entitymanager = emf.createEntityManager();
     return entitymanager;
     }   
     public void closeEmf(){
         emf.close();
         
     }
     
}
