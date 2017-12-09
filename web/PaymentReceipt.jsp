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
                    <td colspan="2">Thank you for staying with us!<br></td>
                </tr>
                <tr>
                    <td>Overall charge:</td>
                    <td>£${b_outstanding}</td>
                </tr>
                <tr>
                    <td>- Rooms</td>
                    <td>£${b_cost}</td>
                </tr>
                <tr>
                    <td>- Extras</td>
                    <td>£${extras}</td>
                </tr>
            </table>
            <br>
            Please come and stay with us again.
            <br>
            <!-- print the page for customer -->
            <form>
                <input type="button" name="saveAsPdf" value="Print" onClick="window.print()">
            </form>     
        </div>
    </body>
</html>
