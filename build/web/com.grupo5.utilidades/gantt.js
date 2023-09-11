gantt.config.date_format = "%Y-%m-%d"; // Formato de fecha (puedes personalizarlo)

gantt.init("gantt_container"); // Inicializa el gráfico en el contenedor especificado

// Define la estructura de datos del gráfico de Gantt
var tasks = [
{
    id: 1,
    text: "Tarea 1",
    start_date: "2023-09-01",
    duration: 5,
    color:'green'
},
{
    id: 2,
    text: "Tarea 2",
    start_date: "2023-09-06",
    duration: 3
},
  // Agrega más tareas aquí
];

gantt.parse({ data: tasks }); // Carga los datos en el gráfico de Gantt

// Personaliza el menú contextual
gantt.config.context_menu = [
    // Otros elementos del menú aquí
    {
      label: "Eliminar tarea personalizada",
      icon: "fa fa-trash", // Icono opcional
      id: "custom-delete", // Identificador único para el elemento
      onClick: function(id) {
        // Lógica personalizada para eliminar la tarea
      }
    }
  ];