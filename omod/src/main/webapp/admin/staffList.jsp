<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage Staff" /></h2>

<a href="staff.form"><spring:message code="Add New staff"/></a>
<br/>
<br/>
<b class="boxHeader">
	<spring:message code="Current Staff"/>
</b>
<form method="post" class="box">
<table id="StaffTable" width="100%">
		<tr>
			<th> <spring:message code="Name" /> </th>
			<th> <spring:message code="Location" /> </th>
			<th> <spring:message code="Job Title" /> </th>
			<th> <spring:message code="Status" /> </th>
			<th> <spring:message code="Sex" /> </th>
			<th> <spring:message code="DOB" /> </th>
		</tr>
		<c:forEach var="staffListItem" items="${StaffListItemList}" varStatus="rowStatus">
			<tr class="${rowStatus.index % 2 == 0 ? 'evenRow' : 'oddRow' }">
				<td valign="top">
				<c:forEach var="name" items="${staffListItem.person.names}" varStatus="varStatus">
					<c:if test="${name.preferred}"><span>${name.givenName}</span>&nbsp;<span>${name.familyName}</span></c:if>
				</c:forEach>
				</td>
				<td valign="top">${staffListItem.locationName}</td>
				<td valign="top">${staffListItem.jobTitle}	</td>
				<td valign="top">${staffListItem.staff.staffStatus.name.name}</td>
				<td valign="top">${staffListItem.person.gender}</td>
				<td valign="top"><openmrs:formatDate date="${staffListItem.person.birthdate}" type="medium" /></td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>