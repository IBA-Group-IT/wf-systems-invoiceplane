package com.ibagroup.wf.intelia.systems.invoiceplane;


import com.ibagroup.wf.intelia.core.config.ConfigurationManager;
import com.ibagroup.wf.intelia.core.robots.RobotProtocol;
import com.ibagroup.wf.intelia.core.security.SecureEntryDtoWrapper;
import com.ibagroup.wf.intelia.systems.invoiceplane.clients.InvoicePlaneClient;
import com.ibagroup.wf.intelia.systems.invoiceplane.pages.LoginPage;
import com.ibagroup.wf.intelia.systems.invoiceplane.pages.MainPage;
import com.ibagroup.wf.intelia.systems.invoiceplane.pages.MenuNavigationBar;

public interface InvoicePlaneRobot extends RobotProtocol {
	
    default MainPage initRobot(SecureEntryDtoWrapper loginCreds) {
    	InvoicePlaneClient client = new InvoicePlaneClient(getCfg());

        LoginPage loginPage = client.getLoginPage();
        storeCurrentMetadata();

        MainPage mainPage = loginPage.login(loginCreds);
        setMainPage(mainPage);
        storeCurrentMetadata();

        MenuNavigationBar menuNavigationBar = new MenuNavigationBar(getCfg());
        setMenuNavigationBar(menuNavigationBar);

        return mainPage;
    }

    default void finiliseRobot() {
        MenuNavigationBar menuNavigationBar = getMenuNavigationBar();
        if (menuNavigationBar != null) {
            getMenuNavigationBar().logout();

            setMenuNavigationBar(null);
        }
    }

    ConfigurationManager getCfg();

    MainPage getMainPage();
    void setMainPage(MainPage mainPage);

    MenuNavigationBar getMenuNavigationBar();
    void setMenuNavigationBar(MenuNavigationBar menuNavigationBar);

}