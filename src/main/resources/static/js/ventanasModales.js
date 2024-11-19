// Función para abrir el modal y cargar datos
function abrirModal() {
  const titulo = `Detalles del Empleado`;
  const contenido = `
        ID: 1
        Nombre: Daniel Alexander Murcia Carpio
        DUI: 000000-0
        Teléfono: 6800-1200
        Cargo: Cajero/a
        Dirección: Colonia Buenos Aires #2
    `;

  document.getElementById('modal-title').textContent = titulo;
  document.getElementById('modal-content').textContent = contenido;

  // Abrir Modal
  const modal = document.getElementById('modal');
  modal.classList.remove('hidden');
  modal.classList.add('flex');
}

// Función para cerrar el modal
function closeModal() {
  const modal = document.getElementById('modal');
  modal.classList.add('hidden');
  modal.classList.remove('flex');
}
