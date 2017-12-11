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
            <h1> Cancel an Existing Booking </h1>
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
                
                <form method="POST" name="cancelBookingForm" action="CancelBooking2">
                    <br/>
                    <input type="submit" value="Cancel Booking"> 
                    <input type=button name="Exit_CancelRooms" value="Exit" onclick="javascript:location.href = 'Booking.html'">
                </form>
            
        </div>
    </body>
</html>
