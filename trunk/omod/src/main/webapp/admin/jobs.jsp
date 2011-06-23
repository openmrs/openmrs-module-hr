<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage jobs" /></h2>

<a href="job.form"><spring:message code="Add New Job"/></a>
 <table>
 <c:forEach var="Job" items="${JobList}">
      <tr>
        <td>${Job.jobId}</td>
        <td>${Job.title}</td>
        <td>${Job.cadre}</td>
      </tr>		
  </c:forEach>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>
