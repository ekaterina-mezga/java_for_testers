package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillCreateContactForm(new ContactData("Sherlock", "Holmes", "London, Backer str., 221b",
                                          "222 22 22", "sherlock@test.test"));
    submitContactCreation();
    returnToHomePage();
  }
}
