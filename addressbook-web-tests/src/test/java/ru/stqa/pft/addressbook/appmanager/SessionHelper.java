package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;

public class SessionHelper extends BaseHelper {

  private WebDriver driver;

  public SessionHelper(WebDriver driver) {
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
