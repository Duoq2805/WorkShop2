package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.dao.AccountDAO;

/**
 *
 * @author ThaiDuong
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            AccountDAO dao = new AccountDAO();
            Account account = dao.getObjectById(username);
            if (account != null && account.getPass().equals(password) && account.getIsUse()) {
                request.getSession().setAttribute("user", account);
                response.sendRedirect("dashboard.jsp");
            } else {
                request.getSession().setAttribute("loginError", true);
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("loginError", true);
            response.sendRedirect("login.jsp");
        }
    }

}
