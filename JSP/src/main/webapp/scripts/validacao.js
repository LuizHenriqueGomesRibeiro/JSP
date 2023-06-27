/**
 * 
 */
jQuery(function() {
	if(jQuery("#formulario").length){
		jQuery("#formulario").validate({
			errorPlacement: function(){
            	return false;
        		},
			rules: {
				login: {
					required: true,
					minlength: 2
				},
				senha: {
					required:true,
					minlength: 2
				},
				email: {
					required: true,
					minlength: 4,
					email: true
				}
			},
			messages: {
				login:{
					required: "O nome é obrigatório",
					minlength: "Seu nome deve ter ao menos dois caracteres"
				},
				senha:{
					required:"A senha é obrigatória",
					minlength: "Sua senha deve ter ao menos dois caracteres"
				},
				email:{
					minlength: "Digite ao menos quatro caracteres",
					email: "O email deve obedecer à formatação-padrão (Exemplo: Abcd@gmail.com)",
					required: "O e-mail é obrigatório"
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