/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.company.main;

import com.company.dao.inter.UserDaoInter;


/**
 *
 * @author Lenovo
 */
public class Main {

    public static void main(String[] args) throws Exception {

   UserDaoInter dao = Context.instanceUserDao();
   dao.removeUser(18);
 
}
    
}
