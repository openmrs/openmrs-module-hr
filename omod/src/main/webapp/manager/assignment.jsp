<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<spring:hasBindErrors name="assignment">
	<spring:message code="fix.error"/>
	<br />
</spring:hasBindErrors>
<form method="post">
<fieldset>
<table>
	<tr>
		<th align="left" valign="top"><spring:message code="Location"/></th>
		<td>
			<spring:bind path="assignment.location">	
				<%-- <select name="job" id="${status.expression}">
					<c:forEach items="${JobList}" var="job" varStatus="status">
						<option value="${job.id}" <c:if test="${ post.hrJobTitle.title == job.title}">selected</c:if>>${job.title}</option>
					</c:forEach>
     		</select>  --%>
     		<label>${assignment.location.name}</label>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Post"/></th>
		<td>
			<spring:bind path="assignment.hrPostHistory.hrPost.hrJobTitle.title">	
				<%-- <select name="location" id="${status.expression}">
					<c:forEach items="${LocationList}" var="location" varStatus="status">
						<option value="${location.id}" <c:if test="${ post.location.name== location.name}">selected</c:if>>${location.name}</option>
					</c:forEach>
     		</select>  --%>
     		<label>${assignment.hrPostHistory.hrPost.hrJobTitle.title}</label>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Assignment"/></th>
		<td>
			<spring:bind path="assignment.assignment">	
				<label>${status.value}</label>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Time Basis"/></th>
		<td>
			<spring:bind path="assignment.timeBasis">	
				<label>${status.value}</label> 
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Note"/></th>
		<td>
			<spring:bind path="assignment.note">	
				<label>${status.value}</label> 
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Work Schedule"/></th>
		<td>
			<spring:bind path="assignment.workSchedule">	
				<label>${assignment.workSchedule.name.name}</label> 
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Start Date"/></th>
		<td>
			<spring:bind path="assignment.startDate">	
				<label><openmrs:formatDate date="${assignment.startDate}" type="medium" /></label> 
			</spring:bind>
		</td>
	</tr>
	
	<tr>
		<th align="left" valign="top"><spring:message code="End Date"/></th>
		<td>
			<spring:bind path="assignment.endDate">
			<input type="text" name="${status.expression}" size="10" 
					   value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
				(<spring:message code="general.format"/>: <openmrs:datePattern />)
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			<%-- <openmrs:formatDate date="${assignment.endDate}" type="medium" /> --%>	
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="End Reason"/></th>
		<td>
			<spring:bind path="assignment.endReason">	
			<label>${assignment.endReason.name.name}</label>
			</spring:bind>
			
		</td>
		<c:if test='${fn:endsWith(assignment.endReason.name.name,":")}'>
		<th align="left" valign="top"><spring:message code="End Reason Text"/></th>
		<td>
		<spring:bind path="assignment.endReasonOther">	
				<input type="text" name="${status.expression}" size="40" value="${assignment.endReasonOther}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if> 
			</spring:bind>
		</td>
		</c:if>	
	</tr>
</table>
<br />

<input type="submit" value="<spring:message code="Save Assignment"/>" name="save">

</fieldset>
<br/>
<br/>
<%@ include file="/WEB-INF/template/footer.jsp"%>