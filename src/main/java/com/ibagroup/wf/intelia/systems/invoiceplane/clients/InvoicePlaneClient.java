package com.ibagroup.wf.intelia.systems.invoiceplane.clients;

import java.util.concurrent.TimeUnit;
import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.systems.invoiceplane.pages.LoginPage;

public class InvoicePlaneClient extends RobotDriverWrapper {

    public InvoicePlaneClient() {
		initDriver();
	}

    private void initDriver() {
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS).pageLoadTimeout(90, TimeUnit.SECONDS);
        getDriver().manage().deleteAllCookies();
    }

    public LoginPage getLoginPage() {
        getDriver().navigate().to(getCfg().getConfigItem("invoicePlane_site_url"));
        return getInjector().getInstance(LoginPage.class);
    }

}
