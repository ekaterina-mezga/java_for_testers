package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class HelperBase {

  private WebDriver driver;

  public HelperBase(WebDriver driver) {
    this.driver = driver;
  }

  public void type(String elementName, String text) {
    driver.findElement(By.name(elementName)).click();
    if (text != null){
      String existingText = driver.findElement(By.name(elementName)).getAttribute("value");
      if (!text.equals(existingText)){
        driver.findElement(By.name(elementName)).clear();
        driver.findElement(By.name(elementName)).sendKeys(text);
      }
    }
  }

  public void attach(String elementName, File file) {
    if (file != null){
        driver.findElement(By.name(elementName)).sendKeys(file.getAbsolutePath());
    }
  }

  public void clickButtonByName(String buttonName) {
    driver.findElement(By.name(buttonName)).click();
  }

  public void clickButtonByXpath(String buttonXpath) {
    driver.findElement(By.xpath(buttonXpath)).click();
  }

  public void clickLinkText(String linkText) {
    driver.findElement(By.linkText(linkText)).click();
  }

  public void clickIcon(WebElement icon){
    icon.click();
  }

  public boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex){
      return false;
    }
  }
}
