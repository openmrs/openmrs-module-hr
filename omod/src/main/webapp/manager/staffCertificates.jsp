<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Certificates" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>


<%@ include file="staffLocalHeader.jsp" %>

<script type="text/javascript">
window.onload=function(){
    var table = document.getElementById('CertificatesTable');
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

#CertificatesTable tbody tr:hover {
	cursor:pointer;
	background-color: #F0E68C;
}

</style>

<table width="100%">
    <tr>
        <td width="72%"><input type="button" value="Add New Certificate" onclick="document.location.href='staffCertificate.form'"/>
    </tr>
</table>


<br/>
<b class="boxHeader">
<spring:message code="Certificates"/>
</b>
<form method="post" class="box">
<table id="CertificatesTable" width="100%">
		<thead>
		<tr>
			<th> <spring:message code="Certificate" /> </th>
			<th> <spring:message code="Level" /></th>
			<th> <spring:message code="Current Certification Date" /> </th>
			<th> <spring:message code="Expiration Date" /></th>
		</thead>
		<tbody>
		<c:forEach var="staffCertificate" items="${staffCertificates}" varStatus="rowStatus">
			<tr onclick="document.location.href='staffCertificate.form?staffCertificateId=${staffCertificate.id}'">
                <td>${staffCertificate.hrCertificate.certificate}</td>
                <td>${staffCertificate.level}</td>
                <td><openmrs:formatDate date="${staffCertificate.currentCertDate}" type="medium" /></td>
                <td><openmrs:formatDate date="${staffCertificate.certExpirationDate}" type="medium" /></td>
            </tr>
        </c:forEach>
		</tbody>
		</table>
	</table>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>