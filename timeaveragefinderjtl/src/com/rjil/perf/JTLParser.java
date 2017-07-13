package com.rjil.perf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JTLParser {
	
	public void run() throws IOException
	{
<<<<<<< HEAD
		String jtl="C:/Users/sudhir.jaiswal/Downloads/test.jtl";
=======
		String jtl="jtl file path";
>>>>>>> 0c7cb53fe4c03e41b315385683d9d915c7b71d31
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
                    double lat=Double.parseDouble(Latency[12]);
                    double idle=Double.parseDouble(IdleTime[13]);
                    double con=Double.parseDouble(Connect[14]);
                    esum=esum + elap;
                    ecount++;
                    lsum=lsum + lat;
                    lcount++;
                    isum=isum + idle;
                    icount++;
                    csum=csum + con;
                    ccount++;
                    System.out.println("Elapsed Time: " + elapsed[1]+" Latency: " + Latency[12]+" IdleTime: " + IdleTime[13]+" Connect Time: " + Connect[14]);
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
