<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Add Post" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<script type="text/javascript">
function toggleReasonText(id,trid)
{
var index=document.getElementById(id).selectedIndex;
var options=document.getElementById(id).options;
	
if(options[index].text.match(":$")==":")	
	document.getElementById(trid).style.display="";
else
	document.getElementById(trid).style.display="none";
}
function updateLocations(addprev) {
	var url = "postHistory.form?";
	select=document.getElementById('hrPost.location');
	option=select.options[select.selectedIndex];
	url += "alllocations="+document.getElementById('alllocations').checked;
	url += "&locationId="+option.value;
	var vacateEndDate=document.getElementById("vacateEndDate");
	var vacateEndReason=document.getElementById("vacateEndReason");
	var vacateEndReasonText=document.getElementById("vacateEndReasonText");
	if(vacateEndDate!=null){
	if(vacateEndDate.value!="")
		url+="&ved="+vacateEndDate.value;
	if(vacateEndReason.options[vacateEndReason.selectedIndex].value!="")
		url+="&ver="+vacateEndReason.options[vacateEndReason.selectedIndex].value;
	if(vacateEndReasonText.value!=""&&vacateEndReasonText.value!=undefined)
		url+="&vert="+vacateEndReasonText.value;
	}
	if(addprev=="true")
	url += "&addprev="+addprev;
	else
	url += "&addprev=false";
	document.location = url;
}
</script>
<spring:hasBindErrors name="postHistory">
<c:set var="errorExist" value="true"/>
	<spring:message code="fix.error"/>
<c:forEach items="${errors.allErrors}" var="error">
	<c:if test="${error.code == 'vacateEndDate' or error.code == 'vacateEndReason' or error.code == 'vacateEndReasonText' or error.code == 'startBeforeVacate' or error.code == 'vacateStartEnd' or error.code == 'newPostOverlap' or error.code == 'Overlap' or error.code == 'NotPrevious' or error.code == 'startBeforeEnd'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
</c:forEach>
</spring:hasBindErrors>
<form method="post">
<c:if test='${createNew==true}'>
<h2><spring:message code="Vacate current position" /></h2>

<fieldset>
<table>
	<tr>
	<th nowrap="nowrap" align="left" valign="top"><spring:message code="End Date"/></th>
		<td nowrap="nowrap">
			<input type="text" name="vacateEndDate" size="10" 
					   value="${vacateEndDate}" onClick="showCalendar(this)" id="vacateEndDate" />
				(<spring:message code="general.format"/>: <openmrs:datePattern />)
		</td>
		<th nowrap="nowrap" align="left" valign="top"><spring:message code="End Reason"/></th>
		<td nowrap="nowrap"> 
			<select name="vacateEndReason" id="vacateEndReason" onchange="toggleReasonText(this.id,'vacateEndReasonText');">
				<option value=""></option>
					<c:forEach items="${EndReasons}" var="endReason">
						<option value="${endReason.answerConcept}" <c:if test='${endReason.answerConcept.id == vacateEndReason}'>selected="selected"</c:if>>${endReason.answerConcept.name.name}</option>
					</c:forEach>
     		</select>
     	<span id="vacateEndReasonText" style="display:none">
			<b><spring:message code="End Reason Text"/></b>
			<input type="text" name="vacateEndReasonText" size="40" value="${vacateEndReasonText}" />
		</span>
		</td>
	</tr>
