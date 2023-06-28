jQuery(function() {
	if(jQuery("#formulario").length){
		jQuery("#formulario").validate({
			rules: {
				login: {
					required: true,
					minlength: 2
				},
				senha: {
					required:true
				},
			},
			messages: {
				login: {
					required: "O nome é obrigatório",
					minlength: "Seu nome deve ter ao menos dois caracteres"
				},
				senha:{
					required:"A senha é obrigatória",
				}
			}
		});
	}
	else{
		function validar() {
			document.forms["frmContato"].submit();
		}
	}
});