package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0){
      app.goTo().groupsPage();
      app.group().create(new GroupData().withName("group1"));
    }

    if (app.db().contacts().size() == 0) {
      Set<GroupData> group = new HashSet<GroupData>();
      group.add(app.db().groups().iterator().next());
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("Sherlock").withLastName("Holmes").withNickname("SH").withAddress("London, Backer str., 221b")
              .withHomePhone("222 22 22").withMobilePhone("333 33 33").withWorkPhone("444 44 44")
              .withEmail("sherlock@test.test").withEmail2("sherlock2@test.test").withEmail3("sherlock3@test.test").withGroups(group));
    }

    boolean contactsInGroups = false;
    for (ContactData contact : app.db().contacts()){
      if (contact.getGroups().size() > 0) {
        contactsInGroups = true;
        break;
      }
    }
    if (!contactsInGroups){
      app.goTo().homePage();
      app.contact().addToGroup(app.db().contacts().iterator().next(), app.db().groups().iterator().next());
    }
  }

  @Test
  public void testRemoveContactFromGroups(){
    int contactId = 0;
    Groups contactGroupsBefore = null;
    app.goTo().homePage();
    for (ContactData contact : app.db().contacts()){
      if (contact.getGroups().size() > 0) {
        contactGroupsBefore = contact.getGroups();
        contactId = contact.getId();
        break;
      }
    }
    GroupData group = contactGroupsBefore.iterator().next();
    Contacts groupContactsBefore = app.db().groupById(group.getId()).getContacts();

    app.contact().filterByGroup(group);
    app.contact().removeFromGroup(contactId);

    assertThat(app.db().contactById(contactId).getGroups(), equalTo(contactGroupsBefore.without(group)));

    assertThat(app.db().groupById(group.getId()).getContacts(),
            equalTo(groupContactsBefore.without(app.db().contactById(contactId))));
  }
}
