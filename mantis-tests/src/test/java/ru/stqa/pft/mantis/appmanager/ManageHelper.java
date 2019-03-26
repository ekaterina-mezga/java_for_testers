package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ManageHelper extends HelperBase{

  public ManageHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUsers(){
    click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php'"));
  }

  public void initManageUser(int id){
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s", id)));
  }
}
