/**
 * 
 */
jQuery(function() {
	var caixa = jQuery("div.caixa_de_dialogo");
	caixa.hide();
	var buscar = jQuery("#buscar");

	buscar.click(function() {
		caixa.hide();
		var nomeBusca = document.getElementById('nomeBusca').value;

		if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {

			var urlAction = document.getElementById('formulario').action;

			$.ajax({

				method: "get",
				url: urlAction,
				data: "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
				success: function(response) {
					System.out.println(response);
				}
			}).fail(function(xhr, status, errorThrown) {
				alert('Erro ao buscar usu�rio por nome: ' + xhr.responseText);
			});
		}else{
			caixa.show();
		}
	});
});
