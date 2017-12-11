<%-- 
    Document   : BookingConfirmation
    Created on : 07-Dec-2017, 15:40:29
    Author     : fvq13ndu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <!--Metadata-->
        <title>Booking Confirmation</title>
        <meta name="description" content="Heartache booking confirmation" />
        <meta name="keywords" content="booking, complete, confirmed" />
        <meta name="robots" content="no index, no follow" />
        <meta name="author" content="Heartache Hotel staff" />

        <!--Style sheets-->
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <link rel="stylesheet" media="screen and (max-width: 600px)" href="max-width-600px.css">
        <link rel="stylesheet" media="screen and (min-width: 600px) and (max-width: 1500px)" href="min-width-600px.css">

        <!--External Stylesheets-->
        <script src ="http://code.jquery.com/jquery-1.9.1.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Barlow+Semi+Condensed" rel="stylesheet">

        <!--JavaScript Links-->
        <script type ="text/javascript"></script>
        <script src ="HHjavascript.js"></script>

    </head>
    <body>

        <div class="Header">
            <p id="TextOptions">Text Options</p>
            <div class="LogoTitle">
                <h1>The Heartache Hotel</h1>
                <a class="Logo" href="Home.html"><img id="MainLogo" src="images\vk.png" alt="logo" style="width: 200px; height: 200px;" /></a>
            </div>
            <div class="banner">
                <img src="images\Header11.png" alt="Blue bedroom" />
            </div>
        </div>

        <div class="ConfirmMain">
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
                <tr><td>Billing address:</td><td>${addressline}</td></tr>
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

        </div>

        <div class="Footer">
            <img id="FooterLogo" src="images\vk.png" alt="Heartache Logo" />

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


            <img id="tripAdviser" src="images\trip.png" alt="Owl" style="height:150px; width:150px" />

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
