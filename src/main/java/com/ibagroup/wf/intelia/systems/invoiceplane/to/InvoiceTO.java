package com.ibagroup.wf.intelia.systems.invoiceplane.to;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class InvoiceTO {

    @Expose
    private String status;

    @Expose
    private String client;

    @Expose
    private Date invoiceDate;

    @Expose
    private Date dueDate;

    @Expose
    private String invoiceNumber;

    @Expose
    private String paymentMethod;

    @Expose
    private String invoiceGroup;

    @Expose
    private Integer invoiceDiscount;

    @Expose
    private BigDecimal amount;

    @Expose
    private BigDecimal balance;

    private Collection<InvoiceItemTO> items = new ArrayList<>();


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Collection<InvoiceItemTO> getItems() {
        return items;
    }

    public void setItems(Collection<InvoiceItemTO> items) {
        this.items = items;
    }

    public String getInvoiceGroup() {
        return invoiceGroup;
    }

    public void setInvoiceGroup(String invoiceGroup) {
        this.invoiceGroup = invoiceGroup;
    }

    public Integer getInvoiceDiscount() {
        return invoiceDiscount;
    }

    public void setInvoiceDiscount(Integer invoiceDiscount) {
        this.invoiceDiscount = invoiceDiscount;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
