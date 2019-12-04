package com.ibagroup.wf.intelia.systems.invoiceplane.pages;

import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.pagefactory.Wait;
import com.ibagroup.wf.intelia.systems.invoiceplane.to.InvoiceTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ViewInvoicesPage extends RobotDriverWrapper implements Constants {

    @FindBy(css = "input#filter")
    @Wait(waitFunc = Wait.WaitFunc.VISIBLE, value = FIELD_WAIT_TIMEOUT)
    private WebElement filter_field;

    @FindBy(xpath = "//div[@id='headerbar']/div[@class='pull-right']//a[@title='First' and not(contains(@class,'disabled'))]")
    private WebElement firstPage;

    @FindBy(xpath = "//div[@id='headerbar']/div[@class='pull-right']//a[@title='Last' and not(contains(@class,'disabled'))]")
    private WebElement lastPage;

    @FindBy(xpath = "//div[@id='headerbar']/div[@class='pull-right']//a[@title='Next' and not(contains(@class,'disabled'))]")
    private WebElement nextPage;

    @FindBy(xpath = "//div[@id='headerbar']/div[@class='pull-right']//a[@title='Prev' and not(contains(@class,'disabled'))]")
    private WebElement prevPage;

    public List<InvoiceTO> getInvoices(String filterText) {

        filter_field.sendKeys(filterText);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        List<WebElement> products = getDriver().findElements(By.xpath("//div[@id='content']//table/tbody/tr"));

        List<InvoiceTO> result = new ArrayList<>();
        if (products != null) {
            result = products.stream().map(product -> {
                Document doc = Jsoup.parse(product.getAttribute("outerHTML"), "", Parser.xmlParser());

                InvoiceTO invoice = new InvoiceTO();
                invoice.setStatus(doc.select("td:nth-child(1)").text());
                invoice.setInvoiceNumber(doc.select("td:nth-child(2)").text());
                invoice.setInvoiceDate(parseDate(doc.select("td:nth-child(3)").text()));
                invoice.setDueDate(parseDate(doc.select("td:nth-child(4)").text()));
                invoice.setClient(doc.select("td:nth-child(5)").text());
                invoice.setAmount(parseMoney(doc.select("td:nth-child(6)").text()));
                invoice.setBalance(parseMoney(doc.select("td:nth-child(7)").text()));

                return invoice;

            }).collect(Collectors.toList());
        }

        return result;
    }

    private Date parseDate(String text) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(text);
        } catch (Exception e) {
        }
        return null;
    }

    private BigDecimal parseMoney(String text) {
        try {
            return new BigDecimal(text.replaceAll("[^\\d.]", ""));
        } catch (Exception e) {
        }
        return BigDecimal.ZERO;
    }

    public boolean firstPage() {
        try {
            firstPage.click();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean lastPage() {
        try {
            lastPage.click();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean nextPage() {
        try {
            nextPage.click();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean prevPage() {
        try {
            prevPage.click();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }


}