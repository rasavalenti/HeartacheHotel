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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author qhf13exu
 */
public class hkRoomStatus extends HttpServlet {

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
    static String r_num;
    static String r_status;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            //String insertSQL;

            // The following lines are here to check the connection between sql and netbeans
            //insertSQL = "insert into customer values (6523690, 'Ann Hinchcliffe14', 'Ann.Hinchcliffe@yahoo.com', '81 New Road, Acle NR13 7GH', 'V', '10/16', '8948106927123585');";
            //System.out.println(insertSQL);
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
            //statement.execute(insertSQL);

            statement.execute("set schema 'HeartacheHotelDB';");

            System.out.println("request:" + request.getParameter("roomStatus"));

            r_status = request.getParameter("roomStatus");
            r_num = request.getParameter("roomNumber");
            request.setAttribute("r_num", r_num);

            System.out.println("status: " + r_status);
            System.out.println("room: " + r_num);
            System.out.println("show rooms: " + ShowRooms.roomNums);

            if (r_status.equals("X")) {
                System.out.println("in x");
                request.setAttribute("r_status", "Unavailable");
            } else if (r_status.equals("A")) {
                System.out.println("in A");
                request.setAttribute("r_status", "Available");
            }
            
            if (ShowRooms.roomNums.contains(r_num)) {

                RequestDispatcher rd;

                String changeRoomStatus;

                changeRoomStatus = "UPDATE room SET r_status = '" + r_status + "' "
                        + "WHERE r_no = '" + r_num + "';";
                System.out.println(changeRoomStatus);
                statement.execute(changeRoomStatus);
                connection.close();

                request.getRequestDispatcher("HousekeepingShowRoomsConfirm.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("HousekeepingShowRoomsErrorNum.jsp").forward(request, response);
            }

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
