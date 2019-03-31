package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException {

    String password = "password123";
    app.login().loginAs(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.manage().manageUsers();
    List<User> users = app.db().users();
    Iterator<User> it = users.iterator();
    User user = null;
    while (it.hasNext()){
      User currUser = it.next();
      if (! currUser.getUsername().equals("administrator")){
        user = currUser;
        break;
      }
    }
    if (user == null){
      throw new Error("Please create a user first!");
    }
    String email = user.getEmail();
    app.manage().initManageUser(user.getId());
    app.manage().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String changePasswordLink = findChangePasswordLink(mailMessages, email);
    app.login().changePassword(changePasswordLink, password);
    assertTrue(app.newSession().login(user.getUsername(), password));

  }

  private String findChangePasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return  regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
