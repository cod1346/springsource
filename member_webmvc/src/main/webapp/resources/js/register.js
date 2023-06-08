
const form = document.querySelector("form");



form.addEventListener("submit", (e) => {
	if (!form.checkValidity()) {
		e.preventDefault();
		e.stopPropagation();
	}

	form.classList.add("was-validated");
})


document.querySelector(".btn-danger").addEventListener('click',()=>{
 	//사용자가 입력한 userid 가져오기
 	const userId = document.querySelector("#userId").value;
 	
 	
 	fetch("/member/dupId",{
 		method:"post",		
 		body: new URLSearchParams({userId:userId})
 		
 	})
 	.then((response)=>response.text())
 	.then((result) => {
 		console.log(result);
 		if(result.trim() === "true"){
 			alert("아이디를 사용할 수 있습니다new");
 		}else{
 			alert("아이디를 사용할 수 없습니다new");
 		}
 	});
 })  