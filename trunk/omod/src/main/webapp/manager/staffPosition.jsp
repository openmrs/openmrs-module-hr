<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>


<%@ include file="staffLocalHeader.jsp" %>
<script type="text/javascript"> 
window.onload=function(){
var table = document.getElementById('PostHistoryTable'); 
var trs = table.getElementsByTagName("tr"); 
for (i=2; i < trs.length ; i++) { 
if (i % 2 == 1) 
trs[i].className = "oddRow"; 
else
trs[i].className = "evenRow"; 	
}
}
</script>
<style type="text/css">

#PostHistoryTable tbody tr:hover {
	cursor:pointer;
	background-color: #F0E68C;
}

</style>
<table width="100%">
<tr><td width="72%"><input type="button" value="Move to a new position"/>&nbsp;&nbsp;<input type="button" value="Terminate Employment"/>&nbsp;&nbsp;<input type="button" value="Add a Current Assignment"/></td><td><input type="button" value="Add a previous position"/><input type="button" value="Add a previous assigment"/></td></tr>
</table>
<br/>
<b class="boxHeader">
<spring:message code="Post History"/>
</b>
<form method="post" class="box">
<table id="PostHistoryTable" width="100%">
		<thead>
		<tr>
			<th> <spring:message code="Edit" /></th>
			<th> <spring:message code="Location" /> </th>
			<th> <spring:message code="Job Title" /> </th>
			<th> <spring:message code="Start Date" /> </th>
			<th> <spring:message code="End Date" /> </th>
			<th> <spring:message code="End Reason" /> </th>
			<th> <spring:message code="Time Basis" /> </th>
			<th> <spring:message code="Grade" /> </th>
			
		</tr>
			<tr>
			<th> </th>
			<th> <spring:message code="Location" /> </th>
			<th> <spring:message code="Assignment" /> </th>
			<th> <spring:message code="Start Date" /> </th>
			<th> <spring:message code="End Date" /> </th>
			<th> <spring:message code="Supervisor" /> </th>
			<th> <spring:message code="Time Basis" /> </th>
			<th> <spring:message code="Work Schedule" /> </th>
			
		</tr>
		</thead>
		<tbody>
		<c:forEach var="postHistory" items="${PostHistories}" varStatus="rowStatus">
			<tr onclick="document.location.href='postHistory.form?postHistoryId=${postHistory.id}'"><td>P</td><td>${postHistory.hrPost.location.name}</td><td>${postHistory.hrPost.hrJobTitle.title}</td><td><openmrs:formatDate date="${postHistory.startDate}" type="medium" /></td><td><openmrs:formatDate date="${postHistory.endDate}" type="medium" /></td><td>${postHistory.endReason.name.name}</td><td>${postHistory.hrPost.timeBasis}</td><td>${postHistory.hrPost.hrJobTitle.grades}</td></tr>
			<c:forEach var="assignment" items="${postHistory.hrAssignments}" varStatus="innerStatus">
			<tr onclick="document.location.href='assignment.form?assignmentId=${assignment.id}'"><td>A</td><td>${assignment.location.name}</td><td>${assignment.assignment}</td><td><openmrs:formatDate date="${assignment.startDate}" type="medium" /></td><td><openmrs:formatDate date="${assignment.endDate}" type="medium" /></td><td>${assignment.supervisor.personName}</td><td>${assignment.timeBasis}</td><td>${assignment.workSchedule.name.name}</td></tr>
			</c:forEach>
		</c:forEach>
		</tbody>
		</table>
	</table>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>