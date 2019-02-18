package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion(){
    app.getNavigationHelper().goToGroupsPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getNavigationHelper().returnToGroupsPage();
  }

  @Test
  public void testAllGroupsDeletion(){
    app.getNavigationHelper().goToGroupsPage();
    app.getGroupHelper().selectAllGroups();
    app.getGroupHelper().deleteSelectedGroups();
    app.getNavigationHelper().returnToGroupsPage();
  }
}
