package co.jhonny.wfslodlink;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.*;
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
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 *
 * @author jhonny
 */
public class WfsRdfLink {
	 
	public String wfs;
    public String layer;
    public String prueba;
    public String endpoint;
    public String query;
    public String geometry;
    public String uri;  
    public String rdfLocation; 
    public String spatialRelation;
    
  
    
    public WfsRdfLink() {
    	wfs = null;
        layer = null;
        prueba = null;
        endpoint = null;
        query = null;
        geometry = null;
        uri = null;
        rdfLocation = null;
        spatialRelation = null;
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
    public void setRdfLocation(String rdfLocation)throws IOException {
        
        this.rdfLocation = rdfLocation;
    }
    public String getRdfLocation() {

    	return rdfLocation;
    }
    public void setSpatialRelation(String spatialRelation)throws IOException {
        
        this.spatialRelation = spatialRelation;
    }
    public String getSpatialRelation() {

 	   return spatialRelation;
    }
    
    
    
    public ResultSet queryEndpoint(String query, String endpoint)throws IOException{
        
       String sparqlQueryString1 = query;
    
       Query SPARQLQuery = QueryFactory.create(sparqlQueryString1);
       QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, SPARQLQuery);
       ResultSet results = qexec.execSelect();
       
       
       //ResultSetFormatter.out(System.out, results, query);      
       //qexec.close();
       return results;
       
    }
    
