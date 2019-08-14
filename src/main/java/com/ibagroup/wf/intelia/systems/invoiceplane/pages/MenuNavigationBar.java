package com.ibagroup.wf.intelia.systems.invoiceplane.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.pagefactory.Wait;
import com.ibagroup.wf.intelia.core.pagefactory.Wait.WaitFunc;


public class MenuNavigationBar extends RobotDriverWrapper {

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = 40)
    @FindBy(xpath = "//a[contains(@class,'logout')]")
    private WebElement logoutButton;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = 40)
    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/a[text()='Dashboard']")
    private WebElement dashboardMenu;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = 40)
    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/a//span[text()='Products']/parent::*")
    private WebElement productsMenu;

    @Wait(waitFunc = WaitFunc.CLICKABLE, value = 40)
    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/ul/li/a[text()='View products']")
    private WebElement viewProductsMenuItem;

    @FindBy(xpath = "//*[@id='ip-navbar-collapse']//li/ul/li/a[text()='Create product']")
    private WebElement createProductMenuItem;


    public ProductsPage openProducts() {
    	productsMenu.click();
    	viewProductsMenuItem.click();

        return getInjector().getInstance(ProductsPage.class);
    }

    public CreateProductPage openCreateProduct() {
        productsMenu.click();
        createProductMenuItem.click();

        return getInjector().getInstance(CreateProductPage.class);
    }

    public void openDashboard() {
    	dashboardMenu.click();
    }

    // If it is necessary to logout from Invoice Plane explicitly
    public void logout() {
        try {
            logoutButton.click();
        } catch (TimeoutException ex) {
            getLogger().info("Timed out on waiting logout button");
        }
    }

}
