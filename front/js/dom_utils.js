
export function addHeader() {
    let header = document.getElementById("header_main");
    clearElementChildren(header);
    let h1 = document.createElement("h1");
    h1.appendChild(document.createTextNode("Labshop Frontend"))
    header.appendChild(h1);
}

export function addNavbar(){
    let navbar = document.getElementById("navbar");
    clearElementChildren("navbar");

    let ul = document.createElement('ul');
    navbar.appendChild(ul);

    let home = document.createElement('li');
    let linkHome = document.createElement('a');
    linkHome.setAttribute('href', '../index.html');
    linkHome.appendChild(document.createTextNode('Home'));
    home.appendChild(linkHome);

    let clients = document.createElement('li');
    let linkClients = document.createElement('a');
    linkClients.setAttribute('href', '../clients_view/clients.html');
    linkClients.appendChild(document.createTextNode('Clients'));
    clients.appendChild(linkClients);


    ul.appendChild(home);
    ul.appendChild(clients);
}

export function addFooter() {
    let footer = document.getElementById("footer_main");
    clearElementChildren(footer);
    let p = document.createElement("p");
    p.appendChild(document.createTextNode("Copyright 2021, Michał Matejuk"))
    footer.appendChild(p);
}

/**
 * Clears all children of the provided element
 *
 * @param {HTMLElement} element parent element
 */
 export function clearElementChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

/**
 * Create new table cell with button with assigned action.
 *
 * @param {string} text text to be displayed on button
 * @param {function} action function to be executed on button click
 * @returns {HTMLTableDataCellElement} table cell with action button
 */
export function createButtonCell(text, action) {
    const td = document.createElement('td');
    const button = document.createElement('button');
    button.appendChild(document.createTextNode(text));
    button.classList.add('ui-control', 'ui-button');
    td.appendChild(button);
    button.addEventListener('click', action);
    return td;
}

/**
 * Create new table cell with hyperlink.
 *
 * @param {string} text text to be displayed on link
 * @param {string} url link url
 */
export function createLinkCell(text, url) {
    const td = document.createElement('td');
    const a = document.createElement('a');
    a.appendChild(document.createTextNode(text));
    a.href = url;
    td.appendChild(a);
    return td;
}

/**
 * Create new table cell with text.
 *
 * @param {string} text text to be displayed
 */
export function createTextCell(text) {
    const td = document.createElement('td');
    td.appendChild(document.createTextNode(text));
    return td;
}

/**
 * Returns value for query param.
 *
 * @param {string} name name of the query param
 * @returns {string} query param value
 */
export function getParameterByName(name) {
    return new URLSearchParams(window.location.search).get(name);
}

/**
 * Removes all children for selected element and adds new text node.
 *
 * @param id element id
 * @param {string} text text used to create text node
 */
export function setTextNode(id, text) {
    let element = document.getElementById(id);
    clearElementChildren(element);
    element.appendChild(document.createTextNode(text));
}
