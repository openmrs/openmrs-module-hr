<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Staff Attribute Type" /></h2>
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
		<td><spring:message code="general.name"/></td>
		<td>
			<spring:bind path="staffAttributeType.name">
				<input type="text" name="name" value="${status.value}" size="50" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td valign="top"><spring:message code="general.description"/></td>
		<td valign="top">
			<spring:bind path="staffAttributeType.description">
				<textarea name="description" rows="3" cols="40" >${status.value}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="FormField.minOccurs"/></td>
		<td>
			<spring:bind path="staffAttributeType.minOccurs">
				<input type="text" name="minOccurs" value="${status.value}" size="10" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="FormField.maxOccurs"/></td>
		<td>
			<spring:bind path="staffAttributeType.maxOccurs">
				<input type="text" name="maxOccurs" value="${status.value}" size="10" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="Handler Type"/></td>
		<td>
			<spring:bind path="Handler Type">
				<select name="handlerType">
					<c:forEach items="${handlertypes}" var="handlertype">
						<option value="${handlertype}" <c:if test="${handlertype == status.value}">selected</c:if>>${handlertype}</option>
					</c:forEach>
				</select>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="AttributeType.handlerConfig"/></td>
		<td>
			<spring:bind path="staffAttributeType.handlerConfiguration">
				<textarea name="handlerConfiguration" rows="3" cols="40" >${status.value}</textarea>
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
</form>

<br/>

<c:if test="${not staffAttributeType.retired && not empty staffAttributeType.staffAttributeTypeId}">
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

<c:if test="${staffAttributeType.retired == true && not empty staffAttributeType.staffAttributeTypeId}">
	<fieldset>
		<h4><spring:message	code="Unretire Staff Attribute Type" /></h4>
		<input type="submit" value='<spring:message code="Unretire Staff Attribute Type"/>'	name="unretire" />
		</fieldset>
</c:if>
<br />

<c:if test="${not empty staffAttributeType.staffAttributeTypeId}">
	<form id="purge" method="post" onsubmit="return confirmPurge()">
			<fieldset>
				<h4><spring:message code="Purge Staff Attribute Type"/></h4>
				<input type="submit" value='<spring:message code="Purge Staff Attribute Type"/>' name="purge" />
			</fieldset>
		</form>
</c:if>
<%@ include file="/WEB-INF/template/footer.jsp"%>