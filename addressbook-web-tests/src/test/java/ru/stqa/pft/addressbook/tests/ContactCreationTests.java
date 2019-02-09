package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.initContactCreation();
    app.fillCreateContactForm(new ContactData("Sherlock", "Holmes", "London, Backer str., 221b",
                                          "222 22 22", "sherlock@test.test"));
    app.submitContactCreation();
    app.returnToHomePage();
  }
}
