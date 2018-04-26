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
//import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.collection.FeatureIteratorImpl;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
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
public class Wfs2Rdf {
    
	   public Model model;
       public String nsgeo = "http://www.opengis.net/ont/geosparql#"; 
       public String sf = "http://www.opengis.net/ont/sf#";   
       public String ontologyUri;
       public String nsOntology ;
       public String resourceUri;
       public String lang;
       public String featureClass;
       public String attribute;
       public String label;
        //String linkUriField = myproperties.getProperty("linkUriField");
       public String outputFile;
        //String shpLinkString = myproperties.getProperty("shpLink")+"/"+ myproperties.getProperty("shpName")+".shp";
       public String includeLabel;
       public String includePoints;
       public String includeRelations;
       public String ignore = "";
       public String wfsGetCapabilities;
       public String modelDirectory;
       public String CRSsource; 
       public String CRStarget;   
       public String endpoint;
       public String query;
       public String geometry;
       public String uri;  
       public String strI;  
       public ResultSet LodData;
       public WfsRdfLink linkBase;
       public Geometry o;
       public String namn1;
       public String layerWFS;       
       public List<QuerySolution> UrisLinked ;
      
       
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
       public void setAttribute(String attribute)throws IOException {
           
           this.attribute = attribute;
       }
       public String getAttribute() {
  
    	   return attribute;
       }
       public void setFeatureClass(String featureClass)throws IOException {
           
           this.featureClass = featureClass;
       }
       public String getFeatureClass() {
  
    	   return featureClass;
       }
       public void setLabel(String label)throws IOException {
           
           this.label= label;
       }
       public String getLabel() {
  
    	   return label;
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
       public void setWfsGetCapabilities(String wfsGetCapabilities)throws IOException {
           
           this.wfsGetCapabilities= wfsGetCapabilities;
       }
       public String getWfsGetCapabilities() {
  
    	   return wfsGetCapabilities;
       }
       public void setModelDirectory(String modelDirectory)throws IOException {
           
           this.modelDirectory= modelDirectory;
       }
       public String getModelDirectory() {
  
    	   return modelDirectory;
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
       
       public void setLayerWFS(String layerWFS)throws IOException {
           
           this.layerWFS = layerWFS;
       }
       public String getLayerWFS() {
  
    	   return layerWFS;
       }
     
       
       
       public Wfs2Rdf() throws URISyntaxException{
    	   nsgeo = "http://www.opengis.net/ont/geosparql#"; 
           sf = "http://www.opengis.net/ont/sf#";   
           
           ontologyUri = null;
           nsOntology = null ;
           resourceUri = null;
           lang = null;
           featureClass = null;
           attribute = null;
           label = null;
            //String linkUriField = myproperties.getProperty("linkUriField");
           outputFile = null;
            //String shpLinkString = myproperties.getProperty("shpLink")+"/"+ myproperties.getProperty("shpName")+".shp";
           includeLabel = null;
           includePoints = null;
           includeRelations = null;
           ignore = "";
           wfsGetCapabilities = null;
    	   File jarFile = new File(Wfs2Rdf.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    	   File jarDir = jarFile.getParentFile();
    	   File folder = new File(jarDir+"/modelo"); 
    	   File rdfFolder = new File(jarDir+"/rdf");
    	   rdfFolder.mkdir();
    	   modelDirectory = folder.getPath();
    	   endpoint = null;
           query = null;
           geometry = null;
           uri = null;
           layerWFS = null;
           
           
       }
        
   
    
    @SuppressWarnings("deprecation")
	public void Convertor() throws ClassNotFoundException, IOException, NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, ParseException {
        
        //consultar endpoint y obtener resultados en un resultset
       
        String getCapabilities = wfsGetCapabilities; 
        Map connectionParameters = new HashMap();
        connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL", getCapabilities );
        
        DataStore data = DataStoreFinder.getDataStore( connectionParameters );
        System.out.println("conectando con:"+ data.toString());
        // Step 3 - discovery
         //String typeNames[] = data.getTypeNames();
         String typeName = this.layerWFS;
         //JOptionPane.showMessageDialog(null, "Tipo de dato:"+ typeName );
    
    
    // Step 4 - target
        FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = data.getFeatureSource(this.layerWFS);
        //JOptionPane.showMessageDialog(null, "Tipo de dato:"+ featureSource.toString() );
        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = featureSource.getFeatures() ;
        System.out.println( "Numero de features:"+collection.size() );
                      
        org.geotools.feature.FeatureIterator<SimpleFeature> iterator = collection.features();          
        int  collectionSize = collection.size();
        
        int num = 0;
        
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
    
        model=TDBFactory.createModel(modelDirectory);
        model.removeAll();
        model.setNsPrefix(nsOntology, ontologyUri); 
        model.setNsPrefix("resource", resourceUri);
        model.setNsPrefix("geo", nsgeo);
        model.setNsPrefix("sf", sf);

        while (iterator.hasNext()) {
            
            
            
            SimpleFeatureImpl feature = (SimpleFeatureImpl) iterator.next();            
            o = (Geometry) feature.getDefaultGeometry();
            
            
                                             
            namn1 = feature.getAttribute(attribute).toString();
            namn1 = namn1.toLowerCase();
            System.out.println("###################El valor del ID es -->"+ namn1);
            String namn2 = feature.getAttribute(label).toString();
            System.out.println("###################El valor de FEATURENAME2 is -->"+ namn2);
            
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
                   
            // Si el elemento tiene de nombre N_P, no sabemos como actuar con el
            if (!namn1.equals(ignore)) //&& (namn1.startsWith("Río") || namn1.startsWith("Riu") || namn1.startsWith("Rio"))
            {
                String tipo = featureClass;
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
                
                
                
                   
               // Property property = feature.getProperties();
                //String name = property.getName().toString();
                //Object value = feature.getAttribute( property.getName() );
                
                //insertGetFeature(wfsGetCapabilities, typeName, label, namn2, resourceUri + aux, ontologyUri + "getFeature");
               
               
                System.out.println("object converted-->"+namn1);
                
                
                
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
                if((name.equals(attribute))||(name.equals(label))||(name.equals("the_geom"))){
                  
                System.out.print( name+"="+value+"," );
                }
                else 
                {    
                    String type = value.getClass().toString();
                    //JOptionPane.showMessageDialog(null, name + stringValue);
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
                    //insertarMultiPolygon(resourceUri, aux, hash, o, collectionSize, collection, includeRelations);     
                    MultiPolygon multiPolygon = (MultiPolygon) o;
                    for (int i = 0; i < multiPolygon.getNumGeometries(); ++i) {
                    Geometry tmpGeometry = multiPolygon.getGeometryN(i);
                    if (tmpGeometry.getGeometryType().equals("Polygon")) {
                    insertarPolygon( resourceUri, aux, hash, tmpGeometry,  collectionSize, collection, includeRelations);
                    } else if (tmpGeometry.getGeometryType().equals("LineString")) {
                    insertarLineString(resourceUri, aux, hash, tmpGeometry, collectionSize, collection, includeRelations);
                    }
                 }      
                }
                else if (o.getGeometryType().equals("MultiLineString")){
                    MultiLineString multiLineString = (MultiLineString) o;
                    for (int i = 0; i < multiLineString.getNumGeometries(); ++i) {
                    Geometry tmpGeometry = multiLineString.getGeometryN(i);
                    if (tmpGeometry.getGeometryType().equals("Polygon")) {
                    insertarPolygon( resourceUri, aux, hash, tmpGeometry,  collectionSize, collection, includeRelations);
                    } else if (tmpGeometry.getGeometryType().equals("LineString")) {
                    insertarLineString(resourceUri, aux, hash, tmpGeometry, collectionSize, collection, includeRelations);
                    }
                }
                }
                 else if (o.getGeometryType().equals("MultiPoint")){
                    insertarMultiPoint(resourceUri, aux, hash, o, collectionSize, collection, includeRelations);
                 }
                
            }
            else {
                System.out.println("No transformamos el elemento número-->"+num);
            }
            /*if (shpLinkString != null ){
            insertarLinks(resourceUri, namn1, o, collection, shpLinkString, linkUriField );
            }
            num++;
            if (num>2)
                break;*/
            
            
            
            // prints name=Canada,classification=1,height=20.5,location=POINT( -124, 52 ),
            /*List<Object> fields = feature.getAttributes();
            SimpleFeatureType featureType = feature.getFeatureType();
            List<AttributeDescriptor> atributteDescriptors = featureType.getAttributeDescriptors();
                      
            //Iterator itAttributes = fields.iterator();          
            if(fields.size() > 0){
            		
            	for (int y=0; y < fields.size(); y++) {
            	 //Object value=fields.get(y); 
            	// JOptionPane.showMessageDialog(null, value.toString());
            //}
            
            // while (itAttributes.hasNext()) {
            	 String value  = feature.getAttribute(y).toString();   
            	 JOptionPane.showMessageDialog(null, value);
            	 String Alias = atributteDescriptors.get(y).getLocalName();
            	 JOptionPane.showMessageDialog(null, Alias);
            	  //optionPane.showMessageDialog(null, itAttributes.toString());
                 /*String getAttribute = feature.getAttribute(itAttributes.toString()).toString();
                  String nameAttribute = itAttributes.toString();
                  
                  if ((Alias.equals(attribute))||(Alias.equals(label))){
                     
                  }
                  else
                  {
                      insertarTripletaResource(resourceUri + attribute, ontologyUri + nameAttribute, resourceUri +  getAttribute );
                  }
              }
            }*/
            
            
        }
       
        //JOptionPane.showMessageDialog(null, strI);
        
        FileOutputStream out = new FileOutputStream(outputFile);
        model.write(out,"Turtle");
        //regenerateWFS();
        
              

    }
 
   public void regenerateWFS(){
	   WFSFeature feature = new WFSFeature();
	  
		boolean isValidEntry = true;
		
		if(Facade.getInstance().isFeatureNameValid(this.featureClass)){
							
			if(Facade.getInstance().existsFeature(this.featureClass)){					
				isValidEntry = false;
				System.out.print("Invalid Feature Name. The feature '" + this.featureClass + "' already exists. <br>" );						
				
			}  else {
								
				feature.setName(this.featureClass.toLowerCase());
									
			}
		
			
		} else {
		
			isValidEntry = false;			
			System.out.print("Invalid Feature Name. It must contain either alphanumeric characters or '_'. <br>");			
		}
		
		feature.setFeatureAbstract("no abstract");
		
		feature.setTitle(this.featureClass);	
		
	    feature.setKeywords("no keywords");
		
	    feature.setEnabled(true);
				
		feature.setCRS(this.CRStarget);
		
		feature.setQuery("SELECT DISTINCT ?a ?b ?c WHERE {?a ?b ?c}"); 
		
		Facade.getInstance().addFeature(feature);
		System.out.print("<script>alert(\"Feature '" + feature.getName() + "' successfully stored. \")</script>");	 
	   
	   
   }
   
    
    
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
    
    private void insertarLineString(String resourceUri, String resource, String hash, Geometry geo, int collectionSize, FeatureCollection collection, String includeRelations )throws UnsupportedEncodingException{
        
        insertarResourceTypeResource(resourceUri+ "Geometry/"+ hash, nsgeo + URLEncoder.encode("LineString", "utf-8").replace("+", "%20"));
        insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri + "Geometry/" + hash);
        insertarTripletaLiteral(resourceUri +"Geometry/"+ hash, nsgeo + "asWKT" , geo.toText(), nsgeo + "wktLiteral");
        if (includeRelations.equals("yes")){  
        org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features(); 
        for (int i=0; i< collectionSize; i++) {
            
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            }   
            
         }  
        }
    }

    private void insertarPolygon(String resourceUri, String resource, String hash, Geometry geo, int collectionSize, FeatureCollection collection, String includeRelations) throws IOException, ParseException{
        
       insertarResourceTypeResource(resourceUri+ "Geometry/"+ hash, sf + URLEncoder.encode("Polygon", "utf-8").replace("+", "%20"));
       insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri + "Geometry/" + hash);
       insertarTripletaLiteral(resourceUri +"Geometry/"+ hash, nsgeo + "asWKT" , geo.toText(), sf + "wktLiteral");
       if (includeRelations.equals("yes")){ 
       org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features();
        for (int i=0; i< collectionSize; i++) {
            
        	
        	
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            
           
            if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            }   
         }
       }
    }
    
    private void insertarCentroide(String resourceUri, String resource, Point centroide, String hashPoint) throws UnsupportedEncodingException{
        
       insertarResourceTypeResource(resourceUri+ "Geometry/"+ hashPoint, sf + URLEncoder.encode("Point", "utf-8").replace("+", "%20"));
       insertarTripletaResource(resourceUri + resource,nsgeo + "hasGeometry", resourceUri + "Geometry/" + hashPoint);
       insertarTripletaLiteral(resourceUri +"Geometry/"+ hashPoint, nsgeo + "asWKT" , centroide.toText(), sf + "wktLiteral");
    
    }
    
    private void insertarPoint(String resourceUri, String resource, String hash, Geometry geo, int collectionSize, FeatureCollection collection, String includeRelations) throws UnsupportedEncodingException{
     insertarResourceTypeResource(resourceUri+ "Geometry/"+ hash, sf + URLEncoder.encode("Point", "utf-8").replace("+", "%20"));
     insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri + "Geometry/" + hash);
     insertarTripletaLiteral(resourceUri +"Geometry/"+ hash, nsgeo + "asWKT" , geo.toText(), sf + "wktLiteral");
     if (includeRelations.equals("yes")){ 
     org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features();
        for (int i=0; i< collectionSize; i++) {
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            }              
         }
     }
    }
       
    
    private void insertarMultiPolygon(String resourceUri, String resource, String hash, Geometry geo,int collectionSize, FeatureCollection collection, String includeRelations) throws UnsupportedEncodingException{
        
        insertarResourceTypeResource(resourceUri + "Geometry/" + hash, sf + URLEncoder.encode("MultiPolygon", "utf-8").replace("+", "%20"));
        insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri  + "Geometry/" + hash);
        insertarTripletaLiteral(resourceUri + "Geometry/" + hash, nsgeo + "asWKT" , geo.toText(), sf + "wktLiteral");
        if (includeRelations.equals("yes")){ 
        org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features();
        
        for (int i=0; i< collectionSize; i++) {
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            }   
         }
        }
    }

    private void insertarMultiPoint(String resourceUri, String resource, String hash, Geometry geo, int collectionSize, FeatureCollection collection, String includeRelations) throws UnsupportedEncodingException{
        
       insertarResourceTypeResource(resourceUri+ "Geometry/"+ hash, sf + URLEncoder.encode("MultiPoint", "utf-8").replace("+", "%20"));
        insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri + "Geometry/" + hash);
        insertarTripletaLiteral(resourceUri +"Geometry/"+ hash, nsgeo + "asWKT" , geo.toText(), sf + "wktLiteral");
       if (includeRelations.equals("yes")){ 
       org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features();
        for (int i=0; i< collectionSize; i++) {
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            }             
         }
       }

    }
    
     private void insertarMultiLineString(String resourceUri, String resource, String hash, Geometry geo, int collectionSize, FeatureCollection collection, String includeRelations) throws UnsupportedEncodingException{
        
        insertarResourceTypeResource(resourceUri+ "Geometry/"+ hash, nsgeo + URLEncoder.encode("MultiLineString", "utf-8").replace("+", "%20"));
        insertarTripletaResource(resourceUri + resource,nsgeo + "defaultGeometry", resourceUri + "Geometry/" + hash);
        insertarTripletaLiteral(resourceUri +"Geometry/"+ hash, nsgeo + "asWKT" , geo.toText(), nsgeo + "wktLiteral");
        if (includeRelations.equals("yes")){ 
        org.geotools.feature.FeatureIterator<SimpleFeature> iterator2 = collection.features();
        for (int i=0; i< collectionSize; i++) {
            
            SimpleFeatureImpl featureP = (SimpleFeatureImpl) iterator2.next();                   
            Geometry geo2 = (Geometry) featureP.getDefaultGeometry();
            if  (equals(geo,geo2)== false){
            relacionesTopologicas(resourceUri, geo, geo2, hash);
            }         
         }
        }
    }
     
     private void relacionesTopologicas (String resourceUri, Geometry geo, Geometry geo2, String hash){
        if (geo != geo2){
            
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
            }
            
            if (equals(geo,geo2)){
               String hashGeo2 = HashGeometry.getHash(geo2.toText());
               insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfEquals", resourceUri + "Geometry/" + hashGeo2); 
            }
            /*if (intersects(geo,geo2)){
               String hashGeo2 = HashGeometry.getHash(geo2.toText());
               insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfIntersects", resourceUri + "Geometry/" + hashGeo2); 
            }*/
            if (touches(geo,geo2)){
               String hashGeo2 = HashGeometry.getHash(geo2.toText());
               insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfTouches", resourceUri + "Geometry/" + hashGeo2); 
            }
            if (within(geo,geo2)){
               String hashGeo2 = HashGeometry.getHash(geo2.toText());
               insertarTripletaResource(resourceUri +"Geometry/"+ hash ,nsgeo + "sfWithin", resourceUri + "Geometry/" + hashGeo2); 
            }
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
     private boolean equals(Geometry geo1, Geometry geo2)  {

        return geo1.equals( geo2 );        
        
    }
     private boolean intersects(Geometry geo1, Geometry geo2)  {

        return geo1.intersects( geo2 );        
        
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
        if (x != null ) {
        
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
    
    private void insertarPrueba(String s, String p, String o) {
        //Permite ingresar una tripleta en el rdf
              
            Resource object = model.createResource(o);
            //JOptionPane.showMessageDialog(null, object.toString());
            Resource subject = model.createResource(s);
            Property property = model.createProperty(p);
            subject.addProperty(property, object);
        
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
    
    private void insertarField(String s, String p, String o,  String x) {
        //Permite ingresar una tripleta en el rdf
        
      
       
       if (x.equals("class java.lang.Double")){
            String xd = "http://www.w3.org/2001/XMLSchema#double";
            Literal l = model.createTypedLiteral(o, xd);
            Resource rField = model.createResource(s);
            Property P = model.createProperty(p);
            rField.addLiteral(P, l);
           } 
       else{
            if (x.equals("class java.lang.Int") ){
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
       
      
    
        
        
        
        //   model.write(System.out);
    

    private String detectLang(String r1){
        String defaultLang = "es";
        //('Barranco%')('Barranquillo%')('Barranc%')('Barrancu%')('Barranquet%')('Barranqueira%')
        if (r1.startsWith("Riu "))
            defaultLang="ca";
        return defaultLang;
    }
    /*
     private void insertGetFeature(String getCapabilities ,String typeName,String attribute,String stringValue, String resName, String resProp ) throws UnsupportedEncodingException{
        
             
      String getCapabilities1 = getCapabilities.replace("Capabilities", "Feature");
     // String complemented = getCapabilities1+"&typeName="+typeName+"&Filter=<Filter><PropertyIsEqualTo><PropertyName>"+attribute+"</PropertyName><Literal>"+stringValue+"</Literal></PropertyIsEqualTo></Filter>";
      //String UTF8 = URLEncoder.encode(complemented, "utf-8");    

       
       Resource rField = model.createResource(resName);
       Property P = model.createProperty(resProp);  
       Resource r2 = model.createResource(getCapabilities1);
       rField.addProperty(P, r2);
       
    }
    */

}