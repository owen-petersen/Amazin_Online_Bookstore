document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("loginForm");
    const clientError = document.getElementById("clientError");

    form.addEventListener("submit", (e) => {
        const username = form.username.value.trim();
        const password = form.password.value.trim();

        if (!username || !password) {
            e.preventDefault();
            clientError.textContent = "Please enter both username and password.";
        } else {
            clientError.textContent = "";
            // normal submit, existing LoginController handles authentication
        }
    });
});
