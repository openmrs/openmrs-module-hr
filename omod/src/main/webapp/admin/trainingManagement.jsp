<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Trainings" otherwise="/login.htm" redirect="/module/hr/admin/trainingManagement.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>

<openmrs:requireConfiguration propertyList="hr.setup" configurationPage="/module/hr/admin/setup.form?targetView=module/hr/admin/trainingManagement" />

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/hr/styles/table.css"/>

<script type="text/javascript">
jQuery(document).ready(function(){
    		jQuery(".select-training").click(function(){
    		var val = jQuery(this).is(':checked');
            jQuery.getJSON("${pageContext.request.contextPath}" + "/ws/rest/v1/hr/training/"+jQuery(this).attr('value')+"?v=full" , function(trainingJson) {
                for(i in trainingJson.hrTrainingClasses)
                {
                    var trainingClass = trainingJson.hrTrainingClasses[i];
                    var table = jQuery("#TrainingClassesTable");
                    var row = jQuery('<tr id="row-'+trainingClass.uuid+'"></tr>');
                    var checkbox;
                    var retired;
                    checkbox = jQuery('<td valign="top"><input type="checkbox" name="trainingClass-'+trainingClass.uuid+'" class="select-trainingClass" value="'+trainingClass.uuid+'"/></td>"');
                    var startDate = jQuery('<td valign="top">'+trainingClass.startDate+'</td>');
                    var location = jQuery('<td valign="top">'+trainingClass.location+'</td>');
                    var organization = jQuery('<td valign="top">'+trainingClass.organization+'</td>');
                    row.append(checkbox);
                    row.append(startDate);
                    row.append(location);
                    row.append(organization);
                    if(val)
                        jQuery('#TrainingClassesTable tr:last').after(row);
                    else
                        jQuery('#row-'+trainingClass.uuid).remove();
                }
             });
            });
    	});

</script>


<h2><spring:message code="hr.trainings.manage" /></h2>
<a href="training.form"><spring:message code="hr.trainings.add"/></a>
<br /><br />

<b class="boxHeader">
<spring:message code="hr.trainings.current"/></b>
<form method="post" class="box">
    <div class = "fixedSizeTable">
	<table id="TrainingsTable" width="100%">
		<tr>
			<th></th>
			<th> <spring:message code="hr.trainings.category"/> </th>
			<th> <spring:message code="hr.trainings.name"/> </th>
			<th> <spring:message code="hr.trainings.description"/> </th>
			<th> <spring:message code="hr.trainings.retired"/> </th>
		</tr>
		<c:forEach var="training" items="${trainingsList}">
			<tr>
			    <td valign="top">
			        <c:choose>
			        <c:when test="${not training.retired}">
			            <input type="checkbox" name="training-${training.uuid}" class="select-training" value="${training.uuid}"/>
			        </c:when>
			        <c:otherwise>
                        <input type="checkbox" name="training-${training.uuid}" class="select-training" value="${training.uuid}" disabled/>
			        </c:otherwise>
			        </c:choose>
			    </td>
				<td valign="top">${training.category}</td>
				<td valign="top">${training.name}</td>
				<td valign="top">${training.description}</td>
				<td valign="top">
                    <c:choose>
                        <c:when test="${training.retired}">
                            <input type="checkbox" name="retired" checked disabled/>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" name="retired" disabled/>
                        </c:otherwise>
                    </c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</form>

</br>
</br>

<a href="trainingClass.form"><spring:message code="hr.training.classes.add"/></a>
<b class="boxHeader">
<spring:message code="hr.training.classes"/></b>
<form method="post" class="box">
    <div class="fixedSizeTable">
        <table id="TrainingClassesTable" width="100%">
            <tr>
                <th></th>
                <th> <spring:message code="hr.training.classes.start.date"/> </th>
                <th> <spring:message code="hr.training.classes.location"/> </th>
                <th> <spring:message code="hr.training.classes.organization"/> </th>
            </tr>
        </table>
	</div>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>