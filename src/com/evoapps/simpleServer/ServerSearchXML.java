package com.evoapps.simpleServer;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.datatype.XMLGregorianCalendar;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

import com.evoapps.datastructure.Publication;
import com.evoapps.lucene.SearchFiles;


public class ServerSearchXML implements Container{

	SearchFiles search;

	
	public ServerSearchXML() {
		// TODO Auto-generated constructor stub
		search = new SearchFiles();
	}
	
	@Override
	public void handle(Request req, Response resp) {
		// TODO Auto-generated method stub
		
		String webpage = req.getAddress().toString();
		webpage = webpage.substring(1);
		System.out.println("webpage>>"+webpage);
		
		ArrayList<Publication> pubList = null;
		PrintStream body = null;
		System.out.println("search" + req.getParameter("search"));
		try {
			pubList = search.search(req.getParameter("search"));
			body = resp.getPrintStream();
			Iterator it = pubList.iterator();
			
			/*while(it.hasNext()){
				Publication pub = (Publication)it.next();
				System.out.println("\t title:"+ pub.getTitle()+ "\t id:"
				+pub.getId() + "\n");
			
			}*/
			
		} catch (IOException e){
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XMLGenerator gen = new XMLGenerator();
		String xml = gen.generateXml(pubList);
		//System.out.println("xml>>" + xml);
		long time = System.currentTimeMillis();
		
		resp.setValue("Content-Type", "text/xml");
		resp.setValue("Server", "HelloWorld/1.0 (Simple 4.0)");
		resp.setDate("Date", time);
		resp.setDate("Last-Modified", time);
		body.println(xml);
		body.close();
	}

	
		
	class XMLGenerator{
		
		StringBuilder builder;
		public XMLGenerator(){
			builder = new StringBuilder();
		}


		public String generateXml(ArrayList<Publication> list){
			
				builder.append(getRootTag(true));
			
				Iterator<Publication> it = list.iterator();
		
				while(it.hasNext()){
					
					builder.append(getPubTag(true));
					Publication pub = (Publication)it.next();
					String id = pub.getId();
					String title = pub.getTitle();
					builder.append(getIdTag(id));
					builder.append(getTitleTag(title));
					builder.append(getPubTag(false));
				}
				
				builder.append(getRootTag(false));
				return builder.toString();
		}
		
		String getTitleTag(String title){
			return "<title>"+title + "</title>";
		}
		
		String getIdTag(String id){
			return "<id>" + id + "</id>";
		}
		
		String getRootTag(boolean isStart){
			if(isStart){
				return "<result>";
			}else{
				return "</result>";
			}
		}
		
		String getPubTag(boolean isStart){
			if(isStart){
				return "<pub>";
			}else{
				return "</pub>";
			}
		}
	}

}
