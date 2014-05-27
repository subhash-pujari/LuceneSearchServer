package com.evoapps.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class DatabaseHandler {
		
		static Connection connect;
		public DatabaseHandler(String databaseName) {
			// TODO Auto-generated constructor stub
			try {
				connect = DriverManager
					      .getConnection("jdbc:mysql://localhost/"+databaseName+"?"
						          + "user=tiger&password=&dontTrackOpenResources=true");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public int getClusterId(int pub){
			int cluster = -1;
			try {
				String sql = "select clust from ClusterPub where pub = "+pub+";";
				Statement statement = connect.createStatement();
				ResultSet result = statement.executeQuery(sql);
				if(result!=null){
					boolean isElement = result.first();
					if(isElement){
						cluster = result.getInt("clust");
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return cluster;
		}
		
		public ArrayList<String> getClustPub(int clust){
			ArrayList<String> listClustPub = new ArrayList<String> ();
			String sql = "select pub from ClusterPub where clust = "+clust+";";
			Statement statement;
			ResultSet result = null;
			
			try {
				statement = connect.createStatement();
				result = statement.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(result == null){
				return null;
			}
			try {
				while(result.next()){
						listClustPub.add(String.valueOf(result.getInt("pub")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return listClustPub;
		}

}
