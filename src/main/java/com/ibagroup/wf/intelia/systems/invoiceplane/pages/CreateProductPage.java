package com.ibagroup.wf.intelia.systems.invoiceplane.pages;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.systems.invoiceplane.to.ProductTO;

public class CreateProductPage extends RobotDriverWrapper {

    @FindBy(id = "family_id")
    private WebElement family;
	
    @FindBy(id = "product_sku")
    private WebElement sku;

    @FindBy(id = "product_name")
    private WebElement productName;
    
    @FindBy(id = "product_description")
    private WebElement productDescription;

    @FindBy(id = "product_price")
    private WebElement productPrice;
    
    @FindBy(id = "tax_rate_id")
    private WebElement taxRate;

    @FindBy(xpath = "//button[@id='btn-submit']")
    private WebElement submit;
    
    @FindBy(xpath = "//div[@class='alert alert-danger']")
    private List<WebElement> addProductFailed;

    public ProductsPage addProduct(ProductTO product) {    	
    	family.click();
    	family.findElement(By.xpath("//option[contains(.,'" + product.getFamily() + "')]")).click();  	
    	    	
    	sku.click();
    	sku.clear();
    	sku.sendKeys(product.getSku());
    	
    	productName.click();
    	productName.clear();
    	productName.sendKeys(product.getProductName());
    	
    	productDescription.click();
    	productDescription.clear();
    	productDescription.sendKeys(product.getProductDescription());
    	
    	productPrice.click();
    	productPrice.clear();
    	productPrice.sendKeys(product.getPrice());
    	
    	taxRate.click();
    	taxRate.findElement(By.xpath("//option[contains(.,'" + product.getFamily() + "')]")).click();

        submit.click();
                
        try {
            if (addProductFailed.size() > 0) {
                getLogger().debug(String.valueOf(addProductFailed.size()));
                throw new RuntimeException(addProductFailed.get(0).getText() + "\n" + "Product: " + product.getProductName());
            }            
        } catch (TimeoutException e) {
            getLogger().debug("Unkown error during creation of Invoice Plane product");
            throw new RuntimeException();
        }
    
        return getInstance(ProductsPage.class);
    }
    
}