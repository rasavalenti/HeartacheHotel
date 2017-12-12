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
 * @author fvq13ndu
 * 
 * This is the servlet that is used to cancel a booking. It follows from 
 * ManageBooking.jsp and goes to BookingCancel.jsp where the customer can confirm
 * that it is indeed the booking they want to cancel
 */
public class CancelBooking extends HttpServlet {

    static String checkin;
    static String checkout;
    static String b_ref;
    static double b_cost;
    static String r_nos;
    static String user;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            //Connecting to the database
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

            statement.execute("set schema 'HeartacheHotelDB';");

            String c_b_ref = request.getParameter("c_b_ref");
            String b_outstanding = null;
            String b_notes = null;
            
            //SQL statement which gets information which can then be displayed in BookingCancel.jsp before the customer cancels the booking
            String SQLStatement = "select b_ref, b_cost, b_outstanding, b_notes from booking where b_ref=" + c_b_ref + ";";

            ResultSet resultSet = statement.executeQuery(SQLStatement);

            while (resultSet.next()) {
                b_ref = resultSet.getString("b_ref");
                b_cost = Double.parseDouble(resultSet.getString("b_cost"));
                b_outstanding = resultSet.getString("b_outstanding");
                b_notes = resultSet.getString("b_notes");
            }
            //Setting the attributed which can then be retrieved in BookingCancel.jso
            request.setAttribute("b_ref", b_ref);
            request.setAttribute("b_cost", b_cost);
            request.setAttribute("b_outstanding", b_outstanding);
            request.setAttribute("b_notes", b_notes);
            
            //Retrieving the checkin/checkout dates for the booking
            SQLStatement = "select DISTINCT(rb.checkin),rb.checkout from booking b, roombooking rb where b.b_ref=rb.b_ref and b.b_ref=" + b_ref + ";";

            resultSet = statement.executeQuery(SQLStatement);

            while (resultSet.next()) {
                checkin = resultSet.getString("checkin");
                checkout = resultSet.getString("checkout");
            }

            request.setAttribute("checkin", checkin);
            request.setAttribute("checkout", checkout);

            //getting the room number for the booking
            SQLStatement = "select rb.r_no from booking b, roombooking rb where b.b_ref=rb.b_ref and b.b_ref=" + b_ref + ";";

            resultSet = statement.executeQuery(SQLStatement);

            ArrayList<String> r_nos_array = new ArrayList();
            while (resultSet.next()) {
                r_nos_array.add(resultSet.getString("r_no"));
            }
            System.out.println(r_nos_array);

            r_nos = r_nos_array.toString();
            r_nos = r_nos.substring(1, r_nos.length());
            r_nos = r_nos.substring(0, r_nos.length() - 1);

            request.setAttribute("r_nos", r_nos);
            
            //The exit link differes dopending on who is using the servlet, this is due to
            //reception and customer using the servlet. So for the customer the exit button takes them to Home.html
            //while the reception gets takes to ReceptionPortal.html
            String exit_link;
            if (user.equals("reception")) {
                exit_link = "\"javascript:location.href = 'ReceptionPortal.html'\"";
                request.setAttribute("exit_link", exit_link);
            }

            if (user.equals("customer")) {
                exit_link = "\"javascript:location.href = 'Home.html'\"";
                request.setAttribute("exit_link", exit_link);
            }

            request.getRequestDispatcher("BookingCancel.jsp").forward(request, response);

            connection.close();

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
