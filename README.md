GeoLOD – Framework for transforming, linking and enrichment of geospatial data to LOD (Beta)
==============

This framework was built looking to give tools to perform the publication, linking, enriching and visualization of geospatial data as Linked Open Data using the standard GeoSPARQL to represent their geometrical and spatial relation aspects. All the tools work as web form where you should give some parameters. Following a more detailed description:

------------------------------------------------------------

SHP2GeoSPARQL (http://localhost:8088/shp2geosparql.jsp)  

Transforming shapefiles to Geosparql. It has options to geospatial inking to LOD sources. It was built based in geometry2rdf1.

Configuration parameters:

//information of shapefile source
* Shapefile location: location of the folder where is located the shapefile that will be converted.
* Shapefile name: name of the shape file without extension

//information to build the URIs
* URI base for the ontology: URI base used to build the URI of each class 
* Namespace of the ontology: characters used to represent the URI base of the ontology in a short way. 
* Ontology class: name or ID of the class in the ontology to put in the URI after the URI Base 
* URI base for resources: URI base used to build the URI of each resource (instance) 
* Id Field: attribute where ID is stored 

//Complementary information 
* CRS source: EPSG number of the coordinate reference system in which are the original data source
* CRS target: EPSG number of the coordinate reference system in which would be stored the transformed data 
* Label Field: attribute where label is stored
* Language: currently support two options Es(Spanish) o En(English)
* Output File directory: location of the folder and name of the output file 
* Include label: yes or not to include a label
* Include Points: yes or not to include centroids
* Include relations: yes or not to include internal spatial relations using GeoSPARQL vocabulary

//For spatial linking with other LOD sources (optional)
* Endpoint: Access URI to the endpoint where is the data stored
* Query: SPARQL query to limit the instances and obtain the URI and the geometry (in WKT)
* Geometry variable: identification of the geometry variable in the SPARQL query, for example:?geo
* URI variable: identification of the URI variable in the SPARQL query, for example:?uri


------------------------------------------------------------

WFS2GeoSPARQL (http://localhost:8088/wfs2geosparql.jsp)

Transforming data from -WFS services to Geosparql. It has options to geospatial linking to LOD sources. It was built based 
in geometry2rdf.

Configuration parameters:

//WFS source descriptors
* WFS GetCapabilities: URI of the get capabilities request
* WFS layer name: name of the layer which will be used


//information to build the URIs
* URI base for the ontology: URI base used to build the URI of each class 
* Namespace of the ontology: characters used to represent the URI base of the ontology in a short way. 
* Ontology class: name or ID of the class in the ontology to put in the URI after the URI Base 
* URI base for resources: URI base used to build the URI of each resource (instance) 
* Id Field: attribute where ID is stored 

//Complementary information 
* CRS source: EPSG number of the coordinate reference system in which are the original data source
* CRS target: EPSG number of the coordinate reference system in which would be stored the transformed data 
* Label Field: attribute where label is stored
* Language: currently support two options Es(Spanish) o En(English)
* Output File directory: location of the folder and name of the output file 
* Include label: yes or not to include a label
* Include Points: yes or not to include centroids
* Include relations: yes or not to include internal spatial relations using GeoSPARQL vocabulary

//For spatial linking with other LOD sources (optional)
* Endpoint: Access URI to the endpoint where is the data stored
* Query: SPARQL query to limit the instances and obtain the URI and the geometry (in WKT)
* Geometry variable: identification of the geometry variable in the SPARQL query, for example:?geo
* URI variable: identification of the URI variable in the SPARQL query, for example:?uri

------------------------------------------------------------

WFS2LD_Connector (http://localhost:8088/wfs2ld_connector.jsp)


This tool make an spatial analysis between instances published as LOD (with geometries serialized as WKT) and through a WFS service, if a spatial relation is found a link showing de type of the spatial relation is build. 
 

Configuration parameters:

LOD source

* Endpoint: Access URI to the endpoint where is the data stored 
* Query: SPARQL query to limit the instances and obtain the URI and the geometry (in WKT)
* Geometry variable: identification of the geometry variable in the SPARQL query, for example:?geo 
* URI variable: identification of the URI variable in the SPARQL query, for example:?uri
* Output file: location of the folder and name of the output file 

WFS source

* Get capabilities: URI of the get capabilities request
* Layer name: name of the layer which will be used


------------------------------------------------------------



WFS2LD_Richer (http://localhost:8088/wfs2ld_richer.jsp)


This tool make an spatial analysis between instances published as LOD (with geometries serialized as WKT) and through a WFS service, if a spatial relation is found a link showing de type of the spatial relation is build and include two attributes to the RDF (enrichment). 
 

Configuration parameters:

LOD source

* Endpoint: Access URI to the endpoint where is the data stored 
* Query: SPARQL query to limit the instances and obtain the URI and the geometry (in WKT)
* Geometry variable: identification of the geometry variable in the SPARQL query, for example:?geo 
* URI variable: identification of the URI variable in the SPARQL query, for example:?uri
* Output file: location of the folder and name of the output file 

WFS source

* Get capabilities: URI of the get capabilities request
* Layer name: name of the layer which will be used

Enrichment attributes identification 

* Enrichment attribute 1: name of the fist attribute to use in the enrichment
* Enrichment attribute 2: name of the second attribute to use in the enrichment

------------------------------------------------------------

The code was integrated over LOD4WFS2
 and could be deployed using the same procces, for more information go to: [LOD4WFS Documentation](http://ifgi.uni-muenster.de/~j_jone02/lod4wfs/LOD4WFS_documentation.pdf) 


A example of map viewer with SPARQL update using a case of use with biodiversity information of the department of Cundinamarca (Colombia) is available by default in: http://localhost:8088/bioviewer.jsp

------------------------------------------------------------
1. http://mayor2.dia.fi.upm.es/oeg-upm/index.php/es/technologies/151-geometry2rdf/index.html
2. https://github.com/jimjonesbr/lod4wfs
