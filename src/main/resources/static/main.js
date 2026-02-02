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
function showCustomSpinner() {
    const spinnerHTML = `
        <div class="custom-spinner-overlay">
            <div class="custom-spinner">
                <div class="custom-spinner-circle"></div>
                <div class="custom-spinner-inner"></div>
                <div class="spinner-text">Calculating...</div>
            </div>
        </div>
    `;
    document.body.insertAdjacentHTML('beforeend', spinnerHTML);
}

/**
 * Hide custom loading spinner
 */
function hideCustomSpinner() {
    const overlay = document.querySelector('.custom-spinner-overlay');
    if (overlay) {
        overlay.remove();
    }
}

/**
 * Initialize application when DOM is ready
 */
document.addEventListener('DOMContentLoaded', function() {
    // Handle form submission spinner
    const confirmButton = document.getElementById('confirmCalculationButton');
    if (confirmButton) {
        confirmButton.addEventListener('click', function(e) {
            const form = this.closest('form');
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