</table>
</fieldset>
</c:if>
<c:choose>
<c:when test="${addprev==true}">
<h2><spring:message code="Add a previous position" /></h2>
</c:when>
<c:when test="${createNew==true}">
<h2><spring:message code="Create Position" /></h2>
</c:when>
<c:otherwise>
<h2><spring:message code="Position" /></h2>
</c:otherwise>
</c:choose>
<fieldset>
<table width="70%">
	<tr>
		<th width="18%" align="left" valign="top"><spring:message code="Location"/></th>
		<td>
				
				<c:choose>
				<c:when test='${createNew==true or addprev==true}'>
				<spring:bind path="postHistory.hrPost.location">
				<select name="location" id="${status.expression}" onchange="updateLocations('${addprev}')">
					<c:forEach items="${locationList}" var="location">
						<option value="${location.id}" <c:if test='${location.id == status.value or location.id==selectedLocation}'>selected="selected"</c:if>>${location.name}</option>
					</c:forEach>
	   			</select>
	   			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
	   			</spring:bind> 
     			<input name="alllocations" id="alllocations" value="" type="checkbox" <c:if test="${param.alllocations}">checked</c:if> onclick="updateLocations('${addprev}')">Show all locations
       			</c:when>
     			<c:otherwise>
     			<spring:bind path="postHistory.hrPost.location.name">
     			<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly"/>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
				</spring:bind>
     			</c:otherwise>
     			</c:choose>
			
		</td>
	</tr>
	<tr>
		<th width="18%" align="left" valign="top"><spring:message code="Post"/></th>
		<td>
				
			<c:choose>
				<c:when test='${createNew==true || addprev==true}'>
				<spring:bind path="postHistory.hrPost">
				<select name="${status.expression}" id="${status.expression}" style="width:250px">
					<option value="" selected="selected"> </option>
					<c:forEach items="${postList}" var="post">
						<option value="${post.id}" <c:if test='${post.id == status.value}'>selected="selected"</c:if>>${post.hrJobTitle.title}</option>
					</c:forEach>
     			</select>
     			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     			</spring:bind> 
     			</c:when>
     		<c:otherwise>
     		<spring:bind path="postHistory.hrPost.hrJobTitle.title"> 
     			<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly"/>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
     		</c:otherwise>
     		</c:choose>
     		
			
		</td>
	</tr>
	<tr>
		<th width="18%" align="left" valign="top"><spring:message code="Grade"/></th>
		<td>
			<spring:bind path="postHistory.grade">	
			<c:choose>
				<c:when test='${createNew==true || param.addprev==true}'>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
				</c:when>
				<c:otherwise>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly"/>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
				</c:otherwise>
			</c:choose>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="18%" align="left" valign="top"><spring:message code="Start Date"/></th>
		<td>
			<spring:bind path="postHistory.startDate">	
			<c:choose>
			<c:when test="${postHistory.endReason==null and postHistory.startDate==null or errorExist}">
			<input type="text" name="${status.expression}" size="10" 
					   value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
				(<spring:message code="general.format"/>: <openmrs:datePattern />)
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
				<input type="text" name="${status.expression}" size="10"  value="${status.value}" style="border: none" readonly="readonly" />(<spring:message code="general.format"/>: <openmrs:datePattern />)
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:otherwise>
			</c:choose> 
			</spring:bind>
		</td>
	</tr>
	<c:if test='${postHistory.endReason!=null or not empty postHistory.endReason or addprev==true}'>
	<tr>
		<th width="18%" align="left" valign="top"><spring:message code="End Date"/></th>
		<td>
			<spring:bind path="postHistory.endDate">
			<c:choose>
			<c:when test="${addprev==true}">
			<input type="text" name="${status.expression}" size="10" 
					   value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
				(<spring:message code="general.format"/>: <openmrs:datePattern />)
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:when>
			<c:otherwise>
				<input type="text" name="${status.expression}" size="10"  value="${status.value}" style="border: none" readonly="readonly" />(<spring:message code="general.format"/>: <openmrs:datePattern />)
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</c:otherwise>
			</c:choose>	
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="18%" align="left" valign="top"><spring:message code="End Reason"/></th>
		<td>
			<c:choose>
			<c:when test="${addprev==true}">
			<spring:bind path="postHistory.endReason">
			<select name="endReason" id="${status.expression}" onchange="toggleReasonText(this.id,'endReasonText');">
				<option value=""></option>
					<c:forEach items="${EndReasons}" var="endReason">
						<option value="${endReason.answerConcept}" <c:if test='${endReason.answerConcept.id == status.value}'>selected="selected"</c:if>>${endReason.answerConcept.name.name}</option>
					</c:forEach>
     		</select>
     		<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     		</spring:bind>
     		</c:when>
     		<c:otherwise>
     		<input type="text" name="" size="40"  value="${postHistory.endReason.name.name}" style="border: none" readonly="readonly" />
			</c:otherwise>
			</c:choose>
			
		</td>
	</tr>
	<tr id="endReasonText" <c:if test='${!fn:endsWith(postHistory.endReason.name.name,":")}'>style="display:none" </c:if>>
		<th width="18%" align="left" valign="top"><spring:message code="End Reason Text"/></th>
		<td>
		<spring:bind path="postHistory.endReasonOther">	
		<c:choose>
			<c:when test="${addprev==true}">
				<input type="text" name="${status.expression}" size="40" value="" />
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
</c:choose>
<c:if test='${createNew==true or addprev==true}'>
<input type="submit" value="<spring:message code="Save Post History"/>" name="submit">
</c:if>
<input type="submit" value='<spring:message code="general.cancel"/>' name="submit">
</fieldset>
<%@ include file="/WEB-INF/template/footer.jsp"%>