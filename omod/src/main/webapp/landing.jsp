<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="hrWeakClerkFunctions" style="float:left">
    <ul>
        <li><a href="manager/findStaff.list">Find Staff</a></li>
        <openmrs:hasPrivilege privilege="View Reports">
            <li><a href="admin/reportSelection.list">View Reports</a></li>
        </openmrs:hasPrivilege>
    </ul>
</div>

<div id="hrStrongClerkFunctions" style="float:left">
    <ul>
        <openmrs:hasPrivilege privilege="Find Staff">
                    <li><a href="admin/staff.list">Manage Staff</a></li>
                </openmrs:hasPrivilege>
        <openmrs:hasPrivilege privilege="Manage Job Titles">
            <li><a href="admin/jobTitles.list">Manage Job Titles</a></li>
        </openmrs:hasPrivilege>
        <openmrs:hasPrivilege privilege="Manage Posts">
            <li><a href="admin/posts.list">Manage Posts</a></li>
        </openmrs:hasPrivilege>
        <openmrs:hasPrivilege privilege="Manage Certificates">
                    <li><a href="admin/certificates.list">Manage Certificates</a></li>
        </openmrs:hasPrivilege>
        <openmrs:hasPrivilege privilege="Manage Competencies">
                    <li><a href="admin/competencies.list">Manage Competencies</a></li>
        </openmrs:hasPrivilege>
        <openmrs:hasPrivilege privilege="Manage Trainings">
                    <li><a href="admin/trainingManagement.list">Manage Trainings</a></li>
        </openmrs:hasPrivilege>

        <openmrs:hasPrivilege privilege="Manage Training Classes">
                    <li><a href="admin/trainingClasses.list">Manage Training Classes</a></li>
        </openmrs:hasPrivilege>

    </ul>
</div>

<div id="hrAdminFunctions" style="float:left">
    <ul>

        <openmrs:hasPrivilege privilege="Manage Staff Attribute Types">
            <li><a href="admin/staffAttributeTypes.list">Manage Staff Attribute Types</a></li>
        </openmrs:hasPrivilege>
    </ul>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>