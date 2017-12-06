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
public class AccessServlet extends HttpServlet {

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

//The following lines are here to check the connection between sql and netbeans
//            insertSQL = "insert into customer values (123470, 'Ann Hinchcliffe14', 'Ann.Hinchcliffe@yahoo.com', '81 New Road, Acle NR13 7GH', 'V', '10/16', '8948106927123585');";
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
//                statement.executeUpdate(insertSQL);
            statement.execute("set schema 'HeartacheHotelDB';");
            
            String maxCno = "select MAX(c_no) as maxcno from customer;";
            ResultSet resultSet = statement.executeQuery(maxCno);
            int c_no=0;
            while (resultSet.next()) {
                c_no = resultSet.getInt("maxcno");
                c_no = c_no + 1;
                out.println("The c_no is: " + c_no);
            }
            
            String forename = request.getParameter("forename");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String addressline = request.getParameter("addressline");
            String city = request.getParameter("city");
            String postcode = request.getParameter("postcode");
            String card = request.getParameter("usercard");
            String month = request.getParameter("month");
            String year = request.getParameter("year");
            String cardnumber = request.getParameter("cardnumber");
            String bookingNotes = request.getParameter("bookingNotes");

            String sqlstatement = "insert into customer values ("+c_no+", '"+forename+" "+surname+"',"+
            " '"+email+"', '"+addressline+", "+city+" "+postcode+"',"+
            " '"+card+"', '"+month+"/"+year+"', '"+cardnumber+"');";
            System.out.println(sqlstatement);
            
            java.sql.Date checkin = CheckDates.checkin;

            java.sql.Date checkout = CheckDates.checkout;

            String roomtype = CheckDates.roomtype;

            int numOfRooms = CheckDates.numOfRooms;
            
//            int numberOfDays = Days.daysBetween(checkin, checkout).getDays();
//            System.out.println(numberOfDays);
            
            if (checkin == null & checkout == null & roomtype == null & numOfRooms == 0) {
            out.println("We don't have enough rooms for the specified dates.");
            }
            
            System.out.println("From AccessServlet: "+checkin+" "+checkout+" "+roomtype+" "+numOfRooms);

            statement.execute(sqlstatement);
            
            String maxBRef = "select MAX(b_ref) as maxbref from booking;";
            resultSet = statement.executeQuery(maxBRef);
            int b_ref=0;
            while (resultSet.next()) {
                b_ref = resultSet.getInt("maxbref");
                b_ref = b_ref + 1;
                out.println("The b_ref is: " + b_ref);
            }
            
            System.out.println("The booking reference is: " + b_ref);
            
            String pricePerNight = "select price from rates where r_class='"+roomtype+"';";
            resultSet = statement.executeQuery(pricePerNight);
            double b_cost = 0;
            while (resultSet.next()) {
                b_cost = resultSet.getDouble("price");
                out.println("The b_cost per night is: " + b_cost);
            }
            System.out.println("The b_cost per night is: " + b_cost);
            
            
            String availableRoom = "select MIN(r.r_no) from room r where r.r_no "
                    + "NOT IN (select rb.r_no from roombooking rb where checkin "
                    + "<= '"+checkout+"' and checkout >= '"+checkin+"' group by rb.r_no) "
                    + "and r_class='"+roomtype+"';";
            resultSet = statement.executeQuery(availableRoom);
            int r_no = 0;
            while (resultSet.next()) {
                r_no = resultSet.getInt("min");
                out.println("The room you are booking is: " + r_no);
            }
            System.out.println("The r_no is: " + r_no);
            
            String Booking = "insert into booking values ("+b_ref+", "+c_no+", 0, 0, '');";
            
            statement.execute(Booking);
            
            System.out.println(Booking);
            
            String roomBooking = "insert into roombooking values ("+r_no+", "+b_ref+", '"+checkin+"', '"+checkout+"');";
            
            statement.execute(roomBooking);
            

            
            
            
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
