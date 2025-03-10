export async function deleteTask(calendar) {
document.querySelector(".delete-task").addEventListener("click", async (event) => {
    event.preventDefault();
    if(event.target.classList.contains("delete-task")){
        const modal = document.getElementById('edit-modal');
        const taskId = modal.dataset.eventId;

        const eliminar = confirm("¿Estas seguro que quieres eliminar esta tarea?");
        if (eliminar){
            try{
            const response = await fetch(`http://localhost:4000/api/tasks/delete/${taskId}`,{
                method: "DELETE",
            });
            if (response.ok){
                alert("Tarea eliminada exitosamente");
                //Actualizar el frontend eliminando la tarea en FullCalendar
                const event = calendar.getEventById(taskId);
                event.remove();
                document.getElementById('edit-modal').style.display='none';
            }else{
                alert("Hubo un error al intentar eliminar la tarea");
            }
            }catch(err){
                console.error("Error al eliminar la tarea:", err);
                alert("Hubo un error al intentar conectar con el servidor");
            }
        }
    }
});
}