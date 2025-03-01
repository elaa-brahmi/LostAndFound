<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2025-02-26
  Time: 8:37 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("userId")==null && session.getAttribute("role")!="user"){
        response.sendRedirect("login.jsp");

    }
%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Title</title>
    <style>

        .custum-file-upload {
            height: 100px;
            width: 300px;
            display: flex;
            flex-direction: column;
            align-items: space-between;
            gap: 20px;
            cursor: pointer;
            align-items: center;
            justify-content: center;
            border: 2px dashed #cacaca;
            background-color: rgba(255, 255, 255, 1);
            padding: 1.5rem;
            border-radius: 10px;
            box-shadow: 0px 48px 35px -48px rgba(0,0,0,0.1);
        }


        .infos input[type="date"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: none;
            margin-bottom: 20px;
            background-color: white;
            color: black;
        }


        .custum-file-upload .icon {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .custum-file-upload .icon svg {
            height: 50px;
            fill: rgba(75, 85, 99, 1);
        }

        .custum-file-upload .text {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .custum-file-upload .text span {
            font-weight: 200;
            color: rgba(75, 85, 99, 1);
        }

        .custum-file-upload input {
            display: none;
        }
        .form {
            display: flex;
            flex-direction: column;
            gap: 10px;
            width:100%;
            max-width: 350px;
            background-color: #fff;
            padding: 20px;
            border-radius: 20px;

        }

        .title {
            font-size: 28px;
            color: royalblue;
            font-weight: 600;
            letter-spacing: -1px;
            position: relative;
            display: flex;
            align-items: center;
            padding-left: 30px;
        }

        .title::before,.title::after {
            position: absolute;
            content: "";
            height: 16px;
            width: 16px;
            border-radius: 50%;
            left: 0px;
            background-color: royalblue;
        }

        .title::before {
            width: 18px;
            height: 18px;
            background-color: royalblue;
        }

        .title::after {
            width: 18px;
            height: 18px;
            animation: pulse 1s linear infinite;
        }
        .signin a {
            color: royalblue;
        }
        .signin a:hover {
            text-decoration: underline royalblue;
        }

        .flex {
            display: flex;
            width: 100%;
            gap: 6px;
        }

        .form label {
            position: relative;
        }

        .form label .input {
            width: 100%;
            padding: 10px 10px 20px 10px;
            outline: 0;
            border: 1px solid rgba(105, 105, 105, 0.397);
            border-radius: 10px;
        }

        .form label .input + span {
            position: absolute;
            left: 10px;
            top: 15px;
            color: grey;
            font-size: 0.9em;
            cursor: text;
            transition: 0.3s ease;
        }

        .form label .input:placeholder-shown + span {
            top: 15px;
            font-size: 0.9em;
        }

        .form label .input:focus + span,.form label .input:valid + span {
            top: 30px;
            font-size: 0.7em;
            font-weight: 600;
        }

        .form label .input:valid + span {
            color: green;
        }

        .submit {
            border: none;
            outline: none;
            background-color: royalblue;
            padding: 10px;
            border-radius: 10px;
            color: #fff;
            font-size: 16px;
            transform: .3s ease;
        }

        .submit:hover {
            background-color: rgb(56, 90, 194);
        }

        @keyframes pulse {
            from {
                transform: scale(0.9);
                opacity: 1;
            }

            to {
                transform: scale(1.8);
                opacity: 0;
            }
        }

        .filter-switch {
            border: 2px solid #ffc000;
            border-radius: 30px;
            position: relative;
            display: flex;
            align-items: center;
            height: 50px;
            width: 300px;
            overflow: hidden;
        }
        .filter-switch input {
            display: none;
        }
        .filter-switch label {
            flex: 1;
            text-align: center;
            cursor: pointer;
            border: none;
            border-radius: 30px;
            position: relative;
            overflow: hidden;
            z-index: 1;
            transition: all 0.5s;
            font-weight: 500;
            font-size: 18px;
        }
        .filter-switch .background {
            position: absolute;
            width: 49%;
            height: 38px;
            background-color: #ffc000;
            top: 4px;
            left: 4px;
            border-radius: 30px;
            transition: left 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
        }
        #option2:checked ~ .background {
            left: 50%;
        }
        #option1:checked + label[for="option1"] {
            color: #212121;
            font-weight: bold;
        }
        #option2:checked + label[for="option2"] {
            color: #212121;
            font-weight: bold;
        }
        #option1:not(:checked) + label[for="option1"],
        #option2:not(:checked) + label[for="option2"] {
            color: #7d7d7d;
        }
        @media (max-width: 576px) { /* Adjust for small screens */
            .form {
                max-width: 90vw; /* Makes form fit smaller screens */
                padding: 15px;
            }
        }



    </style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100"><!--Viewport Height-->
