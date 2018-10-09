package app.servlets;

import app.entities.Category;
import app.entities.Place;
import app.exceptions.DomainException;
import app.repositories.MongoPlaceRepository;
import app.repositories.MySqlPlaceRepository;
import app.repositories.PlaceRepositoryInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetPlacesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PlaceRepositoryInterface repository = new MongoPlaceRepository();
            req.setAttribute("places", repository.getAll());
            req.getRequestDispatcher("/list.jsp").forward(req, resp);
        }catch (DomainException exception){
            PrintWriter writer = resp.getWriter();
            writer.write(exception.getMessage());
        }
    }
}
