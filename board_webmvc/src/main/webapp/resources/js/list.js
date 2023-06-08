checkModal(result);

history.replaceState({}, null, null);

function checkModal(result) {
  if (result === "" || history.state) return;

  if (parseInt(result) > 0) {
    document.querySelector(".modal-body").innerHTML =
      "게시글 " + result + "번이 등록되었습니다.";
  } else if (result == "modify") {
    document.querySelector(".modal-body").innerHTML = "수정이 완료되었습니다.";
  } else if (result == "delete") {
    document.querySelector(".modal-body").innerHTML = "삭제가 완료되었습니다.";
  }

  $("#registerModal").modal("show");
}

const pagination = document.querySelector(".pagination");
const operForm = document.querySelector("#operForm");
const operFrom2 = document.querySelector("#operForm input:nth-child(2)");
const operForm3 = document.querySelector("#operForm input:nth-child(3)");

pagination.addEventListener("click", (e) => {
  e.preventDefault();
  let href = e.target.getAttribute("href");

  operFrom2.value = href;

  operForm.submit();
});

const amount = document.querySelector("#amount");

amount.addEventListener("change", () => {
  operForm3.value = amount.value;

  operForm.submit();
});

const title = document.querySelectorAll(".move");

title.forEach((move) => {
  move.addEventListener("click", (e) => {
    e.preventDefault();

    let href = e.target.getAttribute("href");
    // const bno = "<input type='hidden' name='bno' value='" + href + "'>'";

    // operForm.insertAdjacentHTML("beforeend", bno);
    operForm.action = "/board/read";
    operForm.firstElementChild.value = href;

    operForm.submit();
  });
});

window.onpageshow = function (event) {
  if (event.persisted) {
    location.reload();
  }
};

const searchForm = document.querySelector("#searchForm");

searchForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const type = document.querySelector("#type");
  const keyword = document.querySelector("#keyword");

  console.log(keyword.value);
  console.log(type.value);
  if (type.value === "") {
    alert("검색 조건을 입력하세요");
    type.focos();
    return;
  } else if (keyword.value === "") {
    alert("검색어를 입력하세요");
    keyword.focus();
    return;
  }
  searchForm.submit();
});
