package com.rjil.cloud.hw.reportcreator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class html_add_content
{
  public html_add_content() {}
  
  public java.io.InputStream genStream(String key1)
  {
    return getClass().getResourceAsStream(key1);
  }
  

  public String html_part1(String path, String module1, int file1)
  {
    String number_for = number_Formater(String.valueOf(file1));
    String Filename1 = "sheet" + number_for + ".htm";
    

    String pop = "<th>" + module1 + "</th>";
    

    try
    {
      FileWriter writer = new FileWriter(path + java.io.File.separator + Filename1, true);
      BufferedWriter out = new BufferedWriter(writer);
      




      BufferedReader row_file = new BufferedReader(new InputStreamReader(genStream("MGPart1a.txt")));
      BufferedReader row_file1 = new BufferedReader(new InputStreamReader(genStream("MGPart1b.txt")));
      String thisLine;
      while ((thisLine = row_file.readLine()) != null) {
        String thisLine;
        out.write(thisLine);
        out.write(10);
      }
      out.write(pop);
      out.write(10);
      while ((thisLine = row_file1.readLine()) != null)
      {
        out.write(thisLine);
        out.write(10);
      }
      out.flush();
      out.close();
      writer.close();
      row_file.close();
      
      row_file1.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    
    return Filename1;
  }
  



  public void html_part2(String path, String Filename1, String Filename2, int file1, String axis_name)
  {
    String[] pan = Filename2.split(",");
    String Filename3 = pan[1];
    Filename2 = Filename2.replace(",", "_");
    String chartcounter = "Chart" + Integer.toString(file1);
    try
    {
      FileWriter writer = new FileWriter(path + java.io.File.separator + Filename1, true);
      BufferedWriter out = new BufferedWriter(writer);
      
      BufferedReader row_file = new BufferedReader(new InputStreamReader(genStream("MGPart2.txt")));
      
      String[] labelsplit = axis_name.split(",");
      String thisLine;
      while ((thisLine = row_file.readLine()) != null) { String thisLine;
        if (thisLine.contains("chartpankaj")) {
          thisLine = thisLine.replace("chartpankaj", chartcounter);
        }
        if (thisLine.contains("data1.csv")) {
          thisLine = thisLine.replace("data1.csv", Filename2 + ".csv");
        }
        if (thisLine.contains("CPU %")) {
          thisLine = thisLine.replace("CPU %", Filename3);
        }
        if (thisLine.contains("xlabelPankaj")) {
          thisLine = thisLine.replace("xlabelPankaj", labelsplit[0]);
        }
        if (thisLine.contains("ylabelPankaj")) {
          thisLine = thisLine.replace("ylabelPankaj", labelsplit[1]);
        }
        out.write(thisLine);
        out.write(10);
      }
      

      out.flush();
      out.close();
      writer.close();
      row_file.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  



  public void html_part2end(String path)
  {
    try
    {
      FileWriter writer = new FileWriter(path, true);
      BufferedWriter out = new BufferedWriter(writer);
      out.write("</script>");
      out.write(10);
      out.write("</body>");
      out.write(10);
      out.flush();
      out.close();
      writer.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void Rowfile_add(String path, String Filename1, String Row_file)
  {
    try {
      FileWriter writer = new FileWriter(path + java.io.File.separator + Filename1, true);
      BufferedWriter out = new BufferedWriter(writer);
      
      BufferedReader row_file = new BufferedReader(new InputStreamReader(genStream(Row_file)));
      String thisLine;
      while ((thisLine = row_file.readLine()) != null) {
        String thisLine;
        out.write(thisLine);
        out.write(10);
      }
      out.flush();
      out.close();
      writer.close();
      row_file.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void file_txt_Updater(String path, String txt) {
    try {
      FileWriter writer = new FileWriter(path, true);
      BufferedWriter out = new BufferedWriter(writer);
      out.write(txt);
      out.write(10);
      out.flush();
      out.close();
      writer.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public String number_Formater(String file1) {
    int fnumber = Integer.valueOf(file1).intValue();
    String prefix;
    String prefix; if (fnumber < 10)
    {
      prefix = "00";
    } else {
      String prefix;
      if (fnumber < 100) prefix = "0"; else {
        prefix = "";
      }
    }
    String Final1 = prefix + String.valueOf(fnumber);
    return Final1;
  }
}
