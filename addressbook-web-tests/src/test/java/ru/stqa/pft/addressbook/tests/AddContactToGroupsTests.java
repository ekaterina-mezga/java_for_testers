package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class AddContactToGroupsTests extends TestBase {

    @Test
    public void testAddContactToGroups(){
        ContactData contact = app.db().contacts().iterator().next();
        Groups contactGroupsBefore = contact.getGroups();
        Groups availableGroups = app.db().groups();
        if (contactGroupsBefore.size()>0){
            for (GroupData group : contactGroupsBefore){
                availableGroups = availableGroups.without(group);
            }
        }
        GroupData groupToAdd = availableGroups.iterator().next();
        app.goTo().homePage();
        app.contact().addToGroup(contact, groupToAdd);
        contact = app.db().contactById(contact.getId());
        Groups actualGroups = contact.getGroups();
        Groups expectedGroups = contactGroupsBefore.withAdded(groupToAdd);
        assertThat(actualGroups, equalTo(expectedGroups));
    }
}
