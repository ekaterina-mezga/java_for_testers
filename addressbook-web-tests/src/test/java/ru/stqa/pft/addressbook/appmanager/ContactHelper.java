package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

  private WebDriver driver;

  public ContactHelper(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public void returnToHomePage() {
    clickLinkText("home page");
  }

  public void submitContactCreation() {
    clickButtonByName("submit");
  }

  public void fillContactForm(ContactData contactData, boolean isCreation) {
    typeTextInField("firstname", contactData.getFirstName());
    typeTextInField("lastname", contactData.getLastName());
    typeTextInField("address", contactData.getAddress());
    typeTextInField("home", contactData.getHomePhone());
    typeTextInField("email", contactData.getEmail());

    if (isCreation){
      try {
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } catch (NoSuchElementException ex){
        return;
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    clickLinkText("add new");
  }

  public void createContact(ContactData contact){
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void initContactModification(int index){
    WebElement rowToEdit = driver.findElements(By.cssSelector("[name=\"entry\"]")).get(index);
    clickIcon(rowToEdit.findElement(By.cssSelector("img[alt=\"Edit\"]")));
  }

  public void submitContactModification() {
    clickButtonByName("update");
  }

  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContacts(){
    clickButtonByXpath("//input[@value='Delete']");
  }

  public void confirmAlert(){
    driver.switchTo().alert().accept();
  }

  public void selectAllContacts() {
    driver.findElement(By.cssSelector("#MassCB")).click();
  }

  public boolean isContactExist(){
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList(){
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = driver.findElements(By.cssSelector("[name=\"entry\"]"));
    for (WebElement e: elements){
      String firstName = e.findElements(By.cssSelector("tr[name=\"entry\"] > td")).get(1).getText();
      String lastName = e.findElements(By.cssSelector("tr[name=\"entry\"] > td")).get(2).getText();
      int id = Integer.parseInt(e.findElement(By.cssSelector("tr[name=\"entry\"] input[type=\"checkbox\"]")).getAttribute("id"));
      ContactData contact = new ContactData(id, lastName, firstName, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
