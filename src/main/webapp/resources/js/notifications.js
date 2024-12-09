document.addEventListener("DOMContentLoaded", function () {
    const errorToastEl = document.getElementById("errorToast");
    const successToastEl = document.getElementById("successToast");

    if (errorToastEl) {
        const errorToast = new bootstrap.Toast(errorToastEl);
        errorToast.show();
    }
    if (successToastEl) {
        const successToast = new bootstrap.Toast(successToastEl);
        successToast.show();
    }
});