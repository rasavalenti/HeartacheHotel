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
 * 
 * This is a servlet which gets the information from the BookingForm.jsp and updates
 * 'customer', 'booking' and 'roombooking' tables. Then, sends the information to 
 * BookingConfirmation.jsp
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
            
            //Setting the schema
            statement.execute("set schema 'HeartacheHotelDB';");
            
            
            //Creating a c_no for the new customer (by adding 1 to the biggest c_no in the 'customer' table)
            String maxCno = "select MAX(c_no) as maxcno from customer;";
            ResultSet resultSet = statement.executeQuery(maxCno);
            int c_no = 0;
            while (resultSet.next()) {
                c_no = resultSet.getInt("maxcno");
                c_no = c_no + 1;
                out.println("The c_no is: " + c_no);
            }
            
            //Getting parameters from BookingForm.jsp
            
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
            String b_notes = request.getParameter("bookingNotes");

            String sqlstatement = "insert into customer values (" + c_no + ", '" + forename + " " + surname + "',"
                    + " '" + email + "', '" + addressline + ", " + city + " " + postcode + "',"
                    + " '" + card + "', '" + month + "/" + year + "', '" + cardnumber + "');";
            
            //Printing out onto console to see is the statement used is of correct form
            System.out.println(sqlstatement);
            
            //Getting parameters which were inputed in Booking.html and previously used in CheckDates servlet
            //by retrieving them from the CheckDates.java
            
            java.sql.Date checkin = CheckDates.checkin;

            java.sql.Date checkout = CheckDates.checkout;

            String roomtype = CheckDates.roomtype;

            int numOfRooms = CheckDates.numOfRooms;

            //Printing onto console to keep track/test the servlet
            System.out.println("From AccessServlet: " + checkin + " " + checkout + " " + roomtype + " " + numOfRooms);

            //Executing the sql statement that inserts a new customer into the 'customer' table
            statement.execute(sqlstatement);
            
            //Creating a b_ref for the new booking in the same way as the c_no was created before
            String maxBRef = "select MAX(b_ref) as maxbref from booking;";
            resultSet = statement.executeQuery(maxBRef);
            int b_ref = 0;
            while (resultSet.next()) {
                b_ref = resultSet.getInt("maxbref");
                b_ref = b_ref + 1;
                out.println("The b_ref is: " + b_ref);
            }

            System.out.println("The booking reference is: " + b_ref);
            
            //Getting the price for the requested room type
            String pricePerNight = "select price from rates where r_class='" + roomtype + "';";
            resultSet = statement.executeQuery(pricePerNight);
            double price = 0;
            while (resultSet.next()) {
                price = resultSet.getDouble("price");
                out.println("The price per night is: " + price);
            }
            System.out.println("The price per night is: " + price);
            
            //Inserting values into 'booking' table
            String Booking = "insert into booking values (" + b_ref + ", " + c_no + ", 0, 0, '');";

            statement.execute(Booking);

            System.out.println(Booking);
            
            //Loop for inserting values into the roombooking table
            
            int r_no = 0;
            
            for (int i = numOfRooms; i > 0; i--) {
                //Getting the r_no's from the available rooms for the specified dates
                String availableRoom = "select MIN(r.r_no) from room r where r.r_no "
                        + "NOT IN (select rb.r_no from roombooking rb where checkin "
                        + "<= '" + checkout + "' and checkout >= '" + checkin + "' group by rb.r_no) "
                        + "and r_class='" + roomtype + "';";
                resultSet = statement.executeQuery(availableRoom);

                while (resultSet.next()) {
                    r_no = resultSet.getInt("min");
                    out.println("The room you are booking is: " + r_no);
                }
                System.out.println("The r_no is: " + r_no);
                
                //inserting values into 'roombooking' table using the available r_no
                String roomBooking = "insert into roombooking values (" + r_no + ", " + b_ref + ", '" + checkin + "', '" + checkout + "');";

                statement.execute(roomBooking);
            }
            
            //updating the 'booking' table value which we added previously by adding the booking cost, booking noted and the booking reference
            //It has to be done in this way because of the limitations in the SQL
            String updateBooking = "update booking set b_cost=" + CheckDates.b_cost + ", b_outstanding=" + CheckDates.b_cost + ", b_notes='" + b_notes + "' where b_ref=" + b_ref + ";";

            statement.execute(updateBooking);
            out.println("The total b_cost is: " + CheckDates.b_cost);
            System.out.println("b_cost is: " + CheckDates.b_cost);
            
            //Setting the attributes which will be then used in the BookingConfirmation.jsp
            request.setAttribute("b_cost", CheckDates.b_cost);
            request.setAttribute("c_no", c_no);
            request.setAttribute("forename", forename);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);

            if (cardnumber.length() == 16) {
                String secretcardnumber = "**** **** **** " + cardnumber.substring(11, 15);
                request.setAttribute("secretcardnumber", secretcardnumber);
            }
            request.setAttribute("addressline", addressline);

            request.setAttribute("city", city);
            request.setAttribute("postcode", postcode);
            request.setAttribute("b_ref", b_ref);
            request.setAttribute("checkin", checkin);
            request.setAttribute("checkout", checkout);
            request.setAttribute("roomtype", CheckDates.roomtypename);
            request.setAttribute("numofrooms", numOfRooms);
            
            //Sending the variables to the BookingConfirmation.jsp 
            request.getRequestDispatcher("BookingConfirmation.jsp").forward(request, response);
            
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
