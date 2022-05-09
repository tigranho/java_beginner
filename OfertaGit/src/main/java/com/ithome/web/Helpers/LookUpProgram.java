package com.ithome.web.Helpers;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;

public class LookUpProgram {
    public static String start(HttpServletRequest request) throws IOException {
        String locationName = null;
        getTheFile(request);

        String ip = request.getHeader("X-Forwarded-For");
        long ipAddress = new BigInteger(InetAddress.getByName(ip).getAddress()).longValue();

        System.out.println("By String IP address: \n" +  GeoIPv4.getLocation(ip,request));

        System.out.println("By long IP address: \n" +
                GeoIPv4.getLocation(ipAddress,request));

        System.out.println("By InetAddress IP address: \n" +
                GeoIPv4.getLocation(InetAddress.getByName(ip),request));
        String location = String.valueOf(GeoIPv4.getLocation(ipAddress, request));
        String[] myLocation = location.split("'");
        for (int i = 0; i < myLocation.length; i++) {
            locationName = myLocation[3];

        }
        return locationName;

      /*  String ip = request.getHeader("X-Forwarded-For");
        long ipAddress = new BigInteger(InetAddress.getByName(ip).getAddress()).longValue();

        //File dbfile = new File("D:\\OfertaGit\\src\\main\\java\\com\\ithome\\web\\LocationData\\GeoLiteCity.dat");
        //File dbfile = new File("D:\\OfertaGit\\src\\main\\java\\com\\ithome\\web\\LocationData\\GeoLiteCity.dat");

        File dbfile = new File(request.getServletContext().getRealPath("/") +"dat/GeoLiteCity.dat");


        LookupService lookupService = new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);

        Location location = lookupService.getLocation(ipAddress);

        if (location != null || ip != null) {
            location.region = regionName.regionNameByCode(location.countryCode, location.region);
            System.out.println(location.region);
            System.out.println(location.countryCode);
        }else{
            location = lookupService.getLocation("37.252.95.158");
            location.region = regionName.regionNameByCode(location.countryCode, location.region);
            System.out.println(location.region);
            System.out.println(location.countryCode);
        }
        return location.region;*/
     /* return null;*/
    }

    private static File getTheFile(HttpServletRequest request) {
        return new File(request.getServletContext().getRealPath("/") + "dat/GeoLiteCity.dat");
    }
}
