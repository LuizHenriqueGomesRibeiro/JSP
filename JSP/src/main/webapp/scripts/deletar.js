/**
 * 
 */
jQuery(function() {

	var div = jQuery("div.confirmacao");
	var button = jQuery("#caixa");
	var cancelar = jQuery("#cancelar");
	var deletar = jQuery("#deletar");

	div.hide();

	button.click(function() {
		div.toggle();
	});

	cancelar.click(function() {
		div.hide();
	});

	deletar.click(function() {
		document.getElementById("formulario").method = "get";
		document.getElementById("acao").value = "deletar";
		document.getElementById("formulario").submit();
	});
});