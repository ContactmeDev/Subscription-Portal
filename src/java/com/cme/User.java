package com.cme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
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

@ManagedBean(name="sgbean")

@SessionScoped

public class User 
{
RequestContext context = RequestContext.getCurrentInstance();
FacesMessage message = null;
PreparedStatement ps =null;
Connection con= null;
int i =0;
private String source;
private String service;
private String subservice;
private String email;
private String number;
private String username;
private Boolean cource1;
private String[] srv;
  private String serviceSelected;
private Map<String,Boolean> checkMap = new HashMap<String,Boolean>();

public Map<String, Boolean> getCheckMap() {
        return checkMap;
  }

public void addService(String service, String subService)
{
    
    
}

public void setCource1( Boolean cource1)
{
   this.cource1 = cource1;
}

public Boolean getCource1()
{
    return cource1;
}


public String parametersAction(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        source = params.get("source");
        service = params.get(" service ");
        subservice= params.get("subservice");
        return "result";
    }

public String getSource()   
{
    return source;  
}
public void setSource( String source)
{
   this.source = source;
}
public String getService()
{
    return service;
}
public void setService ( String service)
{
   this.service = service;
}

public String getSubservice()
{
    return subservice;
}
public void setSubservice( String subservice)
{
   this.subservice = subservice;
}

public String getEmail()
{
    return email;
}
public void setEmail( String  email)
{
   this. email =  email;
}

public String getNumber()
{
    return number;
}
public void setNumber( String  number)
{
   this. number =  number;
}

public String getUsername()
{
    return username;
}
public void setUsername( String  username)
{
   this. username =  username;
}

public void validateMobileNo(FacesContext context, UIComponent comp,
			Object value) {

		System.out.println("inside validate method");
		String number= (String) value;
                if (number.length() < 10) {
	        ((UIInput) comp).setValid(false);
                FacesMessage message = new FacesMessage(
		"اقل طول للرقم هو 10 ارقام");
		context.addMessage(comp.getClientId(context), message);}
	                                  }
public String add(){ 
String retValue = "output";
     try
     {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@209.126.76.237:1521:CME" , "CPSPROD" ,"CPS4CME");

        Statement  stmt = con.createStatement();
     
         boolean numberExists = false;

         PreparedStatement st = con.prepareStatement(" SELECT * FROM SUBSCRIPTION_PORTAL WHERE PHONE_NUMBER =?");
           st.setString(1,number);
         ResultSet r1=st.executeQuery();
         String numberCounter;
         if(r1.next()) 
          {
           numberCounter =  r1.getString("PHONE_NUMBER");
           if (numberCounter.equals(number))
           {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("عفوا...  انت مشترك مسبقا في هذه الخدمة"));
              numberExists = true;
           }
          }  
         if (!numberExists) {

           String sql = "INSERT INTO SUBSCRIPTION_PORTAL(SOURCE, NAME, E_MAIL, PHONE_NUMBER,SERVICE_ID , SUBSERVICE_ID)VALUES(?,?,?,?,?,?)";
           ps=con.prepareStatement(sql);
            ps.setString(2,username);
            ps.setString(1,source);
            ps.setString(5,service);
            ps.setString(6,subservice);
            ps.setString(4,number);
            ps.setString(3,email);
//            ps.setBoolean(5,cource1);
    
            i=ps.executeUpdate()     ;
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


    
public String getServiceName() {
        String srvName = "";
        try
        {
           Class.forName("oracle.jdbc.driver.OracleDriver");
           con = DriverManager.getConnection("jdbc:oracle:thin:@209.126.76.237:1521:CME" , "CPSPROD" ,"CPS4CME");
           Statement  stmt = con.createStatement();
          
       
           PreparedStatement st = con.prepareStatement(" SELECT * FROM SUBSERVICE_MAST WHERE SSRV_CODE =? AND SSRV_SRV_CODE =?");
            st.setString(1,subservice);
            st.setString(2,service);
            
            ResultSet r1=st.executeQuery();

            if(r1.next()) 
            {
                srvName = r1.getString("SSRV_NAME_AR");
            }
         }catch(Exception ex)
         {
             System.err.println(ex.getMessage());
         }
         finally{
                try{
                con.close();
                
                }
        
                   catch(Exception e)
                {
             e.printStackTrace();              }
                }
        
        if(srvName.equals(""))
            srvName = "الخدمة غير موجودة";
        
        return srvName;
        
    }
   
    

public   ResultSet getAll() {
  // String re = "output";
  CachedRowSet crs = null;
  ResultSet result = null;
  PreparedStatement st = null;
        try
        {
          Class.forName("oracle.jdbc.driver.OracleDriver");
          con = DriverManager.getConnection("jdbc:oracle:thin:@209.126.76.237:1521:CME" , "CPSPROD" ,"CPS4CME");
          Statement stmt;
          stmt = con.createStatement();
          st = con.prepareStatement(" SELECT * FROM SUBSERVICE_MAST  WHERE SSRV_CODE <> ? AND SSRV_SCAT_CODE='1003'");
          st.setString(1, subservice);
          
          System.out.println("************************: "+subservice);
          result = st.executeQuery();
          crs = new com.sun.rowset.CachedRowSetImpl();
          crs.populate(result);
           
         }
       
        catch(Exception e)
         {
          System.out.println(e);
         }
        finally{
                try{
                    con.close();
                    
                   }
        
                catch(Exception e){
                     e.printStackTrace(); 
                    }
                }
        return crs;
 
                          }

//public String getSelected() {
//    
//	String result = "";
//	for (Entry<String,Boolean> entry : checkMap.entrySet()) {
//		if (entry.getValue()) {
//			result = result + ", "+entry.getKey();
//		}
//	}
//	return result;
//}

          

}
