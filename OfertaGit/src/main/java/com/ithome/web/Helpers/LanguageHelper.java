package com.ithome.web.Helpers;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.Locale;

public class LanguageHelper {
    private String[] pLangauge;
    private String language = null;
    private String country = null;
    private NumberFormat numberFormat = null;

    public String Pagelanguage(HttpServletRequest request, String Pagelanguage) {
        if (request.getParameter("Pagelanguage") != null) {
            pLangauge = Pagelanguage.split("_");

            language = language(pLangauge);
            country = Country(pLangauge);

            Locale locale =  getLocale(language,country);
        }else{
            language = "hy";
            country = "AM";
            Locale locale = getLocale(language,country);
        }
        numberFormat = getNumberFormat();
        return language;
    }

    private Locale getLocale(String language, String country) {
        return new Locale(language, country);
    }

    private String language(String[] pLangauge) {
        return pLangauge[0];
    }

    private String Country(String[] pLangauge) {
        return pLangauge[1];
    }

    private NumberFormat getNumberFormat() {
        return NumberFormat.getNumberInstance();
    }
}
