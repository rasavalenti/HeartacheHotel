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
 */
public class ManageBooking extends HttpServlet {

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
            String insertSQL;

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
//                statement.executeUpdate(insertSQL);
            statement.execute("set schema 'HeartacheHotelDB';");

            String c_no = request.getParameter("manage_c_no");
            String c_email = request.getParameter("email");

            System.out.println(c_email);

            String SQLStatement = "select c.c_no, c.c_name, c.c_email, c.c_address, "
                    + "c.c_cardno from customer c where c.c_no=" + c_no + " and c_email='" + c_email + "';";

            System.out.println(SQLStatement);
            statement.executeQuery(SQLStatement);

            ResultSet resultSet = statement.executeQuery(SQLStatement);

            String c_name = null;
            String c_address = null;
            String c_cardno = null;

            while (resultSet.next()) {
                c_name = resultSet.getString("c_name");
                c_cardno = resultSet.getString("c_cardno");
                c_address = resultSet.getString("c_address");
                c_email = resultSet.getString("c_email");
            }

            if (c_cardno.length() == 16) {
                String secret_c_cardno = "**** **** **** " + c_cardno.substring(11, 15);
                request.setAttribute("manage_c_cardno", secret_c_cardno);
            }

            String SQLStatement2 = "select b_ref from booking  where c_no=" + c_no + ";";

            System.out.println(SQLStatement2);
            statement.executeQuery(SQLStatement2);

            resultSet = statement.executeQuery(SQLStatement2);

            ArrayList<String> bookingReferences = new ArrayList();
            while (resultSet.next()) {
                bookingReferences.add(resultSet.getString("b_ref"));
            }
            System.out.println(bookingReferences);

            String b_refs = bookingReferences.toString();
            b_refs = b_refs.substring(2, b_refs.length());
            b_refs = b_refs.substring(0, b_refs.length() - 2);

            int numOfBRefs = bookingReferences.size();

            request.setAttribute("manage_c_no", c_no);
            request.setAttribute("manage_c_name", c_name);
            request.setAttribute("manage_c_address", c_address);
            request.setAttribute("manage_c_email", c_email);
            request.setAttribute("numOfBRefs", numOfBRefs);
            request.setAttribute("bookingReferences", b_refs);
            
//            ArrayList<String> rooms;
//            for (String b_ref : bookingReferences) {
//                rooms = new ArrayList();
//                String showRooms = "select rb.r_no from "
//                        + "booking b, roombooking rb where "
//                        + "b.b_ref=rb.b_ref and b.b_ref=" + b_ref + ";";
//                resultSet = statement.executeQuery(showRooms);
//                while (resultSet.next()) {
//                    rooms.add(resultSet.getString("r_no"));
//                }
//                System.out.println(rooms);
//            }
            

            request.getRequestDispatcher("BookingManage.jsp").forward(request, response);

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
