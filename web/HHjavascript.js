/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function switchToManage(){
    var manageBooking = {
        Name: "Manage a Booking"
    };
    
    $('#bookingTitle').empty();
    $('#bookingTitle').append(manageBooking.Name);
    
    document.getElementById('book').style.display='none';
    document.getElementById('manage').style.display='block';
}

function switchToBook(){
    var makeBooking = {
            Name: "Make a Booking"
    };
    
    $('#bookingTitle').empty();
    $('#bookingTitle').append(makeBooking.Name);
    
    document.getElementById('manage').style.display='none';
    document.getElementById('book').style.display='block';
}

$(document).ready(function(){
   $("#manage").hide();

});
