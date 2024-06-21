/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function() {
    var linksMostrar = document.querySelectorAll(".mostrar-contraseña");

    linksMostrar.forEach(function(link) {
    
        link.addEventListener("click", function(event) {
            
            event.preventDefault();

            var contraseñaInputId = this.dataset.inputid;
            var contraseñaInput = document.getElementById(contraseñaInputId);
            var icono = this.querySelector('ion-icon');

            if (contraseñaInput.type === "password") {
                contraseñaInput.type = "text";
                icono.name = "eye-off"; //No funciona
                this.textContent = "Ocultar contraseña";
            } else {
                contraseñaInput.type = "password";
                icono.name = "eye"; //No funciona
                this.textContent = "Mostrar contraseña";
            }
        });
    });
});


