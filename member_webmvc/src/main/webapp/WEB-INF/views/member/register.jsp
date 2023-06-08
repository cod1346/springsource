<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<h1>회원 가입 정보 입력</h1>
<form action='<c:url value = "/member/register"/>' method="post" novalidate>
	<div class="mb-3">
		<label for="userId" class="form-label">아이디</label>
		<div class="row">
			<div class="col">
				<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디" required pattern="^(?=[A-Za-z])(?=.*\d)[A-Za-z\d]{5,12}$">
				<div class="invalid-feedback">id를 확인해주세요</div>
			</div>
			<div class="col-2">
				<button type="button" class="w-100 btn btn-danger">중복아이디</button>
			</div>
		</div>
	</div>
	<div class="mb-3">
		<label for="password" class="form-label">비밀번호</label> <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호" required pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%])[A-Za-z\d!@#$%]{5,15}$">
		<div class="invalid-feedback">패스워드를 입력해주세요</div>
	</div>
	<div class="mb-3">
		<label for="name" class="form-label">이름</label> <input type="text" class="form-control" id="name" name="name" placeholder="이름" required pattern="^[가-힣]{2,5}$">
		<div class="invalid-feedback">이름을 입력해주세요</div>
	</div>
	<div class="mb-3">
		<label for="gender" class="form-label">성별</label> <input type="radio" class="form-check-input" value="남" name="gender" required />남 <input type="radio" class="form-check-input" value="여" name="gender" required />여
		<div class="invalid-feedback">성별을 입력해주세요</div>
	</div>
	<div class="mb-3">
		<label for="email" class="form-label">이메일</label> <input type="email" class="form-control" id="email" name="email" placeholder="이메일" required>
		<div class="invalid-feedback">이메일을 입력해주세요</div>
	</div>
	<button type="submit" class="btn btn-primary">회원가입</button>
	<button type="button" class="btn btn-success">취소</button>
</form>
<script>
	const path = '<c:url value="/index.jsp"/>'
</script>
<script src='<c:url value="/resources/js/register.js"/>'></script>
<%@ include file="../include/footer.jsp"%>