import {addFooter, addHeader, addNavbar} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    addHeader();
    addNavbar();
    addFooter();
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => createInfoAction(event));
});

/**
 * Action event handled for creating client.
 * @param {Event} event dom event
 */
function createInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            window.location='../clients_view/clients.html'
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/clients/', true);

    const request = {
        'name': document.getElementById('name').value,
        'surname': document.getElementById('surname').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}


