<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Notes" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>


<%@ include file="staffLocalHeader.jsp" %>

<script type="text/javascript">
window.onload=function(){
    var table = document.getElementById('staffNotesTable');
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

#staffNotesTable tbody tr:hover {
	cursor:pointer;
	background-color: #F0E68C;
}

</style>

<table width="100%">
    <tr>
        <td width="72%"><input type="button" value="Add New ${noteType} Note" onclick="document.location.href='staffNote.form?noteType=${noteType}'"/>
    </tr>
</table>


<br/>
<b class="boxHeader">
<spring:message code="${noteType} Notes"/>
</b>
<form method="post" class="box">
<table id="staffNotesTable" width="100%">
		<thead>
		<tr>
			<th> <spring:message code="Date" /> </th>
			<th> <spring:message code="Added By" /> </th>
		</thead>
		<tbody>
		<c:forEach var="staffNote" items="${staffNotes}" varStatus="rowStatus">
			<tr onclick="document.location.href='staffNote.form?noteId=${staffNote.noteId}&noteType=${staffNote.noteType}'">
                <td>
                    ${staffNote.dateCreated}
                </td>
                <td>
                    ${staffNote.creator.personName}
                </td>
            </tr>
        </c:forEach>
		</tbody>
	</table>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>