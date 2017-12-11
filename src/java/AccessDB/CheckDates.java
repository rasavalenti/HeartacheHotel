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
 */
public class CheckDates extends HttpServlet {

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
    static java.sql.Date checkin;
    static java.sql.Date checkout;
    static String roomtype;
    static int numOfRooms;

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

            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("checkin"));
            checkin = new java.sql.Date(date.getTime());
            System.out.println(checkin);

            Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("checkout"));
            checkout = new java.sql.Date(date2.getTime());
            System.out.println(checkout);

            roomtype = request.getParameter("roomtype");

            numOfRooms = Integer.parseInt(request.getParameter("numOfRooms"));

            statement.execute("set schema 'HeartacheHotelDB';");

            //Even though this might seem confusing hte checkout and checkin have to stay in this way:
            //...checkin <= '"+checkout+"' and checkout >= '"+checkin+"'...
            String query = "select COUNT(*) from room r where r.r_no NOT IN (select rb.r_no "
                    + "from roombooking rb where checkin <= '" + checkout + "' and checkout >= '" + checkin + "' group by rb.r_no) "
                    + "and r_class='" + roomtype + "' group by r.r_class";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);

//            response.sendRedirect("BookingForm.html");
            int availableNumOfRooms = 0;
            while (resultSet.next()) {
                availableNumOfRooms = resultSet.getInt("COUNT");
            }

            System.out.println("you wanted " + numOfRooms + " rooms");
            System.out.println("we have " + availableNumOfRooms + " rooms");

            RequestDispatcher rd;

            if (availableNumOfRooms - numOfRooms >= 0) {
                rd = request.getRequestDispatcher("BookingForm.html");
                rd.forward(request, response);
                System.out.println("We have enough rooms");
            } else {
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
                        + "from roombooking rb where checkin <= '" + checkout + "' and checkout >= '" + checkin + "' group by rb.r_no) "
                        + "and r_class='std_d' group by r.r_class");
                while (resultSet.next()) {
                    availableNumOfRooms = resultSet.getInt("COUNT");

                    out.println(availableNumOfRooms + " Standard Double rooms, <br />");
                }

                resultSet = statement.executeQuery("select COUNT(*) from room r where r.r_no NOT IN (select rb.r_no "
                        + "from roombooking rb where checkin <= '" + checkout + "' and checkout >= '" + checkin + "' group by rb.r_no) "
                        + "and r_class='std_t' group by r.r_class");
                while (resultSet.next()) {
                    availableNumOfRooms = resultSet.getInt("COUNT");
                    out.println(availableNumOfRooms + " Standard Twin rooms, <br />");
                }

                resultSet = statement.executeQuery("select COUNT(*) from room r where r.r_no NOT IN (select rb.r_no "
                        + "from roombooking rb where checkin <= '" + checkout + "' and checkout >= '" + checkin + "' group by rb.r_no) "
                        + "and r_class='sup_d' group by r.r_class");
                while (resultSet.next()) {
                    availableNumOfRooms = resultSet.getInt("COUNT");
                    out.println(availableNumOfRooms + " Premium Double rooms, <br />");
                }

                resultSet = statement.executeQuery("select COUNT(*) from room r where r.r_no NOT IN (select rb.r_no "
                        + "from roombooking rb where checkin <= '" + checkout + "' and checkout >= '" + checkin + "' group by rb.r_no) "
                        + "and r_class='sup_t' group by r.r_class");
                while (resultSet.next()) {
                    availableNumOfRooms = resultSet.getInt("COUNT");
                    out.println(availableNumOfRooms + " Premium Twin rooms. <br />");
                }
                out.println("Please try booking again. If you need assistance, please contact us <a href=\"ContactUs.html\">here</a>.<br/><br/>");

                System.out.println("We don't have anough rooms for the specified date");
                out.println("<input type=button name=\"Exit_CancelRooms\" value=\"Exit\" onclick=\"javascript:location.href = 'Booking.html'\">");
                out.println("</div>");
                out.println("</body>");
            }

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
