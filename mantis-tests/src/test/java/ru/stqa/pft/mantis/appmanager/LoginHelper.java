package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

  public LoginHelper(ApplicationManager app){
    super(app);
  }

  public void loginAs(String username, String password) {
    driver.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void changePassword(String confirmationLink, String password) {
    driver.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }



}
