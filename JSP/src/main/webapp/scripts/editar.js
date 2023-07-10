/**
 * 
 */
function editar(id){
	var urlAction = document.getElementById('formulario').action;
    
    window.location.href = urlAction + '?acao=buscar&id='+id;
}
