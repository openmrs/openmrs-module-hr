<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage TrainingClass" otherwise="/login.htm" redirect="/module/hr/admin/trainingClasses.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />


<spring:hasBindErrors name="trainingClass">
<c:set var="errorExist" value="true"/>
	<spring:message code="fix.error"/>
	<br />
</spring:hasBindErrors>

<c:if test="{trainingClass.trainClassId == 0}">
    <h2><spring:message code="Add New Training Class" /></h2>
</c:if>

<form method="post" name="trainingClassForm">
<fieldset>
<table width="100%" >
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Training"/></th>
		<td>
				<spring:bind path="trainingClass.hrTraining">
				<select name="hrTraining" id="${status.expression}">
					<c:forEach items="${allTrainingsList}" var="training">
                        <option value="${training.trainingId}" <c:if test='${training.trainingId == status.value}'>selected="selected"</c:if>>${training.name}</option>
					</c:forEach>
				</select>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     			</spring:bind>
		</td>
	</tr>
    <tr>
        <th width="10%" align="left" valign="top"><spring:message code="Start Date"/></th>
        <td>
            <spring:bind path="trainingClass.startDate">
                <input type="text" name="${status.expression}" size="10"
                                       value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
                                (<spring:message code="general.format"/>: <openmrs:datePattern />)
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                <spring:hasBindErrors name="trainingClass">
                    <c:forEach items="${errors.allErrors}" var="error">
                        <c:if test="${error.code == 'trainingClassDateInvalid'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
                    </c:forEach>
                </spring:hasBindErrors>
            </spring:bind>
        </td>
    </tr>

    <tr>
        <th valign="top"><spring:message code="Duration"/></th>
        <td>
            <spring:bind path="trainingClass.duration">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
        </td>
    </tr>

    <tr>
        <th valign="top"><spring:message code="CE Units"/></th>
        <td>
            <spring:bind path="trainingClass.ceunits">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
        </td>
    </tr>

    <tr>
        <th valign="top"><spring:message code="Location"/></th>
        <td>
            <spring:bind path="trainingClass.location">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
        </td>
    </tr>
     <tr>
        <th valign="top"><spring:message code="Instructor"/></th>
        <td>
            <spring:bind path="trainingClass.instructor">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
        </td>
    </tr>

    <tr>
        <th valign="top"><spring:message code="Organization"/></th>
        <td>
            <spring:bind path="trainingClass.organization">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
        </td>
    </tr>
    <tr>
        <th valign="top"><spring:message code="Funding Source"/></th>
        <td>
            <spring:bind path="trainingClass.fundingSource">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
        </td>
    </tr>
    <tr>
        <th valign="top"><spring:message code="Course Cost"/></th>
        <td>
            <spring:bind path="trainingClass.costCourse">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
        </td>
    </tr>

    <tr>
        <th valign="top"><spring:message code="Course Registration Cost"/></th>
        <td>
            <spring:bind path="trainingClass.costRegister">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
        </td>
    </tr>
    <tr>
        <th valign="top"><spring:message code="Travel Cost"/></th>
        <td>
            <spring:bind path="trainingClass.costTravel">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
        </td>
    </tr>
    <tr>
        <th valign="top"><spring:message code="PerDiem Cost"/></th>
        <td>
            <spring:bind path="trainingClass.costPerdiem">
                <input type="text" name="${status.expression}" size="40" value="${status.value}" />
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
        </td>
    </tr>


    </table>
<br />
<br />
</fieldset>
<input type="submit" value="<spring:message code="Save Training Class"/>" name="action"/>
<c:if test="${trainingClass.trainingClassId != 0}">
    <input type="submit" value="<spring:message code="Delete Training Class"/>" name="action" onclick="return confirm('<spring:message code="Are you sure you want to Delete this Training Class?"/>')"/>
</c:if>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>