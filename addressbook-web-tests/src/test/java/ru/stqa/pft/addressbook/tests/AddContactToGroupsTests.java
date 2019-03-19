package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("Sherlock").withLastName("Holmes").withNickname("SH").withAddress("London, Backer str., 221b")
              .withHomePhone("222 22 22").withMobilePhone("333 33 33").withWorkPhone("444 44 44")
              .withEmail("sherlock@test.test").withEmail2("sherlock2@test.test").withEmail3("sherlock3@test.test"));
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupsPage();
      app.group().create(new GroupData().withName("group1"));
    }
  }

  @Test
  public void testAddContactToGroups() {
    ContactData contact = app.db().contacts().iterator().next();
    Groups contactGroupsBefore = contact.getGroups();
    Groups availableGroups = app.db().groups();
    GroupData groupToAdd;
    if (contactGroupsBefore.size() > 0){
      if (contactGroupsBefore.equals(availableGroups)){
        app.goTo().groupsPage();
        app.group().create(new GroupData().withName("new_group"));
        availableGroups = app.db().groups();
      }
      for (GroupData group : contactGroupsBefore) {
          availableGroups = availableGroups.without(group);
      }
    }
    groupToAdd = availableGroups.iterator().next();
    Contacts groupContactsBefore = app.db().groupById(groupToAdd.getId()).getContacts();

    app.goTo().homePage();
    app.contact().addToGroup(contact, groupToAdd);

    assertThat(app.db().contactById(contact.getId()).getGroups(),
            equalTo(contactGroupsBefore.withAdded(groupToAdd)));

    assertThat(app.db().groupById(groupToAdd.getId()).getContacts(),
            equalTo(groupContactsBefore.withAdded(contact)));
  }
}
