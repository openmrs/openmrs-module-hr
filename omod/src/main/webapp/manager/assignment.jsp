<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Assignments" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />

<script type="text/javascript">
function toggleReasonText()
{
var index=document.getElementById("endReason").selectedIndex;
var options=document.getElementById("endReason").options;
	
if(options[index].text.match(":$")==":")	
	document.getElementById("endReasonText").style.display="";
else
	document.getElementById("endReasonText").style.display="none";
}
</script>

<spring:hasBindErrors name="assignment">
<c:set var="errorExist" value="true"/>
	<spring:message code="fix.error"/>
	<c:forEach items="${errors.allErrors}" var="error">
	<c:if test="${error.code == 'startDateInvalid' or error.code == 'startEnd' or error.code == 'endBeforeStart'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
</c:forEach>
	<br />
</spring:hasBindErrors>
<c:choose>
<c:when test="${addprev==true}">
<h2><spring:message code="Add a previous assignment" /></h2>
</c:when>
<c:when test="${createNew==true}">
<h2><spring:message code="Create Assignment" /></h2>
</c:when>
<c:when test='${assignment.endReason==null or (fn:endsWith(assignment.endReason.name.name,":") and empty assignment.endReasonOther )}'>
<h2><spring:message code="End Assignment" /></h2>
</c:when>
<c:otherwise>
<h2><spring:message code="Assignment" /></h2>
</c:otherwise>
</c:choose>
<form method="post">
<fieldset>
<table width="100%">
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Location"/></th>
		<td>

				<c:choose>
				<c:when test='${createNew==true or addprev==true}'>
				<spring:bind path="assignment.location">
				<select name="location" id="${status.expression}">
					<c:forEach items="${locationList}" var="location">
						<option value="${location.id}" <c:if test='${location.id == status.value}'>selected="selected"</c:if>>${location.name}</option>
					</c:forEach>
				</select>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     			</spring:bind> 
     			</c:when>
     			<c:otherwise>
     			<spring:bind path="assignment.location.name">
     			<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly"/>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
				</spring:bind>
     			</c:otherwise>
     			</c:choose>
			
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Post"/></th>
		<td>
			<c:choose>
			<c:when test='${createNew==true}'>
				<label>${currentPost.hrPost.hrJobTitle.title}</label>
     		</c:when>
     		<c:when test='${addprev==true}'>
     		<spring:bind path="assignment.hrPostHistory">
     		<select name="${status.expression}" id="${status.expression}">
					<option value=""></option>
					<c:forEach items="${postHistories}" var="postHistory">
						<option value="${postHistory.id}" <c:if test='${postHistory.id == status.value}'>selected="selected"</c:if>>${postHistory.hrPost.hrJobTitle.title}&nbsp;&nbsp;&nbsp;&nbsp;<openmrs:formatDate date="${postHistory.startDate}" type="medium" /> to <openmrs:formatDate date="${postHistory.endDate}" type="medium" /></option>
					</c:forEach>
     		</select>
     		<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     		</spring:bind>
     		</c:when>
     		<c:otherwise>
     		<spring:bind path="assignment.hrPostHistory.hrPost.hrJobTitle.title">
     			<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly"/>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
				</spring:bind>
     		</c:otherwise>
     		</c:choose>
     		
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Assignment"/></th>
		<td>
		
			<spring:bind path="assignment.assignment">	
			<c:choose>
			<c:when test='${createNew==true or addprev==true}'>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:otherwise>
			</c:choose>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Time Basis"/></th>
		<td>
			<spring:bind path="assignment.timeBasis">
			<c:choose>
			<c:when test='${createNew==true or addprev==true}'>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:otherwise>
			</c:choose> 
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Note"/></th>
		<td>
			<spring:bind path="assignment.note">
			<c:choose>
			<c:when test='${createNew==true or addprev==true}'>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>	
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:otherwise>
			</c:choose>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Work Schedule"/></th>
		<td>
			<spring:bind path="assignment.workSchedule">
			<c:if test='${not empty assignment.workSchedule or createNew==true or addprev==true}'>
			<select name="${status.expression}" id="${status.expression}" <c:if test='${empty createNew and empty addprev}'>disabled="disabled"</c:if>>
					<c:forEach items="${workScheduleAnswers}" var="workSchedule">
						<option value="${workSchedule.answerConcept}" <c:if test='${workSchedule.answerConcept.id == status.value}'>selected="selected"</c:if>>${workSchedule.answerConcept.name.name}</option>
					</c:forEach>
     		</select>
     		<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     		<c:if test='${empty createNew and empty addprev}'>
 			<c:forEach items="${workScheduleAnswers}" var="workSchedule">
				 <c:if test='${workSchedule.answerConcept.id == status.value}'><input type="hidden" name="${status.expression}" id="${status.expression}" value="${workSchedule.answerConcept}"></c:if>
			</c:forEach>
			</c:if>
			</c:if>
		</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Start Date"/></th>
		<td>
			<spring:bind path="assignment.startDate">	
			<c:choose>
			<c:when test="${createNew==true or addprev==true}">
			<input type="text" name="${status.expression}" size="10" 
					   value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
				(<spring:message code="general.format"/>: <openmrs:datePattern />)
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
				<spring:hasBindErrors name="assignment">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'startDateInvalid'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
				</c:forEach>
				</spring:hasBindErrors>
			</c:when>
			<c:otherwise>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:otherwise>
			</c:choose> 
			</spring:bind>
		</td>
	</tr>
	<c:if test='${createNew!=true or addprev==true}'>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="End Date"/></th>
		<td>
			<spring:bind path="assignment.endDate">
			<c:choose>
			<c:when test="${assignment.endReason==null or empty assignment.endReason or errorExist==true}">
			<input type="text" name="${status.expression}" size="10" 
					   value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
				(<spring:message code="general.format"/>: <openmrs:datePattern />)
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:otherwise>
			</c:choose>	
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="End Reason"/></th>
		<td>
		
			<c:choose>
			<c:when test="${assignment.endReason==null or empty assignment.endReason or errorExist==true}">
			<spring:bind path="assignment.endReason">
			<select name="endReason" id="${status.expression}" onchange="toggleReasonText();">
				<option value=""></option>
					<c:forEach items="${EndReasons}" var="endReason">
						<option value="${endReason.answerConcept}" <c:if test='${endReason.answerConcept.id == status.value}'>selected="selected"</c:if>>${endReason.answerConcept.name.name}</option>
					</c:forEach>
     		</select>
     		<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     		</spring:bind>
     		</c:when>
     		<c:otherwise>
			<spring:bind path="assignment.endReason.name.name">
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr id="endReasonText" <c:if test='${!fn:endsWith(assignment.endReason.name.name,":")}'>style="display:none" </c:if>>
		<th width="10%" align="left" valign="top"><spring:message code="End Reason Text"/></th>
		<td>
		<spring:bind path="assignment.endReasonOther">	
		<c:choose>
			<c:when test="${assignment.endReason==null or empty assignment.endReason or errorExist==true}">
				<input type="text" name="${status.expression}" size="40" value="${assignment.endReasonOther}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border:none" readonly="readonly" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>		
			</c:otherwise>
		</c:choose> 
			</spring:bind>
		</td>
	</tr>
	</c:if>

</table>
<br />
<c:choose>
<c:when test="${addprev==true}">
<input type="hidden" name="actionString" value="addprev"/>
</c:when>
<c:when test="${createNew==true}">
<input type="hidden" name="actionString" value="createNew"/>
</c:when>
<c:when test='${assignment.endReason==null or empty assignment.endReason or (fn:endsWith(assignment.endReason.name.name,":") and empty assignment.endReasonOther )}'>
<input type="hidden" name="actionString" value="endAssignment"/>
</c:when>
</c:choose>
<c:if test='${createNew==true or assignment.endReason==null or addprev==true or (fn:endsWith(assignment.endReason.name.name,":") and empty assignment.endReasonOther )}'>
<input type="submit" value="<spring:message code="Save Assignment"/>" name="submit"/>
</c:if>
<input type="submit" value='<spring:message code="general.cancel"/>' name="submit"/>
</fieldset>
<br/>
<br/>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>