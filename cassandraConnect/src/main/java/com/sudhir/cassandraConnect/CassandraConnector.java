package com.sudhir.cassandraConnect;

import java.net.InetAddress;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraConnector
{
	   private Cluster cluster;  
	   private Session session;  
	   public void connect(final String node, final int port)  
	   {  
	      this.cluster = Cluster.builder().addContactPoint(node).withPort(port).withCredentials("cassandra", "cassandra").build();  
	      final Metadata metadata = cluster.getMetadata();  
	      String clustername =metadata.getClusterName();
	      System.out.println("Connected to cluster: "+clustername);  
	      for (final Host host : metadata.getAllHosts())  
	      {  
	    	  String dC=host.getDatacenter();
	    	  InetAddress host1=host.getAddress();
	    	  String rack=host.getRack();
	    	  System.out.println("Datacenter: "+dC+"; Host: "+host1+"; Rack:"+rack);  
	      }  
	      session = cluster.connect("boards");  
	   }  

	   public void cqlquery(String cql)
	   {  
		   final ResultSet results = session.execute(cql);
		   for (Row row : results) {
		        System.out.println(row.toString());
		    }
		   /*final ResultSet results = session.execute(cql);
		   final Row movieRow = results.one();
		   System.out.println(movieRow);*/
	   }
	   
	   public Session getSession()  
	   {  
	      return this.session;  
	   }  
	  
	   public void close()  
	   {  
	      cluster.close();  
	   } 
	   
	   public static void main(String[] args)  
	   {  
	      final CassandraConnector client = new CassandraConnector();  
	      final String ipAddress = args.length > 0 ? args[0] : "172.0.0.1";  
	      final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
	      System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");  
	      client.connect(ipAddress, port);
	      client.cqlquery("select * from board_object_mapping where board_key ='0f54286096da11e7bc933bb56cbd0cf3' and object_key='9aa15ed070c14702ac4781985f2ec7d1';");
	      client.close();  
	   }  
}