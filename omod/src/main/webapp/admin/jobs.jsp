<%@page import="org.openmrs.api.context.Context"%>
<%@page import="org.openmrs.api.ConceptService"%>
<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage jobs" /></h2>
<a href="job.form"><spring:message code="Add New Job"/></a>
<br/>
<br/>
<b class="boxHeader">
	<a style="display: block; float: right" href="#">
		<spring:message code="general.toggle.retired" />
	</a>
	<spring:message code="Current Jobs"/>
</b>
<form method="post" class="box">
<table id="JobsTable" width="100%">
		<tr>
			<th> <spring:message code="Job Title" /> </th>
			<th> <spring:message code="ISCO Code Title" /> </th>
			<th> <spring:message code="Description" /> </th>
			<th> <spring:message code="Cadre" /> </th>
			<th> <spring:message code="Grades" /> </th>
		</tr>
		<c:forEach var="job" items="${JobList}" varStatus="rowStatus">
			<tr <c:if test="${job.retired}">class="retired ${rowStatus.index % 2 == 0 ? 'evenRow' : 'oddRow' }"</c:if>  <c:if test="${!job.retired}">class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" }'</c:if> >
				<td valign="top" width="10%">
					<a href="job.form?jobId=${job.jobId}">${job.title}</a>
				</td>
				<td valign="top" width="20%">${job.hrIscoCodes.title}</td>
				<td valign="top" width="50%">${job.description}	</td>
				<td valign="top" width="10%">${job.cadre.name}</td>
				<td valign="top" width="10%">${job.grades}</td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>
