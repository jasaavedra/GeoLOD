<%-- 
    Document   : formValidation
    Created on : 21/01/2015, 09:26:53 PM
   Author     : jhonny
--%>

<%@ page import="co.jhonny.wfslodlink.Shp2Rdf"%> 
<%@page import="de.ifgi.lod4wfs.core.GlobalSettings"%>
<%@ page import="java.io.*"%>

<!DOCTYPE html>
<html>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<script src="js/map.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="assets/css/bootstrap.min.css"><title>JSP Page</title>
    </head>
    <body>
    <div class="bs-docs-featurette">
    <div class="container">
         <h2 class="bs-docs-featurette-title">Shp2Rdf results</h2>
         
     <div class="panel panel-primary">
    <div class="panel-body">    
        <jsp:useBean id="mybean" scope="session" class="co.jhonny.wfslodlink.Shp2Rdf" />
        <jsp:setProperty name="mybean" property="nsOntology" />
        <jsp:setProperty name="mybean" property="ontologyUri" />
        <jsp:setProperty name="mybean" property="resourceUri" />
        <jsp:setProperty name="mybean" property="lang" />
        <jsp:setProperty name="mybean" property="ontologyClass" />
        <jsp:setProperty name="mybean" property="idField" />
        <jsp:setProperty name="mybean" property="labelField" />
        <jsp:setProperty name="mybean" property="outputFile" />
        <jsp:setProperty name="mybean" property="includeLabel" />
        <jsp:setProperty name="mybean" property="includePoints" />
        <jsp:setProperty name="mybean" property="includeRelations" />
        <jsp:setProperty name="mybean" property="CRSsource" />
        <jsp:setProperty name="mybean" property="CRStarget" />
        <jsp:setProperty name="mybean" property="shpLocation" />
        <jsp:setProperty name="mybean" property="shpName" />
        <jsp:setProperty name="mybean" property="query" />
        <jsp:setProperty name="mybean" property="endpoint" />
        <jsp:setProperty name="mybean" property="geometry" />
        <jsp:setProperty name="mybean" property="uri" />
      
        <%  			
	    
        mybean.convertorShp2Rdf();
        
        
        
       
     %>
    
     
      <div class="panel-heading"> RDF Generated in: <jsp:getProperty name="mybean" property="outputFile" /> </div>
     
       <form name="form_feature" method="POST" action="new.jsp" class="form-horizontal" >
          <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
              <p> If  you want to publish a new WFS service with the created links, RDF generated should be published in a triple store to be able to use this tool</p>
              <input type="submit" value="publishing like WFS" class="btn btn-success"/>
           </div>
          </div>
          </form>
      </div>
    </div>
    </div> 
    </div>
    </div>
  </body>
</html>
