<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <c:set var="root" value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 상단 메뉴 부분 -->
	<nav
		class="navbar navbar-expand-md bg-dark navbar-dark fixed-top shadow-lg">
		<a class="navbar-brand" href="${root }main">SoftCampus</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navMenu">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navMenu">
			<ul class="navbar-nav">
			<c:forEach var='obj' items='${topMenuList }'>
				<li class="nav-item">			<!-- 페이지에서 어떤 게시판인지 알아야 돼서 게시판 번호를 파라미터로 붙여줌  -->
					<a href="${root }board/main?board_info_idx=${obj.board_info_idx}" class="nav-link">${obj.board_info_name }</a>
				</li> <!-- 탑메뉴리스트 안에 들어있는 수 만큼 반복하면서 li태그를 만들게 됨  -->
			</c:forEach>
			</ul>


			<ul class="navbar-nav ml-auto">
			<c:choose>
				<c:when test="${loginUserBean.userLogin == true }">
					<li class="nav-item">
						<a href="${root }user/modify" class="nav-link">정보수정</a>
					</li>
					<li class="nav-item">
						<a href="${root }user/logout" class="nav-link">로그아웃</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="nav-item">
						<a href="${root }user/login" class="nav-link">로그인</a>
					</li>
					<li class="nav-item">
						<a href="${root }user/join" class="nav-link">회원가입</a>
					</li>
				</c:otherwise>
			</c:choose>
				
			
			</ul>
		</div>
	</nav>

</body>
</html>