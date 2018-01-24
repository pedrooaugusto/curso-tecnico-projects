function ajaxCallHandler(type){
	return function (data){
		const status = data.status;
		switch(status){
			case 'begin':
				$('#loading').modal();
			break;
			case 'success':
				$('#loading').modal('hide');
				Modal();
			break;
		}
	}
}

function Modal(){
	$("#genericError .text-error").text("Venda concluída com sucesso!");
	$("#genericError .modal-title").text("Venda Finalizada");
	$("#genericError").attr('data-backdrop', 'static').attr('data-keyboard', 'false');
	$("#genericError .modal-footer").html(
			`<a href = '../listar' class = 'btn btn-primary'>Listar</a>
			 <a href = '../../mesa' class = 'btn btn-default'>Voltar</a>`);
	$("#genericError").modal();
}

onload = function (argument) {
	setInterval(function(){
		document.querySelector("#time-count").innerText = new Date().toLocaleString('pt-BR');
		document.querySelector("#formVenda\\:current-date").value = new Date().toLocaleString('pt-BR');
	}, 1000);
}