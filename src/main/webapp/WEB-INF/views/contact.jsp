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
				<form action="loginOk" method="post" name="loginForm">
					<tr>
						<td>
							&nbsp
						</td>
					</tr>
					<tr>
						<td>
							&nbsp
						</td>
					</tr>
					<tr>
						<td>
							&nbsp
						</td>
					</tr>
					<tr>
						<td align="center" width="40%">
							<span class="content_text">
								안녕하세요. 개발자 임태영입니다.<br>
								보고계시는 페이지는 springboot로 개발되었습니다.<br>
								관심있으면 limbirdy@naver.com 으로 연락주세요.
							</span> 
						</td>
					</tr>
					<tr>
						<td>
							&nbsp
						</td>
					</tr>
					<tr>
						<td>
							&nbsp
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							&nbsp
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