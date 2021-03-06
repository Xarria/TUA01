package pl.lodz.p.it.ssbd2021.ssbd02.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountDetailsPage {

    protected WebDriver driver;
    private final By profileTable = By.id("accountDetails");
    private final By loginField = By.id("login");
    private final By firstNameField = By.id("firstName");
    private final By lastNameField = By.id("lastName");
    private final By emailField = By.id("email");
    private final By adminFields = By.id("adminFields");
    private final By accessLevelsField = By.id("accessLevels");
    private final By phoneNumberField = By.id("phoneNumber");
    private final By label = By.tagName("label");
    private final By editBtn = By.id("edit-button");
    private final By changePasswordBtn = By.id("change-password");
    private final By changeEmailBtn = By.id("change-email");

    public AccountDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public ChangePasswordPage openChangePasswordPage() {
        driver.findElement(changePasswordBtn).click();
        return new ChangePasswordPage(driver);
    }

    public ChangeEmailPage openChangeEmailPage() {
        driver.findElement(changeEmailBtn).click();
        return new ChangeEmailPage(driver);
    }

    public EditUserProfilePage openEditUserProfilePage() {
        driver.findElement(editBtn).click();
        return new EditUserProfilePage(driver);
    }

    public boolean areProperFieldsDisplayed(String role) {
        switch (role) {
            case "ADMIN":
                return areCommonFieldsDisplayed() &&
                        driver.findElement(adminFields).isDisplayed() &&
                        driver.findElement(accessLevelsField).isDisplayed();
            case "CLIENT":
            case "EMPLOYEE":
                return areCommonFieldsDisplayed();
        }
        return false;
    }

    private boolean areCommonFieldsDisplayed() {
        return driver.findElement(profileTable).isDisplayed() &&
                driver.findElement(loginField).isDisplayed() &&
                driver.findElement(firstNameField).isDisplayed() &&
                driver.findElement(lastNameField).isDisplayed() &&
                driver.findElement(emailField).isDisplayed() &&
                driver.findElement(phoneNumberField).isDisplayed();
    }

    public List<String> getData() {
        List<String> allLabels =  driver.findElements(label).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return IntStream.range(0, allLabels.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(allLabels::get)
                .map(String::toString)
                .collect(Collectors.toList());
    }

    public By getFirstNameField() {
        return firstNameField;
    }

    public By getLastNameField() {
        return lastNameField;
    }

    public By getPhoneNumberField() {
        return phoneNumberField;
    }

    public By getChangePasswordBtn() {
        return changePasswordBtn;
    }
}
