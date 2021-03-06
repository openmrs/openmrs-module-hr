<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Trainings" otherwise="/login.htm" redirect="/module/hr/admin/trainings.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>

<openmrs:requireConfiguration propertyList="hr.setup" configurationPage="/module/hr/admin/setup.form?targetView=module/hr/admin/trainings" />


<h2><spring:message code="hr.trainings.manage" /></h2>
<a href="training.form"><spring:message code="hr.trainings.add"/></a>
<br /><br />

<b class="boxHeader">
<spring:message code="hr.trainings.current"/></b>
<form method="post" class="box">
	<table id="TrainingsTable" width="100%">
		<tr>
			<th> <spring:message code="hr.trainings.name"/> </th>
			<th> <spring:message code="hr.trainings.category"/> </th>
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