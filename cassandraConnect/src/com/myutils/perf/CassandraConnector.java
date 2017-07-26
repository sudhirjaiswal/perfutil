package com.sudhir;

import com.datastax.driver.core.Cluster;  
import com.datastax.driver.core.Host;  
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;   
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;

public class CassandraConnector  
{  
   private Cluster cluster;  
   private Session session;  
 
   public void connect(final String node, final int port)  
   {  
      this.cluster = Cluster.builder().addContactPoint(node).withPort(port).withCredentials("tejrousr", "tejrousr").build();  
      /*final Metadata metadata = cluster.getMetadata();  
      System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());  
      for (final Host host : metadata.getAllHosts())  
      {  
         System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",  
            host.getDatacenter(), host.getAddress(), host.getRack());  
      }  */
      session = cluster.connect();
  }  

   public Session getSession()  
   {  
      return this.session;  
   }  
  
   public void close()  
   {  
      cluster.close();  
   }  
      
   public void writecsv(String path,String txt){
		try {
			FileWriter writer = new FileWriter(path,true);
			BufferedWriter out = new BufferedWriter(writer);
			 out.write(txt);
		 	 out.write('\n');
		 	out.flush();
		  	   out.close();
		  	   writer.close(); 
		}
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}	
	}
   
   public String readFile(String filename)
   {
       String content = null;
       File file = new File(filename);
       FileReader reader = null;
       try {
           reader = new FileReader(file);
           char[] chars = new char[(int) file.length()];
           reader.read(chars);
           content = new String(chars);
           reader.close();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if(reader !=null){try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
       }
       return content;
   }
   
   public static void main(final String[] args) throws InterruptedException  
   {  
     
      final String ipAddress = "172.23.80.213";  
      final int port = 9042;
      String userfile ="C:/Users/sudhir.jaiswal/Desktop/Boards/new/Boards720Users.csv";
      String path = "C:/Users/sudhir.jaiswal/Desktop/Boards/new/Boards720Userscount.csv";
      //System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "..."); 
      Logger.getRootLogger().setLevel(Level.OFF);
      final CassandraConnector cc = new CassandraConnector(); 
      FileReader reader;
      try {
    	  
		reader = new FileReader(userfile);
		 String thisLine;
	       BufferedReader JTL_content = new BufferedReader(reader); 
	       int variable1 = 0;
	       while ((thisLine = JTL_content.readLine()) != null){
	    	    
	     	  cc.connect(ipAddress, port); 
	    	   String cql = "select count(board_key) from boards.user_board_mapping where user_id= '"+thisLine+"' ALLOW FILTERING;"; 
	    	   ResultSet ReS=cc.getSession().execute(cql);
	    		  Row row = ReS.one();
	    		  long expected = row.getLong(0);
	    		  String exp=String.valueOf(expected);
	    		  String v1=thisLine+","+exp;
				cc.writecsv(path, v1);
				System.out.println(v1);
				cc.close(); 
				if(variable1==50)
				{
					TimeUnit.SECONDS.sleep(5);
					variable1=0;
				}
				else
				{variable1=variable1+1;}
      }
	
	       JTL_content.close(); } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
           //String [] pan=thisLine.split(",");
     
     
	  //System.out.println(expected);
	  /*while(ReS.iterator().hasNext())				// for all rows but on one column
	  {
		  Row R1 = ReS.iterator().next();
		  String v1 = (String)R1.getObject("object_key");
		  
		  
	  }*/
	  
      /*String boardKey=null;
      String objectKey=null;
      String createdBy=null;

      for (Row row : ReS) {
    	  boardKey=row.getString("board_key");
    	  objectKey=row.getString("object_key");
    	  createdBy=row.getString("created_by");
      String AM = boardKey+","+objectKey+","+createdBy;
      System.out.println(AM);
      cc.writecsv(path, AM);
       }*/
       
   }
}