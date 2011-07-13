<ul id="menu">
	<li class="first">
		<a href="${pageContext.request.contextPath}/admin"><spring:message code="admin.title.short"/></a>
	</li>

		<li <c:if test='<%= request.getRequestURI().contains("job") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/admin/jobs.list">
				<spring:message code="Manage Jobs"/>
			</a>
		</li>
		
		<li <c:if test='<%= request.getRequestURI().contains("post") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/admin/posts.list">
				<spring:message code="Manage Posts"/>
			</a>
		</li>
	
		<li <c:if test='<%= request.getRequestURI().contains("staffList.jsp") || request.getRequestURI().contains("staff.jsp") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/admin/staff.list">
				<spring:message code="Manage Staff"/>
			</a>
		</li>
		
		<li <c:if test='<%= request.getRequestURI().contains("staffAttributeType") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/hr/admin/staffAttributeTypes.list">
				<spring:message code="Manage Staff Attribute Types"/>
			</a>
		</li>
</ul>