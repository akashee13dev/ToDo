function submitUser() {
  event.preventDefault();
  var userEmail = document.getElementById("email").value;

  if (userEmail.endsWith(".com")) {
    axios.get('http://localhost:8000/user', {
      params: {
        mail: userEmail,
      }
    }).then(response => {
      console.log(response.data);

      if (response.data && response.data.id) {
        // ✅ Use backticks for template literal
        window.location.href = `ToDO.html?userMail=${encodeURIComponent(userEmail)}`;
      } else {
        alert("User not found or invalid response.");
      }
    }).catch(error => {
      console.error("There was an error!", error);
      alert("There was an error! " + error);
    });
  } else {
    alert("Invalid email address.");
  }
}
