package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupsPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupsPage();
  }

  @Test
  public void testAllGroupsDeletion(){
    app.getNavigationHelper().goToGroupsPage();
    app.getGroupHelper().selectAllGroups();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupsPage();
  }

}
