$(function() {
    var $document = $(document);

    $("#percent_input").on("input", function() {
        var value = this.value;
        var res_value = value.toString().replace(/\%/g, "");
        $("#percent_input").attr("value", res_value);
    });
});
