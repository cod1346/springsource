<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h3>도서정보</h3>
		<div>
			<input type="text" name="code1" id="code1"/>
			<button type="button" class="btn btn-info">특정 도서 조회</button>
			<button type="button" class="btn btn-danger">특정 도서 삭제</button>
		</div>
		<button type="button" class="btn btn-primary">도서 전체 목록</button>
		<div id="result" class="mt-3">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">code</th>
			      <th scope="col">title</th>
			      <th scope="col">writer</th>
			      <th scope="col">price</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>
		</div>
		<div>
			<form action='' method="post" id="insertForm">
				<div class="mb-3">
					<label for="code" class="form-label">code</label>
					<input type="text" class="form-control" id="code" name="code" placeholder="도서코드">
				</div>
				<div class="mb-3">
					<label for="title" class="form-label">title</label>
					<input type="text" class="form-control" id="title" name="title" placeholder="도서명">
				</div>
				<div class="mb-3">
					<label for="writer" class="form-label">writer</label>
					<input type="text" class="form-control" id="writer" name="writer" placeholder="작가">
				</div>
				<div class="mb-3">
					<label for="price" class="form-label">price</label>
					<input type="text" class="form-control" id="price" name="price" placeholder="가격">
				</div>
				<div class="mb-3">
					<label for="description" class="form-label">description</label>
					<textarea class="form-control" class="form-control" id="description" name="description" placeholder="상세정보"></textarea>
				</div>
				<button type="submit" class="btn btn-primary">도서추가</button>
				<button type="button" class="btn btn-success">목록으로</button>
			</form>
		</div>
		<div>
			<form action='' method="post" id="updateForm">
				<div class="mb-3">
					<label for="code" class="form-label">code</label>
					<input type="text" class="form-control" id="code2" name="code" placeholder="도서코드">
				</div>
				<div class="mb-3">
					<label for="price" class="form-label">price</label>
					<input type="text" class="form-control" id="price2" name="price" placeholder="가격">
				</div>
				<button type="submit" class="btn btn-primary">수정</button>
			</form>
		</div>
	</div>
<script src="/resources/book.js"></script>
</body>
</html>