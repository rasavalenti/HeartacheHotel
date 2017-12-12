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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * @author qhf13exu
 */
public class RoomReport extends HttpServlet {

    ArrayList<String> startRooms;
    int startRoomsNum;
    ArrayList<String> checkinRooms;
    int checkinRoomsNum;
    ArrayList<String> checkoutRooms;
    int checkoutRoomsNum;
    ArrayList<String> endRooms;
    int endRoomsNum;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            System.out.println("in roomreport.java");            


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

            System.out.println("before sdf");
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

            String temp = request.getParameter("roomTime");
            System.out.println("temp: "+temp);
            int timePeriod = Integer.parseInt(temp);
            System.out.println("timePeriod: "+timePeriod);
            
            Date dateFrom = sdf.parse(request.getParameter("roomReportDate"));

            System.out.println("time period: " + timePeriod);
            System.out.println("dateFrom: " + sdf.format(dateFrom));
            
            
            long add = dateFrom.getTime() + timePeriod * 24 * 60 * 60 * 1000;
            Date dateTo = new Date(add);

            // funcoinality for weekly, monhly, yearly reports
//            if (timePeriod==6) {
//                long add = dateFrom.getTime() + timePeriod * 24 * 60 * 60 * 1000;
//                dateTo = new Date(add);
//            } else if (timePeriod==30) {
//                dateTo.setMonth(dateFrom.getMonth()+1);
//            } else {
//                dateTo.setYear(dateFrom.getYear()+1);
//            }
            System.out.println("time period: " + timePeriod);
            System.out.println("dateFrom: " + sdf.format(dateFrom));
            System.out.println("dateTo: " + sdf.format(dateTo));

            // 1. Rooms at the start of the week
            String sql = "SELECT * FROM roombooking WHERE checkin < '" + sdf.format(dateFrom) + "' AND checkout >= '" + sdf.format(dateFrom) + "' ORDER BY r_no;";
            System.out.println(sql);

            ResultSet resultSet;
            resultSet = statement.executeQuery(sql);

            startRooms = new ArrayList();
            while (resultSet.next()) {
                startRooms.add(resultSet.getString("r_no"));
            }
            System.out.println(startRooms);

            startRoomsNum = startRooms.size();
            System.out.println(startRoomsNum);

            // 2. Rooms checking-in during week
            sql = "SELECT * FROM roombooking WHERE checkin >= '" + sdf.format(dateFrom) + "' AND checkin <= '" + sdf.format(dateTo) + "' ORDER BY r_no;";
            System.out.println(sql);

            resultSet = statement.executeQuery(sql);

            checkinRooms = new ArrayList();
            while (resultSet.next()) {
                checkinRooms.add(resultSet.getString("r_no"));
            }
            System.out.println(checkinRooms);

            checkinRoomsNum = checkinRooms.size();
            System.out.println(checkinRoomsNum);

            // 3. Rooms checking-out during week
            sql = "SELECT * FROM roombooking WHERE checkout <= '" + sdf.format(dateTo) + "' and checkout >= '" + sdf.format(dateFrom) + "' ORDER BY r_no;";
            System.out.println(sql);

            resultSet = statement.executeQuery(sql);

            checkoutRooms = new ArrayList();
            while (resultSet.next()) {
                checkoutRooms.add(resultSet.getString("r_no"));
            }
            System.out.println(checkoutRooms);

            checkoutRoomsNum = checkoutRooms.size();
            System.out.println(checkoutRoomsNum);

            // 4. Rooms left occupied at the end of the week
            sql = "(SELECT * FROM roombooking WHERE checkin < '" + sdf.format(dateFrom) + "' AND checkout >= '" + sdf.format(dateFrom) + "'\n"
                    + "UNION\n"
                    + "select * from roombooking where checkin >= '" + sdf.format(dateFrom) + "' and checkin <= '" + sdf.format(dateTo) + "')\n"
                    + "EXCEPT\n"
                    + "select * from roombooking where checkout <= '" + sdf.format(dateTo) + "' and checkout >= '" + sdf.format(dateFrom) + "'\n"
                    + "ORDER BY r_no;";
            System.out.println(sql);

            resultSet = statement.executeQuery(sql);

            endRooms = new ArrayList();
            while (resultSet.next()) {
                endRooms.add(resultSet.getString("r_no"));
            }
            System.out.println(endRooms);

            endRoomsNum = endRooms.size();
            System.out.println(endRoomsNum);

            request.setAttribute("dateFrom", sdf.format(dateFrom));
            request.setAttribute("dateTo", sdf.format(dateTo));
            request.setAttribute("startRooms", startRooms);
            request.setAttribute("startRoomsNum", startRoomsNum);
            request.setAttribute("checkinRooms", checkinRooms);
            request.setAttribute("checkinRoomsNum", checkinRoomsNum);
            request.setAttribute("checkoutRooms", checkoutRooms);
            request.setAttribute("checkoutRoomsNum", checkoutRoomsNum);
            request.setAttribute("endRooms", endRooms);
            request.setAttribute("endRoomsNum", endRoomsNum);

            request.getRequestDispatcher("ReceptionRoomReport.jsp").forward(request, response);

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
