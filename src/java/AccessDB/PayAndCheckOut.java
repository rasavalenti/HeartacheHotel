/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccessDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author qhf13exu
 */
public class PayAndCheckOut extends HttpServlet {

 
    
    static int b_outstanding;
    static String b_notes;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        try {

            String cmpHost = "cmpstudb-02.cmp.uea.ac.uk:5432";
            String myDbName = "groupdk"; //your DATABASE name, same as your username 
            String myDBusername = "groupdk"; // use your username for the database username  
            String myDBpwd = "groupdk"; // use your DB’s password, same as your username  

            // make a string for my DB’s connection url 
            String myDbURL = ("jdbc:postgresql://" + cmpHost + "/" + myDbName);

            Class.forName("org.postgresql.Driver");
            // connect to my database on CMP’s web server.
            Connection connection = DriverManager.getConnection(myDbURL, myDBusername, myDBpwd);
            Statement statement = connection.createStatement();
            //statement.execute("set schema 'HeartacheHotelDB';");

            String getOutstanding = "SELECT b_outstanding, b_notes, "
                    + "checkin, checkout, C.r_no, r_status FROM booking AS A "
                    + "JOIN customer As B ON A.c_no = B.c_no "
                    + "JOIN roombooking AS C ON A.b_ref = C.b_ref "
                    + "JOIN room AS D ON C.r_no = D.r_no "
                    + "WHERE A.b_ref='" + HotelReception.bookRef + "';";

            System.out.println(getOutstanding);
            ResultSet resultSet = statement.executeQuery(getOutstanding);

            while (resultSet.next()) {

                b_outstanding = resultSet.getInt("b_outstanding");
                b_notes = resultSet.getString("b_notes");

                request.setAttribute("b_outstanding", b_outstanding);
                request.setAttribute("b_notes", b_notes);
            }

            System.out.println(b_outstanding);
            System.out.println(b_notes);
            
            String payOutstanding = "UPDATE booking SET b_outstanding = 0 "
                    + "WHERE b_ref=" + HotelReception.bookRef + ";";
            statement.executeQuery(payOutstanding);
            
            connection.close(); 
            
            request.getRequestDispatcher("PaymentReceipt.jsp").forward(request, response);

                    
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AccessServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AccessServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
