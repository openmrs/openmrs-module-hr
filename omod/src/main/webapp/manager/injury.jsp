<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Notes" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />


<spring:hasBindErrors name="injury">
<c:set var="errorExist" value="true"/>
	<spring:message code="fix.error"/>
	<br />
</spring:hasBindErrors>

<c:if test="{injury.noteId == 0}">
    <h2><spring:message code="Add New Injury" /></h2>
</c:if>

<form method="post" enctype="multipart/form-data" name="injuryForm">
<fieldset>
<table width="100%">
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Text"/></th>
		<td>
				<spring:bind path="injury.text">
                <textarea name="${status.expression}" value="${status.value}" rows="5" cols="50">${injury.text}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Priority"/></th>
		<td>
     		<spring:bind path="injury.priority">
                <input type="text" value="${status.value}" size="40" name="${status.expression}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
		</td>
	</tr>

</table>
<br />
<br />
</fieldset>

<input type="submit" value="<spring:message code="Save Injury Note"/>" name="action"/>
<c:if test="${injury.noteId!=0}">
    <input type="submit" value="<spring:message code="Delete Injury Note"/>" name="action" onclick="return confirm('<spring:message code="Are you sure you want to Delete this note?"/>')"/>
</c:if>

</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>