/**
function buscaPagAjax(url){
	
	var urlAction = document.getElementById('formulario').action;
    
	jQuery.ajax({
				method: "get",
				url: url,
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
						
						var url = 'nomeBusca='+nomeBusca+'&acao=buscarUserAjaxPage&pagina=' + (p * 5);
						
						jQuery("#ulPaginacaoUserAjax").append('<li style="margin-bottom: -15px;" class="page-item">
						<a class="page-link" onclick="buscaPagAjax(\''+url+'\')">' + (p + 1) + '</a></li>');
					}
				}
				
			}).fail(function(xhr, status, errorThrown) {
				alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
			});
 }
*/

function buscaPagAjax(url){
	
    var urlAction = document.getElementById('formulario').action;
    var nomeBusca = document.getElementById('nomeBusca').value;
    
	 jQuery.ajax({	     
	     method: "get",
	     url : urlAction,
	     data : url,
	     success: function (json, textStatus, xhr) {
		 
		 jQuery('#tabelaresultados > tbody > tr').remove();
		 jQuery("#ulPaginacaoUserAjax > li").remove();
		 
		  for(var p = 0; p < json.length; p++){
		      jQuery('#tabelaresultados > tbody').append('<tr><td style="height: 20px;">' + json[p].cep + '</td><td> ' + json[p].id + '</td><td> ' + json[p].nome + 
						'</td><td><button id="editar" onclick="editar('+json[p].id+')" type="button" style="margin: -15px;" class="btn btn-info">Ver</button></td></tr>');
		  }
		  
		  var totalPagina = xhr.getResponseHeader("totalPagina");
		  	    
			  for (var p = 0; p < totalPagina; p++){

			      var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ (p * 5);
			      alert(url);
			      jQuery("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>'); 
			  }
	     }
	 }).fail(function(xhr, status, errorThrown){
	    alert('Erro ao buscar usu�rio por nome: ' + xhr.responseText);
	 });
}
