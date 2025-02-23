
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>authentication</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
</head>
<body>
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
<input type="hidden" id="error" value="<%=request.getAttribute("error")%>">
<div class="main">
    <input type="checkbox" id="chk" aria-hidden="true">
    <div class="signup">
        <form method="post" action="register">
            <label for="chk" aria-hidden="true">Sign up</label>
            <input type="text" name="name" placeholder="name" required="">
            <input type="text" name="email" placeholder="Email" required="">
            <input type="number" name="phoneNumber" placeholder="Phone Number" required="">
            <input type="password" name="password" placeholder="Password" required="">
            <input type="password" name="confirmPassword" placeholder=" Confirm Password" required="">
            <button>create account</button>
        </form>
    </div>
    <div class="login">
        <form method="post" action="login">
            <label for="chk" aria-hidden="true">Login</label>
            <input type="email" name="email" placeholder="Email" required="">
            <input type="password" name="password" placeholder="Password" required="">
            <button>Login</button>
        </form>
    </div>
</div>
<script type="text/javascript">
    var statusElement=document.getElementById('status');
    var status = statusElement ? statusElement.value : "";
    var errorElement =document.getElementById('error');
    var error=errorElement? errorElement.value :"";
    if(error!= "null" && error.trim() !== ""){
        swal('Error', error, 'warning');
    }

    if(status==='success'){
        swal('success','account created successfully',"success");
    }
    else if(status==='fail'){
        swal('error','user with this email already exists',"error");
    }
</script>
</body>
</html>
