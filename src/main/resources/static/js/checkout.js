let checkoutEndpoint = "/checkout";

function completePayment() {
    submitPayment().then(() => {});
    localStorage.removeItem("cart");
}

async function submitPayment() {
    let path = `${checkoutEndpoint}/purchase`;
    const request = {
        "itemIds": JSON.parse(localStorage.getItem("cart"))
    };

    return fetch(path, {
        method: "POST",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify(request)
    }).then(response => response.json());
}

function loadSummary() {
    let priceSummary = JSON.parse(localStorage.getItem("orderSummary"));

    document.getElementById("subtotal-value").innerText = `$${priceSummary.itemsTotal.toFixed(2)}`;
    document.getElementById("shipping-value").innerText = `$${priceSummary.shipping.toFixed(2)}`;
    document.getElementById("tax-value").innerText = `$${priceSummary.tax.toFixed(2)}`;
    document.getElementById("estimated-total-value").innerText = `$${priceSummary.orderTotal.toFixed(2)}`;
}

document.addEventListener("DOMContentLoaded", () => {
    const purchaseButton = document.getElementById("purchase-button");
    loadSummary();
    purchaseButton.addEventListener("click", () => {
        completePayment();
    });
});