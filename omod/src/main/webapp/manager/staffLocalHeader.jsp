<div id="patientHeader" class="boxHeader">
	<div id="patientHeaderPatientName">${staff.personName}</div>
	
	<div id="patientHeaderPreferredIdentifier">
				<span class="patientHeaderPatientIdentifier">${AttributeToDisplay}</span>
	</div>
	
	<table id="patientHeaderGeneralInfo">
			<tr class="patientHeaderGeneralInfoRow">
				<td>
					<c:if test="${staff.gender == 'M'}"><img src="${pageContext.request.contextPath}/images/male.gif" alt='<spring:message code="Person.gender.male"/>' id="maleGenderIcon"/></c:if>
					<c:if test="${staff.gender == 'F'}"><img src="${pageContext.request.contextPath}/images/female.gif" alt='<spring:message code="Person.gender.female"/>' id="femaleGenderIcon"/></c:if>
					<c:if test="${not empty staff.birthdate}">(<openmrs:formatDate date="${staff.birthdate}" type="medium" />)</c:if><c:if test="${empty staff.birthdate}"><spring:message code="Person.age.unknown"/></c:if>
				</td>
			</tr>
	</table>
</div>

<div id="patientSubheader" class="box">
	<table id="staffSubHeader">
		<tr class="patientObsRow">
			<th id="staffStatus">
				Staff Status: 
			</th>
			<td>
				${staff.staffStatus.name.name}
			</td>
			<th id="initialEmployment">
				Initial Employment: 
			</th>
			<td>
				<openmrs:formatDate date="${staff.initialHireDate}" type="medium" />
			</td>
		</tr>
	</table>
</div>
 
<div id="patientTabs">
<ul >
		<openmrs:hasPrivilege privilege="View Staff">
		<li>
			<a href="${pageContext.request.contextPath}/module/hr/manager/staffDemographics.htm" <c:if test='<%= request.getRequestURI().contains("Demographics") %>'>class="current"</c:if>>
 				<spring:message code="Personal"/>
 			</a>
		</li>
		</openmrs:hasPrivilege>
		<openmrs:hasPrivilege privilege="View Posts">
		<li>
			<a href="${pageContext.request.contextPath}/module/hr/manager/staffPosition.list" <c:if test='<%= request.getRequestURI().contains("staffPosition") %>'>class="current"</c:if>>
				<spring:message code="Post History"/>
			</a>
		</li>
		</openmrs:hasPrivilege>
		<openmrs:hasPrivilege privilege="View Certificates">
            <li>
                <a href="${pageContext.request.contextPath}/module/hr/manager/staffCertificates.list" <c:if test='<%= request.getRequestURI().contains("staffCertificate") %>'>class="current"</c:if>>
                    <spring:message code="Certificates"/>
                </a>
            </li>
        </openmrs:hasPrivilege>

        <openmrs:hasPrivilege privilege="View Education">
            <li>
                <a href="${pageContext.request.contextPath}/module/hr/manager/staffEducations.list" <c:if test='<%= request.getRequestURI().contains("staffEducation") %>'>class="current"</c:if>>
                    <spring:message code="Education"/>
                </a>
            </li>
        </openmrs:hasPrivilege>

</ul>
</div>