<%-- 
    Document   : HousekeepingShowRooms
    Created on : 10-Dec-2017, 16:23:04
    Author     : qhf13exu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="HHcss.css">
        <script src ="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script type ="text/javascript"></script>
        <script src ="HHjavascript.js"></script>
        <title>Rooms</title>
    </head>
    <body>
        <h1>Checked out rooms</h1>
        <form method="POST" name="housekeepingUpdate" id="housekeepingUpdate" action="">
            <table>
                <tr>
                    <td colspan="2"><input type="button" name="refresh" value="Refresh" id="refresh" onclick="ShowRooms.java"></td>                   
                </tr>
                <tr>
                    <td><input type="text" name="roomNumber" id="roomNumber"</td>
                    <td><input type="submit" value="Change" name="submitHousekeeping" id="submitHousekeeping"</td>
                </tr>
            </table>
        </form>
        <table id="roomTable">
            <tr>
                <td>Room Number:</td>
            </tr>
            <script>
                    var rooms = ${roomNums};
                    showTables();
            </script>
        </table>
    </body>
</html>
