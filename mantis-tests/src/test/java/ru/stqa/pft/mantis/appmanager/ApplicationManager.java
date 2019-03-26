package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  private WebDriver driver;
  private String browser;
  private RegistrationHelper registrationHelper;
  private LoginHelper loginHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private ManageHelper manageHelper;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver"));
    System.setProperty("webdriver.gecko.driver", properties.getProperty("firefox.driver"));
  }

  public void stop() {
    if (driver != null){
      driver.quit();
    }
  }

  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null){
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public LoginHelper login(){
    if (loginHelper == null){
      loginHelper = new LoginHelper(this);
    }
    return loginHelper;
  }

  public ManageHelper manage(){
    if (manageHelper == null){
      manageHelper = new ManageHelper(this);
    }
    return manageHelper;
  }

  public FtpHelper ftp(){
    if (ftp == null){
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public WebDriver getDriver() {
    if (driver == null){
      if (browser.equals(BrowserType.CHROME)){
        driver = new ChromeDriver();
      } else if (browser.equals(BrowserType.FIREFOX)){
        driver = new FirefoxDriver();
      } else if (browser.equals(BrowserType.IE)){
        driver = new InternetExplorerDriver();
      }
      driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      driver.get(properties.getProperty("web.baseUrl"));
    }
    return driver;
  }

  public MailHelper mail(){
    if (mailHelper == null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public  DbHelper db(){
    if (dbHelper == null){
      dbHelper = new DbHelper(this);
    }
    return dbHelper;
  }
}
