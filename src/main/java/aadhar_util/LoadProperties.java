package aadhar_util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadProperties {

    public static String posturl = null ;
    public static String sa = null ;
    public static String ac = null ;
    public static String ver = null ;
    public static String lk = null ;
    public static String tid = null ;
    public static String pip = null ;
    public static String ci = null ;
    public static String pubkeycert = null ;
	public static String pubkeycertpre =null;
   
    public static final String conf_properties = "authClient.properties" ;
    
             	
    	   InputStream  fis = null;
	       Properties props = null;
                
               public void propertiesLoader() throws FileNotFoundException{	
			try {   //System.out.println("\nLoading properties file......");
			
			//fis = new FileInputStream(conf_properties);    
              fis = getClass().getClassLoader().getResourceAsStream(conf_properties); 
                                if(fis==null){
                    	           // System.out.println("Sorry, unable to find " + conf_properties);
                    		    return;
                    		}
                                
			} catch (NullPointerException e) {
				//System.out.println("ERROR : config.properties file : " + e.getMessage());        
			}
                       
                       try { 
                        props = new Properties() ;
                        props.load(fis);
                        fis.close();
                       
                        posturl = props.getProperty("posturl");
                        sa = props.getProperty("sa");
                        ac = props.getProperty("ac");
                        lk = props.getProperty("lk");
                        ver = props.getProperty("ver");
                        tid = props.getProperty("tid");
                        pip = props.getProperty("pip");
                        ci = props.getProperty("ci");
                        pubkeycert = props.getProperty("pubkeycert");
                        pubkeycertpre = props.getProperty("pubkeycertpre");
                        
                        //System.out.println("Properties file loaded successfully!");
                    } catch (IOException ex) {
                       // System.out.println("Error : Loading config_properties File" + ex.getMessage());
                        Logger.getLogger(LoadProperties.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            
               }
               
               /* Printing properties values */ 
               public void printProps(){
                    try{
                      /* System.out.println("\n\nPRINTING PROPERTY VALUES..................."); 
                       System.out.println("\nposturl = " + posturl);
                       System.out.println("\nsa = " + sa);
                       System.out.println("\nac = " + ac);
                       System.out.println("\nlk = " + lk);
                       System.out.println("\nver = " + ver);
                       System.out.println("\ntid = " + tid);
                       System.out.println("\npip = " + pip);
                       System.out.println("\nci = " + ci);
                       System.out.println("\npubkeycert = " + pubkeycert);
                       System.out.println("\npubkeycertpre = " + pubkeycertpre);
                       System.out.println("\n\n..........PRINTING PROPERTY VALUES FINISHED SUCCESSFULLY!");*/
                    }
                    catch(Exception e) { e.printStackTrace();}
                    //JOptionPane.showMessageDialog(null, "\n33333333333333");
               }   
               
               
               /* Aadhar number validation */
               public int CheckValidUID(String strUID) {
		int dMultTable[][] = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 0, 6, 7, 8, 9, 5 }, { 2, 3, 4, 0, 1, 7, 8, 9, 5, 6 }, { 3, 4, 0, 1, 2, 8, 9, 5, 6, 7 },
				{ 4, 0, 1, 2, 3, 9, 5, 6, 7, 8 }, { 5, 9, 8, 7, 6, 0, 4, 3, 2, 1 }, { 6, 5, 9, 8, 7, 1, 0, 4, 3, 2 }, { 7, 6, 5, 9, 8, 2, 1, 0, 4, 3 },
				{ 8, 7, 6, 5, 9, 3, 2, 1, 0, 4 }, { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 } };
		int permTable[][] = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 5, 7, 6, 2, 8, 3, 0, 9, 4 }, { 5, 8, 0, 3, 7, 9, 6, 1, 4, 2 }, { 8, 9, 1, 6, 0, 4, 3, 5, 2, 7 },
				{ 9, 4, 5, 3, 1, 2, 6, 8, 7, 0 }, { 4, 2, 8, 6, 5, 7, 3, 9, 0, 1 }, { 2, 7, 9, 3, 8, 0, 6, 4, 1, 5 }, { 7, 0, 4, 6, 9, 1, 3, 2, 5, 8 } };
		int i = 0, c = 0;

		for (i = 0; i < 12; i++) {
			int ni = 0, newC = 0;

			if (Character.isDigit(strUID.charAt(11 - i))) {
				ni = strUID.charAt(11 - i) - '0';
                                //done for testing
                                
                                
                        }
                        
			else {
				return (-1);
			}
			newC = dMultTable[c][permTable[i % 8][ni]];
			c = newC;
		}
		if (c == 0)
			return (0);
		return (-1);
	}
}
