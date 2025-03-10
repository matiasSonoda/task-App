


document.getElementById("loginForm").addEventListener("submit",(event)=>{

    event.preventDefault();

    const password = document.getElementById("password").value;
    const username = document.getElementById("username").value;

    const login = {password:password, username:username};

    try{

        //const response = await fetch("")

    }catch(err){

    }

})