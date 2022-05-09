<%--
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page import="com.ithome.web.SearchController.DummyDB" %>
<%
    DummyDB db = new DummyDB();

    String query = request.getParameter("q");

    List<String> countries = db.getData(query);

    Iterator<String> iterator = countries.iterator();
    while(iterator.hasNext()) {
        String country = (String)iterator.next();
        out.println(country);
    }
%>--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@ page import="com.ithome.web.Constances.Constance" %>

<%
    try{
        String s[]=null;

        Class.forName("com.mysql.jdbc.Driver");
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT", Constance.USERNAMEINDATA2, Constance.USERPASSWORDINDATA2);
        Statement st=con.createStatement();
        ResultSet rs = st.executeQuery("select * from bankscontroller");

        List li = new ArrayList();

        while(rs.next())
        {
            li.add(rs.getString(1));
        }

        String[] str = new String[li.size()];
        Iterator it = li.iterator();

        int i = 0;
        while(it.hasNext())
        {
            String p = (String)it.next();
            str[i] = p;
            i++;
        }

        //jQuery related start
        String query = request.getParameter("q");

        int cnt=1;
        for(int j=0;j<str.length;j++)
        {
            if(str[j].toUpperCase().startsWith(query.toUpperCase()))
            {
                out.print(str[j]+"\n");
                if(cnt>=5)
                    break;
                cnt++;
            }
        }
        //jQuery related end


        rs.close();
        st.close();
        con.close();

    }
    catch(Exception e){
        e.printStackTrace();
    }

//www.java4s.com
%>
