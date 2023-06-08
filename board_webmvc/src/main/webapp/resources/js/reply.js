let replyService = (function () {
  function add(reply, callback) {
    fetch("http://localhost:8080/replies/new", {
      method: "post",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(reply),
    })
      .then((response) => {
        if (!response) {
          throw new Error("데이터 확인");
        }
        return response.text();
      })
      .then((data) => {
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => console.log(error));
  } //add 종료

  function getList(param, callback) {
    let bno = param.bno;
    let page = param.page;
    fetch("/replies/pages/" + bno + "/" + page)
      .then((response) => {
        if (!response.ok) {
          throw new Error("리스트 없음");
        }
        return response.json();
      })
      .then((data) => {
        console.log("리스트와 갯수");
        console.log(data);
        console.log("리스트와 갯수");
        if (callback) {
          callback(data.replyCnt, data.list);
        }
      })
      .catch((error) => console.log(error));
  } //getList 종료

  function displayTime(timeVal) {
    const today = new Date();

    let gap = today.getTime() - timeVal;
    let dateObj = new Date(timeVal);

    let str = "";
    if (gap < 1000 * 60 * 60 * 24) {
      let hh = dateObj.getHours();
      let mi = dateObj.getMinutes();
      let ss = dateObj.getSeconds();

      return [
        (hh > 9 ? "" : "0") + hh,
        ":",
        (mi > 9 ? "" : "0") + mi,
        ":",
        (ss > 9 ? "" : "0") + ss,
      ].join("");
    } else {
      const yy = dateObj.getFullYear();
      const mm = dateObj.getMonth() + 1;
      const dd = dateObj.getDate();
      return [
        yy,
        "/",
        (mm > 9 ? "" : "0") + mm,
        "/",
        (dd > 9 ? "" : "0") + dd,
      ].join();
    }
  }

  function get(rno, callback) {
    fetch("http://localhost:8080/replies/" + rno)
      .then((response) => {
        if (!response.ok) {
          throw new Error("데이터 확인");
        }
        return response.json();
      })
      .then((data) => {
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => console.log(error));
  }

  function update(reply, callback) {
    fetch("http://localhost:8080/replies/" + reply.rno, {
      method: "put",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(reply),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("데이터 확인");
        }
        return response.text();
      })
      .then((data) => {
        callback(data);
      })
      .catch((error) => console.log(error));
  }

  function remove(rno, callback) {
    fetch("http://localhost:8080/replies/" + rno, {
      method: "delete",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("데이터 확인");
        }
        return response.text();
      })
      .then((data) => {
        callback(data);
      })
      .catch((error) => console.log(error));
  }
  return {
    add: add,
    getList: getList,
    displayTime: displayTime,
    get: get,
    update: update,
    remove: remove,
  };
})();
