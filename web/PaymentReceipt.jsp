<%-- 
    Document   : PaymentReceipt
    Created on : 09-Dec-2017, 21:11:26
    Author     : qhf13exu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <!--Metadata-->
        <title>Payment Receipt</title>

        <!--Style sheets-->
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <link rel="stylesheet" media="screen and (max-width: 600px)" href="max-width-600px.css">
        <link rel="stylesheet" media="screen and (min-width: 600px) and (max-width: 1700px)" href="min-width-600px.css">

        <!--External stylesheets-->
        <script src ="http://code.jquery.com/jquery-1.9.1.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Barlow+Semi+Condensed" rel="stylesheet">

        <!--JavaScript Links-->
        <script type ="text/javascript"></script>
        <script src ="HHjavascript.js"></script>

    </head>

    <body>

        <div class="Header">
            <div class="LogoTitle">
                <h1>The Heartache Hotel</h1>
                <a class="Logo" href="ReceptionPortal.html"><img id="MainLogo" src="images\Logo.png" alt="logo" /></a>
            </div>
            <div class="banner">
                <img src="images\Header1.png" alt="Norfolk Coast" />
            </div>
        </div>

        <div class="BookingReceiptMain">
            <a id="PortalReturn" href="ReceptionPortal.html"> <- Back to Portal</a><br>
            <h2>Receipt</h2>
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

        <div class="Footer">
            <img id="FooterLogo" src="images\Logo.png" alt="Heartache Logo" />

            <ul id="FooterLinks">
                <li><a href="Home.html">Home</a></li>
                <li><a href="Booking.html">Bookings</a></li>
                <li><a href="Rooms.html">Rooms</a></li>
                <li><a href="Facilities.html">Facilities</a></li>
                <li><a href="localattractions.html">Local Attractions</a></li>
                <li><a href="Reviews.html">Leave a Review</a></li>
                <li><a href="FindUs.html">Find us</a></li>
                <li><a href="ContactUs.html">Contact us</a></li>
            </ul>

            <ul id="FooterEmail">
                <li>CONTACT US</li>
                <li><a href="mailto:heartachehotel@heartachehotel.com? Subject=Queries" target="_top">Click to Email</a></li>
                <li>Tel: +44-000-000-0000</li>
                <li>Heartache Hotel</li>
                <li>Queen's Road</li>
                <li>Hethersett</li>
                <li>Norfolk</li>
                <li>NR3 4LE</li>
            </ul>

            <ul id="FooterCopyright">
                <li>COPYRIGHT</li>
                <li>@Copyright 2017</li>
                <li>Heartache Hotel</li>
                <li>All Rights Reserved</li>
            </ul>


            <img id="tripAdviser" src="images\trip.png" alt="Owl" />

            <div id="SocialMedia">
                <p>SOCIAL MEDIA LINKS</p>
                <a href="https://en-gb.facebook.com/" target="_blank"><img src="images\facebook.png" alt="Facebook" /></a>
                <a href="https://www.instagram.com/?hl=en" target="_blank"><img src="images\instagram.png" alt="Instagram" /></a>
                <a href="https://twitter.com/login?lang=en-gb" target="_blank"><img src="images\twitter.png" alt="Twitter" /></a>
                <a href="https://www.tumblr.com/" target="_blank"><img src="images\tumblr.png" alt="Tumblr" /></a>
                <a href="https://www.pinterest.com/" target="_blank"><img src="images\pinterest.png" alt="Pinterest" /></a>
                <a href="https://plus.google.com/discover" target="_blank"><img src="images\google-plus.png" alt="Google Plus" /></a>  
            </div>

        </div>
    </body>
</html>
