$(document).ready(function() {
  cargarEmpleados();
});
async function cargarEmpleados(){
    const request = await fetch('api/v1/empleados', {
            method: 'GET',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            }
    });
    const empleados = await request.json();

    let listadoHtml = '';
            //para agragar usuarios de json
            let cont = 0;
          for(let empleado of empleados){
                cont=cont+1;
                var nombre = empleado.persona.nombre +" "+ empleado.persona.apellido;
                var rol = empleado.rol === 1 ? 'Empleado A1' : 'Empleado A2';
                let botonEditar = '<a href="#" class="btn btn-primary btn-circle btn-sm" onclick="mostrarEmpleado(' + empleado.idEmpleado + ')"><i class="fas fa-exclamation-triangle"></i></a>';
                let botonEliminar = '<a href="#" class="btn btn-primary btn-circle btn-sm" onclick="eliminarEmpleado('+empleado.idEmpleado+')"><i class="fas fa-trash"></i></a>';
                let empleadoHtml =  '<tr><td>'+cont+'</td><td>'+nombre+'</td><td>'+empleado.persona.email+'</td><td>'+empleado.celular+'</td><td>'+rol+'</td><td>'+botonEditar+'</td><td>'+botonEliminar+'</td></tr>';
                listadoHtml+=empleadoHtml;
          }
          document.querySelector('#listaEmpleados tbody').outerHTML=listadoHtml;
}
function agregarNuevoEmpleado(){
    limpiarModalEmpleado()
    $('#myModalNuevoEmpleado').modal('show');
}
async function mostrarEmpleado(id){
    $('#myModalNuevoEmpleado').modal('show');
    const request = await fetch('api/v1/empleados/'+id, {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
    });
    const empleado = await request.json();
    document.getElementById('txtNombre').value = empleado.persona.nombre;
    document.getElementById('txtApellido').value = empleado.persona.apellido;
    document.getElementById('txtCorreo').value = empleado.persona.email;
    document.getElementById('txtDni').value = empleado.dni;
    document.getElementById('txtDireccion').value = empleado.direccion;
    document.getElementById('txtFechaNacimiento').value = empleado.fechaNacimiento;
    document.getElementById('txtCelular').value = empleado.celular;
    document.getElementById('cbxcargo').value = empleado.rol === 1 ? 'Empleado A1' : 'Empleado A2';

    document.getElementById('btnSaveChanges').innerHTML = '';
    document.getElementById('btnCancel').innerHTML = '';

    let btnSaveChanges='<button type="button" class="btn btn-primary btn-user btn-block" onclick="editarEmpleado('+empleado.idEmpleado+')">Modificar</button>';
    let btnCancel = '<button type="button" class="btn btn-primary btn-user btn-block" onclick="limpiarModalEmpleado()">Cancelar</button>';

    document.getElementById('btnSaveChanges').innerHTML = btnSaveChanges;
    document.getElementById('btnCancel').innerHTML = btnCancel;
}

async function agregarEmpleado(){
        //var popup = document.getElementById("popupEmpleado");
        let datos = {
            persona: {
                nombre: document.getElementById('txtNombre').value,
                apellido: document.getElementById('txtApellido').value,
                email: document.getElementById('txtCorreo').value
            },
            dni: document.getElementById('txtDni').value,
            direccion: document.getElementById('txtDireccion').value,
            fechaNacimiento: document.getElementById('txtFechaNacimiento').value,
            celular: document.getElementById('txtCelular').value
        };
        var cargo = document.getElementById('cbxcargo').value;
        datos.rol = cargo === 'Empleado A1' ? 1 : 2;
        const request = await fetch('api/v1/empleados', {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });
        limpiarModalEmpleado();
        cargarEmpleados();
        //mostrarAlerta('listaEmpleados.html',popup);
}
async function editarEmpleado(id){
    let empleadoModificado = {
        idEmpleado: id,
        persona: {
            nombre: document.getElementById('txtNombre').value,
            apellido: document.getElementById('txtApellido').value,
            email: document.getElementById('txtCorreo').value
        },
        dni: document.getElementById('txtDni').value,
        direccion: document.getElementById('txtDireccion').value,
        fechaNacimiento: document.getElementById('txtFechaNacimiento').value,
        celular: document.getElementById('txtCelular').value
    };
    var cargo = document.getElementById('cbxcargo').value;
    empleadoModificado.rol = cargo === 'Empleado A1' ? 1 : 2;

    //EL PROBLEMA ESTA EN EL FRONTEND, YA QUE DESDE POSTMAN FUNCIONA TODO BIEN

    const request = await fetch('api/v1/empleados',{
        method: 'PUT',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(empleadoModificado)
    });
    $('#myModalNuevoEmpleado').modal('hide');
    location.reload();
}
async function eliminarEmpleado(id){
 $('#formEliminar').modal('show');

// Agrega un evento click al botón "Eliminar" dentro del modal
    document.getElementById('botonEliminarElemento').addEventListener('click', async function () {
        // Realiza la eliminación utilizando el ID pasado como parámetro
        const request = await fetch('api/v1/empleados/' + id, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        $('#formEliminar').modal('hide');
        location.reload();
    });
}
function limpiarModalEmpleado(){
    $('#myModalNuevoEmpleado').modal('hide');
    document.getElementById('txtNombre').value = '';
    document.getElementById('txtApellido').value = '';
    document.getElementById('txtCorreo').value = '';
    document.getElementById('txtDni').value = '';
    document.getElementById('txtDireccion').value = '';
    document.getElementById('txtFechaNacimiento').value = '';
    document.getElementById('txtCelular').value = '';
    document.getElementById('cbxcargo').value = '';
}
