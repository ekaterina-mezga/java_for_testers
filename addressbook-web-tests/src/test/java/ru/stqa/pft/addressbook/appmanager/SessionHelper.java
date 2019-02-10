package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.chrome.ChromeDriver;

public class SessionHelper extends BaseHelper {

  private ChromeDriver driver;

  public SessionHelper(ChromeDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public void login(String username, String password) {
    typeTextInField("user", username);
    typeTextInField("pass", password);
    clickButtonByXpath("//input[@value='Login']");
  }

  public void logout() {
    clickLinkText("Logout");
  }
}
