import { manejoFormulario } from "../manejoFormulario.js";
import { manejoModal } from "./actualizarTarea.js";
import { updateTask } from "./actualizarTarea.js";
import { deleteTask } from "./eliminarTareas.js";
import { fetchTasks } from "./mostrarTareas.js";



document.addEventListener('DOMContentLoaded',  function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'timeGridWeek',
      aspectRatio: 4,
      headerToolbar: {
        left:'prev,next',
        center: "title",
        right: 'timeGridWeek,timeGridDay,dayGridMonth,multiMonthYear'
      },
    editable: true,
    eventClick: function(info){
      manejoModal(info.event, calendar);
    }
    });
    calendar.render();
    fetchTasks(calendar);
    updateTask(calendar);
    deleteTask(calendar);
    manejoFormulario(calendar);
  });
