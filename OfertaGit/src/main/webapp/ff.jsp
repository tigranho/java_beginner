<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 3/6/2020
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<?xml version = "1.0" encoding = "utf-8"?>
<!DOCTYPE hibernate-configuration SYSTEM
"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>

        <property name = "hibernate.dialect">
            org.hibernate.dialect.MySQLDialect
        </property>

        <property name = "hibernate.connection.driver_class">
            com.mysql.jdbc.Driver
        </property>

        <!-- Assume test is the database name -->

        <property name = "hibernate.connection.url">
            jdbc:mysql://localhost/test
        </property>

        <property name = "hibernate.connection.username">
            root
        </property>

        <property name = "hibernate.connection.password">
            root123
        </property>

        <!-- List of XML mapping files -->
        <mapping resource = "Employee.hbm.xml"/>

    </session-factory>
</hibernate-configuration>
