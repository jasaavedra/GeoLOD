package co.jhonny.wfslodlink;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.CodeSource;
import java.util.*;

import javax.swing.JOptionPane;

import org.eclipse.emf.ecore.util.EContentsEList.FeatureIterator;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
//import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.collection.FeatureIteratorImpl;
import org.geotools.feature.simple.SimpleFeatureImpl;
//import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.MismatchedDimensionException;
//import org.opengis.feature.simple.SimpleFeature;
//import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import de.ifgi.lod4wfs.core.WFSFeature;
import de.ifgi.lod4wfs.facade.Facade;
/**
 *
 * @author Alex
 */
public class viewer {
    
	   
       public String wmsGetCapabilities; 
       public String wmsLayer;   
       public String wfsGetCapabilities; 
       public String wfsLayer;  
       public String popupProperty;
       public String CRS;
       
       
       public void setWmsGetCapabilities(String wmsGetCapabilities)throws IOException {
           
           this.wmsGetCapabilities = wmsGetCapabilities;
       }
       public String getWmsGetCapabilities() {
  
    	   return wmsGetCapabilities;
       }
       
       public void setWmsLayer(String wmsLayer)throws IOException {
           
           this.wmsLayer = wmsLayer;
       }
       public String getWmsLayer() {
  
    	   return wmsLayer;
       }
       public void setWfsGetCapabilities(String wfsGetCapabilities)throws IOException {
           
           this.wfsGetCapabilities = wfsGetCapabilities;
       }
       public String getWfsGetFeature() {
  
    	   return wfsGetCapabilities;
       }
      public void setWfsLayer(String wfsLayer)throws IOException {
           
           this.wfsLayer = wfsLayer;
       }
       public String getWfsLayer() {
  
    	   return wfsLayer;
       }
       public void setPopupProperty(String popupProperty)throws IOException {
           
           this.popupProperty = popupProperty;
       }       
       
       public String getPopupProperty() {
  
    	   return popupProperty;
       }
       public void setCRS(String CRS)throws IOException {
           
           this.CRS = CRS;
       }
       public String getCRS() {
  
    	   return CRS;
       }
       
       
       
       public viewer() throws URISyntaxException{
    	    
    	   wmsGetCapabilities = null;
    	   wmsLayer = null ;
    	   wfsGetCapabilities = null;
    	   wfsLayer = null;
    	   popupProperty = null;
    	   CRS = null;
           
       }
       
       
   
       public void wfs2GeoJson() throws IOException{
    	   JOptionPane.showMessageDialog(null, "Entro" );
    	   String getCapabilities = this.wfsGetCapabilities; 
           Map connectionParameters = new HashMap();
           connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL", getCapabilities );
           
           DataStore dataStore = DataStoreFinder.getDataStore( connectionParameters );
           System.out.println("conectando con:"+ dataStore.toString());
           // Step 3 - discovery
            //String typeNames[] = data.getTypeNames();
            String typeName = this.wfsLayer;
            JOptionPane.showMessageDialog(null, "Tipo de dato:"+ typeName );
       
       
       // Step 4 - target
            SimpleFeatureSource source = dataStore.getFeatureSource(typeName);
            SimpleFeatureCollection wfsCollection = source.getFeatures();
            
            
           //FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = data.getFeatureSource( typeName );
          //String fSource = featureSource.toString();
           //JOptionPane.showMessageDialog(null, "Tipo de dato:"+ fSource );
           //FeatureCollection<SimpleFeatureType, SimpleFeature> wfsCollection = featureSource.getFeatures() ;
    	   
    	   org.geotools.feature.FeatureIterator<SimpleFeature> iterator = wfsCollection.features();    	    
    	   while (iterator.hasNext()) {
    		   SimpleFeatureImpl feature = (SimpleFeatureImpl) iterator.next();
    		   //FeatureJSON fjson = new FeatureJSON();
    		   //fjson.writeFeature(feature, "/home/adminuser/Desktop/linksResults/feature.json");
    	   }    	   
       }
       
       

}