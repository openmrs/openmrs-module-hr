<br/>
<br/>
<table width="100%">
<tr>
	<td><font size="3"><b>${staff.personName}</b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.gender} &nbsp;&nbsp;&nbsp;<openmrs:formatDate date="${staff.birthdate}" type="medium" />&nbsp;&nbsp;&nbsp;Staff Status: ${staff.staffStatus.name.name}&nbsp;&nbsp;Initial Employment:<openmrs:formatDate date="${staff.initialHireDate}" type="medium" />&nbsp;&nbsp;&nbsp;${AttributeToDisplay}</td>
</tr>
</table>
<br/>

<ul id="menu">
		<openmrs:hasPrivilege privilege="View Staff">
		<li <c:if test='<%= request.getRequestURI().contains("Demographics") %>'>class="active first"</c:if> <c:if test='<%= !request.getRequestURI().contains("Demographics") %>'>class="first"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/manager/staffDemographics.htm">
				<spring:message code="Personal"/>
			</a>
		</li>
		</openmrs:hasPrivilege>
		<openmrs:hasPrivilege privilege="Add Post">
		<li <c:if test='<%= request.getRequestURI().contains("Position") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/manager/staffPosition.list">
				<spring:message code="Position"/>
			</a>
		</li>
		</openmrs:hasPrivilege>
</ul>