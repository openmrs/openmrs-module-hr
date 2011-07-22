<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>


<script type="text/javascript"	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/hr/js/tablesorter_filter.js"/>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/hr/js/tablesorter.js"/>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/hr/js/tablesorter_pager.js"/>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/hr/styles/tablesorter.css"/>
<script type="text/javascript">
  jQuery(document).ready(function() {
    $("#StaffTable")
      .tablesorter({textExtraction: staffTextExtraction,debug: false,widthFixed: true, headers: {5: { sorter: false }}})
      .tablesorterFilter({filterContainer: $("#filter-box"),
                          filterColumns: [0],
                          filterCaseSensitive: false})
                          .tablesorterPager({container: $("#pager")}); 
    $("#StaffTable .header").click(function() {
        $("#pager .first").click();
    });
  });
  function updateList() {
		var url = "staff.list?";
		url += "allstaff=" + document.getElementById('allstaff').checked;
		url += "&alllocations="+document.getElementById('alllocations').checked;
		document.location = url;
	}
  var staffTextExtraction = function(node)  
  {  
	  if(node.getElementsByTagName("a")[0]!=undefined){
		  return node.getElementsByTagName("a")[0].innerHTML;
	  }
   	  else{
   	  	   return node.innerHTML;
   	  }
	  
  }
</script>
<h2><spring:message code="Find Staff" /></h2>
<br/>
Filter by name : <input name="filter" id="filter-box" value="" maxlength="30" size="30" type="text">
&nbsp;&nbsp;&nbsp;&nbsp;<input name="allstaff" id="allstaff" value="" type="checkbox" <c:if test="${param.allstaff}">checked</c:if> onclick="updateList()">Show all staff
&nbsp;&nbsp;&nbsp;&nbsp;<input name="alllocations" id="alllocations" value="" type="checkbox" <c:if test="${param.alllocations}">checked</c:if> onclick="updateList()">Show all locations
<br/><br/>
<b class="boxHeader">
	<spring:message code="Current Staff"/>
</b>
<form method="post" class="box">
<table id="StaffTable" width="100%" class="tablesorter">
		<thead>
		<tr>
			<th> <spring:message code="Name" /> </th>
			<th> <spring:message code="Location" /> </th>
			<th> <spring:message code="Job Title" /> </th>
			<th> <spring:message code="Status" /> </th>
			<th> <spring:message code="Sex" /> </th>
			<th> <spring:message code="DOB" /> </th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="staffListItem" items="${StaffListItemList}" varStatus="rowStatus">
			<tr>
				<td valign="top">
				<c:forEach var="name" items="${staffListItem.person.names}" varStatus="varStatus">
					<a id="anchor" href="staffDemographics.htm?staffId=${staffListItem.staff.staffId}" value="<c:if test='${name.preferred}'>${name.givenName} ${name.familyName}</c:if>"><c:if test="${name.preferred}">${name.givenName} ${name.familyName}</c:if></a>
				</c:forEach>
				</td>
				<td valign="top">${staffListItem.locationName}</td>
				<td valign="top">${staffListItem.jobTitle}	</td>
				<td valign="top">${staffListItem.staff.staffStatus.name.name}</td>
				<td valign="top">${staffListItem.person.gender}</td>
				<td valign="top"><openmrs:formatDate date="${staffListItem.person.birthdate}" type="medium" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</form>

<div id="pager" class="pager" align="right">
	
		<img src="${pageContext.request.contextPath}/moduleResources/hr/images/first.png" class="first"/>
		<img src="${pageContext.request.contextPath}/moduleResources/hr/images/prev.png" class="prev"/>
		<input type="text" style="width:25px" readonly="readonly" class="pagedisplay"/>
		<img src="${pageContext.request.contextPath}/moduleResources/hr/images/next.png" class="next"/>
		<img src="${pageContext.request.contextPath}/moduleResources/hr/images/last.png" class="last"/>
		<select class="pagesize">
			<option selected="selected"  value="10">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
			<option  value="40">40</option>
		</select>

</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>