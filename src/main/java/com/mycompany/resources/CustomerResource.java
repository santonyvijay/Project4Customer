/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resources;

import com.mycompany.project4customer.CustomerManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author vidhya
 */
@Path("/customer")
public class CustomerResource {

    @GET
    @Produces("text/plain")
    public String getHello() {
        return "Hello Customers!";
    }

    @Path("/allcustomer")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomers() {
        CustomerManager cm = new CustomerManager();
        String customerList = cm.getCustomers().toString();
        return customerList;
    }

    @Path("/savecust")
    @POST
    public String createCustomers(@FormParam("inputCustName") String inputCustName,
            @FormParam("inputCustAddress") String inputCustAddress, @FormParam("inputGender") String inputGender) {
        if (!"".equals(inputCustName)) {
            CustomerManager cm = new CustomerManager();
            cm.insertCustomer(inputCustName, inputCustAddress, inputGender);

            // String customerList = cm.getCustomers().toString();
            return inputCustName + " has been Created";
        }
        return "Please Enter Customer Name";
    }

    @PUT
    @Path("/updatecust")
    public String upDateCustomers(@FormParam("inputCustName") String inputCustName,
            @FormParam("inputCustAddress") String inputCustAddress, @FormParam("inputGender") String inputGender) {
        CustomerManager cm = new CustomerManager();
        cm.updateCustomer(inputCustName, inputCustAddress, inputGender);
        //String customerList = cm.getCustomers().toString();
        return inputCustName + " has been Modified!";
    }

    @DELETE
    @Path("/deletecust")
    public String deleteCustomers(@FormParam("deleteCustName") String deleteCustName) {
        CustomerManager cm = new CustomerManager();
     
            cm.DeleteCustomer(deleteCustName);
       

        return deleteCustName + " has been Deleted!";
    }

}