    public  List<QuerySolution> linktoWfs (ResultSet LodData, SimpleFeatureImpl feature, String wktGeometry, WfsRdfLink linkBase) throws IOException, ParseException
    {
    	List<QuerySolution> linkedURIS = new ArrayList<QuerySolution>(); 
    	
                //escribirFichero(wfsGeometry.toString());
               while(LodData.hasNext()){
            	   
               QuerySolution solution = LodData.next();
               //String expressionValue = solution.get("musician").asLiteral().getString();
               String StringGeometry = solution.getLiteral(wktGeometry).toString();
               GeometryFactory geometryFactory = new GeometryFactory();
               WKTReader reader = new WKTReader( geometryFactory );
               Geometry queryGeometry = reader.read(StringGeometry) ; 
               Geometry wfsGeometry =(Geometry)feature.getDefaultGeometry();
               //JOptionPane.showMessageDialog(null,wfsGeometry.getGeometryType().toString());
               //JOptionPane.showMessageDialog(null,queryGeometry.getGeometryType().toString());
               
               String wfsGeomType = wfsGeometry.getGeometryType().toString();
               String lodGeomType = queryGeometry.getGeometryType().toString();
               
               switch (wfsGeomType){
                case "MultiPolygon":  wfsGeomType = "MultiPolygon";
                    switch (lodGeomType){
                    	case "MultiPolygon":  lodGeomType = "MultiPolygon";
                    		
                    		if(wfsGeometry.equals(queryGeometry)){ 
                        		linkedURIS.add(solution);
                        			if(linkBase.spatialRelation == null){
                        			linkBase.spatialRelation = "sameAs";                    			
                         	      }                    		
                    			}
                    		else{ 
                    				if(wfsGeometry.contains(queryGeometry)){ 
                    					linkedURIS.add(solution);
                    					if(linkBase.spatialRelation == null){
                    						linkBase.spatialRelation = "sfContains";                    			
                    					}                    		
                    				}
                    				else { 
                    						if(wfsGeometry.within(queryGeometry)){ 
                    							linkedURIS.add(solution);
                    							if(linkBase.spatialRelation == null){
                    								linkBase.spatialRelation = "sfWithin";                    			
                    							}                    		
                    						}
                    						else{ 
                    							if(wfsGeometry.intersects(queryGeometry)){ 
                        							linkedURIS.add(solution);
                        							if(linkBase.spatialRelation == null){
                        								linkBase.spatialRelation = "sfIntersects";                    			
                        							}                    		
                        						}
                    							
                    						}
                    					}
                    			
                    			}
                    	break;
                    	
                    	case "Polygon":  lodGeomType  = "Polygon";
                		
                    	if(wfsGeometry.equals(queryGeometry)){ 
                    		linkedURIS.add(solution);
                    			if(linkBase.spatialRelation == null){
                    			linkBase.spatialRelation = "sameAs";                    			
                     	      }                    		
                			}
                		else{ 
                				if(wfsGeometry.contains(queryGeometry)){ 
                					linkedURIS.add(solution);
                					if(linkBase.spatialRelation == null){
                						linkBase.spatialRelation = "sfContains";                    			
                					}                    		
                				}
                				else { 
                						if(wfsGeometry.within(queryGeometry)){ 
                							linkedURIS.add(solution);
                							if(linkBase.spatialRelation == null){
                								linkBase.spatialRelation = "sfWithin";                    			
                							}                    		
                						}
                						else{ 
                							if(wfsGeometry.intersects(queryGeometry)){ 
                    							linkedURIS.add(solution);
                    							if(linkBase.spatialRelation == null){
                    								linkBase.spatialRelation = "sfIntersects";                    			
                    							}                    		
                    						}
                							
                						}
                					}
                			
                			}
                    	break;
                    	
                    	case "LineString":  lodGeomType  = "LineString";
                		
                				if(wfsGeometry.contains(queryGeometry)){ 
                					linkedURIS.add(solution);
                					if(linkBase.spatialRelation == null){
                						linkBase.spatialRelation = "sfContains";                    			
                					}                    		
                				}
                				else { 
                							if(wfsGeometry.intersects(queryGeometry)){ 
                    							linkedURIS.add(solution);
                    							if(linkBase.spatialRelation == null){
                    								linkBase.spatialRelation = "sfIntersects";                    			
                    							}                    		
                    						} 
                					}               			
                			
                    	break;
                    	
                    	case "Point":  lodGeomType = "Point";                 		
                		
                				if(wfsGeometry.contains(queryGeometry)){ 
                					linkedURIS.add(solution);
                					if(linkBase.spatialRelation == null){
                						linkBase.spatialRelation = "sfContains";                    			
                					}                    		
                				}
                				
                    	break;
                    }
                    break;
                case "Polygon":  wfsGeomType = "Polygon";
                	switch (lodGeomType){
                		case "MultiPolygon":  lodGeomType = "MultiPolygon";
                		if(wfsGeometry.equals(queryGeometry)){ 
                    		linkedURIS.add(solution);
                    			if(linkBase.spatialRelation == null){
                    			linkBase.spatialRelation = "sameAs";                    			
                     	      }                    		
                			}
                		else{ 
                				if(wfsGeometry.contains(queryGeometry)){ 
                					linkedURIS.add(solution);
                					if(linkBase.spatialRelation == null){
                						linkBase.spatialRelation = "sfContains";                    			
                					}                    		
                				}
                				else { 
                						if(wfsGeometry.within(queryGeometry)){ 
                							linkedURIS.add(solution);
                							if(linkBase.spatialRelation == null){
                								linkBase.spatialRelation = "sfWithin";                    			
                							}                    		
                						}
                						else{ 
                							if(wfsGeometry.intersects(queryGeometry)){ 
                    							linkedURIS.add(solution);
                    							if(linkBase.spatialRelation == null){
                    								linkBase.spatialRelation = "sfIntersects";                    			
                    							}                    		
                    						}
                							
                						}
                					}
                			
                			}
                		break;
                		
                		case "Polygon":  lodGeomType  = "Polygon";
                		if(wfsGeometry.equals(queryGeometry)){ 
                    		linkedURIS.add(solution);
                    			if(linkBase.spatialRelation == null){
                    			linkBase.spatialRelation = "sameAs";                    			
                     	      }                    		
                			}
                		else{ 
                				if(wfsGeometry.contains(queryGeometry)){ 
                					linkedURIS.add(solution);
                					if(linkBase.spatialRelation == null){
                						linkBase.spatialRelation = "sfContains";                    			
                					}                    		
                				}
                				else { 
                						if(wfsGeometry.within(queryGeometry)){ 
                							linkedURIS.add(solution);
                							if(linkBase.spatialRelation == null){
                								linkBase.spatialRelation = "sfWithin";                    			
                							}                    		
                						}
                						else{ 
                							if(wfsGeometry.intersects(queryGeometry)){ 
                    							linkedURIS.add(solution);
                    							if(linkBase.spatialRelation == null){
                    								linkBase.spatialRelation = "sfIntersects";                    			
                    							}                    		
                    						}
                							
                						}
                					}
                			
                			}
                		break;
                		
                		case "LineString":  lodGeomType  = "LineString";
                		if(wfsGeometry.contains(queryGeometry)){ 
        					linkedURIS.add(solution);
        					if(linkBase.spatialRelation == null){
        						linkBase.spatialRelation = "sfContains";                    			
        					}                    		
        				}
        				else { 
        							if(wfsGeometry.intersects(queryGeometry)){ 
            							linkedURIS.add(solution);
            							if(linkBase.spatialRelation == null){
            								linkBase.spatialRelation = "sfIntersects";                    			
            							}                   		
            						} 
        					}
                		break;
                		
                		case "Point":  lodGeomType = "Point";              		
                		
        				if(wfsGeometry.contains(queryGeometry)){ 
        					linkedURIS.add(solution);
        					if(linkBase.spatialRelation == null){
        						linkBase.spatialRelation = "sfContains";                    			
        					}                    		
        				}        				                		
                		break;
                	}
                	break; 
                	
                case "LineString":  wfsGeomType = "LineString";
               		switch (lodGeomType){
               			case "MultiPolygon":  lodGeomType = "MultiPolygon";
               			if(wfsGeometry.within(queryGeometry)){ 
							linkedURIS.add(solution);
							if(linkBase.spatialRelation == null){
								linkBase.spatialRelation = "sfWithin";                    			
							}                    		
						}
						else{ 
							if(wfsGeometry.intersects(queryGeometry)){ 
    							linkedURIS.add(solution);
    							if(linkBase.spatialRelation == null){
    								linkBase.spatialRelation = "sfIntersects";                    			
    							}                    		
    						}
							
						}
               			break;
               			
               			case "Polygon":  lodGeomType  = "Polygon";
               			if(wfsGeometry.within(queryGeometry)){ 
							linkedURIS.add(solution);
							if(linkBase.spatialRelation == null){
								linkBase.spatialRelation = "sfWithin";                    			
							}                    		
						}
						else{ 
							if(wfsGeometry.intersects(queryGeometry)){ 
    							linkedURIS.add(solution);
    							if(linkBase.spatialRelation == null){
    								linkBase.spatialRelation = "sfIntersects";                    			
    							}                    		
    						}
							
						}
               			break;
               			
               			case "LineString":  lodGeomType  = "LineString";
               			if(wfsGeometry.crosses(queryGeometry)){ 
							linkedURIS.add(solution);
							if(linkBase.spatialRelation == null){
								linkBase.spatialRelation = "sfCrosses";                    			
							}                    		
						}
               			break;
               			
               			case "Point":  lodGeomType = "Point";
               			if(wfsGeometry.intersects(queryGeometry)){ 
							linkedURIS.add(solution);
							if(linkBase.spatialRelation == null){
								linkBase.spatialRelation = "sfIntersects";                    			
							}                    		
						}
               			break;     
               		}
               		break;
                case "Point":  wfsGeomType = "Point";
            		switch (lodGeomType){
            			case "MultiPolygon":  lodGeomType = "MultiPolygon";
            			if(wfsGeometry.within(queryGeometry)){ 
							linkedURIS.add(solution);
							if(linkBase.spatialRelation == null){
								linkBase.spatialRelation = "sfWithin";                    			
							}                    		
						}
            			break;
            			
            			case "Polygon":  lodGeomType  = "Polygon";
            			if(wfsGeometry.within(queryGeometry)){ 
							linkedURIS.add(solution);
							if(linkBase.spatialRelation == null){
								linkBase.spatialRelation = "sfWithin";                    			
							}                    		
						}
            			break;
            			
            			case "LineString":  lodGeomType  = "LineString";
            			if(wfsGeometry.intersects(queryGeometry)){ 
							linkedURIS.add(solution);
							if(linkBase.spatialRelation == null){
								linkBase.spatialRelation = "sfIntersects";                    			
							}                    		
						}
            			break;
            			
            			case "Point":  lodGeomType = "Point";
            			if(wfsGeometry.equals(queryGeometry)){ 
                    		linkedURIS.add(solution);
                    			if(linkBase.spatialRelation == null){
                    			linkBase.spatialRelation = "sameAs";                    			
                     	      }                    		
                			}
            			else{
            				Geometry buffer = wfsGeometry.buffer(5); 
            				if(buffer.contains(queryGeometry)){ 
                        		linkedURIS.add(solution);
                        			if(linkBase.spatialRelation == null){
                        			linkBase.spatialRelation = "sfIntersects";                    			
                         	      }                    		
                    			}
            			}
            			break;
            		}
            		break;                
               }
               
               /*if(wfsGeometry.getGeometryType().equals("MultiPolygon") && queryGeometry.getGeometryType().equals("Point")){  
            	   if(linkBase.spatialRelation == null){
            	   linkBase.spatialRelation = "sfContains";
            	   } 
               if(wfsGeometry.intersects(queryGeometry)){ 
            	   
            	  linkedURIS.add(solution);  
            	 //String prueba =  String.valueOf(linkedURIS.size());
            	  //JOptionPane.showMessageDialog(null,prueba);
               }   
               }*/
               }
              
                return linkedURIS;   
    }  
   
}
