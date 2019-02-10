package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;

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
}
