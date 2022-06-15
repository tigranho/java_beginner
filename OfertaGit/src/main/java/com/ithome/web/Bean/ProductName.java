package com.ithome.web.Bean;

import java.util.ArrayList;
import java.util.List;

public class ProductName {
    private int productid;
    private int productCode;
    private String productNameAm;
    private String prodeuctNameEn;
    private String productNameRu;
    private List<ProductName> productNames = new ArrayList<>();

    public ProductName(int productid, int productCode, String productNameAm, String prodeuctNameEn, String productNameRu) {
        this.productid = productid;
        this.productCode = productCode;
        this.productNameAm = productNameAm;
        this.prodeuctNameEn = prodeuctNameEn;
        this.productNameRu = productNameRu;
    }

    public ProductName(int productCode, String productName, String prodeuctNameEn, String productNameRu) {
        this.productCode = productCode;
        this.productNameAm = productName;
        this.prodeuctNameEn = prodeuctNameEn;
        this.productNameRu = productNameRu;
    }

    public ProductName() {
    }

    public List<ProductName> getProductNames() {
        return productNames;
    }

    public void setProductNames(List<ProductName> productNames) {
        this.productNames = productNames;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductNameAm() {
        return productNameAm;
    }

    public void setProductNameAm(String productNameAm) {
        this.productNameAm = productNameAm;
    }

    public String getProdeuctNameEn() {
        return prodeuctNameEn;
    }

    public void setProdeuctNameEn(String prodeuctNameEn) {
        this.prodeuctNameEn = prodeuctNameEn;
    }

    public String getProductNameRu() {
        return productNameRu;
    }

    public void setProductNameRu(String productNameRu) {
        this.productNameRu = productNameRu;
    }
}
