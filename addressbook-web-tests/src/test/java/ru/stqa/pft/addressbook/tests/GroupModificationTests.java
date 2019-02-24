package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().goToGroupsPage();
    if (!app.getGroupHelper().isGroupExist()){
      app.getGroupHelper().createGroup(new GroupData("group1", null, null));
    }
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("edit_group1", "edit_groupheader1", "edit_groupfooter1"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupsPage();

    int after = app.getGroupHelper().getGroupCount();

    Assert.assertEquals(after, before);
  }
}
