package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupsPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupData().withName("group1"));
    }
  }

  @Test
  public void testGroupDeletion(){
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

  @Test
  public void testAllGroupsDeletion(){
    app.group().deleteAll();
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(0));
  }
}
