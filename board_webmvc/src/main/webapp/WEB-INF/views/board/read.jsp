<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">게시물 조회</h1>
</div>
<div class="row">
	<div class="col">
		<form action="/board/register" method="post">
		  <div class="form-group">
		    <label for="제목">제목</label>
		    <input type="text" class="form-control" id="title"  name="title" readonly value='${dto.title }'>
		  </div>
		  <div class="form-group">
		    <label for="내용">내용</label>
		    <textarea class="form-control" id="content" rows="10" name="content" readonly >${dto.content }</textarea>
		  </div>
		  <div class="form-group">
		    <label for="글쓴이">글쓴이</label>
		    <input type="text" class="form-control" id="writer" name="writer" readonly value='${dto.writer }'>
		  </div>
		  <button type="button" class="btn btn-info">수정</button>
		  <button type="button" class="btn btn-secondary">목록</button>
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
				<div class="uploadResult">
					<ul></ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 댓글 작성 폼 시작-->
<form action="" class="mt-3" id="replyForm">
	<div class="form-row">
		<div class="col-11">
			<textarea rows="5" name="reply" id="reply1" class="form-control" placeholder="내용"></textarea>
		</div>
		<div class="col my-2">
			<div class="form-row my-2">
				<input type="text" name="replyer" id="replyer1" class="form-control" placeholder="작성자" />
			</div>
			<div class="form-row my-2">
				<button class="btn btn-success btn-block">댓글 작성</button>
			</div>
		</div>
	</div>
</form>
<!-- 댓글 작성 폼 종료 -->

<!-- 댓글 목록 시작 -->
<div class="card mt-4">
	<div class="card-header">
		<i class="fa fa-comments fa-fw"></i>
		댓글
	</div>
	<div class="card-body">
		<ul class="chat list-group list-group-flush">
			<li class='list-group-item border-bottom' data-rno='1'>
				<div class="d-flex justify-content-between">
					<strong class='primary-font'>user00</strong>
					<small class='text-muted text-right'>2023-05-24 00:00</small>
				</div>
				<p>Good J!o!b!</p>
				<div class="btn-group btn-group-sm">
					<button class="btn btn-warning" type="button">수정</button>
					<button class="btn btn-danger" type="button">삭제</button>
				</div>
			</li>
		</ul>
	</div>
	<div class="card-footer">
	<!-- 댓글 페이지 나누기 시작 -->
	
	<!-- 댓글 페이지 나누기 종료 -->
	</div>
</div>
<!-- 댓글 목록 종료 -->

<!-- 댓글 수정 폼 시작 -->
<div class="modal" tabindex="-1" id="replyModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">댓글 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <input type="hidden" name="rno" id="rno" />
        <div class="form-group">
        	<textarea name="reply" id="reply" rows="4" class="form-control" ></textarea>
        </div>
        <div class="form-group">
        	<input type="text" name="replyer" id="replyer" class="form-control" readonly/>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">수정</button>
      </div>
    </div>
  </div>
</div>
<button type="button" id="tete">ddddddddd</button>
<!-- 댓글 수정 폼 종료-->



<form action="" id="operForm">
	<input type="hidden" name="bno" value='${dto.bno }' />
	<input type="hidden" name="page" value='${cri.page }' />
	<input type="hidden" name="amount" value='${cri.amount }' />
	<input type="hidden" name="type" value='${cri.type }' />
	<input type="hidden" name="keyword" value='${cri.keyword }' />
</form>
<script>
	const bno = ${dto.bno};
</script>
<script src="/resources/js/reply.js"></script>
<script src="/resources/js/read.js"></script>
<%@ include file="../include/footer.jsp"%>