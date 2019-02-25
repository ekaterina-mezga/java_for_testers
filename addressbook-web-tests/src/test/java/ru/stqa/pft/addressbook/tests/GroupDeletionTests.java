package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion(){
    app.getNavigationHelper().goToGroupsPage();
    if (!app.getGroupHelper().isGroupExist()){
      app.getGroupHelper().createGroup(new GroupData("group1", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupsPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);    
  }

  @Test
  public void testAllGroupsDeletion(){
    app.getNavigationHelper().goToGroupsPage();
    if (! app.getGroupHelper().isGroupExist()){
      app.getGroupHelper().createGroup(new GroupData("group1", null, null));
    }
    app.getGroupHelper().selectAllGroups();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupsPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), 0);
  }
}
