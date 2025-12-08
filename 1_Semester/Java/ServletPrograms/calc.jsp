<!DOCTYPE html>
<html>

<body>

    <form action="calc" method="post">
        Number 1: <input type="text" name="num1"><br><br>
        Number 2: <input type="text" name="num2"><br><br>
        <input type="submit" value="Calculate">
    </form>

    <% if (request.getAttribute("add") !=null && request.getAttribute("mul") !=null) { %>
        <h3>Results</h3>
        Addition: ${add} <br>
        Multiplication: ${mul} <br>
        <% } %>

</body>

</html>