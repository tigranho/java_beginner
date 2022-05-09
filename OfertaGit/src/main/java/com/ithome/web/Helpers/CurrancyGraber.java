package com.ithome.web.Helpers;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrancyGraber extends Thread {
    @Override
    public void run() {
        super.run();
        getExchange();
    }

    public String getExchange(){
        StringBuilder response = new StringBuilder();
        try {
            String request = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "    <ExchangeRatesLatest xmlns=\"http://www.cba.am/\" />\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>";

            URL url = new URL("http://api.cba.am/exchangerates.asmx");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set timeout as per needs
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);

            // Set DoOutput to true if you want to use URLConnection for output.
            // Default is false
            connection.setDoOutput(true);

            connection.setUseCaches(true);
            connection.setRequestMethod("POST");

            // Set Headers
            connection.setRequestProperty("Content-Type","text/xml");

            // Write XML
            OutputStream outputStream = connection.getOutputStream();
            byte[] b = request.getBytes("UTF-8");
            outputStream.write(b);
            outputStream.flush();
            outputStream.close();

            // Read XML
            InputStream inputStream = connection.getInputStream();
            byte[] res = new byte[2048];
            int i = 0;

            while((i =inputStream.read(res))!=-1)

            {
                response.append(new String(res, 0, i));
            }
            inputStream.close();

            System.out.println("Response= "+response.toString());
        } catch (Exception e) {
            System.out.println();
        }
        return response.toString();
    }

}
