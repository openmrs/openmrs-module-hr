<ul id="menu">
	<li class="first">
		<a href="${pageContext.request.contextPath}/admin"><spring:message code="admin.title.short"/></a>
	</li>
	    <li>
	        <a href="${pageContext.request.contextPath}/module/hr/landing.list"><spring:message code="HR Home"/></a>
	    </li>
		<openmrs:hasPrivilege privilege="Manage Job Titles">
		<li <c:if test='<%= request.getRequestURI().contains("job") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/admin/jobTitles.list">
				<spring:message code="Manage Job Titles"/>
			</a>
		</li>
		</openmrs:hasPrivilege>
		<openmrs:hasPrivilege privilege="Manage Posts">
		<li <c:if test='<%= request.getRequestURI().contains("post") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/admin/posts.list">
				<spring:message code="Manage Posts"/>
			</a>
		</li>
		</openmrs:hasPrivilege>
		<openmrs:hasPrivilege privilege="Manage Staff">
		<li <c:if test='<%= request.getRequestURI().contains("staffList.jsp") || request.getRequestURI().contains("staff.jsp") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/admin/staff.list">
				<spring:message code="Manage Staff"/>
			</a>
		</li>
		</openmrs:hasPrivilege>


		<openmrs:hasPrivilege privilege="Manage Certificates">
        		<li <c:if test='<%= request.getRequestURI().contains("certificates") %>'>class="active"</c:if>>
        			<a href="${pageContext.request.contextPath}/module/hr/admin/certificates.list">
        				<spring:message code="Manage Certificates"/>
        			</a>
        		</li>
        </openmrs:hasPrivilege>

        <openmrs:hasPrivilege privilege="Manage Competencies">
                <li <c:if test='<%= request.getRequestURI().contains("competencies") %>'>class="active"</c:if>>
                    <a href="${pageContext.request.contextPath}/module/hr/admin/competencies.list">
                        <spring:message code="Manage Competencies"/>
                    </a>
                </li>
        </openmrs:hasPrivilege>
        <openmrs:hasPrivilege privilege="Manage Trainings">
                <li <c:if test='<%= request.getRequestURI().contains("trainings") %>'>class="active"</c:if>>
                    <a href="${pageContext.request.contextPath}/module/hr/admin/trainingManagement.list">
                        <spring:message code="Manage Trainings"/>
                    </a>
                </li>
        </openmrs:hasPrivilege>

        <openmrs:hasPrivilege privilege="Manage Training Classes">
                <li <c:if test='<%= request.getRequestURI().contains("trainingClass") %>'>class="active"</c:if>>
                    <a href="${pageContext.request.contextPath}/module/hr/admin/trainingClasses.list">
                        <spring:message code="Manage Training Classes"/>
                    </a>
                </li>
        </openmrs:hasPrivilege>

		<openmrs:hasPrivilege privilege="Run Reports">
		<li <c:if test='<%= request.getRequestURI().contains("eport") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/admin/reportSelection.list">
				<spring:message code="Reports"/>
			</a>
		</li>
		</openmrs:hasPrivilege>
</ul>
