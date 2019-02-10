package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupHelper extends BaseHelper {

  private ChromeDriver driver;

  public GroupHelper(ChromeDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public void returnToGroupsPage() {
    clickLinkText("group page");
  }

  public void submitGroupCreation() {
    clickButtonByName("submit");
  }

  public void fillGroupForm(GroupData groupData) {
    typeTextInField("group_name", groupData.getName());
    typeTextInField("group_header", groupData.getHeader());
    typeTextInField("group_footer", groupData.getFooter());
  }

  public void initGroupCreation() {
    clickButtonByName("new");
  }

  public void deleteSelectedGroups() {
    clickButtonByName("delete");
  }

  public void selectGroup() {
    driver.findElement(By.name("selected[]")).click();
  }

  public void initGroupModification() {
    clickButtonByName("edit");
  }

  public void submitGroupModification() {
    clickButtonByName("update");
  }
}
