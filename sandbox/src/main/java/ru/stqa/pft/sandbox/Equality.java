package ru.stqa.pft.sandbox;

public class Equality {

  public void equality() {

    String s1 = "firefox";
    String s2 = new String(s1);

    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
    
  }
}
