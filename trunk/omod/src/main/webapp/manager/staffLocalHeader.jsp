<br/>
<br/>
<table width="100%">
<tr>
	<td width="40%"><b></b>${person.personName}</td>
	<td width="60%" align="right">
		${person.gender} &nbsp;&nbsp;&nbsp;<openmrs:formatDate date="${person.birthdate}" type="medium" />&nbsp;&nbsp;&nbsp;Staff Status: ${staff.staffStatus.name.name}&nbsp;&nbsp;Initial Employment:<openmrs:formatDate date="${staff.initialHireDate}" type="medium" />
	</td>
</tr>
</table>
<br/>

<ul id="menu">
		<li <c:if test='<%= request.getRequestURI().contains("Demographics") %>'>class="active first"</c:if> <c:if test='<%= !request.getRequestURI().contains("Demographics") %>'>class="first"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/manager/staffDemographics.htm">
				<spring:message code="Personal"/>
			</a>
		</li>
		
		<li <c:if test='<%= request.getRequestURI().contains("Position") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/manager/staffPosition.list">
				<spring:message code="Position"/>
			</a>
		</li>
</ul>