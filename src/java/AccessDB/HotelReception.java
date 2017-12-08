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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        System.out.println("hello");
        
        try {
            System.out.println("in try");
            
            String insertSQL;

//            // The following lines are here to check the connection between sql and netbeans
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

            System.out.println("atfer connection");

            String bookRef = request.getParameter("bookingRefInput");

            System.out.println("Booking ref is: " + bookRef);

            String getCustomer = "SELECT A.b_ref, A.c_no, c_name, c_email, "
                    + "c_address, c_cardtype, c_cardexp, c_cardno, b_outstanding, "
                    + "checkin, checkout FROM booking AS A JOIN customer As B "
                    + "ON A.c_no = B.c_no JOIN	roombooking AS C ON A.b_ref = C.b_ref "
                    + "WHERE A.b_ref='" + bookRef + "';";

            ResultSet resultSet = statement.executeQuery(getCustomer);

            // Printing the actor names whilst the resultSet is not empty
            while (resultSet.next()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                
                out.println("<title>Customer Check In / Out</title>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"HHcss.css\">");
                out.println("<script src =\"http://code.jquery.com/jquery-1.9.1.js\"></script>");
                out.println("<script type =\"text/javascript\"></script>");
                out.println("<script src =\"HHjavascript.js\"></script>");
                out.println("<style>");
                out.println(".monkey {");
                out.println("background: red;");
                out.println("}");
                out.println("</style>");                
                
                out.println("</head>");
                
                out.println("<body>");
                
                out.println("<div class=\"monkey\">");
                
                out.println("Booking reference: " + bookRef
                        + "<br>Name: " + resultSet.getString("c_name")
                        + "<br>Email: " + resultSet.getString("c_email")
                        + "<br>Address: " + resultSet.getString("c_address")
                        + "<br>Check-In: " + resultSet.getString("checkin")
                        + "<br>Check-Out: " + resultSet.getString("checkout"));
                
                out.println("</div");
                
                out.println("</body>");
                
                out.println("</html>");
                
                
                
            }
        } 
        catch (ClassNotFoundException | SQLException e) {
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
