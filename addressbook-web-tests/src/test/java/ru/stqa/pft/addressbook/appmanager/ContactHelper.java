package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends BaseHelper {

  private WebDriver driver;
  private NavigationHelper goTo;

  public ContactHelper(WebDriver driver) {
    super(driver);
    this.driver = driver;
    goTo = new NavigationHelper(driver);
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

  public void create(ContactData contact){
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    confirmAlert();
    goTo.homePage();
  }

  public void deleteAll() {
    selectAllContacts();
    deleteSelectedContacts();
    confirmAlert();
    goTo.homePage();
  }

  public void initContactModification(int id){
    clickIcon(driver.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")));
  }

  public void submitContactModification() {
    clickButtonByName("update");
  }

  private void selectContactById(int id) {
    driver.findElement(By.cssSelector("input[id='" + id + "']")).click();
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

  public Contacts all(){
    Contacts contacts = new Contacts();
    List<WebElement> elements = driver.findElements(By.cssSelector("[name=\"entry\"]"));
    for (WebElement e: elements){
      String firstName = e.findElements(By.cssSelector("td")).get(2).getText();
      String lastName = e.findElements(By.cssSelector("td")).get(1).getText();
      int id = Integer.parseInt(e.findElement(By.cssSelector("input[type=\"checkbox\"]")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
    }
    return contacts;
  }
}
