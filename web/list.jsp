<%@ page import="app.entities.Place" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="app.css">
    <title>Places Manager Webapp</title>
  </head>
  <body>
  <main class="container">
    <a role="button" class="btn btn-dark" href="/add">
      <i class="fas fa-plus"></i>
      Add new place
    </a>
    <table class="table">
      <thead class="thead-dark">
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Address</th>
          <th scope="col">Rating</th>
          <th scope="col">Category</th>
        </tr>
      </thead>
      <tbody>
      <%
        ArrayList<Place> places = (ArrayList<Place>)request.getAttribute("places");
        if(places == null){
        } else {
          for (Place place : places) {
            out.println("<tr>");
            out.println(String.format("<td>%s</td>", place.getName()));
            out.println(String.format("<td>%s</td>", place.getAddress()));
            out.println(String.format("<td>%s</td>", place.getRating()));
            out.println(String.format("<td>%s</td>", place.getCategory().name()));
            out.println("<tr>");
          }
        }
      %>
      </tbody>
    </table>
  </main>

  </body>
</html>
