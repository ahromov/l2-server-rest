var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document
		.querySelector('#singleFileUploadSuccess');

function uploadSingleFile() {
	var formData = new FormData(singleUploadForm);

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/uploadFile");

	xhr.onload = function() {
		console.log(xhr.responseText);
		var response = JSON.parse(xhr.responseText);
		if (xhr.status == 200) {
			singleUploadForm.style.display = "none";
			singleFileUploadError.style.display = "none";
			singleFileUploadSuccess.innerHTML = "<p>"
					+ response.userFirstName
					+ " "
					+ response.userLastName
					+ " "
					+ response.userAge
					+ "</p><p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='"
					+ response.fileDownloadUri + "' target='_blank'>"
					+ response.fileDownloadUri + "</a></p>";
			singleFileUploadSuccess.style.display = "block";
		} else {
			singleFileUploadSuccess.style.display = "none";
			singleFileUploadError.innerHTML = (response && response.message)
					|| "Some Error Occurred";
		}
	}

	xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event) {
	var files = singleFileUploadInput.files;
	if (files.length === 0) {
		singleFileUploadError.innerHTML = "Please select a file";
		singleFileUploadError.style.display = "block";
	}
	uploadSingleFile();
	event.preventDefault();
}, true);