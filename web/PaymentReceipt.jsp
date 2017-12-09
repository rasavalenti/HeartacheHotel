<%-- 
    Document   : PaymentReceipt
    Created on : 09-Dec-2017, 21:11:26
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
        <title>Booking Confirmation</title>
    </head>
    <body>
        <div class="Main">
            <h1>Receipt</h1>
            <table id="receipt">
                
                <tr>
                    <td colspan="2">Thank you for staying with us</td>
                </tr>
                <tr>
                    <td>Paid:</td>
                    <td>${b_outstanding}</td>
                </tr>
                <tr>
                    <td colspan="2">For your rooms and the following items:</td>
                    <td>${b_notes}</td>
                </tr>
            </table>
            <br/><br/>
            Please come and stay with us again.
            <br/><br/>
            <!-- print the page for customer -->
            <form>
                <input type=button name=saveAsPdf value="Print Or Save as PDF" onClick="window.print()">
            </form>
            
        </div>
    </body>
</html>
