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
 * @author qhf13exu
 */
public class AddExtras extends HttpServlet {

 
    // define static variables o be used in other servlets
    static int b_outstanding;
    static int b_cost;
    static int total;

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
            statement.execute("set schema 'HeartacheHotelDB';");

            String bookRef = request.getParameter("bookRef");
            request.setAttribute("bookRef", bookRef);
            
            // work out which extra items and how many have been selected
            String temp1, temp2;
            int choice1, choice2, choice3, choice4, 
                    amount1, amount2, amount3, amount4;
            
            temp1 = request.getParameter("choice1");
            temp2 = request.getParameter("amount1");
            choice1 = Integer.parseInt(temp1);
            amount1 = Integer.parseInt(temp2);
            
            temp1 = request.getParameter("choice2");
            temp2 = request.getParameter("amount2");
            choice2 = Integer.parseInt(temp1);
            amount2 = Integer.parseInt(temp2);
            
            temp1 = request.getParameter("choice3");
            temp2 = request.getParameter("amount3");
            choice3 = Integer.parseInt(temp1);
            amount3 = Integer.parseInt(temp2);
            
            temp1 = request.getParameter("choice4");
            temp2 = request.getParameter("amount4");
            choice4 = Integer.parseInt(temp1);
            amount4 = Integer.parseInt(temp2);
            
            // work out cost of extra items
            int cost = (choice1 * amount1) +
                    (choice2 * amount2) +
                    (choice3 * amount3) + 
                    (choice4 * amount4);
            
            request.setAttribute("cost", cost);
            
            // get outstanding balance linked ot booking reference
            String getOutstanding = "SELECT b_outstanding FROM booking AS A "
                    + "JOIN customer As B ON A.c_no = B.c_no "
                    + "JOIN roombooking AS C ON A.b_ref = C.b_ref "
                    + "JOIN room AS D ON C.r_no = D.r_no "
                    + "WHERE A.b_ref='" + bookRef + "';";
            
            ResultSet resultSet = statement.executeQuery(getOutstanding);
            
            while (resultSet.next()) {
                b_outstanding = resultSet.getInt("b_outstanding");
                request.setAttribute("b_outstanding", b_outstanding);
            }
            
            // work out new outstanding (ading cost of extras) and update b_outstanding lined to booking reference
            total = b_outstanding + cost;
            request.setAttribute("total", total);
            request.setAttribute("cost", cost);
                        
            String updateOutstanding = "UPDATE booking SET b_outstanding = "
                    + total+ "WHERE b_ref=" + bookRef + ";";
            statement.execute(updateOutstanding);
            
            connection.close();
            
            request.getRequestDispatcher("AddExtraConfirm.jsp").forward(request, response);
                    
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
