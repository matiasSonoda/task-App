//Capturar valores de los inputs

export async function  manejoFormulario(calendar){
    document.getElementById("formulario").addEventListener("submit", async(e) =>{
    e.preventDefault();

    const title = document.getElementById("entrada-titulo").value;
    const description = document.getElementById("entrada-descripcion").value;
    const start = document.getElementById("entrada-start").value;
    const end = document.getElementById("entrada-end").value;


    const task = {title: title, description: description,start: start, end: end};

    try{
        const response = await fetch("http://localhost:4000/api/tasks",{
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(task),
        })
        if (!response.ok){
            console.log("No se pudo crear tarea");
        }
        const eventCreated = await response.json();
        calendar.addEvent({
            id: eventCreated.idTask,
            title: eventCreated.title,
            start: eventCreated.start,
            end: eventCreated.end,
            extendedProps: {
                description: eventCreated.description
            }
        })
        alert("Tarea creada con exito");
        document.getElementById("formulario").reset();
        }
    catch(err){
        console.error("Error", err);
    }
})}