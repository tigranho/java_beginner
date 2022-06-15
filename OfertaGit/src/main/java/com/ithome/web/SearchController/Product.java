package com.ithome.web.SearchController;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String name;
    private String price;
    private String pageName;

    public Product() {
    }

    public Product(String id, String name, String price,String pageName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pageName = pageName;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
