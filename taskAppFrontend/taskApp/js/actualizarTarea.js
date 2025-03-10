
/*document.getElementById("task-list-section").addEventListener("click", async (e) => {
    if (e.target.classList.contains("update-task")) {
        const taskId = e.target.getAttribute("data-id");
        console.log("ID ANTES DE ENVIAR",taskId);
        try{
            const response = await fetch(`http://localhost:4000/api/tasks/${taskId}`,{
                method:"GET",
            });
            if (!response.ok){
                console.log("Hubo un error al intentar actualizar la tarea");
                return
            }
            const task = await response.json();
            console.log("response del backend: ", task);
            //Aca conectaria con una funcion que abriria un modal para editar la tarea
            showModal(task);
        }catch(err){
            console.log("Hubo un error al intentar realizar la solicitud HTTP. ", err);
        }
    }
})*/

export function manejoModal(event){
    //Activo el modal: display = flex
    const modal = document.getElementById('edit-modal');
    modal.style.display='flex';
    //cargo con los datos del evento a cada input
    document.getElementById('edit-title').value=event.title;
    document.getElementById('edit-description').value=event.extendedProps.description;
    document.getElementById('edit-start').value = event.start.toISOString().slice(0, 16);
    document.getElementById('edit-end').value = event.end ? event.end.toISOString().slice(0, 16) : '';
    modal.dataset.eventId= event.id;

    document.querySelector(".close-modal").addEventListener("click",()=>{
        document.getElementById("edit-modal").style.display = "none";
    });

    window.addEventListener("click",(e) =>{
        const modal = document.getElementById("edit-modal");
        if (e.target === modal){
            modal.style.display = "none";
        }
    });

}

export async function updateTask(calendar){
    document.getElementById("edit-task-form").addEventListener("submit",async (e)=>{
        e.preventDefault();
        //cargo los nuevos datos del evento
        const id = document.getElementById('edit-modal').dataset.eventId;
        console.log(id);
        const title = document.getElementById('edit-title').value;
        const description = document.getElementById('edit-description').value;
        const start = document.getElementById('edit-start').value;
        const end = document.getElementById('edit-end').value;
        //creo un nuevo objeto para guardarlo y sobreescribir el evento en la bdd
        const updateTask = {idTask: id, title: title,description: description,start:start,end:end};

        try{
            const response = await fetch(`http://localhost:4000/api/tasks/${id}`,{
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(updateTask),
            });
            if (!response.ok){
                throw new Error("Error al actualizar la tarea");
            }
            //guardo la response del backend en un nuevo objeto
            const updatedEvent = await response.json();
            //selecciono el evento actualizado y  lo guardo en una constante
            const event = calendar.getEventById(id);
            //actualizo los datos del evento
            event.setProp('title', updatedEvent.title);
            event.setDates(updatedEvent.start,updatedEvent.end);
            event.setExtendedProp('description', updatedEvent.description);

            alert("tarea editada exitosamente");
            document.getElementById('edit-modal').style.display='none';
        }catch(err){
            alert("hubo un error al intentar actualizar la tarea");
        }
    });
}
/*
    document.getElementById("edit-task-form").addEventListener("submit", async(e)=>{
        e.preventDefault();
        task.title = document.getElementById("edit-title").value;
        task.description = document.getElementById("edit-description").value;
        task.dueDate = document.getElementById("edit-dueDate").value;
        console.log("ID de la tarea para actualizar: ", task.idTask);
        const response = await fetch(`http://localhost:4000/api/tasks/${task.idTask}`,{
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(task),
        })
        if (!response.ok){
            console.error("Hubo un error al actualizar la tarea");
        }
        const updateTask = await response.json();
        updateTaskContentInDom(updateTask);
    })*/

/*
function updateTaskContentInDom(updatedTask){
    const taskItem = document.querySelector(`.task-item[data-id="${updatedTask.idTask}"]`);
    console.log(taskItem);
    if (taskItem){
        taskItem.innerHTML = `
            <h3><strong>Tarea: </strong>${updatedTask.title}</h3>
            <p><strong>Descripcion: </strong>${updatedTask.description}</p>
            <p><strong>Fecha limite: </strong>${updatedTask.dueDate}</p>
            <button type="button" class="delete-task" data-id="${updatedTask.idTask}">Eliminar tarea</button>
            <button type="button" class="update-task" data-id="${updatedTask.idTask}">Editar tarea</button>

        `;
    }else{
        console.log("No se encontro la tarea a actualizar en el DOM");
    }
}*/