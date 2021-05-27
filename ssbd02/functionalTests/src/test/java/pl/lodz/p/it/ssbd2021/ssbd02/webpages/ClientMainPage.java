package pl.lodz.p.it.ssbd2021.ssbd02.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClientMainPage {

    protected WebDriver driver;
    private final By currentUser = By.id("usernameMain");
    private final By currentUsersLevel = By.id("currentLevel");

    public ClientMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getCurrentUser() {
        return currentUser;
    }

    public By getCurrentUsersLevel() {
        return currentUsersLevel;
    }
}
