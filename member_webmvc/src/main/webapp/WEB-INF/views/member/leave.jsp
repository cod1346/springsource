<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>회원탈퇴</h1>
<main class="form-signin w-100 m-auto">
  <form action='<c:url value="/member/leave"/>' method="post">
    <div class="form-floating">
      <input type="text" class="form-control" id="floatingInput" name = "userId" placeholder="userid" value="${authDTO.userId }">
      <label for="floatingInput">UserId</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>

    <button class="w-100 btn btn-lg btn-danger" type="submit">회원탈퇴</button>
  </form>
</main>
<%@ include file="../include/footer.jsp"%>