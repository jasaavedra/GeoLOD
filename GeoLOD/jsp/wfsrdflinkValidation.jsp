<%-- 
    Document   : formValidation
    Created on : 21/01/2015, 09:26:53 PM
   Author     : jhonny
--%>

<%@ page import="co.jhonny.wfslodlink.WfsRdfLink"%> 
<%@ page import="java.io.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <jsp:useBean id="mybean" scope="session" class="co.jhonny.wfslodlink.WfsRdfLink" />
        <jsp:setProperty name="mybean" property="wfs" />
        <jsp:setProperty name="mybean" property="layer" />
        <jsp:setProperty name="mybean" property="endpoint" />
        <jsp:setProperty name="mybean" property="query" />
        <jsp:setProperty name="mybean" property="geometry" />
        <jsp:setProperty name="mybean" property="uri" />
        <jsp:setProperty name="mybean" property="rdfLocation" />
        <h1>WFS:  <jsp:getProperty name="mybean" property="wfs" />!</h1>
        <h1>Layer:  <jsp:getProperty name="mybean" property="layer" />!</h1>
        <h1>Endpoint: <jsp:getProperty name="mybean" property="endpoint" />!</h1>
        <h1>Query: <jsp:getProperty name="mybean" property="query" />!</h1>
        <h1>Geometry: <jsp:getProperty name="mybean" property="geometry" />!</h1>
        <h1>Uri: <jsp:getProperty name="mybean" property="uri" />!</h1> 
        <h1>Uri: <jsp:getProperty name="mybean" property="rdfLocation" />!</h1> 
        
        <%  			
	String endpoint = mybean.endpoint;
        out.println(endpoint);
        String getCapabilities = mybean.wfs;
        mybean.linktoWfs();
        //mybean.includeInRdf();
        out.println(getCapabilities);
        
        //String prueba = mybean.prueba;
        //out.println(prueba);
        
       
     %>
     
     
    </body>
</html>
