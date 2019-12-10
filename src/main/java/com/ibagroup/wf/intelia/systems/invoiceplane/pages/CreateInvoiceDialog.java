package com.ibagroup.wf.intelia.systems.invoiceplane.pages;

import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.pagefactory.Wait;
import com.ibagroup.wf.intelia.systems.invoiceplane.to.InvoiceTO;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;

public class CreateInvoiceDialog extends RobotDriverWrapper implements Constants {

    @FindBy(id = "select2-client_name-container")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement client_name_container;

    @FindBy(css = "input.select2-search__field")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement client_name;

    @FindBy(name = "invoice_date_created")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement invoice_date;

    @FindBy(name = "invoice_group_id")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement invoice_group_id;

    @FindBy(css = "button#invoice_create_confirm")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement invoice_create_confirm;

    public InvoicePage create(InvoiceTO invoice) {

        client_name_container.click();
        client_name.sendKeys(invoice.getClient());
        getDriver().getKeyboard().sendKeys(Keys.ENTER);

        invoice_date.clear();
        invoice_date.sendKeys(new SimpleDateFormat("d/MM/YYYY").format(invoice.getInvoiceDate()));
        getDriver().getKeyboard().sendKeys(Keys.ENTER);

        Select invoice_group_id_select = new Select(invoice_group_id);
        invoice_group_id_select.selectByVisibleText(invoice.getInvoiceGroup());

        invoice_create_confirm.click();

        return getInjector().getInstance(InvoicePage.class);
    }

}
