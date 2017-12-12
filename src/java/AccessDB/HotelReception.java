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
public class HotelReception extends HttpServlet {

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
    
    // set static variables to be accessed by other servlets
    static int no_rooms;
    static String bookRef;
    static ArrayList<String> r_nos;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        try {
            // The following lines are here to check the connection between sql and netbeans
//            String insertSQL;
//            insertSQL = "insert into customer values (987654, 'Ann Hinchcliffe14', 'Ann.Hinchcliffe@yahoo.com', '81 New Road, Acle NR13 7GH', 'V', '10/16', '8948106927123585');";
//            System.out.println(insertSQL);
            
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
//            statement.execute(insertSQL);
            statement.execute("set schema 'HeartacheHotelDB';");

            bookRef = request.getParameter("bookingRefInput");

            System.out.println("Booking ref is: " + bookRef);

            // get customer details for booking reference
            String getCustomer = "SELECT A.b_ref, A.c_no, c_name, c_email, "
                    + "c_address, c_cardtype, c_cardexp, c_cardno, b_outstanding, "
                    + "checkin, checkout, C.r_no, r_status FROM booking AS A "
                    + "JOIN customer As B ON A.c_no = B.c_no "
                    + "JOIN roombooking AS C ON A.b_ref = C.b_ref "
                    + "JOIN room AS D ON C.r_no = D.r_no "
                    + "WHERE A.b_ref='" + bookRef + "';";

            ResultSet resultSet = statement.executeQuery(getCustomer);

            String c_name, c_email, c_address, checkin, checkout;

            while (resultSet.next()) {

                c_name = resultSet.getString("c_name");
                c_email = resultSet.getString("c_email");
                c_address = resultSet.getString("c_address");
                checkin = resultSet.getString("checkin");
                checkout = resultSet.getString("checkout");

                request.setAttribute("bookRef", bookRef);
                request.setAttribute("c_name", c_name);
                request.setAttribute("c_email", c_email);
                request.setAttribute("c_address", c_address);
                request.setAttribute("checkin", checkin);
                request.setAttribute("checkout", checkout);
            }

            // get number of rooms linked to the booking ref
            String numRooms = "SELECT COUNT(C.r_no) FROM booking AS A "
                    + "JOIN customer As B ON A.c_no = B.c_no "
                    + "JOIN roombooking AS C ON A.b_ref = C.b_ref "
                    + "JOIN room AS D ON C.r_no = D.r_no "
                    + "WHERE A.b_ref='" + bookRef + "';";

            resultSet = statement.executeQuery(numRooms);

            no_rooms = 0;
            while (resultSet.next()) {
                no_rooms = resultSet.getInt(1);
                request.setAttribute("no_rooms", no_rooms);
            }

            // get room numbers linked to booking
            String getRooms = "(SELECT C.r_no FROM booking AS A JOIN customer As B "
                    + "ON A.c_no = B.c_no JOIN roombooking AS C ON A.b_ref = C.b_ref "
                    + "JOIN room AS D ON C.r_no = D.r_no WHERE A.b_ref = '" + bookRef + "')";

            resultSet = statement.executeQuery(getRooms);

            r_nos = new ArrayList();
            while (resultSet.next()) {
                r_nos.add(resultSet.getString("r_no"));
            }
            
            request.setAttribute("r_nos", r_nos);
            request.getRequestDispatcher("CheckInOutGuests.jsp").forward(request, response);
            
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
