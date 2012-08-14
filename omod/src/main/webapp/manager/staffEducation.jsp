<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Education" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>

<spring:hasBindErrors name="education">
<c:set var="errorExist" value="true"/>
	<spring:message code="fix.error"/>
	<c:forEach items="${errors.allErrors}" var="error">
	    <c:if test="${error.code == 'wrongYear'}"> <span class="error"><spring:message code="${error.defaultMessage}" text="Invalid Year"/></span><br/></c:if>
    </c:forEach>
	<br />
</spring:hasBindErrors>

<h2><spring:message code="hr.education" /></h2>

<form method="post">

    <fieldset>

        <table width="100%">
            <tr>
                <th width="10%" align="left" valign="top"><spring:message code="hr.education.degree"/></th>
                <td>
                        <spring:bind path="education.degree">
                          <input type="text" value="${status.value}" size="40" name="${status.expression}" />
                          <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                </td>
            </tr>

            <tr>
                <th width="10%" align="left" valign="top"><spring:message code="hr.education.institution"/></th>
                <td>
                        <spring:bind path="education.institution">
                          <input type="text" value="${status.value}" size="40" name="${status.expression}" />
                          <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                </td>
            </tr>

            <tr>
                <th width="10%" align="left" valign="top"><spring:message code="hr.education.institution.location"/></th>
                <td>
                        <spring:bind path="education.institutionLocation">
                          <input type="text" value="${status.value}" size="40" name="${status.expression}" />
                          <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                </td>
            </tr>

            <tr>
                <th width="10%" align="left" valign="top"><spring:message code="hr.education.major"/></th>
                <td>
                        <spring:bind path="education.major">
                          <input type="text" value="${status.value}" size="40" name="${status.expression}" />
                          <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                </td>
            </tr>

            <tr>
                <th width="10%" align="left" valign="top"><spring:message code="hr.education.completion.year"/></th>
                    <td>
                        <spring:bind path="education.degreeYear">
                          <input type="text" value="${status.value}" size="40" name="${status.expression}" />
                          <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
            </tr>

        </table>
        <br />
        <br />

        </fieldset>

    <openmrs:hasPrivilege privilege="Add Staff Education">
        <input type="submit" value="<spring:message code="hr.action.education.save"/>" name="action"/>
        <c:if test="${education.educationId != 0}">
                <input type="submit" value="<spring:message code="hr.action.education.delete"/>" name="action" onclick="return confirm('<spring:message code="Are you sure you want to Delete Education details?"/>')"/>
        </c:if>
    </openmrs:hasPrivilege>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>