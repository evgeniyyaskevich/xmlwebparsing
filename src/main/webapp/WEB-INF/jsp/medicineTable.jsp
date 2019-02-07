<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>

<html>
<table border="1" cellspacing="1">
    <thead align="center">
    <tr>
        <td rowspan="3">Name</td>
        <td rowspan="3">Group</td>
        <td rowspan="3">Analogs</td>
        <td rowspan="3">Producer</td>
        <td colspan="10">Versions</td>
    </tr>
    <tr>
        <td rowspan="2">Consistency</td>
        <td colspan="3">Pack</td>
        <td colspan="4">Certificate</td>
        <td colspan="2">Dosage</td>
    </tr>
    <tr>
        <td>type</td>
        <td>amount</td>
        <td>price($)</td>
        <td>id</td>
        <td>issueDate</td>
        <td>expirationDate</td>
        <td>regOrganization</td>
        <td>dose</td>
        <td>period</td>
    </tr>
    </thead>

    <tbody align="center">
    <c:forEach items="${medicines}" var="medicine">
        <tr>
            <td rowspan="${fn:length(medicine.versions)}"><c:out value="${medicine.name}"/></td>
            <td rowspan="${fn:length(medicine.versions)}"><c:out value="${medicine.group.name}"/></td>
            <td rowspan="${fn:length(medicine.versions)}">
                <c:forEach items="${medicine.analogs}" var="analog">
                    <c:out value="${analog.name}"/> <br>
                </c:forEach>
            </td>
            <td rowspan="${fn:length(medicine.versions)}"><c:out value="${medicine.producer}"/></td>
            <td><c:out value="${medicine.versions[0].consistency.name}"/></td>
            <td><c:out value="${medicine.versions[0].pack.packType.name}"/></td>
            <td><c:out value="${medicine.versions[0].pack.amount}"/></td>
            <td><c:out value="${medicine.versions[0].pack.price}"/></td>
            <td><c:out value="${medicine.versions[0].certificate.id}"/></td>
            <td><c:out value="${medicine.versions[0].certificate.issueDate}"/></td>
            <td><c:out value="${medicine.versions[0].certificate.expirationDate}"/></td>
            <td><c:out value="${medicine.versions[0].certificate.registeringOrganization}"/></td>
            <td><c:out value="${medicine.versions[0].dosage.dose}"/></td>
            <td><c:out value="${medicine.versions[0].dosage.periodicity}"/></td>
        </tr>
        <c:forEach items="${medicine.versions}"
                   begin="1"
                   end="${fn:length(medicine.versions) - 1}"
                   var="version">
            <%--<c:forEach items="${medicine.versions}"
                       varStatus="status"
                       var="version">
                <c:if test="${status.count >= 1}"><c:out value="<tr>"/></c:if> --%>
            <tr>
                <td><c:out value="${version.consistency.name}"/></td>
                <td><c:out value="${version.pack.packType.name}"/></td>
                <td><c:out value="${version.pack.amount}"/></td>
                <td><c:out value="${version.pack.price}"/></td>
                <td><c:out value="${version.certificate.id}"/></td>
                <td><c:out value="${version.certificate.issueDate}"/></td>
                <td><c:out value="${version.certificate.expirationDate}"/></td>
                <td><c:out value="${version.certificate.registeringOrganization}"/></td>
                <td><c:out value="${version.dosage.dose}"/></td>
                <td><c:out value="${version.dosage.periodicity}"/></td>
            </tr>
        </c:forEach>
    </c:forEach>
    </tbody>
</table>
</html>