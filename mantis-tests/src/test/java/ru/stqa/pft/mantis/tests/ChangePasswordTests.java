package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException {
    String user = "user7";
    String password = "password123";
    String email = "user7@test.test";
    app.login().loginAs(user, password);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String changePasswordLink = findChangePasswordLink(mailMessages, email);
    app.login().changePassword(changePasswordLink, password);
    assertTrue(app.newSession().login(user, password));

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
