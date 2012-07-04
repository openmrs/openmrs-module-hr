<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Competencies" otherwise="/login.htm" redirect="/module/hr/admin/staffAttributeTypes.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage Competencies" /></h2>
<a href="competency.form"><spring:message code="Add competency"/></a>
<br /><br />

<b class="boxHeader">
<spring:message code="Current Competencies"/></b>
<form method="post" class="box">
	<table id="CompetenciesTable" width="100%">
		<tr>
			<th> <spring:message code="Name"/> </th>
			<th> <spring:message code="Levels"/> </th>
		</tr>
		<c:forEach var="competency" items="${competenciesList}">
			<tr>
				<td valign="top">
					<a href="competency.form?competencyId=${competency.competencyId}">
						<c:choose>
							<c:when test="${competency.retired == true}">
								<del>${competency.name}</del>
							</c:when>
							<c:otherwise>
								${competency.name}
							</c:otherwise>
						</c:choose>
					</a>
				</td>
				<td valign="top">${competency.levels}</td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>