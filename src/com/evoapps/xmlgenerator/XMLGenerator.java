package com.evoapps.xmlgenerator;

import java.util.ArrayList;
import java.util.Iterator;

import com.evoapps.datastructure.Publication;

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
		return "<title>"+title+"</title>";
	}
	
	String getIdTag(String id){
		return "<id>"+id+"</id>";
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
	
	public static void main(String[] args){
		ArrayList<Publication> list = new ArrayList<Publication>();
		
		list.add(new Publication("subhash", "89"));
		list.add(new Publication("pujari", "90"));
		XMLGenerator gen = new XMLGenerator();
		System.out.println(gen.generateXml(list));
	}
}
