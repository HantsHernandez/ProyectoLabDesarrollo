// Función para cargar fragmentos dinámicamente
function cargarContenido(url) {
  console.log(url);
  const contentDiv = document.getElementById('contenido-principal');

  fetch(url)
    .then((response) => response.text())
    .then((html) => {
      contentDiv.dataset.current = url;
      contentDiv.innerHTML = html;
       if(document.getElementById('tipo-alerta')){
           const tipoAlerta = document.getElementById('tipo-alerta');
           console.log("VALOR de alerta: " + tipoAlerta.value);

          if (tipoAlerta.value === 'eliminar') {
             const mensajeInput = document.getElementById('mensaje-alerta');
             const mensaje = mensajeInput.value;
             console.log("VALOR: " + mensaje);
             var alertaDiv = document.getElementById('alerta3');
                   var alertaMensaje = document.getElementById('alerta-mensaje3');
                   console.log("INGRESO");
                   alertaMensaje.textContent = mensaje;
                   alertaDiv.style.display = 'block';

                   setTimeout(function() {
                       alertaDiv.style.display = 'none';
                   }, 3000);

          }
       }
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
        <img src="../img/logoFarmaciaPanel.svg" alt="logoFarmacia" srcset="" width="312px" height="280px">
    `;
}
/*
document.body.addEventListener('submit', function(event) {
    if (event.target.tagName === 'FORM') {
        event.preventDefault();
    }
});
*/
function enviarBusqueda() {
    const form = document.getElementById("FormFiltro");
    const formData = new FormData(form);
    const formDataObject = {};
    formData.forEach((value, key) => {
        formDataObject[key] = value;
    });
        const actionUrl = form.getAttribute('action');
        console.log("URL de acción: ", actionUrl);
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const params = new URLSearchParams(formDataObject);
    const url = `${actionUrl}?${params.toString()}&_csrf=${csrfToken}`;
    fetch(url, {
        method: "GET",
        headers: {
            "Accept": "application/json",
            'X-CSRF-Token': csrfToken
        }
    })
    .then(response => response.text())
    .then(html => {
        const divResultado = document.getElementById('contenido-principal');
                divResultado.innerHTML = html;
    })
    .catch(error => {
        console.error("Error al enviar el formulario: ", error);
    });
}

function guardar_registros(form){
    const formData = new FormData(document.getElementById(form));
    const formDataObject = {};

    formData.forEach((value, key) => {
        formDataObject[key] = value;
    });

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

    fetch(document.getElementById(form).getAttribute('action'), {
        method: 'POST',
        headers: {
          'Accept': 'application/json, text/plain, */*',
          'X-CSRF-Token': csrfToken
        },
        body: formData
      })
    .then(response => response.text())
    .then(html => {
        const divResultado = document.getElementById('contenido-principal');
        divResultado.innerHTML = html;


    if(document.getElementById('mensaje-alerta')){
            const tipoAlerta = document.getElementById('tipo-alerta');
            console.log("VALOR de alerta: " + tipoAlerta.value);
            if (tipoAlerta.value === 'agregar') {
                const mensajeInput = document.getElementById('mensaje-alerta');
                const mensaje = mensajeInput.value;
                console.log("VALOR: " + mensaje);
                var alertaDiv = document.getElementById('alerta1');
                      var alertaMensaje = document.getElementById('alerta-mensaje1');
                      console.log("INGRESO");
                      alertaMensaje.textContent = mensaje;
                      alertaDiv.style.display = 'block';

                      setTimeout(function() {
                          alertaDiv.style.display = 'none';
                      }, 3000);
            } else if (tipoAlerta.value === 'actualizar') {
                    const mensajeInput = document.getElementById('mensaje-alerta');
                    const mensaje = mensajeInput.value;
                    console.log("VALOR: " + mensaje);
                    var alertaDiv = document.getElementById('alerta2');
                          var alertaMensaje = document.getElementById('alerta-mensaje2');
                          console.log("INGRESO");
                          alertaMensaje.textContent = mensaje;
                          alertaDiv.style.display = 'block';

                          setTimeout(function() {
                              alertaDiv.style.display = 'none';
                          }, 3000);

            }
    }


    /*

        if (document.getElementById('mensaje-alerta')) {
        const mensajeInput = document.getElementById('mensaje-alerta');
        const mensaje = mensajeInput.value;
        console.log("VALOR: " + mensaje);
        var alertaDiv = document.getElementById('alerta');
              var alertaMensaje = document.getElementById('alerta-mensaje');
              console.log("INGRESO");
              // Asignar el mensaje y mostrar la alerta
              alertaMensaje.textContent = mensaje;
              alertaDiv.style.display = 'block';

              // Ocultar la alerta después de 3 segundos (3000 ms)
              setTimeout(function() {
                  alertaDiv.style.display = 'none';
              }, 3000);
      }
        console.log("INGRESOOOOOOOOO");
*/
    })
    .catch(error => console.error('Error en la petición:', error));
}

function actualizarVistaPrevia(input) {
    console.log("Intentando cargar nueva imagen...");

    const preview = document.getElementById('previewImagen');

    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            preview.src = e.target.result;
        };

        reader.readAsDataURL(input.files[0]); // Lee el archivo seleccionado
    } else {
        preview.src = '/img/imgEmpPredeterminado.jpg'; // Restaura la imagen predeterminada
    }
}



