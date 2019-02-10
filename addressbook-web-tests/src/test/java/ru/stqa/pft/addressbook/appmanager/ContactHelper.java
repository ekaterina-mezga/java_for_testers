package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactHelper extends BaseHelper {

  private ChromeDriver driver;

  public ContactHelper(ChromeDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public void returnToHomePage() {
    clickLinkText("home page");
  }

  public void submitContactCreation() {
    clickButtonByName("submit");
  }

  public void fillCreateContactForm(ContactData contactData) {
    typeTextInField("firstname", contactData.getFirstName());
    typeTextInField("lastname", contactData.getLastName());
    typeTextInField("address", contactData.getAddress());
    typeTextInField("home", contactData.getHomePhone());
    typeTextInField("email", contactData.getEmail());
  }

  public void initContactCreation() {
    clickLinkText("add new");
  }

  public void initContactModification(){
    clickIcon("img[alt=\"Edit\"]");
  }

  public void submitContactModification() {
    clickButtonByName("update");
  }

  public void selectContact() {
    driver.findElement(By.name("selected[]")).click();
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
}
