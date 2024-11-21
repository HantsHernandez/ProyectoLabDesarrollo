
document.body.addEventListener('click', function(event) {
    if (event.target.classList.contains('btn-restar')) {
        sumar_restar(event.target, false);
    }
    
    if (event.target.classList.contains('btn-sumar')) {
        sumar_restar(event.target, true);
    }
    
    if (event.target.classList.contains('btn-eliminar')) {
        eliminar_medicamento(event.target);
    }
    
    if (event.target.closest('.item-medicamento')) {
        seleccionar_medicamento(event.target);
    }
});

document.addEventListener('keyup', function(event) {
    if(event.target.classList.contains('cantidad-vendida')){
        let cantidad = event.target.value;
        if(cantidad != ''){
            cantidad = parseInt(cantidad);
            cantidad = cantidad >= 1 ? cantidad : 1;

            sumar_restar(event.target.closest('.card-content'), true, cantidad);
        }
    }

    if(event.target.classList.contains('buscador-medicamentos')){
        realizar_busqueda(event.target);
    }
});

function seleccionar_medicamento(item){
    let contenedor = item.closest('.item-medicamento');
    let medicamento = contenedor.getAttribute('medicamento');

    let existente = document.querySelectorAll(`#lstMedicamentos .registro-medicamento[value="${medicamento}"]`);

    if(existente.length > 0){
        let cardContent = existente[0].closest('.card-content');
        let cantidad = parseInt(cardContent.querySelector('.cantidad-vendida').value) + 1;

        sumar_restar(cardContent, true, cantidad);
    }else{
        let nuevoItem = document.querySelector('#itemVenta');
        let precio = contenedor.getAttribute('precio');

        nuevoItem.querySelector('.precio-unitario').value = precio;
        nuevoItem.querySelector('.precio-total').value = precio;
        nuevoItem.querySelector('.precio').innerHTML = precio;
        nuevoItem.querySelector('.precioUnitario').innerHTML = precio;
        nuevoItem.querySelector('.nombre-medicamento').innerHTML = contenedor.querySelector('.nombre-medicamento').innerHTML;
        nuevoItem.querySelector('.registro-medicamento').value = contenedor.getAttribute('medicamento');
        
        nuevoItem.querySelector('.card-content').setAttribute('disponibles', 
            contenedor.getAttribute('disponibles'));

        nuevoItem.querySelector('.img-compra').setAttribute('src', 
            contenedor.querySelector('.img-medicamento').getAttribute('src'));

        document.querySelector('#lstMedicamentos').insertAdjacentHTML('beforeend', nuevoItem.innerHTML);
    }

    calcular_total();
}

function sumar_restar(boton, sumar, cantidad = 0){
    let cardContent = boton.closest('.card-content');

    if(cantidad != 0){
        cardContent = boton;
    }

    let inputCantidad = cardContent.querySelector('.cantidad-vendida');
    let disponibles = parseInt(cardContent.getAttribute('disponibles'));

    let cantidadVendida = parseInt(inputCantidad.value);
    let precioUnitario = parseFloat(cardContent.querySelector('.precio-unitario').value)

    if(cantidad == 0){
        if(sumar){
            cantidadVendida++;
        }else if(cantidadVendida > 1){
            cantidadVendida--
        }
    }else{
        cantidadVendida = cantidad;
    }

    if(cantidadVendida > disponibles){
        cantidadVendida = disponibles;
    }

    inputCantidad.value = cantidadVendida;

    let subtotal = cantidadVendida * precioUnitario;
    cardContent.querySelector('.precio-total').value = subtotal;
    cardContent.querySelector('.precioUnitario').innerHTML = subtotal.toFixed(2);

    calcular_total();
}

function calcular_total(){
    let total = 0;
    
    document.querySelectorAll('#lstMedicamentos .precio-total').forEach(function(input) {
        total += parseFloat(input.value) || 0;
    });

    document.querySelector('#totalVenta').innerText = total.toFixed(2);
    document.querySelector('.subtotal-venta').value = total;
}

function eliminar_medicamento(boton){
    boton.closest('.card-content').replaceWith('');
    calcular_total();
}

function realizar_busqueda(buscador){
    let query = buscador.value.toLowerCase();
    let items = document.querySelectorAll('#listadoMedicamentos .item-medicamento');

    if (query == '' || query.length == 0) {
        items.forEach(item => {
            item.classList.remove('d-none');
        });
    }else{
        items.forEach(item => {
            const nombre = item.querySelector('.nombre-medicamento').textContent.toLowerCase();
            const descripcion = item.querySelector('.descripcion-medicamento').textContent.toLowerCase();
    
            if (nombre.includes(query) || descripcion.includes(query)) {
                item.classList.remove('d-none');
            } else {
                item.classList.add('d-none');
            }
        });
    }
}
