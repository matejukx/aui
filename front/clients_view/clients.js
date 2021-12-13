import {clearElementChildren, createLinkCell, createButtonCell, createTextCell,addFooter, addHeader, addNavbar} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    addHeader();
    addNavbar();
    addFooter();
    fetchAndDisplayClients();
    let h = document.getElementById("clients_header");
    h.appendChild(createButtonCell('Add client', () => {window.location='../client_add/client_add.html'}));
});

/**
 * Fetches all Clients and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayClients() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayClients(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/clients', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display Clients.
 *
 * @param {{Clients: string[]}} clients
 */
function displayClients(clients) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    clients.clients.forEach(client => {
        tableBody.appendChild(createTableRow(client));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} client
 * @returns {HTMLTableRowElement}
 */
function createTableRow(client) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(client.name));
    tr.appendChild(createLinkCell('view', '../client_view/client.html?client=' + client.id));
    tr.appendChild(createLinkCell('edit', '../client_edit/client_edit.html?client=' + client.id));
    tr.appendChild(createButtonCell('delete', () => deleteClient(client)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } client to be deleted
 */
function deleteClient(client) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayClients();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/clients/' + client.id, true);
    xhttp.send();
}
