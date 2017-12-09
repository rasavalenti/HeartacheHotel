<%-- 
    Document   : AddExtraConfirm
    Created on : 09-Dec-2017, 23:26:48
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
        <title>Add extra confirmation</title>
    </head>
    <body>
        <div class="Main">
            <h1>Add Extra confirmation</h1>
            <table id="receipt">
                <tr>
                    <td>Booking reference:</td>
                    <td>${bookRef}</td>
                </tr>
                <tr>
                    <td>You did owe...</td>
                    <td>£${b_outstanding}</td>
                </tr>
                <tr>
                    <td>Now you owe...</td>
                    <td>£${total}</td>
                </tr>
                <tr>
                    <td>Total cost of extras:</td>
                    <td>£${cost}</td>
                </tr>
                <tr>
                    <td colspan="2"><a href="ReceptionPortal.html">Go back to Reception Portal</a></td>
                </tr>
            </table>
        </div>
    </body>
</html>
