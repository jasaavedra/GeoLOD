package co.jhonny.wfslodlink;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//import com.hp.hpl.jena.db.IDBConnection;
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
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.CodeSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import co.jhonny.wfslodlink.WfsRdfLink;


/**
 *
 * @author Alex
 */
public class Shp2Rdf {
	
        public Model model;
    //IDBConnection conn;
        
        String nsgeo = "http://www.opengis.net/ont/geosparql#"; 
        String sf = "http://www.opengis.net/ont/sf#"; 
        public String ontologyUri;
        public String nsOntology; 
        public String resourceUri;
        public String lang; 
        public String shpLocation;
        public String shpName;
        public String ontologyClass;
        public String idField;
        public String labelField;
        public String outputFile;
        public String includeLabel;
        public String includePoints;
        public String includeRelations;
        public String CRSsource; 
        public String CRStarget;   
        public String endpoint; 
        public String query;  
        public String geometry;
        public String uri; 
        public ResultSet LodData;
        public WfsRdfLink linkBase;
        public List<QuerySolution> UrisLinked ;
        public String strI;
        
        public void setNsOntology(String nsOntology)throws IOException {
            
            this.nsOntology = nsOntology;
        }
        public String getNsOntology() {
   
     	   return nsOntology;
        }
        
        public void setOntologyUri(String ontologyUri)throws IOException {
            
            this.ontologyUri = ontologyUri;
        }
        public String getOntologyUri() {
   
     	   return ontologyUri;
        }
        public void setResourceUri(String resourceUri)throws IOException {
            
            this.resourceUri = resourceUri;
        }
        public String getResourceUri() {
   
     	   return resourceUri;
        }
        public void setLang(String lang)throws IOException {
            
            this.lang = lang;
        }
        public String getLang() {
   
     	   return lang;
        }
        public void setShpLocation(String shpLocation)throws IOException {
            
            this.shpLocation = shpLocation;
        }
        public String getShpLocation() {
   
     	   return shpLocation;
        }
        public void setShpName(String shpName)throws IOException {
            
            this.shpName = shpName;
        }
        public String getShpName() {
   
     	   return shpName;
        }
        public void setOntologyClass(String ontologyClass)throws IOException {
            
            this.ontologyClass = ontologyClass;
        }
        public String getOntologyClass() {
   
     	   return ontologyClass;
        }
        public void setLabelField(String labelField)throws IOException {
            
            this.labelField = labelField;
        }
        public String getLabelField() {
   
     	   return labelField;
        }
        public void setIdField(String idField)throws IOException {
            
            this.idField = idField;
        }
        public String getIdField() {
   
     	   return idField;
        }
        public void setOutputFile(String outputFile)throws IOException {
            
            this.outputFile= outputFile;
        }
        public String getOutputFile() {
   
     	   return outputFile;
        }
        public void setIncludeLabel(String includeLabel)throws IOException {
            
            this.includeLabel= includeLabel;
        }
        public String getIncludeLabel() {
   
     	   return includeLabel;
        }
        public void setIncludePoints(String includePoints)throws IOException {
            
            this.includePoints= includePoints;
        }
        public String getIncludePoints() {
   
     	   return includePoints;
        }
        public void setIncludeRelations(String includeRelations)throws IOException {
            
            this.includeRelations= includeRelations;
        }
        public String getIncludeRelations() {
   
     	   return includeRelations;
        }
        public void setCRSsource(String CRSsource)throws IOException {
            
            this.CRSsource = CRSsource;
        }
        public String getCRSsource() {
   
     	   return CRSsource;
        }
        public void setCRStarget(String CRStarget)throws IOException {
            
            this.CRStarget = CRStarget;
        }
        public String getCRStarget() {
   
     	   return CRStarget;
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
        
        
        public Shp2Rdf() throws Exception {

        
        //Properties myproperties = new properties().getproperties("configuration.properties");
        //CodeSource codeSource = properties.class.getProtectionDomain().getCodeSource();
        ontologyUri = null;
        nsOntology = null;
        resourceUri = null;
        lang = null;
        
        
        //convertor gr1 = new convertor(modelDirectory, ontologyUri, resourceUri, nsOntology);
        shpName = null ;
        ontologyClass = null ;
        idField = null;
        labelField = null;
        //String linkUriField = myproperties.getProperty("linkUriField");
        outputFile = null;
        //String shpLinkString = myproperties.getProperty("shpLink")+"/"+ myproperties.getProperty("shpName")+".shp";
        includeLabel = null;
        includePoints = null;
        includeRelations = null;
        //String ignore = "";
        //gr1.SHPtoRDF(null, null, fileString, featureString, featureClass, outputFile, attribute, label, ignore, resourceUri, ontologyUri, lang, includeLabel, includeRelations, includePoints);
        
    }
    
    @SuppressWarnings({ "deprecation", "unchecked" })
	public void convertorShp2Rdf() throws ClassNotFoundException, URISyntaxException, IOException, NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, ParseException {

       String fileString = shpLocation +"/"+ shpName+".shp";
       File jarFile = new File(Shp2Rdf.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
       File jarDir = jarFile.getParentFile();
       File folder = new File(jarDir+"/modelo"); 
       File rdfFolder = new File(jarDir+"/rdf");
       rdfFolder.mkdir();
       String modelDirectory = folder.getPath();
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

        boolean dirBool = f.mkdir();
    
        model = TDBFactory.createModel(modelDirectory);
        model.removeAll();
        model.setNsPrefix(nsOntology, ontologyUri); 
        model.setNsPrefix("resource", resourceUri);
        model.setNsPrefix("geo", nsgeo);
        model.setNsPrefix("sf", sf);
              


        File file = new File(fileString);
        @SuppressWarnings("rawtypes")
		Map map = new HashMap();
        map.put("url", file.toURL());
        DataStore dataStore = DataStoreFinder.getDataStore(map);
        SimpleFeatureSource featureSource = dataStore.getFeatureSource(shpName);
        SimpleFeatureCollection collection = featureSource.getFeatures();
        //Iterator iterator = collection.iterator();      
        org.geotools.feature.FeatureIterator<SimpleFeature> iterator = collection.features();          
        int  collectionSize = collection.size();
        
        //int num = 0;

        while (iterator.hasNext()) {
            
            
            
            SimpleFeatureImpl feature = (SimpleFeatureImpl) iterator.next();            
            Geometry o = (Geometry) feature.getDefaultGeometry();        
            
                                   
            String namn1 = feature.getAttribute(idField).toString();
            namn1 = namn1.toLowerCase();
            String namn2 = feature.getAttribute(labelField).toString();
            System.out.println("###################El valor de FEATURENAME2 is -->"+ namn1);

            // Si el elemento tiene de nombre N_P, no sabemos como actuar con el
            //if (!namn1.equals(ignore)) //&& (namn1.startsWith("Río") || namn1.startsWith("Riu") || namn1.startsWith("Rio"))
            //{
                String tipo = ontologyClass;
                String resource = namn1;
                String defaultLang = lang;
                if (CRSsource != null && CRStarget != null) {
                    //Quiere decir que hay que transformar
                    CoordinateReferenceSystem sourceCRS = CRS.decode(CRSsource);
                    CoordinateReferenceSystem targetCRS = CRS.decode(CRStarget);
                    MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);
                    Geometry targetGeometry = JTS.transform(o, transform);
                    o = targetGeometry;
                }
                String hash = HashGeometry.getHash(o.toText());
                //URLEncoder.encode("Río"+name, "utf-8");
                String encTipo = URLEncoder.encode(tipo,"utf-8").replace("+", "");
                String encResource = URLEncoder.encode(resource,"utf-8").replace("+", "");
                 //encTipo + "/" +:
                String aux = encResource;
                insertarResourceTypeResource(resourceUri + aux, ontologyUri + URLEncoder.encode(tipo, "utf-8").replace("+", "%20"));
                defaultLang=detectLang(resource);
                
                if (this.endpoint != null){ 
                    linkBase = new WfsRdfLink();
                    LodData = linkBase.queryEndpoint(this.query, this.endpoint);
                    
                    UrisLinked = linkBase.linktoWfs(LodData, feature, this.geometry, linkBase);
                
                
                if(UrisLinked.size() > 0 ) {            	
                    
                    for (int j = 0; j < UrisLinked.size(); j++) {  
                        QuerySolution uriLinked = UrisLinked.get(j);
                        
                        Resource object = uriLinked.getResource(this.uri);
                        strI = object.toString();   
                        //JOptionPane.showMessageDialog(null, strI);
                        //String strT = namn1;   
                        //JOptionPane.showMessageDialog(null, strT);
                        if (linkBase.spatialRelation == "sfContains"){ 
                        insertarTripletaLiteral(resourceUri + namn1, nsgeo + "sfContains", strI, null); 
                        }
                        if (linkBase.spatialRelation == "sfWithin"){ 
                            insertarTripletaLiteral(resourceUri + namn1, nsgeo + "sfWithin", strI, null); 
                            }
                        if (linkBase.spatialRelation == "sfIntersects"){ 
                            insertarTripletaLiteral(resourceUri + namn1, nsgeo + "sfIntersects", strI, null); 
                            }
                        if (linkBase.spatialRelation == "sfCrosses"){ 
                            insertarTripletaLiteral(resourceUri + namn1, nsgeo + "sfCrosses", strI, null); 
                            }
                        if (linkBase.spatialRelation == "sameAs"){ 
                            insertarTripletaLiteral(resourceUri + namn1, "http://www.w3.org/2002/07/owl#sameAs", strI, null); 
                            }            
                          
                     }
                    
                    UrisLinked = null;
                    }
                    }
                                

                if (includeLabel.equals("yes")){ 
                	if (namn2.length() >= 1){
                	namn2 = namn2.substring(0, 1).toUpperCase()+namn2.substring(1).toLowerCase();	
                	}
                	insertarLabelResource(resourceUri + aux, namn2, defaultLang);
                }
                for (org.opengis.feature.Property property : feature.getProperties()) {
                String name = property.getName().toString();
                name = name.toLowerCase();  
                Object value = feature.getAttribute( property.getName() );                               
                String stringValue = value.toString();  
                if (stringValue.length() >= 1){
                stringValue = stringValue.substring(0, 1).toUpperCase()+stringValue.substring(1).toLowerCase();  
                }
                if((name.equals(idField))||(name.equals(labelField))||(name.equals("the_geom"))){
                  
                System.out.print( name+"="+value+"," );
                }
                else 
                {    
                    String type = value.getClass().toString();
                    type = type.toLowerCase();
                    //JOptionPane.showMessageDialog(null, type);                  
                    insertarField(resourceUri + aux, ontologyUri + name, stringValue, type); 
                                       
                } 
               }
                
                
                
                if (o.getGeometryType().equals("LineString"))
                    insertarLineString(resourceUri, aux, hash, o, collectionSize, collection, includeRelations);
                else if (o.getGeometryType().equals("Point"))
                    insertarPoint(resourceUri, aux, hash, o, collectionSize, collection, includeRelations);
                else if (o.getGeometryType().equals("Polygon")){
                    insertarPolygon(resourceUri, aux, hash, o,  collectionSize, collection, includeRelations);
                    if (includePoints.equals("yes")){
                        Point centroide = o.getCentroid();     
                        String hashPoint = HashGeometry.getHash(centroide.toText());
                        insertarCentroide(resourceUri, resource, centroide, hashPoint);
                    }
                }
                else if (o.getGeometryType().equals("MultiPolygon")){
                    insertarMultiPolygon(resourceUri, aux, hash, o, collectionSize, collection, includeRelations);     
                    if (includePoints.equals("yes")){
                        Point centroide = o.getCentroid();     
                        String hashPoint = HashGeometry.getHash(centroide.toText());                   
                        insertarCentroide(resourceUri, resource, centroide, hashPoint);
                     }
                }
                else if (o.getGeometryType().equals("MultiLineString")){
                    insertarMultiLineString(resourceUri, aux, hash, o, collectionSize, collection, includeRelations);
                }
                 else if (o.getGeometryType().equals("MultiPoint")){
                    insertarMultiPoint(resourceUri, aux, hash, o, collectionSize, collection, includeRelations);
                 }
                
            }
        FileOutputStream out = new FileOutputStream(outputFile);
    	model.write(out,"Turtle");
	 }
            /*if (shpLinkString != null ){
            insertarLinks(resourceUri, namn1, o, collection, shpLinkString, linkUriField );
            }
            num++;
            if (num>2)
                break;*/
        
    	

    

    /*private void insertarLinks(String resourceUri, String resource, Geometry geo, FeatureCollection collection, String shpLinkString, String linkUriField)throws MalformedURLException, IOException, NoSuchAuthorityCodeException, FactoryException, TransformException
    {
        File file = new File(shpLinkString);
        Map map = new HashMap();
        map.put("url", file.toURL());
        DataStore dataStore = DataStoreFinder.getDataStore(map);
        SimpleFeatureSource featureSource = dataStore.getFeatureSource(shpLinkString);
        SimpleFeatureCollection collectionLinks = featureSource.getFeatures();        
        Iterator iterator = collectionLinks.iterator();          
     
        while (iterator.hasNext()) {
            SimpleFeatureImpl featurelink = (SimpleFeatureImpl) iterator.next();            
            Geometry linkGeo = (Geometry) featurelink.getDefaultGeometry();
           
            if (contains(geo,linkGeo))
            {
                
               String linkUri = featurelink.getAttribute(linkUriField).toString();
               insertarTripletaResource(resourceUri + resource, nsgeo + "sfContains", resourceUri + linkUri);
            }                
           
        }
        
    }*/
    
    private void insertarLineString(String resourceUri, String resource, String hash, Geometry geo, int collectionSize, @SuppressWarnings("rawtypes") FeatureCollection collection, String includeRelations )throws UnsupportedEncodingException{
        
        insertarResourceTypeResource(resourceUri+ "Geometry/"+ hash, nsgeo + URLEncoder.encode("LineString", "utf-8").replace("+", "%20"));
        insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri + "Geometry/" + hash);
        insertarTripletaLiteral(resourceUri +"Geometry/"+ hash, nsgeo + "asWKT" , geo.toText(), nsgeo + "wktLiteral");
        if (includeRelations.equals("yes")){
        @SuppressWarnings("unchecked")
		org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features();  
        
        for (int i=0; i< collectionSize; i++) {
            
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            //if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            //}   
            
         }  
        }
    }

