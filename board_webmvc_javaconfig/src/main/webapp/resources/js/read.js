function showAttachFile(uploadResultArr) {
  let str = "";
  uploadResultArr.forEach((item) => {
    console.log("file타입 : " + item.fileType);
    if (item.fileType) {
      let fileCallPath = encodeURIComponent(
        item.uploadPath + "\\s_" + item.uuid + "_" + item.fileName
      );

      //str += "<li><image src='/display?fileName=" + fileCallPath + "'></li>";
      let oriFileCallPath = encodeURIComponent(
        item.uploadPath + "\\" + item.uuid + "_" + item.fileName
      );

      console.log("파일타입 이프");

      str += "<li data-path='" + item.uploadPath + "' data-uuid='" + item.uuid + "' ";
      str += " data-filename='" + item.fileName + "' data-type='" + item.fileType + "' >";
      str += "<a href='/display?fileName=" + oriFileCallPath + "' data-lightbox='image'>";
      str +=
        "<div class='text-center'><img src='/display?fileName=" + fileCallPath + "'></a></div>";
      str += "<small>" + item.fileName + "</small>";
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
      str += "</li>";
    }
  });
  document.querySelector(".uploadResult ul").insertAdjacentHTML("beforeend", str);
}
//게시물 조회
fetch("/board/getAttachList?bno=" + bno)
  .then((response) => {
    if (!response.ok) {
      throw new Error("데이터 확인");
    }
    return response.json();
  })
  .then((data) => {
    data.forEach((element) => {});
    showAttachFile(data);
  })
  .catch((error) => console.log(error));

const form = document.querySelector("#operForm");

const info = document.querySelector(".btn-info");
const secondary = document.querySelector(".btn-secondary");

info.addEventListener("click", () => {
  form.action = "/board/modify";
  form.submit();
});

secondary.addEventListener("click", () => {
  form.firstElementChild.remove();
  form.action = "/board/list";
  form.submit();
});

let page = 1;
let chat = document.querySelector(".chat");
showList(page);

function showReplyPage(total) {
  let endPage = Math.ceil(page / 10.0) * 10;
  let startPage = endPage - 9;
  let prev = startPage != 1;
  let next = false;

  if (endPage * 10 >= total) {
    endPage = Math.ceil(total / 10.0);
  }
  console.log("페이지나누기 토탈 : " + total);
  if (endPage * 10 < total) {
    next = true;
  }

  let str = "<ul class='pagination justify-content-center-end'>";
  if (prev) {
    str +=
      "<li class='page-item'><a class='page-link' href='" + (startPage - 1) + "'>Previous</a></li>";
  }
  for (let idx = startPage; idx <= endPage; idx++) {
    let active = page == idx ? "active" : "";
    str +=
      "<li class='page-item " +
      active +
      "'><a class='page-link' href='" +
      idx +
      "'>" +
      idx +
      "</a> </li>";
  }
  if (next) {
    str += "<li class='page-item'><a class='page-link' href='" + (endPage + 1) + "'>Next</a></li>";
  }
  str += "</ul>";
  document.querySelector(".card-footer").innerHTML = str;
}

document.querySelector(".card-footer").addEventListener("click", (e) => {
  e.preventDefault();
  page = e.target.getAttribute("href");
  // console.log(page);
  showList(page);
});

function showList(pageNum) {
  replyService.getList({ bno: bno, page: page || 1 }, (total, result) => {
    console.log("read.js에서 확인");
    console.log(total);
    console.log("result : " + result);

    if (pageNum == -1) {
      page = Math.ceil(total / 10.0);
      showList(page);
      return;
    }

    if (result == null || result.length == 0) {
      chat.innerHTML = "";
      return;
    }
    let str = "";
    for (let idx = 0; idx < result.length; idx++) {
      str += "<li class='list-group-item border-bottom' data-rno='" + result[idx].rno + "'>";
      str += "<div class='d-flex justify-content-between'>";
      str += "<strong class='primary-font'>" + result[idx].replyer + "</strong>";
      str +=
        "<small class='text-muted text-right'>" +
        replyService.displayTime(result[idx].replydate) +
        "</small>";
      str += "</div>";
      str += "<p>" + result[idx].reply + "</p>";
      str += "<div class='btn-group btn-group-sm'>";
      str += "<button class='btn btn-warning' type='button'>수정</button>";
      str += "<button class='btn btn-danger' type='button'>삭제</button>";
      str += "</div>";
      str += "</li>";
    }
    chat.innerHTML = str;
    showReplyPage(total);
  });
}

//document.querySelector(".btn-block").addEventListener("click");
document.querySelector("#replyForm").addEventListener("submit", (e) => {
  e.preventDefault();
  const reply = document.querySelector("#reply1");
  const replyer = document.querySelector("#replyer1");
  replyService.add(
    {
      bno: bno,
      reply: reply.value,
      replyer: replyer.value,
    },
    (result) => {
      if (result === "success") {
        alert("댓글등록완료");
        reply.value = "";
        replyer.value = "";
        showList(-1);
      }
    }
  );
});

chat.addEventListener("click", (e) => {
  let li = e.target.closest("li");

  //data-속성 값 가져오기 : dataset
  let rno = li.dataset.rno;

  if (e.target.classList.contains("btn-warning")) {
    //댓글하나가져오기
    replyService.get(rno, (result) => {
      console.log(result);
      let rno = (document.querySelector("#rno").value = result.rno);
      let reply = (document.querySelector("#reply").value = result.reply);
      let replyer = (document.querySelector("#replyer").value = result.replyer);
    });

    $("#replyModal").modal("show");
  } else if (e.target.classList.contains("btn-danger")) {
    replyService.remove(rno, (result) => {
      if (result === "success") {
        alert("삭제 성공");
        showList(page);
      }
    });
  }
});

document.querySelector(".modal-footer .btn-primary").addEventListener("click", () => {
  const updateReply = {
    rno: document.querySelector("#rno").value,
    reply: document.querySelector("#reply").value,
  };
  replyService.update(updateReply, (result) => {
    if (result === "success") {
      $("#replyModal").modal("hide");
      showList(page);
    }
  });
});
