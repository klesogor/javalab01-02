package app.entities;

import app.exceptions.DomainException;

public enum Category {
    Bar(1), Restraunt(2), Cafe(3), Cinema(4), Pub(5), None(6);

    private int _code;

    Category(int code)
    {
        _code = code;
    }

    public int get_code() {
        return _code;
    }

    public static Category fromCode(int code) throws DomainException{
        switch (code){
            case 1:
                return Bar;
            case 2:
                return Restraunt;
            case 3:
                return Cafe;
            case 4:
                return Cinema;
            case 5:
                return Pub;
            case 6:
                return None;
            default:
                throw new DomainException();
        }
    }
}
