package com.evoapps.rankingsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import com.evoapps.database.DatabaseHandler;
import com.evoapps.datastructure.PubSim;
import com.evoapps.datastructure.Publication;

public class RankingSystem {
	
		HashMap<String, ArrayList> neighMap = new HashMap<String, ArrayList>();
		String filePathNeigh = "pubNeighComm4.csv";
		DatabaseHandler dbhandler;
		
		public RankingSystem() {
			// TODO Auto-generated constructor stub
			dbhandler = new DatabaseHandler("acda_bfs");
		}
		
		void initNeighMap(){
			File file = new File(filePathNeigh);
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				String[] pubArray = null;
				ArrayList listPub = new ArrayList();
				
				while((line=reader.readLine()) != null){
					pubArray = line.split("\t");
					if(pubArray!=null){
						listPub = new ArrayList(); 
						for(int i=1; i<pubArray.length;i++){
							listPub.add(pubArray[i]);
						}
						neighMap.put(pubArray[0], listPub);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		ArrayList<Publication> getPubList(String id){
			
				ArrayList<Publication> pubList = new ArrayList<Publication>();
				
				//get the cluster in which string belongs to 
				// add some function in the database handler to get he cluster id
				int clusterId = dbhandler.getClusterId(Integer.parseInt(id));
				
				//get all the other publication in that cluster
				// add function in the database handler that can return the publication
				// for publication which are in given cluster
				ArrayList<String> listPub = dbhandler.getClustPub(clusterId);
				
				//find the similarity between the pub and given pub
				//Run the function to find the simillarity between given publication
				//and publication in the cluster
				ArrayList<PubSim> simList = getSimilarityList(id, listPub);
				
				
				//sort the publication based on their simillarity score
				//function to sort the publication based on their similarity score
				Collections.sort(simList, new Comparator<PubSim>() {

					@Override
					public int compare(PubSim pub1, PubSim pub2) {
						// TODO Auto-generated method stub
						if(pub1.getSim() > pub2.getSim()){
							return 1;
						}else if(pub1.getSim() < pub2.getSim()){
							return -1;
						}else{
							return 0;
						}
						
					}
				});
				
				//get the title for top 20 publication
				//for top 20 publication get their title and authors
				//add the method in database handler to do it
				
				//return the list back to calling function
				//return
				return pubList;
		}
		
		ArrayList<PubSim> getSimilarityList(String id, ArrayList<String> listPub){
			ArrayList<PubSim> simList = new ArrayList<PubSim>();
			
			Iterator<String> it = listPub.iterator();
			String curr;
			while(it.hasNext()){
				curr = (String)it.next();
				simList.add(new PubSim(id, curr, comPubSim(id, curr)));
			}
			
			return simList;
		}
		
		float comPubSim(String pub1, String pub2){
			ArrayList pub1List = neighMap.get(pub1);
			ArrayList pub2List = neighMap.get(pub2);
			
			HashSet<String> pub1set = new HashSet<String>(pub1List);
			HashSet<String> pub2set = new HashSet<String>(pub2List);
			
			HashSet<String> intersection = pub1set;
			HashSet<String> union = pub1set;
			
			intersection.retainAll(pub2set);
			union.addAll(pub2set);
			
			float sim = ((float)intersection.size())/(float)(union.size());
			System.out.print("sim>>"+pub1+">>"+pub2+">>"+sim);
			return 0;
		}
		

}
