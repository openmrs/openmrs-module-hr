<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="View Staff Demographics" otherwise="/login.htm" redirect="/module/hr/manager/staffDemographics.htm"/>
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
					<c:forEach var="name" items="${staff.names}" varStatus="status">
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
			<c:forEach var="address" items="${staff.addresses}" varStatus="status">
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
<c:if test='${not empty person.attributes}'>
<br/>
<div class="boxHeader"><spring:message code="Attributes"/></div>
<div class="box">
<table>
<openmrs:forEachDisplayAttributeType personType="" displayType="all" var="attrType">
	<c:choose>
	<c:when test="${attrType.retired == true or empty person.attributeMap[attrType.name]}"></c:when>
	<c:otherwise>
	<tr>
		<td><spring:message code="PersonAttributeType.${fn:replace(attrType.name, ' ', '')}" text="${attrType.name}"/></td>
		<td>${person.attributeMap[attrType.name]}</td>
	</tr>
	</c:otherwise>
	</c:choose>
</openmrs:forEachDisplayAttributeType>
</table>
</div>
</c:if>
<c:if test='${not empty staff.attributes}'>
<br/>
<div class="boxHeader"><spring:message code="Staff Attributes"/></div>
<div class="box">
<table>
	<c:forEach items="${attrTypes}" var="attrType">
		<c:choose>
			<c:when test="${attrType.retired == true or empty attributeMap[attrType.name]}"></c:when>
			<c:otherwise>
				<tr>
					<td><spring:message code="${attrType.name}" />
					</td>
					<td>${attributeMap[attrType.name]}</td>
				</tr>
		</c:otherwise>
	</c:choose>
	</c:forEach>
</table>
	</div>
</c:if>
<br/>
<br/>

<div id="staffDemographicsEdit">
	<a href="${pageContext.request.contextPath}/module/hr/admin/staff.form?personId=${staff.staffId}&managerEdit=true"><spring:message code="Edit this Staff"/></a> 
</div>


<%@ include file="/WEB-INF/template/footer.jsp"%>