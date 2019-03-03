package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

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
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).withFirstName("John").withLastName("Watson")
            .withAddress("London, Backer str., 221b").withHomePhone("333 33 33").withEmail("watson@test.test");
    app.contact().modify(index, contact);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
