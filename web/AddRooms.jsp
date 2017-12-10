<%-- 
    Document   : AddRooms
    Created on : 10-Dec-2017, 13:39:39
    Author     : fvq13ndu
--%>

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
            <h1> Add Rooms to an Existing Booking </h1>
            <table id="formDetails">
                
                <tr><td colspan="2"><strong>Booking Details</strong></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>Booking reference:</td><td>${b_ref}</td></tr>
                <tr><td>Booking cost:</td><td>${b_cost}</td></tr>
                <tr><td>Outstanding cost:</td><td>${b_outstanding}</td></tr>
                <tr><td>Booking notes:</td><td>${b_notes}</td></tr> 
                <tr><td>Check-in:</td><td>${checkin}</td></tr> 
                <tr><td>Check-out:</td><td>${checkout}</td></tr> 
                <tr><td>Room numbers:</td><td>${r_nos}</td></tr> 
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                
            </table>
                
                <form method="POST" name="addRoomsForm" action="AddRooms2">
                        
                        <label> Number of rooms you would like to add: </label><input type="number" name="add_r_num" id="add_r_num">

                        <label> Room type: </label><select name="add_r_roomtype" id="add_r_roomtype">
                        <option value="std_d">Standard Double</option>
                        <option value="std_t">Standard Twin</option>
                        <option value="sup_d">Premium Double</option>
                        <option value="sup_t">Premium Twin</option>
                        </select>

               
                    <input class="FormInput" type="submit" value="Add Rooms">
                    
                </form>
            
        </div>
    </body>
</html>
