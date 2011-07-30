<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>

<spring:hasBindErrors name="postHistory">
	<spring:message code="fix.error"/>
	<br />
</spring:hasBindErrors>
<c:choose>
<c:when test="${not empty param.addprev}">
<h2><spring:message code="Add a previous position" /></h2>
</c:when>
<c:when test="${createNew==true}">
<h2><spring:message code="Create Position" /></h2>
</c:when>
<c:when test="${assignment.endReason==null}">
<h2><spring:message code="End Position" /></h2>
</c:when>
<c:otherwise>
<h2><spring:message code="Position" /></h2>
</c:otherwise>
</c:choose>
<form method="post">
<fieldset>
<table>
	<tr>
		<th align="left" valign="top"><spring:message code="Location"/></th>
		<td>
			<spring:bind path="postHistory.hrPost.location">
			<c:choose>
				<c:when test='${createNew==true || param.addprev==true}'>
				<select name="location" id="${status.expression}">
					<c:forEach items="${locationList}" var="location" varStatus="status">
						<option value="${location.id}">${location.name}</option>
					</c:forEach>
     			</select> 
				</c:when>
				<c:otherwise>
				<label>${postHistory.hrPost.location.name}</label>
				</c:otherwise>
				</c:choose>	
				<%-- <select name="job" id="${status.expression}">
					<c:forEach items="${JobList}" var="job" varStatus="status">
						<option value="${job.id}" <c:if test="${ post.hrJobTitle.title == job.title}">selected</c:if>>${job.title}</option>
					</c:forEach>
     		</select>  --%>
     		</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Post"/></th>
		<td>
			<spring:bind path="postHistory.hrPost">	
			<c:choose>
				<c:when test='${createNew==true || param.addprev==true}'>
				<select name="post" id="${status.expression}">
					<c:forEach items="${postList}" var="post" varStatus="status">
						<option value="${post}">${post.hrJobTitle.title}</option>
					</c:forEach>
     			</select> 
     			</c:when>
     			<c:otherwise>
     		<label>${postHistory.hrPost.hrJobTitle.title}</label>
     		</c:otherwise>
     		</c:choose>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Grade"/></th>
		<td>
			<spring:bind path="postHistory.grade">	
			<c:choose>
				<c:when test='${createNew==true || param.addprev==true}'>
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
				</c:when>
				<c:therwise>
				<label>${status.value}</label>
				</c:therwise>
			</c:choose>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="Start Date"/></th>
		<td>
			<spring:bind path="postHistory.startDate">	
				<label><openmrs:formatDate date="${postHistory.startDate}" type="medium" /></label> 
			</spring:bind>
		</td>
	</tr>
	
	<tr>
		<th align="left" valign="top"><spring:message code="End Date"/></th>
		<td>
			<spring:bind path="postHistory.endDate">
			<openmrs:formatDate date="${postHistory.endDate}" type="medium" />	
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th align="left" valign="top"><spring:message code="End Reason"/></th>
		<td>
			<spring:bind path="postHistory.endReason">	
			<label>${postHistory.endReason.name.name}</label>
			</spring:bind>
			
		</td>
		<c:if test='${fn:endsWith(postHistory.endReason.name.name,":")}'>
		<th align="left" valign="top"><spring:message code="End Reason Text"/></th>
		<td>
		<spring:bind path="postHistory.endReasonOther">	
				<input type="text" name="${status.expression}" size="40" value="${postHistory.endReasonOther}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if> 
			</spring:bind>
		</td>
		</c:if>	
	</tr>
</table>
<br />

<input type="submit" value="<spring:message code="Save Post History"/>" name="save">

</fieldset>
<%@ include file="/WEB-INF/template/footer.jsp"%>