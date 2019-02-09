package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper extends BaseHelper{

    private ChromeDriver driver;

    public NavigationHelper(ChromeDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void goToGroupsPage(){
      clickLinkText("groups");
    }
}
