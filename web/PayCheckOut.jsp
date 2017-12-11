<%-- 
    Document   : BookingConfirmation
    Created on : 09-Dec-2017, 20:12:34
    Author     : qhf13exu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <!--Metadata-->
        <title>PayCheckOut</title>

        <!--Style sheets-->
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <link rel="stylesheet" media="screen and (max-width: 600px)" href="max-width-600px.css">
        <link rel="stylesheet" media="screen and (min-width: 600px) and (max-width: 1500px)" href="min-width-600px.css">

        <!--External stylesheets-->
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
                <a class="Logo" href="Home.html"><img id="MainLogo" src="images\Logo.png" alt="logo" style="width: 200px; height: 200px;" /></a>
            </div>
            <div class="banner">
                <img src="images\Header8.png" alt="Norfolk Farmland" />
            </div>
        </div>

        <div class="PayCheckOutMain">
            <h1>Pay and check out</h1>
            <table id="formDetails">

                <tr>
                    <td colspan="2"><strong>Personal details</strong></td>
                </tr>
                <tr>
                    <td>Customer number is:</td>
                    <td>${c_no}</td>
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
