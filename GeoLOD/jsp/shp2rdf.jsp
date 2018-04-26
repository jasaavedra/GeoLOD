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
        <title>WFS 2 RDF</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="assets/css/bootstrap.min.css">
    </head>
    <body>
    <div class="bs-docs-featurette">
  <div class="container">
    <h2 class="bs-docs-featurette-title"> SHP2RDF - beta</h2>
    <p> Publishing SHP data as RDF </p>
   <div class="panel panel-primary">
    <div class="panel-body">     
       
   <form name="form_shp2rdf" method="POST" action="shp2rdfValidation.jsp" class="form-horizontal">  
      
    <div class="form-group">
            <label for="shpLocation" class="col-sm-2 control-label">Shape file location (until folder)</label>
        <div class="col-sm-10">
    	<input  id="shpLocation" type="text" name="shpLocation" class="form-control">
    	</div>
    </div>  
	<div class="form-group">
            <label for="shpName" class="col-sm-2 control-label">Shape file name</label>
        <div class="col-sm-10">
    	<input  id="shpName" type="text" name="shpName" class="form-control">
    	</div>
    </div> 
    
    <div class="form-group">
            <label for="ontologyUri" class="col-sm-2 control-label">URI Base for Ontology</label>
        <div class="col-sm-10">
    	<input id="ontologyUri" type="text" name="ontologyUri" class="form-control">
    	</div>
    </div>
    <div class="form-group">
            <label for="nsOntology" class="col-sm-2 control-label">Name Space of the ontology</label>
        <div class="col-sm-10">
    	<input id="nsOntology" type="text" name="nsOntology" class="form-control">
    	</div>
    </div>
     <div class="form-group">
            <label for="resourceUri" class="col-sm-2 control-label">URI Base for Resources</label>
        <div class="col-sm-10">
    	<input id="resourceUri" type="text" name="resourceUri" class="form-control">
    	</div>
    </div>
    <div class="form-group">
            <label for="CRSsource" class="col-sm-2 control-label">CRS source</label>
        <div class="col-sm-10">
    	<input id="CRSsource" type="text" name="CRSsource" class="form-control">
    	</div>
    </div> 
    <div class="form-group">
            <label for="CRStarget" class="col-sm-2 control-label">CRS target</label>
        <div class="col-sm-10">
    	<input id="CRStarget" type="text" name="CRStarget" class="form-control">
    	</div>
    </div>    
    <div class="form-group">
            <label for="lang" class="col-sm-2 control-label">Language</label>
        <div class="col-sm-10">
    	<input id="lang" type="text" name="lang" class="form-control">
    	</div>
    </div> 
  	
    

    <div class="form-group">
            <label for="ontologyClass" class="col-sm-2 control-label">Ontology Class</label>
        <div class="col-sm-10">
    	<input  id="ontologyClass" type="text" name="ontologyClass" class="form-control">
    	</div>
    </div> 
    <div class="form-group">
            <label for="idField" class="col-sm-2 control-label">Id Field</label>
        <div class="col-sm-10">
    	<input  id="idField" type="text" name="idField" class="form-control">
    	</div>
    </div> 
    <div class="form-group">
            <label for="labelField" class="col-sm-2 control-label">Label Field</label>
        <div class="col-sm-10">
    	<input  id="labelField" type="text" name="labelField" class="form-control">
    	</div>
    </div>
   <div class="form-group">
            <label for="outputFile" class="col-sm-2 control-label">Output File Directory</label>
        <div class="col-sm-10">
    	<input  id="outputFile" type="text" name="outputFile" class="form-control">
    	</div>
    </div>
    <div class="form-group">
            <label for="includeLabel" class="col-sm-2 control-label">Include Label (yes or not)</label>
        <div class="col-sm-10">
    	<input  id="includeLabel" type="text" name="includeLabel" class="form-control">
    	</div>
    </div>
    <div class="form-group">
            <label for="includePoints" class="col-sm-2 control-label">Include Points (yes or not)</label>
        <div class="col-sm-10">
    	<input  id="includePoints" type="text" name="includePoints" class="form-control">
    	</div>
    </div>
     <div class="form-group">
            <label for="includeRelations" class="col-sm-2 control-label">Include Relations (yes or not)</label>
        <div class="col-sm-10">
    	<input  id="includeRelations" type="text" name="includeRelations" class="form-control">
    	</div>
    </div>   
     
    
    <p> Fill these fields for linking with LOD sources </p>

   <div class="form-group">
            <label for="endpoint" class="col-sm-2 control-label">Endpoint</label>
        <div class="col-sm-10">
    	<input id="endpoint" type="text" name="endpoint" class="form-control">
    	</div>
    </div>
	<div class="form-group">
            <label for="query" class="col-sm-2 control-label">Query</label>
        <div class="col-sm-10">
    	<input  id="query" type="text" name="query" class="form-control">
    	</div>
    </div>
    <div class="form-group">
            <label for="geometry" class="col-sm-2 control-label">Geometry Variable</label>
        <div class="col-sm-10">
    	<input  id="geometry" type="text" name="geometry" class="form-control">
    	</div>
    </div>
    <div class="form-group">
            <label for="geometry" class="col-sm-2 control-label">Uri variable</label>
        <div class="col-sm-10">
    	<input  id="uri" type="text" name="uri" class="form-control">
    	</div>
    </div>    
    
<input type="submit" value="Validate" id="button" class="btn btn-success"/>
</form>
</div>
</div>
</div>
    </body>
</html>
