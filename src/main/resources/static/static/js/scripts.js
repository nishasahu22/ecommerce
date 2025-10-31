// Example simple alert for demo; extend for advanced actions
document.addEventListener("DOMContentLoaded", function() {
    // Show alerts for flash messages
    const success = document.querySelector(".alert-success");
    const error = document.querySelector(".alert-danger");
    if (success) {
        setTimeout(() => { success.style.display = "none"; }, 3000);
    }
    if (error) {
        setTimeout(() => { error.style.display = "none"; }, 5000);
    }
});
