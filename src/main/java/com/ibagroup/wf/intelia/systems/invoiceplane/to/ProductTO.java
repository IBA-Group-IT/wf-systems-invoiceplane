package com.ibagroup.wf.intelia.systems.invoiceplane.to;

import com.freedomoss.workfusion.utils.gson.GsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibagroup.wf.intelia.core.mis.LoggableDetail;
import com.ibagroup.wf.intelia.core.to.BaseTO;

public class ProductTO extends BaseTO {

    @LoggableDetail(name = "index")
    @SerializedName("index")
    @Expose
    private long index;

    @LoggableDetail(name = "family")
	@SerializedName("family")
	@Expose
	private String family;

    @LoggableDetail(name = "sku")
	@SerializedName("sku")
	@Expose
	private String sku;

    @LoggableDetail(name = "product_name")
	@SerializedName("product_name")
	@Expose
	private String productName;

    @LoggableDetail(name = "product_description")
	@SerializedName("product_description")
	@Expose
	private String productDescription;

    @LoggableDetail(name = "price")
	@SerializedName("price")
	@Expose
	private String price;

	@SerializedName("tax_rate")
	@Expose
	private String taxRate;

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String toJson() {
        return GsonUtils.GSON.toJson(this);
    }

}