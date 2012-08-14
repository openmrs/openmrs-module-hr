<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Staff Certificates" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
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
        <td width="72%"><input type="button" value="<spring:message code="hr.certificates.add" />" onclick="document.location.href='staffCertificate.form'"/>
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
			<th> <spring:message code="hr.certificate" /> </th>
			<th> <spring:message code="hr.certificates.level" /></th>
			<th> <spring:message code="hr.certificates.certification.date" /> </th>
			<th> <spring:message code="hr.certificates.expiration.date" /></th>
		</thead>
		<tbody>
		<c:forEach var="staffCertificate" items="${staffCertificates}" varStatus="rowStatus">
			<tr onclick="document.location.href='staffCertificate.form?staffCertId=${staffCertificate.id}'">
                <td>
                    <c:choose>
                        <c:when test="${not empty staffCertificate.certCancel}">
                            <del>${staffCertificate.hrCertificate.certificate}</del>
                        </c:when>
                        <c:otherwise>
                            ${staffCertificate.hrCertificate.certificate}
                        </c:otherwise>
                    </c:choose>
                </td>
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