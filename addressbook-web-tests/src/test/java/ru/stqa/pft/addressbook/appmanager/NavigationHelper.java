package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

  private WebDriver driver;

  public NavigationHelper(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public void goToGroupsPage() {
    clickLinkText("groups");
  }

  public void goToHomePage(){
    clickLinkText("home");
  }
}
