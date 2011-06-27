<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="localHeader.jsp" %>

<script type="text/javascript"	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/hr/js/jquery.cookie.js"/>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/hr/js/jquery.treeview.js"/>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/hr/styles/jquery.treeview.css"/>
<h2><spring:message code="Job Title" /></h2>
<style type="text/css">
	th {
		text-align: left;
	}
</style>
	<script type="text/javascript">
	var nameArray=new Array();
	$(document).ready(function(){
		$("#tree").treeview({
		animated: "fast",
		collapsed: true,
		control: "#treecontrol"
		});
	});
	/* window.onload=function createArray()
	{
		<c:forEach var="code" items="${IscoCodeList}">
		nameArray.push("${code.title}");
		</c:forEach>
	} */
	
</script>
<c:if test="${jobs.retired}">
<form action="" method="post">
	<div class="retiredMessage">
	<div>
	<spring:message code="This Job Title is retired by"/>
	${job.retiredBy.personName}
	<openmrs:formatDate date="${job.dateRetired}" type="medium" />
	-
	${job.retireReason}
	<input type="submit" value='<spring:message code="Unretire Job Title"/>' name="unretireJobTitle"/>
	</div>
	</div>
	</form>
</c:if>
<spring:hasBindErrors name="job">
	<spring:message code="fix.error"/>
	<br />
</spring:hasBindErrors>
<form method="post">
<fieldset>
<table cellpadding="3" cellspacing="0" id="table">
	<tr>
		<th valign="top"><spring:message code="Job Title"/></th>
		<td>
			<spring:bind path="job.title">	
				<input type="text" name="${status.expression}" size="40" value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if> 
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Description"/></th>
		<td>
			<spring:bind path="job.description">	
				<textarea name="${status.expression}" rows="3" cols="80">${status.value}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Cadre"/></th>
		<td>
		<spring:bind path="job.cadre">
			<select name="${status.expression}" id="${status.expression}">
					<c:forEach items="${CadreAnswers}" var="answer" varStatus="status">
						<option value="${answer.answerConcept}" <c:if test="${ job.cadre.id == answer.answerConcept.conceptId}">selected</c:if>>${answer.answerConcept.name.name}</option>
					</c:forEach>
     		</select>
     		<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     	</spring:bind>
     		
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Grades"/></th>
		<td>
			<spring:bind path="job.grades">	
				<input type="text" name="${status.expression}" size="40"  value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if> 
			</spring:bind>
		</td>
	</tr>	
	<tr>
		<th valign="top"><spring:message code="ISCO Code"/></th>
		<td>
		<spring:bind path="job.hrIscoCodes.title">	
		<input type="text" name="${status.expression}" size="40"  value="${job.hrIscoCodes.title}"/>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
		</spring:bind>
		<%--  <!-- <div id="iscoCodeTree" style="height: 200px;width: 585px;overflow: auto;border:solid grey 1px;"> -->
		<ul id="tree" class="treeview">
		<c:forEach var="code" items="${IscoCodeList}" varStatus="rowStatus">
		</c:forEach>
		</ul> --%>
		<!-- </div> --> 
		</td>
	</tr>	
	<tr>
		<th valign="top"><spring:message code="Definition"/></th>
		<td>
			<spring:bind path="job.hrIscoCodes.definition">
				<textarea readonly="readonly" name="${status.expression}" rows="7" cols="80">${fn:trim(status.value)}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>				
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Tasks Include"/></th>
		<td>
			<spring:bind path="job.hrIscoCodes.tasksInclude">
				<textarea readonly="readonly" name="${status.expression}" rows="7" cols="80">${fn:trim(status.value)}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>				
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Included Occupations"/></th>
		<td>
			<spring:bind path="job.hrIscoCodes.includedOccupations">
				<textarea readonly="readonly" name="${status.expression}" rows="7" cols="80">${fn:trim(status.value)}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>				
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Excluded Occupations"/></th>
		<td>
			<spring:bind path="job.hrIscoCodes.excludedOccupations">
				<textarea readonly="readonly" name="${status.expression}" rows="7" cols="80">${fn:trim(status.value)}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>				
			</spring:bind>
		</td>
	</tr>
	
</table>
<br/>
<input type="submit" value='<spring:message code="Save Job Title"/>'>
&nbsp;
</fieldset>
<br/>
<br/>
<c:if test="${not job.retired && job.jobId!='0'}">
	
		<fieldset>
			<h4><spring:message code="Retire this job title"/></h4>
			
			<b><spring:message code="general.reason"/></b>
			<input type="text" value="" size="40" name="retireReason" />
			<spring:hasBindErrors name="job">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'retireReason'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span></c:if>
				</c:forEach>
			</spring:hasBindErrors>
			<br/>
			<input type="submit" value='<spring:message code="Retire Job Title"/>' name="retireJobTitle"/>
		</fieldset>
	</form>
</c:if>
<%@ include file="/WEB-INF/template/footer.jsp"%>
