package com.rjil.perf;

import java.util.Scanner;
import com.rjil.perf.NewImageCreate;

public class PrintImage {
    public static String fileName;
    public static String dataFolder;

    public static void main(String[] args) {
        dataFolder = "D:\\New_data\\";
        NewImageCreate image = new NewImageCreate();
	     Scanner reader = new Scanner(System.in);
	     System.out.println("How many image you want ?");
	     int noofimage = reader.nextInt();
        int i = 1;
        while (i <= noofimage) {
            fileName = image.CreateImage(dataFolder, "1000", "1000", "1000", "900");
            System.out.println(fileName);
            ++i;
        }
        reader.close();
    }
}