<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Certificates" otherwise="/login.htm" redirect="/module/hr/admin/staffAttributeTypes.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage Certificates" /></h2>
<a href="certificate.form"><spring:message code="Add Certificate"/></a>
<br /><br />

<b class="boxHeader">
<spring:message code="Current Certificates"/></b>
<form method="post" class="box">
	<table id="certificatesTable" width="100%">
		<tr>
			<th> <spring:message code="Certificate"/> </th>
			<th> <spring:message code="Agency"/> </th>
			<th> <spring:message code="Levels"/> </th>
		</tr>
		<c:forEach var="certificate" items="${certificatesList}">
			<tr>
				<td valign="top">
					<a href="certificate.form?certificateId=${certificate.certificateId}">
						<c:choose>
							<c:when test="${certificate.retired == true}">
								<del>${certificate.certificate}</del>
							</c:when>
							<c:otherwise>
								${certificate.certificate}
							</c:otherwise>
						</c:choose>
					</a>
				</td>
				<td valign="top">${certificate.agency}</td>
				<td valign="top">${certificate.levels}</td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>