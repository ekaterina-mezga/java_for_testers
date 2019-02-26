package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isContactExist()){
      app.getContactHelper().createContact(new ContactData("Sherlock", "Holmes", "London, Backer str., 221b",
              "222 22 22", "sherlock@test.test", "group1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().confirmAlert();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
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
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), 0);
  }
}
