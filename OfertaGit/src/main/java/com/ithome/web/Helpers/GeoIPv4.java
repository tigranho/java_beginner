package com.ithome.web.Helpers;

import com.maxmind.geoip.LookupService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class GeoIPv4 {
    private static File dbfile;
    private static LookupService lookUp;

   /* static {
        try {
           *//* lookUp = new LookupService(
                    GeoIPv4.class.getResource("D:\\OfertaGit\\src\\main\\java\\com\\ithome\\web\\Resources\\GeoLiteCity.dat").getFile(),
                    LookupService.GEOIP_MEMORY_CACHE);*//*



            System.out.println("GeoIP Database loaded: " + lookUp.getDatabaseInfo());
        } catch (IOException e) {
            System.out.println("Could not load geo ip database: " + e.getMessage());
        }
    }*/

    public static GeoLocation getLocation(String ipAddress, HttpServletRequest request) throws IOException {
        dbfile = new File(request.getServletContext().getRealPath("/") +"dat/GeoLiteCity.dat");
        lookUp =new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);
        return GeoLocation.map(lookUp.getLocation(ipAddress));
    }

    public static GeoLocation getLocation(long ipAddress, HttpServletRequest request) throws IOException {
        dbfile = new File(request.getServletContext().getRealPath("/") +"dat/GeoLiteCity.dat");
        lookUp =new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);
        return GeoLocation.map(lookUp.getLocation(ipAddress));
    }

    public static GeoLocation getLocation(InetAddress ipAddress, HttpServletRequest request) throws IOException {
        lookUp =new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);

        return GeoLocation.map(lookUp.getLocation(ipAddress));
    }
}
