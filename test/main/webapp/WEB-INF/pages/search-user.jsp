<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="GET" modelAttribute="userSearchCriteria">
     <table>
        <tr>
            <td><form:input path="name" /></td>
        </tr>
     </table>
    <button id="search" value="Search">Search</button>
</form:form>

<c:if test="${not empty userList}">
    <table border="1px" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th width="10%">id</th><th width="15%">name</th><th width="10%">age</th>
                <th width="10%">admin</th><th width="10%">date</th><th width="10%">actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.isAdmin}</td>
                <td>${user.createdDate}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/user/edit/${user.id}.html">Edit</a><br/>
                    <a href="${pageContext.request.contextPath}/user/delete/${user.id}.html">Delete</a><br/>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>