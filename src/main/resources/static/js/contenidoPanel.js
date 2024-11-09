// Función para cargar fragmentos dinámicamente
function cargarContenido(url) {
  const contentDiv = document.getElementById('contenido-principal');
  if (contentDiv.dataset.current === url) {
    return; // Si el contenido ya está cargado, no se recarga
  }

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

// Función para mostrar el contenido principal del Dashboard sin recargar el contenedor
function mostrarDashboard() {
  const contentDiv = document.getElementById('contenido-principal');
  contentDiv.dataset.current = 'dashboard';
  contentDiv.innerHTML = `
        <h2 class="text-2xl font-semibold mb-4">Contenido Principal</h2>
        <p>Esta área es para el contenido principal del panel...</p>
    `;
}
