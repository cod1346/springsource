document.querySelector("#uploadBtn").addEventListener("click", () => {
  const formData = new FormData();

  let inputFiles = document.querySelector("#uploadFile").files;
  console.log(inputFiles);

  for (let i = 0; i < inputFiles.length; i++) {
    formData.append("uploadFile", inputFiles[i]);
  }

  fetch("/uploadAjax", {
    method: "post",
    body: formData,
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error();
      }
      return response.json();
    })
    .then((data) => {
      console.log(data);
      showUploadedFile(data);
    })
    .catch((error) => console.log(error));
});

function showUploadedFile(uploadResultArr) {
  let str = "";
  uploadResultArr.forEach((item) => {
    if (item.fileType) {
      let fileCallPath = encodeURIComponent(
        item.uploadPath + "\\s_" + item.uuid + "_" + item.fileName
      );

      //str += "<li><image src='/display?fileName=" + fileCallPath + "'></li>";
      let oriFileCallPath = encodeURIComponent(
        item.uploadPath + "\\" + item.uuid + "_" + item.fileName
      );

      str += "<li data-path='" + item.uploadPath + "' data-uuid='" + item.uuid + "' ";
      str += " data-filename='" + item.fileName + "' data-type='" + item.fileType + "' >";
      str += "<a href='/display?fileName=" + oriFileCallPath + "' data-lightbox='image'>";
      str +=
        "<div class='text-center'><img src='/display?fileName=" + fileCallPath + "'></a></div>";
      str += "<small>" + item.fileName + "</small>";
      str += "<span data-file='" + fileCallPath + "' data-type='image'> X </span>";
      str += "</li>";
    } else {
      let fileCallPath = encodeURIComponent(
        item.uploadPath + "\\" + item.uuid + "_" + item.fileName
      );

      str += "<li data-path='" + item.uploadPath + "' data-uuid='" + item.uuid + "' ";
      str += " data-filename='" + item.fileName + "' data-type='" + item.fileType + "' >";
      str += "<a href='/download?fileName=" + fileCallPath + "'>";
      str += "<div class='text-center'><img src='/resources/img/txt-file.png'></div>";
      str += "<small>" + item.fileName + "</small></a>";
      str += "<span data-file='" + fileCallPath + "' data-type='file'> X </span>";
      str += "</li>";
    }
  });
  document.querySelector(".uploadResult ul").insertAdjacentHTML("beforeend", str);
}

document.querySelector(".uploadResult").addEventListener("click", (e) => {
  //data-@@ 태그 속성 값 가져오기 : dataset.@@

  const targetFile = e.target.dataset.file;
  const type = e.target.dataset.type;
  console.log("data-file = " + targetFile + ",data-type = " + type);
  const li = e.target.closest("li");

  const formData = new FormData();
  formData.append("fileName", targetFile);
  formData.append("type", type);
  //const data = new URLSearchParams(formData);

  fetch("deleteFile", {
    method: "post",
    body: formData,
  })
    .then((response) => {
      if (!response) {
        throw new Error("파일 제거 실패");
      }
      return response.text();
    })
    .then((data) => {
      console.log(data);
      li.remove();
    })
    .catch((error) => console.log(error));
});
