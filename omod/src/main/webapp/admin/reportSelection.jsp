<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>

<h2><spring:message code="Reports" /></h2>
<br/>

<br/>
<b class="boxHeader">
<spring:message code="HR Reports"/>
</b>
<form method="post" class="box">
<table id="ReportsTable" width="100%">
		<tr>
			<th> <spring:message code="Report Name" /> </th>
			<th> <spring:message code="Description" /> </th>
			
		</tr>
		<c:forEach var="report" items="${ReportList}" varStatus="rowStatus">
			<tr>
				<td valign="top" width="10%">
					<a href="generateReport.form?reportId=${report.reportId}">${report.name}</a>
				</td>
				<td valign="top" width="90%">${report.description}</td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>