<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Posts" otherwise="/login.htm" redirect="/module/hr/admin/Posts.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>
<openmrs:htmlInclude file="/scripts/dojoConfig.js" />
<openmrs:htmlInclude file="/scripts/dojo/dojo.js" />
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
function updateList() {
	var url = "posts.list?";
	url += "allposts=" + document.getElementById('allposts').checked;
	url += "&alllocations="+document.getElementById('alllocations').checked;
	document.location = url;
}
dojo.addOnLoad( function() {
	toggleRowVisibilityForClass("PostsTable", "retired", false);
})
</script>
<style type="text/css">

#postsUnder tbody tr:hover {
	cursor:pointer;
	background-color: #F0E68C;
}
#postsUnder tr td.nobg {
	background-color: white;
}

</style>
<h2><spring:message code="Manage Posts" /></h2>
<a href="post.form"><spring:message code="Add New Post"/></a>
<br/>
<br/>
<input name="allposts" id="allposts" value="" type="checkbox" <c:if test="${param.allposts}">checked</c:if> onclick="updateList()">Include non current posts
&nbsp;&nbsp;&nbsp;&nbsp;<input name="alllocations" id="alllocations" value="" type="checkbox" <c:if test="${param.alllocations}">checked</c:if> onclick="updateList()">Show all locations
<br/>
<br/>
<b class="boxHeader">
	<a style="display: block; float: right" href="#" onClick="return toggleRowVisibilityForClass('PostsTable', 'retired', false);">
		<spring:message code="general.toggle.retired" />
	</a>
	<spring:message code="Current Posts"/>
</b>

<form method="post" class="box">
<table id="PostsTable" width="80%" class="tablesorter">
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
			<c:if test="${prevJobTitle!='' and prevLocation!=''}"><%out.print("</tbody></table></td></tr>"); %></c:if>
			<c:set var="prevJobTitle" value="${postList.post.hrJobTitle.title}"/>
			<c:set var="prevLocation" value="${postList.post.location.name}"/>
			<tr><td align="left" width="5%"><label id="hideShowLabel${postList.post.hrJobTitle.jobId}${postList.post.location.id}" style="cursor:pointer;" onclick="hideShowDetails('${postList.post.hrJobTitle.jobId}','${postList.post.location.id}')">+</label></td><td width="30%" align="left">${postList.post.hrJobTitle.title}</td><td align="left" width="50%">${postList.post.location.name }</td><td></td><td></td></tr>
			<tr id="detailsRow${postList.post.hrJobTitle.jobId}${postList.post.location.id}" style="display:none;"><td colspan="5"><table id="postsUnder" width="80%">
			<thead><tr><th width="5%"></th><th align="left" width="20%"> <spring:message code="Time Basis" /></th><th align="left" width="5%"> <spring:message code="Status" /></th> <th align="left" width="20%"><spring:message code="Funding Source" /></th><th align="left" width="20%"> <spring:message code="Most recent"/></th></tr></thead>
			<tbody><tr <c:if test="${postList.post.retired}">class="retired  ${rowStatus.index % 2 == 0 ? 'evenRow' : 'oddRow' }"</c:if>  <c:if test="${!postList.post.retired}">class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" }'</c:if> onclick="document.location.href='post.form?postId=${postList.post.postId}'"><td width="5%" class="nobg"></td><td width="20%">${postList.post.timeBasis}</td><td width="5%">${postList.post.status.name.name}</td><td width="20%">${postList.post.fundingSource}</td><td width="20%">${postList.mostRecentIncumbent}</td></tr>
			</c:when>
			<c:otherwise>
			<tr <c:if test="${postList.post.retired}">class="retired  ${rowStatus.index % 2 == 0 ? 'evenRow' : 'oddRow' }"</c:if>  <c:if test="${!postList.post.retired}">class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" }'</c:if> onclick="document.location.href='post.form?postId=${postList.post.postId}'"><td width="5%" class="nobg"></td><td width="20%">${postList.post.timeBasis}</td><td width="5%">${postList.post.status.name.name}</td><td width="20%">${postList.post.fundingSource}</td><td width="20%">${postList.mostRecentIncumbent}</td></tr>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</table>
	</table>
</form>

<br/>
<br/>
<br/>
<br/>
<br/>
<%@ include file="/WEB-INF/template/footer.jsp"%>