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
            receive: function(event,ui){
                var estado = $(this).attr("id");
                
                //colocamos el estado al que se mueve
                ui.item.attr("estado",estado);
                
                //TODO:
                //actualizar el tablero  // <------- esto voy a hacer
                
            },
            items: ">div"
        });
    }

    agregarSortables();

    // Este metodo agrega un nuevo estado al talero
    $("#btn-agregarEstado").click(function () {
        var penultimoItem = $("#board .swim-lane").eq(-1);
        let estado = `<div class="card ms-3 me-3 mt-4 swim-lane">
    <div class="card-header justify-content-center">
        <h3 class="card-title">Nuevo Estado</h3>
    </div>
    <div class="card-body justify-content-center swim-lane-content">
        
    </div>
    <div class="card-footer">
      <button type="button"class="btn-agregarTarea text-secondary" data-bs-toggle="modal" data-bs-target="#agregarTareasModal">Agregar Tarea <i class="fa-solid fa-plus"></i></button>
    </div>
    </div>`;
        penultimoItem.after(estado);
        agregarSortables();
    });


//  // Este evento sirve para agregar una nueva tarea a un estado
//  $(document).on("click",'.btn-agregarTarea', function(){
//    var tablero = $(this).closest(".swim-lane");
//    var tarea = `<div class="card shadow-sm mb-2 task" draggable="true">
//    <div class="card-body justify-content-center task-content">
//        Nueva Tarea
//    </div>
//    </div>`;
//    var ultimaTarea=tablero.find('.swim-lane-content .task:last');
//    console.log(ultimaTarea)
//    if(ultimaTarea.length != 0){
//      ultimaTarea.after(tarea);
//      console.log("esto se ejecuto")
//    }else{
//      tablero.find('.swim-lane-content').html(tarea);
//    }
//  });


    //Metodos para permitir editar el titulo del estado
    $(document).on('dblclick', '.card-title', function () {
        $(this).attr("contenteditable", true);
        textAnterior = $(this).text();
        $(this).text("");
        $(this).focus();
    });

    $(document).on('blur', '.card-title', function () {
        if ($(this).text() == "") {
            $(this).text(textAnterior);
        }
        $(this).attr("contenteditable", false);
    });

    $(document).on('keydown', '.card-title', function (e) {
        if (e.which === 13) {
            $(this).blur(); //proboca un clic en otro lado si se presiona enter
        }
    });


    //Metodos para permitir editar el texto dentro de las tareas
    $(document).on('dblclick', '.task-content', function () {
        $(this).attr("contenteditable", true);
        textAnterior = $(this).text();
        $(this).text("");
        $(this).focus();
    });

    $(document).on('blur', '.task-content', function () {
        if ($(this).text() === "") {
            $(this).text(textAnterior);
        }
        $(this).attr("contenteditable", false);
    });

    $(document).on('keydown', '.task-content', function (e) {
        if (e.which === 13) {
            $(this).blur();
        }
    });

    //Evento cuando se envia el formulario para agregar tareas
    $(document).on("submit", "#formAgregarTarea", function (e) {
        e.preventDefault();
        var formData = $(this).serializeArray();
        var tarea = formData.find(item => item.name === 'tarea');
        var estado = formData.find(item => item.name === 'estado');
        var usuario = formData.find(item => item.name === 'usuario');
        var fechaFin = formData.find(item => item.name === 'fechaFin');
        var textEstado = $('#estado').find('option:selected').text();
        console.log(estado.value);
        if (parseInt(estado.value) !== 0) {
            $("#cerrarModal-guardar").click();
            $("#resetForm-Agregar").click();
            var tareaTablero = `<div class="card shadow-sm mb-2 task" draggable="true" estado = "${estado.value}">
            <div class="card-body justify-content-center task-content">
                ${tarea.value}j
            </div>
            </div>`;
            var tareaList = `<tr>
                            <td><input type="checkbox"></td>
                            <td>${tarea.value}</td>
                            <td>${fechaFin.value}</td>
                            <td></td>
                            <td class="${getColorEstado(estado.value)}">${textEstado}</td>
                            </tr>`;
            var ultimaTarea = $(`#${estado.value}`).eq(0).find('.swim-lane-content .task:last');
            var tablero = $('.table tbody').eq(-1);
            if (ultimaTarea.length !== 0) {
                ultimaTarea.after(tareaTablero);
            }
            tablero.after(tareaList);
            $(".table").DataTable();
            limpiarValidaciones();
        }else{
            $("#validacion-estado").text("Tienes que seleccionar un estado.");
        }

    });
});

function limpiarValidaciones(){
    $("#validacion-estado").text("");
}

function getColorEstado(estado){
    if(estado==1){
        return "text-bg-danger";
    }
    if(estado==2){
        return "text-bg-warning";
    }
    if(estado==3){
        return "text-bg-success";
    }
}