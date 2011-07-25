<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>


<%@ include file="staffLocalHeader.jsp" %>
<br/>

<div class="boxHeader"><spring:message code="Staff"/></div>
<div class="box">
	<table>
		<thead>
			<tr>
				<th><spring:message code="Person.names"/></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td valign="top">
					<c:forEach var="name" items="${person.names}" varStatus="status">
						<c:if test="${!name.voided}">
						<% request.setAttribute("name", pageContext.getAttribute("name")); %>
							<spring:nestedPath path="name">
								<openmrs:portlet url="nameLayout" id="namePortlet" size="quickView" parameters="layoutShowExtended=true" />
							</spring:nestedPath>
						</c:if>
					</c:forEach>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<br/>

<div class="boxHeader"><spring:message code="Person.addresses"/></div>
<div class="box">
	<table class="personAddress">
		<thead>
			<openmrs:portlet url="addressLayout" id="addressPortlet" size="columnHeaders" parameters="layoutShowTable=false|layoutShowExtended=true" />
		</thead>
		<tbody>
			<c:forEach var="address" items="${person.addresses}" varStatus="status">
				<c:if test="${!address.voided}">
				<% request.setAttribute("address", pageContext.getAttribute("address")); %>
				<spring:nestedPath path="address">
					<openmrs:portlet url="addressLayout" id="addressPortlet" size="inOneRow" parameters="layoutMode=view|layoutShowTable=false|layoutShowExtended=true" />
				</spring:nestedPath>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
</div>

<br/>
<br/>

<div id="staffDemographicsEdit">
	<a href="${pageContext.request.contextPath}/module/hr/admin/staff.form?staffId=${person.personId}"><spring:message code="Edit this Staff"/></a> 
</div>


<%@ include file="/WEB-INF/template/footer.jsp"%>