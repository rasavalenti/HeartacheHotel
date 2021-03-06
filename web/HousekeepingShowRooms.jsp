<%-- 
    Document   : HousekeepingShowRooms
    Created on : 10-Dec-2017, 16:23:04
    Author     : qhf13exu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <!--Metadata-->
        <title>Rooms</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--Style sheets-->
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <link rel="stylesheet" media="screen and (max-width: 600px)" href="max-width-600px.css">
        <link rel="stylesheet" media="screen and (min-width: 600px) and (max-width: 1700px)" href="min-width-600px.css">

        <!--External style sheets-->
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
                <a class="Logo" href="HousekeepingView.html"><img id="MainLogo" src="images\Logo.png" alt="logo" /></a>
            </div>
            <div class="banner">
                <img src="images\Header9.png" alt="Room with luggage" />
            </div>
        </div>

        <div class="ShowRoomsMain">
            <h2>Checked out rooms</h2>
            <div class="RoomsForm">
                <table id="showRooms">
                    <tr>
                    <form method="POST" name="refresh" action="ShowRooms">
                        <td colspan="2"><input class="FormOutput" type="Submit" value="Refresh" name="refresh" id="refresh"</td>
                    </form>
                    </tr>
                    <form method="POST" name="housekeepingUpdate" id="housekeepingUpdate" action="hkRoomStatus">   
                        <tr colspan="2">
                            <td><input class="FormOutput" type="text" name="roomNumber" id="roomNumber"></td>
                        </tr>
                        <tr>
                            <td>
                                <input class="FormOutput" name="roomStatus" type="radio" id="radioAvailable" value="A" required><label for="radioAvailable">Available</label>
                            </td>
                        </tr>
                        <tr colspan="2">
                            <td>
                                <input class="FormOutput" name="roomStatus" type="radio" id="radioUnavailable" value="X" required><label for="radioUnavailable">Unavailable</label>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><input class="FormOutput" type="submit" value="Change" name="submitHousekeeping" id="submitHousekeeping"</td>
                        </tr>
                    </form>
                </table>

                <table id="roomTable">
                    <tr>
                        <td>Room Number:</td>
                    </tr>
                    <script>
                        var rooms = ${roomNums};
                        showTables();
                    </script>
                </table>
            </div>
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
