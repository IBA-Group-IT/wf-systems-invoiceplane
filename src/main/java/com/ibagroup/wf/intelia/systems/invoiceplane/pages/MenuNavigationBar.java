package com.ibagroup.wf.intelia.systems.invoiceplane.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.pagefactory.Wait;
import com.ibagroup.wf.intelia.core.pagefactory.Wait.WaitFunc;


public class MenuNavigationBar extends RobotDriverWrapper implements Constants{

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = FIELD_WAIT_TIMEOUT)
    @FindBy(xpath = "//a[contains(@class,'logout')]")
    private WebElement logoutButton;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = FIELD_WAIT_TIMEOUT)
    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/a[text()='Dashboard']")
    private WebElement dashboardMenu;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = FIELD_WAIT_TIMEOUT)
    @FindBy(xpath = "//div[@id='ip-navbar-collapse']//span[text()='Clients']")
    private WebElement clientsMenu;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = FIELD_WAIT_TIMEOUT)
    @FindBy(xpath = "//div[@id='ip-navbar-collapse']//span[text()='Quotes']")
    private WebElement quotesMenu;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = FIELD_WAIT_TIMEOUT)
    @FindBy(xpath = "//div[@id='ip-navbar-collapse']//span[text()='Invoices']")
    private WebElement invoicesMenu;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = FIELD_WAIT_TIMEOUT)
    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/a//span[text()='Products']/parent::*")
    private WebElement productsMenu;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = FIELD_WAIT_TIMEOUT)
    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/ul/li/a[text()='View products']")
    private WebElement viewProductsMenuItem;

    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/ul/li/a[text()='Create product']")
    private WebElement createProductMenuItem;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = FIELD_WAIT_TIMEOUT)
    @FindBy(xpath = "//div[@id='ip-navbar-collapse']//span[text()='Payments']")
    private WebElement paymentsMenu;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = FIELD_WAIT_TIMEOUT)
    @FindBy(xpath = "//div[@id='ip-navbar-collapse']//span[text()='Reports']")
    private WebElement reportsMenu;

    public ProductsPage openProducts() {
    	productsMenu.click();
    	viewProductsMenuItem.click();

        return getInjector().getInstance(ProductsPage.class);
    }

    public CreateInvoiceDialog openCreateInvoice() {
        invoicesMenu.click();
        invoicesMenu.findElement(By.xpath("//li/a[text()='Create Invoice']")).click();
        return getInjector().getInstance(CreateInvoiceDialog.class);
    }

    public CreateProductPage openCreateProduct() {
        productsMenu.click();
        createProductMenuItem.click();

        return getInjector().getInstance(CreateProductPage.class);
    }

    public CreateClientPage openCreateClient() {
        clientsMenu.click();
        clientsMenu.findElement(By.xpath("//li/a[text()='Add Client']")).click();
        return getInjector().getInstance(CreateClientPage.class);
    }

    public ViewInvoicesPage openViewInvoices() {
        invoicesMenu.click();
        invoicesMenu.findElement(By.xpath("//li/a[text()='View Invoices']")).click();
        return getInjector().getInstance(ViewInvoicesPage.class);
    }

    public void openDashboard() {
    	dashboardMenu.click();
    }

    // If it is necessary to logout from InvoiceTO Plane explicitly
    public void logout() {
        try {
            logoutButton.click();
        } catch (TimeoutException ex) {
            getLogger().info("Timed out on waiting logout button");
        }
    }

}
