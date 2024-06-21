/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */




document.addEventListener("DOMContentLoaded", function() {

    const toast = document.getElementById('notification-toast');
    const toastTitle = document.getElementById('toast-title');
    const toastUrl = toast.getAttribute('data-url');
    
    function fetchLastArticle() {
        fetch(toastUrl)
            .then(response => response.json())
            .then(data => {
                if (data && data.nombre) {
                    toastTitle.textContent = data.nombre;
                    toast.style.animationPlayState = 'running';
                }
            })
            .catch(error => console.error('Error fetching the article:', error));
    }

    setInterval(fetchLastArticle, 10000);

    fetchLastArticle();

    const closeButton = document.querySelector('[data-toast-close]');
    closeButton.addEventListener('click', function() {
        toast.classList.add('closed');
    });
});

