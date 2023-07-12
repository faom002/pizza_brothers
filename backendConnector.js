const inputUserName = document.getElementById('user-name');
const inputPassword = document.getElementById('password');
const buttonRegister = document.getElementById('button-register');

function registerUser() {
  const userName = inputUserName.value;
  const password = inputPassword.value;

  fetch("http://localhost:8080/demo/add", {
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

buttonRegister.onclick = registerUser;
