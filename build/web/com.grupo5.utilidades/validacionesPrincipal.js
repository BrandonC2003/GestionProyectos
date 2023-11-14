$(document).ready(function(){
    
    //validacion cuando se cree un proyecto
    $("#insertarProyecto").submit(function(e){
        e.preventDefault();
        $("#proyectoVal").text("");
        
        let valorProyecto = $("#Proyecto").val().trim();
        
        if(valorProyecto===""){
            $("#proyectoVal").text("El nombre del proyecto no puede quedar vacío.");
            return false;
        }
        
        $(this).unbind('submit').submit();
    });
});
