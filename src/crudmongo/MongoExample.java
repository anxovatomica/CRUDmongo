/**
 *
 * @author yatusabe
 */
package crudmongo;

/*
IMPORTANTE:
La tabla en MySQL se convierte en una colección en Mongo
La fila se convierte en un documento
La columna se convierte en un campo*/

import com.mongodb.*;

public class MongoExample {
    public static void main(String[] args) {
        
        //Se ejecuta en el localhost en el puerto 27017
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //Instanciamos la basededatos
        DB database;
        //Creamos la bd con el nombre "BaseDatosMongo"
        database = mongoClient.getDB("BaseDatosMongo");

        //Mostramos las bases de datos que existen
        //mongoClient.getDatabaseNames().forEach(System.out::println);
        //Creamos una coleccion llamada "Biblioteca", dejamos las opciones en null
        database.createCollection("Biblioteca", null);

        //Sacamos por pantallas las collecciones
        database.getCollectionNames().forEach(System.out::println);

        /*Creamos la info*/
        //Seleccionamos la tabla "Biblioteca"
        DBCollection collection = database.getCollection("Biblioteca");
        //instanciamos un objecto de tipo "BasicDBObject" con nombre "document" para insertar info
        BasicDBObject document = new BasicDBObject();
        //creamos la info
        document.put("Título", "Hercule Poirot");
        document.put("Autor/a", "Agatha Christie");
        //La insertamos en la coleccion adequada
        collection.insert(document);

        /*Update data*///-->cambiar/actualizar info de la base de datos*/
        //instanciamos un objeto tipo "BasicDBObject" para decirle qué información queremos cambiar 
        BasicDBObject query = new BasicDBObject();
        //Datos que se van a cambiar
        query.put("Título", "Hercule Poirot");
        //Nueva instancia con los nuevos datos
        BasicDBObject newDocument = new BasicDBObject();
        //Instroducimos la nueva información
        newDocument.put("Título", "Asesinato en el orient express");
        //Efectuamos el cambio(update)
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);
        //Cambiamos "newDocument" por "query"
        collection.update(query, updateObject);

        /*Read data*/
        //instanciamos el mismo objeto que antes para poder hacer consultas
        BasicDBObject searchQuery = new BasicDBObject();
        //Le decimos que es lo que queremos buscar
        searchQuery.put("Título", "Asesinato en el orient express");
        //Busca
        DBCursor cursor = collection.find(searchQuery);
        //Recorremos e imprimimnos para ver
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        
        /*Delete data*/
        //Instanciamos 
        BasicDBObject deleteQuery = new BasicDBObject();
        //Le decimos qué es lo que queremos eliminar
        deleteQuery.put("Título", "Asesinato en el orient express");
        //eliminamos
        collection.remove(deleteQuery);
        //database.dropDatabase();
        //FIN
    }
}