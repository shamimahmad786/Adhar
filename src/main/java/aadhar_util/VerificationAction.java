/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aadhar_util;

/**
 *
 * @author nic5109
 */
import commonapi.utility.Verhoeff;
import uidai.ClientForm;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.Adhar.repository.AadharHistoryRepository;
import com.example.Adhar.repository.AadharTxnHistoryRepository;

//import uidai.ClientForm;

public class VerificationAction extends HttpServlet {

    String result;
	Map<String,String> resultObj;
    String gender = "";
    String dob = "";
    //String server="10.249.34.231"; //PRE PRODUCTION
    //String server = "10.247.252.93";// PRODUCTION
    String server = "10.247.141.227";// PRODUCTION
    String adharno;
    String adharNum;
    String name;
    String mobile = "";
    String studentid;
//    ResourceBundle resource = ResourceBundle.getBundle("authClient");
//    String posturl = resource.getString("posturl");
    ClientForm cf = new ClientForm();

    //  public String AadhaarResult(String aadhaar, String name, String gender,String dob,String server) throws IOException {
    public Map<String,String> AadhaarResult(String aadhaar, String n, String dobirth, String gen,AadharHistoryRepository aadharHistoryRepository, AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
        adharno = aadhaar;
        adharNum = aadhaar;
        name = n;
        dob = dobirth;
        gender = gen;

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // Validation for aadhar Number
//        server = request.getParameter("rblASA");
//
//        adharno = request.getParameter("adharno");
//        name = request.getParameter("name");
//
//        if (!Verhoeff.validateVerhoeff(adharno)) {
//            System.out.println("Invalid Aadhaar No.");
//        } 
//        else if (request.getParameter("rblASA") != null) {
//
//           
//            System.out.println(cf.register(adharno, name, gender, dob, server));
//        }
//
//    
//
//   else if(!Verhoeff.validateVerhoeff (adharno) ){
//         System.out.println("Invalid Aadhaar No.");
//    }
//
//    
//        else {
//    	   	try {
//            System.out.println(cf.register(adharno, name, gender, dob, server));
//        } catch (Exception e) {
//
//            System.out.println(e);
//
//        }
//    }
//
//    }
        if (!aadhaar.equals(null) || !name.equals(null)) {
            if (!Verhoeff.validateVerhoeff(adharno)) {
                //System.out.println("***************************Identity data Mismatch********************  "+adharno);

                String no = "N";
                String msg1 = "Invalid Aadhaar No.";
                String msg2 = "Identity data Mismatch";
                result = no + "$" + msg1 + "$" + msg2;

                return resultObj;
            } else {
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);

                return resultObj;
            }

        }
        return resultObj;
    }

    public Map<String,String> AadhaarResultG(String aadhaar, String n, String gen,AadharHistoryRepository aadharHistoryRepository, AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
        adharno = aadhaar;
        adharNum = aadhaar;
        name = n;

        gender = gen;

        if (!aadhaar.equals(null) || !name.equals(null)) {
            if (!Verhoeff.validateVerhoeff(adharno)) {
                //System.out.println("***************************Identity data Mismatch********************  "+adharno);

                String no = "N";
                String msg1 = "Invalid Aadhaar No.";
                String msg2 = "Identity data Mismatch";
                result = no + "$" + msg1 + "$" + msg2;

                return resultObj;
            } else {
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);

                return resultObj;
            }

        }
        return resultObj;
    }

    public String AadhaarResultM5(String aadhaar, String n, String dobirth, String gen, String mob,AadharHistoryRepository aadharHistoryRepository, AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
        adharno = aadhaar;
        adharNum = aadhaar;
        name = n;
        dob = dobirth;
        gender = gen;
        mobile = mob;

        if (!aadhaar.equals(null) || !name.equals(null)) {
            if (!Verhoeff.validateVerhoeff(adharno)) {
                //System.out.println("***************************Identity data Mismatch********************  "+adharno);

                String no = "N";
                String msg1 = "Invalid Aadhaar No.";
                String msg2 = "Identity data Mismatch";
                result = no + "$" + msg1 + "$" + msg2;

                return result;
            } else {
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);

                return result;
            }

        }
        return result;
    }

    public String AadhaarResultM4(String aadhaar, String n, String gen, String mob,AadharHistoryRepository aadharHistoryRepository, AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
        adharno = aadhaar;
        adharNum = aadhaar;
        name = n;

        gender = gen;
        mobile = mob;

        if (!aadhaar.equals(null) || !name.equals(null)) {
            if (!Verhoeff.validateVerhoeff(adharno)) {
                //System.out.println("***************************Identity data Mismatch********************  "+adharno);

                String no = "N";
                String msg1 = "Invalid Aadhaar No.";
                String msg2 = "Identity data Mismatch";
                result = no + "$" + msg1 + "$" + msg2;

                return result;
            } else {
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);

                return result;
            }

        }
        return result;
    }

    public String AadhaarResult2P(String aadhaar, String n,AadharHistoryRepository aadharHistoryRepository, AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
        adharno = aadhaar;
        adharNum = aadhaar;
        name = n;

        if (!aadhaar.equals(null) || !name.equals(null)) {
            if (!Verhoeff.validateVerhoeff(adharno)) {
                //System.out.println("***************************Identity data Mismatch********************  "+adharno);

                String no = "N";
                String msg1 = "Invalid Aadhaar No.";
                String msg2 = "Identity data Mismatch";
                result = no + "$" + msg1 + "$" + msg2;

                return result;
            } else {
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);

                return result;
            }

        }
        return result;
    }

    /////////////////////////////TOKEN
    public String AadhaarResultToken(String aadhaar, String n, String dobirth, String gen,AadharHistoryRepository aadharHistoryRepository, AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
        adharno = aadhaar;
        adharNum = aadhaar;
        name = n;
        dob = dobirth;
        gender = gen;

        if (!aadhaar.equals(null) || !name.equals(null)) {
            if (!Verhoeff.validateVerhoeff(adharno)) {
                //System.out.println("***************************Identity data Mismatch********************  "+adharno);

//                String no = "N";
//                String msg1 = "Invalid Aadhaar No.";
//                String msg2 = "Identity data Mismatch";
//                result = no + "$" + msg1 + "$" + msg2;
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);
                return result;
            } else {
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);

                return result;
            }

        }
        return result;
    }

    public String AadhaarResultGToken(String aadhaar, String n, String gen,AadharHistoryRepository aadharHistoryRepository, AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
        adharno = aadhaar;
        adharNum = aadhaar;
        name = n;
        gender = gen;

        if (!aadhaar.equals(null) || !name.equals(null)) {
            if (!Verhoeff.validateVerhoeff(adharno)) {
                //System.out.println("***************************Identity data Mismatch********************  "+adharno);

//                String no = "N";
//                String msg1 = "Invalid Aadhaar No.";
//                String msg2 = "Identity data Mismatch";
//                result = no + "$" + msg1 + "$" + msg2;
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);
                return result;
            } else {
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);

                return result;
            }

        }
        return result;
    }

    public String AadhaarResultM5Token(String aadhaar, String n, String dobirth, String gen, String mob,AadharHistoryRepository aadharHistoryRepository, AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
        adharno = aadhaar;
        adharNum = aadhaar;
        name = n;
        dob = dobirth;
        gender = gen;
        mobile = mob;

        if (!aadhaar.equals(null) || !name.equals(null)) {
            if (!Verhoeff.validateVerhoeff(adharno)) {
                //System.out.println("***************************Identity data Mismatch********************  "+adharno);

//                String no = "N";
//                String msg1 = "Invalid Aadhaar No.";
//                String msg2 = "Identity data Mismatch";
//                result = no + "$" + msg1 + "$" + msg2;
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);
                return result;
            } else {
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);

                return result;
            }

        }
        return result;
    }

    public String AadhaarResultM4Token(String aadhaar, String n, String gen, String mob,AadharHistoryRepository aadharHistoryRepository, AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
        adharno = aadhaar;
        adharNum = aadhaar;
        name = n;
        gender = gen;
        mobile = mob;

        if (!aadhaar.equals(null) || !name.equals(null)) {
            if (!Verhoeff.validateVerhoeff(adharno)) {
                //System.out.println("***************************Identity data Mismatch********************  "+adharno);

//                String no = "N";
//                String msg1 = "Invalid Aadhaar No.";
//                String msg2 = "Identity data Mismatch";
//                result = no + "$" + msg1 + "$" + msg2;
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);
                return result;
            } else {
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);

                return result;
            }

        }
        return result;
    }

    public Map<String,String> AadhaarResult2PToken(String aadhaar, String n,String studentid, AadharHistoryRepository aadharHistoryRepository, AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
    	
    	
    	System.out.println("AadhaarResult2PToken");
        adharno = aadhaar;
        adharNum = aadhaar;
        name = n;
        if (!aadhaar.equals(null) || !name.equals(null)) {
            if (!Verhoeff.validateVerhoeff(adharno)) {
                //System.out.println("***************************Identity data Mismatch********************  "+adharno);

//                String no = "N";
//                String msg1 = "Invalid Aadhaar No.";
//                String msg2 = "Identity data Mismatch";
//                result = no + "$" + msg1 + "$" + msg2;
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);
                return resultObj;
            } else {
            	resultObj = cf.register(adharNum, name, gender, dob, server, mobile,studentid,aadharHistoryRepository,aadharTxnHistoryRepository);

                return resultObj;
            }

        }
        return resultObj;
    }

}
