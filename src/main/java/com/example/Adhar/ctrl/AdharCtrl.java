package com.example.Adhar.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Adhar.repository.AadharHistoryRepository;
import com.example.Adhar.repository.AadharTxnHistoryRepository;
//import com.example.MOERADTEACHER.modal.TeacherAwards;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import aadhar_util.VerificationAction;



@Controller
public class AdharCtrl {
	
	@Autowired
	AadharHistoryRepository aadharHistoryRepository;
	
	@Autowired
	AadharTxnHistoryRepository aadharTxnHistoryRepository;
	

	@RequestMapping(value = "/authCheckByAadharAndName", method = RequestMethod.POST)
	public ResponseEntity<?> authCheckByAadharAndName(@RequestBody String data) throws Exception {
		System.out.println("Called");
		Map<String,String> resultObj = null;
		ObjectMapper mapperObj = new ObjectMapper();
		Map<String,String> dataObj=new HashMap<String,String>();
		try {
			dataObj = mapperObj.readValue(data, new TypeReference<Map<String,String>>() {
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
	        String res = null;
	        VerificationAction vn = new VerificationAction();
	        resultObj = vn.AadhaarResult2PToken(dataObj.get("uidai"), dataObj.get("name"),dataObj.get("studentid"),aadharHistoryRepository,aadharTxnHistoryRepository);
//	        res = vn.AadhaarResult2PToken("490662686515", "Sarvendra Kumar Tarun");
//	        System.out.println("Complate");
//	        VerificationAction vn = new VerificationAction();
//	        res = vn.AadhaarResult("999971658847", "Kumar Agarwal", "04-05-1978","M");
	        
	        System.out.println("res---->"+res);
	        
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
		return ResponseEntity.ok(resultObj);
	}
	
	
	@RequestMapping(value = "/authCheckByAadharAndNameAnddobAndGender", method = RequestMethod.POST)
	public ResponseEntity<?> authCheckByAadharAndNameAnddobAndGender(@RequestBody String data) throws Exception {
		System.out.println("Called");
		Map<String,String> resultObj = null;
		ObjectMapper mapperObj = new ObjectMapper();
		Map<String,String> dataObj=new HashMap<String,String>();
		try {
			dataObj = mapperObj.readValue(data, new TypeReference<Map<String,String>>() {
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
	        String res = null;
	        VerificationAction vn = new VerificationAction();
	        resultObj = vn.AadhaarResult(dataObj.get("uidai"), dataObj.get("name"),dataObj.get("dob"), dataObj.get("gen"),aadharHistoryRepository,aadharTxnHistoryRepository);
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
		return ResponseEntity.ok(resultObj);
	}
	
	@RequestMapping(value = "/authCheckByAadharAndNameAndGender", method = RequestMethod.POST)
	public ResponseEntity<?> authCheckByAadharAndNameAndGender(@RequestBody String data) throws Exception {
		System.out.println("Called");
		Map<String,String> resultObj = null;
		ObjectMapper mapperObj = new ObjectMapper();
		Map<String,String> dataObj=new HashMap<String,String>();
		try {
			dataObj = mapperObj.readValue(data, new TypeReference<Map<String,String>>() {
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
	        String res = null;
	        VerificationAction vn = new VerificationAction();
	        resultObj = vn.AadhaarResultG(dataObj.get("uidai"), dataObj.get("name"), dataObj.get("gen"),aadharHistoryRepository,aadharTxnHistoryRepository);
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
		return ResponseEntity.ok(resultObj);
	}
	
	
//	AadhaarResult(String aadhaar, String n, String dobirth, String gen,
	
	
}