<!-- From Uiverse.io by Yaya12085 -->
<form class="form mx-auto" action="addItem" method="post">
    <p class="title text-center">add item </p>
    <div class="flex">
        <label>
            <input required="" placeholder="" type="text" name="name" class="input">
            <span>name</span>
        </label>

        <label>
            <input required="" placeholder="" type="text" name="description" class="input">
            <span>description</span>
        </label>
    </div>

    <label>


        <label>

        <select style="border-radius:1px solid gray;" class="input" id="category" name="category" required >
            <option value="Electronics">Electronics</option>
            <option value="Personal Accessories">Personal Accessories</option>
            <option value="Identification & Documents">Identification & Documents</option>
            <option value="Clothing & Wearables">Clothing & Wearables</option>
            <option value="Keys & Security Items">Keys & Security Items</option>
            <option value="Travel & Transport Items">Travel & Transport Items</option>
            <option value="Household & Miscellaneous Items">Household & Miscellaneous Items</option></select>

        </label>




    </label>

    <label>
        <input required="" placeholder="" name="location" type="text" class="input" required="">
        <span>location</span>
    </label>
    <label>

        <label class="custum-file-upload" for="file">
            <div class="icon">
                <svg xmlns="http://www.w3.org/2000/svg" fill="" viewBox="0 0 24 24"><g stroke-width="0" id="SVGRepo_bgCarrier"></g><g stroke-linejoin="round" stroke-linecap="round" id="SVGRepo_tracerCarrier"></g><g id="SVGRepo_iconCarrier"> <path fill="" d="M10 1C9.73478 1 9.48043 1.10536 9.29289 1.29289L3.29289 7.29289C3.10536 7.48043 3 7.73478 3 8V20C3 21.6569 4.34315 23 6 23H7C7.55228 23 8 22.5523 8 22C8 21.4477 7.55228 21 7 21H6C5.44772 21 5 20.5523 5 20V9H10C10.5523 9 11 8.55228 11 8V3H18C18.5523 3 19 3.44772 19 4V9C19 9.55228 19.4477 10 20 10C20.5523 10 21 9.55228 21 9V4C21 2.34315 19.6569 1 18 1H10ZM9 7H6.41421L9 4.41421V7ZM14 15.5C14 14.1193 15.1193 13 16.5 13C17.8807 13 19 14.1193 19 15.5V16V17H20C21.1046 17 22 17.8954 22 19C22 20.1046 21.1046 21 20 21H13C11.8954 21 11 20.1046 11 19C11 17.8954 11.8954 17 13 17H14V16V15.5ZM16.5 11C14.142 11 12.2076 12.8136 12.0156 15.122C10.2825 15.5606 9 17.1305 9 19C9 21.2091 10.7909 23 13 23H20C22.2091 23 24 21.2091 24 19C24 17.1305 22.7175 15.5606 20.9844 15.122C20.7924 12.8136 18.858 11 16.5 11Z" clip-rule="evenodd" fill-rule="evenodd"></path> </g></svg>
            </div>
            <div class="text">
                <span>Click to upload image</span>
            </div>
            <input type="file" id="file" name="image" required="">
        </label><br>
        <label>

        <input name="date" id="data" type="date" class="infos input" required="" style="width:300px;">

        </label>

        <div id="firstFilter" class="filter-switch mt-3">
            <input checked="" id="option1" name="type" type="radio" value="lost" required="" />
            <label class="option" for="option1">lost</label>
            <input id="option2" name="options" type="radio" value="found" />
            <label class="option" for="option2">found</label>
            <span class="background"></span>
        </div>

<div class="container justify-content-center mt-2" style="display: flex;">
    <button class="submit">Submit</button></div></label>

</form>
</body>
</html>
