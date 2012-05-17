<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Posts" otherwise="/login.htm" redirect="/module/hr/admin/posts.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Post" /></h2>
<c:if test="${post.retired}">
<form action="" method="post">
	<div class="retiredMessage">
	<div>
	<spring:message code="This Post is retired by"/>
	${post.retiredBy.personName}
	<openmrs:formatDate date="${post.dateRetired}" type="medium" />
	-
	${post.retireReason}
	<input type="submit" value='<spring:message code="Unretire Post"/>' name="unretirePost"/>
	</div>
	</div>
	</form>
</c:if>
<spring:hasBindErrors name="post">
	<spring:message code="fix.error"/>
	<c:forEach items="${errors.allErrors}" var="error">
	<c:if test="${error.code == 'statusChange'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
</c:forEach>
	<br />
</spring:hasBindErrors>
<form method="post">
<fieldset>
<table>
	<tr>
		<th valign="top"><spring:message code="Job Title"/></th>
		<td>
			<spring:bind path="post.hrJobTitle">	
				<select name="${status.expression}" id="${status.expression}">
					<c:forEach items="${JobList}" var="job" varStatus="varStatus">
						<c:if test='${job.retired==false}'>
						<option value="${job.id}" <c:if test="${status.value== job.id}">selected</c:if>>${job.title}</option>
						</c:if>
					</c:forEach>
     		</select> 
     		<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Location"/></th>
		<td>
			<spring:bind path="post.location">	
				<select name="${status.expression}" id="${status.expression}">
					<c:forEach items="${LocationList}" var="location" varStatus="varStatus">
				 		<option value="${location.id}" <c:if test="${ status.value== location.id}">selected</c:if>>${location.name}</option>
					</c:forEach>
     		</select> 
     		<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Status"/></th>
		<td>
			<spring:bind path="post.status">	
				<select name="${status.expression}" id="${status.expression}">
					<c:forEach items="${PostStatusAnswers}" var="answer">
						<option value="${answer.answerConcept}" <c:if test="${ status.value == answer.answerConcept.conceptId}">selected</c:if>>${answer.answerConcept.name.name}</option>
					</c:forEach>
					<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     		</select> 
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Time Basis"/></th>
		<td>
			<spring:bind path="post.timeBasis">	
				<input type="text" name="${status.expression}" size="40" value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if> 
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Funded By"/></th>
		<td>
			<spring:bind path="post.fundingSource">	
				<input type="text" name="${status.expression}" size="40" value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if> 
			</spring:bind>
		</td>
	</tr>
</table>
<br />
<input type="submit" value="<spring:message code="Save Post"/>" name="save">

</fieldset>
<br/>
<br/>

<c:if test="${not post.retired && post.postId!=0 && prevStatus.name.name!='Filled'}">
	<fieldset>
			<h4><spring:message code="Retire Post"/></h4>
			
			<b><spring:message code="general.reason"/></b>
			<input type="text" value="" size="40" name="retireReason" />
			<spring:hasBindErrors name="post">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'retireReason'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span></c:if>
				</c:forEach>
			</spring:hasBindErrors>
			<br/>
			<input type="submit" value='<spring:message code="Retire Post"/>' name="retirePost"/>
		</fieldset>
</c:if>

<br/>
<%@ include file="/WEB-INF/template/footer.jsp"%>
