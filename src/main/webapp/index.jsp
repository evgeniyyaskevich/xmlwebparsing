<%@ page contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <title>XML/XSD & WEB-PARSING</title>
</head>

<body style="background: deepskyblue;">
<div align="center">
    <br> <br>
    <h1>XML/XSD & WEB-PARSING</h1> <br> <br>

    <form action="parsing"
          method="POST"
          enctype="multipart/form-data">

        <label>
            <select name="parser">
                <option value="SAX" selected>SAX</option>
                <option value="DOM">DOM</option>
                <option value="StAX">StAX</option>
            </select>
        </label> <br> <br>

        <input type="file" name="inputFile"> <br> <br>
        <input type="submit" value="Parse!">
    </form>
</div>
</body>
</html>