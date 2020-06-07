var newsForm = document.querySelector('#newsForm');
var newsImage = document.querySelector('#newsImage');
var newsUploadError = document.querySelector('#newsUploadError');
var newsUploadSuccess = document.querySelector('#newsUploadSuccess');
var hostName = '127.0.0.1';
var hostPort = '8080';

function addNews() {
	let formData = new FormData(newsForm);

	let xhr = new XMLHttpRequest();
	xhr.open("POST", "http://" + hostName + ":" + hostPort + "/news/add");

	xhr.send(formData);

	xhr.onload = function () {
		console.log(xhr.responseText);

		var response = JSON.parse(xhr.responseText);

		if (xhr.status == 200) {
			newsForm.style.display = "none";
			newsUploadError.style.display = "none";
			newsUploadSuccess.innerHTML = `<p>${response.id} <br> ${response.title} <br> ${response.text} <br> ${new Date(response.date).toLocaleDateString()} <br> ${new Date(response.date).toLocaleTimeString()} <br> <img style='width: 50px; margin-bottom: 20px' src='data:image/png;base64,${response.image}'> <br> ${response.status}</p> `;
			newsUploadSuccess.style.display = "block";
		} else {
			newsUploadSuccess.style.display = "none";
			newsUploadError.innerHTML = (response && response.status)
				|| "Some Error Occurred";
		}
	}
}

newsForm.addEventListener('submit', function (event) {
	var files = newsImage.files;

	if (files.length === 0) {
		newsUploadError.innerHTML = "Please select a file";
		newsUploadError.style.display = "block";
	}

	addNews();

	event.preventDefault();
}, true);