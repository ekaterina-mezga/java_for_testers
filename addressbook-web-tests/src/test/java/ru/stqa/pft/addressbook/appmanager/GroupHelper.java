package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends BaseHelper {

  private WebDriver driver;
  private Groups groupCache = null;

  public GroupHelper(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public void submitGroupCreation() {
    clickButtonByName("submit");
  }

  public void fillGroupForm(GroupData groupData) {
    type("group_name", groupData.getName());
    type("group_header", groupData.getHeader());
    type("group_footer", groupData.getFooter());
  }

  public void initGroupCreation() {
    clickButtonByName("new");
  }

  public void deleteSelectedGroups() {
    clickButtonByName("delete");
  }

  public void selectGroupById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initGroupModification() {
    clickButtonByName("edit");
  }

  public void submitGroupModification() {
    clickButtonByName("update");
  }

  public void returnToGroupsPage() {
    clickLinkText("group page");
  }

  public void selectAllGroups() {
    List<WebElement> list = driver.findElements(By.name("selected[]"));

    for(WebElement element : list){
      if(!element.isSelected())
        element.click();
    }
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupsPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupsPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupsPage();
  }

  public void deleteAll() {
    selectAllGroups();
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupsPage();
  }

  public boolean isGroupExist() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public Groups all(){
    if (groupCache != null){
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement e: elements){
      String name = e.getText();
      int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }
}
