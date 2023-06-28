jQuery(function() {
	if(jQuery("#formulario").length){
		jQuery("#formulario").validate({
			rules: {
				login: {
					required: true,
				},
				nome: {
					required: true,
				},
				email: {
					required: true,
				},
				senha: {
					required:true
				},
			},
			messages: {
				login: {
					required: "O login é obrigatório"
				},
				nome: {
					required: "O nome é obrigatório"
				},
				email: {
					required: "O E-mail é obrigatório"
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