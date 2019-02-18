package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isContactExist()){
      app.getContactHelper().createContact(new ContactData("Sherlock", "Holmes", "London, Backer str., 221b",
              "222 22 22", "sherlock@test.test", "group1"));
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("John", "Watson", "London, Backer str., 221b",
            "333 33 33", "watson@test.test", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
