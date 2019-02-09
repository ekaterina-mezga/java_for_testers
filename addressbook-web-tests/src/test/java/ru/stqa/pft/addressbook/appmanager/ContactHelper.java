package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {

    private ChromeDriver driver;

    public ContactHelper(ChromeDriver driver){

        this.driver = driver;
    }

    public void returnToHomePage() {
        driver.findElement(By.linkText("home page")).click();
    }

    public void submitContactCreation() {
        driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillCreateContactForm(ContactData contactData) {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(contactData.getAddress());
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).clear();
        driver.findElement(By.name("home")).sendKeys(contactData.getHomePhone());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    public void initContactCreation() {
        driver.findElement(By.linkText("add new")).click();
    }
}
