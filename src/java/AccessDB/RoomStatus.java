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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author qhf13exu
 */

public class RoomStatus extends HttpServlet {

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

            // The following lines are here to check the connection between sql and netbeans
         //String insertSQL;
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

            String status = request.getParameter("roomStatus");
            System.out.println(status);

            RequestDispatcher rd;

            System.out.println("in Roomstatus.java");
            
            
            if (status.equals("C")) {
                // if checking out get payment details fo booking reference
                String getDetails = "SELECT A.c_no, c_name, c_email, c_address, "
                        + "c_cardtype, c_cardexp, c_cardno FROM customer AS A "
                        + "JOIN booking AS B ON A.c_no=B.c_no WHERE b_ref ='"
                        + HotelReception.bookRef + "'";
              
                ResultSet resultSet;
                resultSet = statement.executeQuery(getDetails);

                String c_no, c_name, c_email, c_address, c_cardtype, c_cardexp, c_cardno;

                while (resultSet.next()) {
                    c_no = resultSet.getString("c_no");
                    c_name = resultSet.getString("c_name");
                    c_email = resultSet.getString("c_email");
                    c_address = resultSet.getString("c_address");
                    c_cardtype = resultSet.getString("c_cardtype");
                    c_cardexp = resultSet.getString("c_cardexp");
                    c_cardno = resultSet.getString("c_cardno");
                    
                    request.setAttribute("c_no", c_no);
                    request.setAttribute("c_name", c_name);
                    request.setAttribute("c_email", c_email);
                    request.setAttribute("c_address", c_address);
                    request.setAttribute("c_cardtype", c_cardtype);
                    request.setAttribute("c_cardexp", c_cardexp);
                    request.setAttribute("c_cardno", c_cardno);
                }
                
                
                String changeRoomStatus;
                for (String room : HotelReception.r_nos) {
                    changeRoomStatus = "UPDATE room SET r_status = '" + status + "' "
                            + "WHERE r_no = '" + room + "';";
                    System.out.println(changeRoomStatus);
                    statement.execute(changeRoomStatus);
                }
                
                request.setAttribute("bookRef", HotelReception.bookRef);
                request.setAttribute("r_nos", HotelReception.r_nos);
                
                
                
                rd = request.getRequestDispatcher("PayCheckOut.jsp");
                rd.forward(request, response);
            } else {
                
                // if checking in update status and send on to checkinsuccess jsp
                System.out.println("in else");
                String changeRoomStatus;
                for (String room : HotelReception.r_nos) {
                    changeRoomStatus = "UPDATE room SET r_status = '" + status + "' "
                            + "WHERE r_no = '" + room + "';";
                    System.out.println(changeRoomStatus);
                    statement.execute(changeRoomStatus);
                }
                
                request.setAttribute("bookRef", HotelReception.bookRef);
                request.setAttribute("r_nos", HotelReception.r_nos);
                
                rd = request.getRequestDispatcher("CheckInSuccess.jsp");
                rd.forward(request, response);
                
            }


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
