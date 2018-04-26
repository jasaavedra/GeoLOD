<%-- 
    Document   : formValidation
    Created on : 21/01/2015, 09:26:53 PM
   Author     : jhonny
--%>

<%@ page import="co.jhonny.wfslodlink.RdfWfsLink"%> 
<%@ page import="java.io.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="bs-docs-featurette">
       <div class="container">
         <h2 class="bs-docs-featurette-title">RDF - WFS Links results</h2>
        <div class="panel panel-primary">
    	<div class="panel-body"> 
        <jsp:useBean id="mybean" scope="session" class="co.jhonny.wfslodlink.RdfWfsLink" />
        <jsp:setProperty name="mybean" property="wfs" />
        <jsp:setProperty name="mybean" property="layer" />
        <jsp:setProperty name="mybean" property="endpoint" />
        <jsp:setProperty name="mybean" property="query" />
        <jsp:setProperty name="mybean" property="geometry" />
        <jsp:setProperty name="mybean" property="uri" />
        <jsp:setProperty name="mybean" property="outputFile" />
        <jsp:setProperty name="mybean" property="enrichment1" />
        <jsp:setProperty name="mybean" property="enrichment2" />
        <jsp:setProperty name="mybean" property="urienrichment1" />
        <jsp:setProperty name="mybean" property="urienrichment2" />
    <%--     <h1>WFS:  <jsp:getProperty name="mybean" property="wfs" />!</h1>
        <h1>Layer:  <jsp:getProperty name="mybean" property="layer" />!</h1>
        <h1>Endpoint: <jsp:getProperty name="mybean" property="endpoint" />!</h1>
        <h1>Query: <jsp:getProperty name="mybean" property="query" />!</h1>
        <h1>Geometry: <jsp:getProperty name="mybean" property="geometry" />!</h1>
        <h1>Uri: <jsp:getProperty name="mybean" property="uri" />!</h1> --%>
        <h2>Links RDF File generated in: <jsp:getProperty name="mybean" property="outputFile" />!</h2> 
        <h2>Enrichment atribute <jsp:getProperty name="mybean" property="enrichment1" />!</h2> 
        <h2>Enrichment atribute <jsp:getProperty name="mybean" property="enrichment2" />!</h2>
        <%  			
	    //String enrichment1 = mybean.enrichment1;
        //out.println(enrichment1);
        //String getCapabilities = mybean.wfs;
        mybean.linktoWfs();
        //out.println(getCapabilities);
        
        //String prueba = mybean.prueba;
        //out.println(prueba);
        
       
     %>
     </div>
          </div>
          </div>
          </div>
     
    </body>
</html>
