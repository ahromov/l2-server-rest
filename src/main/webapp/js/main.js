var newsForm = document.querySelector('#newsForm');
var newsImage = document.querySelector('#newsImage');
var newsUploadError = document.querySelector('#newsUploadError');
var newsUploadSuccess = document.querySelector('#newsUploadSuccess');

async function send(method, url, data) {
	return await fetch(url, {
		method: method,
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	});
}

function addNews() {
	let formData = new FormData(newsForm);

	send("POST", "/news/add", formData).then(response => response.json())
		.then((_result) => {
			if (_result.status == 'Success') {
				newsForm.style.display = "none";
				newsUploadError.style.display = "none";
				newsUploadSuccess.innerHTML = `<p>${response.id} <br> ${response.title} <br> ${response.text} <br> ${new Date(response.date).toLocaleDateString()} <br> ${new Date(response.date).toLocaleTimeString()} <br> <img style='width: 50px; margin-bottom: 20px' src='data:image/png;base64,${response.image}'> <br> ${response.status}</p> `;
				newsUploadSuccess.style.display = "block";
			} else {
				newsUploadSuccess.style.display = "none";
				newsUploadError.innerHTML = (_result.status)
					|| "Some Error Occurred";
			}
		})
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