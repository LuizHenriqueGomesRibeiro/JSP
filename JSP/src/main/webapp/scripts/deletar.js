/**
 * 
 */
jQuery(function(){
	var button = jQuery("#deletar");
	button.click(function(){
		deletar();
	});
});

function deletar(){
	document.getElementById("formulario").method = "get";
	document.getElementById("acao").value = "deletar";
	document.getElementById("formulario").submit();
}
