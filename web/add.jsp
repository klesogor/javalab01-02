<%@ page import="app.entities.Category" %>
<%@ page import="app.entities.Place" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="app.css">
    <title>Add new place</title>
</head>
<body>
<main class="container">
    <form action="/add" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" name="name" class="form-control" id="name" placeholder="Place name">
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" name="address" class="form-control" id="address" placeholder="Place address">
        </div>
        <div class="form-row">
            <div class="col">
                <label for="rating">Rating:</label>
                <select id="rating" name="rating">
                    <%
                        for (
                                int i = Place.LOWEST_RATING;
                                i <= Place.HIGHEST_RATING;
                                i++
                        ) {
                            out.println(String.format("<option value='%s'>%s</option>", i, i));
                        }
                    %>
                </select>
            </div>
            <div class="col">
                <label for="category">Category:</label>
                <select id="category" name="category">
                    <%
                        Category[] categories = Category.values();
                        for (Category category : categories) {
                            out.println(String.format(
                                    "<option value='%s'>%s</option>",
                                    category.get_code(),
                                    category.name()
                            ));
                        }
                    %>
                </select>
            </div>
        </div>
        <div class="form-group text-center">
            <button type="submit" class="btn btn-dark">Submit</button>
        </div>
    </form>
</main>
</body>
</html>
