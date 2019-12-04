package com.ibagroup.wf.intelia.systems.invoiceplane.pages;

import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.pagefactory.Wait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateClientPage extends RobotDriverWrapper implements Constants {

    @FindBy(css = "input#client_name")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement client_name;

    @FindBy(css = "button#btn-submit")
    @Wait(waitFunc = Wait.WaitFunc.CLICKABLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement btn_save;

    public void create(String clientName) {

        client_name.sendKeys(clientName);
        getDriver().getKeyboard().sendKeys(Keys.ENTER);

        btn_save.click();
        //Wait for save is completed
        //waitForElement(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#fullpage-loader")), 10);
    }

}
