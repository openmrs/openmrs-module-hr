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
		<openmrs:hasPrivilege privilege="Manage Staff Attribute Types">
		<li <c:if test='<%= request.getRequestURI().contains("staffAttributeType") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/admin/staffAttributeTypes.list">
				<spring:message code="Manage Staff Attribute Types"/>
			</a>
		</li>
		</openmrs:hasPrivilege>

		<openmrs:hasPrivilege privilege="Manage Certificates">
        		<li <c:if test='<%= request.getRequestURI().contains("certificate") %>'>class="active"</c:if>>
        			<a href="${pageContext.request.contextPath}/module/hr/admin/certificates.list">
        				<spring:message code="Manage Certificates"/>
        			</a>
        		</li>
        </openmrs:hasPrivilege>

		<openmrs:hasPrivilege privilege="View Reports">
		<li <c:if test='<%= request.getRequestURI().contains("eport") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/admin/reportSelection.list">
				<spring:message code="Reports"/>
			</a>
		</li>
		</openmrs:hasPrivilege>
</ul>
