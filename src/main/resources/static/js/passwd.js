async function send(method, url, data) {
	return await fetch(url, {
		method: method,
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	});
}

$(document).on('click', "button.button", function() {
	let login = $("#username").val();
	let password = $("#password").val();
	let newPassword = $("#newPassword").val();
	let newRepeatedPassword = $("#newRepeatedPassword").val();

	var userData = {
		login: login,
		password: password,
		newPassword: newPassword,
		newRepeatedPassword: newRepeatedPassword
	};

	let prom = send('POST', '/cabinet/changePass', userData);
	prom.then(response => {
		if (response.status == 200) {
			resetFormAndHideStatus();
			$("#newsUploadSuccess").html(`<p>${response.status}</p><br><a href="/home">Back to home</a>`).css('display', 'block').css('color', 'green');
		} else {
			prom.then(response => response.json()).then(data => {
				resetFormAndHideStatus();
				$("#newsUploadError").html(`<p>${data.status}</p><br><a href="/home">Back to home</a>`).css('display', 'block').css('color', 'red');
			})
		}
	});

	function resetFormAndHideStatus() {
		$('form').trigger('reset');
		$('.responseStatus').css('display', 'none');
	}
})