<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage TrainingClasses" otherwise="/login.htm" redirect="/module/hr/admin/trainingClasses.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage Training Classes" /></h2>
<a href="training.form"><spring:message code="Add Training"/></a>
<br /><br />

<b class="boxHeader">
<spring:message code="Current Training Classes"/></b>
<form method="post" class="box">
	<table id="TrainingClassesTable" width="100%">
		<tr>
			<th> <spring:message code="Training"/> </th>
			<th> <spring:message code="Training Category"/> </th>
			<th> <spring:message code="Organization"/> </th>
		</tr>
		<c:forEach var="training" items="${trainingClassesList}">
			<tr>
				<td valign="top">
					<a href="trainingClass.form?trainingClassId=${trainingClass.trainingClassId}">
						<c:choose>
							<c:when test="${trainingClass.retired == true}">
								<del>${trainingClass.hrTraining.name}</del>
							</c:when>
							<c:otherwise>
								${trainingClass.hrTraining.name}
							</c:otherwise>
						</c:choose>
					</a>
				</td>
				<td valign="top">${trainingClass.hrTraining.category}</td>
				<td valign="top">${trainingClass.organization}</td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>