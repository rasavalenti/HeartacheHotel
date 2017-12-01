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

});


//Slideshow
function slideshow(divID) {
    var elements = document.querySelector('.slide').getElementsByID(divID);

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
    
    var sqlstatement = "insert into customer values ("+c_no+", '"+forename+" "+surname+"',"+
            " '"+email+"', '"+addressline+", "+city+" "+postcode+"',"+
            " '"+card+"', '"+month+"/"+year+"', '"+cardnumber+"');";
    
    //alert is here to check how the sql statement looks
    alert("THE SQL STATEMENT IS: " + sqlstatement);
    
}
