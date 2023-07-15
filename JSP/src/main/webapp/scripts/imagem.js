/**
 * 
 */
function visualizarImg(fotoembase64, filefoto){
	var preview = document.getElementById(fotoembase64);
	var fileUser = document.getElementById(filefoto).files[0];
	var reader = new FileReader();

	reader.onloadend = function(){
		preview.src = reader.result;
	};
	
	if(fileUser){
		reader.readAsDataURL(fileUser);
	}else{
		preview.src = '';
	}
}
