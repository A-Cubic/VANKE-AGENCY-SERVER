package org.wanke.com.common;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class Util {
	public static String createUuid() {
		String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
		return uuid;
	}

	public static String getMD5(String str) {
		return Hashing.md5().newHasher().putString(str, Charsets.UTF_8).hash().toString();
	}
	
	 public static String getMD5Digest(String inStr) {
	        MessageDigest md5 = null;
	        try {
	            md5 = MessageDigest.getInstance("MD5");
	        } catch (Exception e) {

	            e.printStackTrace();
	            return "";
	        }
	        char[] charArray = inStr.toCharArray();
	        byte[] byteArray = new byte[charArray.length];

	        for (int i = 0; i < charArray.length; i++)
	            byteArray[i] = (byte) charArray[i];

	        byte[] md5Bytes = md5.digest(byteArray);

	        StringBuffer hexValue = new StringBuffer();

	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16)
	                hexValue.append("0");
	            hexValue.append(Integer.toHexString(val));
	        }

	        return hexValue.toString();
	    }
	
    public final static String formatUnixDateToDate(long unixDate){
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sd.format(new Date(unixDate));
    }

    public final static String formatDateToString(Date date,String format){
    	SimpleDateFormat sd = new SimpleDateFormat(format);
    	return sd.format(date);
    }
    
    public final static String formatDateToDay(){
    	Date date = new Date();
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    	return sd.format(date);
    }
    
    public final static String formatDateToYestoday(){
    	Date date = new Date();
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    	return sd.format(new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000));
    }
    
    public final static long formatDateToUnixDate(String date){
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long reDate = 0l;
    	try {
			reDate = sd.parse(date).getTime();
			System.out.println(reDate);
			System.out.println("test:git");
		} catch (ParseException e) {
			 e.printStackTrace();
		}
    	return reDate;
    }
}
