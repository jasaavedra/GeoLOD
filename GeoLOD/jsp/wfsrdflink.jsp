<%-- 
    Document   : inicio
    Created on : 21/01/2015, 08:30:48 PM
    Author     : jhonny
--%>
<%@ page import="java.io.*"%>
<%@ page import="co.jhonny.wfslodlink.Wfs2Rdf"%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%  
     String wfsName = request.getParameter("wfs");
     String layerName = request.getParameter("layerName");
     String outputFile = request.getParameter("outputFile");
      %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>WFS and LOD Linker - jsp prueba</h1>
        
        <div id="tabs-1">
<form name="form_feature" method="POST" action="wfsrdflinkValidation.jsp" class="form-basic">  
  <table>
  <tr>
    <td>
    <br>Get Capabilities</br>
    <input id="wfs" type="text" name="wfs" value="<%=wfsName%>">
    <br>Layer name</br>
    <input id="layer" type="text" name="layer" value="<%=layerName%>">  
    <br>RDF Location</br>
    <input id="rdfLocation" type="text" name="rdfLocation" value="<%=ouputFile%>">  
    </td>
     
<td>
    <br>Endpoint</br>
    <input id="endpoint" type="text" name="endpoint" >
</td>
<td>
    <br>Query</br>
    <input  id="query" type="text" name="query">
    <br>geometry</br>
    <input  id="geometry" type="text" name="geometry">
    <br>uri</br>
    <input  id="uri" type="text" name="uri">
</td>
</tr>          
       
</table>
<input type="submit" value="Validate" id="button" class="btn btn-success"/>
</form>
</div>

	
    </body>
</html>
