<%-- 
    Document   : BookingConfirmation
    Created on : 09-Dec-2017, 20:12:34
    Author     : qhf13exu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <script src ="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script type ="text/javascript"></script>
        <script src ="HHjavascript.js"></script>
        <title>PayCheckOut</title>
    </head>
    <body>
        <div class="Main">
            <h1>Pay and check out</h1>
            <table id="formDetails">
                
                <tr>
                    <td colspan="2"><strong>Personal details</strong></td>
                </tr>
                <tr>
                    <td>Customer number is:</td>
                    <td>Bob${c_no}</td>
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
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>

                <tr>
                    <td colspan="2"><strong>Payment information</strong></td>
                </tr>
                <tr>
                    <td>Card number:</label></td>
                    <td>${c_cardno}</td>
                </tr>
                <tr>
                    <td>Card type:</label></td>
                    <td>${c_cardtype}</td>
                </tr>
                <tr>
                    <td>Card expiration:</label></td>
                    <td>${c_cardexp}</td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td>${c_address}</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
                <form method="POST" name="PayCheckOut" action="PayAndCheckOut">
                    <input type="submit" value="Pay and Check out">
                </form>
            
        </div>
    </body>
</html>
