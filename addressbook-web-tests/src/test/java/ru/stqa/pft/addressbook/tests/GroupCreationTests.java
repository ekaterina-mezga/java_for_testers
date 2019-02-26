package ru.stqa.pft.addressbook.tests;

import com.sun.source.tree.IfTree;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation(){
    app.getNavigationHelper().goToGroupsPage();

    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("group1", null, null);

    app.getGroupHelper().createGroup(group);

    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (GroupData g : after){
      if (g.getId() > max){
        max = g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals((new HashSet<Object>(before)), new HashSet<Object>(after));
  }
}
