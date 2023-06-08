<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">게시물 등록</h1>
</div>
<div class="row">
	<div class="col">
		<form action="" method="post" id="registerForm" novalidate>
		  <div class="form-group">
		    <label for="제목">제목</label>
		    <input type="text" class="form-control" id="title" placeholder="제목" name="title" required>
		  	<div class="invalid-feedback">
		  		제목을 확인해 주세요
		  	</div>
		  </div>
		  <div class="form-group">
		    <label for="내용">내용</label>
		    <textarea class="form-control" id="content" rows="10" name="content" placeholder="내용"required></textarea>
		  	<div class="invalid-feedback">
		  		내용을 확인해 주세요
		  	</div>
		  </div>
		  <div class="form-group">
		    <label for="글쓴이">글쓴이</label>
		    <input type="text" class="form-control" id="writer" placeholder="글쓴이" name="writer"required>
		  	<div class="invalid-feedback">
		  		작성자를 확인해 주세요
		  	</div>
		  </div>
		  <button type="submit" class="btn btn-primary">등록</button>
		  <button type="reset" class="btn btn-secondary">취소</button>
		</form>
	</div>
</div>
<div class="row mt-3">
	<div class="col">
		<div class="card">
			<div class="card-header">
				<i class="fa fa-fale"></i>
				파일첨부
			</div>
			<div class="card-body">
				<div class="form-group uploadDiv">
					<input type="file" name="uploadFile" id="uploadFile"  accept="image/*,.txt"multiple />
				</div>
				<div class="uploadResult">
					<ul></ul>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	const path = '${pageContext.request.requestURI}';
</script>
<script src="/resources/js/register.js"></script>
<script src="/resources/js/upload.js"></script>
<%@ include file="../include/footer.jsp"%>