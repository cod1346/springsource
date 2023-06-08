/**
 *
 */
const form = document.querySelector("#registerForm");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  if (!form.checkValidity()) {
    // e.stopPropagation();
    form.classList.add("was-validated");
  } else {
    const lis = document.querySelectorAll(".uploadResult ul li");
    console.log(lis);

    let str = "";
    lis.forEach((element,idx) => {
      let path = element.dataset.path;
      let uuid = element.dataset.uuid;
      let filename = element.dataset.filename;
      let type = element.dataset.type;
      
      //<input type = 'hidden' name='attachList[].' value=''/>

      str+="<input type = 'hidden' name='attachList["+idx+"].uuid'"+"value='"+uuid+"'/>";
      str+="<input type = 'hidden' name='attachList["+idx+"].uploadPath'"+"value='"+path+"'/>";
      str+="<input type = 'hidden' name='attachList["+idx+"].fileName'"+"value='"+filename+"'/>";
      str+="<input type = 'hidden' name='attachList["+idx+"].fileType'"+"value='"+type+"'/>";
    });
    form.insertAdjacentHTML("beforeend",str);
    form.submit()
  }
});
