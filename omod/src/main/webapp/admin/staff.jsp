<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Staff" otherwise="/login.htm" redirect="/module/hr/admin/staff.list"/>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp" %>

<openmrs:requireConfiguration propertyList="hr.setup" configurationPage="/module/hr/admin/setup.form?targetView=module/hr/admin/staff" />

<script type="text/javascript">
//Saves the last tab clicked on (aka "current" or "selected" tab)
var lastTab = new Array();
lastTab["name"]	 = null;
lastTab["address"]= null;

// Number of objects stored.  Needed for 'add new' purposes.
// starts at -1 due to the extra 'blank' data div in the *Boxes dib
var numObjs = new Array();
numObjs["name"]	= -1;
numObjs["address"]= -1;


function initializeChildren(obj, type) {
	if (obj.hasChildNodes()) {
		var child = obj.firstChild;
		while (child != null) {
			if (child.nodeName == "DIV") {
				child.style.display = "none";
				numObjs[type] = numObjs[type] + 1;
			}
			child = child.nextSibling;
		}
	}
}
function selectTab(tab, type) {
	if (tab != null && tab.id != null) {
		var data = document.getElementById(tab.id + "Data");
		if (data != null) {
			addClass(tab, 'selected');                              //set the tab as selected
			data.style.display = "";                                //show the data box
			if (lastTab[type] != null && lastTab[type] != tab) {    //if there was a last tab
				removeClass(lastTab[type], 'selected');             //set the last tab as unselected
				var lastData = document.getElementById(lastTab[type].id + "Data");
				if (lastData != null) 
					lastData.style.display = "none";                //hide last data tab
			}
			lastTab[type] = tab;             //new tab is now the last tab
			tab.blur();                      //get rid of the ugly dotted border the browser creates.
		}
	}
	return false;
}
function voidedBoxClicked(chk) {
	var parent = chk.parentNode;
	while (parent.id.indexOf("Data") == -1)
		parent = parent.parentNode;
	var tabId = parent.id.substring(0, parent.id.lastIndexOf("Data"));
	var tab = document.getElementById(tabId);
	if (chk.checked == true)
		addClass(tab, 'voided');
	else
		removeClass(tab, 'voided');
}
function addNew(type) {
	var newData = document.getElementById(type + "Data");
	if (newData != null) {
		var tabToClone = document.getElementById(type + "Tab");
		var tabClone = tabToClone.cloneNode(true);
		tabClone.id = type + numObjs[type];
		tabClone.style.display = "";
		var parent = tabToClone.parentNode;
		parent.insertBefore(tabClone, tabToClone);

		var dataClone = newData.cloneNode(true);
		dataClone.id = type + numObjs[type] + "Data";
		parent = newData.parentNode;
		parent.insertBefore(dataClone, newData);
		
		//find the active checkbox and add an onclick listener to it
		//and assign names and ids to the start and end input fields
		var inputs = dataClone.getElementsByTagName("input");
		for (var i in inputs) {
			var input = inputs[i];
			if (input && input.name == "activeCheckbox") {
				var addressIndex = numObjs[type];
				input.checked = true;
				input.onclick = function(){
					updateEndDate(this, 'addresses[' + addressIndex + '].endDate');
				};
			}
			else if (input && input.name == "startDate")
				input.id = 'addresses[' + numObjs[type] + '].startDate';
			else if (input && input.name == "endDate"){
				input.id = 'addresses[' + numObjs[type] + '].endDate';
				input.disabled = 'disabled';
			}
		}

		numObjs[type] = numObjs[type] + 1;
	}

	return selectTab(tabClone, type);
}

