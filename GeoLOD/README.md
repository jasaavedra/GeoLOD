GeoLOD – Framework for transforming, linking and enrichment of geospatial data to LOD (Beta)
==============

This framework was built looking to give tools to perform the publication, linking and enriching of geospatial data as Linked Open Data using the standard GeoSPARQL to represent their geometrical and spatial relation aspects. Following a description of the tools:

ActivityToolDescription Access URITransforming and geospatial linkingSHP2GeoSPARQL  Transforming shapefiles to Geosparql. It has options to geospatial linking to LOD sources. It was built based in geometry2rdf1.
http://localhost:8088/shp2geosparql.jspWFS2GeoSPARQLTransforming data from -WFS services to Geosparql. It has options to geospatial linking to LOD sources. It was built based in geometry2rdf.http://localhost:8088/shp2geosparql.jspVinculación y enriquecimiento con WFSWFS2LD_ConnectorThis tool make an spatial analysis between instances published as LOD (with geometries serialized as WKT) and through a WFS service, if a spatial relation is found a link showing de type of the spatial relation is build. It has an option to transform and include two attributes to the RDF. http://localhost:8088/wfs2ld_connector.jsp

1 http://mayor2.dia.fi.upm.es/oeg-upm/index.php/es/technologies/151-geometry2rdf/index.html

---------------

------------------------------------------------------------

---------------

------------------------------------------------------------

