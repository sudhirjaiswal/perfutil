package com.rjil.perf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JTLParser {
	
	public void run() throws IOException
	{
		String jtl="C:/Users/sudhir.jaiswal/Downloads/16k HA beconn 500/Tej_SingleFileUpload_RateLimit_03_17_2017_07_32_36_LG1.jtl";
		BufferedReader br = null;
		String line = "";
		double esum=0, lsum = 0, isum = 0, csum =0;
		double ecount=0, lcount = 0, icount = 0, ccount = 0;
        try {
        	
        	br = new BufferedReader(new FileReader(jtl));
        	br.readLine();
        	try {
        	  while ((line = br.readLine()) != null) {
                        // use comma as separator
                    String[] elapsed = line.split(",");
                    String[] Latency = line.split(",");
                    String[] IdleTime = line.split(",");
                    String[] Connect = line.split(",");
                    double elap=Double.parseDouble(elapsed[1]);
                    double lat=Double.parseDouble(Latency[15]);
                    double idle=Double.parseDouble(IdleTime[18]);
                    double con=Double.parseDouble(Connect[19]);
                    esum=esum + elap;
                    ecount++;
                    lsum=lsum + lat;
                    lcount++;
                    isum=isum + idle;
                    icount++;
                    csum=csum + con;
                    ccount++;
                    //System.out.println("Elapsed Time: " + elapsed[1]+" Latency: " + Latency[15]+" IdleTime: " + IdleTime[18]+" Connect Time: " + Connect[19]);
                }
            } catch (IOException e) {
                System.out.println("NA");
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double aet = esum/ecount;
        double alat = lsum/lcount;
        double aidle = isum/icount;
        double acon = csum/ccount;
        System.out.println("Total Records :"+ecount);
        System.out.println("Average Elapsed Time : "+aet+" ms");
        System.out.println("Average Latency : "+alat+" ms");
        System.out.println("Average IdleTime : "+aidle+" ms");
        System.out.println("Average Connect Time : "+acon+" ms");
      }

	public static void main(String[] args) {
		JTLParser jp = new JTLParser();
		try {
			jp.run();
			//jp.calcPercentiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
