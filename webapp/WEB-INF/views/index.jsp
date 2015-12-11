<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.hanains.emaillist.dao.EmailListDao" %>
<%@ page import = "com.hanains.emaillist.vo.EmailListVo" %>
<%@ page import = "java.util.List" %>


<%
	List<EmailListVo> list = (List<EmailListVo>)request.getAttribute("list");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메일 리스트에 가입되었습니다.</h1>
	<p>입력한 정보 내역입니다.</p>
	<!-- 메일정보 리스트 -->
	<%-- list에서 하나씩 빼서 테이블를 채운다--%>
	

	<c:set var = "count" value = "${fn:length(list)}"/>
	--${count}--
	<c:forEach items = "${list }" var = "vo" varStatus = "status">
	
	<table border="1" cellpadding="5" cellspacing="2">
		<tr>
		<td colspan="2"> ${status.index } : ${status.count } </td>
		<td>count : ${count-status.index }</td>
		</tr>
		
		<tr>
			<td align=right>First name: </td>
			<td>${vo.firstName }</td>
		</tr>
		<tr>
			<td align=right width="110">Last name: </td>
			<td width="110">${vo.lastName }</td>
		</tr>
		<tr>
			<td align=right>Email address: </td>
			<td>${vo.email }</td>
		</tr>
	</table>
	

	</table>
	<br>
</c:forEach>
	<p>
		<a href = "/emaillist3/form"> 추가메일 등록</a>  머해야됨
	</p>
	<br>
</body>
</html>