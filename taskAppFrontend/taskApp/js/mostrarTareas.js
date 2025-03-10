export async function fetchTasks(calendar) {
    try{
        const response = await fetch("http://localhost:4000/api/tasks/all");
        if(!response.ok){
            throw new Error("Error al obtener las tareas");
        }
        const tasks = await response.json();
     //renderiza las tareas en la interfaz de usuario
        tasks.forEach(e => {
            calendar.addEvent({
                id: e.idTask,
                title: e.title,
                start: e.start,
                end: e.end,
                extendedProps: {
                    description: e.description
                }
            }) 
        });
    }catch(err){
        console.error("Error: ", err);
        alert("Hubo un problem al cargar las tareas.");
    }
}



/*export function renderTasks(tasks){
    const taskList = document.getElementById("task-list-section");
    taskList.innerHTML = ""; //Limpiar el contenedor antes de agregar nuevas tareas
    if(tasks.length === 0 ){
            const emptymessage = document.createElement("div");
            emptymessage.className = "task-item";
            emptymessage.innerHTML = `
                <p><strong>No hay tareas disponibles</strong></p>`;
            taskList.appendChild(emptymessage);
    }else{
    tasks.forEach((task) => {
        const taskItem = document.createElement("div");
        taskItem.className = "task-item";
        taskItem.setAttribute("data-id",task.idTask);
        taskItem.innerHTML = `
            <h3><strong>Tarea:</strong> ${task.title}</h3>
            <p><strong>Descripcion: </strong> ${task.description}</p>
            <p><strong>Fecha limite: </strong>${task.dueDate}</p>
            <button type="button" class="delete-task" data-id="${task.idTask}">Eliminar tarea</button>
            <button type="button" class="update-task" data-id="${task.idTask}">Editar tarea</button>
            `;
        taskList.appendChild(taskItem); //Agregar la tarea al contenedor;
    });}
}*/

/*export function addTaskToDom(task){
    const taskList = document.getElementById("task-list-section");
    const newTask = document.createElement("div");
    newTask.className = "task-item";
    newTask.setAttribute("data-id",task.idTask);
    newTask.innerHTML = `
        <h3><strong>Tarea: </strong>${task.title}</h3>
        <p><strong>descripcion: </strong>${task.description}</p>
        <p><strong>fecha limite: </strong>${task.start}</p>
        <button type="button" class="delete-task" data-id="${task.idTask}">Eliminar tarea</button>
        <button type="button" class="update-task" data-id="${task.idTask}">Editar tarea</button>
        `;
    taskList.appendChild(newTask);
}*/



