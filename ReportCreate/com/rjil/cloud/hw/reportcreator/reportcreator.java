package com.rjil.cloud.hw.reportcreator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.swing.JOptionPane;




public class reportcreator
{
  public static List<String> list1 = new ArrayList();
  public static List<String> list2 = new ArrayList();
  public static List<Integer> list3 = new ArrayList();
  public static List<String> list4 = new ArrayList();
  public static List<String> list5 = new ArrayList();
  public static List<String> master1 = new ArrayList();
  public static int counter1;
  public static String folder1;
  public static String RowAWSContentpath;
  public static String Jmeterhtmlreport;
  public static String Result_folder1;
  public static String TestReport_name;
  public static String LoadPatterninfo;
  public static String Add_info;
  
  public reportcreator() {}
  
  public void displayDirectoryContents(String directory, String path) {
    File dir = new File(directory);
    String ext = "txt";
    try {
      File[] files = dir.listFiles();
      for (File file : files) {
        if (file.isDirectory())
        {
          displayDirectoryContents(file.getCanonicalPath(), path);

        }
        else if (file.getCanonicalPath().toLowerCase().endsWith(ext))
        {

          if (file.length() > 0L)
          {
            BufferedReader reader = new BufferedReader(new FileReader(file.getCanonicalPath()));
            int lines = 0;
            

            while (reader.readLine() != null) { lines++;
            }
            
            reader.close();
            if (lines != 1)
            {


              update_file_name(file.getCanonicalPath(), path);
            }
          }
        }
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  

  public void update_file_name(String Filepath, String path)
  {
    String pan = Filepath.substring(path.length());
    pan = pan.replace("\\", "/");
    

    String[] splittedFileName = pan.split("/");
    String temp_str = "";
    String File_name_final = "";
    for (int i = 0; i < splittedFileName.length; i++)
    {
      if (splittedFileName[i] != "")
      {

        if (splittedFileName[i].compareToIgnoreCase("txt") != 0)
        {



          if (splittedFileName[i].contains("2017"))
          {

            temp_str = splittedFileName[i].substring(0, splittedFileName[i].indexOf("_2017"));
            File_name_final = File_name_final + "," + temp_str;
          }
          else
          {
            File_name_final = File_name_final + splittedFileName[i];
          }
          temp_str = "";
        }
      }
    }
    



    list2.add(File_name_final);
    Create_file_new(path, Filepath, File_name_final);
  }
  
  public void intial_setup_folder() {
    String path = Result_folder1;
    Dash_board_page pan1 = new Dash_board_page();
    



    if (TestReport_name == "")
    {
      TestReport_name = pan1.result_folder(Jmeterhtmlreport + File.separator + "index.html");
    }
    String output_fol = path + File.separator + TestReport_name;
    folder1 = TestReport_name;
    try
    {
      new File(output_fol).mkdir();
      new File(output_fol + File.separator + "HW").mkdir();
      new File(output_fol + File.separator + "Upload").mkdir();
      new File(output_fol + File.separator + "TestResults").mkdir();
      new File(output_fol + File.separator + "HW" + File.separator + "HWOutput_files").mkdir();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void Create_file_new(String path, String filepath, String filename) {
    path = Result_folder1;
    filename = filename.replace(",", "_");
    String ServerCSVFile = path + File.separator + folder1 + File.separator + "HW" + File.separator + "HWOutput_files" + File.separator + filename + ".csv";
    String pop = "date,close";
    String thisLine = null;
    int line_num = 0;
    try
    {
      FileReader reader = new FileReader(filepath);
      BufferedReader row_file = new BufferedReader(reader);
      FileWriter writer = new FileWriter(ServerCSVFile, true);
      BufferedWriter out = new BufferedWriter(writer);
      out.write(pop);
      out.write(10);
      while ((thisLine = row_file.readLine()) != null) {
        line_num++;
        if (line_num == 1) {
          list5.add(thisLine);
        }
        else
        {
          out.write(thisLine);
          out.write(10);
        }
      }
      out.flush();
      out.close();
      writer.close();
      row_file.close();
      reader.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void array_list_counter(String path) {
    int count = 0;
    int mod = 0;
    html_add_content pop = new html_add_content();
    for (String filename1 : list1) {
      mod++;
      String HP1 = pop.html_part1(path + File.separator + folder1 + File.separator + "HW" + File.separator + "HWOutput_files", filename1, mod);
      list4.add(HP1);
      for (String filename2 : list2)
      {

        String[] pdop = filename2.split(",");
        if (filename1.contentEquals(pdop[0]))
          count++;
      }
      list3.add(Integer.valueOf(count));
      
      count = 0;
    }
    




    for (String filename3 : list4)
    {
      System.out.print(filename3);
    }
    

    System.out.print("Creating Support and Dashboard Html");
    array_list2_counter(path);
    file_adder(path);
    final_content_writer(path);
    System.out.print("Completed Support and Dashboard Html");
  }
  
  public void file_adder(String path)
  {
    html_add_content pop = new html_add_content();
    
    pop.Rowfile_add(path + File.separator + folder1 + File.separator + "HW" + File.separator + "HWOutput_files", "table.html", "MGPart4tabstrip.txt");
    pop.Rowfile_add(path + File.separator + folder1 + File.separator + "HW", "HWOutput.htm", "MGindexpart1.txt");
  }
  

  public void final_content_writer(String path)
  {
    html_add_content pop = new html_add_content();
    
    add_data_in_table_page(path + File.separator + folder1 + File.separator + "HW" + File.separator + "HWOutput_files" + File.separator + "table.html");
    String q4 = "<frame src=\"HWOutput_files/" + (String)list4.get(0) + "\" name=\"main\">";
    pop.file_txt_Updater(path + File.separator + folder1 + File.separator + "HW" + File.separator + "HWOutput.htm", q4);
    pop.Rowfile_add(path + File.separator + folder1 + File.separator + "HW", "HWOutput.htm", "MGindexpart2.txt");
    


    pop.Rowfile_add(path + File.separator + folder1 + File.separator + "HW" + File.separator + "HWOutput_files", "table.html", "MGPart4tabstrip2.txt");
  }
  

  public void add_data_in_table_page(String path)
  {
    html_add_content pop = new html_add_content();
    int fname = 0;
    int ismaster = 0;
    
    String p1 = "";
    for (String filename1 : list1) {
      for (String filename2 : master1) {
        if (filename1.contentEquals(filename2)) {
          ismaster++;
        }
      }
      if (ismaster > 0) {
        p1 = "<li><a href=\"" + (String)list4.get(fname) + "\" target=\"main\">" + filename1 + "</a></li>";
      }
      else {
        p1 = "<ul><li><a href=\"" + (String)list4.get(fname) + "\" target=\"main\">" + filename1 + "</a></li></ul>";
      }
      pop.file_txt_Updater(path, p1);
      
      fname++;
      ismaster = 0;
    }
  }
  
  public void array_list2_counter(String path)
  {
    html_add_content pop = new html_add_content();
    int counter1 = 0;
    int st = 0;int end = 0;
    for (String filename1 : list4)
    {
      end += ((Integer)list3.get(counter1)).intValue();
      int pp = 1;
      for (int i = st; i < end; i++) {
        pop.html_part2(path + File.separator + folder1 + File.separator + "HW" + File.separator + "HWOutput_files", filename1, (String)list2.get(i), pp, (String)list5.get(i));
        

        pp++;
      }
      
      st = end;
      counter1++;
      pop.html_part2end(path + File.separator + folder1 + File.separator + "HW" + File.separator + "HWOutput_files" + File.separator + filename1);
    }
  }
  


  public void Read_file(String path, String file1)
  {
    String thisLine = null;
    
    String path1 = path;
    path = path + File.separator + file1;
    String[] ppp;
    try {
      FileReader reader = new FileReader(path);
      BufferedReader txt_content = new BufferedReader(reader);
      while ((thisLine = txt_content.readLine()) != null)
      {

        if (!thisLine.contentEquals(""))
        {
          ppp = thisLine.split("/");
          
          list1.add(ppp[0]);
          master1.add(ppp[0]);
          String pop1 = ppp[2].substring(1, ppp[2].length() - 1);
          String[] submod = pop1.split(",");
          for (String n : submod) {
            n = n.trim();
            list1.add(n);
          }
        }
      }
      




      txt_content.close();

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    intial_setup_folder();
    Set<String> hs = new LinkedHashSet(list1);
    
    list1.clear();
    list1.addAll(hs);
    hs.clear();
    for (String filename1 : list1)
    {
      displayDirectoryContents(path1 + File.separator + filename1, path1);
    }
  }
  
  public void readproperty_setproperty()
  {
    String Systempath = System.getProperty("user.dir", "EMPTY");
    Systempath = Systempath + File.separator + "Report1.xml";
    try {
      File file = new File(Systempath);
      FileInputStream fileInput = new FileInputStream(file);
      Properties properties = new Properties();
      properties.loadFromXML(fileInput);
      fileInput.close();
      RowAWSContentpath = properties.getProperty("ROWAWSDATAPATH");
      Jmeterhtmlreport = properties.getProperty("ROWjMETERHTMLREPORTDATAPATH");
      Result_folder1 = properties.getProperty("OUTPUTDUMP");
      TestReport_name = properties.getProperty("REPORTNAME");
      LoadPatterninfo = properties.getProperty("LOADPATTERN");
      Add_info = properties.getProperty("ADDITIONALINFO");
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  



  public static void main(String[] args)
  {
    reportcreator pan = new reportcreator();
    pan.readproperty_setproperty();
    Dash_board_page pan1 = new Dash_board_page();
    try
    {
      JOptionPane.showMessageDialog(null, "Welcome to H/w Report Creator");
      JOptionPane.showMessageDialog(null, "To Add the Path of parent folder which contain mapping.txt");
      

      String path = RowAWSContentpath;
      System.out.println("Starting Intial Setup");
      pan.Read_file(path, "mappings.txt");
      System.out.println("Completed Intial Setup");
      System.out.println("Preparing Garph Pages");
      pan.array_list_counter(Result_folder1);
      pan1.main_page_init(Jmeterhtmlreport, Result_folder1, TestReport_name, LoadPatterninfo, Add_info);
      list1.clear();
      list2.clear();
      list3.clear();
      list4.clear();
      list5.clear();
      master1.clear();
      
      System.out.println("Completed");

    }
    catch (Exception e)
    {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Sorry Wrong Directory");
    }
  }
}
