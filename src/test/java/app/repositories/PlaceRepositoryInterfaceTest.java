package app.repositories;

import app.entities.Category;
import app.entities.Place;
import app.exceptions.DomainException;
import app.exceptions.PlaceNotFoundException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public abstract class PlaceRepositoryInterfaceTest {
    protected PlaceRepositoryInterface repository;
    protected Place testPlace1;
    protected Place testPlace2;
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(PlaceRepositoryInterface.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    public void test_crud_operations()
    {
        try {
            repository.save(testPlace1);
            assertTrue(testObjectHasId(testPlace1));
            repository.save(testPlace2);
            assertTrue(testObjectHasId(testPlace2));
            assertEquals(2, repository.getAll().toArray().length);
            repository.delete(testPlace2);
            assertEquals(testPlace1.getName(), repository.getById(testPlace1.getId()).getName());
            repository.delete(testPlace1);
            assertEquals(0, repository.getAll().toArray().length);
        } catch (Exception exception){
            fail(exception.getMessage());
        }

    }

    @Test(expected = PlaceNotFoundException.class)
    public void test_get_byId_negative_case() throws DomainException
    {
        repository.getById(getFakeId());
    }

    abstract protected boolean testObjectHasId(Place place);

    abstract protected Object getFakeId();


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
    }

}
