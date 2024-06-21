/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", function() {   
            
            var botonOferta = document.getElementById('botonOferta');
            
            var precioOriginal = document.getElementById('precioOriginal');
            var precioDescuento = document.getElementById('precioDescuento');
            
            var mensajeOferta = document.getElementById('mensajeOferta');
            var cantComprada= document.getElementById('cantComprada');
            var cantPagada= document.getElementById('cantPagada');
            
            var ofertaFlash = document.getElementById('oferta-flash');
            var mensajeTiempo = document.getElementById('mensajeTiempo');   
            var mensajeDIA = document.getElementById('mensajeDIA'); 
            var mensajeHORA = document.getElementById('mensajeHORA'); 
            var mensajeMIN = document.getElementById('mensajeMIN'); 
            var mensajeSEG = document.getElementById('mensajeSEG'); 
            var dias = document.getElementById('dias');
            var horas = document.getElementById('horas');
            var min = document.getElementById('min');
            var seg = document.getElementById('seg');
            
            botonOferta.addEventListener('click', function() {
                var articuloId = getArticuloIdFromUrl();
                
                var baseUrl = window.location.origin + window.location.pathname.split('/').slice(0, -2).join('/');
                var url = `${baseUrl}/api/articulo/${articuloId}/oferta`;
                

                fetch(url)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Network response was not ok " + response.statusText);
                        }
                        return response.json();
                    })
                    .then(oferta => {
                        var precioOriginalValue = oferta.precioOriginal;
                        var precioOfertadoValue = oferta.precioOfertado;
                        
                        var cantCompradaValue = oferta.cantidadComprada;
                        var cantPagadaValue = oferta.cantidadPagada;
                        
                        var diasValues = oferta.vigenciaDias;
                        var horasValues = oferta.vigenciaHoras;
                        var minValues = oferta.vigenciaMinutos;
                        var segValues = oferta.vigenciaSegundos;

                        ofertaFlash.style.display = 'inline'; 

                        precioOriginal.textContent = `${precioOriginalValue.toFixed(2)}€`;
                        precioDescuento.textContent = `${precioOfertadoValue.toFixed(2)}€`;
                        precioDescuento.style.display = 'inline'; 
                        
                        cantComprada.textContent = `${cantCompradaValue}`;
                        cantPagada.textContent = `${cantPagadaValue}`;
                        mensajeOferta.style.display = 'inline';
                        
                        dias.textContent = `${diasValues}`;
                        horas.textContent = `${horasValues}`;
                        min.textContent = `${minValues}`;
                        seg.textContent = `${segValues}`;
                        ofertaFlash.style.display = 'inline';
                        mensajeTiempo.style.display = 'inline';
                        mensajeDIA.style.display = 'inline';
                        mensajeHORA.style.display = 'inline';
                        mensajeMIN.style.display = 'inline';
                        mensajeSEG.style.display = 'inline';
                    })
                    .catch(err => console.error("Fetch error: ", err));
                
            });
});

function getArticuloIdFromUrl() {
        var pathArray = window.location.pathname.split('/');
        return pathArray[pathArray.length - 1];
}
        

       

