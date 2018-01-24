function ajaxCallHandler(type){
	return function (data){
		const status = data.status;
		switch(status){
			case 'begin':
				if(type === 'add'){
					$('#mesa-details').modal('hide');
				}
				$('#loading').modal();
			break;
			case 'success':
				$('#loading').modal('hide');
				if(type === 'open'){
					$('#mesa-details').modal();
				}
				if(type === 'add'){
					$('#mesa-details').modal();
				}
				buttonsWorkAround();
			break;
		}
	}
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

function selectItem(item){
	/* The order is this method is very important */
	const inputDesc = $('#search-input');
	const inputPrice = $('#price-input');
	inputDesc.val(item.innerText);

	filterItem(inputDesc);

	inputPrice.val(item.dataset.price);
	$("#formMesa\\:product-id").val(item.dataset.id);

	item.classList.add('activex');
	$("#formMesa\\:quant-input").focus();
	updateTotalPrice(document.querySelector("#formMesa\\:quant-input"));
}


function updateTotalPrice({value}){
	if(value !== "" && $("#formMesa\\:product-id").val() !== "")
	{
		const isNumber = new RegExp('^[0-9]*$', 'gm').test(value);
		const quant = parseInt(value, 10);
		if(isNumber && quant > 0)
		{
			const price = parseFloat($('#price-input').val(), 10);
			
			const totalPrice = Intl.NumberFormat('pt-br', {
				style: 'currency',
				currency: 'BRL'
			}).format(price * quant);
			
			$("#total-price-input").val(totalPrice);
			$("#formMesa\\:add-button").attr('disabled', false);
			return true;
		}
	}
	$("#total-price-input").val("");
	$("#formMesa\\:add-button").attr('disabled', true);
	return true;
}

function checkLink(link){
	if(link.getAttribute('disabled')){
		event.preventDefault();
		return false;
	}
	return true;
}

function filterItem(item){
   	const value = $(item).val().toLowerCase();
   	$("#item-list li").removeClass('activex');
   	$("#price-input").val('');
	$("#formMesa\\:product-id").val('');	
	$("#total-price-input").val('');
	$("#formMesa\\:add-button").attr('disabled', true);
   	$("#item-list li").filter(function() {
   		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
   	});
}
