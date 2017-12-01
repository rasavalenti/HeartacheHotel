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
