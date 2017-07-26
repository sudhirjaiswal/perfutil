package com.rjil.cloud.contact.md5;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Calculate_Contact_md5
{
  public Calculate_Contact_md5() {}
  
  public String getImageHash(String imageBinary)
  {
    StringBuilder hashtext = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(imageBinary.getBytes("utf-8"));
      BigInteger number = new BigInteger(1, messageDigest);
      hashtext.append(number.toString(16));
      

      while (hashtext.length() < 32) {
        hashtext.insert(0, "0");
      }
      return hashtext.toString();
    } catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
      e.getMessage();
    }
    return hashtext.toString();
  }
  





  public String getFldHash(String FirstName, String LastName, String Email, String PhoneNo)
  {
    StringBuilder FourFieldHash = new StringBuilder();
    
    FourFieldHash.append(LastName);
    FourFieldHash.append(FirstName);
    FourFieldHash.append(PhoneNo);
    FourFieldHash.append(Email);
    String valueToBeHashed = FourFieldHash.toString();
    System.out.println("valueToBeHashed==" + valueToBeHashed);
    StringBuilder hashtext = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(valueToBeHashed.getBytes("utf-8"));
      BigInteger number = new BigInteger(1, messageDigest);
      hashtext.append(number.toString(16));
      

      while (hashtext.length() < 32) {
        hashtext.insert(0, "0");
      }
      return hashtext.toString();
    } catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
      e.getMessage();
    }
    return hashtext.toString();
  }
  







  public String getVCardHash(String vCard)
  {
    System.out.println(vCard);
    StringBuilder hashtext = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(vCard.getBytes("utf-8"));
      BigInteger number = new BigInteger(1, messageDigest);
      hashtext.append(number.toString(16));
      

      while (hashtext.length() < 32) {
        hashtext.insert(0, "0");
      }
      return hashtext.toString();
    } catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
      e.getMessage();
    }
    return hashtext.toString();
  }
  


  public static void main(String[] args)
  {
    Calculate_Contact_md5 pan = new Calculate_Contact_md5();
    String vcard1 = "[\"vcard\",[[\"version\",{},\"text\",\"4.0\"],[\"n\",{},\"text\",[\"\",\"wminvfdhbt\",\"\",\"\",\"\"]],[\"fn\",{},\"text\",\"uehcsvbigp\"],[\"tel\",{\"type\":\"home\"},\"uri\",\"tel:4796468887\"],[\"note\",{},\"text\",\"\"]]]";
    String pop = pan.getFldHash("Pankaj", "Nandy", "pan123@pan.com", "1234567890");
    System.out.println(pop);
    String pop1 = pan.getVCardHash(vcard1);
    System.out.println(pop1);
  }
}
