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
        Groups groupsToAdd = app.db().groups();
        if (contactGroupsBefore.size()>0){
            for (GroupData group : contactGroupsBefore){
                groupsToAdd = groupsToAdd.without(group);
            }
        }
        app.goTo().homePage();
        GroupData groupToAdd = groupsToAdd.iterator().next();
        app.contact().addToGroup(contact, groupToAdd);
        Groups actualGroups = contact.getGroups();
        Groups expectedGroups = contactGroupsBefore.withAdded(groupToAdd);
        assertThat(actualGroups, equalTo(expectedGroups));
    }
}
