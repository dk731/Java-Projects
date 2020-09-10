<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
    <table>
        <th>
            <tr>
                <h1>  Autors List  </h1>
                <p/>

                <table border="1">
                        <tr>
                            <th style="border:3px solid"> Nr. </th>
                            <th style="border:3px solid"> Autor Name </th>
                            <th style="border:3px solid"> Books Count </th>
                            <th style="border:3px solid"> AVG Rating </th>
                            <th style="border:3px solid"> SUM Rating </th>
                        </tr>
                        <c:set var="num" value="1"/>
                        <c:forEach var="book" items="${autorsList}">
                                <tr>
                                    <td>  <c:out value="${num}"/>  </td>
                                    <td>  <c:out value="${book.getName()}"/>  </td>
                                    <td>  <c:out value="${book.getBooksCount()}"/>  </td>
                                    <td>  <c:out value="${book.getAvgRating()}"/>  </td>
                                    <td>  <c:out value="${book.getSumRating()}"/>  </td>
                                </tr>
                                <c:set var="num" value="${num+1}"/>
                            </c:forEach>
                    </table>

            </tr>
        </th>
    </table>
    <p/>
    <p/>

    <form action="goToBooks">
        <button name="name" value="value" type="submit"> Go Back To Library</button>
    </form>


</body>
</html>