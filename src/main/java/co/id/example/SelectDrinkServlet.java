package co.id.example;

import co.id.example.model.DrinkType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(
        name = "selectdrinkservlet",
        urlPatterns = "/SelectDrink"
)
public class SelectDrinkServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String drinkType = req.getParameter("Type");

        DrinkService liquorService = new DrinkService();
        DrinkType l = DrinkType.valueOf(drinkType);

        List drinkBrands = liquorService.getAvailableBrands(l);

        req.setAttribute("brands", drinkBrands);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);

    }
}