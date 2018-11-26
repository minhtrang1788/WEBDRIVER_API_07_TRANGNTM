package selenium_api;

import java.util.Random;

import org.testng.annotations.Test;

public class general {
  

  public static String getSaltString() {
      String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
      StringBuilder salt = new StringBuilder();
      Random rnd = new Random();
      while (salt.length() < 10) { // length of the random string.
          int index = (int) (rnd.nextFloat() * SALTCHARS.length());
          salt.append(SALTCHARS.charAt(index));
      }
      String saltStr = salt.toString()+ "@example.com";
      return saltStr;

  }
}
