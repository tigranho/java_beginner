
$( function() {
    $( "#slider-range-min" ).slider({
        range: "min",
        value: 120000,
        min: 50000,
        max: 1200000,
        step: 10000,
        slide: function( event, ui ) {
            $( "#amount" ).val(ui.value);
        }
    });
    $( "#amount").val($("#slider-range-min" ).slider( "value" ) );

    $( "#slider-range-min-second" ).slider({
        range: "min",
        value: 120000,
        min: 50000,
        max: 1200000,
        step: 10000,
        slide: function( event, ui ) {
            $( "#amount-second" ).val(ui.value);
        }
    });
    $( "#amount-second").val($("#slider-range-min-second" ).slider( "value" ) );
} );