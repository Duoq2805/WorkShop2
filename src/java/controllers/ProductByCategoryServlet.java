package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;
import model.dao.ProductDAO;

/**
 *
 * @author ThaiDuong
 */
@WebServlet("/productPortfolio")
public class ProductByCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String typeId = request.getParameter("typeId");
        List<Product> products = new ProductDAO().listByCategory(typeId);
        request.setAttribute("productPortfolio", products);
        request.getRequestDispatcher("/jsp/public/productPortfolio.jsp").forward(request, response);
    }

}
