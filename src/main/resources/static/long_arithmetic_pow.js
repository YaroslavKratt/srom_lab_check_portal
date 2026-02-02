// Modern vanilla JavaScript for power calculation

var NumberSystem = {"HEX": 16, "BINARY": 2, "DECIMAL": 10, "BIN": 2};
Object.freeze(NumberSystem);

function getEnumValue(numberSystemStr) {
    console.log('Number system:', numberSystemStr);
    if (numberSystemStr === "HEX")
        return NumberSystem.HEX;
    if (numberSystemStr === "BINARY" || numberSystemStr === "BIN")
        return NumberSystem.BINARY;
    if (numberSystemStr === "DECIMAL")
        return NumberSystem.DECIMAL;
    return NumberSystem.DECIMAL; // default
}

function longPower(a, b) {
    if (a.equals(bigInt.zero))
        return "0";
    if (b.equals(bigInt.zero))
        return "1";
    if (a.equals(bigInt.one))
        return "1";

    // For very large exponents, use a more memory-efficient approach
    let result = bigInt.one;
    let base = a;
    let exp = b;

    // Binary exponentiation (right-to-left)
    while (exp.greater(bigInt.zero)) {
        if (exp.isOdd()) {
            result = result.times(base);
        }
        exp = exp.divide(2);
        if (exp.greater(bigInt.zero)) {
            base = base.times(base);
        }
    }

    return result.toString();
}

// Wait for DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    console.log('Power calculation script loaded');

    // Use event delegation for the power button
    document.body.addEventListener('click', function(event) {
        // Check if the clicked element is the power button
        if (event.target && (event.target.id === 'power' || event.target.closest('#power'))) {
            event.preventDefault();
            console.log('Power button clicked');

            const powerButton = document.getElementById('power');
            const powText = document.getElementById('powText');

            if (!powerButton || !powText) {
                console.error('Power button or text element not found');
                return;
            }

            powerButton.disabled = true;
            powText.textContent = window.i18n ? window.i18n.calculating : 'Calculating...';

            // Show spinner
            if (typeof showCustomSpinner === 'function') {
                showCustomSpinner();
            }

            // Allow up to 60 seconds for large calculations
            setTimeout(function() {
                try {
                    const numberSystemSelect = document.getElementById('numberSystemSelect');
                    const fnumber = document.getElementById('fnumber');
                    const snumber = document.getElementById('snumber');

                    if (!numberSystemSelect || !fnumber || !snumber) {
                        throw new Error(window.i18n ? window.i18n.requiredFields : 'Required elements not found');
                    }

                    const numberSystemStr = numberSystemSelect.value;
                    const firstNumber = fnumber.value.trim();
                    const secondNumber = snumber.value.trim();

                    console.log('Inputs:', {
                        numberSystem: numberSystemStr,
                        firstNumber: firstNumber,
                        secondNumber: secondNumber
                    });

                    if (!firstNumber || !secondNumber) {
                        throw new Error(window.i18n ? window.i18n.enterBoth : 'Please enter both numbers');
                    }

                    const numberSystem = getEnumValue(numberSystemStr);

                    // Check if bigInt is available
                    if (typeof bigInt === 'undefined') {
                        throw new Error(window.i18n ? window.i18n.libraryMissing : 'BigInteger library not loaded');
                    }

                    const a = bigInt(firstNumber, numberSystem);
                    const b = bigInt(secondNumber, numberSystem);

                    console.log('BigInt values:', {a: a.toString(), b: b.toString()});

                    // Estimate result size and warn if it will take long
                    if (a.greater(bigInt.one) && b.greater(bigInt(1000))) {
                        const estimatedDigits = b.toJSNumber() * Math.log10(a.toJSNumber());
                        if (estimatedDigits > 100000) {
                            powText.textContent = 'This will take a while (est. ' +
                                Math.round(estimatedDigits).toLocaleString() + ' digits)...';
                        }

                        // Hard limit at 10 million digits to prevent complete browser freeze
                        if (estimatedDigits > 10000000) {
                            throw new Error('Result would be too large (' +
                                Math.round(estimatedDigits).toLocaleString() +
                                ' digits). Maximum supported: 10,000,000 digits.');
                        }
                    }

                    const result = longPower(a, b);
                    const resultText = bigInt(result, 10).toString(numberSystem).toUpperCase();

                    console.log('Result:', resultText);
                    powText.textContent = resultText;

                } catch (error) {
                    console.error('Error calculating power:', error);

                    // Handle different types of errors
                    let errorMessage = error.message;
                    if (error.message && error.message.includes('too large to allocate')) {
                        errorMessage = window.i18n ? window.i18n.powerTooLarge : 'Result is too large for browser memory. Try smaller numbers or use modular exponentiation.';
                    } else if (error.message && error.message.includes('Maximum call stack')) {
                        errorMessage = window.i18n ? window.i18n.powerStack : 'Calculation is too complex. Try smaller exponent values.';
                    }

                    powText.textContent = 'Error: ' + errorMessage;
                } finally {
                    // Hide spinner
                    if (typeof hideCustomSpinner === 'function') {
                        hideCustomSpinner();
                    }
                    powerButton.disabled = false;
                }
            }, 100);
        }
    });
});
