package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("Sherlock").withLastName("Holmes").withNickname("SH").withAddress("London, Backer str., 221b")
              .withHomePhone("222 22 22").withMobilePhone("333 33 33").withWorkPhone("444 44 44")
              .withEmail("sherlock@test.test").withEmail2("sherlock2@test.test").withEmail3("sherlock3@test.test").withGroup("group1"));
    }
  }

  @Test
  public void testContactModification(){
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstName("John").withLastName("Watson").withNickname("JW").withAddress("London, Backer str., 221b")
            .withHomePhone("555 55 55").withMobilePhone("666 66 66").withWorkPhone("777 77 77")
            .withEmail("watson@test.test").withEmail2("watson2@test.test").withEmail3("watson3@test.test");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
