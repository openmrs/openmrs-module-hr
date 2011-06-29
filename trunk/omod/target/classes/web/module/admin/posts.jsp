<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage Posts" /></h2>
<a href="post.form"><spring:message code="Add New Post"/></a>
<br/>
<br/>
<b class="boxHeader">
	<a style="display: block; float: right" href="#">
		<spring:message code="general.toggle.retired" />
	</a>
	<spring:message code="Current Posts"/>
</b>
<form method="post" class="box">
<table id="PostsTable" width="100%">
		<tr>
			<th> <spring:message code="Post Id" /> </th>
			<th> <spring:message code="Job Title" /> </th>
			<th> <spring:message code="Location" /> </th>
			<th> <spring:message code="Time Basis" /> </th>
			<th> <spring:message code="Status" /> </th>
			<th> <spring:message code="Funding Source" /> </th>
		</tr>
		<c:forEach var="post" items="${PostList}" varStatus="rowStatus">
			<tr <c:if test="${post.retired}">class="retired ${rowStatus.index % 2 == 0 ? 'evenRow' : 'oddRow' }"</c:if>  <c:if test="${!post.retired}">class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" }'</c:if> >
				<td valign="top" width="10%">
					<a href="post.form?postId=${post.postId}">${post.postId}</a>
				</td>
				<td valign="top" width="20%">${post.hrJobTitle.title}</td>
				<td valign="top" width="20%">${post.location.name}</td>
				<td valign="top" width="10%">${post.timeBasis}	</td>
				<td valign="top" width="10%">${post.status.name}</td>
				<td valign="top" width="10%">${post.fundingSource}</td>
			</tr>
		</c:forEach>
	</table>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>