package com.ibagroup.wf.intelia.systems.invoiceplane.to;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class InvoiceItemTO {

    @Expose
    private String name;

    @Expose
    private BigDecimal quantity;

    @Expose
    private BigDecimal price;

    @Expose
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
