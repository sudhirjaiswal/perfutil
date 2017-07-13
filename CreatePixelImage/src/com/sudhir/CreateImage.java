package com.sudhir;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class CreateImage {
	public static void main(String args[])throws IOException{
		int width = 1080;
		int height = 720;
		     //create buffered image object image
		     BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		     //file object
		     File f = null;
		     Scanner reader = new Scanner(System.in);
		     System.out.println("How may image you want ?");
		     int noofimage = reader.nextInt();
		     //create random image pixel by pixel
		     for(int y = 0; y < height; y++){
		       for(int x = 0; x < width; x++){
		         int a = (int)(Math.random()*256); //alpha
		         int r = (int)(Math.random()*256); //red
		         int g = (int)(Math.random()*256); //green
		         int b = (int)(Math.random()*256); //blue
		 
		         int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel
		 
		         img.setRGB(x, y, p);
		       }
		     }

		     try{
		    	 for (int i=1;i<=noofimage;i++)
		    	 {
		       f = new File("D:\\Image\\image_"+i+".png");
		       ImageIO.write(img, "png", f);
		       System.out.println("File "+i+" Created");
		    	 }
		     }catch(IOException e){
		       System.out.println("Error: " + e);
		     }
		     reader.close();
		  }
}