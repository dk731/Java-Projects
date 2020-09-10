<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
    <table>

        <th>
            <h1>Books List</h1>
            <p/>
            <table border="1">
                <tr>
                    <th style="border:3px solid"> Nr. </th>
                    <th style="border:3px solid"> Autor Name </th>
                    <th style="border:3px solid"> Book Name </th>
                    <th style="border:3px solid"> Pages </th>
                    <th style="border:3px solid"> Rating </th>
                </tr>
                <c:set var="num" value="1"/>
                <c:forEach var="book" items="${booksList}">
                        <tr>
                            <td>  <c:out value="${num}"/>  </td>
                            <td>  <c:out value="${book.getAutor()}"/>  </td>
                            <td>  <c:out value="${book.getName()}"/>  </td>
                            <td>  <c:out value="${book.getPages()}"/>  </td>
                            <td>  <c:out value="${book.getRating()}"/>  </td>
                        </tr>
                        <c:set var="num" value="${num+1}"/>
                    </c:forEach>
            </table>
            <form action="sortAction">
                Sort by :
                <input type="text" name="sortParam"/>
                <input type="submit"/>
            </form>
        </th>

        <th>

            <form action="addAction">
                <table>

                    <th> Add Book </th>
                    <tr>
                        <td> Books Autor </td>
                        <td> <input type="text" name="autorAdd"/> </td>
                    </tr>

                    <tr>
                        <td> Books Name </td>
                        <td> <input type="text" name="nameAdd"/> </td>
                    </tr>

                    <tr>
                        <td> Books Pages </td>
                        <td> <input type="text" name="pagesAdd"/> </td>
                    </tr>

                    <tr>
                        <td> Books Rating </td>
                        <td> <input type="text" name="ratingAdd"/> </td>
                    </tr>

                </table>

                <input type="submit"/>
            </form>



            <form action="delAction">
                <table>

                    <th> Delete Book </tr>
                    <tr>
                        <td> Books Name </td>
                        <td> <input type="text" name="delName"/> </td>
                    </tr>

                </table>

                <input type="submit"/>
            </form>
        </th>
    </table>


    <form action="goToAutors">
        <button name="name" value="value" type="submit"> Go To Autors Library</button>
    </form>


</body>
</html>