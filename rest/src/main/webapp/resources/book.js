const content = document.querySelector(".table tbody");
document.querySelector(".btn-primary").addEventListener("click", () => {
  fetch("/list")
    .then((response) => response.json())
    .then((data) => {
      let result = "";
      data.forEach((element, idx) => {
        result += "<tr>";
        result += "<th scope='row'>" + element.code + "</th>";
        result += "<td>" + element.title + "</td>";
        result += "<td>" + element.writer + "</td>";
        result += "<td>" + element.price + "</td>";
        result += "</tr>";
      });
      content.innerHTML = result;
    })
    .catch((error) => console.log(error));
});

document.querySelector(".btn-info").addEventListener("click", () => {
  const code = document.querySelector("#code1").value;
  fetch("/" + code)
    .then((response) => {
      if (!response.ok) {
        throw new Error("url 확인");
      }
      return response.json();
    })
    .then((data) => {
      let result = "";
      result += "<tr>";
      result += "<th scope='row'>" + data.code + "</th>";
      result += "<td>" + data.title + "</td>";
      result += "<td>" + data.writer + "</td>";
      result += "<td>" + data.price + "</td>";
      result += "</tr>";
      content.innerHTML = result;
    })
    .catch();
});

const form = document.querySelector("#insertForm");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  let data = {
    code: document.querySelector("#code").value,
    title: document.querySelector("#title").value,
    writer: document.querySelector("#writer").value,
    price: document.querySelector("#price").value,
    description: document.querySelector("#description").value,
  };
  console.log(data);

  fetch("/create", {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("데이터 확인");
      }
      return response.text();
    })
    .then((data) => {
      if (data === "success") {
        alert("입력 성공");
      } else {
        alert("입력 실패");
      }
    })
    .catch((error) => alert(error));
});

document.querySelector(".btn-danger").addEventListener("click", () => {
  const code = document.querySelector("#code1").value;
  fetch("/" + code, {
    method: "delete",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("데이터 확인");
      }
      return response.text();
    })
    .then((data) => {
      if (data === "success") {
        alert("삭제성공");
      }
    })
    .catch((error) => alert(error));
});

const updateForm = document.querySelector("#updateForm");

updateForm.addEventListener("submit", (e) => {
  e.preventDefault();
  let data = {
    code: document.querySelector("#code2").value,
    price: document.querySelector("#price2").value,
  };
  fetch("/update", {
    method: "put",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("데이터 확인");
      }
      return response.text();
    })
    .then((data) => {
      if (data === "success") {
        alert("수정성공");
      }
    })
    .catch((error) => alert(error));
});
