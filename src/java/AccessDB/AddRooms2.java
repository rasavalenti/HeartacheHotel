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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fvq13ndu
 *
 * This is a servlet that follows from the AddRooms.jsp and does a similar check
 * of which rooms are available for the specified dates as the one done in
 * CheckDates servlet. It leads to AddRoomsConfirmation.jsp
 */
public class AddRooms2 extends HttpServlet {

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

    static String user;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            //Connecting to database
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

            //Getting the parameters from AddRooms.jsp
            String roomtype = request.getParameter("add_r_roomtype");

            int numOfRooms = Integer.parseInt(request.getParameter("add_r_num"));

            statement.execute("set schema 'HeartacheHotelDB';");

            //Checking if the requested rooms can be booked for the specified dates
            String query = "select COUNT(*) from room r where r.r_no NOT IN (select rb.r_no "
                    + "from roombooking rb where checkin <= '" + AddRooms.checkout + "' and checkout >= '" + AddRooms.checkin + "' group by rb.r_no) "
                    + "and r_class='" + roomtype + "' group by r.r_class";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);

            int availableNumOfRooms = 0;
            while (resultSet.next()) {
                availableNumOfRooms = resultSet.getInt("COUNT");
            }

            //Information printed onto console which helps with testing/tracking 
            System.out.println("you wanted " + numOfRooms + " rooms");
            System.out.println("we have " + availableNumOfRooms + " rooms");

            RequestDispatcher rd;

            //A loop which in case there are enough of the requested rooms it goes on and udates the required tables
            //in case there are not enough rooms it displays a message with the rooms that we still have available for the specified dates
            if (availableNumOfRooms - numOfRooms >= 0) {
                int r_no = 0;
                for (int i = numOfRooms; i > 0; i--) {
                    //Getting the room number that can later be used to book by retrieving the smallest room number available 
                    //with the specifications made by the customer/reception
                    String availableRoom = "select MIN(r.r_no) from room r where r.r_no "
                            + "NOT IN (select rb.r_no from roombooking rb where checkin "
                            + "<= '" + AddRooms.checkout + "' and checkout >= '" + AddRooms.checkin + "' group by rb.r_no) "
                            + "and r_class='" + roomtype + "';";
                    resultSet = statement.executeQuery(availableRoom);

                    while (resultSet.next()) {
                        r_no = resultSet.getInt("min");
                        out.println("The room you are booking is: " + r_no);
                    }
                    System.out.println("The r_no is: " + r_no);

                    //Updating the roombooking table
                    String roomBooking = "insert into roombooking values (" + r_no + ", " + AddRooms.b_ref + ", '" + AddRooms.checkin + "', '" + AddRooms.checkout + "');";
                    System.out.println(roomBooking);
                    statement.execute(roomBooking);
                }
                //Getting the total
                String pricePerNight = "select price from rates where r_class='" + roomtype + "';";
                resultSet = statement.executeQuery(pricePerNight);
                double price = 0;
                while (resultSet.next()) {
                    price = resultSet.getDouble("price");
                    out.println("The price per night is: " + price);
                }
                System.out.println("The price per night is: " + price);

                //Getting the number of days that the customer is staying
                String numberOfDaysStayed = "SELECT(SELECT checkout from roombooking "
                        + "where b_ref='" + AddRooms.b_ref + "' and r_no='" + r_no + "') - (SELECT checkin from "
                        + "roombooking where b_ref='" + AddRooms.b_ref + "' and r_no='" + r_no + "') as stay_days;";
                resultSet = statement.executeQuery(numberOfDaysStayed);
                int daysStay = 0;
                while (resultSet.next()) {
                    daysStay = resultSet.getInt("stay_days");
                    out.println("The number of days you are staying is: " + daysStay);
                }
                System.out.println("The number of days you are staying is: " + daysStay);

                //getting the total cost for the added room
                double b_cost_to_add = price * daysStay * numOfRooms;

                //adding the cost of the added room to the total booking cost
                double total_b_cost = AddRooms.b_cost + b_cost_to_add;

                String updateBooking = "update booking set b_cost=" + total_b_cost + ", b_outstanding=" + total_b_cost + " where b_ref=" + AddRooms.b_ref + ";";
                System.out.println(updateBooking);
                statement.execute(updateBooking);
                out.println("The total b_cost is: " + total_b_cost);

                System.out.println("b_cost is: " + total_b_cost);

                String SQLStatement = "select rb.r_no from booking b, roombooking rb where b.b_ref=rb.b_ref and b.b_ref=" + AddRooms.b_ref + ";";

                resultSet = statement.executeQuery(SQLStatement);

                //The arraylist for the room numbes after the new room/rooms were added
                ArrayList<String> new_r_nos_array = new ArrayList();
                while (resultSet.next()) {
                    new_r_nos_array.add(resultSet.getString("r_no"));
                }
                System.out.println(new_r_nos_array);

                String new_r_nos = new_r_nos_array.toString();
                new_r_nos = new_r_nos.substring(1, new_r_nos.length());
                new_r_nos = new_r_nos.substring(0, new_r_nos.length() - 1);

                //Setting the attributes which will then be retrieved in AddRoomsConfirmation.jsp
                request.setAttribute("previous_r_nos", AddRooms.r_nos);
                request.setAttribute("new_r_nos", new_r_nos);

                request.setAttribute("previous_b_cost", AddRooms.b_cost);
                request.setAttribute("total_b_cost", total_b_cost);

                //This is the exit link which either foes pack to the ReceptionPortal.html
                //it the user is reception and toes to Home.html if the user is a customer
                //These values were specified in ReceptionManageBooking or ManageBooking servlets
                //depending on who is using the servlet
                String exit_link;
                if (user.equals("reception")) {
                    exit_link = "\"javascript:location.href = 'ReceptionPortal.html'\"";
                    request.setAttribute("exit_link", exit_link);
                }

                if (user.equals("customer")) {
                    exit_link = "\"javascript:location.href = 'Home.html'\"";
                    request.setAttribute("exit_link", exit_link);
                }

                rd = request.getRequestDispatcher("AddRoomsConfirmation.jsp");
                rd.forward(request, response);

            } else {
                //This is the error mesage that's printed out in case there aren't enough rooms, this is very similar to CheckDates servlet
                //it displays the number of rooms available for each individual type for the spacified dates.

                out.println("<html>\n"
                        + "    <head>\n"
                        + "        <title>Try Again</title>\n"
                        + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"HHcss.css\">\n"
                        + "\n"
                        + "    </head>");
                out.println("<body>");
                out.println("<div class=\"Main\">");
                out.println("<h1>Please try again:</h1>");
                out.println("Sorry, we do not have enough rooms for the specified date.<br />");
                out.println("The total number of rooms available for the specified date and room type is: " + availableNumOfRooms + " .<br />");
                out.println("The rooms still available for your chosen dates are: <br />");
                resultSet = statement.executeQuery("select COUNT(*) from room r where r.r_no NOT IN (select rb.r_no "
                        + "from roombooking rb where checkin <= '" + AddRooms.checkout + "' and checkout >= '" + AddRooms.checkin + "' group by rb.r_no) "
                        + "and r_class='std_d' group by r.r_class");
                while (resultSet.next()) {
                    availableNumOfRooms = resultSet.getInt("COUNT");

                    out.println(availableNumOfRooms + " Standard Double rooms, <br />");
                }

                resultSet = statement.executeQuery("select COUNT(*) from room r where r.r_no NOT IN (select rb.r_no "
                        + "from roombooking rb where checkin <= '" + AddRooms.checkout + "' and checkout >= '" + AddRooms.checkin + "' group by rb.r_no) "
                        + "and r_class='std_t' group by r.r_class");
                while (resultSet.next()) {
                    availableNumOfRooms = resultSet.getInt("COUNT");
                    out.println(availableNumOfRooms + " Standard Twin rooms, <br />");
                }

                resultSet = statement.executeQuery("select COUNT(*) from room r where r.r_no NOT IN (select rb.r_no "
                        + "from roombooking rb where checkin <= '" + AddRooms.checkout + "' and checkout >= '" + AddRooms.checkin + "' group by rb.r_no) "
                        + "and r_class='sup_d' group by r.r_class");
                while (resultSet.next()) {
                    availableNumOfRooms = resultSet.getInt("COUNT");
                    out.println(availableNumOfRooms + " Premium Double rooms, <br />");
                }

                resultSet = statement.executeQuery("select COUNT(*) from room r where r.r_no NOT IN (select rb.r_no "
                        + "from roombooking rb where checkin <= '" + AddRooms.checkout + "' and checkout >= '" + AddRooms.checkin + "' group by rb.r_no) "
                        + "and r_class='sup_t' group by r.r_class");
                while (resultSet.next()) {
                    availableNumOfRooms = resultSet.getInt("COUNT");
                    out.println(availableNumOfRooms + " Premium Twin rooms. <br />");
                }
                //Again, the exit buttons depend on who is using the servlet
                System.out.println("We don't have anough rooms for the specified date");
                if (user.equals("reception")) {
                    out.println("<input type=button name=\"Exit_CancelRooms\" value=\"Exit\" onclick=\"javascript:location.href = 'ReceptionPortal.html'\">");
                }

                System.out.println("We don't have anough rooms for the specified date");
                if (user.equals("customer")) {
                    out.println("Please try booking again. If you need assistance, please contact us <a href=\"ContactUs.html\">here</a>.<br/><br/>");
                    out.println("<input type=button name=\"Exit_CancelRooms\" value=\"Exit\" onclick=\"javascript:location.href = 'Booking.html'\">");
                }

                out.println("</div>");
                out.println("</body>");
            }

            connection.close();

//            statement.execute(sqlstatement);
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
            Logger.getLogger(CheckDates.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CheckDates.class.getName()).log(Level.SEVERE, null, ex);
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
