function openPopup() {
    document.getElementById("myPopup").style.height = "100%";
    document.getElementById("body").classList.add('popup-open');
}

function closePopup() {
    document.getElementById("myPopup").style.height = "0%";
    document.getElementById("body").classList.remove('popup-open');
}

