<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
    <body>
        <h1>
            Available Brands
        </h1>
        <%
        List result= (List) request.getAttribute("brands");
        Iterator it = result.iterator();
        out.println("<h4>We have:</h4>");
        out.println("<ul>");
        while(it.hasNext()){
            out.println("<li>"+ it.next() + "</li>");
        }
        out.println("</ul>");
        %>
        <input type="button" value="Back" onclick="history.back()">

    </body>
</html>