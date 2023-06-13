<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<h1>비밀번호 변경</h1>
<main class="form-signin w-100 m-auto">
  <form action='<c:url value="/member/changePwd"/>' method="post">
    <div class="form-floating">
      <input type="text" class="form-control" id="floatingInput" name = "userId" placeholder="userid" value="${authDTO.userId }" readonly>
      <label for="floatingInput">아이디</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword1" name="currentpassword" placeholder="기존비밀번호">
      <label for="floatingPassword1">기존 비밀번호</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword2" name="newpassword" placeholder="새 비밀번호">
      <label for="floatingPassword2">새 비밀번호</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword3" name="confirmpassword" placeholder="새 비밀번호 확인">
      <label for="floatingPassword3">새 비밀번호 확인</label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit">비밀번호 변경</button>
  </form>
</main>
<script>
const password="${loginDto.password}";
console.log(password+"이거");</script>
<script src='<c:url value="/js/change.js"/>'></script>
<%@ include file="../include/footer.jsp"%>