/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function() {
    const wishlistButtons = document.querySelectorAll('.wishlist-add');
    actualizarContadorFavoritos();
    wishlistButtons.forEach(button => {
        button.addEventListener('click', function() {
            const id = this.getAttribute('id');
            let favoritos = JSON.parse(localStorage.getItem('wishlist')) || [];

            if (!favoritos.includes(id)) {
                favoritos.push(id);
                localStorage.setItem('wishlist', JSON.stringify(favoritos));
                cambiarIcono();
                alert('Artículo agregado a la lista de deseos.');               
                actualizarContadorFavoritos();
            } else {
                alert('Este artículo ya está en la lista de deseos.');
            }
        });
    });
    actualizarContadorFavoritos();
});

function actualizarContadorFavoritos() {
    var favoritosCount = document.getElementById('favoritos-count');
    let favoritos = JSON.parse(localStorage.getItem('wishlist')) || [];
    var numeroFavoritos = favoritos.length;
    favoritosCount.textContent = `${numeroFavoritos}`;
}

window.addEventListener("load", verFavoritos);

function verFavoritos() {
    let favoritos = JSON.parse(localStorage.getItem('wishlist')) || [];
    const listaFavoritos = document.getElementById('listaFavs');
    favoritos.forEach(favorito => {
        const listItem = document.createElement('li');
        const divItem = document.createElement('div');
        divItem.textContent = ' Artículo: ' + favorito;
        listItem.appendChild(divItem);
        listaFavoritos.appendChild(listItem);
    });
}

//La idea del siguiente método es que al añadir un articulo a favoritos cambie el icono para que se vea que está añadido, pero no se porque no funciona
function cambiarIcono() {
    var icon = document.getElementById('iconoCora');
    icon.setAttribute('name', 'heart');
}