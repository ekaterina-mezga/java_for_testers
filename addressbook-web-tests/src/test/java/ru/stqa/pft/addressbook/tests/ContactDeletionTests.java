package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData()
                      .withFirstName("Sherlock").withLastName("Holmes")
                      .withAddress("London, Backer str., 221b").withHomePhone("222 22 22").withEmail("sherlock@test.test").withGroup("group1"));
    }
  }

  @Test
  public void testContactDeletion(){
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }



  @Test
  public void testAllContactsDeletion(){
    app.contact().deleteAll();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), 0);
  }
}
