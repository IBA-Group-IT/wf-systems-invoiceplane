package com.ibagroup.wf.intelia.systems.invoiceplane.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.pagefactory.Wait;
import com.ibagroup.wf.intelia.core.pagefactory.Wait.WaitFunc;

public class MainPage extends RobotDriverWrapper {

    @FindBy(xpath = "//div[@id='panel-quick-actions']//a//span[text()='Add Client']/parent::*")
    @Wait(waitFunc = WaitFunc.CLICKABLE, value = 20)
    private WebElement addClientButton;

    public void addClient() {
    	addClientButton.click();
    }
}