function removeTab(obj, type) {
	var data = obj.parentNode;
	var tabId = data.id.substring(0, data.id.lastIndexOf("Data"));
	var tab = document.getElementById(tabId);
	var tabToSelect = null;			
	
	if (data != null && tab != null) {
		var tabparent = tab.parentNode;

		var sibling = tab.nextSibling;
		while (tabToSelect == null && sibling != tabparent.lastChild) {
			if (sibling.id == null || sibling.className == "addNew" || sibling.style.display == "none")
				sibling = sibling.nextSibling;
			else
				tabToSelect = sibling;
		}
		if (tabToSelect == null || tabToSelect == tabparent.lastChild) {
			sibling = tab.previousSibling;
			while (tabToSelect == null && sibling != tabparent.firstChild) {
				if (sibling.id == null || sibling.className == "addNew")
					sibling = sibling.previousSibling;
				else
					tabToSelect = sibling;
			}
		}
		
		if (tabToSelect != null && tabToSelect.id != null) {
			//only remove this node if it is not the last
			tabparent.removeChild(tab);
			var dataparent = data.parentNode;
			dataparent.removeChild(data);
		}
		
	}
	
	return selectTab(tabToSelect, type);
}

function modifyTab(obj, value, child) {
	var parent = obj.parentNode;
	while (parent.nodeName != "DIV") {
		parent = parent.parentNode;
	}
	var tabId = parent.id.substring(0, parent.id.lastIndexOf("Data"));  //strip 'Data' from div id
	var tab = document.getElementById(tabId);
	tab.childNodes[child].innerHTML = value;
}
function removeBlankData() {
	var obj = document.getElementById("nameData");
	if (obj != null)
		obj.parentNode.removeChild(obj);
	obj = document.getElementById("addressData");
	if (obj != null)
		obj.parentNode.removeChild(obj);
}
function preferredBoxClick(obj) {
	var inputs = document.getElementsByTagName("input");
	if (obj.checked == true) {
		for (var i=0; i<inputs.length; i++) {
			var input = inputs[i];
			if (input.type == "checkbox")
				if (input.alt == obj.alt && input != obj)
					input.checked = false;
		}
	}
}
</script>
<style type="text/css">
	.tabBar {
		float: left;
		font-size: 11px;
		width: 158px;
		}
		.tabBar a {
			display: block;
			border-width: 2px;
			border-style: none solid none none;
			border-color: navy;
			background-color: WhiteSmoke;
			text-decoration: none;
			padding: 4px;
			}
			.tabBar a:hover {
				text-decoration: underline;
			}
		.tabBar .selected {
			border-width: 2px;
			border-style: solid none solid solid;
			border-color: navy;
		}
	.tabBoxes {
		margin-left: 156px;
		border: 2px solid navy;
		padding: 3px;
		min-height: 150px;
	}
	#pInformationBox .tabBoxes {
		margin-left: 1px;
	}
	.addNew, .removeTab {
		font-size: 10px;
		float: right;
		margin: 3px;
		cursor: pointer;
	}
	
</style>
<c:set var="errorsFromPreviousSubmit" value="false"/>
<spring:hasBindErrors name="person">
	<c:set var="errorsFromPreviousSubmit" value="true"/>
</spring:hasBindErrors>
<c:choose>
	<c:when test="${errorsFromPreviousSubmit == 'false' && empty param.personId && empty createNewPerson}">	
		<script type="text/javascript">
			function personSelectedCallback(relType, person) {
				if (person != null && person.personId != null) {
					document.getElementById('useExistingButton').disabled = false;
				} else {
					document.getElementById('useExistingButton').disabled = true;
				}
			}
		</script>
		<h2><spring:message code="Add Staff"/></h2>
		<spring:message code="Select person to be assigned as staff"/>
		<br/>
		<br/>
		<table>
			<tr valign="top">
				<td style="border-right: 1px lightgrey solid; padding-right: 5em">
					<h3><spring:message code="Create a new person"/></h3>
					<form method="get" action="staff.form">
						<input type="hidden" name="createNewPerson" value="true"/>
						<input type="submit" value="<spring:message code="general.next"/>"/>
					</form>
				</td>
				<td style="padding-left: 5em">
					<h3><spring:message code="Use a person already existing "/></h3>
					<form method="get" action="staff.form">
						<spring:message code="Select existing person"/> <openmrs_tag:personField formFieldName="personId" formFieldId="existingPersonId" callback="personSelectedCallback"/>
						<br/>
						<input id="useExistingButton" disabled="true" type="submit" value="<spring:message code="general.next"/>"/>
					</form>
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
	<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
	<openmrs:htmlInclude file="/scripts/validation.js" />

	<h2><spring:message code="Add Staff"/></h2>
  	<c:if test="${thisStaff.staffId==person.personId and not empty person.personId}">
	<div id="staffExists" class="retiredMessage">
		<div><spring:message code="Person already exists as staff"/></div>
	</div>
	</c:if>

	<c:if test="${person.dead}">
	<div id="staffFormDeceased" class="retiredMessage">
		<div><spring:message code="This person is deceased"/></div>
	</div>
	</c:if>

	<spring:hasBindErrors name="staff">
	<spring:message code="fix.error"/>
	<div class="error">
		<c:forEach items="${errors.allErrors}" var="error">
			<spring:message code="${error.code}" text="${error.code}"/><br/><!-- ${error} -->
		</c:forEach>
	</div>
	<br />
	</spring:hasBindErrors>
