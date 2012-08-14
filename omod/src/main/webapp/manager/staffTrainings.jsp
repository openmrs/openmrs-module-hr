<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Staff Trainings" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/hr/styles/table.css"/>


<%@ include file="staffLocalHeader.jsp" %>

<script type="text/javascript">
    window.onload=function(){
    var table = document.getElementById('TrainingsTable');
    var trs = table.getElementsByTagName("tr");
        for (i=2; i < trs.length ; i++) {
            if (i % 2 == 1)
                trs[i].className = "oddRow";
            else
                trs[i].className = "evenRow";
        }
    }
    $j(document).ready(function(){
        getTrainingsForCategory(selectBox);
    });

    function getTrainingClass(checkbox){
        var val = jQuery(checkbox).is(':checked');
        $j.getJSON("${pageContext.request.contextPath}" + "/ws/rest/v1/hr/training/"+$j(checkbox).attr('value')+"?v=full" , function(trainingJson) {
            for(i in trainingJson.hrTrainingClasses)
            {
                var trainingClass = trainingJson.hrTrainingClasses[i];
                var table = jQuery("#createTrainPersonTable");
                var row = jQuery('<tr id="row-'+trainingClass.uuid+'"></tr>');
                var startDate = jQuery('<td valign="top">'+trainingClass.startDate+'</td>');
                var location = jQuery('<td valign="top">'+trainingClass.location+'</td>');
                var organization = jQuery('<td valign="top">'+trainingClass.organization+'</td>');
                var ceunits = jQuery('<td valign="top">'+trainingClass.ceunits+'</td>');
                var days = jQuery('<td valign="top">'+trainingClass.duration+'</td>');
                var totalCost = Number(trainingClass.costRegister)+Number(trainingClass.costTravel)+Number(trainingClass.costRegister)+(Number(trainingClass.duration)*Number(trainingClass.costPerdiem));
                var cost = jQuery('<td valign="top">'+totalCost+'</td>');
                var a = "assignPersonToClass('"+trainingClass.uuid+"');";
                var b = "deleteRow('row-"+trainingClass.uuid+"');";
                var links = jQuery('<td valign="top"></td>');
                var selectLink = jQuery('<input type="button" value="Select" onClick="'+a+'"/>');
                var discardLink = jQuery('<input type="button" value="Discard" onClick="'+b+'"/>');
                links.append(selectLink);
                links.append(discardLink);
                row.append(startDate);
                row.append(location);
                row.append(organization);
                row.append(ceunits);
                row.append(days);
                row.append(cost);
                row.append(links);
                if(val)
                    $j('#createTrainPersonTable tr:last').after(row);
                else
                    $j('#row-'+trainingClass.uuid).remove();
            }
         });
    }

    function assignPersonToClass(trainingClassID){
        var reasonVar = prompt("Enter reason","");
        var staffID = document.getElementById("staffID").value;
        $j.getJSON("${pageContext.request.contextPath}" + "/ws/rest/v1/hr?getPersonForStaffId="+staffID , function(personValue) {

          var data = {person:personValue.person ,hrTrainingClass :trainingClassID,reason : reasonVar };
            jQuery.ajax({
                "url" : "${pageContext.request.contextPath}" + "/ws/rest/v1/hr/trainPerson",
                "type" : "POST",
                "contentType" : "application/json",
                "data" : JSON.stringify(data),
                "success" : function(data) {
                    var row = $j("<tr></tr>");
                    var date = $j('<td>'+data.dateCreated+'</td>');
                    var location = $j('<td>'+data.hrTrainingClass.location+'</td>');
                    var organization = $j('<td>'+data.hrTrainingClass.hrTraining.name+'</td>');
                    var ceunits = $j('<td>'+data.hrTrainingClass.ceunits+'</td>');
                    var completed;
                    if(data.completed)
                        completed = $j('<input type="checkbox" disabled checked/>');
                    else
                        completed = $j('<input type="checkbox" name="'+data.uuid+'" onClick="completeTraining(this)"/>');
                    row.append(date);
                    row.append(location);
                    row.append(organization);
                    row.append(ceunits);
                    row.append(completed);
                    $j('#TrainingsTable tr:last').after(row);
                }
            });
        });


    }

    function completeTraining(checkbox){
        var trainingPersonUUID = $j(checkbox).attr('name');
        var data={completed:true};
        jQuery.ajax({
                "url" : "${pageContext.request.contextPath}" + "/ws/rest/v1/hr/trainPerson/"+trainingPersonUUID,
                "type" : "POST",
                "contentType" : "application/json",
                "data" : JSON.stringify(data),
                "success" : function(data) {
                    $j(checkbox).attr("disabled",true);
                }
            });

    }

    function deleteRow(rowID){
        $j('#'+rowID).remove();
    }

    function getTrainingsForCategory(selectBox){
        $j.getJSON("${pageContext.request.contextPath}" + "/ws/rest/v1/hr/training?category="+$j(selectBox).attr('value') , function(trainingsJson) {
            for(i in trainingsJson.results)
                {
                    var trainingJson = trainingsJson.results[i];
                    var table = jQuery("#TrainingCoursesTable");
                    var row = jQuery('<tr class="cat-row-'+trainingJson.category+'"></tr>');
                    var name;
                    var checkbox;
                    checkbox = jQuery('<td valign="top"><input type="checkbox" name="training-'+trainingJson.uuid+'" class="select-training" value="'+trainingJson.uuid+'" onClick="getTrainingClass(this);"/></td>"');
                    var name = jQuery('<td valign="top">'+trainingJson.name+'</td>');
                    row.append(checkbox);
                    row.append(name);
                    $j('#TrainingCoursesTable tr:last').after(row);
                }
        });
    }



