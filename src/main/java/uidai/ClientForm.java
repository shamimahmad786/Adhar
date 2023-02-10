package uidai;

import aadhar_util.AadhaarMask;
import com.databaseUtil.PgConnection;
import com.example.Adhar.modal.AadharHistory;
import com.example.Adhar.modal.AadharTxnHistory;
import com.example.Adhar.repository.AadharHistoryRepository;
import com.example.Adhar.repository.AadharTxnHistoryRepository;
import com.sequenceGenrator.RequestIdGenrater;
import com.sequenceGenrator.TimeStamp;
import demo.commonapi.utility.IpHttpClient;
import demo.commonapi.utility.XmlUtility;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.security.Security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebService;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;
//import sun.org.mozilla.javascript.internal.regexp.SubString;
@WebService(serviceName = "verificationService1")
public class ClientForm {

    public static String udc = "UDC000000001";
    public static String url = null;
    public static String ci = null;
    public static String tid = null;
    public static String errInfo = null;
    public static String errCode = null;
    public static String env = "PP";
    public static String capenv = "PP";
    public static String demoauth = "false";
    public static String capdemoauth = "false";
    public static Boolean usePI = false;
    public static String pidTimeStamp = null;

    FileInputStream in = null;
    Properties props = null;
//    Uses use = new Uses() ;
//    Pid pid =new Pid();
    // public Encrypter encrypter = null;
    String AuthURl;
    String EkycURL;
    String OtpURL;
   int responseCode;// For Connection to Asa Server eg- responce code 200 for ok
    private String response = "";

    /* Data */
    private String hmac = "";
    private String skey = "";
    private String data = "";

    /* Attributes */
    // private Map<String,String> xmlattb;
    private String rdsid = "";
    private String rdsver = "";
    private String dpid = "";
    private String dc = "";
    private String mi = "";
    private String mc = "";
    private String srno = "";
    public String uid = "";
    public String posturls = "";
    public String username = "";
    
