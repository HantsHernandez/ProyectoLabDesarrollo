// Función para cargar fragmentos dinámicamente
function cargarContenido(url) {
  const contentDiv = document.getElementById('contenido-principal');
  /* if (contentDiv.dataset.current === url) {
    return; // Si el contenido ya está cargado, no se recarga
  } */

  fetch(url)
    .then((response) => response.text())
    .then((html) => {
      contentDiv.dataset.current = url; // Actualiza la URL actual en el dataset
      contentDiv.innerHTML = html;
    })
    .catch((error) => {
      console.error('Error al cargar el contenido:', error);
      contentDiv.innerHTML = '<p>Error al cargar el contenido.</p>';
    });
}

document.body.addEventListener('submit', function(event) {
    if (event.target.tagName === 'FORM') {
        event.preventDefault();
    }
});

function mostrarDashboard() {
  const contentDiv = document.getElementById('contenido-principal');
  contentDiv.dataset.current = 'dashboard';
  contentDiv.innerHTML = `
        <h2 class="text-2xl font-semibold mb-4">Contenido Principal</h2>
        <p>Esta área es para el contenido principal del panel...</p>
    `;
}

function guardar_registros(form){
    const formData = new FormData(document.getElementById(form));    
    const formDataObject = {};

    formData.forEach((value, key) => {
        formDataObject[key] = value;
    });

    fetch(document.getElementById(form).getAttribute('action'), {
        method: 'POST',
        headers: {
          'Accept': 'application/json, text/plain, */*',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formDataObject)
      })
    .then(response => response.text())
    .then(html => {
        const divResultado = document.getElementById('contenido-principal');
        divResultado.innerHTML = html;
    })
    .catch(error => console.error('Error en la petición:', error));
}