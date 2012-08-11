<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Certificates" otherwise="/login.htm" redirect="/module/hr/admin/certificates.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="hr.certificates.manage" /></h2>
<a href="certificate.form"><spring:message code="hr.certificates.add"/></a>
<br /><br />

<b class="boxHeader">
<spring:message code="hr.certificates.current"/></b>
<form method="post" class="box">
	<table id="certificatesTable" width="100%">
		<tr>
			<th> <spring:message code="hr.certificate"/> </th>
			<th> <spring:message code="hr.certificates.agency"/> </th>
			<th> <spring:message code="hr.certificates.levels"/> </th>
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