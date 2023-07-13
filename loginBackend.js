const buttonLogin = document.getElementById('login-btn');
const inputPasswordLogin = document.getElementById('password-login');
const inputUserNameLogin = document.getElementById('login-input');

function loginExisitingUser() {
    const userName = inputUserNameLogin.value;
    const password = parseInt(inputPasswordLogin.value);


    //    console.log('Input username:', userName);
    // console.log('Input password:', password);

    fetch('http://localhost:8080/pizza/all')
        .then(response => response.json())
        .then(data => {
            // Process the retrieved data here
            data.forEach(item => {
                if (userName === item.userName && password === item.password) {
                    console.log(item.userName + " " + item.password);
                }else {
                	console.log("incorrect password and username");
                }
            });

        })
        .catch(error => {
            // Handle any errors that occur during the request
            console.error(error);
        });



}

buttonLogin.onclick = loginExisitingUser;