<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>BOARD READ</h2>
	<form action="" method="post">
		<div>
			<label for="name">name</label>
			<input type="text" name="name" id="name" value='${boardDTO.name }' />
		</div>
		<div>
			<label for="password">password</label>
			<input type="password" name="password" id="password" value='${boardDTO.password }' />
		</div>
		<div>
			<label for="title">title</label>
			<input type="text" name="title" id="title"value='${boardDTO.title }' />
		</div>
		<div>
			<label for="content">content</label>
			<textarea name="content" id="" cols="30" rows="5">${boardDTO.content}</textarea>
		</div>
		<div>
			<button type="submit">등록</button>
		</div>
	</form>
</body>
</html>