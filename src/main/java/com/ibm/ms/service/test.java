package com.ibm.ms.service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class test {
	 public static void exeCmd(String commandStr) {  
	        BufferedReader br = null;  
	        try {  
	            Process p = Runtime.getRuntime().exec(commandStr);  
	            br = new BufferedReader(new InputStreamReader(p.getInputStream(),Charset.forName("GBK")));  
	            String line = null;  
	            StringBuilder sb = new StringBuilder();  
	            while ((line = br.readLine()) != null) {  
	                sb.append(line + "\n");  
	                System.out.println(sb.toString());
	            }  
	            
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (br != null){  
	                try {  
	                    br.close();  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	    }  
	  
	    public static void main(String[] args) {  
	        String commandStr = "yo microservice";  
	        test.exeCmd(commandStr);
	    }  
}