    private void insertarPolygon(String resourceUri, String resource, String hash, Geometry geo, int collectionSize, @SuppressWarnings("rawtypes") FeatureCollection collection, String includeRelations) throws UnsupportedEncodingException{
        
       insertarResourceTypeResource(resourceUri+ "Geometry/"+ hash, sf + URLEncoder.encode("Polygon", "utf-8").replace("+", "%20"));
       insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri + "Geometry/" + hash);
       insertarTripletaLiteral(resourceUri +"Geometry/"+ hash, nsgeo + "asWKT" , geo.toText(), sf + "wktLiteral");
       if (includeRelations.equals("yes")){ 
    	@SuppressWarnings("unchecked")
   		org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features();  
        for (int i=0; i< collectionSize; i++) {
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            //if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            //}   
         }
       }
    }
    
    private void insertarCentroide(String resourceUri, String resource, Point centroide, String hashPoint) throws UnsupportedEncodingException{
        
       insertarResourceTypeResource(resourceUri+ "Geometry/"+ hashPoint, sf + URLEncoder.encode("Point", "utf-8").replace("+", "%20"));
       insertarTripletaResource(resourceUri + resource,nsgeo + "hasGeometry", resourceUri + "Geometry/" + hashPoint);
       insertarTripletaLiteral(resourceUri +"Geometry/"+ hashPoint, nsgeo + "asWKT" , centroide.toText(), sf + "wktLiteral");
    
    }
    
    private void insertarPoint(String resourceUri, String resource, String hash, Geometry geo, int collectionSize, @SuppressWarnings("rawtypes") FeatureCollection collection, String includeRelations) throws UnsupportedEncodingException{
     insertarResourceTypeResource(resourceUri+ "Geometry/"+ hash, sf + URLEncoder.encode("Point", "utf-8").replace("+", "%20"));
     insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri + "Geometry/" + hash);
     insertarTripletaLiteral(resourceUri +"Geometry/"+ hash, nsgeo + "asWKT" , geo.toText(), sf + "wktLiteral");
     if (includeRelations.equals("yes")){ 
    	@SuppressWarnings("unchecked")
    	org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features(); 
        for (int i=0; i< collectionSize; i++) {
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            //if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            //}              
         }
     }
    }
       
    
    private void insertarMultiPolygon(String resourceUri, String resource, String hash, Geometry geo,int collectionSize, @SuppressWarnings("rawtypes") FeatureCollection collection, String includeRelations) throws UnsupportedEncodingException{
        
        insertarResourceTypeResource(resourceUri + "Geometry/" + hash, sf + URLEncoder.encode("MultiPolygon", "utf-8").replace("+", "%20"));
        insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri  + "Geometry/" + hash);
        insertarTripletaLiteral(resourceUri + "Geometry/" + hash, nsgeo + "asWKT" , geo.toText(), sf + "wktLiteral");
        if (includeRelations.equals("yes")){ 
        	@SuppressWarnings("unchecked")
        	org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features(); 
        
        for (int i=0; i< collectionSize; i++) {
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            //if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            //}   
         }
        }
    }

    private void insertarMultiPoint(String resourceUri, String resource, String hash, Geometry geo, int collectionSize, @SuppressWarnings("rawtypes") FeatureCollection collection, String includeRelations) throws UnsupportedEncodingException{
        
       insertarResourceTypeResource(resourceUri+ "Geometry/"+ hash, sf + URLEncoder.encode("MultiPoint", "utf-8").replace("+", "%20"));
        insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri + "Geometry/" + hash);
        insertarTripletaLiteral(resourceUri +"Geometry/"+ hash, nsgeo + "asWKT" , geo.toText(), sf + "wktLiteral");
       if (includeRelations.equals("yes")){ 
    	   @SuppressWarnings("unchecked")
       	org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features(); 
        for (int i=0; i< collectionSize; i++) {
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
           // if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            //}             
         }
       }

    }
    
     private void insertarMultiLineString(String resourceUri, String resource, String hash, Geometry geo, int collectionSize, @SuppressWarnings("rawtypes") FeatureCollection collection, String includeRelations) throws UnsupportedEncodingException{
        
        insertarResourceTypeResource(resourceUri+ "Geometry/"+ hash, nsgeo + URLEncoder.encode("MultiLineString", "utf-8").replace("+", "%20"));
        insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri + "Geometry/" + hash);
        insertarTripletaLiteral(resourceUri +"Geometry/"+ hash, nsgeo + "asWKT" , geo.toText(), nsgeo + "wktLiteral");
        if (includeRelations.equals("yes")){ 
        	@SuppressWarnings("unchecked")
        	org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features(); 
        for (int i=0; i< collectionSize; i++) {
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            //if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            //}         
         }
        }
    }
     
     private void relacionesTopologicas (String resourceUri, Geometry geo, Geometry geo2, String hash){
        if (geo != geo2){
            
        	/*if (equals(geo,geo2)){
                String hashGeo2 = HashGeometry.getHash(geo2.toText());
                insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfEquals", resourceUri + "Geometry/" + hashGeo2); 
             }
        	else {*/
             if (intersects(geo,geo2)){
                String hashGeo2 = HashGeometry.getHash(geo2.toText());
                insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfIntersects", resourceUri + "Geometry/" + hashGeo2); 
             }
             else {
             if (touches(geo,geo2)){
                String hashGeo2 = HashGeometry.getHash(geo2.toText());
                insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfTouches", resourceUri + "Geometry/" + hashGeo2); 
             }
             //}
        	}
             /*if (within(geo,geo2)){
                String hashGeo2 = HashGeometry.getHash(geo2.toText());
                insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfWithin", resourceUri + "Geometry/" + hashGeo2); 
             }
        	if (overlaps(geo,geo2)){
               String hashGeo2 = HashGeometry.getHash(geo2.toText());
               insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfOverlaps", resourceUri + "Geometry/" + hashGeo2); 
            } 
            if (contains(geo,geo2)){
               String hashGeo2 = HashGeometry.getHash(geo2.toText());
               insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfContains", resourceUri + "Geometry/" + hashGeo2); 
               
            }
            if (crosses(geo,geo2)){
               String hashGeo2 = HashGeometry.getHash(geo2.toText());
               insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfCrosses", resourceUri + "Geometry/" + hashGeo2); 
            }*/
            
            
        }
}
    private boolean overlaps(Geometry geo1, Geometry geo2)  {

        return geo1.overlaps( geo2 );        
        
    }
    private boolean contains(Geometry geo1, Geometry geo2)  {
        
    if  (geo1 != geo2){
        return geo1.contains( geo2 );
    }
    else 
        return false;        
    }
     private boolean crosses(Geometry geo1, Geometry geo2)  {

        return geo1.crosses( geo2 );        
        
    }
     
     private boolean disjoint(Geometry geo1, Geometry geo2)  {

        return geo1.disjoint( geo2 );        
        
    }
     /*private boolean equals(Geometry geo1, Geometry geo2)  {

        return geo1.equals( geo2 );        
        
    }*/
     private boolean intersects(Geometry geo1, Geometry geo2)  {

        return geo1.intersects(geo2 );        
        
    }
     
     private boolean touches(Geometry geo1, Geometry geo2)  {

        return geo1.touches( geo2 );        
        
    }
     private boolean within(Geometry geo1, Geometry geo2)  {

        return geo1.within(geo2) ;        
        
    }
    
  

    private void insertarResourceTypeResource(String r1, String r2) {

        Resource resource1 = model.createResource(r1);
        Resource resource2 = model.createResource(r2);
        model.add(resource1, RDF.type, resource2);
    }
    
         
    private void insertarTripletaLiteral(String s, String p, String o,  String x) {
        //Permite ingresar una tripleta en el rdf
        if (x != null) {
        
            Literal l = model.createTypedLiteral(o, x);
            Resource rGeometry = model.createResource(s);
            Property P = model.createProperty(p);
            rGeometry.addLiteral(P, l);
        } else {
            Resource rGeometry = model.createResource(s);
            Property P = model.createProperty(p);
            rGeometry.addProperty(P, o);
        }
        //   model.write(System.out);
    }

    private void insertarTripletaResource(String s, String p, String o) {
        //Permite ingresar una tripleta en el rdf
        Resource rGeometry = model.createResource(s);
        Property P = model.createProperty(p);
        Resource r2 = model.createResource(o);
        rGeometry.addProperty(P, r2);
        // model.write(System.out);
    }

    private void insertarLabelResource(String r1, String label, String lang) {

        Resource resource1 = model.createResource(r1);
        model.add(resource1, RDFS.label, model.createLiteral(label, lang));
    }

    private String detectLang(String r1){
        String defaultLang = "es";
        //('Barranco%')('Barranquillo%')('Barranc%')('Barrancu%')('Barranquet%')('Barranqueira%')
        if (r1.startsWith("Riu "))
            defaultLang="ca";
        return defaultLang;
    }

    private void insertarField(String s, String p, String o,  String x) {
        //Permite ingresar una tripleta en el rdf
        
      
       
       if (x.equals("class java.lang.double") ){
            String xd = "http://www.w3.org/2001/XMLSchema#double";
            Literal l = model.createTypedLiteral(o, xd);
            Resource rField = model.createResource(s);
            Property P = model.createProperty(p);
            rField.addLiteral(P, l);
           } 
       else{
            if (x.equals("class java.lang.integer") ) {
            String xI = "http://www.w3.org/2001/XMLSchema#Int";
            Literal l = model.createTypedLiteral(o, xI);
            Resource rField = model.createResource(s);
            Property P = model.createProperty(p);
            rField.addLiteral(P, l);
           }
           else{
            if(p.equals("http://datos.igac.gov.co/ontologias/catastro/bogota/URI")) {
            Resource rField = model.createResource(s);
            Property P = model.createProperty(p);
            Resource r2 = model.createResource(o);
            rField.addProperty(P, r2); 
            }
            else{
            Resource rField = model.createResource(s);
            Property P = model.createProperty(p);
            Literal rObject = model.createLiteral(o);
            rField.addLiteral(P, rObject);
            
           }
        }
       }
       
       
       
       }    

}