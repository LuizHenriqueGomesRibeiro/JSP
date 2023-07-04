/**
 * 
 */
jQuery(function() {

	var div = jQuery("div.confirmacao");
	var button = jQuery("#caixa");
	var cancelar = jQuery("#cancelar");

	div.hide();

	button.click(function() {
		div.toggle();
	});

	cancelar.click(function() {
		div.hide();
	});

	var deletar = jQuery("#deletar");

	deletar.click(function() {
		document.getElementById("formulario").method = "get";
		document.getElementById("acao").value = "deletar";
		document.getElementById("formulario").submit();
	});
});
