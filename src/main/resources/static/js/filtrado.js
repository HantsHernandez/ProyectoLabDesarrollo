/*
// Seleccionamos los elementos
const filtroTexto = document.getElementById("filtro-texto");
const filtroSelect = document.getElementById("filtro-select");

// Función para ejecutar el filtrado
function filtrarDatos() {
    const texto = filtroTexto.value;
    const criterio = filtroSelect.value;

    // Enviar la solicitud AJAX al servidor
    fetch(`/filtrar?criterio=${criterio}&texto=${texto}`)
        .then(response => response.json())
        .then(data => {

            console.log(data);

        })
        .catch(error => {
            console.error("Error al filtrar los datos:", error);
        });
}

// Agregar eventos para detectar cuando se cambia el texto o el select
filtroTexto.addEventListener("input", filtrarDatos); // Al escribir en el input
filtroSelect.addEventListener("change", filtrarDatos); // Al cambiar la selección

*/