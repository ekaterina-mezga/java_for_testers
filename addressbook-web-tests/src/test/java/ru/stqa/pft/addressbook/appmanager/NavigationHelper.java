package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

  private WebDriver driver;

  public NavigationHelper(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public void goToGroupsPage() {
    if (isElementPresent(By.tagName("h1"))
            && driver.findElement(By.tagName("h1")).getText().equals("groups")
            && isElementPresent(By.name("new"))){
      return;
    }
    clickLinkText("groups");
  }

  public void goToHomePage(){
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    clickLinkText("home");
  }
}
