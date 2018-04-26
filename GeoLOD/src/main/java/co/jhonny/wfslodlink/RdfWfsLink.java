package co.jhonny.wfslodlink;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.jhonny.wfslodlink.Wfs2Rdf;
import java.util.Map;

import javax.swing.JOptionPane;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;

import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 *
 * @author jhonny
 */
public class RdfWfsLink {
    public String wfs;
    public String layer;
    public String prueba;
    public String endpoint;
    public String query;
    public String geometry;
    public String uri;  
    public Model model;
    public String nsgeo; 
    public String outputFile;
    public String enrichment1;
    public String enrichment2;
    public String urienrichment1;
    public String urienrichment2;
    public String modelDirectory;
    
    
    public RdfWfsLink() throws URISyntaxException {
        wfs = null;
        layer = null;
        prueba = null;
        endpoint = null;
        query = null;
        geometry = null;
        uri = null;
        outputFile = null; 
        enrichment1 = null; 
        enrichment2 = null; 
        urienrichment1 = null; 
        urienrichment2 = null; 
        nsgeo = "http://www.opengis.net/ont/geosparql#"; 
        File jarFile = new File(RdfWfsLink.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
 	   	File jarDir = jarFile.getParentFile();
 	   	File folder = new File(jarDir+"/modelo"); 
 	   	File rdfFolder = new File(jarDir+"/rdf");
 	   	rdfFolder.mkdir();
 	   	modelDirectory = folder.getPath();
    }
    public void setWfs(String wfs)throws IOException {
                
                this.wfs = wfs;
    }
    public String getWfs() {
       
       return wfs;
    }
    
    public void setLayer(String layer)throws IOException {
                
                this.layer = layer;
    }
    public String getLayer() {
       
       return layer;
    }
    

    public void setEndpoint(String endpoint)throws IOException {
                
                this.endpoint = endpoint;
    }
    public String getEndpoint() {
       
       return endpoint;
    }

    public void setQuery(String query)throws IOException {
                
                this.query = query;
    }
    public String getQuery() {
       
       return query;
    }
    
    public void setGeometry(String geometry)throws IOException {
                
                this.geometry = geometry;
    }
    public String getGeometry() {
       
       return geometry;
    }
    
    
    public void setUri(String uri)throws IOException {
                
                this.uri = uri;
    }
    public String getUri() {
       
       return uri;
    }
    public void setOutputFile(String uri)throws IOException {
        
        this.outputFile = uri;
    }
    public String getOutputFile() {

    	return outputFile;
    }
  public void setEnrichment1(String enrichment1)throws IOException {
	          
        this.enrichment1= enrichment1;
    }
  public String getEnrichment1()throws IOException {  
	  
      return enrichment1;
  }
  public void setEnrichment2(String enrichment2)throws IOException {
      
      this.enrichment2= enrichment2;
  }
public String getEnrichment2()throws IOException {  
	  
    return enrichment2;
}

public void setUrienrichment1(String urienrichment1)throws IOException {
    
    this.urienrichment1= urienrichment1;
}
public String getUrienrichment1()throws IOException {  
	  
  return urienrichment1;
}

public void setUrienrichment2(String urienrichment2)throws IOException {
    
    this.urienrichment2= urienrichment2;
}
public String getUrienrichment2()throws IOException {  
	  
  return urienrichment2;
}


    
    public ResultSet queryEndpoint()throws IOException{
        
       String sparqlQueryString1 = this.query;
    
       Query query = QueryFactory.create(sparqlQueryString1);
       QueryExecution qexec = QueryExecutionFactory.sparqlService(this.endpoint, query);
       ResultSet results = qexec.execSelect();
       //String enrichment2 = this.enrichment2;
       //JOptionPane.showMessageDialog(null,enrichment2);
       
       
       //ResultSetFormatter.out(System.out, results, query);      
       //qexec.close();
       return results;
       
        }
    
    @SuppressWarnings("deprecation")
	public void linktoWfs () throws IOException, ParseException{
               
        crearFichero();
        File f = new File(modelDirectory);

        if (f.exists()){
            String[] ficheros = f.list();
            if (ficheros.length>0){
                for (int i=0;i<ficheros.length;i++){
                    File auxFile = new File(modelDirectory+"/"+ficheros[i]);
                    boolean delete = auxFile.delete();
                }
            }
            if (f.delete())
                System.out.println("El directorio " + modelDirectory + " ha sido borrado correctamente");
            else
                System.out.println("El directorio " + modelDirectory + " no se ha podido borrar");
        }

        //boolean dirBool = f.mkdir();
    
        model=TDBFactory.createModel(modelDirectory);
        model.removeAll();
        
        String getCapabilities = this.wfs;        
        Map connectionParameters = new HashMap();
        connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL", getCapabilities );
        DataStore data = DataStoreFinder.getDataStore(connectionParameters);
        //String typeNames[] = data.getTypeNames();  
        //String typeName = typeNames[0];
        String typeName = this.layer;
        
        //for (String i: typeNames){            
         FeatureSource<SimpleFeatureType, SimpleFeature> source = data.getFeatureSource( typeName );
         FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures() ;
         
         
         try (FeatureIterator<SimpleFeature> iterator = collection.features()) {
            while( iterator.hasNext() ){
                SimpleFeature feature = iterator.next();
                Geometry wfsGeometry = (Geometry) feature.getDefaultGeometry();
                
                ResultSet results = this.queryEndpoint();
                //escribirFichero(wfsGeometry.toString());
                while(results.hasNext()){
               QuerySolution solution = results.next();
               //String expressionValue = solution.get("musician").asLiteral().getString();
               String StringGeometry = solution.get(this.geometry).toString();
               //String StringUri = this.uri.toString();
               //JOptionPane.showMessageDialog(null, StringUri);
               
               GeometryFactory geometryFactory = new GeometryFactory();
               WKTReader reader = new WKTReader( geometryFactory );
               Geometry queryGeometry = reader.read(StringGeometry) ;
               String wfsGeomType = wfsGeometry.getGeometryType().toString();
               String lodGeomType = queryGeometry.getGeometryType().toString();
               
               switch (lodGeomType){
               case "MultiPolygon":  lodGeomType = "MultiPolygon";
                   switch (wfsGeomType){
                   	case "MultiPolygon":  wfsGeomType = "MultiPolygon";
                   		
                   		if(queryGeometry.equals(wfsGeometry)){ 
                   			String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                   			insertarTripletaLiteral(solution.getResource(this.uri).toString() , "http://www.w3.org/2002/07/owl#sameAs",strT );
                   			FileOutputStream out = new FileOutputStream(outputFile);
                   			model.write(out,"Turtle");                       			                  		
                   			}
                   		else{ 
                   				if(queryGeometry.contains(wfsGeometry)){ 
                   					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                           			insertarTripletaLiteral( solution.getResource(this.uri).toString() , nsgeo + "sfContains",strT);
                           			FileOutputStream out = new FileOutputStream(outputFile);
                           			model.write(out,"Turtle");                		
                   				}
                   				else { 
                   						if(queryGeometry.within(wfsGeometry)){ 
                   							String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                                   			insertarTripletaLiteral(solution.getResource(this.uri).toString() , nsgeo + "sfWithin",strT );
                                   			FileOutputStream out = new FileOutputStream(outputFile);
                                   			model.write(out,"Turtle");                    		
                   						}
                   						else{ 
                   							if(queryGeometry.intersects(wfsGeometry)){ 
                   								String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                                       			insertarTripletaLiteral( solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT);
                                       			FileOutputStream out = new FileOutputStream(outputFile);
                                       			model.write(out,"Turtle");                  		
                       						}
                   							
                   						}
                   					}
                   			
                   			}
                   	break;
                   	
                   	case "Polygon":  wfsGeomType  = "Polygon";
               		
                   	if(queryGeometry.equals(wfsGeometry)){ 
               			String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
               			insertarTripletaLiteral(solution.getResource(this.uri).toString() , "http://www.w3.org/2002/07/owl#sameAs",strT );
               			FileOutputStream out = new FileOutputStream(outputFile);
               			model.write(out,"Turtle");                       			                  		
               			}
               		else{ 
               				if(queryGeometry.contains(wfsGeometry)){ 
               					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                       			insertarTripletaLiteral( solution.getResource(this.uri).toString() , nsgeo + "sfContains",strT);
                       			FileOutputStream out = new FileOutputStream(outputFile);
                       			model.write(out,"Turtle");                		
               				}
               				else { 
               						if(queryGeometry.within(wfsGeometry)){ 
               							String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                               			insertarTripletaLiteral(solution.getResource(this.uri).toString() , nsgeo + "sfWithin",strT );
                               			FileOutputStream out = new FileOutputStream(outputFile);
                               			model.write(out,"Turtle");                    		
               						}
               						else{ 
               							if(queryGeometry.intersects(wfsGeometry)){ 
               								String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                                   			insertarTripletaLiteral( solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT);
                                   			FileOutputStream out = new FileOutputStream(outputFile);
                                   			model.write(out,"Turtle");                  		
                   						}
               							
               						}
               					}
               			
               			}
                   	break;
                   	
                   	case "LineString":  wfsGeomType  = "LineString";
               		
               				if(queryGeometry.contains(wfsGeometry)){ 
               					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                       			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfContains", strT );
                       			FileOutputStream out = new FileOutputStream(outputFile);
                       			model.write(out,"Turtle");                    		
               				}
               				else { 
               							if(queryGeometry.intersects(wfsGeometry)){ 
               								String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                                   			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT );
                                   			FileOutputStream out = new FileOutputStream(outputFile);
                                   			model.write(out,"Turtle");                  		
                   						} 
               					}               			
               			
                   	break;
                   	
                   	case "Point":  wfsGeomType = "Point";                 		
               		
               				if(queryGeometry.contains(wfsGeometry)){ 
               					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                       			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfContains", strT );
                       			FileOutputStream out = new FileOutputStream(outputFile);
                       			model.write(out,"Turtle");                    		
               				}
               				
                   	break;
                   }
                   break;
               case "Polygon":  lodGeomType = "Polygon";
               	switch (wfsGeomType){
               	case "MultiPolygon":  wfsGeomType = "MultiPolygon";
           		
           		if(queryGeometry.equals(wfsGeometry)){ 
           			String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
           			insertarTripletaLiteral(solution.getResource(this.uri).toString() , "http://www.w3.org/2002/07/owl#sameAs",strT );
           			FileOutputStream out = new FileOutputStream(outputFile);
           			model.write(out,"Turtle");                       			                  		
           			}
           		else{ 
           				if(queryGeometry.contains(wfsGeometry)){ 
           					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                   			insertarTripletaLiteral( solution.getResource(this.uri).toString() , nsgeo + "sfContains",strT);
                   			FileOutputStream out = new FileOutputStream(outputFile);
                   			model.write(out,"Turtle");                		
           				}
           				else { 
           						if(queryGeometry.within(wfsGeometry)){ 
           							String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                           			insertarTripletaLiteral(solution.getResource(this.uri).toString() , nsgeo + "sfWithin",strT );
                           			//insertarTripletaLiteral(solution.getResource(this.uri).toString() , nsgeo + "Clima",feature.getDescriptor().toString() );
                           			FileOutputStream out = new FileOutputStream(outputFile);
                           			model.write(out,"Turtle");                    		
           						}
           						else{ 
           							if(queryGeometry.intersects(wfsGeometry)){ 
           								String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                               			insertarTripletaLiteral( solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT);
                               			FileOutputStream out = new FileOutputStream(outputFile);
                               			model.write(out,"Turtle");                  		
               						}
           							
           						}
           					}
           			
           			}
           	break;
           	
           	case "Polygon":  wfsGeomType  = "Polygon";
       		
           	if(queryGeometry.equals(wfsGeometry)){ 
       			String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
       			insertarTripletaLiteral(solution.getResource(this.uri).toString() , "http://www.w3.org/2002/07/owl#sameAs",strT );
       			FileOutputStream out = new FileOutputStream(outputFile);
       			model.write(out,"Turtle");                       			                  		
       			}
       		else{ 
       				if(queryGeometry.contains(wfsGeometry)){ 
       					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
               			insertarTripletaLiteral( solution.getResource(this.uri).toString() , nsgeo + "sfContains",strT);
               			FileOutputStream out = new FileOutputStream(outputFile);
               			model.write(out,"Turtle");                		
       				}
       				else { 
       						if(queryGeometry.within(wfsGeometry)){ 
       							String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                       			insertarTripletaLiteral(solution.getResource(this.uri).toString() , nsgeo + "sfWithin",strT );
                       			FileOutputStream out = new FileOutputStream(outputFile);
                       			model.write(out,"Turtle");                    		
       						}
       						else{ 
       							if(queryGeometry.intersects(wfsGeometry)){ 
       								String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                           			insertarTripletaLiteral( solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT);
                           			FileOutputStream out = new FileOutputStream(outputFile);
                           			model.write(out,"Turtle");                  		
           						}
       							
       						}
       					}
       			
       			}
           	break;
           	
           	case "LineString":  wfsGeomType  = "LineString";
       		
       				if(queryGeometry.contains(wfsGeometry)){ 
       					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
               			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfContains", strT );
               			FileOutputStream out = new FileOutputStream(outputFile);
               			model.write(out,"Turtle");                    		
       				}
       				else { 
       							if(queryGeometry.intersects(wfsGeometry)){ 
       								String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                           			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT );
                           			FileOutputStream out = new FileOutputStream(outputFile);
                           			model.write(out,"Turtle");                  		
           						} 
       					}               			
       			
           	break;
           	
           	case "Point":  wfsGeomType = "Point";                 		
       		
       				if(queryGeometry.contains(wfsGeometry)){ 
       					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
               			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfContains", strT );
               			FileOutputStream out = new FileOutputStream(outputFile);
               			model.write(out,"Turtle");                    		
       				}
       				
           	break;
           }
           break;
               case "LineString":  lodGeomType = "LineString";
              		switch (wfsGeomType){
              			case "MultiPolygon":  wfsGeomType = "MultiPolygon";
              			if(queryGeometry.within(wfsGeometry)){ 
              				String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                   			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfWithin", strT );
                   			FileOutputStream out = new FileOutputStream(outputFile);
                   			model.write(out,"Turtle");                    		
						}
						else{ 
							if(queryGeometry.intersects(wfsGeometry)){ 
								String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
	                   			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT );
	                   			FileOutputStream out = new FileOutputStream(outputFile);
	                   			model.write(out,"Turtle");                  		
   						}
							
						}
              			break;
              			
              			case "Polygon":  wfsGeomType  = "Polygon";
              			if(queryGeometry.within(wfsGeometry)){ 
              				String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                   			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfWithin", strT );
                   			FileOutputStream out = new FileOutputStream(outputFile);
                   			model.write(out,"Turtle");                   		
						}
						else{ 
							if(queryGeometry.intersects(wfsGeometry)){ 
								String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
	                   			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT );
	                   			FileOutputStream out = new FileOutputStream(outputFile);
	                   			model.write(out,"Turtle");                  		
							}
							
						}
              			break;
              			
              			case "LineString":  wfsGeomType  = "LineString";
              			if(queryGeometry.crosses(wfsGeometry)){ 
              				String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                   			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfCrosses", strT );
                   			FileOutputStream out = new FileOutputStream(outputFile);
                   			model.write(out,"Turtle");                   		
						}
              			break;
              			
              			case "Point":  wfsGeomType = "Point";
              			if(queryGeometry.intersects(wfsGeometry)){ 
              				String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
                   			insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT );
                   			FileOutputStream out = new FileOutputStream(outputFile);
                   			model.write(out,"Turtle");                    		
						}
              			break;     
              		}
              		break;
               case "Point":  lodGeomType = "Point";
           		switch (wfsGeomType){
           			case "MultiPolygon":  wfsGeomType = "MultiPolygon";
           			if(queryGeometry.within(wfsGeometry)){ 
           					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
           					//JOptionPane.showMessageDialog(null, strT);
           					//insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfWithin", strT );
           					insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfWithin", strT );
           					//String enrichment1 = this.enrichment1;
           					//String enrichment2 = this.enrichment2;
           					//JOptionPane.showMessageDialog(null,enrichment1);
           					if(this.enrichment1  != null){ 
           					insertarTripletaLiteral(solution.getResource(this.uri).toString() , this.urienrichment1,feature.getAttribute(this.enrichment1).toString());
           					}
           					if(this.enrichment2  != null){ 
           					insertarTripletaLiteral(solution.getResource(this.uri).toString() , this.urienrichment2,feature.getAttribute(this.enrichment2).toString());
           					}
           					FileOutputStream out = new FileOutputStream(outputFile);
           					model.write(out,"Turtle");                    		
						}
           			break;
           					
           			case "Polygon":  wfsGeomType  = "Polygon";
           			if(queryGeometry.within(wfsGeometry)){ 
           				String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
       					insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfWithin", strT );
       					insertarTripletaLiteral(solution.getResource(this.uri).toString() , nsgeo + "Clima",feature.getAttribute(this.enrichment1).toString());
       					FileOutputStream out = new FileOutputStream(outputFile);
       					model.write(out,"Turtle");                   		
						}
           			break;
           			
           			case "LineString":  wfsGeomType  = "LineString";
           			if(queryGeometry.intersects(wfsGeometry)){ 
           					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
           					insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT );
           					FileOutputStream out = new FileOutputStream(outputFile);
           					model.write(out,"Turtle");                  		
						}
           			break;
           			
           			case "Point":  wfsGeomType = "Point";
           			if(queryGeometry.equals(wfsGeometry)){ 
           				String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
       					insertarTripletaLiteral(solution.getResource(this.uri).toString(), "http://www.w3.org/2002/07/owl#sameAs", strT );
       					FileOutputStream out = new FileOutputStream(outputFile);
       					model.write(out,"Turtle"); 
           			   }
           			else{
           				Geometry buffer = queryGeometry.buffer(5); 
           				if(buffer.contains(wfsGeometry)){ 
           					String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
           					insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT );
           					FileOutputStream out = new FileOutputStream(outputFile);
           					model.write(out,"Turtle");
           			}
           			break;
           		}
           		break;                
              }
            } 
               
               //if(/*wfsGeometry.getGeometryType().equals("Multipolygon") ||wfsGeometry.getGeometryType().equals("Polygon") &&*/ queryGeometry.getGeometryType()=="Point"){
               
               //Point queryGeometry = (Point) reader.read(StringGeometry);
               /*if(wfsGeometry.intersects(queryGeometry)){
                 //escribirFichero(solution.get(this.uri).toString(),getCapabilities.replace("GetCapabilities", "GetFeature") +"&typeName=Bogota:Loca&featureID="+feature.getIdentifier()+" ");
                 //escribirFichero("prueba","prueba");
            	  String strT =getCapabilities.replace("GetCapabilities", "GetFeature")+"&typeName="+this.layer+"&featureID="+feature.getIdentifier();   
            	  //JOptionPane.showMessageDialog(null, "resource URI"+solution.getResource(this.uri));
            	  //JOptionPane.showMessageDialog(null, "object URI"+ strT);
                 insertarTripletaLiteral(solution.getResource(this.uri).toString(), nsgeo + "sfIntersects", strT);
                 FileOutputStream out = new FileOutputStream(outputFile);
                 model.write(out,"Turtle");
                 
                 
                 //}             
               }  */
       
            
               
        }
        }
       }
        
                
                
        
        // 
        //
                
        
    }
    
    private void insertarTripletaLiteral(String s, String p, String o) {
        //Permite ingresar una tripleta en el rdf
              
            Resource object = model.createResource(o);
            //JOptionPane.showMessageDialog(null, object.toString());
            Resource subject = model.createResource(s);
            Property property = model.createProperty(p);
            subject.addProperty(property, object);
        
        //   model.write(System.out);
    }
    
    public void linkfromWFS(){
   
    }
    
        
    public static void crearFichero() throws IOException {
       String ruta = "/media/sf_compartidaU/linkCund.rdf";
        File archivo = new File(ruta);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.close(); 
    
    }
    
    public static void escribirFichero(String subject, String predicado) throws IOException {
       String ruta = "/media/sf_compartidaU/linkCund.rdf";
        File archivo = new File(ruta);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo,true));
        bw.write(subject + " owl:sameAs " +predicado );
        bw.close(); 
    }
}