<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Staff Attribute Types" otherwise="/login.htm" redirect="/module/hr/admin/staffAttributeTypes.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<script type="text/javascript">

	function confirmPurge() {
		if (confirm("Are you sure you want to purge this staff attribute type? It will be permanently removed from the system.")) {
			return true;
		} else {
			return false;
		}
	}
	
</script>
<h2><spring:message code="Staff Attribute Type"/></h2>

<spring:hasBindErrors name="staffAttributeType">
	<spring:message code="fix.error"/>
	<br />
</spring:hasBindErrors>
<form method="post">
<fieldset>
<table>
	<tr>
		<td><spring:message code="Attribute To Display"/></td>
		<td>
			<input type="checkbox" name="toBeDisplayed" <c:if test='${checked}'>checked='true'</c:if>/>
		</td>
	</tr>
	<tr>
		<td><spring:message code="general.name"/></td>
		<td>
			<spring:bind path="staffAttributeType.name">
				<input type="text" name="name" value="${status.value}" size="35" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="general.format"/></td>
		<td>
			<spring:bind path="staffAttributeType.format">
				<select name="format">
					<option value=""></option>
					<c:forEach items="${formats}" var="format">
						<option value="${format}" <c:if test="${format == status.value}">selected</c:if>>${format}</option>
					</c:forEach>
				</select>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="Foreign Key"/></td>
		<td>
			<spring:bind path="staffAttributeType.foreignKey">
				<input type="text" name="foreignKey" value="${status.value}" size="35" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="Searchable"/></td>
		<td>
			<spring:bind path="staffAttributeType.searchable">
				<input type="hidden" name="_${status.expression}">
				<input type="checkbox" name="${status.expression}" id="${status.expression}" <c:if test="${status.value == true}">checked</c:if>/>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td valign="top"><spring:message code="general.description"/></td>
		<td valign="top">
			<spring:bind path="staffAttributeType.description">
				<textarea name="description" rows="3" cols="40">${status.value}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="Edit Privilege"/></td>
		<td>
			<spring:bind path="staffAttributeType.editPrivilege">
				<select name="editPrivilege">
					<option value=""></option>
					<c:forEach items="${privileges}" var="privilege">
						<option value="${privilege.privilege}" <c:if test="${privilege.privilege == status.value}">selected</c:if>>${privilege.privilege}</option>
					</c:forEach>
				</select>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>	
	<c:if test="${!(staffAttributeType.creator == null)}">
		<tr>
			<td><spring:message code="general.createdBy" /></td>
			<td><openmrs:format user="${ staffAttributeType.creator }"/></td>
		</tr>
	</c:if>
</table>
<br />

<input type="submit" value="<spring:message code="Save Staff Attribute Type"/>" name="save">

</fieldset>


<br/>

<c:if test="${not staffAttributeType.retired && staffAttributeType.staffAttributeTypeId!=0}">
	<fieldset>
			<h4><spring:message code="Retire Staff Attribute Type"/></h4>
			
			<b><spring:message code="general.reason"/></b>
			<input type="text" value="" size="40" name="retireReason" />
			<spring:hasBindErrors name="staffAttributeType">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'retireReason'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span></c:if>
				</c:forEach>
			</spring:hasBindErrors>
			<br/>
			<input type="submit" value='<spring:message code="Retire Staff Attribute Type"/>' name="retire"/>
		</fieldset>
</c:if>

<br/>

<c:if test="${staffAttributeType.retired == true && staffAttributeType.staffAttributeTypeId!=0}">
	<fieldset>
		<h4><spring:message	code="Unretire Staff Attribute Type" /></h4>
		<input type="submit" value='<spring:message code="Unretire Staff Attribute Type"/>'	name="unretire" />
		</fieldset>
</c:if>
<br />
</form>
<c:if test="${staffAttributeType.staffAttributeTypeId!=0}">
	<form id="purge" method="post" onsubmit="return confirmPurge()">
			<fieldset>
				<h4><spring:message code="Purge Staff Attribute Type"/></h4>
				<input type="submit" value='<spring:message code="Purge Staff Attribute Type"/>' name="purge" />
			</fieldset>
		</form>
</c:if>
<%@ include file="/WEB-INF/template/footer.jsp"%>