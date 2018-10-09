package app.repositories;

import app.entities.Place;
import org.bson.types.ObjectId;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MongoRepositoryTest extends PlaceRepositoryInterfaceTest {
    protected boolean testObjectHasId(Place place) {
        return place.getMongoId() != null;
    }

    protected Object getFakeId() {
        return new ObjectId("abcdef12312313213");
    }
}
