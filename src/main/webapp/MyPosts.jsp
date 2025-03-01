

<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.Date"
%>
<%
  if(session.getAttribute("userId")==null && session.getAttribute("role")!="user"){
    response.sendRedirect("login.jsp");

  }



%>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>My posts</title>
    <style>
        /* From Uiverse.io by Madflows */
        .cardUser {
            display: flex;
            position: relative;
            flex-direction: column;
            height: 350px;
            width: 190px;
            min-width: 250px;
            padding: 1rem;
            border-radius: 16px;
            background: #17141d;
            box-shadow: -1rem 0 3rem #00000067;
            transition: .2s;
            font-family: 'Inter', sans-serif;
        }

        .cardUser:hover {
            transform: translateY(-0.4rem);
        }

        .card-author {
            position: relative;
            display: grid;
            grid-template-columns: 75px 1fr;
            align-items: center;
            margin: 3rem 0 0;
        }

        .author-name {
            color: #7a7a8c;
        }

        .author-name-prefix {
            color: #e52e71;
            font-weight: 600;
        }

        .author-avatar span {
            display: block;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background: #f2f2f2;
            margin: 16px 10px;
        }

        .half-circle {
            position: absolute;
            bottom: 0;
            left: 0;
            width: 60px;
            height: 48px;
            fill: none;
            stroke: #ff8a00;
            stroke-width: 8;
            stroke-linecap: round;
        }

        .card-header {
            margin-bottom: auto;
            color: #7a7a8c;
        }

        .card-header p {
            font-size: 14px;
            margin: 0 0 1rem;
            color: #7a7a8c;
        }

        .card-header .title {
            font-size: 15px;
            margin: .25rem 0 auto;
            cursor: pointer;
            font-family: 'Arial Black', sans-serif;
        }

        .card-header .title:hover {
            background: linear-gradient(90deg,#ff8a00,#e52e71);
            text-shadow: none;
            background-clip: text;
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .tags {
            margin: 1rem 0 2rem;
            padding: .5rem 0 1rem;
            line-height: 2;
            margin-bottom: 0;
        }

        .tags a {
            font-style: normal;
            font-weight: 700;
            color: #7a7a8c;
            text-transform: uppercase;
            font-size: .66rem;
            border: 3px solid #28242f;
            border-radius: 2rem;
            padding: .2rem .85rem .25rem;
            position: relative;
        }

        .tags a:hover {
            background: linear-gradient(90deg,#ff8a00,#e52e71);
            text-shadow: none;
            -webkit-text-fill-color: transparent;
            -webkit-background-clip: text;
            -webkit-box-decoration-break: clone;
            box-decoration-break: clone;
            background-clip: text;
            border-color: white;
        }
        /* From Uiverse.io by vinodjangid07 */
        .notifbutton {
            width: 50px;
            height: 50px;
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: rgb(44, 44, 44);
            border-radius: 50%;
            cursor: pointer;
            transition-duration: .3s;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.13);
            border: none;
        }

        .bell {
            width: 18px;
        }

        .bell path {
            fill: white;
        }

        .notifbutton:hover {
            background-color: rgb(56, 56, 56);
        }

        .notifbutton:hover .bell {
            animation: bellRing 0.9s both;
        }

        /* bell ringing animation keyframes*/
        @keyframes bellRing {
            0%,
            100% {
                transform-origin: top;
            }

            15% {
                transform: rotateZ(10deg);
            }

            30% {
                transform: rotateZ(-10deg);
            }

            45% {
                transform: rotateZ(5deg);
            }

            60% {
                transform: rotateZ(-5deg);
            }

            75% {
                transform: rotateZ(2deg);
            }
        }

        .notifbutton:active {
            transform: scale(0.8);
        }

    </style>
</head>
<body>
<script>
    $(document).ready(function(){
        fetchItems();
        fetchUserInfos();
    });
    function fetchUserInfos(){
        $.ajax({
            url: 'http://localhost:8080/UserInfos',
            type: 'GET',
            data:{
                userId:<%= session.getAttribute("userId") %>,
            },
            dataType: 'json',
            success: function(data){
                console.log(data);
                renderUserInfos(data);
            },
            error: function(xhr,status,error){
                console.log(error);
            }
        });
    }
    function fetchItems(){
        $.ajax({
            url: 'http://localhost:8080/UserItems',
            type:'GET',
            data:{
                userId: <%= session.getAttribute("userId") %>,

            },
            dataType: 'json',
            success: function(data){
                console.log(data.accepted);
                console.log(data.pending);

                renderItemsAccepted(data.accepted);
                renderItemsPending(data.pending);


            },
            error: function(xhr,status,error){
                console.log(error);
            }

        });
    }
    function renderItemsPending(PendingPosts){
        var Pendingdiv=document.getElementById("PendingPosts");
        Pendingdiv.innerHTML="";
        PendingPosts.forEach(function(item){


        });
    }
    function renderItemsAccepted(AcceptedPosts){

    }
    function renderUserInfos(user){
        var div =document.getElementById("userProfile");
        div.innerHTML=`
        <div class="cardUser">
        <header class="card-header">
            <p><%= new Date()%></p>
            <span class="title">`+user.email+`</span>
            <span class="title">`+user.phone+`</span>
        </header>
        <div class="card-author">
            <a class="author-avatar" href="#">
    <span>
  </span></a>
            <svg class="half-circle" viewBox="0 0 106 57">
                <path d="M102 4c0 27.1-21.9 49-49 49S4 31.1 4 4"></path>
            </svg>
            <div class="author-name">
                <div class="author-name-prefix">user</div> `+user.name+`
            </div>
        </div>
        <div class="tags">

<button class="notifbutton">
   <svg viewBox="0 0 448 512" class="bell"><path d="M224 0c-17.7 0-32 14.3-32 32V49.9C119.5 61.4 64 124.2 64 200v33.4c0 45.4-15.5 89.5-43.8 124.9L5.3 377c-5.8 7.2-6.9 17.1-2.9 25.4S14.8 416 24 416H424c9.2 0 17.6-5.3 21.6-13.6s2.9-18.2-2.9-25.4l-14.9-18.6C399.5 322.9 384 278.8 384 233.4V200c0-75.8-55.5-138.6-128-150.1V32c0-17.7-14.3-32-32-32zm0 96h8c57.4 0 104 46.6 104 104v33.4c0 47.9 13.9 94.6 39.7 134.6H72.3C98.1 328 112 281.3 112 233.4V200c0-57.4 46.6-104 104-104h8zm64 352H224 160c0 17 6.7 33.3 18.7 45.3s28.3 18.7 45.3 18.7s33.3-6.7 45.3-18.7s18.7-28.3 18.7-45.3z"></path></svg>
</button>
            
        </div>
    </div>

        `;


    }
</script>
<div id="userProfile" style="position: relative;top: 7%;margin-left: 5%;">



</div>


<div id="PendingPosts">

</div>
<div id="acceptedPosts"></div>

</body>
</html>
