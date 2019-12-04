package com.ibagroup.wf.intelia.systems.invoiceplane.pages;

import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.pagefactory.Wait;
import com.ibagroup.wf.intelia.systems.invoiceplane.to.InvoiceItemTO;
import com.ibagroup.wf.intelia.systems.invoiceplane.to.InvoiceTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;

public class InvoicePage extends RobotDriverWrapper implements Constants {

    @FindBy(css = "input#invoice_discount_percent")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement invoice_discount_percent;

    @FindBy(css = ".btn_add_row")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement btn_add_row;

    @FindBy(css = "#btn_save_invoice")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement btn_save_invoice;

    @FindBy(css = "input#invoice_number")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement invoice_number;

    public String updateInvoice(InvoiceTO invoice) {

        Select payment_method_select = new Select(waitForElement(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "select#payment_method")), FIELD_WAIT_TIMEOUT));
        payment_method_select.selectByVisibleText(invoice.getPaymentMethod());

        invoice_discount_percent.sendKeys(String.valueOf(invoice.getInvoiceDiscount()));

        Iterator<InvoiceItemTO> i = invoice.getItems().iterator();
        while (i.hasNext()) {
            InvoiceItemTO item = i.next();
            waitForElement(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//*[@id='item_table']/tbody[last()]/tr[1]//input[@name='item_name']")), FIELD_WAIT_TIMEOUT).sendKeys(item.getName());
            waitForElement(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//*[@id='item_table']/tbody[last()]/tr[1]//input[@name='item_quantity']")), FIELD_WAIT_TIMEOUT)
                    .sendKeys(String.valueOf(item.getQuantity()));
            waitForElement(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//*[@id='item_table']/tbody[last()]/tr[1]//input[@name='item_price']")), FIELD_WAIT_TIMEOUT).sendKeys(item.getPrice()
                    .toString());
            waitForElement(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//*[@id='item_table']/tbody[last()]/tr[2]/td[1]/div/textarea[@name='item_description']")), FIELD_WAIT_TIMEOUT)
                    .sendKeys(item.getDescription());

            if (i.hasNext()) {
                btn_add_row.click();
            }
        }

        invoice_number.click();
        getDriver().getKeyboard().sendKeys(Keys.END);
        invoice_number.sendKeys(" [" + invoice.getClient() + "]");
        getDriver().getKeyboard().sendKeys(Keys.ENTER);

        btn_save_invoice.click();

        //Wait for save is completed
        try {
            waitForElement(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#fullpage-loader")), 10);
        } catch (Exception e) {
            // more that 5 items just hangs
            getDriver().navigate().refresh();
        }

        // The invoice is reloaded
        return waitForElement(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#invoice_number")),
                FIELD_WAIT_TIMEOUT).getAttribute("value");

    }

}
