/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project4customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vidhya
 */
public class CustomerManager {

    private static String dbURL = "jdbc:derby://localhost:1527/CustDB;create=true";
    private static String tableName = "CUSTOMERS";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    List<Customer> customerList = new ArrayList();
    
   public List<Customer> getCustomers()
   {
            selectCustomers();
            return customerList;
   }
   
    public static void main(String[] args)
    {
        //createConnection();
        //insertCustomer("La", "1345 Berkeley", "male");
        //updateCustomer("La", "1111 BerKely", "Female");
        //DeleteCustomer("La");
    }
    
   private void selectCustomers()
    {
        try
        {
            createConnection();
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            while(results.next())
            {
                Customer c = new Customer();
                c.setCustName(results.getString(1));
                c.setAddress(results.getString(2));
                c.setGender(results.getString(3));
                customerList.add(c);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
   
    public void insertCustomer(String custName, String address, String gender)
    {
        try
        {
            createConnection();
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values ('" + custName + "','" + address +"','" + gender +"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    public void updateCustomer(String custName, String address, String gender)
    {
        try
        {
            createConnection();
            stmt = conn.createStatement();
            stmt.execute("update "+ tableName + " set CUSTADDRESS = '"+ address +"', GENDER = '"+ gender +"' where CUSTNAME = '" + custName +"'");
            
            stmt.close();
        }
        catch(SQLException sqlExcept)
        {
             sqlExcept.printStackTrace();
        }
    }
    
    public void DeleteCustomer(String custName)
    {
        try
        {
            createConnection();
            stmt = conn.createStatement();
            stmt.execute("delete  from  "+ tableName + " where CUSTNAME = '" + custName +"'");
            
            stmt.close();
        }
        catch(SQLException sqlExcept)
        {
             sqlExcept.printStackTrace();
        }
    }
    
     private void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
}
