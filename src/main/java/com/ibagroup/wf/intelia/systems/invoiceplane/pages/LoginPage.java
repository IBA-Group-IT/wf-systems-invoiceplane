package com.ibagroup.wf.intelia.systems.invoiceplane.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.pagefactory.Wait;
import com.ibagroup.wf.intelia.core.pagefactory.Wait.WaitFunc;
import com.ibagroup.wf.intelia.core.security.SecureEntryDtoWrapper;
import com.ibagroup.wf.intelia.systems.InvalidLoginException;

public class LoginPage extends RobotDriverWrapper {

    private static final int WAIT_FIELD = 20;

    @FindBy(id = "email")
    @Wait(waitFunc = WaitFunc.CLICKABLE, value = WAIT_FIELD)
    private WebElement email;

    @FindBy(id = "password")
    @Wait(waitFunc = WaitFunc.CLICKABLE, value = WAIT_FIELD)
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    @Wait(waitFunc = WaitFunc.CLICKABLE, value = WAIT_FIELD)
    private WebElement submit;

    @Wait(waitFunc = WaitFunc.VISIBLE, value = 40)
    @FindBy(xpath = "//*[self::div[@id='main-area'] or self::div[@id='login']/div[contains(@class,'alert')]]")
    private WebElement loginFailed;

    @FindBy(xpath = "//div[@id='login']/div[contains(@class,'alert')]")
    @Wait(waitFunc = WaitFunc.VISIBLE, value = 5)
    private WebElement loginFailedMessage;

    public MainPage login(SecureEntryDtoWrapper invoicePlaneCred) {
        email.click();
        email.clear();
        email.sendKeys(invoicePlaneCred.getKey());

        password.click();
        password.clear();
        password.sendKeys(invoicePlaneCred.getValue());

        submit.click();

        try {
        	String actualId = loginFailed.getAttribute("id");
            getLogger().debug(actualId);
            if (!"main-area".equalsIgnoreCase(actualId)) {
                throw new InvalidLoginException(loginFailedMessage.getText() + "\n" + "User Id: " + invoicePlaneCred.getKey());
            }
        } catch (TimeoutException e) {
            getLogger().debug("Unkown error during Invoice Place authorisation process.");
            throw new RuntimeException();
        }

        return getInstance(MainPage.class);
    }

}