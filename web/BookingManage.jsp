<%-- 
    Document   : BookingManage
    Created on : 08-Dec-2017, 14:10:05
    Author     : fvq13ndu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type ="text/javascript"></script>
        <script src ="HHjavascript.js"></script>
        <title>Manage Booking</title>
    </head>
    <body>
        <div class="Main">
            <h1> Manage Booking </h1>
            <table>

                <tr><td colspan="2"><strong>Personal details</strong></td></tr>
                <tr><td>Customer number:</td><td>${manage_c_no}</td></tr>
                <tr><td>Name:</td><td>${manage_c_name}</td></tr>
                <tr><td>Email:</td><td>${manage_c_email}</td></tr>  
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>

                <tr><td colspan="2"><strong>Payment information</strong></td></tr>
                <tr><td>Card number:</label></td><td>${manage_c_cardno}</td></tr>
                <tr><td>Billing address:</td><td>${manage_c_address}</td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>

                <tr><td colspan="2"><strong>Bookings</strong></td></tr>
                <tr><td>Number of bookings:</label></td><td>${numOfBRefs}</td></tr>
                <tr><td>Booking references:</label></td><td>${bookingReferences}</td></tr>

            </table>
            <br/>
            <button onclick="switchToAddRooms()">Add more rooms</button> 
            <button onclick="switchToEditDetails()">Edit personal details</button>
            <button onclick="switchToCancelBooking()">Cancel booking</button> 
            <br/><br/>

            <form id="m_manageBooking" class="tabcontent2">
                <fieldset>
                    <legend>Add rooms to an existing booking:</legend>
                    <label>Enter booking reference: </label><input type="text" name="m_b_ref" id="m_b_ref">
                    
                </fieldset>
            </form> 

            <form id="m_cancelBooking" class="tabcontent2">
                <fieldset>
                    <legend>Cancel an existing booking:</legend>
                    <label>Enter booking reference: </label><input type="text" name="c_b_ref" id="c_b_ref">
                </fieldset>
            </form>  

            <form id="m_editDetails" class="tabcontent2">
                <fieldset>
                    <legend>Edit personal details:</legend>
                    <label>New email: </label><input type="text" name="c_email" id="c_email">
                     
                </fieldset>
            </form>  
        </div>
    </body>
</html>
