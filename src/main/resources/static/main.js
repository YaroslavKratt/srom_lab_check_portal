// Modern vanilla JavaScript - no jQuery

/**
 * Remove validation errors from form elements
 */
function removeValidation() {
    document.querySelectorAll('.is-invalid').forEach(el => {
        el.classList.remove('is-invalid');
    });
    document.querySelectorAll('.invalid-feedback').forEach(el => {
        el.style.display = 'none';
    });
}

/**
 * Show custom loading spinner
 */
function showCustomSpinner(text) {
    // Remove existing spinner first
    hideCustomSpinner();

    const spinnerText = text || (window.i18n && window.i18n.calculating) || 'Calculating...';
    const spinnerHTML = `
        <div class="custom-spinner-overlay">
            <div class="custom-spinner">
                <div class="spinner-dots">
                    <div class="spinner-dot"></div>
                    <div class="spinner-dot"></div>
                    <div class="spinner-dot"></div>
                </div>
                <div class="spinner-text">${spinnerText}</div>
            </div>
        </div>
    `;
    document.body.insertAdjacentHTML('beforeend', spinnerHTML);
}

/**
 * Hide custom loading spinner
 */
function hideCustomSpinner() {
    const overlays = document.querySelectorAll('.custom-spinner-overlay');
    overlays.forEach(function(overlay) {
        overlay.parentNode.removeChild(overlay);
    });
}

/**
 * Initialize application when DOM is ready
 */
document.addEventListener('DOMContentLoaded', function() {
    // Handle form submission spinner (skip for finite field form - it has its own handling)
    const confirmButton = document.getElementById('confirmCalculationButton');
    if (confirmButton) {
        confirmButton.addEventListener('click', function(e) {
            const form = this.closest('form');
            // Skip spinner for finite field form - it has async handling with its own spinner
            if (form && form.id === 'finiteFieldForm') {
                return;
            }
            if (form && form.checkValidity()) {
                showCustomSpinner();
            }
        });
    }

    // Remove validation on input change
    const formElements = document.querySelectorAll('input, select');
    formElements.forEach(element => {
        element.addEventListener('change', removeValidation);
        element.addEventListener('input', removeValidation);
    });

    // Add animation class to result cards
    const resultCards = document.querySelectorAll('.result-card');
    resultCards.forEach(card => {
        card.classList.add('animate-slide-in');
    });

    // Handle power calculation button if exists
    const powerButton = document.getElementById('power');
    if (powerButton) {
        powerButton.addEventListener('click', function(e) {
            e.preventDefault();
            showCustomSpinner();
            // Power calculation logic would go here
            // For now, this is just a placeholder
            setTimeout(() => {
                hideCustomSpinner();
            }, 1000);
        });
    }
});

// Hide spinner on page load (in case it's still showing from previous submission)
window.addEventListener('load', function() {
    hideCustomSpinner();
});
