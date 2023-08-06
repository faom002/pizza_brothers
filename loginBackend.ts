const buttonLogin: HTMLElement | null = document.getElementById('login-btn');
const inputPasswordLogin: HTMLInputElement | null = document.getElementById('password-login') as HTMLInputElement;
const inputUserNameLogin: HTMLInputElement | null = document.getElementById('login-input') as HTMLInputElement;

function loginExistingUser(): void {
    const userName: string = inputUserNameLogin.value;
    const password: number = parseInt(inputPasswordLogin.value, 10);

    const url: string = `http://localhost:8081/pizza/user?username=${encodeURIComponent(userName)}&password=${password}`;

    console.log(url);

    fetch(url)
        .then(response => response.json())  // Parse the response as JSON
        .then(data => {
            if (Array.isArray(data) && data.length > 0) {
                const user = data[0] as { userName: string }; // Assuming the backend returns an array of users with matching credentials
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

if (buttonLogin) {
    buttonLogin.addEventListener('click', loginExistingUser);
}
