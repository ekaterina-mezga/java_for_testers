package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

  private WebDriver driver;

  public SessionHelper(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public void login(String username, String password) {
    type("user", username);
    type("pass", password);
    clickButtonByXpath("//input[@value='Login']");
  }

  public void logout() {
    clickLinkText("Logout");
  }
}
