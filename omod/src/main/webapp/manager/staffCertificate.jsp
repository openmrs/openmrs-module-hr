<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Add Staff Certificate" otherwise="/login.htm" redirect="/module/hr/manager/findStaff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="staffLocalHeader.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/hr/js/jquery.imageZoom.js"/>

<script type="text/javascript">
    jQuery(document.body).imageZoom();
    $(document).ready(function(){
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
        var levels = document.staffCertForm.level;
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
       var sel = document.staffCertForm.hrCertificate;
       var certificateId = sel.options[sel.selectedIndex].value;
       var url="${pageContext.request.contextPath}" + "/moduleServlet/hr/LevelPopulator?certificateId=" + certificateId;
       reqObj.open("GET",url,true);
       reqObj.onreadystatechange=handleTableHttpResponse;
       reqObj.send(null);
    }
</script>

<c:if test="${expired}">
    <div class="retiredMessage">
        <div>
            <spring:message code="Certificate Expired on"/>
            <openmrs:formatDate date="${staffCertificate.certExpirationDate}" type="medium" />
        </div>
    </div>
</c:if>


<c:if test="${not empty staffCertificate.certCancel}">
    <div class="retiredMessage">
        <div>
            <spring:message code="This certificate is Cancelled on"/>
            <openmrs:formatDate date="${staffCertificate.cancelDate}" type="medium" />
            -
            ${staffCertificate.certCancel}
        </div>
    </div>
</c:if>

<spring:hasBindErrors name="staffCertificate">
<c:set var="errorExist" value="true"/>
	<spring:message code="fix.error"/>
	<c:forEach items="${errors.allErrors}" var="error">
	<c:if test="${error.code == 'endBeforeStart'}"><span class="error"><spring:message code="${error.defaultMessage}" text="Expiry date before certification date."/></span><br/></c:if>
</c:forEach>
	<br />
</spring:hasBindErrors>

<c:if test="{staffCertificate.staffCertId == 0}">
    <h2><spring:message code="Add New Staff Certificate" /></h2>
</c:if>

<form method="post" enctype="multipart/form-data" name="staffCertForm">
<fieldset>
<table width="100%">
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Certificate"/></th>
		<td>
				<spring:bind path="staffCertificate.hrCertificate">
				<select name="hrCertificate" id="${status.expression}" onChange="getLevels()">
				    <option value="">Select Certificate</option>
					<c:forEach items="${allCertificatesList}" var="certificate">
					    <c:if test="${not certificate.retired}">
						    <option value="${certificate.certificateId}" <c:if test='${certificate.certificateId == status.value}'>selected="selected"</c:if>>${certificate.certificate}</option>
						</c:if>
					</c:forEach>
				</select>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
     			</spring:bind>
		</td>
	</tr>
	<tr>
		<th width="10%" align="left" valign="top"><spring:message code="Level"/></th>
		<td>
     		<spring:bind path="staffCertificate.level">
     		    <select name="level" id="${status.expression}"/>
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            </spring:bind>
		</td>
	</tr>
    <tr>
        <th width="10%" align="left" valign="top"><spring:message code="Certification Date"/></th>
        <td>
            <spring:bind path="staffCertificate.currentCertDate">
                <input type="text" name="${status.expression}" size="10"
                                       value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
                                (<spring:message code="general.format"/>: <openmrs:datePattern />)
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                <spring:hasBindErrors name="staffCertificate">
                    <c:forEach items="${errors.allErrors}" var="error">
                        <c:if test="${error.code == 'currentCertDateInvalid'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
                    </c:forEach>
                </spring:hasBindErrors>
            </spring:bind>
        </td>
    </tr>

    <tr>
        <th width="10%" align="left" valign="top"><spring:message code="Expiration Date"/></th>
        <td>
            <spring:bind path="staffCertificate.certExpirationDate">
                <input type="text" name="${status.expression}" size="10"
                                       value="${status.value}" onClick="showCalendar(this)" id="${status.expression}" />
                                    (<spring:message code="general.format"/>: <openmrs:datePattern />)
                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                <spring:hasBindErrors name="staffCertificate">
                    <c:forEach items="${errors.allErrors}" var="error">
                        <c:if test="${error.code == 'certExpirationDateInvalid'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><br/></c:if>
                    </c:forEach>
                </spring:hasBindErrors>
            </spring:bind>
        </td>
    </tr>
    <c:if test="${staffCertificate.imagePresent}">
        <tr>
            <th width="10%" align="left" valign="top"><spring:message code="Image"/></th>
            <td>
                <a href="${pageContext.request.contextPath}/moduleServlet/hr/ImageServlet?staffCertId=${staffCertificate.staffCertId}&size=orig">
                <img alt="" id="certImage" src="${pageContext.request.contextPath}/moduleServlet/hr/ImageServlet?staffCertId=${staffCertificate.staffCertId}&size=display" />
                </a>
            </td>
        </tr>
    </c:if>

    <tr>
        <c:if test="${staffCertificate.imagePresent}">
            <th width="10%" align="left" valign="top"><spring:message code="Change Image"/></th>
        </c:if>
        <c:if test="${not staffCertificate.imagePresent}">
            <th width="10%" align="left" valign="top"><spring:message code="Upload Image"/></th>
        </c:if>
        <td>
            <input type="file" name="image" size="40" />
        </td>
    </tr>

</table>
<br />
<br />
</fieldset>
<c:if test="${empty staffCertificate.certCancel}">
    <input type="submit" value="<spring:message code="Save Staff Certificate"/>" name="submit"/>
</c:if>
<c:if test="${empty staffCertificate.certCancel && not expired && staffCertificate.staffCertId!=0}">
	<fieldset>
			<h4><spring:message code="Cancel Staff Certificate"/></h4>

			<b><spring:message code="general.reason"/></b>
			<input type="text" value="" size="40" name="certCancel" />
			<spring:hasBindErrors name="staffCertificate">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'certCancel'}"><span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span></c:if>
				</c:forEach>
			</spring:hasBindErrors>
			<br/>
			<input type="submit" value='<spring:message code="Cancel Staff Certificate"/>' name="cancelStaffCertificate"/>
		</fieldset>
</c:if>


</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>