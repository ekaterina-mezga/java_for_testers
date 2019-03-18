package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class ContactHelper extends BaseHelper {

  private WebDriver driver;
  private NavigationHelper goTo;
  private Contacts contactCache = null;

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
    type("firstname", contactData.getFirstName());
    type("lastname", contactData.getLastName());
    type("nickname", contactData.getNickname());
    type("address", contactData.getAddress());
    type("home", contactData.getHomePhone());
    type("mobile", contactData.getMobilePhone());
    type("work", contactData.getWorkPhone());
    type("email", contactData.getEmail());
    type("email2", contactData.getEmail2());
    type("email3", contactData.getEmail3());
    attach("photo", contactData.getPhoto());


    if (isCreation){
      try {
        Groups groups = contactData.getGroups();
        if (groups.size() >0){
        Assert.assertTrue(groups.size() == 1);
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(groups.iterator().next().getName());
      }
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
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    confirmAlert();
    contactCache = null;
    goTo.homePage();
  }

  public void deleteAll() {
    selectAllContacts();
    deleteSelectedContacts();
    confirmAlert();
    contactCache = null;
    goTo.homePage();
  }

  public void initContactModification(int id){
    clickIcon(driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))));
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

  public int count(){
    return driver.findElements(By.name("selected[]")).size();
  }

  public Contacts all(){
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for (WebElement e: elements){
      List<WebElement> cells = e.findElements(By.cssSelector("td"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      int id = Integer.parseInt(e.findElement(By.cssSelector("input[type=\"checkbox\"]")).getAttribute("id"));
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
    String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
    String home = driver.findElement(By.name("home")).getAttribute("value");
    String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
    String work = driver.findElement(By.name("work")).getAttribute("value");
    String address = driver.findElement(By.name("address")).getAttribute("value");
    String email = driver.findElement(By.name("email")).getAttribute("value");
    String email2 = driver.findElement(By.name("email2")).getAttribute("value");
    String email3 = driver.findElement(By.name("email3")).getAttribute("value");
    driver.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
