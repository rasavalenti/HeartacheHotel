<%-- 
    Document   : AddRoomsConfirmation
    Created on : 10-Dec-2017, 17:24:28
    Author     : fvq13ndu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Booking Form</title>
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <script src ="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script type ="text/javascript"></script>
        <script src ="HHjavascript.js"></script>

    </head>
    <body>
        <div class="Main">       
            <h1> Changes after adding rooms </h1>
            <table id="AddRooms_conf">
                
                <tr><td>&nbsp;</td><td><strong>Before:</strong></td><td><strong>Now:</strong></td></tr>
                <tr><td>Booking cost:</td><td>${previous_b_cost}</td><td>${total_b_cost}</td></tr>
                <tr><td>Outstanding cost:</td><td>${previous_b_cost}</td><td>${total_b_cost}</td></tr>
                <tr><td>Room numbers:</td><td>${previous_r_nos}</td><td>${new_r_nos}</td></tr> 

            </table>
                </br></br>
             <form>
                <input type=button name="Exit_AddRooms" value="Exit" onclick="javascript:location.href = 'Booking.html'">
            </form>
        </div>        
    </body>
</html>
