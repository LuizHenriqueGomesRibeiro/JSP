jQuery(function() {
	if(jQuery("#formulario").length){
		jQuery("#formulario").validate({
			rules: {
				login: {
					required: true,
				},
				senha: {
					required:true,
				}
			},
			messages: {
				login:{
					required: "O nome é obrigatório",
				},
				senha:{
					required:"A senha é obrigatória",
				},
			}
		});
	}
	else{
		function validar() {
			document.forms["frmContato"].submit();
		}
	}
});