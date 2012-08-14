<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Posts" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
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
</script>
<spring:hasBindErrors name="postHistory">
<c:set var="errorExist" value="true"/>
<spring:message code="fix.error"/>
<c:forEach items="${errors.allErrors}" var="error">
	<c:if test="${error.code == 'startBeforeEnd' or error.code == 'afterAssignment'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
</c:forEach>
</spring:hasBindErrors>
<form method="post">
<h2><spring:message code="Terminate Employment" /></h2>

<fieldset>
<table width="70%">
	<tr>
		<th width="18%" align="left" valign="top"><spring:message code="End Date"/></th>
		<td>
			<spring:bind path="postHistory.endDate">
			<c:choose>
			<c:when test="${postHistory.endReason==null or empty postHistory.endReason or errorExist}">
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
			<c:when test="${postHistory.endReason==null or empty postHistory.endReason or errorExist}">
			<spring:bind path="postHistory.endReason">
			<select name="endReason" id="${status.expression}" onchange="toggleReasonText(this.id,'endReasonText');">
				<option value=""></option>
					<c:forEach items="${EndReasons}" var="endReason" varStatus="varStatus">
						<option value="${endReason.answerConcept}" <c:if test='${endReason.answerConcept.id == status.value}'>selected="selected"</c:if>>${endReason.answerConcept.name.name}</option>
					</c:forEach>
     		</select>
     		<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
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
			<c:when test="${postHistory.endReason==null or empty postHistory.endReason or errorExist}">
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
<input type="submit" value="<spring:message code="Terminate"/>" name="submit">
<input type="submit" value='<spring:message code="general.cancel"/>' name="submit">
</fieldset>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>