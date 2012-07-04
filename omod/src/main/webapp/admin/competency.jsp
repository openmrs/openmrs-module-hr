<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Competencies" otherwise="/login.htm" redirect="/module/hr/admin/competencies.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Competency" /></h2>
<c:if test="${competency.retired}">
    <form method="post">
        <div class="retiredMessage">
            <div>
                <spring:message code="This competency is retired by"/>
                ${competency.retiredBy.personName}
                <openmrs:formatDate date="${competency.dateRetired}" type="medium" />
                -
                ${competency.retireReason}
                <input type="submit" value='<spring:message code="Unretire Competency"/>' name="unretireCompetency"/>
            </div>
        </div>
	</form>
</c:if>

<spring:hasBindErrors name="competency">
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
                        <spring:bind path="competency.name">
                            <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
            <tr>
            <tr>
                    <th valign="top"><spring:message code="Category"/></th>
                    <td>
                        <spring:bind path="competency.category">
                            <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
            <tr>
        <tr>
            <th valign="top"><spring:message code="Levels"/></th>
            <td>
                <spring:bind path="competency.levels">
                    <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                    Give comma separated list of applicable levels for this competency.
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
            </td>
        </tr>
        <tr>
            <th valign="top"><spring:message code="Edit Privilege"/></th>
            <td>
                <spring:bind path="competency.editPrivilege">
                    <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
            </td>
        </tr>


        </table>
        <br />
        <input type="submit" value="<spring:message code="Save competency"/>" name="save">

    </fieldset>
<br/>
<br/>

<c:if test="${not competency.retired && competency.competencyId!=0 }">
	<fieldset>
			<h4><spring:message code="Retire competency"/></h4>

			<b><spring:message code="general.reason"/></b>
			<input type="text" value="" size="40" name="retireReason" />
			<spring:hasBindErrors name="competency">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'retireReason'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span></c:if>
				</c:forEach>
			</spring:hasBindErrors>
			<br/>
			<input type="submit" value='<spring:message code="Retire competency"/>' name="retirecompetency"/>
		</fieldset>
</c:if>
</form>
<br/>
<%@ include file="/WEB-INF/template/footer.jsp"%>
