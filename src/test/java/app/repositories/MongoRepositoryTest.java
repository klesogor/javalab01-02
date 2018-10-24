package app.repositories;

import app.entities.Category;
import app.entities.Place;
import app.exceptions.DomainException;
import app.exceptions.PlaceNotFoundException;
import org.bson.types.ObjectId;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class MongoRepositoryTest {
    private PlaceRepositoryInterface repository;
    private Place testPlace1;
    private Place testPlace2;

    @Test
    public void test_crud_operations() throws Exception {
        repository.save(testPlace1);
        repository.save(testPlace2);
        assertEquals(2, repository.getAll().toArray().length);
        assertNotNull(testPlace1.getMongoId());
        assertNotNull(testPlace2.getMongoId());
        repository.delete(testPlace2);
        assertEquals(testPlace1.getName(), repository.getById(testPlace1.getMongoId()).getName());
        repository.delete(testPlace1);
    }

    @Test(expected = PlaceNotFoundException.class)
    public void test_get_byId_negative_case() throws Exception {
        repository.getById(getFakeId());
    }

    private ObjectId getFakeId() {
        return new ObjectId();
    }


    @org.junit.Before
    public void setUp() throws Exception {
        testPlace1 = new Place(
                "Test address 1",
                "Test name 1",
                5,
                Category.Restraunt
        );
        testPlace2 = new Place(
                "Test address 2",
                "Test name 2",
                10,
                Category.None
        );
        repository = new MongoPlaceRepository();
    }

}
