<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Trainings" otherwise="/login.htm" redirect="/module/hr/admin/trainingManagement.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>


<script type="text/javascript">
jQuery(document).ready(function(){
    		jQuery(".select-training").click(function(){
    		var val = jQuery(this).is(':checked');

            jQuery.getJSON("${pageContext.request.contextPath}" + "/module/hr/admin/trainingClasses.json?trainingClassId=1", function(json) {
               alert("JSON Data: " + json);
             });
            });
    	});
</script>

<%@ include file="localHeader.jsp" %>
<h2><spring:message code="Manage Trainings" /></h2>
<a href="training.form"><spring:message code="Add new training description"/></a>
<br /><br />

<b class="boxHeader">
<spring:message code="Current Trainings"/></b>
<form method="post" class="box">
	<table id="TrainingsTable" width="100%">
		<tr>
			<th></th>
			<th> <spring:message code="Category"/> </th>
			<th> <spring:message code="Name"/> </th>
			<th> <spring:message code="Description"/> </th>
			<th> <spring:message code="Retired"/> </th>
		</tr>
		<c:forEach var="training" items="${trainingsList}">
			<tr>
			    <td valign="top">
			        <input type="checkbox" name="training-${training.trainingId}" class="select-training" value="${training.trainingId}"/>
			    </td>
				<td valign="top">${training.category}</td>
				<td valign="top">${training.name}</td>
				<td valign="top">${training.description}</td>
				<td valign="top">
                    <c:choose>
                        <c:when test="${training.retired == true}">
                            <input type="checkbox" name="retired" checked disabled/>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" name="retired" checked disabled/>
                        </c:otherwise>
                    </c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>