package com.fv.shiftreport.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import com.fv.shiftreport.model.SynchData;

public class Util {
	private static final String currentDir = System.getProperty("user.dir");
	
	public static String macAddress;
	
	public static String getDisplayDate(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(Calendar.getInstance().getTime());
	}
	
	public static String convertToReadableDate(Date date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(date.getTime());
	}
	
	public static String fileToString(File file){
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = in.readLine()) != null) {
				sb.append(str + "\n ");
			}
			in.close();
			return sb.toString();
		} catch (Exception e) {
			writeToFile("Error", e.getMessage());
		} 
		return null;
	}

    public static void writeToFile(String fileName, String message){
    	try {
    		if(null!=message){
    			File file = new File(currentDir+"\\"+fileName+getDisplayDate()+".txt");
    			if (!file.exists()) {
    				file.createNewFile();
    			}

    			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
    			BufferedWriter bw = new BufferedWriter(fw);
    			bw.write(message+"\r\n");
    			bw.close();
    		}
		} catch (IOException e) {
			writeToFile("error", e.getMessage());
		}
    }
    
    public static String fixCsvString(String value){
    	if(value.contains(",")){
    		value = "\""+value+"\"";
    	}
    	return value;
    }
    
    public static boolean isNotEmpty(String value){
    	if(null!=value){
    		return !value.trim().isEmpty();
    	}
    	return false;
    }
    public static boolean isEmpty(String value){
    	return !isNotEmpty(value);
    }
    
    public static Double toDouble(Object value){
    	if(value==null || value.toString().isEmpty()){
    		return 0.00;
    	}
    	DecimalFormat df = new DecimalFormat("#.###");      
    	return Double.valueOf(df.format(Double.valueOf(value.toString().replace(",", ""))));
    }
    
    public static String toString(Object value){
    	if(value==null){
    		return "";
    	}
    	return String.valueOf(value);
    }
    
    public static Double addToDouble(String value1, String value2){
    	return toDouble(value1)+toDouble(value2);
    }
    
    public static String getMacAddress() throws UnknownHostException, SocketException{
    	StringBuilder sb = new StringBuilder();
    	for(Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();){
    		NetworkInterface ni = e.nextElement();
    		if(ni.getHardwareAddress()!=null){
    			for (int i = 0; i < ni.getHardwareAddress().length; i++) {
    				sb.append(String.format("%02X%s", ni.getHardwareAddress()[i], (i < ni.getHardwareAddress().length - 1) ? "" : ""));
    			}
    			if(sb.length()>0){
    				break;
    			}
    		}
    	}	
    	return sb.toString();
    }
    
    public static void main(String args[]) throws UnknownHostException, SocketException{
    	System.out.println(getMacAddress());
    }
    
    public static void exportTransaction(SynchData synchData) throws IOException{
    	 FileOutputStream fout=new FileOutputStream("data");  
    	 ObjectOutputStream out=new ObjectOutputStream(fout);  
    	 out.writeObject(synchData);  
    	 out.flush(); 
    	 out.close();
    	
    }
    
    @SuppressWarnings("unchecked")
     public static SynchData importTransaction() throws IOException, ClassNotFoundException{
   	 FileInputStream fin = new FileInputStream("data");  
   	 ObjectInputStream in =new ObjectInputStream(fin);  
   	 SynchData synchData = (SynchData)in.readObject();  
   	 in.close();
   	 return synchData;
   	
   }
}
