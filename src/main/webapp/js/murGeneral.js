function reload(){
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : "v1/reload/",
		dataType : "json",
		data : JSON.stringify({
		}),
		success : function(data, textStatus, jqXHR) {
			//console.log(data)
			var i;
			var content = "";
			for(i = 0 ; i < data.length ; i++) {
				content += "<div class=\"post\">";
				content += "<div class=\"pseudo\">" + data[i].pseudo + "</div>";
				content += "<div class=\"description\">" + data[i].description + "</div>";
				if(data[i].type == "audio")
					content += "<audio controls=\"controls\"> <source src=\"" + data[i].path + "\" type=\"audio/mp3\" /> Votre navigateur n'est pas compatible </audio>";
				
				if(data[i].type == "video")
					content += "<video controls src=\"" + data[i].path + "\"> video mp4 </video>";
				
				if(data[i].type == "image")
					content += "<image controls src=\" " + data[i].path + "\"></image>";
			
				
				content += "</div>";
			}
			$("#posts").html(content);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			//console.log(data)
			alert('erreur');
		}
	});
}



function getUser(idCookie){
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : "v1/user",
		dataType : "json",
		data : JSON.stringify({
			"id" : idCookie
		}),
		success : function(data, textStatus, jqXHR) {
			console.log(data);
			$("#user").html(data.pseudo);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('postUser error: ' + textStatus);
		}
	});
}