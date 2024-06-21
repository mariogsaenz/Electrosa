/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function() {
    
    let total = 0;

    const items = document.querySelectorAll('.card-layout__item.clg-item');

    items.forEach(item => {
        const cantidad = parseInt(item.querySelector('.cantidad').textContent);
        const precio = parseFloat(item.querySelector('.precio').textContent);

        console.log(`Cantidad: ${cantidad}, Precio por unidad: ${precio}`);

        total += cantidad * precio;
    });

    document.getElementById('totalPrecio').textContent = total.toFixed(2);
});
