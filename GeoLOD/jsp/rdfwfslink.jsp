<%-- 
    Document   : inicio
    Created on : 21/01/2015, 08:30:48 PM
    Author     : jhonny
--%>
<%@ page import="java.io.*"%>
<%@page import="de.ifgi.lod4wfs.core.GlobalSettings"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script src="js/map.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    </head>
    <body>
        <div class="bs-docs-featurette">
  			<div class="container">
   			 <h2 class="bs-docs-featurette-title">Enriching RDF with WFS data</h2>
             <p> Linking  wfs data with RDF to enrich the Linked Data Cloud with SDI data </p>
      <div class="panel panel-primary">
    <div class="panel-body"> 
		<form name="form_feature" method="POST" action="rdfwfslinkValidation.jsp" class="form-horizontal">  
  <table>
  <tr>
    
<td>
    <br>Get Capabilities</br>
    <input id="wfs" type="text" name="wfs" class="form-control">
    <br>layer name</br>
    <input id="layer" type="text" name="layer" class="form-control">
      
</td>
<td>
    <br>Enrichment atributte 1</br>
    <input id="enrichment1" type="text" name="enrichment1" class="form-control">
    <br>Enrichment atributte 2</br>
    <input id="enrichment2" type="text" name="enrichment2" class="form-control">     
  
</td>
<td>
    <br>URI Enrichment atributte 1</br>
    <input id="urienrichment1" type="text" name="urienrichment1" class="form-control">
    <br>URI Enrichment atributte 2</br>
    <input id="urienrichment2" type="text" name="urienrichment2" class="form-control">     
  
</td>

<td>
    <br>Endpoint</br>
    <input id="endpoint" type="text" name="endpoint" class="form-control">
    <br>Query</br>
    <input  id="query" type="text" name="query" class="form-control">
    </td>
<td>
    <br>Geometry variable</br>
    <input  id="geometry" type="text" name="geometry" class="form-control">
    <br>Uri variable</br>
    <input  id="uri" type="text" name="uri" class="form-control">
</td>
<td>
	<br>Output File</br>
    <input id="outputFile" type="text" name="outputFile" class="form-control">
    
</td>
</tr>          
       
</table>
<br> </br>
<input type="submit" value="Validate" id="button" class="btn btn-success"/>
</form>
</div>
    </body>
</html>
