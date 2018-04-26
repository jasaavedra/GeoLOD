<%@ page import="java.io.*"%>
<%@ page import="de.ifgi.lod4wfs.core.GlobalSettings"%>
<%@ page import="de.ifgi.lod4wfs.core.*"%>
<%@ page import="de.ifgi.lod4wfs.facade.*"%>
<%@ page import="de.ifgi.lod4wfs.factory.*"%>
<%@ page import="com.hp.hpl.jena.query.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.hp.hpl.jena.query.ARQ"%>
<%@ page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bogota Tourism information</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />
<script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
</head>
<body>
<div class="bs-docs-featurette">
  <div class="container">
       
    <h2 class="bs-docs-featurette-title">BOGOTA tourism basic information <small>(Beta 0.1)</small></h2>
    <h4 class="bs-docs-featurette-title"><small>Linked Open Data and Web Feature Services</small></h4>
    
    
    
      <div class="container">
      <% 			
		 
		WFSFeature feature = new WFSFeature();
		boolean isValidEntry = true;		
		feature.setName("testreg");
		feature.setEndpoint("http://ec2-54-94-208-47.sa-east-1.compute.amazonaws.com:8080/parliament/sparql");
		if(Facade.getInstance().isQueryValid(request.getParameter("query"))){ 
			feature.setQuery(request.getParameter("query"));
		} else {
			isValidEntry = false;	
			out.println("Invalid SPARQL Query.  <br>");			
		}
		feature.setFeatureAbstract("abstract");	
		feature.setTitle("testreg");	
		feature.setKeywords("keywords");
	    feature.setGeometryVariable("?wkt");
	    feature.setEnabled(true); 
	    feature.setCRS("EPSG:4326");
		if(!Facade.getInstance().isVariableValid(feature)){
			isValidEntry = false;
			out.println("Invalid Geometry Variable. The geometry variable provided cannot be found in the given SPARQL query.  <br>");
		}
        if(isValidEntry){
        	Facade.getInstance().addFeature(feature);	
		}
		 
				
     %>
       
           
      </div>
 <div id="map" style="width: 1200px; height:800px">
        
	<script>
            
var map = L.map('map').setView([4.59, -74.08], 9); 
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoiamFzdjk4MyIsImEiOiJjajZ0cXBlZXMwZDNiMnhwZ21sMGNtMHQxIn0.OdG0xtaWQUZLvzvzJDfl0g', {
		maxZoom: 25,
		attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
			'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="http://mapbox.com">Mapbox</a>',
		id: 'mapbox.streets'
}).addTo(map);
 



 function loadGeoJson(data) {
            console.log(data);
            L.geoJson(data, {
            onEachFeature: function (feature, layer) {
            layer.bindPopup("<a href = "+feature.properties.uri+" target='_blank' >"+feature.properties.sn+"</a>");
    }
}).addTo(map);


                  
}
        

$.ajax({
    url: 'http://10.0.2.15:8088/lod4wfs/wfs?request=GetFeature&SERVICE=WFS&VERSION=1.0.0&REQUEST=GetFeature&TYPENAME=fda:testreg&SRSNAME=EPSG:4326&outputformat=text/javascript&format_options=callback:loadGeoJson',
    dataType: 'jsonp',
    jsonpCallback: 'getJson',
    contentType: 'application/json', 
    success: loadGeoJson
});             
 
            
	</script>

            
            
 </div>
</div>
    </div>
</body>
</html>