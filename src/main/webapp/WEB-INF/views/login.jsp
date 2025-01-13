<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/css/title.css">
<link rel="stylesheet" href="../resources/css/content.css">
<title>회원가입</title>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	
	<table border="0" cellpadding="20" cellspacing="0" width="100%">
		<tr>
			<td align="center">
				<span class="title_text01">DEVELOPER TAEYOUNG's PROFILE</span>
			</td>
		</tr>
		<tr>
			<td align="center">
				<span class="title_text02">I'm Taeyoung Lim, a developer who wants a development job. Please call me back.</span>
			</td>
		</tr>
		<tr>
			<td align="center" height="500">
				<table border="0" cellpadding="10" cellspacing="0"  width="60%" class="content_box" height="530">
				<form action="joinOk" method="post" name="joinForm">
					<tr>
						<td colspan="2">
							&nbsp
						</td>
					</tr>
					<tr>
						<td colspan="2">
							&nbsp
						</td>
					</tr>
					<tr>
						<td colspan="2">
							&nbsp
						</td>
					</tr>
					<tr>
						<td align="right" width="40%">
							<span class="content_text">아이디 :</span> 
						</td>
						<td>
							<input type="text" name="mid" class="input_box">
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="content_text">비밀번호 :</span> 
						</td>
						<td>
							<input type="password" name="mpw" class="input_box">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							&nbsp
						</td>
					</tr>
					<tr>
						<td colspan="2">
							&nbsp
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="회원가입" class="content_btn">&nbsp&nbsp
							<input type="button" value="로그인" class="content_btn">
						</td>
					</tr>
				</form>
				</table>
			</td>
		</tr>
	</table>
	
	<%@ include file="include/footer.jsp" %>
</body>
</html>