/** Cart information stored in local storage. */
let cart = JSON.parse(localStorage.getItem("cart")) || [];
/** The endpoint to retrieve product data */
let productEndpoint = "/product";

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
 * @param event {object} The event from which the function was invoked.
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
    console.log("Getting product with id: " + itemNo);
    let path = productEndpoint + "/" + itemNo;
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
 * Renders the cart using the cart information from the local storage.
 * @returns {Promise<boolean>} A promise of the resulting modification of the DOM.
 */
async function displayCart() {
    let cartList = document.getElementById("cart-items");

    let items, item, itemImage, itemTitle, itemAuthor, itemPublishingYr, itemNoPages, itemPrice, itemRemoveButton;
    items = document.createElement("div");
    items.className = "items";

    document.getElementById("cart-items").innerHTML = "hello world!";
    let products = await getProducts(cart);

    document.getElementById("cart-items").innerHTML = "Second hello world!";
    for (let i = 0; i < cart.length; i++) {
        item = document.createElement("div");
        item.id = products[i].itemNo;
        item.className = "item";

        itemImage = document.createElement("div");
        itemImage.className = "item-image";

        itemTitle = document.createElement("div");
        itemTitle.className = "item-title";
        itemTitle.textContent = "Title: " + products[i].title;

        itemAuthor = document.createElement("div");
        itemAuthor.className = "item-author";
        itemAuthor.textContent = "Author: " + products[i].author;

        itemPublishingYr = document.createElement("div");
        itemPublishingYr.className = "item-publishing-year";
        itemPublishingYr.textContent = "Publishing year: " + products[i].publishingYear;

        itemNoPages = document.createElement("div");
        itemNoPages.className = "item-no-pages";
        itemNoPages.textContent = "No. of pages: " + products[i].noPages;

        itemPrice = document.createElement("div");
        itemPrice.className = "item-price";
        itemPrice.textContent = "$" + (products[i].price/100);

        itemRemoveButton = document.createElement("button");
        itemRemoveButton.className = "item-remove-button";
        itemRemoveButton.addEventListener("click", () => {
            removeFromCart(products[i].itemNo);
        });
        itemRemoveButton.type = "button"; // This ensures that the button does not default to submitting to the form
        itemRemoveButton.textContent = "Remove from cart"

        item.append(itemImage, itemTitle, itemAuthor, itemPublishingYr, itemNoPages, itemPrice, itemRemoveButton);
        items.appendChild(item);
        console.log(item.outerHTML);
    }
    cartList.innerHTML = "";
    cartList.append(items); // Cannot use [cartList.innerHTML = items.outerHTML] because DOM loses the event listeners or added functions since html has to be parsed again.
    return false;
}

/**
 * Triggered after the purchase button is pressed.
 * @param event {object} The event from which the function was triggered.
 */
function purchase(event) {
    event.preventDefault();
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
