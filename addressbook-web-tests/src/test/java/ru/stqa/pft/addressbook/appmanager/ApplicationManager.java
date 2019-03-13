package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  private WebDriver driver;
  private GroupHelper groupHelper;
  private NavigationHelper navigationHelper;
  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    System.setProperty("webdriver.chrome.driver", "d:/Work/learning/tools/chromedriver246.exe");
    System.setProperty("webdriver.gecko.driver", "d:/Work/learning/tools/geckodriver.exe");
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser.equals(BrowserType.CHROME)){
      driver = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)){
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)){
      driver = new InternetExplorerDriver();
    }
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    driver.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    contactHelper = new ContactHelper(driver);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    sessionHelper.logout();
    driver.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
