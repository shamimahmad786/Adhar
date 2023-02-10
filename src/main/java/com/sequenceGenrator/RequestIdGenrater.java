/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sequenceGenrator;

import com.databaseUtil.PgConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Logger;

/**
 *
 * @author nic5109
 */
public class RequestIdGenrater {
	Logger logger = Logger.getLogger(RequestIdGenrater.class.getName());
	PreparedStatement pre = null;
	ResultSet rs = null;
	Connection con = null;
	long counter;
	String seq_aadhaar_request_id;
	GetCurrentDateTime datetimeObj = new GetCurrentDateTime();

	public  String getRequestID() throws SQLException {
//        try {
             String c_date=  datetimeObj.getDate();
             String c_time=  datetimeObj.getTime();
//            String Query = "SELECT nextval('seq_aadhaar_var_student') as seq_aadhaar_var_app_no";
//            PgConnection pgc=new PgConnection();
//            con = pgc.connect();
//            pre = con.prepareStatement(Query);
//            rs = pre.executeQuery();
//            if (rs.next()) {
//                counter = rs.getLong("seq_aadhaar_var_app_no");
//}
//            if (counter == 1) {
//                seq_aadhaar_request_id ="NIC" + String.format("%012d", counter)+""+c_date+""+c_time+""+"NSP";
//            } else {
//                seq_aadhaar_request_id ="NIC" + String.format("%012d", counter)+""+c_date+""+c_time+""+"NSP";
//            }
//
//        } catch (Exception e) {
// logger.info("Error in RequestIdGenrater  class for sequense  "+e);
// 
//        }
//        finally{
//        try{
//        if(rs!=null){
//            rs.close();
//                    }
//        
//        }catch(Exception ex){}
//        try{
//        if(pre!=null){
//            pre.close();
//                    }
//        
//        }catch(Exception ex){}
//        
//        try{
//        if(con!=null){
//            con.close();
//                    }
//        
//        }catch(Exception ex){
//        
//            System.out.println("Exception in Sequence****"+ex);
//        }
//        
//        }
//    	NIC83097834554620230103175345aadhaar-auth
             
             try {
            	 Random random = new Random();
         	    char[] digits = new char[12];
         	    digits[0] = (char) (random.nextInt(9) + '1');
         	    for (int i = 1; i < 12; i++) {
         	        digits[i] = (char) (random.nextInt(10) + '0');
         	    }
         	    
         	    System.out.println("digit--->"+Long.parseLong(new String(digits)));
         	   seq_aadhaar_request_id ="NIC"+""+Long.parseLong(new String(digits))+""+c_date+""+c_time+""+"aadhaar-auth";
             }catch(Exception ex) {
            	 ex.printStackTrace();
             }
             
             
    	
//    	NIC + 123456654321 + 20160329 + 140845 +PKSN
    	
        System.out.println("seq_aadhaar_request_id *********"+seq_aadhaar_request_id);
        return seq_aadhaar_request_id;
    
    }
}

//Creating Dynamic TXN:-
//NIC + Randomly Generated No. (12 digits) + Date YYYYMMDD (8 digits) + Time hhmmss (6 digits) + Project Name (4 to 8 char)
//
//Example of TXN for the project is :- NIC + 123456654321 + 20170223 + 140845 +NHPS
//
//Final TX