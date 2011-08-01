<%@ include file="/WEB-INF/template/include.jsp"%>

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
	url += "alllocations="+document.getElementById('alllocations').checked;
	if(addprev=="true")
	url += "&addprev="+addprev;
	else
	url += "&addprev=false";
	document.location = url;
}
</script>
<spring:hasBindErrors name="postHistory">
	<spring:message code="fix.error"/>
	<br />
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
					   value="" onClick="showCalendar(this)" id="vacateEndDate" />
				(<spring:message code="general.format"/>: <openmrs:datePattern />)
		</td>
		<th nowrap="nowrap" align="left" valign="top"><spring:message code="End Reason"/></th>
		<td nowrap="nowrap"> 
			<select name="vacateEndReason" id="vacateEndReason" onchange="toggleReasonText(this.id,'vacateEndReasonText');">
				<option value=""></option>
					<c:forEach items="${EndReasons}" var="endReason" varStatus="status">
						<option value="${endReason.answerConcept}">${endReason.answerConcept.name.name}</option>
					</c:forEach>
     		</select>
     	<span id="vacateEndReasonText" style="display:none">
			<b><spring:message code="End Reason Text"/></b>
			<input type="text" name="vacateEndReasonText" size="40" value="" />

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
<c:when test="${postHistory.endReason==null}">
<h2><spring:message code="End Position" /></h2>
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
				<select name="location" id="${status.expression}">
					<c:forEach items="${locationList}" var="location" varStatus="status">
						<option value="${location.id}">${location.name}</option>
					</c:forEach>
	   			</select>
	   			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     			<input name="alllocations" id="alllocations" value="" type="checkbox" <c:if test="${param.alllocations}">checked</c:if> onclick="updateLocations('${addprev}')">Show all locations
     			</spring:bind> 
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
				<select name="post" id="${status.expression}">
					<c:forEach items="${postList}" var="post" varStatus="status">
						<option value="${post}">${post.hrJobTitle.title}</option>
					</c:forEach>
     			</select>
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
			<c:when test="${postHistory.endReason==null and postHistory.startDate==null}">
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
		<th width="18%" align="left" valign="top"><spring:message code="End Date"/></th>
		<td>
			<spring:bind path="postHistory.endDate">
			<c:choose>
			<c:when test="${postHistory.endReason==null or empty postHistory.endReason}">
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
		<th width="18%" align="left" valign="top"><spring:message code="End Reason"/></th>
		<td>
			<c:choose>
			<c:when test="${postHistory.endReason==null or empty postHistory.endReason}">
			<spring:bind path="postHistory.endReason">
			<select name="endReason" id="${status.expression}" onchange="toggleReasonText(this.id,'endReasonText');">
				<option value=""></option>
					<c:forEach items="${EndReasons}" var="endReason" varStatus="status">
						<option value="${endReason.answerConcept}">${endReason.answerConcept.name.name}</option>
					</c:forEach>
     		</select>
     		</spring:bind>
     		</c:when>
     		<c:otherwise>
			<spring:bind path="postHistory.endReason.name.name">
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" style="border: none" readonly="readonly" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
			</c:otherwise>
			</c:choose>
			
		</td>
	</tr>
	<tr id="endReasonText" <c:if test='${!fn:endsWith(postHistory.endReason.name.name,":")}'>style="display:none" </c:if>>
		<th width="18%" align="left" valign="top"><spring:message code="End Reason Text"/></th>
		<td>
		<spring:bind path="postHistory.endReasonOther">	
		<c:choose>
			<c:when test="${postHistory.endReason==null or empty postHistory.endReason}">
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
</table>
<br />
<c:choose>
<c:when test="${addprev==true}">
<input type="hidden" name="action" value="addprev"/>
</c:when>
<c:when test="${createNew==true}">
<input type="hidden" name="action" value="movetonew"/>
</c:when>
<c:when test="${postHistory.endReason==null or empty postHistory.endReason}">
<input type="hidden" name="action" value="endPostHistory"/>
</c:when>
</c:choose>
<c:if test='${createNew==true or postHistory.endReason==null or addprev==true}'>
<input type="submit" value="<spring:message code="Save Post History"/>" name="save">
</c:if>
</fieldset>
<%@ include file="/WEB-INF/template/footer.jsp"%>