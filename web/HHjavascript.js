/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        });
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


//Calendar datepicker for booking
$(function () {
$("#checkIn").datepicker({dateFormat: 'dd-mm-yy', minDate: 0, maxDate: "+1Y"});
        //$("#checkOut").datepicker({dateFormat: 'dd-mm-yy', minDate: document.getElementById("checkIn").value});
        });
        function checkOutDate() {
        alert("Check out date" + document.getElementById("checkIn").value);
                $("#checkOut").datepicker({dateFormat: 'dd-mm-yy', minDate: document.getElementById("checkIn").value, maxDate: "+1Y"});
                var minDate = $("#checkOut").datepicker("option", "minDate");
                alert(minDate);
                }
