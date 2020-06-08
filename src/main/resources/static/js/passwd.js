var passwdForm = document.querySelector('#changePassword');
var uploadError = document.querySelector('#newsUploadError');
var uploadSuccess = document.querySelector('#newsUploadSuccess');

function changePasswd() {
	let formData = new FormData(passwdForm);

	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/cabinet/password/change");
	xhr.send(formData);
	xhr.onload = function () {
		var response = xhr.responseText;

		if (xhr.status == '200') {
			passwdForm.style.display = "none";
			uploadError.style.display = "none";
			uploadSuccess.innerHTML = `<p>${response}</p><br><a href="/home">Back to home</a>`;
			uploadSuccess.style.display = "block";
		} else {
			uploadSuccess.style.display = "none";
			uploadError.innerHTML = (response && response.status)
				|| "Some Error Occurred";
		}
	}
}

passwdForm.addEventListener('submit', function (event) {
	changePasswd();

	event.preventDefault();
}, true);