package com.ithome.web.ExChangeRateController;

public class GetCurrentRateController {
    public String getCurrentRate(String url, String xmlBody) {
        return getCurrancyFromWeb
                .getInstance()
                .post(url, xmlBody);
    }
}
