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

			jQuery.ajax({

				method: "get",
				url: urlAction,
				data: "&nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
				success: function(json, textStatus, xhr) {
					
					jQuery('#tabelaresultados tbody tr').remove();
					jQuery("#ulPaginacaoUserAjax li").remove();
	
					for (var p = 0; p < json.length; p++) {
						jQuery('#tabelaresultados > tbody').append('<tr><td style="height: 20px;">' + 
						json[p].cep + '</td><td> ' + json[p].id + '</td><td> ' + json[p].nome + 
						'</td><td><button id="editar" onclick="editar('+json[p].id+')" type="button" style="margin: -15px;" class="btn btn-info">Ver</button></td></tr>');
					}
					
					var totalPagina = xhr.getResponseHeader("totalPagina");

					for(var p = 0; p < totalPagina; p++){
						var url = "nomeBusca="+nomeBusca+"&acao=buscarUserAjaxPage&pagina=" + p * 5;

						jQuery("#ulPaginacaoUserAjax").append('<li style="margin-bottom: -15px;" class="page-item"><a class="page-link" onclick="buscaPagAjax(\''+url+'\')">' + (p + 1) + '</a></li>');
					}
				}
			}).fail(function(xhr, status, errorThrown) {
				alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
			});
		} else {
			caixa.show();
		}
	});
});
