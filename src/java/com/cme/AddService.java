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

@SessionScoped

public class AddService
{
RequestContext context = RequestContext.getCurrentInstance();
FacesMessage message = null;
PreparedStatement ps =null;
Connection con= null;
int i =0;

private Map<String,Boolean> checkMap = new HashMap<String,Boolean>();

    AddService() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

public Map<String, Boolean> getCheckMap() {
        return checkMap;
  }

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
//  public   ResultSet getAll() {
//  // String re = "output";
//  CachedRowSet crs = null;
//  ResultSet result = null;
//  PreparedStatement st = null;
//        try
//        {
//          Class.forName("oracle.jdbc.driver.OracleDriver");
//          con = DriverManager.getConnection("jdbc:oracle:thin:@209.126.76.237:1521:CME" , "CPSPROD" ,"CPS4CME");
//          Statement stmt;
//          stmt = con.createStatement();
//          st = con.prepareStatement(" SELECT * FROM SUBSERVICE_MAST  WHERE SSRV_CODE <> ? AND SSRV_SCAT_CODE='1003'");
//          st.setString(1, subservice);
//          
//          System.out.println("************************: "+subservice);
//          result = st.executeQuery();
//          crs = new com.sun.rowset.CachedRowSetImpl();
//          crs.populate(result);
//           
//         }
//       
//        catch(Exception e)
//         {
//          System.out.println(e);
//         }
//        finally{
//                try{
//                    con.close();
//                    
//                   }
//        
//                catch(Exception e){
//                     e.printStackTrace(); 
//                    }
//                }
//        return crs;
// 
//                          }
//                                  

//           String sql = "INSERT INTO SUBSCRIPTION_PORTAL(SOURCE, NAME, E_MAIL, PHONE_NUMBER,SERVICE_ID , SUBSERVICE_ID)VALUES(?,?,?,?,?,?)";
//           ps=con.prepareStatement(sql);
//            ps.setString(2,username);
//            ps.setString(1,source);
//            ps.setString(5,service);
//            ps.setString(6,subservice);
//            ps.setString(4,number);
//            ps.setString(3,email);
//            ps.setBoolean(5,cource1);
    
//            i=ps.executeUpdate()     ;
//        }
//     
//      }
//     catch (SQLException ex) {
//        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//        retValue = "error";
//       }
//     catch (ClassNotFoundException ex) {
//        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//        retValue = "error";
//       }
//      finally{
//                try{
//                con.close();
//                ps.close();
//                }
//        
//                   catch(Exception e)
//                {
//             e.printStackTrace();              }

  
                
 


 
                          

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

          


