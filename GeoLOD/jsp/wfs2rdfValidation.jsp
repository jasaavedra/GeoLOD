<%-- 
    Document   : formValidation
    Created on : 21/01/2015, 09:26:53 PM
   Author     : jhonny
--%>

<%@ page import="co.jhonny.wfslodlink.Wfs2Rdf"%> 
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
         <h2 class="bs-docs-featurette-title">Wfs2Rdf results</h2>
         
     <div class="panel panel-primary">
    <div class="panel-body">    
        <jsp:useBean id="mybean" scope="session" class="co.jhonny.wfslodlink.Wfs2Rdf" />
        <jsp:setProperty name="mybean" property="nsOntology" />
        <jsp:setProperty name="mybean" property="ontologyUri" />
        <jsp:setProperty name="mybean" property="resourceUri" />
        <jsp:setProperty name="mybean" property="lang" />
        <jsp:setProperty name="mybean" property="featureClass" />
        <jsp:setProperty name="mybean" property="attribute" />
        <jsp:setProperty name="mybean" property="label" />
        <jsp:setProperty name="mybean" property="outputFile" />
        <jsp:setProperty name="mybean" property="includeLabel" />
        <jsp:setProperty name="mybean" property="includePoints" />
        <jsp:setProperty name="mybean" property="includeRelations" />
        <jsp:setProperty name="mybean" property="wfsGetCapabilities" />
        <jsp:setProperty name="mybean" property="CRSsource" />
        <jsp:setProperty name="mybean" property="CRStarget" />
        <jsp:setProperty name="mybean" property="query" />
        <jsp:setProperty name="mybean" property="endpoint" />
        <jsp:setProperty name="mybean" property="geometry" />
        <jsp:setProperty name="mybean" property="uri" />
        <jsp:setProperty name="mybean" property="layerWFS" />
        <!--
        <h1> Name Space Ontology:  <jsp:getProperty name="mybean" property="nsOntology" />!</h1>
        <h1> Ontology Uri:  <jsp:getProperty name="mybean" property="ontologyUri" />!
        <h1> Resources Base Uri: <jsp:getProperty name="mybean" property="resourceUri" />!</h1>
        <h1> Language: <jsp:getProperty name="mybean" property="lang" />!</h1>
        <h1> Ontology Class: <jsp:getProperty name="mybean" property="featureClass" />!</h1>
        <h1> ID Field name: <jsp:getProperty name="mybean" property="attribute" />!</h1> 
        <h1> Label Field:  <jsp:getProperty name="mybean" property="label" />!</h1>
        <h1> Output File Location: <jsp:getProperty name="mybean" property="outputFile" />!</h1>
        <h1> Inlude Label: <jsp:getProperty name="mybean" property="includeLabel" />!</h1>
        <h1> Include Points: <jsp:getProperty name="mybean" property="includePoints" />!</h1>
        <h1> Include Relations: <jsp:getProperty name="mybean" property="includeRelations" />!</h1> 
        <h1> WFS Get Capabilities: <jsp:getProperty name="mybean" property="wfsGetCapabilities" />!</h1>
        <h1> CRS source: <jsp:getProperty name="mybean" property="CRSsource" />!</h1> 
        <h1> CRS target: <jsp:getProperty name="mybean" property="CRStarget" />!</h1>
        <h1> Endpoint: <jsp:getProperty name="mybean" property="endpoint" />!</h1>
        <h1> Query: <jsp:getProperty name="mybean" property="query" />!</h1>
        <h1>Geometry: <jsp:getProperty name="mybean" property="geometry" />!</h1>
        <h1>Uri: <jsp:getProperty name="mybean" property="uri" />!</h1>
        <h1>Uri: <jsp:getProperty name="mybean" property="layerWFS" />!</h1>
        -->
        <%  			
	    //String endpoint = mybean.nsOntology;
        //out.println(endpoint);
        //String getCapabilities = mybean.wfsGetCapabilities;
        mybean.Convertor();
        
        //out.println(getCapabilities);
        
        //String prueba = mybean.prueba;
        //out.println(prueba);
        
       
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
     <td>
    <jsp:include page="wfsrdflink.jsp" flush="true" >
    	<jsp:param name='wfs' value='<%=mybean.wfsGetCapabilities%>' />
    	<jsp:param name='layerName' value='<%=mybean.featureClass%>' />
    	<jsp:param name='outputFile' value='<%=mybean.outputFile%>' />
    	<jsp:param name='outputFile' value='<%=mybean.CRStarget%>' />
	</jsp:include>
    </td>
    </body>
</html>
