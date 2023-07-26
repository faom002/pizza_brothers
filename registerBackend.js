const inputUserNameRegistration = document.getElementById('register-input');
const inputPasswordRegistration = document.getElementById('password-register');
const buttonRegister = document.getElementById('button-register');




function registerUser() {
    const userName = inputUserNameRegistration.value;
    const password = inputPasswordRegistration.value;

    fetch("http://localhost:8081/pizza/add", {
            method: "POST",
            body: new URLSearchParams({
                userName: userName,
                password: password
            })
        })
        .then(function(response) {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error("Request failed");
            }
        })
        .then(function(data) {
            // Handle response
            console.log(data);
        })
        .catch(function(error) {
            // Handle error
            console.log(error);
        });
}



buttonRegister.addEventListener("click",registerUser);



