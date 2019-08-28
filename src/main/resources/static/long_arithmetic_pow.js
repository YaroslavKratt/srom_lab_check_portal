var NumberSystem = {"HEX": 16, "BINARY": 2, "DECIMAL": 10};
Object.freeze(NumberSystem);


function getEnumValue(numberSystemStr) {
    if (numberSystemStr === "HEX")
        return NumberSystem.HEX;
    if (numberSystemStr === "BINARY")
        return NumberSystem.BINARY;
    if (numberSystemStr === "DECIMAL")
        return NumberSystem.DECIMAL;
}

$(document).on('click', '#power', function () {
    var container = $("#cards");
    spinner(container);
    $("#power").hide();

    setTimeout(function () {
        var numberSystem = getEnumValue($("#numberSystemSelect").val());
        var a = bigInt($("#fnumber").val(), numberSystem);
        var result = bigInt.one;
        var binaryB = bigInt($("#snumber").val(), numberSystem)
            .toString(2).split("")
            .reverse();

        for (var i = 0; i < binaryB.length; i++) {
            if (binaryB[i] === '1') {
                result = result.times(a);
            }
            a = a.times(a);
        }
        result = result.toString()
        $("#powText").text(bigInt( result,10).toString(numberSystem));
        $('#cards').waitMe("hide");
    }, 0)

});

function spinner(element) {
    $(document).ready(element.waitMe({
        effect: 'bounce',
        text: '',
        bg: '#FFF',
        color: '#000',
        maxSize: '',
        waitTime: -1,
        textPos: 'vertical',
        fontSize: '',
        source: '',
        onClose: function () {
        }
    }));
}






