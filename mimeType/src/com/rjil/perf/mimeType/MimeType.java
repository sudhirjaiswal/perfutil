package com.rjil.perf.mimeType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MimeType {
	
	public String mimetype(String path)
	{
		String mime_type="";
		try {
			mime_type= Files.probeContentType(Paths.get(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return mime_type;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MimeType sud=new MimeType();
		String sudreturn=sud.mimetype("C:\\Users\\sudhir.jaiswal\\Desktop\\DocConv\\New folder\\dfasdfd.txt");
		System.out.println(sudreturn);
	}

}
