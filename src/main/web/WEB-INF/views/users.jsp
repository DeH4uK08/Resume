<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DeH4uK
  Date: 04.04.2019
  Time: 1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Users</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            color: #566787;
            font-family: 'Varela Round', sans-serif;
            font-size: 13px;
            background-image: url(data:image/png;base64,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);
        }

        .table-wrapper {
            background: #fff;
            padding: 20px 25px;
            margin: 30px 0;
            border-radius: 3px;
            box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
        }

        .table-title {
            background: #435d7d;
            color: #fff;
            padding: 16px 30px;
            margin: -20px -25px 10px;
            border-radius: 3px 3px 0 0;
        }

        .table-title h2 {
            margin: 5px 0 0;
            font-size: 24px;
        }

        .table-title .btn {
            color: #fff;
            float: right;
            font-size: 13px;
            border: none;
            min-width: 50px;
            border-radius: 2px;
            outline: none !important;
            margin-left: 10px;
        }

        .table-title .btn i {
            float: left;
            font-size: 21px;
            margin-right: 5px;
        }

        .table-title .btn span {
            float: left;
            margin-top: 2px;
        }

        table.table tr th, table.table tr td {
            border-color: #e9e9e9;
            padding: 15px 30px;
            vertical-align: middle;
        }

        table.table tr th:first-child {
            width: 140px;
        }

        table.table tr th:last-child {
            width: 260px;
        }

        table.table-striped tbody tr:nth-of-type(odd) {
            background-color: #fcfcfc;
        }

        table.table-striped.table-hover tbody tr:hover {
            background: #f5f5f5;
        }

        table.table th i {
            font-size: 13px;
            margin: 0 5px;
            cursor: pointer;
        }

        table.table td:last-child i {
            opacity: 0.9;
            font-size: 22px;
            margin: 0 10px;
        }

        table.table td a {
            font-weight: bold;
            color: #566787;
            display: inline-block;
            text-decoration: none;
            outline: none !important;
        }

        table.table td a:hover {
            color: #2196F3;
        }

        table.table td a.edit {
            color: #FFC107;
        }

        table.table td a.delete {
            color: #F44336;
        }

        table.table td i {
            font-size: 19px;
        }

        /* Modal styles */
        .modal .modal-dialog {
            max-width: 400px;
        }

        .modal .modal-header, .modal .modal-body, .modal .modal-footer {
            padding: 20px 30px;
        }

        .modal .modal-content {
            border-radius: 3px;
        }

        .modal .modal-footer {
            background: #ecf0f1;
            border-radius: 0 0 3px 3px;
        }

        .modal .modal-title {
            display: inline-block;
        }

        .modal .form-control {
            border-radius: 2px;
            box-shadow: none;
            border-color: #dddddd;
        }

        .modal textarea.form-control {
            resize: vertical;
        }

        .modal .btn {
            border-radius: 2px;
            min-width: 100px;
        }

        .modal form label {
            font-weight: normal;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            // Activate tooltip
            $('[data-toggle="tooltip"]').tooltip();
        });

        $(function () {
            $('[type="date"]').prop('max', function () {
                return new Date().toJSON().split('T')[0];
            });
        });

        $(document).on("click", ".open-DeleteModal", function () {
            var userId = $(this).data('id');
            $(".modal-content #userId").val(userId);
            $('#deleteEmployeeModal').modal('show');
        });
        $(document).on("click", "#dangerButton", function () {
            $('<form action="/deleteUser" method="POST"><input type="hidden" name="userId" value="' + $(".modal-content #userId").val() + '"></form>').appendTo($(document.body)).submit();
        });
    </script>
</head>
<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Users</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i>
                        <span>Add New User</span></a>
                    <a href="${pageContext.request.contextPath}/convertIntoYaml" class="btn btn-primary"
                       data-toggle="modal"><i class="material-icons">&#xE24D;</i> <span>Convert into YAML</span></a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Birthday</th>
                <th>Email</th>
                <th>Phone number</th>
                <th>Actions</th>
            </tr>
            </thead>

            <tbody>
            <%--@elvariable id="users" type="java.util.List"--%>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.getFirstName()}</td>
                    <td>${user.getLastName()}</td>
                    <td>${user.getBirthday()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getPhoneNumber()}</td>
                    <td>
                        <a href="<c:url value="/infoAboutUser?userId=${user.userId}" />" class="view"><i
                                class="material-icons" title="Show more" data-toggle="tooltip">&#xE417;</i></a>
                        <a href="<c:url value="/updateUser?userId=${user.userId}" />" class="edit"
                           data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" data-id="${user.userId}" class="open-DeleteModal delete"
                           data-toggle="modal"><i class="material-icons" data-toggle="tooltip"
                                                  title="Delete">&#xE872;</i></a>
                        <a href="<c:url value="/exportToPdf?userId=${user.userId}" />" class="export"><i
                                class="material-icons" data-toggle="tooltip" title="Export to PDF">&#xE24D;</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="addEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="<c:url value="/addUser" />" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Add User</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>First name</label>
                        <label for="firstName"></label><input type="text" pattern="^[A-Za-z0-9_]{3,20}$"
                                                              title="Use only characters of english alphabet and numbers (until 20 characters)"
                                                              class="form-control" id="firstName" name="firstName"
                                                              required>
                    </div>
                    <div class="form-group">
                        <label>Last name</label>
                        <label for="lastName"></label><input type="text" pattern="^[A-Za-z0-9_]{3,20}$"
                                                             title="Use only characters of english alphabet and numbers (until 20 characters)"
                                                             class="form-control" id="lastName" name="lastName"
                                                             required>
                    </div>
                    <div class="form-group">
                        <label>Birthday</label>
                        <label for="birthday"></label><input type="date" class="form-control" id="birthday"
                                                             name="birthday" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <label for="email"></label><input type="email" class="form-control" id="email" name="email"
                                                          required>
                    </div>
                    <div class="form-group">
                        <label>Phone number</label>
                        <label for="phoneNumber"></label><input type="tel" pattern="^\+[0-9]{12}" class="form-control"
                                                                id="phoneNumber"
                                                                name="phoneNumber"
                                                                title="format: +380977167761" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Delete User</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete this Record?</p>
                <p class="text-warning">
                    <small>This action cannot be undone.</small>
                </p>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="userId"/>
                <button class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button class="btn btn-danger" id="dangerButton">Delete</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