    public String[] args = null;
    public String msg;
    public String newPidTime;
    public String newPidTimeResponse;
    public long now = 0L;
    public long startTime = 0L;
    public long endTime = 0L;
    //private String udCode = "";
    public String txn;
    public String gender = "";
    public String dob = "";
    public String mobile = "";
    //String server = "10.249.34.231"; //Pre Production
    String server="10.247.252.93"; // production
    public String pip;
    /*
    Paramter for Aadhaar Log
     */
    PgConnection pgObj;
    PreparedStatement ps;
    Connection con_log;
    String _transaction_id;
    String _aadhaar_num;
    String _aadhar_name;
    String _server_ip; //or pip
    String _transaction_time;
    String _response;
    String _other_response;
    int _connection_responseCode;
    String consent_remark="My Aadhaar number can be used for name identification and payment purpose";
    String errorCode="";
    String _pid_string="";
                   // boolean  consent_taken =true;
    public Map<String,String> register(String adharNum, String name, String gender, String dob, String server, String mob,String studentid,AadharHistoryRepository aadharHistoryRepository,AadharTxnHistoryRepository aadharTxnHistoryRepository) throws IOException {
       System.out.println("In registration");
       
       ResourceBundle resource = ResourceBundle.getBundle("authClient");
       pip = resource.getString("pip");
       Map<String,String> resObj=new HashMap<String,String>();
       AadharHistory aaHistryObj=new  AadharHistory();
       AadharTxnHistory aaTxnObj= new AadharTxnHistory();
       System.out.println("1----pip---->"+pip);


        AuthenticationNICBIO.main(args);

        uid = adharNum;
        username = name;
        mobile = mob;
        //posturls="http://"+server+":8080/NicASAServer/ASAMain/2.0";// Production Url
        //posturls = "http://10.249.34.231:8080/NicASAServer/ASAMain"; // Pre Production

               
       //posturls = "http://10.249.34.250:8080/NicASAServer/ASAMain"; //production  
        
        posturls = "http://10.247.252.95:8080/NicASAServer/ASAMain";
        
//       posturls = "http://developer.uidai.gov.in/authserver/2.5/public/9/9/MCNYL7FpPgjEhx7HBp9tu59Vdm4FnYGlxuqHctfAeNNaCufVafshqzQ"; //production   Commented by Shamim
//       posturls = " http://developer.uidai.gov.in/authrd/2.0";
        
        RequestIdGenrater rg = new RequestIdGenrater();
// Commented by Shamim        
        try {
            _transaction_id = rg.getRequestID();
        } catch (SQLException ex) {
            Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        txn=_transaction_id;
        try {

            Security.addProvider(new BouncyCastleProvider());
//            NIC + 123456654321 + 20170223 + 140845 +NHPS
            //Creating auth xml with minutiae to send to the server 
            String authXML = this.createDemoAUAAuthXml(uid, true, false, username, gender, dob, server,mobile);
            
            System.out.println("2---authXML--->"+authXML);
            System.out.println("Request XML---->"+authXML);
            System.out.println("XML complete--->");
            IpHttpClient client = new IpHttpClient();
            HttpURLConnection con = client.getHttpUrlConnection(posturls);
            
//System.out.println("1posturls ---"+posturls);
//System.out.println("2con ---"+con);
            client.sendRequest(authXML, con);
              //System.out.println("\n\n1Request XML : " + authXML + "\n\n");

            /*Getting response code from server */
         //   int responseCode = client.getResponseCode(con);
               responseCode = client.getResponseCode(con);
               
               System.out.println("responseCode---->"+responseCode);
               
               
               if (responseCode == 200) {
            	   String authResp = client.getResponseDataAsString(con);
            	   System.out.println("authRespXML---->"+authResp);
            	   XmlUtility utility = new XmlUtility();
                   Element element = utility.getDocumentElement(authResp);
                   NamedNodeMap attributes = element.getAttributes();
                   String retValue = utility.getAttributeValue(attributes, "ret");
                   System.out.println("retValue---->"+retValue);
                   String infoValue = utility.getAttributeValue(attributes, "info");
                  
                   if(retValue.equalsIgnoreCase("n")) {
                	   errorCode = utility.getAttributeValue(attributes, "code");   
                	   String error = utility.getAttributeValue(attributes, "error"); 
                	   aaHistryObj.setInputXml(authXML);
                	   aaHistryObj.setTxn(txn);
                	   aaHistryObj.setResponseXml(authResp);
                	   aaHistryObj.setFinalResponse("N");
                	   
                	   aadharHistoryRepository.save(aaHistryObj);
                	   resObj.put("status", "0");
                	   resObj.put("message", errorCode);
                	   
                	   
                	   aaTxnObj.setStudentId(Integer.parseInt(studentid));
                	   aaTxnObj.setStudentName(username);
                	   aaTxnObj.setAadharResponseTxnId(txn);
                	   aaTxnObj.setAadharResponseInfo(infoValue);
                	   aaTxnObj.setAadharResponseCode(errorCode);
                	   aaTxnObj.setAadharResponseError(error);
                	   aaTxnObj.setAadharResponseRet(retValue);
                	   aaTxnObj.setAadharRequestPid(_pid_string);
                	   aaTxnObj.setAadharAuthDate(new Date());
                	   aadharTxnHistoryRepository.save(aaTxnObj);
                	   
                   }else if(retValue.equalsIgnoreCase("y") || retValue.equalsIgnoreCase("Y")) {
                	  
                	   aaHistryObj.setInputXml(authXML);
                	   aaHistryObj.setTxn(txn);
                	   aaHistryObj.setResponseXml(authResp);
                	   aaHistryObj.setFinalResponse("Y");
                	   aadharHistoryRepository.save(aaHistryObj);
                	   resObj.put("status", "1");
                	   resObj.put("message", "success");
                	   
                	   
                	   aaTxnObj.setStudentId(Integer.parseInt(studentid));
                	   aaTxnObj.setStudentName(username);
                	   aaTxnObj.setAadharResponseTxnId(txn);
                	   aaTxnObj.setAadharResponseInfo(infoValue);
                	   aaTxnObj.setAadharResponseCode(errorCode);
                	   aaTxnObj.setAadharResponseError(errorCode);
                	   aaTxnObj.setAadharResponseRet(retValue);
                	   aaTxnObj.setAadharRequestPid(_pid_string);
                	   aaTxnObj.setAadharAuthDate(new Date());
                	   aadharTxnHistoryRepository.save(aaTxnObj);
                   }
               }
               
               
               
               
               
            /* Checking response code */
//            if (responseCode == 200) {
//                String authResp = client.getResponseDataAsString(con);
//
//                System.out.println("authResp---->"+authResp);
//                
//                //System.out.println("3Response XML : " + authResp + "\n");
//                XmlUtility utility = new XmlUtility();
//                Element element = utility.getDocumentElement(authResp);
//                NamedNodeMap attributes = element.getAttributes();
//               // System.out.println("authResp = " + authResp);
//                /* Getting ret attribute value from the response */
//                String retValue = utility.getAttributeValue(attributes, "ret");
//                
//                System.out.println("retValue---->"+retValue);
//                
//                String infoValue = utility.getAttributeValue(attributes, "info");
//                String tokenValue = infoValue.substring(3, 75);
//                String verificationMethod = infoValue.substring(76, 77);
//                /*Calculate Response Time*/
//                endTime = System.nanoTime();
////              System.out.println("start time :"+startTime);
////              System.out.println("End time :"+endTime);
//                long timee = endTime - startTime;
//                double ResTime = (timee / 1000000000.0);
////                System.out.println("Response time: " +timee ); 
////                System.out.println("Response time nano :" +ResTime ); 
//                DecimalFormat df = new DecimalFormat();
//                df.setMaximumFractionDigits(2);
//                String finalResponseTime = df.format(ResTime);
////                System.out.println(finalResponseTime);
//
//                /*String resTimeStamp1 = utility.getAttributeValue(attributes, "ts");
//                
//                String resTimeStamp = resTimeStamp1.substring(resTimeStamp1.indexOf("T") + 1);
//                resTimeStamp = resTimeStamp.substring(0, resTimeStamp.indexOf("+"));
//                
//                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//                sdf.setTimeZone(TimeZone.getTimeZone("IST"));
//                String getDate = resTimeStamp1.substring(0, resTimeStamp1.indexOf("T"));
//               
//                Date datee = sdf.parse(getDate + " "+resTimeStamp);
//                
//                long ResInMilli =datee.getTime();
//                long FinalResponeTime = ResInMilli-now;
//
//                long seconds = (long) (FinalResponeTime / 1000.0);
//            //    System.out.println("Response time in milliseconds SECNDS CONVERT: " +seconds ); */
//                String err;
//
//                if (retValue.equalsIgnoreCase("y")) {
//                    String yes = "Y";
////System.out.println("4Authentication Successful "+server);
//
//                    String msg1 = "Authentication Successful from " + server + " server.";
//                    String displayResTime = "<br>" + "Response returned in " + finalResponseTime + " seconds.";
//                    //System.out.println("AAdhaar Res*****************" + msg + displayResTime);
//                    msg = yes+"$"+msg1+"$"+"success"+"$"+tokenValue+"$"+verificationMethod;
//                    /*
//                            Logic for Create log in database
//                     */
//                      _aadhaar_num=  AadhaarMask.maskAadhaarString(uid, 0, uid.length()-4, 'X');
//                 //   System.out.println("Mask aadhaar_num   "+_aadhaar_num);
//                    
//                   
//                  // _aadhaar_num= uid;
//                   _aadhar_name=username;
//                   _server_ip=pip;
//                   _response=yes;
//                   _other_response="Authentication Successful";
//                   _connection_responseCode=responseCode;
//                    pgObj = new PgConnection();
//                    con_log = pgObj.connect();
//                    String sql =  "insert into aadhaar_transaction_log\n" +
//                    "(transaction_id , aadhaar_num , aadhar_name , server_ip ,transaction_time ,response ,other_response,connection_responseCode, tokenid,vid, verification_mode ,consent_remark,consent_taken) VALUES\n" +
//                    "(?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?)";
//                    try{
//                        
//                   
//                     
//                        
//                        
//                    ps= con_log.prepareStatement(sql);
//                    ps.setString(1,_transaction_id);
//                    ps.setString(2,_aadhaar_num);
//                    ps.setString(3,_aadhar_name);
//                    ps.setString(4,_server_ip); // _server_ip
////                    ps.setString(5,TimeStamp.getDate());
//                    ps.setString(5,_response);
//                    ps.setString(6,_other_response);
//                    ps.setInt(7,_connection_responseCode); 
//                    ps.setString(8,tokenValue); 
//                    ps.setString(9,""); 
//                    ps.setString(10,verificationMethod); 
//                     ps.setString(11,consent_remark); 
//                    ps.setBoolean(12,true);
//                    
//                    int index=ps.executeUpdate();
//                    if (index>0) 
//                          {
//                      
//                       return msg;
//                    }
//                    else{
//                              return msg;
//                              
//                              }
//                    
//                        
//                        
//                        
//                    } catch(Exception ex){
//                        System.out.println("EXCEPTION = " +ex.toString());}
//                  
//                    
//                   // return msg;
//                    // return msg+displayResTime;
//                } else {
//                    String no = "N";
//                    err = utility.getAttributeValue(attributes, "err");
//                    String msg1 = "Authentication Failed.";
//                    String errMsg = "<br>" + "Error Code : " + err;
//                    String displayResTime = "<br>" + "Response returned in " + finalResponseTime + " seconds.";
//                    msg = no + "$" + msg1 + "$" + err;
//                    System.out.println(msg+" :" + err);
//                    // return msg+errMsg+displayResTime;
//                    /*
//                            Logic for Create log in database
//                     */
//                    
//                    
//                 //_aadhaar_num=AadhaarMask.maskAadhaarNumber(uid, maskFormate);
//                _aadhaar_num=  AadhaarMask.maskAadhaarString(uid, 0, uid.length()-4, 'X');
//                          
//
//                    
//                //   _aadhaar_num= uid;
//                   _aadhar_name=username;
//                   _server_ip=pip;
//                   _response=no;
//                   _other_response=err;
//                   _connection_responseCode=responseCode;
//                    pgObj = new PgConnection();
//                    con_log = pgObj.connect();
//                    String sql =  "insert into aadhaar_transaction_log"
//				+ "(transaction_id,aadhaar_num,aadhar_name,server_ip,transaction_time,response,other_response,connection_responseCode,consent_remark,consent_taken) VALUES"
//				+ "(?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?)";
//                    
//                    
//                    ps = con_log.prepareStatement(sql);
//                    ps.setString(1,_transaction_id);
//                    ps.setString(2,_aadhaar_num);
//                    ps.setString(3,_aadhar_name);
//                    ps.setString(4,_server_ip);
////                    ps.setString(5,TimeStamp.getDate());
//                    ps.setString(5,_response);
//                    ps.setString(6,_other_response);
//                    ps.setInt(7,_connection_responseCode);
//                      ps.setString(8,consent_remark); 
//                    ps.setBoolean(9,true);
//                    
//                    
//                    int index=ps.executeUpdate();
//                    if (index>0) 
//                          {
//                      
//                       return msg;
//                    }
//                    else{
//                              return msg;
//                              
//                              }
// 
//                }
//            } else {
//                /*
//                Connection Fail
//                */
//                //System.out.println("2.responseCodeERROR = " + responseCode);
//                String msg = " Unable to connect authentication server.";
//               
//                
//                  _aadhaar_num=  AadhaarMask.maskAadhaarString(uid, 0, uid.length()-4, 'X');
//                  // System.out.println("Mask aadhaar_num   "+_aadhaar_num);
//
//                // _aadhaar_num= uid;
//                   _aadhar_name=username;
//                   _server_ip=pip;
//                   _response="NA";
//                   _other_response="NA";
//                   _connection_responseCode=responseCode;
//                    pgObj = new PgConnection();
//                    con_log = pgObj.connect();
//                    String sql =  "insert into aadhaar_transaction_log"
//				+ "(transaction_id , aadhaar_num , aadhar_name , server_ip ,transaction_time ,response ,other_response,connection_responsecode,consent_remark,consent_taken ) VALUES"
//				+ "(?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?)";
//
//                    ps = con_log.prepareStatement(sql);
//                    
//                    ps.setString(1,_transaction_id);
//                    ps.setString(2,_aadhaar_num);
//                    ps.setString(3,_aadhar_name);
//                    ps.setString(4,_server_ip);
////                    ps.setString(5,TimeStamp.getDate());
//                    ps.setString(5,_response);
//                    ps.setString(6,_other_response);
//                    ps.setInt(7,_connection_responseCode);
//                      ps.setString(8,consent_remark); 
//                    ps.setBoolean(9,true);
//                     int index=ps.executeUpdate();
//                    if (index>0) 
//                          {
//                      
//                       return msg;
//                    }
//                    else{
//                              return msg;
//                              
//                              }
//
//                  
//               // return msg;
//            }

        } catch (Exception e) {
            //System.out.println("5Authentication Error = " + e.toString());
            e.printStackTrace();
        }
         finally{
       
        
        try{
        if(ps!=null){
            ps.close();
                    }
        
        }catch(Exception ex){}
        
        try{
        if(con_log!=null){
            con_log.close();
                    }
        
        }catch(Exception ex){
        
            //System.out.println("Exception in Sequence****"+ex);
        }
        
        }
        
        
        
        return resObj;

    }

    public String createDemoAUAAuthXml(String aadhaarno, boolean isPI, boolean isBIo, String name, String gender, String dob, String server, String mobile) {
        /* ------------ Creating pid xml here ----------- */
    	
    	System.out.println("In XML Creation--->");
        StringWriter writer = new StringWriter();

        try {

            byte[] pid = createPID(name, gender, dob, mobile);

            System.out.println(pid);
            
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlSerializer serializer = factory.newSerializer();

            serializer.setOutput(writer);
//            serializer.startDocument("UTF-8", true);

            /* -------------- <Auth> starts here ------------------- */
            serializer.startTag("", "Auth");

            /* These are auth xml attributes */
//            serializer.attribute("", "xmlns", "http://developer.uidai.gov.in/authserver/2.5/public/9/9/MCNYL7FpPgjEhx7HBp9tu59Vdm4FnYGlxuqHctfAeNNaCufVafshqzQ");
//            serializer.attribute("", "xmlns", "http://www.uidai.gov.in/authentication/uid-auth-request/2.5");
            serializer.attribute("", "uid", aadhaarno);
            serializer.attribute("", "rc", "Y");
            serializer.attribute("", "tid", aadhar_util.LoadProperties.tid); //Registered or blank
            serializer.attribute("", "ver", aadhar_util.LoadProperties.ver);
//            serializer.attribute("", "txn", "RDDEMOAUTH123");//txn
//            serializer.attribute("", "txn", "UKC:KUA1:20121022084122630");
//            serializer.attribute("", "txn", "NIC83097834554620230103175345aadhaar-auth");
            
            serializer.attribute("", "txn", txn);//txn
            //serializer.attribute("", "pip", aadhar_util.LoadProperties.pip);
            serializer.attribute("", "ac", aadhar_util.LoadProperties.ac);
            serializer.attribute("", "sa", aadhar_util.LoadProperties.sa);
            serializer.attribute("", "lk", aadhar_util.LoadProperties.lk);

            /* -------------- <uses> starts here -------------------*/
            serializer.startTag("", "Uses");
            if (isPI) {
                serializer.attribute("", "pi", "y");
            } else {
                serializer.attribute("", "pi", "n");
            }

            serializer.attribute("", "pa", "n");
            serializer.attribute("", "pfa", "n");

            if (isBIo) {
                serializer.attribute("", "bio", "y");
                serializer.attribute("", "bt", "FMR");
            } else {
                serializer.attribute("", "bio", "n");
            }

            serializer.attribute("", "otp", "n");
            serializer.attribute("", "pin", "n");
            serializer.endTag("", "Uses");
            /* --------------- </uses> closes here -------------------- */

 /* -------------- <meta> starts here ------------------- */
//            serializer.startTag("", "Meta");
            serializer.startTag("", "Device");
            serializer.attribute("", "udc", udc);
            serializer.attribute("", "rdsId", rdsid);
            serializer.attribute("", "rdsVer", rdsver);
            serializer.attribute("", "dpId", dpid);
            serializer.attribute("", "dc", dc);
            serializer.attribute("", "mi", mi);
            serializer.attribute("", "mc", mc);
            serializer.endTag("", "Device");
            /* -------------- </meta> closes here ------------------- */

            //Encrypter Encpt = new Encrypter(Loadproperties.pubkeycert, 256);
            //encSessionKey, encXMLPIDData, encryptedHmac  set in data here...PID is encryped with session key 
            //AuthRequestData ReqData = Encpt.generateAuthRequestDataNoBase64Encoding(pid);
            /**
             * ********************NEW for session key
             * ******************************
             */
            byte[] sessionKey = null;
            byte[] syncSessionKey = null;
            byte[] encryptedSessionKey = null;
            
            System.out.println("6------------Before Session Set");
            

//10.249.34.231//pre production
//10.247.252.93 // Prod
            
            System.out.println("server---->"+server);
            
//            if(server.equals("10.247.252.93")) {
            	if(server.equals("10.247.141.227")) {
            	
                //uidai.Encrypter encrypter = new uidai.Encrypter(aadhar_util.LoadProperties.pubkeycertpre); //for pre Production
                  uidai.Encrypter encrypter = new uidai.Encrypter(aadhar_util.LoadProperties.pubkeycert);// for Production
                uidai.SessionKeyDetails sessionKeyDetails = null;
                try {
                    sessionKey = encrypter.generateSessionKey();
                    SynchronizedKey synchronizedKey = new SynchronizedKey(
                            sessionKey, java.util.UUID.randomUUID()
                            .toString(), new Date());
                    syncSessionKey = synchronizedKey.getSeedSkey();
                    encryptedSessionKey = encrypter
                            .encryptUsingPublicKey(syncSessionKey);
                    sessionKeyDetails = SessionKeyDetails
                            .createSkeyToInitializeSynchronizedKey(
                                    synchronizedKey.getKeyIdentifier(),
                                    encryptedSessionKey);
                } catch (Exception e) {
                    //Log.e("session Exception","-->"+e);
                    System.out.println(e.getMessage());
                }

                /**
                 * ******************************************************
                 */
                /**
                 * *******************NEW for data and
                 * hmac***********************************
                 */
                //byte[]  xmlPidBytes = getPidXml("",bio,pi, fpImgString , otp_check , otp).getBytes();
                byte[] cipherTextWithTS = null;
                byte[] encSrcHash = null;
                uidai.HashGenerator hashgenerator = new uidai.HashGenerator();

                try {
                    cipherTextWithTS = encrypter.encrypt(pid, sessionKey, pidTimeStamp);
                    byte[] iv = encrypter.generateIv(pidTimeStamp);
                    byte[] aad = encrypter.generateAad(pidTimeStamp);
                    byte[] srcHash = encrypter.generateHash(pid);
                    encSrcHash = encrypter.encryptDecryptUsingSessionKey(true, sessionKey, iv, aad, srcHash);
                    byte[] decryptedText = encrypter.decrypt(cipherTextWithTS, sessionKey, encSrcHash);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                byte[] hmac = hashgenerator.generateSha256Hash(pid);
                String encryptedHmac = null;
                String encdata = null;

                try {
                    //encdata = Base64.encodeToString(encrypter.encryptUsingSessionKey(syncSessionKey, xmlPidBytes), Base64.DEFAULT);
                    //encdata = Base64.encodeToString(cipherTextWithTS, 0);
                    encdata = new String(Base64.encode(cipherTextWithTS));
                    //encryptedHmac = Base64.encodeToString(encrypter.encryptUsingSessionKey(syncSessionKey, hmac),
                    //encryptedHmac = Base64.encodeToString(encSrcHash, 0);
                    encryptedHmac = new String(Base64.encode(encSrcHash));
                } catch (Exception ex) {
                    //  System.out.println("Error:" + ex.getMessage());
                }
                /**
                 * **********************************************************
                 */

                /* -------------- <skey> starts here ------------------- */
                serializer.startTag("", "Skey");
                serializer.attribute("", "ci", encrypter.getCertificateIdentifier());
                serializer.text(new String(Base64.encode(sessionKeyDetails.getSkeyValue())));
                serializer.endTag("", "Skey");
                /* -------------- </skey> closes here ------------------- */

 /* -------------- <data> starts here ------------------- */
                serializer.startTag("", "Data");
                serializer.attribute("", "type", "X");
                serializer.text(encdata);
                serializer.endTag("", "Data");
                /* -------------- </data> closes here ------------------- */

 /* -------------- <hmac> starts here ------------------- */
                serializer.startTag("", "Hmac");
                serializer.text(encryptedHmac);
                serializer.endTag("", "Hmac");
                /* -------------- </hmac> closes here ------------------- */

                serializer.endTag("", "Auth");
                serializer.endDocument();
            } else {
                uidai.Encrypter encrypter = new uidai.Encrypter(aadhar_util.LoadProperties.pubkeycert);
                uidai.SessionKeyDetails sessionKeyDetails = null;
                try {
                    sessionKey = encrypter.generateSessionKey();
                    SynchronizedKey synchronizedKey = new SynchronizedKey(
                            sessionKey, java.util.UUID.randomUUID()
                            .toString(), new Date());
                    
                    System.out.println("7----synchronizedKey-->"+synchronizedKey);
                    
                    syncSessionKey = synchronizedKey.getSeedSkey();
                    
                    System.out.println("8---->syncSessionKey"+syncSessionKey);
                    
                    encryptedSessionKey = encrypter
                            .encryptUsingPublicKey(syncSessionKey);
                    
                    
                    System.out.println("9----encryptedSessionKey---->"+encryptedSessionKey);
                    sessionKeyDetails = SessionKeyDetails
                            .createSkeyToInitializeSynchronizedKey(
                                    synchronizedKey.getKeyIdentifier(),
                                    encryptedSessionKey);
                    
                    System.out.println("10----sessionKeyDetails---->"+sessionKeyDetails);
                    
                } catch (Exception e) {
                    //Log.e("session Exception","-->"+e);
                    System.out.println(e.getMessage());
                }

                /**
                 * ******************************************************
                 */
                /**
                 * *******************NEW for data and
                 * hmac***********************************
                 */
                //byte[]  xmlPidBytes = getPidXml("",bio,pi, fpImgString , otp_check , otp).getBytes();
                byte[] cipherTextWithTS = null;
                byte[] encSrcHash = null;
                uidai.HashGenerator hashgenerator = new uidai.HashGenerator();

                try {
                    cipherTextWithTS = encrypter.encrypt(pid, sessionKey, pidTimeStamp);
                    byte[] iv = encrypter.generateIv(pidTimeStamp);
                    byte[] aad = encrypter.generateAad(pidTimeStamp);
                    byte[] srcHash = encrypter.generateHash(pid);
                    encSrcHash = encrypter.encryptDecryptUsingSessionKey(true, sessionKey, iv, aad, srcHash);
                    byte[] decryptedText = encrypter.decrypt(cipherTextWithTS, sessionKey, encSrcHash);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                byte[] hmac = hashgenerator.generateSha256Hash(pid);
                String encryptedHmac = null;
                String encdata = null;

                try {
                    //encdata = Base64.encodeToString(encrypter.encryptUsingSessionKey(syncSessionKey, xmlPidBytes), Base64.DEFAULT);
                    //encdata = Base64.encodeToString(cipherTextWithTS, 0);
                    encdata = new String(Base64.encode(cipherTextWithTS));
                    //encryptedHmac = Base64.encodeToString(encrypter.encryptUsingSessionKey(syncSessionKey, hmac),
                    //encryptedHmac = Base64.encodeToString(encSrcHash, 0);
                    encryptedHmac = new String(Base64.encode(encSrcHash));
                } catch (Exception ex) {
                    System.out.println("Error:" + ex.getMessage());
                }
                /**
                 * **********************************************************
                 */

                /* -------------- <skey> starts here ------------------- */
                serializer.startTag("", "Skey");
                serializer.attribute("", "ci", encrypter.getCertificateIdentifier());
                serializer.text(new String(Base64.encode(sessionKeyDetails.getSkeyValue())));
                serializer.endTag("", "Skey");
                /* -------------- </skey> closes here ------------------- */

 /* -------------- <data> starts here ------------------- */
                serializer.startTag("", "Data");
                serializer.attribute("", "type", "X");
                serializer.text(encdata);
                serializer.endTag("", "Data");
                /* -------------- </data> closes here ------------------- */

 /* -------------- <hmac> starts here ------------------- */
                serializer.startTag("", "Hmac");
                serializer.text(encryptedHmac);
                serializer.endTag("", "Hmac");
                /* -------------- </hmac> closes here ------------------- */

                serializer.endTag("", "Auth");
                serializer.endDocument();
            }

        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return writer.toString();

    }
    
    
    
    //XML FOR OTP
    
    

    
    
    

    private byte[] createPID(String name, String gender, String dob, String mobile) throws ParseException {
    	System.out.println("5---Create PID");
        StringWriter writer = new StringWriter();
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //DateFormat dfm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        now = System.currentTimeMillis();
        Date date = new Date(now);
        pidTimeStamp = dfm.format(date);
        String timestamp = dfm.format(date).replace(" ", "T");
        // String timestamp1 = dfm1.format(date).replace(" ", "T");
        // newPidTime = timestamp1.substring(timestamp1.lastIndexOf("T") + 1);    
        // System.out.println("in milliseconds: " +now);

        startTime = System.nanoTime();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlSerializer serializer = factory.newSerializer();
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);

            /* -------------- <Pid> starts here ------------------- */
            serializer.startTag("", "Pid");

            /* These are pid xml attributes */
            serializer.attribute("", "ts", timestamp);
            serializer.attribute("", "ver", "2.0");
            serializer.attribute("", "wadh", "");

            /* -------------- <Demo> starts here ------------------- */
            serializer.startTag("", "Demo");
            //serializer.attribute("", "lang", "06");

            /* -------------- <Pi> starts here ------------------- */
            serializer.startTag("", "Pi");
            serializer.attribute("", "ms", "E");
            serializer.attribute("", "mv", "100");
            // if(!name.trim().isEmpty()){
            serializer.attribute("", "name", name);
            //  }
            //serializer.attribute("", "lname", "");
            //serializer.attribute("", "lmv", "");

            // Comment by prateek
            if (gender != "") {
                serializer.attribute("", "gender", gender);
            }
            if (dob != "") {
                serializer.attribute("", "dob", dob);
            }

            //serializer.attribute("", "dobt", "");
            //serializer.attribute("", "age", "");
            if(mobile != ""){
            serializer.attribute("", "phone", mobile);
            }
            //serializer.attribute("", "email", "");
            serializer.endTag("", "Pi");
            /* -------------- <Pi> ends here ------------------- */

            serializer.endTag("", "Demo");
            /* -------------- <Demo> ends here ------------------- */

            serializer.endTag("", "Pid");
            /* -------------- <Pid> ends here ------------------- */
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("PID Create");
        System.out.println("\n\nPid XML: " + writer.toString() + "\n\n");
        _pid_string=writer.toString();
        return writer.toString().getBytes();
    }
    
    
    
    

    public String getXMLText(String inxml, String tag) {
        String outstr = "";
        if (inxml.contains("<" + tag + ">") && inxml.contains("</" + tag + ">")) {
            outstr = inxml.substring(inxml.indexOf("<" + tag + ">") + (tag.length() + 2), inxml.indexOf("</" + tag + ">"));

        }
        return outstr;
    }

    public String getXMLText(String inxml, String tag, int addIndex) {
        String outstr = "";
        if (inxml.contains("<" + tag) && inxml.contains("</" + tag + ">")) {
            outstr = inxml.substring(inxml.indexOf("<" + tag) + (tag.length() + 1 + addIndex), inxml.indexOf("</" + tag + ">"));
        }
        return outstr;
    }

    public Map<String, String> getXMLAttribute(String inxml) {
        Map<String, String> result = new HashMap<String, String>();

        result.put("rdsId", "");
        result.put("rdsVer", "");
        result.put("dpId", "");
        result.put("dc", "");
        result.put("mi", "");
        result.put("mc", "");
        result.put("ci", "");
        result.put("srno", "");

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(inxml));

            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    //  DebugMsgNic("Start document");
                } else if (eventType == XmlPullParser.END_DOCUMENT) {
                    //   System.out.println("End document"); 
                } else if (eventType == XmlPullParser.START_TAG) {
                    //DebugMsgNic("Start tag "+xpp.getName());  
                    for (int i = 0; i < xpp.getAttributeCount(); i++) {
                        result.put(xpp.getAttributeName(i), xpp.getAttributeValue(i));
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    // DebugMsgNic("End tag "+xpp.getName());           
                } else if (eventType == XmlPullParser.TEXT) {
                    //  DebugMsgNic("Text "+xpp.getText()); 
                }
                eventType = xpp.next();
            }
        } catch (Exception ex) {
            //  System.out.println("Error: " + ex.getMessage());    
        }
        return result;
    }

}
