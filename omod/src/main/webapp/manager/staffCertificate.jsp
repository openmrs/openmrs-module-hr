<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Add Staff Certificate" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />

<spring:hasBindErrors name="staffCertificate">
<c:set var="errorExist" value="true"/>
	<spring:message code="fix.error"/>
	<c:forEach items="${errors.allErrors}" var="error">
	<c:if test="${error.code == 'endBeforeStart'}"><span class="error"><spring:message code="${error.defaultMessage}" text="Expiry date before certification date."/></span><br/></c:if>
</c:forEach>
	<br />
</spring:hasBindErrors>

<h2><spring:message code="Add New Staff Certificate" /></h2>

<form method="post">
<fieldset>
<table width="100%">
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Certificate"/></th>
		<td>
				<spring:bind path="staffCertificate.hrCertificate">
				<select name="hrCertificate" id="${status.expression}">
					<c:forEach items="${allCertificatesList}" var="certificate">
						<option value="${certificate.certificateId}" <c:if test='${certificate.certificateId == status.value}'>selected="selected"</c:if>>${certificate.certificate}</option>
					</c:forEach>
				</select>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Level"/></th>
		<td>
     		<spring:bind path="staffCertificate.level">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
		</td>
	</tr>
    <tr>
        <th width="10%" align="left" valign="top"><spring:message code="Certification Date"/></th>
        <td>
            <spring:bind path="staffCertificate.currentCertDate">
                <input type="text" name="${status.expression}" size="10"
                                       value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
                                (<spring:message code="general.format"/>: <openmrs:datePattern />)
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                <spring:hasBindErrors name="staffCertificate">
                    <c:forEach items="${errors.allErrors}" var="error">
                        <c:if test="${error.code == 'currentCertDateInvalid'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
                    </c:forEach>
                </spring:hasBindErrors>
            </spring:bind>
        </td>
    </tr>

    <tr>
        <th width="10%" align="left" valign="top"><spring:message code="Expiration Date"/></th>
        <td>
            <spring:bind path="staffCertificate.certExpirationDate">
                <input type="text" name="${status.expression}" size="10"
                                       value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
                                (<spring:message code="general.format"/>: <openmrs:datePattern />)
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                <spring:hasBindErrors name="staffCertificate">
                    <c:forEach items="${errors.allErrors}" var="error">
                        <c:if test="${error.code == 'certExpirationDateInvalid'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
                    </c:forEach>
                </spring:hasBindErrors>
            </spring:bind>
        </td>
    </tr>

</table>
<br />

<input type="submit" value="<spring:message code="Save Staff Certificate"/>" name="submit"/>

</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>