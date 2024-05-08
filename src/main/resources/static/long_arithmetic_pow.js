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
    const container = $("#cards");
    spinner(container);
    $("#power").hide();

    setTimeout(function () {
        var numberSystem = getEnumValue($("#numberSystemSelect").val());
        var a = bigInt($("#fnumber").val(), numberSystem);
        var b = bigInt($("#snumber").val(), numberSystem);

        $("#powText").text(bigInt(longPower(a, b), 10).toString(numberSystem));
        $('#cards').waitMe("hide");
    }, 0)

});

function longPower(a, b) {
    if (a.equals( bigInt.zero))
        return "0";
    if (b.equals( bigInt.zero))
        return "1";
    let result = bigInt.one;
    const binaryB = b.toString(2)
    .split("")
    .reverse();

    for (var i = 0; i < binaryB.length; i++) {
        if (binaryB[i] === '1') {
            result = result.times(a);
        }
        a = a.times(a);
    }
    result = result.toString()
    return result;
}

function spinner(element) {
    $(document).ready(element.waitMe({
        effect: 'bounce',
        text: '',
        bg: '#FFF',
        color: '#aa00f8',
        maxSize: '',
        waitTime: -1,
        textPos: 'vertical',
        fontSize: '',
        source: '',
        onClose: function () {
        }
    }));
}