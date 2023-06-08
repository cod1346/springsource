const form = document.querySelector("form");

document.querySelector(".btn-success").addEventListener("click",()=>{
	location.href=path
})

form.addEventListener("submit",(e)=>{
	e.preventDefault();
	const code = document.querySelector("#code")
	const title = document.querySelector("#title")
	const writer = document.querySelector("#writer")
	const price = document.querySelector("#price")
	
	if(code.value.length!==4||code.value===""||isNaN(code.value)){
		alert("코드를 확인하시오");
		code.select();
		return;
	}else if(title.value===""){
		alert("도서명을 확인하시오");
		title.select();
		return;
	} else if(writer.value===""){
		alert("저자명을 확인하시오");
		writer.select();
		return;
	} else if(price.value===""||isNaN(price.value)){
		alert("가격을 확인하시오");
		price.select();
		return;
	}
	form.submit();
})