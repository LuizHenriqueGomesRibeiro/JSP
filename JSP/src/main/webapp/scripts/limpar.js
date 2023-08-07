jQuery(function(){
	
	var limpar = jQuery("#limpar");
	
	var nome = jQuery("#nome");
	var email = jQuery("#email");
	var login = jQuery("#login");
	var senha = jQuery("#senha");
	var id = jQuery("#id");
	var cep = jQuery("#cep");
	var rua = jQuery("#rua");
	var bairro = jQuery("#bairro");
	var localidade = jQuery("#localidade");
	var uf = jQuery("#uf");
	var numero = jQuery("#numero");
	var dataNascimento = jQuery("#dataNascimento");
	var renda = jQuery("#renda");
	
	limpar.click(function(){
		nome.val("");
		email.val("");
		login.val("");
		senha.val("");
		id.val("");
		cep.val("");
		rua.val("");
		bairro.val("");
		localidade.val("");
		uf.val("");
		numero.val("");
		dataNascimento.val("");
		renda.val("");
	});
});

jQuery(function(){
	var telefone = jQuery("#telefoneEsconder");
	var limpar = jQuery("#limpar");
	var caixa = jQuery("#caixa");
	
	limpar.click(function(){
		telefone.hide();
		caixa.hide();
	})
})

jQuery(function(){
	
	var limpar = jQuery("#limpar_pesquisa");
	var nome = jQuery("#nome_pesquisa");
	
	limpar.click(function(){
		nome.val("");
	});
});