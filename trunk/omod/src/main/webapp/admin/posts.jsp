<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage Posts" /></h2>
<a href="post.form"><spring:message code="Add New Post"/></a>
<br/>
<br/>
<b class="boxHeader">
	<a style="display: block; float: right" href="#">
		<spring:message code="general.toggle.retired" />
	</a>
	<spring:message code="Current Posts"/>
</b>
<script type="text/javascript">
function hideShowDetails(jobid,locationid)
{
	
	if (document.getElementById("detailsRow" + jobid + locationid).style.display == "none") {
			document.getElementById("hideShowLabel" + jobid + locationid).innerHTML = "-";
			document.getElementById("detailsRow" + jobid + locationid).style.display = "";
		} else {
			document.getElementById("hideShowLabel" + jobid + locationid).innerHTML = "+";
			document.getElementById("detailsRow" + jobid + locationid).style.display = "none";
		}

	}
</script>
<style type="text/css">

#postsUnder tr:hover {
	cursor:pointer;
	background-color: #F0E68C;
}
#postsUnder tr td.nobg {
	background-color: white;
}
#postsUnder tr.nobg {
	background-color: white;
}
</style>
<form method="post" class="box">
<table id="PostsTable" width="80%">
		<tr>
			<th> </th>
			<th> <spring:message code="Job Title" /> </th>
			<th> <spring:message code="Location" /> </th>
			
		</tr>
		<c:set var="prevJobTitle" value=""/>
		<c:set var="prevLocation" value=""/>
		<c:forEach var="postList" items="${PostListItemList}" varStatus="rowStatus">
			<c:choose>
			<c:when test="${(prevJobTitle != postList.post.hrJobTitle.title) or  (prevLocation != postList.post.location.name)}">
			<c:if test="${prevJobTitle!='' and prevLocation!=''}"><%out.print("</table></td></tr>"); %></c:if>
			<c:set var="prevJobTitle" value="${postList.post.hrJobTitle.title}"/>
			<c:set var="prevLocation" value="${postList.post.location.name}"/>
			<tr><td align="left" width="5%"><label id="hideShowLabel${postList.post.hrJobTitle.jobId}${postList.post.location.id}" style="cursor:pointer;" onclick="hideShowDetails('${postList.post.hrJobTitle.jobId}','${postList.post.location.id}')">+</label></td><td width="30%" align="left">${postList.post.hrJobTitle.title}</td><td align="left" width="50%">${postList.post.location.name }</td><td></td><td></td></tr>
			<tr id="detailsRow${postList.post.hrJobTitle.jobId}${postList.post.location.id}" style="display:none;"><td colspan="5"><table id="postsUnder" width="80%">
			<tr class="nobg"><th width="5%"></th><th align="left" width="20%"> <spring:message code="Time Basis" /></th><th align="left" width="5%"> <spring:message code="Status" /></th> <th align="left" width="20%"><spring:message code="Funding Source" /></th><th align="left" width="20%"> <spring:message code="Most recent"/></th></tr>
			<tr <c:if test="${postList.post.retired}">class="retired  ${rowStatus.index % 2 == 0 ? 'evenRow' : 'oddRow' }"</c:if>  <c:if test="${!postList.post.retired}">class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" }'</c:if> onclick="document.location.href='post.form?postId=${postList.post.postId}'"><td width="5%" class="nobg"></td><td width="20%">${postList.post.timeBasis}</td><td width="5%">${postList.post.status.name.name}</td><td width="20%">${postList.post.fundingSource}</td><td width="20%">${postList.mostRecentIncumbent}</td></tr>
			</c:when>
			<c:otherwise>
			<tr <c:if test="${postList.post.retired}">class="retired  ${rowStatus.index % 2 == 0 ? 'evenRow' : 'oddRow' }"</c:if>  <c:if test="${!postList.post.retired}">class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" }'</c:if> onclick="document.location.href='post.form?postId=${postList.post.postId}'"><td width="5%" class="nobg"></td><td width="20%">${postList.post.timeBasis}</td><td width="5%">${postList.post.status.name.name}</td><td width="20%">${postList.post.fundingSource}</td><td width="20%">${postList.mostRecentIncumbent}</td></tr>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</table>
	</table>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>