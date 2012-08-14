<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Evaluations" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />

<script type="text/javascript">
    jQuery(document).ready(function(){
    		getLevels();
    	});
    function getHTTPObject() {
          var xmlhttp;
                try {
                    if(window.ActiveXObject)
                    {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    else if(window.XMLHttpRequest)
                    {
                    xmlhttp=new XMLHttpRequest();
                    }
                } catch (E) {
                    xmlhttp = false;
                }
         return xmlhttp;
    }
    function handleTableHttpResponse() {
        results=reqObj.responseText.split("^");
        var levels = document.evaluationForm.level;
        var i;
        var length =  levels.options.length;
        for(i = 0 ; i< length ; i++)
            levels.options.remove(i);
        for(i in results)
        {

            var opt = document.createElement("option");
            opt.text=results[i];
            opt.value=results[i]
            levels.options.add(opt);
        }
    }

    var reqObj=getHTTPObject();

    function getLevels(){
       var sel = document.evaluationForm.hrCompetency;
       var competencyId = sel.options[sel.selectedIndex].value;
       var url="${pageContext.request.contextPath}" + "/moduleServlet/hr/LevelPopulator?competencyId=" + competencyId;
       reqObj.open("GET",url,true);
       reqObj.onreadystatechange=handleTableHttpResponse;
       reqObj.send(null);
    }
</script>


<spring:hasBindErrors name="evaluation">
<c:set var="errorExist" value="true"/>
	<spring:message code="fix.error"/>
	<br />
</spring:hasBindErrors>

<c:if test="{evaluation.evaluationId == 0}">
    <h2><spring:message code="hr.evaluation.add" /></h2>
</c:if>

<form method="post" name="evaluationForm">
<fieldset>
<table width="100%" >
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="hr.evaluation.competency"/></th>
		<td>
				<spring:bind path="evaluation.hrCompetency">
				<select name="hrCompetency" id="${status.expression}" onChange="getLevels()">
				    <option value="">Select Competency</option>
					<c:forEach items="${allCompetenciesList}" var="competency">
                        <option value="${competency.competencyId}" <c:if test='${competency.competencyId == status.value}'>selected="selected"</c:if>>${competency.name}</option>
					</c:forEach>
				</select>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="hr.evaluation.level"/></th>
		<td>
     		<spring:bind path="evaluation.level">
     		    <select name="level" id="${status.expression}"/>
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
		</td>
	</tr>
    <tr>
        <th width="10%" align="left" valign="top"><spring:message code="hr.evaluation.evaluation.date"/></th>
        <td>
            <spring:bind path="evaluation.evaluationDate">
                <input type="text" name="${status.expression}" size="10"
                                       value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
                                (<spring:message code="general.format"/>: <openmrs:datePattern />)
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                <spring:hasBindErrors name="evaluation">
                    <c:forEach items="${errors.allErrors}" var="error">
                        <c:if test="${error.code == 'evaluationDateInvalid'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
                    </c:forEach>
                </spring:hasBindErrors>
            </spring:bind>
        </td>
    </tr>
    <tr>
        <th width="10%" align="left" valign="top"><spring:message code="hr.evaluation.evaluation.evaluator"/></th>
            <td>
                <spring:bind path="evaluation.evaluator">
                   <openmrs_tag:personField formFieldName="evaluator" formFieldId="evaluator" initialValue="${status.value}"/>
                   <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
            </td>
    </tr>

    <tr>
        <th width="10%" align="left" valign="top"><spring:message code="hr.evaluation.evaluation.staff.comments"/></th>
        <td>
                <spring:bind path="evaluation.staffComments">
                <textarea name="${status.expression}" value="${status.value}" rows="5" cols="50">${evaluation.staffComments}</textarea>
                </spring:bind>
        </td>
    </tr>
    <tr>
        <th width="10%" align="left" valign="top"><spring:message code="hr.evaluation.evaluation.evaluator.comments"/></th>
        <td>
                <spring:bind path="evaluation.evaluatorComments">
                <textarea name="${status.expression}" value="${status.value}" rows="5" cols="50">${evaluation.staffComments}</textarea>
                </spring:bind>
        </td>
    </tr>
    </table>
<br />
<br />
</fieldset>
<input type="submit" value="<spring:message code="hr.action.evaluation.save"/>" name="action"/>
<c:if test="${evaluation.evaluationId != 0}">
    <input type="submit" value="<spring:message code="hr.action.evaluation.delete"/>" name="action" onclick="return confirm('<spring:message code="Are you sure you want to Delete this evaluation?"/>')"/>
</c:if>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>