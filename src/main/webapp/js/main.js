var newsForm = document.querySelector('#newsForm');
var newsImage = document.querySelector('#newsImage');
var newsUploadError = document.querySelector('#newsUploadError');
var newsUploadSuccess = document.querySelector('#newsUploadSuccess');

async function send(method, url, data) {
	return await fetch(url, {
		method: method,
		headers: {
			'Content-Type': 'multipart/form-data'
		},
		// body: JSON.stringify(data)
		body: data
	});
}

function addNews() {
	let formData = new FormData(newsForm);

	send("POST", "/news/add", formData).then(response => response.json())
		.then((_result) => {
			if (_result.status == 'Success') {
				newsForm.style.display = "none";
				newsUploadError.style.display = "none";
				newsUploadSuccess.innerHTML = `<p>${_result.id} <br> ${_result.title} <br> ${_result.text} <br> ${new Date(_result.date).toLocaleDateString()} <br> ${new Date(_result.date).toLocaleTimeString()} <br> <img style='width: 50px; margin-bottom: 20px' src='data:image/png;base64,${response.image}'> <br> ${response.status}</p> `;
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