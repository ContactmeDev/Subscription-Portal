package com.cme;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.sql.rowset.CachedRowSet;
import org.primefaces.context.RequestContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
@RequestScoped
@ManagedBean(name="tableBean")
public class TableBean {
    

public class AddService
{
    public String getService() {
return service;
}
public void setService ( String service)
{
   this.service = service;
}

public String getSubService() {
return subService;
}
public void setSubService( String subService)
{
   this.subService = subService;
}
 
    String subService;
    String service;
  public AddService(String service, String subService) {
              this.service=service;
              this.subService=subService;
        }
}
//   private ArrayList<AddService> adsrvs;
    private List addService = new ArrayList(); 
    RequestContext context = RequestContext.getCurrentInstance();
FacesMessage message = null;
PreparedStatement ps =null;
Connection con= null;

int i =0;
 ResultSet r1 =null;
//    public ArrayList<AddService> bringRec() {
  public String addAll(){ 
String retValue = "index";
     try
     {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@209.126.76.237:1521:CME" , "CPSPROD" ,"CPS4CME");

while (r1.next())
{
           String sql = "INSERT INTO SUBSCRIPTION_PORTAL(SERVICE_ID , SUBSERVICE_ID)VALUES(?,?)";
           ps=con.prepareStatement(sql);
           i=ps.executeUpdate()     ;
//            ps.setString(2,username);
//            ps.setString(1,source);
//            ps.setString(6,subservice);
//            ps.setString(4,number);
//            ps.setString(3,email);
//            ps.setBoolean(5,cource1);
     AddService addsrv = new AddService();
             addsrv.setService(r1.getString(3));
             addsrv.setSubService(r1.getString(2));
addService.add(addsrv);
            
        
}
     }
      
     catch (Exception e)
                {
             e.printStackTrace();}
    
      finally{
                try{
                con.close();
                }
        
                   catch(Exception e)
                {
             e.printStackTrace();              }
                }
   return retValue;
 
}
}
