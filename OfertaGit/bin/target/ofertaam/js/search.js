// search popup

document.getElementById("overlay").addEventListener('click', function (ev) {
    this.classList.remove('show');
    document.getElementById("searchPopup").classList.remove('show');
    document.getElementsByTagName("html")[0].classList.remove('no-scroll');
});

document.getElementById("searchBtn").addEventListener('click', function (ev) {
    ev.stopPropagation();
    document.getElementById("searchPopup").classList.add('show');
    document.getElementById("overlay").classList.add('show');
    document.getElementsByTagName("html")[0].classList.add('no-scroll');
});

document.getElementById("closeBtn").addEventListener('click', function (ev) {
    ev.stopPropagation();
    document.getElementById("searchPopup").classList.remove('show');
    document.getElementById("overlay").classList.remove('show');
    document.getElementsByTagName("html")[0].classList.remove('no-scroll');
});
