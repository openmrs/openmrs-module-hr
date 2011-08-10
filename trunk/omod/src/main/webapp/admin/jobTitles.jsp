<%@ include file="/WEB-INF/template/include.jsp"%>


<openmrs:require privilege="Manage Job Titles" otherwise="/login.htm" redirect="/module/hr/admin/jobTitles.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/dojoConfig.js" />
<openmrs:htmlInclude file="/scripts/dojo/dojo.js" />

<script type="text/javascript">
	dojo.addOnLoad( function() {
		toggleRowVisibilityForClass("JobsTable", "retired", false);
	})
</script>

<h2><spring:message code="Manage job titles" /></h2>
<a href="jobTitle.form"><spring:message code="Add New Job Title"/></a>
<br/>

<br/>
<b class="boxHeader">
	<a style="display: block; float: right"
		href="#"
		onClick="return toggleRowVisibilityForClass('JobsTable', 'retired', false);">
		<spring:message code="general.toggle.retired" />
	</a>
	<spring:message code="Current Job Titles"/>
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
		<c:forEach var="jobTitle" items="${JobList}" varStatus="rowStatus">
			<tr <c:if test="${jobTitle.retired}">class="retired ${rowStatus.index % 2 == 0 ? 'evenRow' : 'oddRow' }"</c:if>  <c:if test="${!jobTitle.retired}">class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" }'</c:if> >
				<td valign="top" width="10%">
					<a href="jobTitle.form?jobId=${jobTitle.jobId}">${jobTitle.title}</a>
				</td>
				<td valign="top" width="20%">${jobTitle.hrIscoCodes.title}</td>
				<td valign="top" width="50%">${jobTitle.description}	</td>
				<td valign="top" width="10%">${jobTitle.cadre.name}</td>
				<td valign="top" width="10%">${jobTitle.grades}</td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>
