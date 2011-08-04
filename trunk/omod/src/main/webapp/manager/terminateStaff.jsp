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
</script>
<form:form method="post">
<h2><spring:message code="Vacate current position" /></h2>

<fieldset>
<table>
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
					<c:forEach items="${EndReasons}" var="endReason" varStatus="status">
						<option value="${endReason.answerConcept}">${endReason.answerConcept.name.name}</option>
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
</fieldset>
</form:form>
<%@ include file="/WEB-INF/template/footer.jsp"%>