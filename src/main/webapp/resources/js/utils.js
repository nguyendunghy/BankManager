/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function setIconSendEmailOnGrid(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c fa fa-envelope color-red");
}
function resetIconSendEmailOnGrid(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c fa fa-envelope color-black");
}
function setIconEditOnGrid(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c fa icon-edit color-red");
}
function resetIconEditOnGrid(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c fa icon-edit color-black");
}
function setIconDeleteOnGrid(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c fa icon-trash color-red");
}
function resetIconDeleteOnGrid(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c fa icon-trash color-black");
}
function setIconLockOnGrid(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c fa fa-lock color-red");
}
function resetIconLockOnGrid(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c fa fa-lock color-black");
}
function setIconUnlockOnGrid(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c fa fa-unlock color-red");
}
function resetIconUnlockOnGrid(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c fa fa-unlock color-black");
}
function setIconSeeDetailOnGrid(controlId) {
//    document.getElementById(controlId).childNodes[0].setAttribute("class", "fa fa-eye color-red");
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c  ui-icon fa fa-eye color-red");
//    document.getElementById(controlId).childNodes[0].setAttribute("style", "margin:0 auto;");
}
function resetIconSeeDetailOnGrid(controlId) {
//    document.getElementById(controlId).childNodes[0].setAttribute("class", "fa fa-eye color-black");
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c  ui-icon fa fa-eye color-black");
//    document.getElementById(controlId).childNodes[0].setAttribute("style", "margin:0 auto;");
}
function setIconFaLegalOnGrid(controlId) {
//    document.getElementById(controlId).childNodes[0].setAttribute("class", "fa fa-eye color-red");
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c  ui-icon fa fa-legal color-red");
//    document.getElementById(controlId).childNodes[0].setAttribute("style", "margin:0 auto;");
}
function resetIconFaLegalOnGrid(controlId) {
//    document.getElementById(controlId).childNodes[0].setAttribute("class", "fa fa-eye color-black");
    document.getElementById(controlId).childNodes[0].setAttribute("class", "ui-button-icon-left ui-icon ui-c  ui-icon fa fa-legal color-black");
//    document.getElementById(controlId).childNodes[0].setAttribute("style", "margin:0 auto;");
}

function setIconDownload(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "fa fa-download color-red");
    document.getElementById(controlId).childNodes[0].setAttribute("styleClass", "ui-icon fa fa-download color-red");
    document.getElementById(controlId).childNodes[0].setAttribute("style", "margin:0 auto;");
}
function resetIconDownload(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "fa fa-download color-blue-label");
    document.getElementById(controlId).childNodes[0].setAttribute("styleClass", "ui-icon fa fa-download color-blue-label");
    document.getElementById(controlId).childNodes[0].setAttribute("style", "margin:0 auto;");
}
function setIconActive(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "fa fa-play-circle color-red");
    document.getElementById(controlId).childNodes[0].setAttribute("style", "margin:0 auto;font-size: 18px");
}
function resetIconActive(controlId) {
    document.getElementById(controlId).childNodes[0].setAttribute("class", "fa fa-play-circle color-blue-label");
    document.getElementById(controlId).childNodes[0].setAttribute("style", "margin:0 auto;font-size: 18px");
}
//    document.getElementById(controlId).childNodes[0].setAttribute("style", "margin:0 auto;");
