$(function() {
  var $document = $(document);
  var $r = $('input[name=range_two]');
  var $value = $('input[name="value_two"]');
  var output_hyper = document.querySelectorAll('output')[1];
  var max_num_hyper = parseInt($('#amount_range_two').attr('max'));
  var min_num = parseInt($('#amount_range_two').attr('min'));

  // set initial output value
  output_hyper.textContent = $r[0].value;
  var out_value = output_hyper.textContent;
  var comma_value = (parseInt(out_value.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
  $( "#amount_two" ).val(comma_value);

  // update output value
  $document.on('input', 'input[name=range_two]', function(e) {
    output_hyper.textContent = e.currentTarget.value;
    var out_value = output_hyper.textContent;
    var comma_value = (parseInt(out_value.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
    $( "#amount_two" ).val(comma_value);
  });

  // Initialize
  $r.rangeslider({
    polyfill: false
  });

  // Example functionality to demonstrate programmatic attribute/value changes
  $value.on('input', function() {
    var value = $value[0].value;

    $r.val(value).change();
    $r.rangeslider('update', true);

    // update output value
    output_hyper.textContent = value;
    var out_value = output_hyper.textContent;
    var comma_value = (parseInt(out_value.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
    $( "#amount_two" ).val(comma_value);
  });

  $("#amount_two").on("input", function() {
    var value = this.value;
    var res_value = value.toString().replace(/\,/g, "");
    $r.val(res_value).change();
    $r.rangeslider('update', true);

    // update output value
    output_hyper.textContent = res_value;

    var out_value = output_hyper.textContent;
    var res = out_value.toString().replace(/\,/g, "");
    console.log(res);
    $( "#amount_two" ).val(value);
    var result_int = parseInt(res);
    console.log(result_int);

    if ( result_int > max_num_hyper ) {
    var number_max = (parseInt(max_num_hyper.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
    $('#amount_two').val(number_max);
    }
  });
  $("#amount_two").focusout(function(){
    var outputer = output_hyper.textContent;
    var outputer_int = parseInt(outputer);
    if ( outputer_int < min_num ) {
      var number_min = (parseInt(min_num.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
      output_hyper.textContent = min_num;
      $('#amount_two').val(number_min);
      console.log("error");
    }
  });
});
