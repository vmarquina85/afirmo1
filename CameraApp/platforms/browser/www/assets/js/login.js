// Afirmo app 
//=============================================================
// Archivo: login.js
//Autores: G6 IS2 Grupo 6 del curso de Ingenieria de Software II

//DESCRIPCION
// ============================================================
// Este archivo se encarga de llamar al procedimiento en php utilizando
// AJAX (Metodo POST)




function Ingresar () {
	
var parametros = {
	"usuario" : document.getElementById("usuario").value,
	"password" : document.getElementById("password").value
};
$.ajax({
	data:  parametros,
	url:'http://192.168.8.100/afirmo/login_cls.php',
	type:  'POST',
	success:  function (response) {
		if (response.trim()=='correcto') {
			 window.location.href = 'bienvenido.html';
			
		}else{
			document.getElementById("alert").innerHTML=response;

		};
	}
});
}