fetch("/board/getAttachList?bno=" + bno)
  .then((response) => {
    if (!response.ok) {
      throw new Error("데이터 확인");
    }
    return response.json();
  })
  .then((data) => {
    data.forEach((element) => {});
    console.log(data);
    showUploadedFile(data);
  })
  .catch((error) => console.log(error));

const modifyForm = document.querySelector("#modifyForm");

modifyForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const lis = document.querySelectorAll(".uploadResult ul li");
  console.log(lis);

  let str = "";
  lis.forEach((element, idx) => {
    let path = element.dataset.path;
    let uuid = element.dataset.uuid;
    let filename = element.dataset.filename;
    let type = element.dataset.type;

    //<input type = 'hidden' name='attachList[].' value=''/>

    str += "<input type = 'hidden' name='attachList[" + idx + "].uuid'" + "value='" + uuid + "'/>";
    str +=
      "<input type = 'hidden' name='attachList[" + idx + "].uploadPath'" + "value='" + path + "'/>";
    str +=
      "<input type = 'hidden' name='attachList[" +
      idx +
      "].fileName'" +
      "value='" +
      filename +
      "'/>";
    str +=
      "<input type = 'hidden' name='attachList[" + idx + "].fileType'" + "value='" + type + "'/>";
  });
  modifyForm.insertAdjacentHTML("beforeend", str);

  console.log("수정폼");
  console.log(modifyForm);

  modifyForm.submit();
});

const form = document.querySelector("#operForm");

const info = document.querySelector(".btn-info");
const secondary = document.querySelector(".btn-secondary");
const danger = document.querySelector(".btn-danger");

if (danger) {
  danger.addEventListener("click", () => {
    form.action = "/board/remove";
    form.submit();
  });
}

secondary.addEventListener("click", () => {
  form.firstElementChild.remove();
  form.action = "/board/list";
  form.submit();
});

console.log(path);
