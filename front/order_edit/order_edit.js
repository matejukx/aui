import {getParameterByName,addFooter, addHeader, addNavbar} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';


window.addEventListener('load', () => {
    addHeader();
    addNavbar();
    addFooter();
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
    fetchAndDisplayOrder();
});

/**
 * Fetches current client's orders and updates edit form.
 */
function fetchAndDisplayOrder() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() +  '/api/orders/'
        + getParameterByName('order'), true);
    xhttp.send();
}

/**
 * Action event handled for updating order info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayOrder();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/orders/'
        + getParameterByName('order'), true);

    const request = {
        'description': document.getElementById('description').value,
        'price': parseInt(document.getElementById('price').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}


