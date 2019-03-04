package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData()
                      .withFirstName("Sherlock").withLastName("Holmes")
                      .withAddress("London, Backer str., 221b").withHomePhone("222 22 22").withEmail("sherlock@test.test").withGroup("group1"));
    }
  }

  @Test
  public void testContactDeletion(){
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }



  @Test
  public void testAllContactsDeletion(){
    app.contact().deleteAll();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), 0);
  }
}
