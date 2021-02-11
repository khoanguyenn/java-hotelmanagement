<%@ page import="java.util.List" %>
<%@ page import="com.model.Room" %>
<jsp:useBean id="roomList" scope="request" type="java.util.List<com.model.Room>"></jsp:useBean>
<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
<script src='https://kit.fontawesome.com/99359d7515.js' crossorigin='anonymous'></script>
<head>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
            integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
            crossorigin="anonymous"></script>
    <title>Room information</title>
</head>
<body>
    <div align="center">
        <div class="row" style="width: 500px">
            <div class="col-sm-4"><h2>Room list</h2></div>
            <div class="col-sm-8"><button class="btn btn-success" data-toggle="modal" data-target="#roomModal"><span><i class="fa fa-new"></i></span>Add new room</button></div>
        </div>
        <div style="width: 500px">
            <table class="table table-hover table-bordered">
                <tr align="center" valign="middle">
                    <th>Number</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${roomList}" var="room">
                    <tr align="center" valign="middle">
                        <td><c:out value="${room.number}"/></td>
                        <td><c:out value="${room.type}"/></td>
                        <td><c:out value="${room.status}"/></td>
                        <td>
                            <button
                                    class="btn btn-success"
                                    data-toggle="modal"
                                    data-target="#roomModal"
                                    data-room-number="${room.number}"
                                    data-room-type="${room.type}"
                                    data-room-status="${room.status}"><span><i class="far fa-edit"></i></span>Edit</button>
                            <button class="btn btn-danger"><span><i class="fas fa-trash"></i></span>Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade"
         id="roomModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">
                        Room information</h3>
                    <button type="button"
                            class="close"
                            data-dismiss="modal"
                            aria-label="Close">
                                <span aria-hidden="true">
                                  ×</span>
                    </button>
                </div>
                <form action="/hotelmanagement/rooms" method="post">
                <div class="modal-body">
                        <div class="form-group row mb-2">
                            <label for="roomNumber" class="col-sm-4 col-form-label">Room number</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="roomNumber" placeholder="${room.number}" name="number">
                            </div>
                        </div>
                        <div class="form-group row mb-2">
                            <label for="roomType" class="col-sm-4 col-form-label">Room type</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="roomType" name="type">
                                    <option>Twin Room</option>
                                    <option>Triple Room</option>
                                    <option>Queen Room</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row mb-2">
                            <label for="roomStatus" class="col-sm-4 col-form-label">Room status</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="roomStatus" name="status">
                                    <option>Available</option>
                                    <option>Staying</option>
                                    <option>Reserved</option>
                                </select>
                            </div>
                        </div>
                    </div>
                <div class="modal-footer">
                    <button type="button"
                            class="btn btn-secondary"
                            data-dismiss="modal">
                        Close</button>
                    <button type="submit"
                            class="btn btn-primary">
                        Save changes</button>
                </div>
                </form>
            </div>
        </div>
    </div>
    </div>
    <script>
        var roomModal =
            document.getElementById("roomModal");

        roomModal.addEventListener(
            "show.bs.modal", function (event) {
                // Button that triggered the modal
                var button = event.relatedTarget;
                // Extract info from data-* attributes
                var roomNumber = button.getAttribute("data-room-number");
                var roomType = button.getAttribute("data-room-type");
                var roomStatus = button.getAttribute("data-room-status");
                // Update the modal's content.

                var numberInput = roomModal.querySelector(".modal-body input");
                var selectInput =
                    roomModal.querySelectorAll(".modal-body select");

                numberInput.value = roomNumber;
                selectInput[0].value = roomType;
                selectInput[1].value = roomStatus;
            });
    </script>
</body>
</html>
