<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Evaluations" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>


<%@ include file="staffLocalHeader.jsp" %>

<script type="text/javascript">
window.onload=function(){
    var table = document.getElementById('EvaluationsTable');
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

#EvaluationsTable tbody tr:hover {
	cursor:pointer;
	background-color: #F0E68C;
}

</style>

<table width="100%">
    <tr>
        <td width="72%"><input type="button" value="<spring:message code="hr.evaluation.add" />" onclick="document.location.href='evaluation.form'"/>
    </tr>
</table>


<br/>
<b class="boxHeader">
<spring:message code="hr.evaluations"/>
</b>
<form method="post" class="box">
<table id="EvaluationsTable" width="100%">
		<thead>
		<tr>
			<th> <spring:message code="hr.evaluation.competency" /> </th>
			<th> <spring:message code="hr.evaluation.level" /></th>
			<th> <spring:message code="hr.evaluation.evaluation.date" /> </th>
			<th> <spring:message code="hr.evaluation.evaluation.evaluator" /></th>
		</thead>
		<tbody>
		<c:forEach var="evaluation" items="${evaluations}" varStatus="rowStatus">
			<tr onclick="document.location.href='evaluation.form?evaluationId=${evaluation.id}'">
                <td>
                    ${evaluation.hrCompetency.name}
                </td>
                <td>${evaluation.level}</td>
                <td><openmrs:formatDate date="${evaluation.evaluationDate}" type="medium" /></td>
                <td>${evaluation.evaluator.personName}</td>
            </tr>
        </c:forEach>
		</tbody>
		</table>
	</table>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>