<form id="staffForm" method="post" action="staff.form" onSubmit="removeBlankData()">
	<c:if test="${person.personId != null}">
		<input type="hidden" name="personId" value="${person.personId}"/>
	</c:if>
	<c:if test="${createNewPerson}">
		<input type="hidden" name="createNewPerson" value="true"/>
	</c:if>
	<h3><spring:message code="Staff Names"/></h3>
		<spring:hasBindErrors name="person.names">
			<span class="error">${error.errorMessage}</span><br/>
		</spring:hasBindErrors>
		<div id="pNames">
			<div class="tabBar" id="pNameTabBar">
				<c:forEach var="name" items="${person.names}" varStatus="varStatus">
					<a href="javascript:return false;" onClick="return selectTab(this, 'name');" id="name${varStatus.index}" <c:if test="${name.voided}">class='voided'</c:if>><span>${name.givenName}</span>&nbsp;<span>${name.familyName}</span></a>
				</c:forEach>
				<a href="javascript:return false;" onClick="return selectTab(this, 'name');" id="nameTab" style="display: none"><span></span>&nbsp;<span></span></a>
				<input type="button" onClick="return addNew('name');" class="addNew" id="name" value='<spring:message code="Add New Name"/>'/>
			</div>
			<div class="tabBoxes" id="nameDataBoxes">
				<c:forEach var="name" items="${person.names}" varStatus="varStatus">
					<spring:nestedPath path="person.names[${varStatus.index}]">
						<div id="name${varStatus.index}Data" class="tabBox">
							<openmrs:portlet url="nameLayout" id="namePortlet" size="full" parameters="layoutShowTable=true|layoutShowExtended=true|layoutHideVoidOption=${(name.personNameId == null)}" />
							<!-- <input type="button" onClick="return removeTab(this, 'name');" class="removeTab" value='<spring:message code="Patient.removeThisName"/>'/><br/> --> <br/>
						</div>
					</spring:nestedPath>
				</c:forEach>
				<div id="nameData" class="tabBox">
					<spring:nestedPath path="emptyName">
						<openmrs:portlet url="nameLayout" id="namePortlet" size="full" parameters="layoutShowTable=true|layoutShowExtended=true|layoutHideVoidOption=true" />
						<!-- <input type="button" onClick="return removeTab(this, 'name');" class="removeTab" value='<spring:message code="Patient.removeThisName"/>'/><br/> --> <br/>
					</spring:nestedPath>
				</div>
			</div>
		</div>
	
	<br style="clear: both" />
	
	<h3><spring:message code="Staff Addresses"/></h3>
		<spring:hasBindErrors name="person.addresses">
			<span class="error">${error.errorMessage}</span><br/>
		</spring:hasBindErrors>
		<div id="pAddresses">
			<div class="tabBar" id="pAddressesTabBar">
				<c:forEach var="address" items="${person.addresses}" varStatus="varStatus">
					<a href="javascript:return false;" onClick="return selectTab(this, 'address');" id="address${varStatus.index}" <c:if test="${address.voided}">class='voided'</c:if>><span>${address.cityVillage}</span>&nbsp;</a>
				</c:forEach>
				<a href="javascript:return false;" onClick="return selectTab(this, 'address');" id="addressTab" style="display: none"><span></span>&nbsp;</a>
				<input type="button" onClick="return addNew('address');" class="addNew" id="address" value='<spring:message code="Add New Address"/>'/>			
			</div>
			<div class="tabBoxes" id="addressDataBoxes">
				<c:forEach var="address" items="${person.addresses}" varStatus="varStatus">
					<spring:nestedPath path="person.addresses[${varStatus.index}]">
						<div id="address${varStatus.index}Data" class="tabBox">
							<openmrs:portlet url="addressLayout" id="addressPortlet" size="full" parameters="layoutShowTable=true|layoutShowExtended=true|layoutHideVoidOption=${(address.personAddressId == null)}" />
							<%-- @ include file="include/editPersonAddress.jsp" --%>
							<!-- <input type="button" onClick="return removeTab(this, 'name');" class="removeTab" value='<spring:message code="Patient.removeThisAddress"/>'/><br/> --> <br/>
						</div>
					</spring:nestedPath>
				</c:forEach>
				<div id="addressData" class="tabBox">
					<spring:nestedPath path="emptyAddress">
						<openmrs:portlet url="addressLayout" id="addressPortlet" size="full" parameters="layoutShowTable=true|layoutShowExtended=true|layoutHideVoidOption=true" />
						<!-- <input type="button" onClick="return removeTab(this, 'name');" class="removeTab" value='<spring:message code="Patient.removeThisAddress"/>'/><br/> --> <br/>
					</spring:nestedPath>
				</div>
			</div>
		</div>
	
	<br/> 
	 <h3><spring:message code="Staff Information"/></h3>
		<div class="tabBox" id="pInformationBox">
			<div class="tabBoxes">
				<table>
					<spring:nestedPath path="person">
						<%@ include file="/WEB-INF/view/admin/person/include/editPersonInfo.jsp" %>
					</spring:nestedPath>
					 <tr>
					<td><spring:message code="Staff Status"/></td>
					<td>
					<select name="staffStatus" id="staffStatus">
					<c:forEach items="${StatusAnswers}" var="staffStatus">
						<option value="${staffStatus.answerConcept}" <c:if test='${staffStatus.answerConcept.id == thisStaff.staffStatus.id or staffStatus.answerConcept.id == modelStatus.id}'>selected="selected"</c:if>>${staffStatus.answerConcept.name.name}</option>
					</c:forEach>
	   				</select>
					</td>
					</tr>
						<c:forEach items="${attrTypes}" var="attrType">
							<c:set var="authorized" value="false" />
							<c:choose>
								<c:when test="${not empty attrType.editPrivilege}">
									<openmrs:hasPrivilege privilege="${attrType.editPrivilege.privilege}">
										<c:set var="authorized" value="true" />
									</openmrs:hasPrivilege>
								</c:when>
								<c:otherwise>
									<c:set var="authorized" value="true" />
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${attrType.retired == true}"></c:when>
								<c:otherwise>
									<tr>
										<td><spring:message	code="${attrType.name}"/>
										</td>
										<td>
										<c:choose>
											<c:when test="${authorized == true}">
												<openmrs:fieldGen type="${attrType.format}"	formFieldName="staffAttrType.${attrType.staffAttributeTypeId}" val="${attributeMap[attrType.name].hydratedObject}" parameters="optionHeader=[blank]|showAnswers=${attrType.foreignKey}|isNullable=false" />
														<%-- isNullable=false so booleans don't have 'unknown' radiobox --%>
												</c:when>
												<c:otherwise>
													${attributeMap[attrType.name]}
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</table>
			</div>
		</div>	

	<br /> 
	<spring:bind path="person.personId">
		<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
	</spring:bind>
	<input type="hidden" name="managerEdit" id="editingRole" value="${managerEdit}"/> 
	<input type="submit" name="action" id="saveButton" value='<spring:message code="Save Staff"/>' />
</form>
	<br/>
	<script>

	var array = new Array(2);
	array[0] = "name";
	array[1] = "address";
	for (var i = 0; i < array.length; i ++) {
		var id = array[i];
		var dataBoxes = document.getElementById(id + "DataBoxes");
		initializeChildren(dataBoxes, id);
		if (numObjs[id] < 1) {
			addNew(id);
		}
		selectTab(document.getElementById(id + "0"), id);
	}

</script>
	</c:otherwise>
	</c:choose>

<%@ include file="/WEB-INF/template/footer.jsp"%>