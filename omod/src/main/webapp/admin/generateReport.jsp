<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>


<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />

<h2><spring:message code="Generate Report" /></h2>

<spring:hasBindErrors name="HrReport">
	<spring:message code="fix.error" />
	<div class="error"><c:forEach items="${errors.allErrors}"
		var="error">
		<spring:message code="${error.code}" text="${error.code}" />
		<br />
		<!-- ${error} -->
	</c:forEach></div>
</spring:hasBindErrors>

<br />

<h3><c:out value="${HrReport.name}" /></h3>
<p><c:out value="${HrReport.description}"/></p>


<form method="post">
<fieldset>
<c:forEach var="p" items="${HrReport.parameters}">
<c:if test='${p!=null}'><c:set var="nonNullValueExists" value="true"></c:set></c:if>
</c:forEach>
<c:if test='${not empty HrReport.parameters and nonNullValueExists}'>
Enter the following parameters:
<table>
	<c:forEach var="parameter" items="${HrReport.parameters}" varStatus="varStatus">
	<c:if test='${parameter!=null}'>
		<c:if test="${parameter.visible}">
			<tr>
				<td><c:out value="${parameter.displayName}" /></td>
				<spring:nestedPath path="HrReport.parameters[${varStatus.index}]">
					<td><c:choose>
						<c:when
							test="${parameter.interfaceClass == 'class java.lang.Boolean'}">
							<spring:bind path="valueBoolean">
								<select name="${status.expression}" id="valueBooleanSelect">
									<option value="true"
										<c:if test="${status.value == true}">selected="selected"</c:if>><spring:message
										code="general.true" /></option>
									<option value="false"
										<c:if test="${status.value == false}">selected="selected"</c:if>><spring:message
										code="general.false" /></option>
								</select>
								<c:if test="${status.errorMessage != ''}">
									<span class="error">${status.errorMessage}</span>
								</c:if>
							</spring:bind>
						</c:when>
						<c:when
							test="${parameter.interfaceClass == 'class org.openmrs.Concept'}">
							<spring:bind path="valueConcept">
								<openmrs:fieldGen type="org.openmrs.Concept"
									formFieldName="${status.expression}" val="${status.value}"
									parameters="isNullable=true" />
								<c:if test="${status.errorMessage != ''}">
									<span class="error">${status.errorMessage}</span>
								</c:if>
							</spring:bind>
						</c:when>
						<c:when
							test="${parameter.interfaceClass == 'class java.util.Date'}">
							<spring:bind path="valueDate">
								<input type="text" name="${status.expression}" size="10"
									value="${status.value}" onClick="showCalendar(this)"
									id="${status.expression}" />
								(<spring:message code="general.format"/>: <openmrs:datePattern />)
								<c:if test="${status.errorMessage != ''}">
									<span class="error">${status.errorMessage}</span>
								</c:if>
							</spring:bind>
						</c:when>
						<c:when
							test="${parameter.interfaceClass == 'class org.openmrs.Location'}">
							<spring:bind path="valueLocation">
								<select name="${status.expression}">
									<openmrs:forEachRecord name="location">
										<option value="${record.locationId}"
											<c:if test="${status.value == record.locationId}">selected="selected"</c:if>>${record.name}</option>
									</openmrs:forEachRecord>
								</select>
								<c:if test="${status.errorMessage != ''}">
									<span class="error">${status.errorMessage}</span>
								</c:if>
							</spring:bind>
						</c:when>
						<c:otherwise>
							<spring:bind path="default_value">
								<input type="text" name="${status.expression}"
									value="${status.value}" size="10" />
								<c:if test="${status.errorMessage != ''}">
									<span class="error">${status.errorMessage}</span>
								</c:if>
							</spring:bind>
						</c:otherwise>
					</c:choose></td>
				</spring:nestedPath>
			</tr>
		</c:if>
		</c:if>
	</c:forEach>
</table>
</c:if>
Output Format:
<select name="outputFormat" id="outputFormat">
<option value="PDF">PDF</option>
<option value="Excel">Excel</option>
</select>
</fieldset>
<br />
<input type="submit" name="action"
	value="<spring:message code="Generate"/>">
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>