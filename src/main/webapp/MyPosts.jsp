

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
        /* From Uiverse.io by Codecite */
        .btn {
            transition: all 0.3s ease-in-out;
            font-family: "Dosis", sans-serif;
        }
        .acceptedBtn{
            border-radius: 50px;
            background-image: linear-gradient(135deg, #58d68d 0%, #58d68d 100%);
            box-shadow: 0 20px 30px -6px rgba(238, 103, 97, 0.5);
            outline: none;
            cursor: pointer;
            border: none;
            font-size: 19px;
            color: white;

        }
        .acceptedBtn:hover {
            transform: translateY(3px);
            box-shadow: none;
        }

        .acceptedBtn:active {
            opacity: 0.5;
        }
        .lostbtn {

            border-radius: 50px;
            background-image: linear-gradient(135deg, #feb692 0%, #f9e79f 100%);
            box-shadow: 0 20px 30px -6px rgba(238, 103, 97, 0.5);
            outline: none;
            cursor: pointer;
            border: none;
            font-size: 19px;
            color: white;
        }

        .lostbtn:hover {
            transform: translateY(3px);
            box-shadow: none;
        }

        .lostbtn:active {
            opacity: 0.5;
        }


        /* From Uiverse.io by ZAKARIAE48CHELLE */
        .BtnEdit {
            position: relative;
            display: flex;
            align-items: center;
            justify-content: flex-start;
            width: 100px;
            height: 40px;
            border: none;
            padding: 0px 20px;
            background-color: black;
            color: white;
            font-weight: 700;
            cursor: pointer;
            border-radius: 10px;
            box-shadow: 5px 5px 0px black;
            transition-duration: 0.3s;
        }

        .svg {
            width: 13px;
            position: absolute;
            right: 0;
            margin-right: 20px;
            fill: white;
            transition-duration: 0.3s;
        }

        .BtnEdit:hover {
            color: transparent;
        }

        .BtnEdit:hover svg {
            right: 43%;
            margin: 0;
            padding: 0;
            border: none;
            transition-duration: 0.3s;
        }

        .BtnEdit:active {
            transform: translate(3px, 3px);
            transition-duration: 0.3s;
            box-shadow: 2px 2px 0px white;
        }
        .containerPending{
            margin-top: 3%;
            display: table-row;
            border-radius: 16px;

            padding:5%;
            background: #f8f9f9;
            box-shadow: -1rem 0 3rem #00000067;



        }

        .slit-in-vertical {
            -webkit-animation: slit-in-vertical 0.45s ease-out both;
            animation: slit-in-vertical 0.45s ease-out both;
        }

        @-webkit-keyframes slit-in-vertical {
            0% {
                -webkit-transform: translateZ(-800px) rotateY(90deg);
                transform: translateZ(-800px) rotateY(90deg);
                opacity: 0;
            }
            54% {
                -webkit-transform: translateZ(-160px) rotateY(87deg);
                transform: translateZ(-160px) rotateY(87deg);
                opacity: 1;
            }
            100% {
                -webkit-transform: translateZ(0) rotateY(0);
                transform: translateZ(0) rotateY(0);
            }
        }

        @keyframes slit-in-vertical {
            0% {
                -webkit-transform: translateZ(-800px) rotateY(90deg);
                transform: translateZ(-800px) rotateY(90deg);
                opacity: 0;
            }
            54% {
                -webkit-transform: translateZ(-160px) rotateY(87deg);
                transform: translateZ(-160px) rotateY(87deg);
                opacity: 1;
            }
            100% {
                -webkit-transform: translateZ(0) rotateY(0);
                transform: translateZ(0) rotateY(0);
            }
        }




        .outline {
            position: absolute;
            inset: 0;
            pointer-events: none;
        }

        .rect {
            stroke-dashoffset: 5;
            stroke-dasharray: 0 0 10 40 10 40;
            transition: 0.5s;
            stroke: #fff;
        }

        .nav {
            position: relative;
            height: 60px;
        }

        .container:hover .outline .rect {
            transition: 999999s;
            stroke-dashoffset: 1;
            stroke-dasharray: 0;
        }

        .container {
            width: 99%;
            position: absolute;
            inset: 0;
            background: grey;
            display: flex;
            flex-direction: row;
            justify-content: space-around;
            align-items: center;
            padding: 0.5em;
        }

        .btn {
            padding: 0.5em 1.5em;
            color: #fff;
            font-size: 2em;
            text-decoration: none;
            cursor: pointer;
            transition: 0.1s;
        }

        .btn:hover {
            background: #fff3;
        }

        .btn:nth-child(1):hover ~ svg .rect {
            stroke-dashoffset: 0;
            stroke-dasharray: 0 2 8 73.3 8 10.7;
        }

        .btn:nth-child(2):hover ~ svg .rect {
            stroke-dashoffset: 0;
            stroke-dasharray: 0 12.6 9.5 49.3 9.5 31.6;
        }

        .btn:nth-child(3):hover ~ svg .rect {
            stroke-dashoffset: 0;
            stroke-dasharray: 0 24.5 8.5 27.5 8.5 55.5;
        }

        .btn:nth-child(4):hover ~ svg .rect {
            stroke-dashoffset: 0;
            stroke-dasharray: 0 34.7 6.9 10.2 6.9 76;
        }

        .btn:hover ~ .outline .rect {
            stroke-dashoffset: 0;
            stroke-dasharray: 0 0 10 40 10 40;
            transition: 0.5s !important;
        }


    </style>
</head>
<body class="vh-100" style="overflow-x: hidden;  padding:0;">
<div class="nav">
    <div class="container">
        <a href="index.jsp" class="btn">Home</a>
        <a href="MyPosts.jsp" class="btn">profile</a>
        <a href="addItem.jsp" class="btn" >report lost/found item </a>
        <a href="about.jsp" class="btn">about</a>
        <svg
                class="outline"
                overflow="visible"
                width="100%"
                height="60"
                viewBox="0 0 100% 60"
                xmlns="http://www.w3.org/2000/svg"
        >
            <rect
                    class="rect"
                    pathLength="100"
                    x="0"
                    y="0"
                    width="100%"
                    height="60"
                    fill="transparent"
                    stroke-width="5"
            ></rect>
        </svg>
    </div>
</div>
<script>
    $(document).ready(function () {
        fetchItems();
        fetchUserInfos();
    });

    function fetchUserInfos() {
        $.ajax({
            url: 'http://localhost:8080/UserInfos',
            type: 'GET',
            data: {
                userId:<%= session.getAttribute("userId") %>,
            },
            dataType: 'json',
            success: function (data) {
                console.log(data);
                renderUserInfos(data);
            },
            error: function (xhr, status, error) {
                console.log(error);
            }
        });
    }

    function fetchItems() {
        $.ajax({
            url: 'http://localhost:8080/UserItems',
            type: 'GET',
            data: {
                userId: <%= session.getAttribute("userId") %>,

            },
            dataType: 'json',
            success: function (data) {
                console.log(data.accepted);
                console.log(data.pending);
                renderItemsAccepted(data.accepted);
                renderItemsPending(data.pending);


            },
            error: function (xhr, status, error) {
                console.log(error);
            }

        });
    }

    function renderItemsPending(PendingPosts) {
        var Pendingdiv = document.getElementById("PendingPosts");
        Pendingdiv.innerHTML = "";
        PendingPosts.forEach(function (item) {
            var div = document.createElement("div");
            div.className = "containerPending";
            div.innerHTML = `
             <span style="font-size:18px;">` + item.name + `: ` + item.description + `</span>
        <span style="font-size:18px;">` + item.datefound + `</span>
        <span style="font-size:18px;"><strong>` + item.type + `</strong></span>



        <button class="BtnEdit" onclick="editItem(item.id)" style="position:absolute;right:15%;">
            Edit
            <svg viewBox="0 0 512 512" class="svg">
                <path
                        d="M410.3 231l11.3-11.3-33.9-33.9-62.1-62.1L291.7 89.8l-11.3 11.3-22.6 22.6L58.6 322.9c-10.4 10.4-18 23.3-22.2 37.4L1 480.7c-2.5 8.4-.2 17.5 6.1 23.7s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L387.7 253.7 410.3 231zM160 399.4l-9.1 22.7c-4 3.1-8.5 5.4-13.3 6.9L59.4 452l23-78.1c1.4-4.9 3.8-9.4 6.9-13.3l22.7-9.1v32c0 8.8 7.2 16 16 16h32zM362.7 18.7L348.3 33.2 325.7 55.8 314.3 67.1l33.9 33.9 62.1 62.1 33.9 33.9 11.3-11.3 22.6-22.6 14.5-14.5c25-25 25-65.5 0-90.5L453.3 18.7c-25-25-65.5-25-90.5 0zm-47.4 168l-144 144c-6.2 6.2-16.4 6.2-22.6 0s-6.2-16.4 0-22.6l144-144c6.2-6.2 16.4-6.2 22.6 0s6.2 16.4 0 22.6z"
                ></path>
            </svg>
        </button><div class="w-100"></div><br><button class="lostbtn">` + item.status + `</button>
            `;
            Pendingdiv.appendChild(div);


        });
    }

    function renderItemsAccepted(AcceptedPosts) {
        console.log(AcceptedPosts);
        var acceptedPosts=document.getElementById("acceptedPosts");
        acceptedPosts.innerHTML = "";
        AcceptedPosts.forEach(function (item) {
        var divPosts=document.createElement("div");
        divPosts.className="containerPending";

            divPosts.innerHTML = `
             <span style="font-size:18px;">` + item.name + `: ` + item.description + `</span>
        <span style="font-size:18px;">` + item.datefound + `</span>
        <span style="font-size:18px;"><strong>` + item.type + `</strong></span>
        <button class="BtnEdit" onclick="editItem(item.id)" style="position:absolute;right:15%;">
            Edit
            <svg viewBox="0 0 512 512" class="svg">
                <path
                        d="M410.3 231l11.3-11.3-33.9-33.9-62.1-62.1L291.7 89.8l-11.3 11.3-22.6 22.6L58.6 322.9c-10.4 10.4-18 23.3-22.2 37.4L1 480.7c-2.5 8.4-.2 17.5 6.1 23.7s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L387.7 253.7 410.3 231zM160 399.4l-9.1 22.7c-4 3.1-8.5 5.4-13.3 6.9L59.4 452l23-78.1c1.4-4.9 3.8-9.4 6.9-13.3l22.7-9.1v32c0 8.8 7.2 16 16 16h32zM362.7 18.7L348.3 33.2 325.7 55.8 314.3 67.1l33.9 33.9 62.1 62.1 33.9 33.9 11.3-11.3 22.6-22.6 14.5-14.5c25-25 25-65.5 0-90.5L453.3 18.7c-25-25-65.5-25-90.5 0zm-47.4 168l-144 144c-6.2 6.2-16.4 6.2-22.6 0s-6.2-16.4 0-22.6l144-144c6.2-6.2 16.4-6.2 22.6 0s6.2 16.4 0 22.6z"
                ></path>
            </svg>
        </button><div class="w-100"></div><br><button class="acceptedBtn">` + item.status + `</button>
            `;
            acceptedPosts.appendChild(divPosts);
        });
    }
    function renderUserInfos(user) {
        var div = document.getElementById("userProfile");
        div.innerHTML = `
        <div class="cardUser">
        <header class="card-header">
            <p><%= new Date()%></p>
            <span class="title">` + user.email + `</span>
            <span class="title">` + user.phone + `</span>
        </header>
        <div class="card-author">
            <a class="author-avatar" href="#">
    <span>
  </span></a>
            <svg class="half-circle" viewBox="0 0 106 57">
                <path d="M102 4c0 27.1-21.9 49-49 49S4 31.1 4 4"></path>
            </svg>
            <div class="author-name">
                <div class="author-name-prefix">user</div> ` + user.name + `
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
<div style="margin-top: 5%;display: flex;justify-content: space-around;">
    <div id="userProfile" style="width: 35%;
    position: relative;
    top: 7%;
    margin-left: 5%;
 transition: transform 0.1s ease-out;">
    </div>
    <div style="height: auto;
    width: 40%;
    margin-right: 10%;
    display: flex;
    flex-direction: column;">
        <div id="PendingPosts" style="display: flex;
    height: auto;
    row-gap: 7%;
    flex-direction: column;">
        </div>
        <div id="acceptedPosts" style="display: flex;
    height: auto;
    row-gap: 7%;
    flex-direction: column;"></div>
    </div>
</div>
<script>

    const cards = document.querySelectorAll(".containerPending *");
    const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add("slit-in-vertical");
                observer.unobserve(entry.target); // Ensures animation happens only once
            }
        });
    }, {threshold: 0.2}); // Trigger when 20% of the element is visible

    cards.forEach(card => observer.observe(card));
    window.addEventListener("scroll", () => {
        let scrollY = window.scrollY; // Get how much the user has scrolled
        document.querySelector("#userProfile").style.transform = `translateY(`+scrollY`+px)`;
    });


    function editItem(itemId){
        $.ajax({
            url: 'http://localhost:8080/ItemById',
            type: 'GET',
            data: {
                itemId:itemId,

            },
            dataType: 'json',
            success: function (data) {
               console.log(itemId)


            },
            error: function (xhr, status, error) {
                console.log(error);
            }
        })

    }
</script>
</body>
</html>
