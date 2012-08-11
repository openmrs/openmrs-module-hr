<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Notes" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />

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

<spring:hasBindErrors name="staffNote">
<c:set var="errorExist" value="true"/>
	<spring:message code="fix.error"/>
	<br />
</spring:hasBindErrors>

<c:if test="{staffNote.noteId == 0}">
    <h2><spring:message code="Add New ${staffNote.noteType}" /></h2>
</c:if>

<form method="post" enctype="multipart/form-data" name="staffNoteForm">
<fieldset>
<table width="100%">
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Text"/></th>
		<td>
				<spring:bind path="staffNote.text">
                <textarea name="${status.expression}" value="${status.value}" rows="5" cols="50">${staffNote.text}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Date"/></th>
		<td>
     		<spring:bind path="staffNote.dateTime">
                <input type="text" name="${status.expression}" size="10"
                                       value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
                                (<spring:message code="general.format"/>: <openmrs:datePattern />)
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
		</td>
	</tr>
</table>
<br />
<br />
</fieldset>

<input type="submit" value="<spring:message code="Save ${staffNote.noteType} Note"/>" name="action"/>
<c:if test="${staffNote.noteId != 0}">
    <input type="submit" value="<spring:message code="Delete ${staffNote.noteType} Note"/>" name="action" onclick="return confirm('<spring:message code="Are you sure you want to Delete this note?"/>')"/>
</c:if>
</form>
<br/>
<hr/>
<br/>
<table width="100%">
    <tr>
        <td width="40%"><input type="button" value="Add Related ${staffNote.noteType} Note" onclick="document.location.href='staffNote.form?parent=${staffNote.noteId}&noteType=${staffNote.noteType}'"/>
        <c:choose>
            <c:when test="${not empty staffNote.parent && not empty staffNote.parent.parent}">
                <td width="40%"><a href="staffNote.form?noteId=${staffNote.parent.noteId}&parent=${staffNote.parent.parent.noteId}&noteType=${staffNote.noteType}">Goto Parent Note</a>
            </c:when>
            <c:when test="${not empty staffNote.parent}">
                <td width="40%"><a href="staffNote.form?noteId=${staffNote.parent.noteId}&noteType=${staffNote.noteType}">Goto Parent Note</a>
            </c:when>
        </c:choose>
    </tr>
</table>

<br/>
<b class="boxHeader">
<spring:message code="Related Notes"/>
</b>
<form method="post" class="box">
<table id="staffNotesTable" width="100%">
		<thead>
		<tr>
			<th> <spring:message code="Date" /> </th>
			<th> <spring:message code="Added By" /> </th>
		</thead>
		<tbody>
		<c:forEach var="staffNote" items="${childrenNotes}" varStatus="rowStatus">
			<tr onclick="document.location.href='staffNote.form?noteId=${staffNote.noteId}&noteType=${staffNote.noteType}&parent=${staffNote.parent.noteId}'">
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