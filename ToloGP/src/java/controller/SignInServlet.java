/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.StaffDAO;
import model.User;
import model.UserDAO;

/**
 *
 * @author Marken Tuan Nguyen
 */
@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String path = "";
        HttpSession session = request.getSession(false);
        session.setAttribute("sessionKey", session.getId());
        
        
        if (request.getParameter("action").equals("logIn")){
                path = "/view/Login.jsp";
                request.getServletContext().getRequestDispatcher(path).forward(request, response);
        } else {
            
            Connection connection = (Connection) getServletContext().getAttribute("connection");
            String userTable = (String) getServletContext().getAttribute("userTable");
            String staffTable = (String) getServletContext().getAttribute("staffTable");

            UserDAO userDB = new UserDAO();
            userDB.getConnection(connection);
            request.setAttribute("userList", userDB.getUserLists(userTable));

            User user = userDB.authenticateUser(userTable, new User(
                    request.getParameter("username"), 
                    request.getParameter("password"))
            );
            if (user!= null){
                System.out.println("user: " + user.toString());
                session.setAttribute("user", user);

                switch(user.getuRole()){
                    case "Admin":
                        path = "/Admin";
                        break;
                    case "Doctor":
                    case "Nurse":
                        path = "/Staff";
                        break;
                }
                
                response.sendRedirect(request.getContextPath() + path);
            }
                
//              test staff
//                StaffDAO staffDB = new StaffDAO();
//                staffDB.getConnection(connection);
//                request.setAttribute("staffList", staffDB.getStaffLists(staffTable));
 
        
        }   

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
