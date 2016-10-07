// Afirmo app 
//=============================================================
// Archivo: registrar.js
//Autores: G6 IS2 Grupo 6 del curso de Ingenieria de Software II

//DESCRIPCION
// ============================================================
// Este archivo se encarga de llamar al procedimiento en php utilizando
// AJAX (Metodo POST)
function registrar() {
var parametros = {
	"nombre" : document.getElementById("nombre").value,
	"empresa" : document.getElementById("empresa").value,
	"email" : document.getElementById("email").value,
	"usuario" : document.getElementById("usuario").value,
	"password" : document.getElementById("password").value,
	"repassword" : document.getElementById("repassword").value
};

$.ajax({
	data:  parametros,
	url:'http://192.168.8.100/afirmo/registrar_cls.php',
	type:  'POST',
	success:  function (response) {
		if (response.trim()=='correcto') {
			 alert("Usuario Registrado");
			window.location.href = 'index.html';
			
		}else{
			document.getElementById("alert").innerHTML=response;

		};
	}
});
}
