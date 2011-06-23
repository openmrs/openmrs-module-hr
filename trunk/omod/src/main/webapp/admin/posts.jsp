<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage Posts" /></h2>
<a href="post.form"><spring:message code="Add New Post"/></a>
<table>
 <c:forEach var="Post" items="${PostList}">
      <tr>
        <td>${Post.postId}</td>
        <td>${Post.hrJobTitle.title}</td>
        <td>${Post.locationId}</td>
        <td>${Post.fundingSource}</td>
      </tr>		
  </c:forEach>
</table>
<%@ include file="/WEB-INF/template/footer.jsp"%>