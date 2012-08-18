<?xml version="1.0" encoding="UTF-8" standalone="no"?>

<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:openmrs="urn:jsptld:/WEB-INF/taglibs/openmrs.tld"
	version="2.0">

	<%@ include file="localHeader.jsp" %>

	<openmrs:require privilege="Manage Global Properties" otherwise="/login.htm" redirect="/module/hr/admin/setup.form" />

	<openmrs:htmlInclude file="/moduleResources/hr/js/setup.js"/>
	<openmrs:htmlInclude file="/moduleResources/hr/styles/hr.css"/>

	<script type="text/javascript">
		jsslab.sampleDataInstalled = ${sampleDataInstalled};
	</script>


	<!-- ====================== -->
	<!-- Global Properties -->
	<!-- ====================== -->

	<h2><spring:message code="hr.setup.globalproperties.title" /></h2>

	<table>
		<c:forEach var="gp" items="${globalPropertiesObject}" varStatus="loop">
			<tr>
				<td>
					<label for="globalPropertyObjectSelect_${loop.index}">${gp.description}</label>
				</td><td>
					<input type="text" name="${gp.property}" id="globalPropertySelect_${loop.index}" value="${gp.propertyValue}" />
				</td><td>
					<input type="submit" class="globalPropertySubmit" id="globalPropertySubmit_${loop.index}" value="<spring:message code='hr.setup.globalproperties.save' />" />
				</td><td>
					<span id="globalPropertyResult_${loop.index}"></span>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />

	<!-- ====================== -->
	<!--   HR SETUP SECTION    -->
	<!-- ====================== -->

	<h2><spring:message code="hr.setup.databasetables.title" /></h2>
	<div>

		<c:choose>
			<c:when test="${sampleDataInstalled}">
				<span><spring:message code="hr.setup.database.sampledata.installed" /></span>
				<input type="submit" value="Install Now" disabled="disabled" />
			</c:when>
			<c:otherwise>
				<span><spring:message code="hr.setup.database.sampledata.notinstalled" /></span>
				<input id="installSampleData" type="submit" value="Install Now" />
				<span id="installationResult"></span>
			</c:otherwise>
		</c:choose>
	</div>

	<div>
		<input id="finishSetup" type="submit" value="Finish Setup" />
	</div>

</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>