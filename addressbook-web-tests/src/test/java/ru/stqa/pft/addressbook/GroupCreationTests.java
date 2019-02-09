package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {

    goToGroupsPage();
    initGroupCreation();
    fillGroupForm(new GroupData("group1", "groupheader1", "groupfooter1"));
    submitGroupCreation();
    returnToGroupsPage();
  }


}
