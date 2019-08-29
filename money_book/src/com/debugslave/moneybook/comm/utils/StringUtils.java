package com.debugslave.moneybook.comm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class StringUtils {
	
	public static String getRandomString(int len) {
		
		if (len > 0) {
			StringBuffer sb = new StringBuffer();
			Random rnd = new Random();
			for (int i = 0; i < len; i++) {
			    int rIndex = rnd.nextInt(3);
			    switch (rIndex) {
			    case 0:
			        // a-z
			    	sb.append((char) ((int) (rnd.nextInt(26)) + 97));
			        break;
			    case 1:
			        // A-Z
			    	sb.append((char) ((int) (rnd.nextInt(26)) + 65));
			        break;
			    case 2:
			        // 0-9
			    	sb.append((rnd.nextInt(10)));
			        break;
			    }

			}
			return sb.toString();
		} else {
			return "";
		}
		
	}
	
	public static String getSHA256(String decrytedStr) {
		
		String encryptedStr = "";
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			StringBuffer sb = new StringBuffer(); 

			sh.update(decrytedStr.getBytes()); 

			
			byte byteData[] = sh.digest();
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}

			encryptedStr = sb.toString();

		}catch(NoSuchAlgorithmException e){
			encryptedStr = ""; 
		}


		
		return encryptedStr;
	}
	
}
