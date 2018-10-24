package app.servlets;

import app.entities.Category;
import app.entities.Place;
import app.repositories.MongoPlaceRepository;
import app.repositories.MySqlPlaceRepository;
import app.repositories.PlaceRepositoryInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddPlaceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PlaceRepositoryInterface repository = new MySqlPlaceRepository();
            Place addedPlace = new Place(
                    req.getParameter("address"),
                    req.getParameter("name"),
                    Integer.parseInt(req.getParameter("rating")),
                    Category.fromCode(Integer.parseInt(req.getParameter("category")))
            );
            repository.save(addedPlace);
            resp.sendRedirect("http://localhost:8080");
        } catch (Exception exception) {
            PrintWriter writer = resp.getWriter();
            writer.write(exception.getMessage());
        }

    }
}
