
$(document).ready(function() {
    initCarousels();
});

function initCarousels() {
    var owlSimpleCl = $('#simpleCarousel');
    var owlPartnersCl = $('#partnersCarousel');

    owlSimpleCl.owlCarousel({
        margin: 18,
        nav: true,
        responsive:{
            0:{
                items: 1
            },
            620:{
                items: 2
            },
            860:{
                items: 3
            },
        }
    });

    owlPartnersCl.owlCarousel({
        margin: 2,
        nav: true,
        responsive:{
            0:{
                items: 2
            },
            620:{
                items: 3
            },
            860:{
                items: 4
            },
        }
    });
}
