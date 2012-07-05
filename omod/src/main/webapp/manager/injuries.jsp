<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Notes" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>


<%@ include file="staffLocalHeader.jsp" %>

<script type="text/javascript">
window.onload=function(){
    var table = document.getElementById('injuriesTable');
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

#injuriesTable tbody tr:hover {
	cursor:pointer;
	background-color: #F0E68C;
}

</style>

<table width="100%">
    <tr>
        <td width="72%"><input type="button" value="Add New Injury Note" onclick="document.location.href='injury.form'"/>
    </tr>
</table>


<br/>
<b class="boxHeader">
<spring:message code="injuries"/>
</b>
<form method="post" class="box">
<table id="injuriesTable" width="100%">
		<thead>
		<tr>
			<th> <spring:message code="Date" /> </th>
		</thead>
		<tbody>
		<c:forEach var="injury" items="${injuries}" varStatus="rowStatus">
			<tr onclick="document.location.href='injury.form?noteId=${injury.noteId}'">
                <td>
                    ${injury.dateCreated}
                </td>
            </tr>
        </c:forEach>
		</tbody>
		</table>
	</table>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>