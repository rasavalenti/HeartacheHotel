<%-- 
    Document   : BookingConfirmation
    Created on : 07-Dec-2017, 15:40:29
    Author     : fvq13ndu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <script src ="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script type ="text/javascript"></script>
        <script src ="HHjavascript.js"></script>
        <title>Booking Confirmation</title>
    </head>
    <body>
        <div class="Main">
            <h1> Booking Confirmation </h1>
            <table id="formDetails">
                
                <tr><td colspan="2"><strong>Personal details</strong></td></tr>
                <tr><td>Your customer number is:</td><td>${c_no}</td></tr>
                <tr><td>Forename:</td><td>${forename}</td></tr>
                <tr><td>Surname:</td><td>${surname}</td></tr>
                <tr><td>Email:</td><td>${email}</td></tr>  
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>

                <tr><td colspan="2"><strong>Payment information</strong></td></tr>
                <tr><td>Card number:</label></td><td>${secretcardnumber}</td></tr>
                <tr><td>Address:</td><td>${addressline}</td></tr>
                <tr><td>City:</td><td>${city}</td></tr>
                <tr><td>Post Code:</td><td>${postcode}</td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>

                <tr><td colspan="2"><strong>Booking information</strong></td></tr>
                <tr><td>Your booking reference is:</td><td>${b_ref}</td></tr>
                <tr><td>Check-in:</label></td><td>${checkin}</td></tr>
                <tr><td>Check-out:</td><td>${checkout}</td></tr>
                <tr><td>Room type:</td><td>${roomtype}</td></tr>
                <tr><td>Number of Rooms:</td><td>${numofrooms}</td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>

            </table>
            <br/><br/>
            Note: Please keep note of your customer number and booking reference, you will require them to edit your booking.
            <br/><br/>
            You can print this page, or save it as PDF for reference:
            <br/><br/>
            <form>
                <input type=button name=saveAsPdf value="Print Or Save as PDF" onClick="window.print()">
            </form>
            
        </div
    </body>
</html>
