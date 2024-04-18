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
                let botonEditar = '<a href="#" class="btn btn-primary btn-circle btn-sm"><i class="fas fa-exclamation-triangle"></i></a>';
                let botonEliminar = '<a href="#" class="btn btn-primary btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
                let empleadoHtml =  '<tr><td>'+cont+'</td><td>'+nombre+'</td><td>'+empleado.persona.email+'</td><td>'+empleado.celular+'</td><td>'+rol+'</td><td>'+botonEditar+'</td><td>'+botonEliminar+'</td></tr>';
                listadoHtml+=empleadoHtml;
          }
          document.querySelector('#listaEmpleados tbody').outerHTML=listadoHtml;
}
function agregarNuevoEmpleado(){
    $('#myModalNuevoEmpleado').modal('show');
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
        $('#myModalNuevoEmpleado').modal('hide');
        limpiarModalEmpleado();
        cargarEmpleados();
        //mostrarAlerta('listaEmpleados.html',popup);
}
function limpiarModalEmpleado(){
    document.getElementById('txtNombre').value = '';
    document.getElementById('txtApellido').value = '';
    document.getElementById('txtCorreo').value = '';
    document.getElementById('txtDni').value = '';
    document.getElementById('txtDireccion').value = '';
    document.getElementById('txtFechaNacimiento').value = '';
    document.getElementById('txtCelular').value = '';
    document.getElementById('cbxcargo').value = '';
}