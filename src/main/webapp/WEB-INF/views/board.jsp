<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/css/title.css">
<link rel="stylesheet" href="../resources/css/content.css">
<link rel="stylesheet" href="../resources/css/board.css">
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
					<tr class="board_title_tr">
						<th class="board_title_td" width="7%">번호</th>
						<th class="board_title_td" width="9%">아이디</th>
						<th class="board_title_td" width="10%">이름</th>
						<th class="board_title_td" width="54%">제목</th>
						<th class="board_title_td" width="12%">등록일</th>
						<th class="board_title_td" width="8%">조회수</th>
					</tr>
					<c:forEach items="${bDtos }" var="bDto">
					<tr class="board_content_tr">
						<td class="board_content_td" align="center">${bDto.bnum }</td>
						<td class="board_content_td" align="center">${bDto.bid }</td>
						<td class="board_content_td" align="center">${bDto.bname }</td>
						<td class="board_content_td">
							<a class="board_link" href="contentView?bnum=${bDto.bnum }">
							<c:choose>
								<c:when test="${fn:length(bDto.btitle) > 30}">
									${fn:substring(bDto.btitle, 0, 30) }...
								</c:when>
								<c:otherwise>
									${bDto.btitle }
								</c:otherwise>
							</c:choose>
							</a>
						</td>
						<td class="board_content_td" align="center">
							${fn:substring(bDto.bdate, 0, 10) }
						</td>
						<td class="board_content_td" align="center">${bDto.bhit }</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="6">
							&nbsp
						</td>
					</tr>
					<tr>
						<td colspan="6" align="right">
							<input type="button" value="글쓰기" class="content_btn" onclick="javascript:location.href='write'">
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