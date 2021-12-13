import {addFooter, addHeader, clearElementChildren} from './js/dom_utils.js';

export function addNavbar(){
    let navbar = document.getElementById("navbar");
    clearElementChildren("navbar");

    let ul = document.createElement('ul');
    navbar.appendChild(ul);

    let home = document.createElement('li');
    let linkHome = document.createElement('a');
    linkHome.setAttribute('href', 'index.html');
    linkHome.appendChild(document.createTextNode('Home'));
    home.appendChild(linkHome);

    let clients = document.createElement('li');
    let linkClients = document.createElement('a');
    linkClients.setAttribute('href', 'clients_view/clients.html');
    linkClients.appendChild(document.createTextNode('Clients'));
    clients.appendChild(linkClients);


    ul.appendChild(home);
    ul.appendChild(clients);
}

window.addEventListener('load', () => {
    addHeader();
    addNavbar();
    addFooter();
});
