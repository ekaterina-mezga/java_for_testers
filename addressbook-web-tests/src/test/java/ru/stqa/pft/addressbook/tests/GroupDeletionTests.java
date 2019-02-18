package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion(){
    app.getNavigationHelper().goToGroupsPage();
    if (!app.getGroupHelper().isGroupExist()){
      app.getGroupHelper().createGroup(new GroupData("group1", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupsPage();
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
  }
}
