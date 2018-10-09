package app.repositories;

import app.entities.Category;
import app.entities.Place;
import app.exceptions.DomainException;
import app.exceptions.PlaceNotFoundException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoPlaceRepository extends AbstractMongoRepository implements PlaceRepositoryInterface {
    private MongoCollection<Document> collection;
    public MongoPlaceRepository() throws DomainException{
        super();
        collection = database.getCollection("places");
    }

    public void save(Place entity) throws DomainException {
        if(entity.getMongoId() != null){
            updatePlace(entity);
        } else {
            insertPlace(entity);
        }
    }

    public void delete(Place entity) throws DomainException {
        collection.deleteOne(new Document("_id", entity.getMongoId()));
    }

    public Place getById(Object id) throws DomainException {
        Document entity = collection.find(new Document("_id", (ObjectId) id)).first();
        return parsePlace(entity);
    }

    public List<Place> getAll() throws DomainException {
        Iterable<Document> results = collection.find();
        ArrayList<Place> places = new ArrayList<Place>();
        for (Document place: results) {
            places.add(parsePlace(place));
        }
        return places;
    }

    private Document parseDocumentFromPlace(Place entity) {
        return (new Document())
                .append("address", entity.getAddress())
                .append("name", entity.getName())
                .append("category", entity.getCategory().get_code())
                .append("rating", entity.getRating());
    }

    private void insertPlace(Place entity){
        Document object = parseDocumentFromPlace(entity);
        collection.insertOne(object);
        entity.setMongoId((ObjectId)object.get("_id"));
    }

    private void updatePlace(Place entity){
        collection.updateOne(
                new BasicDBObject("_id", entity.getMongoId()),
                new BasicDBObject("$set", parseDocumentFromPlace(entity))
        );
    }

    private Place parsePlace(Document place) throws DomainException{
        if(place == null){
            throw new PlaceNotFoundException();
        }

        Place retrivedPlace = new Place(
                place.getString("address"),
                place.getString("name"),
                place.getInteger("rating"),
                Category.fromCode(place.getInteger("category"))
        );
        retrivedPlace.setMongoId((ObjectId) place.get("_id"));
        return retrivedPlace;
    }
}
