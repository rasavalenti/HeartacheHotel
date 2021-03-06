<%-- 
    Document   : BookingForm
    Created on : 11-Dec-2017, 20:29:38
    Author     : fvq13ndu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>

        <!--Metadata-->
        <title>Booking Form</title>
        <meta name="keywords" content="booking, form, bookingform, make, manage" />
        <meta name="robots" content="no index, no follow" />
        <meta name="author" content="Heartache Hotel staff" />

        <!--Style Sheets-->
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <link rel="stylesheet" media="screen and (max-width: 600px)" href="max-width-600px.css">
        <link rel="stylesheet" media="screen and (min-width: 600px) and (max-width: 1700px)" href="min-width-600px.css">

        <!--External Style Sheets-->
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
                <a class="Logo" href="Home.html"><img id="MainLogo" src="images\Logo.png" alt="logo" /></a>
            </div>
            <div class="banner">
                <img src="images\Header10.png" alt="Room with luggage" />
            </div>
        </div>

        <div class="BookingFormMain">

            <form method="POST" name="Form1" action="AccessServlet">

                <fieldset>
                    <table id="formDetails">
                        <tr><td colspan="2"><strong>Booking details</strong></td></tr>
                        <tr><td><label> Check-in: </label></td> <td>${checkin}</td></tr>

                        <tr><td><label> Check-out: </label></td> <td>${checkout}</td></tr>

                        <tr><td><label> Number of rooms: </label> </td> <td>${numOfRooms}</td></tr>
                        <tr><td><label> Room type: </label> </td> <td>${roomtypename}</td></tr>
                        <tr><td><label> Total: </label> </td> <td>£ ${b_cost}</td></tr>
                        <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                        <tr><td colspan="2"><strong>Personal details</strong></td></tr>
                        <tr><td><label for="forename"> Forename: </label></td> <td><input type="text" name="forename" id="forename" required></td></tr>

                        <tr><td><label for="surname"> Surname: </label></td> <td> <input type="text" name="surname" id="surname" required></td></tr>

                        <tr><td><label for="email"> Email: </label> </td> <td><input type="email" name="email" id="email" required></td></tr>

                        <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                        <tr><td colspan="2"><strong>Payment information</strong></td></tr>

                        <tr><td>
                                <label for="usercard">Card type: </label> </td> <td>
                                <select id="usercard" name="usercard">
                                    <option value="V">Visa</option>
                                    <option value="MC">Mastercard</option>
                                    <option value="A">American Express</option>
                                </select> </td></tr>

                        <tr><td>
                                <label for="cardnumber">Card number:</label> </td> <td>
                                <input type="number" id="cardnumber" name="cardnumber" maxlength="16" required> </td></tr>


                        <tr>
                            <td>
                                Expiration date:
                            </td>    
                            <td>
                                <select name="month" id="month" required>
                                    <option value="1">January</option>
                                    <option value="2">February</option>
                                    <option value="3">March</option>
                                    <option value="4">April</option>
                                    <option value="5">May</option>
                                    <option value="6">June</option>
                                    <option value="7">July</option>
                                    <option value="8">August</option>
                                    <option value="9">September</option>
                                    <option value="10">October</option>
                                    <option value="11">November</option>
                                    <option value="12">December</option>
                                </select>


                                <select name="year" id="year" required>
                                    <option value="17">2017</option>
                                    <option value="18">2018</option>
                                    <option value="19">2019</option>
                                    <option value="20">2020</option>
                                    <option value="21">2021</option>
                                    <option value="22">2022</option>
                                    <option value="23">2023</option>
                                    <option value="24">2024</option>
                                </select>

                            </td>
                        </tr>
                        <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                        <tr><td colspan="2"><strong>Billing Address </strong></td></tr>
                        <tr><td>Address:</td><td><input type="text" name="addressline" id="addressline" required></td></tr>
                        <tr><td>City:</td><td><input type="text" name="city" id="city" required></td></tr>
                        <tr><td>Post Code:</td><td><input type="text" name="postcode" id="postcode" required></td></tr>

                        <tr><td>Additional Notes: </td><td>
                                <textarea id="WideArea" name="bookingNotes" cols="30" rows="10" placeholder="If you have any additional requirements please state them here..."></textarea> </td></tr>


                    </table>
                    <br/>
                    <input class="FormInput" type="submit" value="Make a booking">
                    <input class="FormInput" type="reset" value="Reset" id="reset_button">
                    <input class="FormInput" type="button" value="Cancel" onclick="javascript:location.href = 'Booking.html'">
                    

                </fieldset>    
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
