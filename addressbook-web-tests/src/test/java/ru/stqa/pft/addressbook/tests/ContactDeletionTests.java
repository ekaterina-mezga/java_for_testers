package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isContactExist()){
      app.getContactHelper().createContact(new ContactData("Sherlock", "Holmes", "London, Backer str., 221b",
              "222 22 22", "sherlock@test.test", "group1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().confirmAlert();
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testAllContactsDeletion(){
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isContactExist()){
      app.getContactHelper().createContact(new ContactData("Sherlock", "Holmes", "London, Backer str., 221b",
              "222 22 22", "sherlock@test.test", "group1"));
    }
    app.getContactHelper().selectAllContacts();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().confirmAlert();
    app.getNavigationHelper().goToHomePage();
  }
}
