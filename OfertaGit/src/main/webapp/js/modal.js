var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementById("closeModal");

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
    document.getElementsByTagName("html")[0].classList.add('no-scroll');
};

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
    document.getElementsByTagName("html")[0].classList.remove('no-scroll');
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
        document.getElementsByTagName("html")[0].classList.remove('no-scroll');
    }
};