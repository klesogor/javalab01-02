package app.repositories;

import app.entities.Place;
import app.exceptions.DomainException;
import app.exceptions.PlaceNotFoundException;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SQLPlaceRepositoryTest extends PlaceRepositoryInterfaceTest {
    protected boolean testObjectHasId(Place place) {
        return place.getId() != null;
    }

    @Test()
    public void test_crud_operations(){
        super.test_crud_operations();
    }

    @Test(expected = PlaceNotFoundException.class)
    public void test_get_byId_negative_case() throws DomainException{
        super.test_get_byId_negative_case();
    }

    protected Object getFakeId() {
        return 9999;
    }

    @org.junit.Before
    public void setUp() throws Exception {
        super.setUp();
    }
}
