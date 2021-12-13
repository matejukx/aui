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
    fetchAndDisplayClient();
    fetchAndDisplayOrders();
    let id = getParameterByName("client");
    let h = document.getElementById("orders_add");
    h.appendChild(createButtonCell('Add order', () => {window.location=('../order_add/order_add.html?client=' + id)}));
});

/**
 * Fetches all clients and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayOrders() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayOrders(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/clients/' + getParameterByName('client') + '/orders', true);
    xhttp.send();
}

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
 * @param {{id: number, name: string}} order
 * @returns {HTMLTableRowElement}
 */
function createTableRow(order) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(order.description));
    tr.appendChild(createLinkCell('edit', '../order_edit/order_edit.html?client='
        + getParameterByName('client') + '&order=' + order.id));
    tr.appendChild(createLinkCell('view', '../order_view/order.html?order=' + order.id));
    tr.appendChild(createButtonCell('delete', () => deleteOrder(order)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} order to be deleted
 */
function deleteOrder(order) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayOrders();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/orders/' + order.id, true);
    xhttp.send();
}


/**
 * Fetches single client and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayClient() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayClient(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/clients/' + getParameterByName('client'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display client.
 *
 * @param {{login: string, name: string, surname:string}} client
 */
function displayClient(client) {
    setTextNode('name', client.name);
    setTextNode('surname', client.surname);
}
