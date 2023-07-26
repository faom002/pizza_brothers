const buttonLogin = document.getElementById('login-btn');
const inputPasswordLogin = document.getElementById('password-login');
const inputUserNameLogin = document.getElementById('login-input');

function loginExistingUser() {
    const userName = inputUserNameLogin.value;
    const password = parseInt(inputPasswordLogin.value);

    const url = `http://localhost:8081/pizza/user?username=${encodeURIComponent(userName)}&password=${password}`;

        console.log(url);

    fetch(url)
        .then(response => response.json())  // Parse the response as JSON
        .then(data => {
            if (data.length > 0) {
                const user = data[0]; // Assuming the backend returns an array of users with matching credentials
                console.log(user.userName + " is logged in successfully"); // 'userName' should be lowercase

                const redirectUrl = 'pizza-brothers.html?username=' + encodeURIComponent("welcome " + user.userName);
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

buttonLogin.addEventListener('click', loginExistingUser);
