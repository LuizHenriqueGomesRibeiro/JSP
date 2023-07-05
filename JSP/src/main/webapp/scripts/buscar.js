/**
 * 
 */
jQuery(function() {
	var caixa = jQuery("div.caixa_de_dialogo");
	caixa.hide();
	var buscar = jQuery("#buscar");

	buscar.click(function() {
		var nome = jQuery("#nome_pesquisa").val();

		if (nome != null && nome != '') {
			alert("teste");
			caixa.hide();
		}else{
			caixa.show();
		}
	});
});