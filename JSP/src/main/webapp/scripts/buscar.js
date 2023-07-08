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
				success: function(json) {


					
					$('#tabelaresultados tbody tr').remove();

					for (var p = 0; p < json.length; p++) {
						$('#tabelaresultados > tbody').append('<tr> <td>' + json[p].id + '</td> <td> ' + json[p].nome + '</td> <td><button type="button" class="btn btn-info">Ver</button></td></tr>');
					}

					document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
				}
			}).fail(function(xhr, status, errorThrown) {
				alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
			});
		} else {
			caixa.show();
		}
	});
});
