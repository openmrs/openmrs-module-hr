<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />

<script type="text/javascript">
function toggleReasonText(reason)
{
var index=document.getElementById("endReason").selectedIndex;
var options=document.getElementById("endReason").options;
	
if(options[index].text.match(":$")==":")	
	endReasonDiv.style.display="";
else
	endReasonDiv.style.display="none";
}
</script>

<spring:hasBindErrors name="assignment">
	<spring:message code="fix.error"/>
	<br />
</spring:hasBindErrors>
<c:choose>
<c:when test="${param.addprev==true}">
<h2><spring:message code="Add a previous assignment" /></h2>
</c:when>
<c:when test="${createNew==true}">
<h2><spring:message code="Create Assignment" /></h2>
</c:when>
<c:when test="${assignment.endReason==null}">
<h2><spring:message code="End Assignment" /></h2>
</c:when>
<c:otherwise>
<h2><spring:message code="Assignment" /></h2>
</c:otherwise>
</c:choose>
<form method="post">
<fieldset>
<table width="35%">
	<tr>
		<th align="left" valign="top"><spring:message code="Location"/></th>
		<td>
			<spring:bind path="assignment.location">	
				<c:choose>
				<c:when test='${createNew==true or param.addprev==true}'>
				<select name="location" id="${status.expression}">
					<c:forEach items="${locationList}" var="location" varStatus="status">
						<option value="${location.id}">${location.name}</option>
					</c:forEach>
     			</select> 
     			</c:when>
     			<c:otherwise>
     			<label>${assignment.location.name}</label>
     			</c:otherwise>
     			</c:choose>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Post"/></th>
		<td>
			<c:choose>
			<c:when test='${createNew==true or param.addprev==true}'>
				<label>${currentPost.hrPost.hrJobTitle.title}</label>
     		</c:when>
     		<c:otherwise>
     		<label>${assignment.hrPostHistory.hrPost.hrJobTitle.title}</label>
     		</c:otherwise>
     		</c:choose>
     		
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Assignment"/></th>
		<td>
		
			<spring:bind path="assignment.assignment">	
			<c:choose>
			<c:when test='${createNew==true or param.addprev==true}'>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
				<label>${status.value}</label>
			</c:otherwise>
			</c:choose>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Time Basis"/></th>
		<td>
			<spring:bind path="assignment.timeBasis">
			<c:choose>
			<c:when test='${createNew==true or param.addprev==true}'>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
				<label>${status.value}</label>
			</c:otherwise>
			</c:choose> 
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Note"/></th>
		<td>
			<spring:bind path="assignment.note">
			<c:choose>
			<c:when test='${createNew==true or param.addprev==true}'>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>	
				<label>${status.value}</label> 
			</c:otherwise>
			</c:choose>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Work Schedule"/></th>
		<td>
			<spring:bind path="assignment.workSchedule">
			<c:choose>
			<c:when test='${createNew==true or param.addprev==true}'>
			<select name="${status.expression}" id="${status.expression}">
					<c:forEach items="${workScheduleAnswers}" var="workSchedule" varStatus="status">
						<option value="${workSchedule.answerConcept}">${workSchedule.answerConcept.name.name}</option>
					</c:forEach>
     		</select>
     		</c:when>
     		<c:otherwise>	
				<label>${assignment.workSchedule.name.name}</label>
			</c:otherwise> 
			</c:choose>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Start Date"/></th>
		<td>
			<spring:bind path="assignment.startDate">	
			<c:choose>
			<c:when test="${assignment.endReason==null and assignment.startDate==null}">
			<input type="text" name="${status.expression}" size="10" 
					   value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
				(<spring:message code="general.format"/>: <openmrs:datePattern />)
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
				<label><openmrs:formatDate date="${assignment.startDate}" type="medium" /></label>
			</c:otherwise>
			</c:choose> 
			</spring:bind>
		</td>
	</tr>
	<c:if test='${createNew!=true or param.addprev==true}'>
	<tr>
		<th align="left" valign="top"><spring:message code="End Date"/></th>
		<td>
			<spring:bind path="assignment.endDate">
			<c:choose>
			<c:when test="${assignment.endReason==null}">
			<input type="text" name="${status.expression}" size="10" 
					   value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
				(<spring:message code="general.format"/>: <openmrs:datePattern />)
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
			<openmrs:formatDate date="${assignment.endDate}" type="medium" />
			</c:otherwise>
			</c:choose>	
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="End Reason"/></th>
		<td>
			<spring:bind path="assignment.endReason">
			<c:choose>
			<c:when test="${assignment.endReason==null}">
			<select name="endReason" id="${status.expression}" onchange="toggleReasonText();">
					<c:forEach items="${EndReasons}" var="endReason" varStatus="status">
						<option value="${endReason.answerConcept}">${endReason.answerConcept.name.name}</option>
					</c:forEach>
     		</select>
			</c:when>	
			<c:otherwise>
			<label>${assignment.endReason.name.name}</label>
			</c:otherwise>
			</c:choose>
			</spring:bind>
			
		</td>
	</tr>
	<tr id="endReasonDiv" <c:if test='${!fn:endsWith(assignment.endReason.name.name,":")}'>style="display:none" </c:if>>
		<th align="left" valign="top"><spring:message code="End Reason Text"/></th>
		<td>
		<spring:bind path="assignment.endReasonOther">	
		<c:choose>
			<c:when test="${assignment.endReason==null}">
				<input type="text" name="${status.expression}" size="40" value="${assignment.endReasonOther}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
				<label>${assignment.endReasonOther}</label>		
			</c:otherwise>
		</c:choose> 
			</spring:bind>
		</td>
	</tr>
	</c:if>

</table>
<br />
<c:choose>
<c:when test="${param.addprev==true}">
<input type="hidden" name="action" value="addPrevious"/>
</c:when>
<c:when test="${createNew==true}">
<input type="hidden" name="action" value="createNew"/>
</c:when>
<c:when test="${assignment.endReason==null}">
<input type="hidden" name="action" value="EndAssignment"/>
</c:when>
</c:choose>
<c:if test='${createNew==true or assignment.endReason==null or param.addprev==true}'>
<input type="submit" value="<spring:message code="Save Assignment"/>" name="save">
</c:if>
</fieldset>
<br/>
<br/>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>