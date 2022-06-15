
$( function() {
    var min_num = parseInt($('#amount').attr('min'));
    var max_num = parseInt($('#amount').attr('max'));
    var step_num = parseInt($('#amount').attr('step'));
    var value_num = parseInt($('#amount').attr('value'));

    $( "#slider-range-min" ).slider({
        range: "min",
        value: value_num,
        min: min_num,
        max: max_num,
        step: step_num,
        slide: function( event, ui ) {
            var ui_value = ui.value;
            var comma_value = (parseInt(ui_value.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
            $( "#amount" ).val(comma_value);
        }
    });
    $( "#amount").val($("#slider-range-min" ).slider( "value" ) );

    $( "#slider-range-min-second" ).slider({
        range: "min",
        value: value_num,
        min: min_num,
        max: max_num,
        step: step_num,
        slide: function( event, ui ) {
            var ui_value = ui.value;
            var comma_value = (parseInt(ui_value.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
            $( "#amount-second" ).val(comma_value);
        }
    });
    $( "#amount-second").val($("#slider-range-min-second" ).slider( "value" ) );
} );