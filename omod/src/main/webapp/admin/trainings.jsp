<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Trainings" otherwise="/login.htm" redirect="/module/hr/admin/trainings.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage Trainings" /></h2>
<a href="training.form"><spring:message code="Add training"/></a>
<br /><br />

<b class="boxHeader">
<spring:message code="Current Trainings"/></b>
<form method="post" class="box">
	<table id="TrainingsTable" width="100%">
		<tr>
			<th> <spring:message code="Name"/> </th>
			<th> <spring:message code="Category"/> </th>
		</tr>
		<c:forEach var="training" items="${trainingsList}">
			<tr>
				<td valign="top">
					<a href="training.form?trainingId=${training.trainingId}">
						<c:choose>
							<c:when test="${training.retired == true}">
								<del>${training.name}</del>
							</c:when>
							<c:otherwise>
								${training.name}
							</c:otherwise>
						</c:choose>
					</a>
				</td>
				<td valign="top">${training.category}</td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>