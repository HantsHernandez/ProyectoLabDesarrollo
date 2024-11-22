function abrirModal(endpoint, id) {
    console.log(`Abriendo modal para ${endpoint} con ID ${id}`);

    const modal = document.getElementById('modal');
    console.log(modal);

    modal.classList.remove('hidden');
    modal.classList.add('flex');

    fetch(`/${endpoint}/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("No se pudo obtener la información.");
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('modal-title').textContent = `Detalles de ${capitalize(endpoint)}`;

            let contenido = "";
            for (const key in data) {
                if (data.hasOwnProperty(key)) {
                    contenido += `<p><strong>${capitalize(key)}:</strong> ${data[key]}</p>`;
                }
            }

            document.getElementById('modal-content').innerHTML = contenido;

            const modal = document.getElementById('modal');
            modal.classList.remove('hidden');
            modal.classList.add('flex');
        })
        .catch(error => {
            console.error(error);
            alert("Hubo un error al cargar los datos.");
        });
}

function closeModal() {
    const modal = document.getElementById('modal');
    modal.classList.add('hidden');
    modal.classList.remove('flex');
}

function capitalize(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}
