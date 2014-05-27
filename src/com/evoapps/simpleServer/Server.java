package com.evoapps.simpleServer;

import java.io.PrintStream;

import org.simpleframework.http.Part;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

import com.evoapps.lucene.SearchFiles;
//import org.simpleframework.transport.Server;

public class Server implements Container {

   String webpage;
   SearchFiles searchEngine;
   
   Server(){
	   searchEngine = new SearchFiles();
   }
   
   
   
   public void handle(Request request, Response response) {
      
	   
	   try {
		
		 webpage = request.getAddress().toString();
		 System.out.println("webpage>>"+webpage);
		 
		 
         PrintStream body = response.getPrintStream();
         long time = System.currentTimeMillis();
         
         Part searchPart = request.getPart("search");
         String searchterm = null;
         if(searchPart!=null){
        	 searchterm = searchPart.getContent();
         }
         
         
         
         //  String type = request.getPart("type").getContent();
         String page = null;
      //   if(type==null){
        	 page = new MainPage().createPage(webpage, searchterm);
        // }
         /*if(Type!=null){
        	 System.out.println(Type); 
         }else{
        	 System.out.println("type is null"); 
         }
         switch(Integer.parseInt(Type)){
         	
         	case 1:
         		//show main page
         		break;
         		
         	case 2:
         		//show search results page
         		break;
         		
         	case 3:
         		//show recommended results
         		break;
         
         }*/
        
         response.setValue("Content-Type", "text/xml");
         response.setValue("Server", "HelloWorld/1.0 (Simple 4.0)");
         response.setDate("Date", time);
         response.setDate("Last-Modified", time);
   
         if(page != null){
        	 body.println(page); 
         }
         
         body.close();
      
	   } catch(Exception e) {
         e.printStackTrace();
      }
   } 
   
   
}