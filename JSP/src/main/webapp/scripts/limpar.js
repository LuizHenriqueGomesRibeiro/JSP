jQuery(function(){
	
	var limpar = jQuery("#limpar");
	
	var nome = jQuery("#nome");
	var email = jQuery("#email");
	var login = jQuery("#login");
	var senha = jQuery("#senha");
	var id = jQuery("#id");
	
	limpar.click(function(){
		nome.val("");
		email.val("");
		login.val("");
		senha.val("");
		id.val("");
	});
});