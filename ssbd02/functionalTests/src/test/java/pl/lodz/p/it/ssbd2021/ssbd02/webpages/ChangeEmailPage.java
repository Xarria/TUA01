package pl.lodz.p.it.ssbd2021.ssbd02.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ChangeEmailPage {

    protected WebDriver driver;
    private final By newEmailField = By.id("new-email");
    private final By newEmailRepeatField = By.id("new-email-repeat");
    private final By confirmButton = By.id("confirm");
    private final By changeEmailForm = By.id("change-email-form");
    private final By invalidFormatError = By.id("invalid-format-error");
    private final By differentEmailsError = By.id("different-emails-error");
    private final By existingEmailError = By.id("existing-email-error");

    public ChangeEmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getChangeEmailForm() {
        return changeEmailForm;
    }

    public By getConfirmButton() {
        return confirmButton;
    }

    public By getInvalidFormatError() {
        return invalidFormatError;
    }

    public By getDifferentEmailsError() {
        return differentEmailsError;
    }

    public By getExistingEmailError() {
        return existingEmailError;
    }

    public void changeEmail(String newEmail, String newEmailRepeat) {
        driver.findElement(newEmailField).sendKeys(newEmail);
        driver.findElement(newEmailRepeatField).sendKeys(newEmailRepeat);
        driver.findElement(newEmailRepeatField).sendKeys(Keys.TAB);
    }
}
