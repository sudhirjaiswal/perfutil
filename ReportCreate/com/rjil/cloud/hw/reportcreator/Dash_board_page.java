package com.rjil.cloud.hw.reportcreator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dash_board_page
{
  public static String Result_folder1;
  public static String TestReport_name;
  public static String Jmeterhtmlreport;
  public static String LoadPatterninfo;
  public static String Add_info;
  
  public Dash_board_page() {}
  
  public void main_page_init(String rpath, String opath, String rfolder, String lpat, String apat)
  {
    Jmeterhtmlreport = rpath;
    Result_folder1 = opath;
    TestReport_name = rfolder;
    LoadPatterninfo = lpat;
    Add_info = apat;
    main_page_part1();
  }
  
  public void main_page_part1() {
    html_add_content pop = new html_add_content();
    pop.Rowfile_add(Result_folder1 + File.separator + TestReport_name, "index.php", "Dashpart1.txt");
    pop.Rowfile_add(Result_folder1 + File.separator + TestReport_name, "myprocessingscript.php", "phppage.txt");
    pop.Rowfile_add(Result_folder1 + File.separator + TestReport_name, "download1.php", "phppage1.txt");
    main_page_part2();
  }
  
  public String result_folder(String path) {
    String foldername = "";
    try {
      FileReader reader = new FileReader(path);
      
      BufferedReader row_file = new BufferedReader(reader);
      
      int counter = 0;int t1 = 0;
      String v1 = "";
      String thisLine; while ((thisLine = row_file.readLine()) != null) { String thisLine;
        counter++;
        if (thisLine.contains("File:")) {
          t1 = counter + 1;
        }
        if (counter == t1)
        {
          v1 = thisLine;
        }
      }
      row_file.close();
      reader.close();
      v1 = v1.trim();
      v1 = v1.substring(4, v1.length() - 5);
      v1 = v1.replace("\"", "");
      v1 = v1.replace(".jtl", "");
      System.out.println(v1);
      foldername = v1;

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    return foldername;
  }
  
  public void main_page_part2() {
    String v1 = "";String v2 = "";String v3 = "";String v4 = "";
    html_add_content pop = new html_add_content();
    
    try
    {
      FileReader reader = new FileReader(Jmeterhtmlreport + File.separator + "index.html");
      BufferedReader row_file = new BufferedReader(reader);
      

      int t1 = 0;int t2 = 0;int t3 = 0;int t4 = 0;
      int counter = 0;
      String thisLine; while ((thisLine = row_file.readLine()) != null) { String thisLine;
        counter++;
        if (thisLine.contains("File:")) {
          t1 = counter;
        }
        
        if (thisLine.contains("Start Time:")) {
          t2 = counter;
        }
        if (thisLine.contains("End Time:")) {
          t3 = counter;
        }
        if (thisLine.contains("Description:"))
          t4 = counter;
      }
      t1++;
      t2++;
      t3++;
      t4++;
      
      counter = 0;
      row_file.close();
      reader.close();
      
      FileReader reader1 = new FileReader(Jmeterhtmlreport + File.separator + "index.html");
      BufferedReader row_file1 = new BufferedReader(reader1);
      while ((thisLine = row_file1.readLine()) != null) {
        counter++;
        if (counter == t1)
        {
          v1 = thisLine;
        }
        if (counter == t2)
        {
          v2 = thisLine;
        }
        if (counter == t3)
        {
          v3 = thisLine;
        }
        if (counter == t4)
        {
          v4 = thisLine;
        }
      }
      row_file1.close();
      reader1.close();
      
      v1 = v1.trim();
      v2 = v2.trim();
      v3 = v3.trim();
      v4 = v4.trim();
      v1 = v1.substring(4, v1.length() - 5);
      v2 = v2.substring(4, v2.length() - 5);
      v3 = v3.substring(4, v3.length() - 5);
      v4 = v4.substring(4, v4.length() - 5);
      v1 = v1.replace("\"", "");
      v2 = v2.replace("\"", "");
      v3 = v3.replace("\"", "");
      System.out.println(v1);
      System.out.println(v2);
      System.out.println(v3);
      System.out.println(v4);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    String[] d1 = v2.split(" ");
    String[] d2 = v3.split(" ");
    String finmaint = d1[0] + "," + d1[1] + "," + d2[1] + ",fail,interim";
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "mytestdata.txt", finmaint);
    String p1 = "<p><strong>Test Details:&nbsp;</strong>" + v4 + "<p>";
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", p1);
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<table border=1 id=\"myTable\">");
    p1 = "<thead><tr style=\"background-color: #66c2ff;\"><th>Label</th><th>Total Request</th><th>Fail</th><th>Error %</th><th>Average response time</th><th>90th pct</th><th>95th pct</th><th>99th pct</th><th>Throughput</th><th>Received KB/sec</th><th>Sent KB/sec</th><th>Min</th><th>Max</th></tr></thead>";
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", p1);
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<tbody>");
    main_page_part3("#statisticsTable");
    p1 = "<script type=\"text/javascript\">$(document).ready(function(){jq171('#myTable').dataTable();});</script>";
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", p1);
    pop.Rowfile_add(Result_folder1 + File.separator + TestReport_name, "index.php", "attachment.txt");
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<p> </p>");
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<p><strong>Test Analysis:</strong>" + Add_info + "</p>");
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<?php");
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "$file = file(\"./analysis.txt\");");
    
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "foreach($file as $f){echo \"<p>\".$f.\"<p>\";}");
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "?>");
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<h3><strong>Test Duration:</strong></h3>");
    v2 = "<h4>Start Time: " + v2 + " UTC</h4>";
    v3 = "<h4>End Time: " + v3 + " UTC</h4>";
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", v2);
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", v3);
    p1 = "<p><a href=\"../" + TestReport_name + "/HW/HWOutput.htm\">For detailed H/W Report click here</a></p>";
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", p1);
    p1 = "<p><a href=\"../" + TestReport_name + "/TestResults/index.html\">For detailed Test Result click here</a></p>";
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", p1);
    
    main_page_LPdata();
    main_page_top5_error();
  }
  
  public void main_page_part3(String sfind)
  {
    html_add_content pop = new html_add_content();
    
    String path = Jmeterhtmlreport + File.separator + "\\content\\js\\dashboard.js";
    String v1 = "";
    try {
      FileReader reader = new FileReader(path);
      BufferedReader row_file = new BufferedReader(reader);
      

      String thisLine;
      
      while ((thisLine = row_file.readLine()) != null)
      {
        String thisLine;
        
        if (thisLine.contains(sfind))
        {
          v1 = thisLine;
        }
      }
      

      row_file.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    
    Matcher m = Pattern.compile("\"data\": (.+?)],")
      .matcher(v1);
    while (m.find())
    {
      String pas = m.group();
      pas = pas.replace("[", "");
      pas = pas.replace("]", "");
      pas = pas.replace("data\":", "");
      pas = pas.replace("\"", "");
      Statdata(pas);
    }
    
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "</tbody>");
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "</table>");
  }
  



  public void Statdata(String str)
  {
    html_add_content pop = new html_add_content();
    String[] statd = str.split(",");
    
    for (int j = 0; j < statd.length; j++)
    {
      statd[j] = statd[j].trim();
      if (statd[j].contentEquals("null")) {
        statd[j] = "-";
      }
    }
    List<String> list = new java.util.ArrayList(Arrays.asList(statd));
    list.removeAll(Arrays.asList(new String[] { "", null }));
    if (list.size() > 1) {
      pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<tr>");
      
      int pp = 0;
      for (int i = 0; i < list.size(); i++)
      {
        String p1 = "";
        
        if (!statd[i].contentEquals(""))
        {
          try {
            Double.parseDouble(statd[i]);

          }
          catch (NumberFormatException e)
          {
            pp = 1;
          }
          


          if (pp == 0)
          {
            statd[i] = statd[i].trim();
            NumberFormat df = java.text.DecimalFormat.getInstance();
            df.setMinimumFractionDigits(0);
            df.setMaximumFractionDigits(2);
            df.setRoundingMode(java.math.RoundingMode.DOWN);
            double d = Double.parseDouble(statd[i]);
            statd[i] = df.format(d);
            p1 = "<td style=\"text-align: center;\">" + statd[i] + "</td>";


          }
          else if (statd[i].contentEquals("-")) { p1 = "<td style=\"text-align: center;\">" + statd[i] + "</td>";
          } else { p1 = "<td style=\"text-align: left;\">" + statd[i] + "</td>";
          }
          
          pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", p1);
          pp = 0;
        }
      }
      


      pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "</tr>");
    }
    
    list.clear();
  }
  
  public void main_page_LPdata() { html_add_content pop = new html_add_content();
    

    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<h3>User Load:</h3>");
    String t1 = "";
    String[] lprow = LoadPatterninfo.split(",");
    for (int i = 0; i < lprow.length; i++) {
      String[] lprows = lprow[i].split(":");
      t1 = t1 + "<p><strong>" + lprows[0] + "</strong>:" + lprows[1] + "</p>";
      pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", t1);
      t1 = "";
    }
  }
  
  public void main_page_top5_error() { html_add_content pop = new html_add_content();
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<h3><strong>Top 5 Error codes by sampler:</strong></h3>");
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<table border=1 id=\"myTable1\">");
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<thead><tr style=\"background-color: #66c2ff;\"><th>Sample&nbsp;</th><th>#Samples&nbsp;</th><th>#Errors&nbsp;</th><th>Error1&nbsp;</th><th>#Errors1&nbsp;</th><th>Error2&nbsp;</th><th>#Errors2&nbsp;</th><th>Error3&nbsp;</th><th>#Errors3&nbsp;</th><th>Error4&nbsp;</th><th>#Errors4&nbsp;</th><th>Error5&nbsp;</th><th>#Errors5&nbsp;</th></tr></thead>");
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", "<tbody>");
    main_page_part3("#top5ErrorsBySamplerTable");
    String p1 = "<script type=\"text/javascript\">$(document).ready(function(){jq171('#myTable1').dataTable();});</script>";
    pop.file_txt_Updater(Result_folder1 + File.separator + TestReport_name + File.separator + "index.php", p1);
    pop.Rowfile_add(Result_folder1 + File.separator + TestReport_name, "index.php", "Dashpart2.txt");
    copy_content();
  }
  
  public void copy_content() {
    String Drive1 = Result_folder1;
    Drive1 = Drive1.substring(0, Drive1.indexOf(":") + 1);
    

    try
    {
      FileWriter writer = new FileWriter(Result_folder1 + File.separator + "p1.bat", true);
      BufferedWriter out = new BufferedWriter(writer);
      out.write(Drive1);
      String st1 = Jmeterhtmlreport;
      String st2 = Result_folder1 + "/" + TestReport_name + "/" + "TestResults";
      String st3 = st2;
      st1 = st1.replace("/", "\\");
      st2 = st2.replace("/", "\\");
      String cmd = "xcopy " + st1 + " " + st2 + " /E /H";
      out.write(10);
      out.write(cmd);
      out.flush();
      out.close();
      writer.close();
      Runtime.getRuntime().exec(Result_folder1 + File.separator + "p1.bat");
      java.util.concurrent.TimeUnit.SECONDS.sleep(2L);
      new File(Result_folder1 + File.separator + "p1.bat").delete();
      System.out.println("file copyed");
      update_html_report(st3 + File.separator + "index.html");
      update_html_report(st3 + "/content/pages/OverTime.html");
      update_html_report(st3 + "/content/pages/ResponseTimes.html");
      update_html_report(st3 + "/content/pages/Throughput.html");
    }
    catch (IOException|InterruptedException e) {
      e.printStackTrace();
    }
  }
  

  public void update_html_report(String path)
  {
    int lineno = 0;int counter = 0;
    try
    {
      FileReader reader1 = new FileReader(path);
      int type = 0;
      BufferedReader row_file1 = new BufferedReader(reader1);
      String thisLine; while ((thisLine = row_file1.readLine()) != null) { String thisLine;
        counter++;
        if (thisLine.contains("<a class=\"navbar-brand\" href=\"index.html\">JioCloud Performance Test Results</a>")) {
          lineno = counter;
          type = 1;
        }
        if (thisLine.contains("<a class=\"navbar-brand\" href=\"../../index.html\">JioCloud Performance Test Results</a>")) {
          lineno = counter;
          type = 2;
        }
      }
      row_file1.close();
      reader1.close();
      lineno--;
      
      row_file1.close();
      reader1.close();
      java.nio.file.Path path1 = java.nio.file.Paths.get(path, new String[0]);
      List<String> lines = Files.readAllLines(path1, StandardCharsets.UTF_8);
      
      int position = lineno;
      String extraLine = "";
      if (type == 1)
        extraLine = "<a class=\"navbar-brand\" href=\"../index.php\">&lt;&lt;Back</a>";
      if (type == 2) {
        extraLine = "<a class=\"navbar-brand\" href=\"../../index.php\">&lt;&lt;Back</a>";
      }
      lines.add(position, extraLine);
      Files.write(path1, lines, StandardCharsets.UTF_8, new java.nio.file.OpenOption[0]);

    }
    catch (IOException e)
    {

      e.printStackTrace();
    }
  }
}
