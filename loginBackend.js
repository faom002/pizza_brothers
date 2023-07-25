const buttonLogin = document.getElementById('login-btn');
const inputPasswordLogin = document.getElementById('password-login');
const inputUserNameLogin = document.getElementById('login-input');

function loginExistingUser() {
    const userName = inputUserNameLogin.value;
    const password = parseInt(inputPasswordLogin.value);

    const url = `http://localhost:8080/pizza/user?username=${userName}&password=${password}`;

    fetch(url)
        .then(response => response.text())  // Use response.text() because our method in backend returns type string instead of response.json()
        .then(data => {
            if (data === userName) {  // Compare the response with the userName
                console.log(data + " is logged in successfully");

                const redirectUrl = 'pizza-brothers.html?username=' + encodeURIComponent("welcome " + data);
                window.location.href = redirectUrl;
            } else {
                console.log("Incorrect password or username");
            }
        })
        .catch(error => {
            // Handle any errors that occur during the request
            console.error(error);
        });
}

buttonLogin.onclick = loginExistingUser;
