function removeValidation() {
    $(".is-invalid").removeClass("is-invalid");
    $(".invalid-feedback").hide()
}

$("#confirmCalculationButton").click(function () {
    if(calculated){
        spinner($("#cards"));
    }
    else {
        $("#confirmCalculationButton").hide();
        spinner($(".button-wrapper"))
    }
});