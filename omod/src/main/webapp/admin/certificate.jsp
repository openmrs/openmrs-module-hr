<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Add Certificate" otherwise="/login.htm" redirect="/module/hr/admin/certificates.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Certificate" /></h2>
<c:if test="${certificate.retired}">
    <form method="post">
        <div class="retiredMessage">
            <div>
                <spring:message code="This certificate is retired by"/>
                ${certificate.retiredBy.personName}
                <openmrs:formatDate date="${certificate.dateRetired}" type="medium" />
                -
                ${certificate.retireReason}
                <input type="submit" value='<spring:message code="Unretire Certificate"/>' name="unretireCertificate"/>
            </div>
        </div>
	</form>
</c:if>

<spring:hasBindErrors name="certificate">
	<spring:message code="fix.error"/>
	<c:forEach items="${errors.allErrors}" var="error">
	<c:if test="${error.code == 'statusChange'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
</c:forEach>
	<br />
</spring:hasBindErrors>

<form method="post">
    <fieldset>
        <table>

            <tr>
                    <th valign="top"><spring:message code="Agency"/></th>
                    <td>
                        <spring:bind path="certificate.agency">
                            <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
            <tr>
            <tr>
                    <th valign="top"><spring:message code="Certificate"/></th>
                    <td>
                        <spring:bind path="certificate.certificate">
                            <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
            <tr>
        <tr>
                <th valign="top"><spring:message code="Levels"/></th>
                <td>
                    <spring:bind path="certificate.levels">
                        <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                        <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                    </spring:bind>
                </td>
            </tr>

        </table>
        <br />
        <input type="submit" value="<spring:message code="Save Certificate"/>" name="save">

    </fieldset>
<br/>
<br/>

<c:if test="${not certificate.retired && certificate.certificateId!=0 }">
	<fieldset>
			<h4><spring:message code="Retire Certificate"/></h4>

			<b><spring:message code="general.reason"/></b>
			<input type="text" value="" size="40" name="retireReason" />
			<spring:hasBindErrors name="certificate">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'retireReason'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span></c:if>
				</c:forEach>
			</spring:hasBindErrors>
			<br/>
			<input type="submit" value='<spring:message code="Retire Certificate"/>' name="retireCertificate"/>
		</fieldset>
</c:if>
</form>
<br/>
<%@ include file="/WEB-INF/template/footer.jsp"%>
