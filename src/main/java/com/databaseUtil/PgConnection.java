/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.databaseUtil;

/**
 *
 * @author nic5109
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author super
 */
public class PgConnection {
    
    
//   public Connection connect11()
// { Logger logger = Logger.getLogger(PgConnection.class.getName());
//
//    
//        Connection con = null;
//     try {
//             InitialContext context = new InitialContext();
//             //DataSource ds =(DataSource) context.lookup("java:/jboss/NSPMISDS"); 
//             DataSource ds =(DataSource) context.lookup("java:/jboss/NSPDS_201718");          
//             con = ds.getConnection();
//           
//        }
//        catch( NamingException | SQLException e )
//        {
//          System.out.println(e.toString());
//         logger.info("Connection Problem With DataSource   "+e.toString());
//        }
//        
//  return con;
// }
   
   
   
   public Connection connect() {
        Connection con1 = null;
        try {
            Class.forName("org.postgresql.Driver");
          //con1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/nsp_aadhaar_verification","nsp_write_user","nsp@123#");
          //con1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/nsp_aadhaar_verification", "postgres", "nic123");
          con1 = DriverManager.getConnection("jdbc:postgresql://10.247.51.161:5432/nsp_aadhaar_verification", "postgres", "nic123");

        } catch (ClassNotFoundException e) {
            System.out.print(e.toString());
        } catch (SQLException e) {
            System.out.print(e.toString());
        }
        return con1;

    }
   
   

}

