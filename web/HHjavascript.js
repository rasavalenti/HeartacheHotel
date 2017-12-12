/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global rooms */

function switchToManage() {

    document.getElementById('book').style.display = 'none';
    document.getElementById('manage').style.display = 'block';
}

function switchToBook() {

    document.getElementById('manage').style.display = 'none';
    document.getElementById('book').style.display = 'block';
}

$(document).ready(function () {
    $("#manage").hide();
    $("#book").show();
    $("#m_manageBooking").hide();
    $("#m_cancelBooking").hide();

});

function switchToCancelBooking() {
    document.getElementById('m_manageBooking').style.display = 'none';
    document.getElementById('m_cancelBooking').style.display = 'block';
}

function switchToAddRooms() {
    document.getElementById('m_cancelBooking').style.display = 'none';
    document.getElementById('m_manageBooking').style.display = 'block';
}


//Image Slideshow
function slideshow() {
    var elements = document.querySelectorAll('.slide');
    if (curSlide === elements.length) {
        curSlide = 0;
    }

    for (x = 0; x < elements.length; x++) {
        if (x === curSlide) {
            elements[x].style.opacity = 1;
        } else {
            elements[x].style.opacity = 0;
        }
    }
    curSlide++;
}

//Review Slideshow
function reviewSlideshow() {
    var elements = document.querySelectorAll('.review');
    if (revSlide === elements.length) {
        revSlide = 0;
    }

    for (x = 0; x < elements.length; x++) {
        if (x === revSlide) {
            elements[x].style.opacity = 1;
        } else {
            elements[x].style.opacity = 0;
        }
    }
    revSlide++;
}

function getReview() {
    text = first.value + Review.value;
    document.getElementById("first").innerHTML += '<p>' + text;
    document.getElementById("Review").innerHTML = '<p>' + text;
    document.getElementById("first" + "Review").value = "Submit";
}

//The saveBookingPage function gets the information from the bookin form and makes it into
//an sql statement
function saveBookingPage()
{
    var c_no = Math.floor((Math.random() * 10000) + 13000);
    //The random c_no needs to be checked against the existing customers to make 
    //sure it doesn't duplicate

    var forename = document.getElementById("forename").value;
    var surname = document.getElementById("surname").value;
    var email = document.getElementById("email").value;
    var addressline = document.getElementById("addressline").value;
    var city = document.getElementById("city").value;
    var postcode = document.getElementById("postcode").value;
    var card = document.getElementById("card").value;
    var month = document.getElementById("month").value;
    var year = document.getElementById("year").value;
    var cardnumber = document.getElementById("cardnumber").value;

    var sqlstatement = "insert into customer values (" + c_no + ", '" + forename + " " + surname + "'," +
            " '" + email + "', '" + addressline + ", " + city + " " + postcode + "'," +
            " '" + card + "', '" + month + "/" + year + "', '" + cardnumber + "');";

    //alert is here to check how the sql statement looks
    alert("THE SQL STATEMENT IS: " + sqlstatement);
}


//Calendar datepicker for booking
$(function () {
    $("#checkIn").datepicker({dateFormat: 'dd-mm-yy', minDate: 0, maxDate: "+1Y"});
    $("#checkOut").datepicker({dateFormat: 'dd-mm-yy', minDate: 0});

    $("#checkInHP").datepicker({dateFormat: 'dd-mm-yy', minDate: 0, maxDate: "+1Y"});
    $("#checkOutHP").datepicker({dateFormat: 'dd-mm-yy', minDate: 0});
//    $("#checkOutHP").datepicker({dateFormat: 'dd-mm-yy', minDate: document.getElementById("checkInHP").value + "1D"});

});

//$(function () {
//    $("#checkInHP").datepicker({dateFormat: 'dd-mm-yy', minDate: 0, maxDate: "+1Y"});
//    //$("#checkOut").datepicker({dateFormat: 'dd-mm-yy', minDate: document.getElementById("checkIn").value});
//});

function checkOutDate() {
//    alert("Check out date" + document.getElementById("checkIn").value);
    $("#checkOut").datepicker({dateFormat: 'dd-mm-yy', minDate: document.getElementById("checkInHP").value, maxDate: "+1Y"});
    var minDate = $("#checkOut").datepicker("option", "minDate");
//    alert(minDate);
}

function checkOutDate2() {
//    alert("Check out date" + document.getElementById("checkIn").value);
    $("#checkOutHP").datepicker({dateFormat: 'dd-mm-yy', minDate: document.getElementById("checkInHP").value, maxDate: "+1Y"});
    var minDate = $("#checkOutHP").datepicker("option", "minDate");
//    alert(minDate);
}

// function for showing checked out rooms in table
function showTables()
{
    var table = document.getElementById("roomTable");
    for (var i = 0; i < rooms.length; i++) {
        console.log("room[" + i + "]: " + rooms[i]);
        var tr = table.insertRow(i + 1);
        var td = tr.insertCell(0);
        td.innerHTML = rooms[i];
    }
}

// js for interactive map on findus.html
function init() {
    map = new OpenLayers.Map("hotelMap");
    var mapnik = new OpenLayers.Layer.OSM();
    var zoom = 15;
    var center = new OpenLayers.LonLat(1.17359, 52.59761).transform('EPSG:4326', 'EPSG:3857');
    map.addLayer(mapnik);
    map.setCenter(center, zoom);
}