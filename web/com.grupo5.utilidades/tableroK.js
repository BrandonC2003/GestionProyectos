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
        $("#cerrarModal-guardar").click();
        $("#resetForm-Agregar").click();
        var tarea = `<div class="card shadow-sm mb-2 task" draggable="true">
    <div class="card-body justify-content-center task-content">
        ${tarea.value}
    </div>
    </div>`;
        var ultimaTarea = $("#board .swim-lane").eq(0).find('.swim-lane-content .task:last');
        if (ultimaTarea.length !== 0) {
            ultimaTarea.after(tarea);
        }
    });
});