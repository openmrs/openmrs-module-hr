<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Staff Attribute Types" otherwise="/login.htm" redirect="/module/hr/admin/staffAttributeTypes.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>

<openmrs:requireConfiguration propertyList="hr.setup" configurationPage="/module/hr/admin/setup.form?targetView=module/hr/admin/staffAttributeTypes" />

<h2><spring:message code="Manage Staff Attribute Types" /></h2>
<a href="staffAttributeType.form"><spring:message code="Add New Staff Attribute Type"/></a>
<br /><br />

<b class="boxHeader">
<spring:message code="Current Staff Atrribute Types"/></b>
<form method="post" class="box">
	<table id="StaffAttributeTypesTable">
		<tr>
			<th> <spring:message code="general.name"/> </th>
			<th> <spring:message code="general.description"/> </th>
			<th> <spring:message code="Attribute to be Displayed"/> </th>
		</tr>
		<c:forEach var="staffAttributeType" items="${StaffAttributeTypeList}">
			<tr>
				<td valign="top">
					<a href="staffAttributeType.form?staffAttributeTypeId=${staffAttributeType.staffAttributeTypeId}">
						<c:choose>
							<c:when test="${staffAttributeType.retired == true}">
								<del>${staffAttributeType.name}</del>
							</c:when>
							<c:otherwise>
								${staffAttributeType.name}
							</c:otherwise>
						</c:choose>
					</a>
				</td>
				<td valign="top">${staffAttributeType.description}</td>
				<td valign="top" align="center"><c:if test='${toBeDisplayed==staffAttributeType.name}'><img src="${pageContext.request.contextPath}/moduleResources/hr/images/tick.gif"></c:if></td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>