package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation(){
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/icon.png ");
    ContactData contact = new ContactData()
            .withFirstName("Sherlock").withLastName("Holmes").withAddress("London, Backer str., 221b")
            .withHomePhone("222 22 22").withMobilePhone("333 33 33").withWorkPhone("444 44 44")
            .withEmail("sherlock@test.test").withEmail2("sherlock2@test.test").withEmail3("sherlock3@test.test").withGroup("group1").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
           before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
