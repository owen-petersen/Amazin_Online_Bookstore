function purchase() {
    localStorage.removeItem("cart");
}

document.addEventListener("DOMContentLoaded", () => {
    const purchaseButton = document.getElementById("purchase-button");

    purchaseButton.addEventListener("click", () => {
        purchase();
    });
});