package com.evoapps.simpleServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.evoapps.datastructure.Publication;
import com.evoapps.lucene.SearchFiles;

public class MainPage {
	
		SearchFiles search;
		
		MainPage(){
			search = new SearchFiles();
		}
		
		String createPage(String webpage, String searchterm){
			
			
			ArrayList<Publication> list = null;
			String pageName = webpage.substring(1);
			System.out.println("pageName>>"+ pageName);
			if(pageName.equals("searchresult.html") && searchterm != null){
				try {
					list =  search.search(searchterm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				StringBuilder builder = new StringBuilder();
				
				Iterator it = null;
				if(list!=null){
					it = list.iterator();
				}
				
				builder.append("<html>" +
						"<head>" +
						"<h1>Search Result: "+searchterm+"</h1>" +
						"</head>" +
						"<body>");
				
				builder.append("<ol>");
				while(it.hasNext()){
					Publication pub = (Publication)it.next();
					builder.append("<li>"+pub.getId()+">>"+pub.getTitle()+"</li>");
				
				}
				builder.append("</ol>");
				builder.append("</body></html>");
				return builder.toString();
			}
			return readFromHtmlTemplate(pageName);
		}
		
		String readFromHtmlTemplate(String fileName){
			
			File file = new File(fileName);
			if(file.exists()){
				StringBuilder htmlPage = new StringBuilder();
				try {
					BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
					String line;
					while((line=br.readLine())!=null){
						htmlPage.append(line);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return htmlPage.toString();
			}else{
				return "<h1>Error 404</h1>";
			}
			
		}

}
