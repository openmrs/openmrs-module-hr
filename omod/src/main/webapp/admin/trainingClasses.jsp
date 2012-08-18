<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Training Classes" otherwise="/login.htm" redirect="/module/hr/admin/trainingClasses.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>

<openmrs:requireConfiguration propertyList="hr.setup" configurationPage="/module/hr/admin/setup.form?targetView=module/hr/admin/trainingClasses" />


<h2><spring:message code="hr.training.classes.manage" /></h2>
<a href="trainingClass.form"><spring:message code="hr.training.classes.add"/></a>
<br /><br />

<b class="boxHeader">
<spring:message code="hr.training.classes.current"/></b>
<form method="post" class="box">
	<table id="TrainingClassesTable" width="100%">
		<tr>
			<th> <spring:message code="hr.training.classes.training"/> </th>
			<th> <spring:message code="hr.trainings.category"/> </th>
			<th> <spring:message code="hr.training.classes.organization"/> </th>
		</tr>
		<c:forEach var="training" items="${trainingClassesList}">
			<tr>
				<td valign="top">
					<a href="trainingClass.form?trainingClassId=${trainingClass.trainingClassId}">
                            ${trainingClass.hrTraining.name}
					</a>
				</td>
				<td valign="top">${trainingClass.hrTraining.category}</td>
				<td valign="top">${trainingClass.organization}</td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>