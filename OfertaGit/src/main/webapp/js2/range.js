$(function() {
  var $document = $(document);
  var $r = $('input[name=range]');
  var $value = $('input[name="value"]');
  var output = document.querySelectorAll('output')[0];
  var max_num = parseInt($('#amount_range').attr('max'));
  var min_num = parseInt($('#amount_range').attr('min'));
  
  // set initial output value
  output.textContent = $r[0].value;
  var out_value = output.textContent;
  var comma_value = (parseInt(out_value.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
  $( "#amount" ).val(comma_value);
  $( "#value_amount_url" ).val(out_value);
  
  // update output value
  $document.on('input', 'input[name="range"]', function(e) {
    output.textContent = e.currentTarget.value;
    var out_value = output.textContent;
    var comma_value = (parseInt(out_value.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
    $( "#amount" ).val(comma_value);
    $( "#value_amount_url" ).val(out_value);
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
    output.textContent = value;
    var out_value = output.textContent;
    var comma_value = (parseInt(out_value.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
    $( "#amount" ).val(comma_value);
    $( "#value_amount_url" ).val(out_value);
  });

  $("#amount").on("input", function() {
    var value = this.value;
    var res_value = value.toString().replace(/\,/g, "");
    $r.val(res_value).change();
    $r.rangeslider('update', true);

    // update output value
    output.textContent = res_value;

    var out_value = output.textContent;
    $( "#value_amount_url" ).val(out_value);
    var res = out_value.toString().replace(/\,/g, "");
    console.log(res);
    $( "#amount" ).val(value);
    var result_int = parseInt(res);
    console.log(result_int);

    if ( result_int > max_num ) {
      var number_max = (parseInt(max_num.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
      $('#amount').val(number_max);
      output.textContent = max_num;
      $( "#value_amount_url" ).val(max_num);
      console.log("error");
    }
  });
  $("#amount").focusout(function(){
    var outputer = $('output').text();
    var outputer_int = parseInt(outputer);
    if ( outputer_int < min_num ) {
      var number_min = (parseInt(min_num.toString().replace(/[^\d]+/gi, '')) || 0).toLocaleString('en-US');
      output.textContent = min_num;
      $( "#value_amount_url" ).val(min_num);
      $('#amount').val(number_min);
      console.log("error");
    }
  });

    $('#select_month').on('change', function() {
        var value = $(this).val();
        $( "#select_month_value" ).val(value);
    });
});
