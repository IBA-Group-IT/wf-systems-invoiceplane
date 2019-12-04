package com.ibagroup.wf.intelia.systems.invoiceplane;

import com.ibagroup.wf.intelia.core.Injector;
import com.ibagroup.wf.intelia.core.config.ConfigurationManager;
import com.ibagroup.wf.intelia.core.config.ConfigurationManager.Formatter;
import com.ibagroup.wf.intelia.core.security.SecureEntryDtoWrapper;
import com.ibagroup.wf.intelia.core.security.SecurityUtils;
import com.ibagroup.wf.intelia.systems.invoiceplane.clients.InvoicePlaneClient;
import com.ibagroup.wf.intelia.systems.invoiceplane.pages.*;
import com.ibagroup.wf.intelia.systems.invoiceplane.to.CreateInvoiceResultTO;
import com.ibagroup.wf.intelia.systems.invoiceplane.to.InvoiceTO;
import com.ibagroup.wf.intelia.systems.invoiceplane.to.ProductTO;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InvoicePlaneSystem {

    public static final String INVOICEPLANE_CREDENTIALS_ALIAS = "invoice_plane";

    @Inject
    private Logger logger;

    @Inject
    private SecurityUtils securityUtils;

    @Inject
    private ConfigurationManager cfg;

    @Inject
    private Injector injector;

    public ProductsPage addProduct(ProductTO product) {
        MenuNavigationBar menuNavigationBar = initSystem();
        CreateProductPage createProductPage = menuNavigationBar.openCreateProduct();
        ProductsPage productsPage = createProductPage.addProduct(product);
        logger.debug("Adding product: " + product.getProductName());
        finaliseSystem(menuNavigationBar);
        return productsPage;
    }

    public CreateInvoiceResultTO createInvoice(InvoiceTO invoice) {
        MenuNavigationBar menuNavigationBar = initSystem();
        CreateInvoiceDialog createInvoiceDialog = menuNavigationBar.openCreateInvoice();
        InvoicePage invoicePage = createInvoiceDialog.create(invoice);
        String invoice_number = invoicePage.updateInvoice(invoice);
        return new CreateInvoiceResultTO(invoice_number);
    }

    public List<ProductTO> parseProducts() {
        MenuNavigationBar menuNavigationBar = initSystem();
        int expectedProductsCount = cfg.getConfigItem("products_count", 20, Formatter.INT).intValue();
        // click Products -> View products
        ProductsPage productsPage = menuNavigationBar.openProducts();
        List<ProductTO> products = new ArrayList<ProductTO>();
        while (true) {
            products.addAll(productsPage.getProducts());
            products = products.stream().filter(distinctByKey(p -> p.getProductName().toLowerCase())).collect(Collectors.toList());

            if (products.size() > expectedProductsCount || !productsPage.nextPage()) {
                break;
            }
        }
        // leave only first 20 products
        if (expectedProductsCount < products.size()) {
            products.subList(expectedProductsCount, products.size()).clear();
        }
        logger.debug("Extracted products count: " + products.size());
        finaliseSystem(menuNavigationBar);

        return products;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public MenuNavigationBar initSystem() {
        LoginPage loginPage = injector.getInstance(InvoicePlaneClient.class).getLoginPage();
        SecureEntryDtoWrapper loginCreds = getLoginCreds();
        loginPage.login(loginCreds);
        return injector.getInstance(MenuNavigationBar.class);
    }

    private SecureEntryDtoWrapper getLoginCreds() {
        final SecureEntryDtoWrapper secureEntry = securityUtils.getSecureEntry(INVOICEPLANE_CREDENTIALS_ALIAS);

        if (secureEntry == null) {
            throw new IllegalStateException("Could not get credentials from Secret Vault. Please set 'admin@workfusion.com' as key, 'o66Lc1Jn6Z' as value for '"
                    + INVOICEPLANE_CREDENTIALS_ALIAS + "' alias in your Secret Vault");
        }
        return secureEntry;
    }

    public void finaliseSystem(MenuNavigationBar menuNavigationBar) {
        if (menuNavigationBar != null) {
            menuNavigationBar.logout();
        }
    }

}
