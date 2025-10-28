/** Cart information stored in local storage. */
let cart = JSON.parse(localStorage.getItem("cart")) || [];
/** The endpoint to retrieve product data */
let productEndpoint = "/product"; // Placeholder until controllers are made.

/**
 * Enables addition of products to the cart.
 * @param itemNo {number} The item number of the product to be added.
 */
function addToCart(itemNo) {
    cart.push(itemNo);
    localStorage.setItem("cart", JSON.stringify(cart));
}

/**
 * Enables removal of products from the cart.
 * @param itemNo {number} The item number of the product.
 * @returns {boolean} Returns false in order to disable form submissions.
 */
function removeFromCart(itemNo) {
    console.log("Entered remove");
    if (cart.length === 0) {
        localStorage.removeItem("cart");
        cart = [];
        return false;
    }
    let itemIdx = cart.findIndex((value, index, array) => {
        return value == itemNo;
    })

    if (itemIdx === -1) {
        return false;
    }
    console.log("itemIdx: ", itemIdx);
    cart.splice(itemIdx, 1);
    console.log("Cart after removal: ", cart);
    console.log(cart);
    localStorage.setItem("cart", JSON.stringify(cart));
    displayCartWrap();
    return false;
}

/**
 * Retrieves product information from the server using the item number.
 * @param itemNo {number} The item number of the product.
 * @returns {Promise<any>} A promise of the retrieved product.
 */
function getProduct(itemNo) {
    console.log(`Getting product with id: ${itemNo}`);
    let path = `${productEndpoint}/${itemNo}`; // Placeholder until controllers made.
    return fetch(path).then(response => response.json());
}

/**
 * Retrieves all the products corresponding to the item numbers in a given list.
 * @param itemNumbers {Array} The item numbers of the products to be retrieved.
 * @returns {Promise<Awaited<unknown>[]>} A promise of the retrieved products.
 */
async function getProducts(itemNumbers) {
    return Promise.all(itemNumbers.map(id => getProduct(id)));
}

/**
 * Format the product objects returned into the appropriate HTML model.
 * @param products {Array} The product objects to format.
 * @returns {HTMLDivElement} The formatted HTML model.
 */
function formatToHTML(products) {
    let items, item, itemImage, itemDetails, itemTitle, itemAuthor, itemPublishingYr, itemNoPages, itemPrice,
        itemRemoveButtonBox, itemRemoveButton;
    let num;
    items = document.createElement("div");
    items.className = "items";
    for (let i = 0; i < cart.length; i++) {
        item = document.createElement("div");
        item.id = products[i].id;
        item.className = "item";

        // Item image
        itemImage = document.createElement("div");
        itemImage.className = "item-picture";

        // Item details
        itemDetails = document.createElement("div");
        itemDetails.className = "item-details";

        itemTitle = document.createElement("div");
        itemTitle.className = "item-title";
        itemTitle.textContent = products[i].title;

        itemAuthor = document.createElement("div");
        itemAuthor.className = "item-author";
        itemAuthor.textContent = products[i].author;

        itemPublishingYr = document.createElement("div");
        itemPublishingYr.className = "item-publishing-year";
        itemPublishingYr.textContent = `Publishing year: ${products[i].publishedYear}`;

        itemNoPages = document.createElement("div");
        itemNoPages.className = "item-no-pages";
        itemNoPages.textContent = `No. of pages: ${products[i].numOfPages}`;

        itemPrice = document.createElement("div");
        itemPrice.className = "item-price";
        num = (products[i].price);
        itemPrice.textContent = `$${num.toFixed(2)}`;

        itemDetails.append(itemTitle, itemAuthor, itemPublishingYr, itemNoPages, itemPrice);

        // Button
        itemRemoveButtonBox = document.createElement("div");
        itemRemoveButtonBox.className = "item-remove-button-box";

        itemRemoveButton = document.createElement("button");
        itemRemoveButton.className = "item-remove-button";
        itemRemoveButton.addEventListener("click", () => {
            removeFromCart(products[i].id);
        });
        itemRemoveButton.type = "button"; // This ensures that the button does not default to submitting to the form
        itemRemoveButton.textContent = "Remove from cart";

        itemRemoveButtonBox.appendChild(itemRemoveButton);

        item.append(itemImage, itemDetails, itemRemoveButtonBox);
        items.appendChild(item);
    }
    return items;
}

/**
 * Renders the cart using the cart information from the local storage.
 * @returns {Promise<boolean>} A promise of the resulting modification of the DOM.
 */
async function displayCart() {
    let cartList = document.getElementById("cart-items");

    let products = await getProducts(cart);
    let cartItemsNo = document.getElementById("cart-items-no");
    cartItemsNo.innerText = `(${products.length})`;
    let items = formatToHTML(products);
    cartList.innerHTML = "";
    cartList.append(items); // Cannot use [cartList.innerHTML = items.outerHTML] because DOM loses the event listeners or added functions since html has to be parsed again.

    let productTotal, shipping, tax, estimatedTotal;
    productTotal = 0;
    for (let i = 0; i < products.length; i++) {
        productTotal += products[i].price;
    }
    shipping = productTotal > 0? 15 : 0;
    tax = productTotal * 0.13;
    estimatedTotal = productTotal + shipping + tax;

    document.getElementById("subtotal-value").innerText = `$${productTotal.toFixed(2)}`;
    document.getElementById("shipping-value").innerText = `$${shipping.toFixed(2)}`;
    document.getElementById("tax-value").innerText = `$${tax.toFixed(2)}`;
    document.getElementById("estimated-total-value").innerText = `$${estimatedTotal.toFixed(2)}`;
    return false;
}

/**
 * Triggered after the purchase button is pressed.
 * @param event {object} The event from which the function was triggered.
 */
function purchase(event) {
    event.preventDefault();
    localStorage.removeItem("cart");
    cart = [];
    displayCart().then(() => {});
}

/**
 * A wrapper to render the cart without issues of async.
 */
function displayCartWrap() {
    displayCart().then(() => {});
}

/**
 * Ensures that the cart is fully displayed upon load.
 */
document.addEventListener("DOMContentLoaded", () => {
    displayCartWrap();
})
