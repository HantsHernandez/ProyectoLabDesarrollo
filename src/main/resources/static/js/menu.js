// Selecciona todos los elementos que activan el desplegable
const dropdownToggles = document.querySelectorAll('.sidebar-dropdown-toggle');

dropdownToggles.forEach(function (toggle) {
  toggle.addEventListener('click', function (e) {
    e.preventDefault();
    // Obtiene el submenú asociado al elemento clicado
    const submenu = this.nextElementSibling;

    // Alterna la clase 'hidden' para mostrar u ocultar el submenú
    submenu.classList.toggle('hidden');

    // Alterna la rotación del icono de flecha para indicar expansión
    const arrowIcon = this.querySelector('.ri-arrow-right-s-line');
    arrowIcon.classList.toggle('rotate-90');
  });
});
