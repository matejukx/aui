import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode,
    addFooter, 
    addHeader, 
    addNavbar
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    addHeader();
    addNavbar();
    addFooter();
    fetchAndDisplayOrder();
});

/**
 * Updates the DOM tree in order to display orders.
 *
 * @param {{orders: {id: number, name:string}[]}} orders
 */
function displayOrders(orders) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    orders.orders.forEach(order => {
        tableBody.appendChild(createTableRow(order));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, description: string, price:int}} order
 * @returns {HTMLTableRowElement}
 */
function createTableRow(order) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(order.description));
    tr.appendChild(createLinkCell('edit', '../order_edit/order_edit.html?order='
        + getParameterByName('order') + '&order=' + order.id));
    tr.appendChild(createButtonCell('delete', () => deleteOrder(order)));
    return tr;
}

/**
 * Fetches single order and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayOrder() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayOrder(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/orders/' + getParameterByName('order'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display order.
 *
 * @param {{id: number, description: string, price:int}} order
 */
function displayOrder(order) {
    setTextNode('description', order.description);
    setTextNode('price', order.price);
}
