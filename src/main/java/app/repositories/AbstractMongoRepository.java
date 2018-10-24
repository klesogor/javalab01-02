package app.repositories;

import app.exceptions.DomainException;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public abstract class AbstractMongoRepository  {
    protected MongoDatabase database;
    protected static String databaseName = "javalab01";

    public AbstractMongoRepository() throws DomainException
    {
        MongoClient client = new MongoClient();
        database = client.getDatabase(databaseName);
    }
}
