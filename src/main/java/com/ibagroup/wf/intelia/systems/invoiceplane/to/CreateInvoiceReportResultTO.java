package com.ibagroup.wf.intelia.systems.invoiceplane.to;

public class CreateInvoiceReportResultTO {

    private byte[] screenshot;
    private byte[] reportCsv;

    public CreateInvoiceReportResultTO(byte[] screenshot, byte[] reportCsv) {
        this.screenshot = screenshot;
        this.reportCsv = reportCsv;
    }

    public byte[] getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(byte[] screenshot) {
        this.screenshot = screenshot;
    }

    public byte[] getReportCsv() {
        return reportCsv;
    }

    public void setReportCsv(byte[] reportCsv) {
        this.reportCsv = reportCsv;
    }
}
