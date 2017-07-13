package com.rjil.perf;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class NewImageCreate {
	
	   public String CreateImage(String dir, String height1, String width1, String height2, String width2) {
	        int image_height1 = new Integer(Integer.parseInt(height1));
	        int image_width1 = new Integer(Integer.parseInt(width1));
	        int image_height2 = new Integer(Integer.parseInt(height2));
	        int image_width2 = new Integer(Integer.parseInt(width2));
	        BufferedImage bi = new BufferedImage(image_width1, image_height1, 1);
	        Graphics2D g2 = bi.createGraphics();
	        g2.setColor(Color.getHSBColor(NewImageCreate.randFloat(), NewImageCreate.randFloat(), NewImageCreate.randFloat()));
	        g2.fillRect(5, 5, image_width1, image_height1);
	        g2.setStroke(new BasicStroke(20.0f));
	        g2.setPaint(new GradientPaint(NewImageCreate.randFloat(), NewImageCreate.randFloat(), Color.getHSBColor(NewImageCreate.randFloat(), NewImageCreate.randFloat(), NewImageCreate.randFloat()), NewImageCreate.randFloat(), NewImageCreate.randFloat(), Color.getHSBColor(NewImageCreate.randFloat(), NewImageCreate.randFloat(), NewImageCreate.randFloat()), true));
	        int end_x = NewImageCreate.randInt(image_height1, image_width1);
	        int end_y = NewImageCreate.randInt(image_width2, image_height2);
	        g2.fill(new Ellipse2D.Float(NewImageCreate.randFloat(), NewImageCreate.randFloat(), end_x, end_y));
	        String random_num = String.valueOf(NewImageCreate.randInt(10000, 99999));
	        String filename = "IMAGE_" + System.currentTimeMillis() + random_num + ".jpg";
	        String fullPath = String.valueOf(String.valueOf(dir)) + filename;
	        try {
	            FileOutputStream out = new FileOutputStream(fullPath);
	            ImageIO.write((RenderedImage)bi, "jpg", out);
	        }
	        catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return filename;
	    }

	    public static int randInt(int min, int max) {
	        Random rand = new Random();
	        int randomNum = rand.nextInt(max - min + 1) + min;
	        return randomNum;
	    }

	    public static float randFloat() {
	        Random rand = new Random();
	        float randomFloat = rand.nextFloat();
	        return randomFloat;
	    }
}
