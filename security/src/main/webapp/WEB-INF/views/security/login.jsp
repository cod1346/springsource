<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Login</h1>

<!-- 
	spring security 로그인 페이지 규칙
	
	1) post 방식으로 action="/login" 지정
	2) 아이디를 입력 받은 요소의 name은 반드시 username 이여야 함
	3) 비밀번호를 입력 받는 요소의 name은 반드시 password 이여야 함
	4) post 방식으로 가는 모든 폼 안에서는 csrf 토큰 값이 포함되어 있어야 함
	

 -->


<main class="form-signin w-100 m-auto">
  <form action='<c:url value="/login"/>' method="post">
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

    <div class="form-floating">
      <input type="text" class="form-control" id="floatingInput" name = "username" placeholder="userid">
      <label for="floatingInput">UserId</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>

    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" name="remember-me"> Remember me(자동로그인)
      </label>
    </div>
    <div>
    	<span class="text-danger">${error}</span>
    </div>
    
    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
    
    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-body-secondary">&copy; 2017–2023</p>
  </form>
</main>
<%@ include file="../include/footer.jsp"%>