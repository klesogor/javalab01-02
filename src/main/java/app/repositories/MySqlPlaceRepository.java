package app.repositories;

import app.entities.Category;
import app.entities.Place;
import app.exceptions.DatabaseException;
import app.exceptions.DomainException;
import app.exceptions.PlaceNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlPlaceRepository extends AbstractSqlRepository implements PlaceRepositoryInterface {

    public MySqlPlaceRepository() throws SQLException {
        super();
    }

    public void save(Place entity) throws Exception{
            if(entity.getId() != null){
                updatePlace(entity);
            } else {
                insertPlace(entity);
            }

    }

    public void delete(Place entity) throws DomainException{
        try{
            if(entity.getId() == null){
                throw new PlaceNotFoundException(); //we can't delete what's not in database
            }
            String sql = "DELETE FROM Places WHERE id = ?";
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        }catch (SQLException exception){
            throw  new DatabaseException();
        }
    }

    public Place getById(Object id) throws DomainException{
        try{
            String sql = "SELECT * FROM Places WHERE id = ?";
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setInt(1, (Integer)id);
            ResultSet result = statement.executeQuery();
            if(!result.first()) {
                throw new PlaceNotFoundException();
            }
            return this.parsePlace(result);
        }catch (SQLException exception){
            throw  new DatabaseException();
        }
    }

    public List<Place> getAll() throws DomainException{
        try{
            String sql = "SELECT * FROM Places";
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            ArrayList<Place> list = new ArrayList<Place>();
            while (result.next()){
                list.add(this.parsePlace(result));
            }
            return list;
        }catch (SQLException exception){
            throw  new DatabaseException();
        }
    }

    public void runMigration() throws DomainException
    {
        try {
            String migration = "CREATE TABLE Places(" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "address VARCHAR(210) NOT NULL," +
                    "category INT NOT NULL," +
                    "rating INT NOT NULL" +
                    ")";
            dbConnection.prepareStatement(migration).executeUpdate();
        }catch (SQLException exception){
            throw new DatabaseException();
        }
    }

    private Place parsePlace(ResultSet set) throws DomainException{
        try {
            Place place = new Place(
                    set.getString("address"),
                    set.getString("name"),
                    set.getInt("rating"),
                    Category.fromCode(set.getInt("category"))
            );
            place.setId(set.getInt("id"));
            return place;
        }catch (SQLException exception){throw new DatabaseException(); }

    }

    private void insertPlace(Place entity) throws DatabaseException
    {
        try {
            String sql = "INSERT INTO Places(address, name, category, rating) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, entity.getAddress());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getCategory().get_code());
            statement.setInt(4, entity.getRating());
            statement.executeUpdate();
            ResultSet set = dbConnection.prepareStatement("SELECT LAST_INSERT_ID() id").executeQuery();
            set.first();
            entity.setId(set.getInt("id"));
        } catch(SQLException exception) {
            throw new DatabaseException();
        }
    }

    private void updatePlace(Place entity) throws DatabaseException
    {
        try {
            String sql = "UPDATE Places SET address = ?, name = ?, category = ?, rating = ? WHERE id = ?";
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, entity.getAddress());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getCategory().get_code());
            statement.setInt(4, entity.getRating());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
        }catch (SQLException exception){
            throw new DatabaseException();
        }
    }

}