</script>
<style type="text/css">

#TrainingsTable tbody tr:hover {
	cursor:pointer;
	background-color: #F0E68C;
}

</style>


<br/>
<b class="boxHeader">
<spring:message code="hr.training.history"/>
</b>
<form method="post" class="box">
    <div class="fixedSizeTable">
        <table id="TrainingsTable" width="100%">
            <thead>
            <tr>
                <th> <spring:message code="hr.training.date" /> </th>
                <th> <spring:message code="hr.training.classes.location" /> </th>
                <th> <spring:message code="hr.trainings.name" /> </th>
                <th> <spring:message code="hr.training.classes.ce.units" /> </th>
                <th> <spring:message code="hr.training.classes.completed" /></th>
            </thead>
            <tbody>
            <c:forEach var="staffTraining" items="${staffTrainings}" varStatus="rowStatus">
                <tr>
                    <td><openmrs:formatDate date="${staffTraining.dateCreated}" type="medium" /></td>
                    <td>${staffTraining.hrTrainingClass.location}</td>
                    <td>${staffTraining.hrTrainingClass.hrTraining.name}</td>
                    <td>${staffTraining.hrTrainingClass.ceunits}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not staffTraining.completed}">
                                <input type="checkbox" name="${staffTraining.uuid}" onClick="completeTraining(this)" />
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" disabled checked/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>

</br></br>

<h2>Course Selection</h2>

</br>

<div name="left">
    Select Training Category :
    <select name="hrTrainingCategory" id="trainingCategory" width="100%" onChange="getTrainingsForCategory(this)">
        <c:forEach var="category" items="${trainingCategories}" varStatus="rowStatus">
            <option value="${category}">${category}</option>
        </c:forEach>
    </select>
    <b class="boxHeader">
    <spring:message code="hr.training.classes"/></b>
    <form method="post" class="box">
        <div class="fixedSizeTable">
            <table id="TrainingCoursesTable" width="100%">
                <tr>
                    <th></th>
                    <th> <spring:message code="hr.trainings.name"/> </th>
                </tr>
            </table>
    	</div>
    </form>
</div>

</br>
</br>

<form method="post" class="box">
    <div class="fixedSizeTable">
        <table id="createTrainPersonTable" width="100%">
            <tr>
                <th><spring:message code="hr.training.date"/></th>
                <th><spring:message code="hr.training.classes.location"/></th>
                <th><spring:message code="hr.training.classes.organization"/></th>
                <th><spring:message code="hr.training.classes.ce.units"/></th>
                <th><spring:message code="hr.training.classes.duration"/></th>
                <th><spring:message code="hr.training.classes.cost"/></th>
                <th></th>
            </tr>
        </table>
    </div>
</form>


<%@ include file="/WEB-INF/template/footer.jsp"%>