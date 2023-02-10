/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uidai;


import java.io.FileNotFoundException;

import aadhar_util.LoadProperties;

/**
 *
 * @author cODE - iNFINITY
 */

public class AuthenticationNICBIO{
    
        public static void main(String args[]) throws FileNotFoundException{ 
        	
            // System Properties set here in the Client form set here..............
            LoadProperties properties = new LoadProperties();
            properties.propertiesLoader();
            properties.printProps();
        }
        
        void setResTime(int ti){
           Time = ti;
        }
 
        int getResTime(){
           return(Time) ;
        }
        int Time ;
}

