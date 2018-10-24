package app.entities;

import app.exceptions.DomainException;
import app.exceptions.InvalidRatingException;
import org.bson.types.ObjectId;

public class Place {
    public static final int LOWEST_RATING = 1;
    public static final int HIGHEST_RATING = 10;
    private Integer _id;
    private ObjectId _mongoId;
    private String _address;
    private String _name;
    private int _rating;
    private Category _category;

    public Place(String address, String name, int rating, Category category) throws DomainException
    {
        if(rating < Place.LOWEST_RATING || rating > Place.HIGHEST_RATING)
            throw new InvalidRatingException();
        _address = address;
        _name = name;
        _rating = rating;
        _category = category;
    }

    public void setRating(int rating) throws InvalidRatingException {
        if(rating < Place.LOWEST_RATING || rating > Place.HIGHEST_RATING)
            throw new InvalidRatingException();
        this._rating = rating;
    }

    public int getRating() {
        return _rating;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        this._address = address;
    }

    public Category getCategory() {
        return _category;
    }

    public void setCategory(Category category) {
        this._category = category;
    }

    public void setId(Integer id)
    {
        this._id = id;
    }

    public Integer getId()
    {
        return this._id;
    }

    public ObjectId getMongoId() {
        return _mongoId;
    }

    public void setMongoId(ObjectId _mongoId) {
        this._mongoId = _mongoId;
    }

}
