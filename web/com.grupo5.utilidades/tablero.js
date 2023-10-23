$(document).ready(function () {
    var textAnterior = "";
    // Metodo para hacer los elementos sortables
    function agregarSortables() {
        $("#board").sortable({
            items: ">div"
        });
        $(".swim-lane-content").sortable({
            connectWith: ".swim-lane-content",
            start: function (event, ui) {
                ui.item.addClass("is-dragging");
            },
            stop: function (event, ui) {
                ui.item.removeClass("is-dragging");
            },
            receive: function (event, ui) {
                var estado = $(this).attr("id");

                //colocamos el estado al que se mueve
                ui.item.attr("estado", estado);

                //TODO:
                //actualizar el tablero  // <------- esto voy a hacer

            },
            items: ">div"
        });
    }

    agregarSortables();


    //Eventos para controlar los estados.


    // Este metodo agrega un nuevo estado al talero
    $("#btn-agregarEstado").click(function () {
        $('#insertEstadoModal').modal('show');
    });

    //evento click para agregar un estado.
    $('#formInsertarEstado').submit(function (e) {
        e.preventDefault();
        var formData = $(this).serialize();
        console.log(formData);

        var params = new URLSearchParams(formData);
        var datos = {};

        for (const [key, value] of params) {
            datos[key] = value;
        }

        console.log(datos.estado);
        $('#estadoVal').text("");
        if (datos.estado.trim() === "") {
            $('#estadoVal').text("Tienes que completar el estado.");
            return false;
        }
        $.ajax({
            url: "EstadosControlador?accion=insertar", // Reemplaza con la URL de tu servlet
            type: "POST", // Puedes usar POST para enviar datos al servidor
            data: formData, // Convierte el objeto de datos a JSON
            dataType: "json", // Espera una respuesta en formato JSON
            success: function (data) {
                var penultimoItem = $("#board .swim-lane").eq(-1);
                let estado = `<div class="card ms-3 me-3 mt-4 swim-lane">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h3 class="card-title"> ${datos.estado}</h3>
        <div class="dropdown no-arrow">
            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
               data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                 aria-bs-labelledby="dropdownMenuLink">
                <div class="dropdown-header">Acciones:</div>
                <button class="dropdown-item" data-bs-toggle="modal" data-bs-target="#agregarTareasModal">Agregar tarea</button>
                <button class="dropdown-item editarEstado">Editar estado</button>
                <div class="dropdown-divider"></div>
                <button class="dropdown-item btn-eliminar-estado">Eliminar</button>
            </div>
        </div>
    </div>
    <div class="card-body justify-content-center swim-lane-content" id="${data.idEstado}">
        
    </div>
    <div class="card-footer">
      <button type="button"class="btn-agregarTarea text-secondary" data-bs-toggle="modal" data-bs-target="#agregarTareasModal">Agregar Tarea <i class="fa-solid fa-plus"></i></button>
    </div>
    </div>`;
                penultimoItem.after(estado);
                agregarSortables();
                $('#cerrarModal-guardarEstado').click();

            },
            error: function () {
                Swal.fire("Error", "Ocurrio un error al agregar el estado", "error");
            }
        });
    });

    //controlar el evento click de mi boton para cerrar el modal de agregar estado
    $('#cerrarModal-guardarEstado').click(function () {
        $('#btnLimpiar-agergarEstado').click();
        $('#estadoVal').text("");
    });

    //Metodos para permitir editar el titulo del estado
    $(document).on('dblclick', '.card-title', function () {
        $(this).attr("contenteditable", true);
        textAnterior = $(this).text();
        $(this).text("");
        $(this).focus();
    });

    $(document).on('blur', '.card-title', function () {
        if ($(this).text() === "") {
            $(this).text(textAnterior);
        }
        $(this).attr("contenteditable", false);
    });

    $(document).on('keydown', '.card-title', function (e) {
        if (e.which === 13) {
            $(this).blur(); //proboca un clic en otro lado si se presiona enter
        }
    });

    //evento para mostrar el modal para modificar el estado
    $(document).on('click', '.editarEstado', function () {
        let estado = $(this).closest('.card').find('.card-title').text();
        console.log(estado);
        $('#estadoEdit').val(estado);
        $('#modificarEstadoModal').modal('show');
    });

    //Evento para controlar cuando se cierre el modal para modificar el estado
    $('#cerrarModal-modificarEstado').click(function () {
        ('#btnLimpiar-modificarEstado').click();
    });

    //Evento submit del formulario para modificar el estado.  <----------



    //evento click para eliminar un estado.
    $(document).on("click", '.btn-eliminar-estado', function () {
        Swal.fire({
            title: "¿Estás seguro?",
            text: "Una vez eliminado, no podrás recuperar este estado ni las tareas asociadas a él.",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            cancelButtonColor: "#3085d6",
            confirmButtonText: "Sí, eliminarlo",
            cancelButtonText: "Cancelar"
        }).then((result) => {
            if (result.isConfirmed) {
                $(this).closest(".swim-lane").remove();
                Swal.fire("Eliminado", "El estado ha sido eliminado correctamente.", "success");
            }
        });
    });

    //Eventos para Controlar las tareas.


    //Mostrar el modal cuando se de click sobre la tarea. este sera para acutualizar la tarea
//    $(document).on('click', '.task-content', function () {
//        $('#agregarTareasModal').modal('show');
//    });

    //evento para controlar las acciones al cerrar el modal para guardar tarea
    $(document).on('click', '#cerrarModal-guardarTarea', function () {
        $("#resetForm-Agregar").click();
        limpiarValidaciones();
    });



    $(document).on("submit", "#formAgregarTarea", function (e) {
        e.preventDefault();
        var formData = $(this).serialize();
        console.log(formData);

        var params = new URLSearchParams(formData);
        var datos = {};

        for (const [key, value] of params) {
            datos[key] = value;
        }

        console.log(datos.estado);
        var textEstado = $('#estado').find('option:selected').text();

        if (parseInt(datos.estado) !== 0) {
            $("#validacion-estado").text("");
            $.ajax({
                url: "TareasControlador?accion=insertar", // Reemplaza con la URL de tu servlet
                type: "POST", // Puedes usar POST para enviar datos al servidor
                data: formData, // Convierte el objeto de datos a JSON
                dataType: "json", // Espera una respuesta en formato JSON
                success: function (data) {

                    $("#cerrarModal-guardarTarea").click();
                    var tareaTablero = `<div class="card shadow-sm mb-2 task" draggable="true" estado = "${datos.estado}" id="${data.idTarea}">
            <div class="card-body justify-content-center task-content">
                ${datos.tarea}
            </div>
            </div>`;
                    var tareaList = `<tr>
                            <td><input type="checkbox"></td>
                            <td>${datos.tarea}</td>
                            <td>${datos.fechaFin}</td>
                            <td></td>
                            <td class="">${textEstado}</td>
                            </tr>`;
                    var ultimaTarea = $(`#${data.estado}`).find('.task:last');
                    var tablero = $('.table tbody').eq(-1);
                    console.log(ultimaTarea);
                    if (ultimaTarea.length === 0) {
                        $(`#${datos.estado}`).append(tareaTablero);
                    } else {
                        ultimaTarea.after(tareaTablero);
                    }

                    tablero.after(tareaList);

                    $(".table").DataTable();
                    $('#cerrarModal-guardarTarea').click();
                },
                error: function () {
                    Swal.fire("Error", "Ocurrio un error al agregar la tarea", "error");
                }
            });
        } else {
            $("#validacion-estado").text("Tienes que seleccionar un estado.");
        }

    });


});

function limpiarValidaciones() {
    $("#validacion-estado").text("");
}