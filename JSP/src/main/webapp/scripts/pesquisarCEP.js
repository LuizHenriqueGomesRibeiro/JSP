/**
 * 
 */
function pesquisarCEP() {
	
	var cep = jQuery("#cep").val();

	jQuery.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function(dados) {
		
		if (!("erro" in dados)) {
			jQuery("#cep").val(dados.cep);
			jQuery("#rua").val(dados.logradouro);
			jQuery("#bairro").val(dados.bairro);
			jQuery("#localidade").val(dados.localidade);
			jQuery("#uf").val(dados.uf);
			jQuery("#numero").val(dados.siafi);
		} else {
			alert("Este CEP não é válido.");
		}
	});
}

