// mb-navigation

document.getElementById("mbNavBtn").addEventListener('click', function (ev) {
    ev.stopPropagation();
    document.getElementById("mbNav").classList.add('show');
    document.getElementsByTagName("html")[0].classList.add('no-scroll');
});

document.getElementById("navCloseBtn").addEventListener('click', function (ev) {
    ev.stopPropagation();
    document.getElementById("mbNav").classList.remove('show');
    document.getElementsByTagName("html")[0].classList.remove('no-scroll');
});

// var selectBoxes = document.querySelectorAll(".def-select-box");
//
// selectBoxes.forEach(function(item) {
//     item.addEventListener('click', function (ev) {
//         this.classList.toggle('opened');
//     });
// });


function openTabItem(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tab-content");

    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tab-link");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();


function toggleSubSuggestions() {
    document.querySelector('.sub-row').classList.toggle('show');
    document.querySelector('.icon-arrow-down').classList.toggle('icon-arrow-up');
}

function toggleBoxes(id) {
    var e = document.getElementById(id);

    if(e.style.display == 'block')
        e.style.display = 'none';
    else
        e.style.display = 'block';
}
