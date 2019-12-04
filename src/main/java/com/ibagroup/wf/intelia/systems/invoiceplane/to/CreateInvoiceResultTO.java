package com.ibagroup.wf.intelia.systems.invoiceplane.to;

public class CreateInvoiceResultTO {

    String invoiceNumber;

    public CreateInvoiceResultTO(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}