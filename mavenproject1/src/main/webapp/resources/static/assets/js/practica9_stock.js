
document.addEventListener("DOMContentLoaded", function() {
    
    var articuloId = getArticuloIdFromUrl();
    
    var baseUrl = window.location.origin + window.location.pathname.split('/').slice(0, -2).join('/');
    var url = `${baseUrl}/api/articulo/${articuloId}/vendido/hoy`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok " + response.statusText);
            }
            return response.text();
        })
        .then(responseText => renderSoldToday(responseText))
        .catch(err => console.error("Fetch error: ", err));
});

function getArticuloIdFromUrl() {
        var pathArray = window.location.pathname.split('/');
        return pathArray[pathArray.length - 2];
}
    
function renderSoldToday(responseText) {
        var soldTodayElement = document.getElementById("soldToday");
        soldTodayElement.innerText = responseText;
}