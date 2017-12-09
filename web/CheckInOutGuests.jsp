<%-- 
    Document   : CheckInOutGuests
    Created on : 08-Dec-2017, 19:54:46
    Author     : qhf13exu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <script src ="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script type ="text/javascript"></script>
        <script src ="HHjavascript.js"></script>
        <title>Check In / Out Guests</title>
    </head>
    <body>
        <div class="Main">    
            <h1>Guest Booking Information</h1>
            <table id="guestDetails">
                <tr>
                    <td>Booking reference:</td>
                    <td>${bookRef}</td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td>${c_name}</td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>${c_email}</td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td>${c_address}</td>
                </tr>
                <tr>
                    <td>Check-In:</td>
                    <td>${checkin}</td>
                </tr>
                <tr>
                    <td>Check-Out:</td>
                    <td>${checkout}</td>
                </tr>
                <tr>
                    <td>Number of rooms:</td>
                    <td>${no_rooms}</td>
                </tr>
                <tr>
                    <td>Rooms:</td>
                    <td>${r_nos}</td>
                </tr>
                <tr>
                <form method="POST" name="changeRoomStatus" action="RoomStatus">
                    <td>
                        <select id="roomStatus" name="roomStatus">
                            <option value="X">Check-in</option>
                            <option value="C">Check-out</option>
                            <option value="A">Make available</option>
                        </select>
                    </td>
                    <td>
                        <input type="submit" value="Update">
                    </td>
                </form>
                </tr>
            </table>

        </div>
    </body>
</html>
