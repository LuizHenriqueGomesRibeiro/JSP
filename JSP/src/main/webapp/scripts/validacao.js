jQuery(function() {
	if(jQuery("#formulario").length){
		jQuery("#formulario").validate({
			rules: {
				login: {
					required: true
				},
				nome: {
					required: true
				},
				email: {
					required: true
				},
				senha: {
					required: true
				},
				sexo: {
					required: true
				},
				perfil: {
					required: true
				},
				telefone: {
					required: true
				},
				cep: {
					required: true
				},
				rua: {
					required: true
				},
				bairro: {
					required: true
				},
				localidade: {
					required: true
				},
				numero: {
					required: true
				},
				uf: {
					required: true
				}
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
					required:"A senha é obrigatória"
				},
				sexo:{
					required:"O sexo é obrigatório"
				},
				perfil:{
					required:"O perfil é obrigatório"
				},
				telefone:{
					required:"Este campo é obrigatório"
				},
				cep:{
					required: "O CEP é obrigatório"
				},
				rua: {
					required: "A rua é obrigatória"
				},
				bairro: {
					required: "O bairro é obrigatório"
				},
				localidade: {
					required: "A localidade é obrigatória"
				},
				uf: {
					required: "O Estado é obrigatório"
				},
				numero: {
					required: "O número é obrigatório"
				}
			}
		});
	}
	else{
		validar();
	}
});

function validar() {
	document.forms["frmContato"].submit();
}