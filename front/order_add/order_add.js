import {getParameterByName,addFooter, addHeader, addNavbar} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';


window.addEventListener('load', () => {
    addHeader();
    addNavbar();
    addFooter();
    const infoForm = document.getElementById('infoForm');
    let clientId = getParameterByName("client")
    infoForm.addEventListener('submit', event => createInfoAction(event, clientId));
});

/**
 * Action event handled for creating order.
 * @param {Event} event dom event
 * @param {uuid} clientId client id
 */
function createInfoAction(event, clientId) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            window.location=('../client_view/client.html?client=' + clientId)
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/orders/', true);

    const request = {
        'clientId' : clientId,
        'description': document.getElementById('description').value,
        'price': parseInt(document.getElementById('price').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}


