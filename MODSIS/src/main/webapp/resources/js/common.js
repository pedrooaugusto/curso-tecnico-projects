const formName = FACE === "CLIENTE" ? "formCliente" : "formProduto"

const fields = FACE === "CLIENTE" ? 
	['codigo1', 'nome', 'telefone', 'celular', 'email','paymentDay', 'endr'] :
	['codigo1', 'nome', 'preco', 'monitorado'];


function search(){
	const text = document.getElementById(`${formName}:searchCod`).value;
	if(text !== "" && parseInt(text, 10) && new RegExp('^[0-9]*$', 'gm').test(text)){
		return true;
	}
	else{
		Modal('Error', 'NUMBER_CONVERSION', FACE.toLocaleLowerCase());
		return false;
	}
}

function checkForm(notInsert = true){
	const Form_ = FACE === "CLIENTE" ? formCliente : formProduto;
	const fields_ = fields.filter((v, k) => {
		if(!notInsert && k === 0)
			return false;
		return true;
	}).map(a => `${formName}:${a}`);
	for(let a of fields_){
		if(Form_[a].type !== "checkbox" && Form_[a].value === ''){
			Modal('Error', 'EMPTY_FIELD', FACE.toLocaleLowerCase());
			return false;
		}
	}
	return true;
}

function ajaxCallHandler(type){
	return function (data){
		const status = data.status;
		switch(status){
			case 'begin':
				if(type !== 'clear')
					$('#loading').modal();
			break;
			case 'success':
				if(type !== 'clear')
					$('#loading').modal('hide');
				const nome = document.getElementById(`${formName}:nome`).value;
				if(type === 'search')
					if(nome === "")
						Modal('Error', 'NOT_FOUND', FACE.toLocaleLowerCase());
				if(type === 'update')
					if(nome !== "")
						Modal('Success', 'UPDATE_OK', FACE.toLocaleLowerCase());
				if(type === 'insert')
					if(nome !== "")
						Modal('Success', 'INSERT_OK', FACE.toLocaleLowerCase());
				if(type === 'delete')
					if(nome === "")
						Modal('Success', 'DELETE_OK', FACE.toLocaleLowerCase());
				buttonsWorkAround();
			break;
		}
	}
}

function Modal(modal_type, message_type, element, size = 'md'){
	const messages = {
			NUMBER_CONVERSION: `O termo de busca não pôde ser convertido para um número inteiro natural.`,
			NOT_FOUND: `Um ${element} com este código não foi encontrado.`,
			UPDATE_OK: `Registro alterado com sucesso!`,
			EMPTY_FIELD: `Preencha todos os campos!`,
			INSERT_OK: `Registro inserido com sucesso!`,
			DELETE_OK: `Registro excluido com sucesso!`
	};
	$("#genericError .text-error").text(messages[message_type]);
	$("#genericError .modal-title").text(modal_type);
	$("#genericError").modal();
}

function buttonsWorkAround(){
	const all = document.querySelectorAll("[disabledp]");
	all.forEach(a => {
		if(a.getAttribute('disabledp') === 'true'){
			a.removeAttribute('disabledp');
			a.setAttribute('disabled', true);
		}
		else{
			a.removeAttribute('disabledp');
			a.removeAttribute('disabled');
		}
	});
}

onload = function (){
	buttonsWorkAround();
}