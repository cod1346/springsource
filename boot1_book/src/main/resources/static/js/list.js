const select = document.querySelector(".form-select");

const keyword = document.querySelector(".form-control");

const form = document.querySelector(".g-3")

form.addEventListener("submit",(e)=>{
	e.preventDefault();
	
	const select = document.querySelector(".form-select");

	const keyword = document.querySelector(".form-control");

	if(select.value=="검색기준선택"){
		alert("검색기준을 선택하시오");
		return;
	}
	else if(keyword.value.length==0){
		alert("검색어를 입력하시오");
		keyword.select()
		return;
	}
	form.submit();
})