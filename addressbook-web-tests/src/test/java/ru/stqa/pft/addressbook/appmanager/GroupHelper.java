package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends BaseHelper {

  private WebDriver driver;

  public GroupHelper(WebDriver driver) {
    super(driver);
    this.driver = driver;
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

  public void selectGroup(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
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

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupsPage();
  }

  public boolean isGroupExist() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> getGroupList(){
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement e: elements){
      String name = e.getText();
      String id = e.findElement(By.tagName("input")).getAttribute("value");
      GroupData group = new GroupData(id, name, null, null);
      groups.add(group);
    }
    return groups;
  }
}
