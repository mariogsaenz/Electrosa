/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

window.addEventListener("load", init);
// o window.addEventListener("DOMContentLoaded",init)
function init() {
    let languageSelector = document.getElementById('language-selector');
    languageSelector.addEventListener('change', function (e) {
        this.form.submit();
    });  
}


