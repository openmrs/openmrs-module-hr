<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage training" otherwise="/login.htm" redirect="/module/hr/admin/trainings.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Training" /></h2>
<c:if test="${training.retired}">
    <form method="post">
        <div class="retiredMessage">
            <div>
                <spring:message code="This training is retired by"/>
                ${training.retiredBy.personName}
                <openmrs:formatDate date="${training.dateRetired}" type="medium" />
                -
                ${training.retireReason}
                <input type="submit" value='<spring:message code="Unretire Training"/>' name="unretireTraining"/>
            </div>
        </div>
	</form>
</c:if>

<spring:hasBindErrors name="training">
	<spring:message code="fix.error"/>
	<c:forEach items="${errors.allErrors}" var="error">
        <c:if test="${error.code == 'statusChange'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
        <c:if test="${error.code == 'startsWithComma' or error.code == 'endsWithComma'}"><span class="error"><spring:message code="${error.defaultMessage}" text="Improper text for Levels"/></span><br/></c:if>
    </c:forEach>
	<br />
</spring:hasBindErrors>

<form method="post">
    <fieldset>
        <table>

            <tr>
                    <th valign="top"><spring:message code="Name"/></th>
                    <td>
                        <spring:bind path="training.name">
                            <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
            <tr>

            <tr>
                    <th valign="top"><spring:message code="Category"/></th>
                    <td>
                        <spring:bind path="training.category">
                            <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
            <tr>
            <tr>
                <th width="10%" align="left" valign="top"><spring:message code="Description"/></th>
                <td>
                        <spring:bind path="training.description">
                        <textarea name="${status.expression}" value="${status.value}" rows="5" cols="50">${training.description}</textarea>
                        </spring:bind>
                </td>
            </tr>

        </table>
        <br />
        <input type="submit" value="<spring:message code="Save Training"/>" name="save">

    </fieldset>
<br/>
<br/>

<c:if test="${not training.retired && training.trainingId!=0 }">
	<fieldset>
			<h4><spring:message code="Retire Training"/></h4>

			<b><spring:message code="general.reason"/></b>
			<input type="text" value="" size="40" name="retireReason" />
			<spring:hasBindErrors name="training">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'retireReason'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span></c:if>
				</c:forEach>
			</spring:hasBindErrors>
			<br/>
			<input type="submit" value='<spring:message code="Retire Training"/>' name="retireTraining"/>
		</fieldset>
</c:if>
</form>
<br/>
<%@ include file="/WEB-INF/template/footer.jsp"%>
