$(document).on('click', "button.green", function() {
	let formData = new FormData();

	let title = $("#newsTitle").val();
	let text = $("#newsText").val();
	let image = $("#newsImage").prop('files');

	formData.append("image", image[0]);
	formData.append('newsDto', new Blob([JSON.stringify({
		"title": title,
		"text": text
	})], {
		type: "application/json"
	}));

	let prom = fetch('/news/add', {
		method: 'POST',
		body: formData
	});

	prom.then(response => response.json()).then(data => {
		if (data.status == 400) {
			$('div.status').html("");
			$('div.status').append(data.message + '<br>');
			data.errors.forEach(e => $('div.status').append(e.field + ": " + e.defaultMessage + '<br>').css('color', 'red'));
			return;
		}

		$('form').trigger('reset');
		$('div.status').html(`<p>${data.id} <br> 
								${data.title} <br> 
								${data.text} <br> 
								${new Date(data.date).toLocaleDateString()} <br> 
								${new Date(data.date).toLocaleTimeString()} <br> 
								<img style='width: 50px; margin-bottom: 20px' src='data:image/png;base64,${data.image}'></p> `).css('color', 'green');
	});
})
