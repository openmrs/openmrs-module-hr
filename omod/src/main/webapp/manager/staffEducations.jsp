<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Education" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>


<%@ include file="staffLocalHeader.jsp" %>

<script type="text/javascript">
window.onload=function(){
    var table = document.getElementById('EducationsTable');
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

#EducationsTable tbody tr:hover {
	cursor:pointer;
	background-color: #F0E68C;
}

</style>

<table width="100%">
    <tr>
        <td width="72%"><input type="button" value="<spring:message code="hr.education.add" />" onclick="document.location.href='staffEducation.form'"/>
    </tr>
</table>


<br/>
<b class="boxHeader">
<spring:message code="Education"/>
</b>
<form method="post" class="box">
<table id="EducationsTable" width="100%">
		<thead>
		<tr>
			<th> <spring:message code="hr.education.degree" /> </th>
			<th> <spring:message code="hr.education.institution" /></th>
			<th> <spring:message code="hr.education.major" /> </th>
		</thead>
		<tbody>
		<c:forEach var="education" items="${staffEducations}" varStatus="rowStatus">
			<tr onclick="document.location.href='staffEducation.form?educationId=${education.id}'">
                <td>
                    ${education.degree}
                </td>
                <td>${education.institution}</td>
                <td>${education.major}</td>
            </tr>
        </c:forEach>
		</tbody>
		</table>
	</table>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>