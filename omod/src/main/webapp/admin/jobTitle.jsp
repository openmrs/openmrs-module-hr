<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage Job Titles" otherwise="/login.htm" redirect="/module/hr/admin/jobTitles.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="localHeader.jsp" %>

<openmrs:requireConfiguration propertyList="hr.setup" configurationPage="/module/hr/admin/setup.form?targetView=module/hr/admin/jobTitles" />

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
	var reqObj=getHTTPObject();
	$(document).ready(function(){
		$("#tree").treeview({
		animated: "fast",
		collapsed: true,
		control: "#treecontrol"
		});
	});
	function setAttributes(path,code){
		var url=path+"/moduleServlet/hr/AttributePopulator?code="+code;
		reqObj.open("GET",url,true);		
		reqObj.onreadystatechange=handleTableHttpResponse;
		reqObj.send(null);
	}
	function getHTTPObject() {
		  var xmlhttp;
		        try {
					if(window.ActiveXObject)
					{
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
					else if(window.XMLHttpRequest)
					{
					xmlhttp=new XMLHttpRequest();
					}
				} catch (E) {
					xmlhttp = false;
				}
		 return xmlhttp;
	}
	function handleTableHttpResponse() {
		results=reqObj.responseText.split("^");
		if(results[0]!="null")
		document.getElementById("hrIscoCodes.title").value=results[0];
		else
			document.getElementById("hrIscoCodes.title").value="";
		if(results[1]!="null")
		document.getElementById("iscoDefinition").value=results[1];
		else
			document.getElementById("iscoDefinition").value="";
		if(results[2]!="null")
		document.getElementById("iscoTasksInclude").value=results[2];
		else
			document.getElementById("iscoTasksInclude").value="";
		if(results[3]!="null")
		document.getElementById("iscoIncludedOccupations").value=results[3];
		else
			document.getElementById("iscoIncludedOccupations").value="";
		if(results[4]!="null")
		document.getElementById("iscoExcludedOccupations").value=results[4];
		else
			document.getElementById("iscoExcludedOccupations").value="";
	}
</script>
<c:if test="${job.retired}">
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
				<textarea id="${status.expression}" name="${status.expression}" rows="3" cols="80">${status.value}</textarea>
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
		<th valign="top"><spring:message code="ISCO Code Title"/></th>
		<td>
			<spring:bind path="job.hrIscoCodes.title">
				<input type="text" readonly="readonly" id="${status.expression}" name="${status.expression}" size="40"  value="${status.value}" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
				<spring:hasBindErrors name="job">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'IscoCode'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span></c:if>
				</c:forEach>
				</spring:hasBindErrors>
			<c:set var="length" value="0"/>
			<c:set var="loopStarted" value="false"/>
				<ul id="tree" class="treeview">
				<c:forEach var="code" items="${IscoCodeList}" >
					<c:set var="codeLength" value="${fn:length(code.codeIsco)}" />
					<c:if test="${(length- codeLength)>0}">
					<c:forEach begin="0" end="${length- codeLength-1}" step="1" varStatus="vs">
						<c:set var="loopStarted" value="true"/>				
						<c:choose>
						<c:when test="${vs.index!=2}">
							<% out.println("</li></ul>"); %>
						</c:when>
						<c:otherwise>
							<% out.println("</li>"); %>
						</c:otherwise>
					</c:choose>
					</c:forEach>
					</c:if>
					<c:choose>
					<c:when test="${fn:length(code.codeIsco)==1 }">
						<li <c:if test="${fn:startsWith(job.hrIscoCodes.codeIsco,code.codeIsco)}">class="open" </c:if>><span <c:if test="${fn:startsWith(job.hrIscoCodes.codeIsco,code.codeIsco)}">style="font-weight:bold"</c:if>>${code.title}
						<%out.print("</span>"); %>
					</c:when>
					<c:when test="${fn:length(code.codeIsco)==2 }">
						<ul><li <c:if test="${fn:startsWith(job.hrIscoCodes.codeIsco,code.codeIsco)}">class="open" </c:if>><span <c:if test="${fn:startsWith(job.hrIscoCodes.codeIsco,code.codeIsco)}">style="font-weight:bold"</c:if>>${code.title}
						<%out.print("</span>"); %>
					</c:when>
					<c:when test="${fn:length(code.codeIsco)==3 }">
						<ul><li <c:if test="${fn:startsWith(job.hrIscoCodes.codeIsco,code.codeIsco)}">class="open" </c:if>><span <c:if test="${fn:startsWith(job.hrIscoCodes.codeIsco,code.codeIsco)}">style="font-weight:bold"</c:if>>${code.title}
						<%out.println("</span>"); %>
					</c:when>
					<c:when test="${fn:length(code.codeIsco)==4 }">
						<c:set var="length" value="4"/>
						<ul><li <c:if test="${fn:startsWith(job.hrIscoCodes.codeIsco,code.codeIsco)}">class="open" </c:if>><input type="radio" id="IscoCode" name="IscoCode" <c:if test="${job.hrIscoCodes.codeIsco==code.codeIsco }">checked="checked"</c:if> value="${code.codeIsco}" onclick="setAttributes('${pageContext.request.contextPath}','${code.codeIsco}')"/>${code.title}
						<%out.println("</li></ul>"); %>
					</c:when>
					</c:choose>
					<c:if test="${loopStarted}">
						<c:set var="length" value="0"/>
						<c:set var="loopStarted" value="false"/>
					</c:if>
				</c:forEach>
				<%out.println("</li></ul></ul></li></ul>");%>
			</spring:bind>
		</td>
	</tr>	
	<tr>
		<th valign="top"><spring:message code="Definition"/></th>
		<td>
			<spring:bind path="job.hrIscoCodes.definition">
				<textarea readonly="readonly" id="iscoDefinition" name="iscoDefinition" rows="7" cols="80">${fn:trim(status.value)}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>				
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Tasks Include"/></th>
		<td>
			<spring:bind path="job.hrIscoCodes.tasksInclude">
				<textarea readonly="readonly" id="iscoTasksInclude" name="iscoTasksInclude" rows="7" cols="80">${fn:trim(status.value)}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>				
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Included Occupations"/></th>
		<td>
			<spring:bind path="job.hrIscoCodes.includedOccupations">
				<textarea readonly="readonly" id="iscoIncludedOccupations" name="iscoIncludedOccupations" rows="7" cols="80">${fn:trim(status.value)}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>				
			</spring:bind>
		</td>
	</tr>
	<tr>
		<th valign="top"><spring:message code="Excluded Occupations"/></th>
		<td>
			<spring:bind path="job.hrIscoCodes.excludedOccupations">
				<textarea readonly="readonly" id="iscoExcludedOccupations" name="iscoExcludedOccupations" rows="7" cols="80">${fn:trim(status.value)}</textarea>
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
