
const form = document.querySelector("form");

console.log(password);

form.addEventListener("submit",(e)=>{
	e.preventDefault();
	
	const currentPassword = document.querySelector("#floatingPassword1");
	const newPassword = document.querySelector("#floatingPassword2");
	const confirmPassword = document.querySelector("#floatingPassword3");
	
	if(newPassword.value!=confirmPassword.value||newPassword.value==""){
		alert("비밀번호확인");
		return;
	}
		form.submit();
});