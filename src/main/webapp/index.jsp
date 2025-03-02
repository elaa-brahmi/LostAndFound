
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  if(session.getAttribute("userId")==null && session.getAttribute("role")!="user"){
    response.sendRedirect("login.jsp");

  }


%>


<html>
  <head>
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/three.js/r128/three.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>

  .form {
    --input-text-color: #fff;
    --input-bg-color: #283542;
    --focus-input-bg-color: transparent;
    --text-color: #949faa;
    --active-color: #1b9bee;
    --width-of-input: 200px;
    --inline-padding-of-input: 1.2em;
    --gap: 0.9rem;
  }

  .form {
    font-size: 0.9rem;
    display: flex;
    gap: 0.5rem;
    justify-content: center;
    position: relative;
    isolation: isolate;
  }

  .fancy-bg {
    position: absolute;
    width: 100%;
    inset: 0;
    background: var(--input-bg-color);
    border-radius: 30px;
    height: 100%;
    z-index: -1;
    pointer-events: none;
    box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
  }

  label {
    width: 100%;
    padding: 0.8em;
    height: 40px;
    padding-inline: var(--inline-padding-of-input);
    display: flex;
    align-items: center;
  }

  .search,.close-btn {
    position: absolute;
  }

  .search {
    fill: var(--text-color);
    left: var(--inline-padding-of-input);
  }

  .search svg {
    width: 17px;
    display: block;
  }

  .close-btn {
    border: none;
    right: var(--inline-padding-of-input);
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #fff;
    padding: 0.1em;
    width: 20px;
    height: 20px;
    border-radius: 50%;
    background: var(--active-color);
    opacity: 0;
    visibility: hidden;
  }

  .input {
    color: black;
    width: 100%;
    margin-inline: min(2em,calc(var(--inline-padding-of-input) + var(--gap)));
    background: none;
    border: none;
  }

  .input:focus {
    outline: none;
  }

  .input::placeholder {
    color: var(--text-color)
  }

  .input:focus ~ .fancy-bg {
    border: 1px solid var(--active-color);
    background: var(--focus-input-bg-color);
  }

  .input:focus ~ .search {
    fill: var(--active-color);
  }

  .input:valid ~ .close-btn {
    opacity: 1;
    visibility: visible;
  }
  input:-webkit-autofill,
  input:-webkit-autofill:hover,
  input:-webkit-autofill:focus,
  input:-webkit-autofill:active {
    -webkit-transition: "color 9999s ease-out, background-color 9999s ease-out";
    -webkit-transition-delay: 9999s;
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

  .btn-search {
    --bg: grey;
    --text-color: #fff;
    position: relative;
    width: 150px;
    border: none;
    background: var(--bg);
    color: var(--text-color);
    padding: 1em;
    font-weight: bold;
    text-transform: uppercase;
    transition: 0.2s;
    border-radius: 5px;
    opacity: 0.8;
    letter-spacing: 1px;
    box-shadow: #949faa 0px 7px 2px,#949faa 0px 8px 5px;
  }

  button:hover {
    opacity: 1;
  }

  button:active {
    top: 4px;
    box-shadow:#283542 0px 3px 2px,#000 0px 3px 5px;
  }
  .card {
    width:26%;
    height:550px;
    padding: .8em;
    background: #f5f5f5;
    position: relative;
    overflow: visible;
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
  }
  .card-img {
    background-color: #e5e7e9;
    height: 40%;
    width: 100%;
    border-radius: .5rem;
    transition: .3s ease;
  }
  .card-info {
    padding-top: 10%;
  }
  .card-footer {
    width: 100%;
    display: flex;
    justify-content: center;

    padding-top: 10px;
    border-top: 1px solid #ddd;
  }

  .text-title {
    font-weight: 900;
    font-size: 1.2em;
    line-height: 1.5;
  }

  .text-body {
    font-size: .9em;
    padding-bottom: 10px;
  }

 .card-button {
    border: 1px solid #252525;
    display: flex;
    padding: .3em;
    cursor: pointer;
    border-radius: 50px;
    transition: .3s ease-in-out;
  }

 .card-img:hover {
    transform: translateY(-25%);
    box-shadow: rgba(226, 196, 63, 0.25) 0px 13px 47px -5px, rgba(180, 71, 71, 0.3) 0px 8px 16px -8px;
  }

  .card-button:hover {
    border: 1px solid #ffcaa6;
    background-color: #ffcaa6;
  }
  .card-btn {
    width:70px;
    height:50px;
    border: none;
    font-size: 1rem;
    background-color: green;
    color: white;
    cursor: pointer;
    transition: all 0.3s ease;
    border-radius: 2em;
    padding: 0.6em 1em;
  }

  .card-btn:hover {
    background-color: #026602;
  }
  #cardItems{
    justify-content: start;
    display: flex;
    margin-top: 180px;
    margin-left: 130px;

    padding-left: 5%;
    max-width: 80%;
    min-width: 80%;
    max-height: 1200px;
    min-height: 1200px;
    flex-direction: row;
    flex-wrap: wrap;
    row-gap: 50px;
    column-gap: 15px;
  }
  .pagination {
    justify-content: center;
    display: flex
  ;
    width: 80%;
  }
  .pagination button {
    margin: 0 5px;
    padding: 5px 10px;
    cursor: pointer;
  }
  /* From Uiverse.io by kamehame-ha */
  .light-button button.bt {
    position: relative;
    height: 55px;
    display: flex;
    align-items: flex-end;
    outline: none;
    background: none;
    border: none;
    cursor: pointer;
  }

  .light-button button.bt .button-holder {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 40px;
    width: 100px;
    background-color: #0a0a0a;
    border-radius: 5px;
    color: #0f0f0f;
    font-weight: 700;
    transition: 300ms;
    outline: #0f0f0f 2px solid;
    outline-offset: 20;
  }



  .light-button button.bt .light-holder {
    position: absolute;
    height: 60px;
    width: 100px;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .light-button button.bt .light-holder .dot {
    position: absolute;
    top: 0;
    width: 10px;
    height: 10px;
    background-color: #0a0a0a;
    border-radius: 10px;
    z-index: 2;
  }

  .light-button button.bt .light-holder .light {
    position: absolute;
    top: 0;
    width: 200px;
    height: 10px;
    clip-path: polygon(50% 0%, 25% 100%, 75% 100%);
    background: transparent;
  }

  .light-button button.bt:hover .button-holder svg {
    fill: rgba(88, 101, 242, 1);
  }

  .light-button button.bt:hover .button-holder {
    color: rgba(88, 101, 242, 1);
    outline: rgba(88, 101, 242, 1) 2px solid;
    outline-offset: 2px;
  }

  .light-button button.bt:hover .light-holder .light {
    background: rgb(255, 255, 255);
    background: linear-gradient(
            180deg,
            rgba(88, 101, 242, 1) 0%,
            rgba(255, 255, 255, 0) 75%,
            rgba(255, 255, 255, 0) 100%
    );
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
  <div class="d-flex mx-auto align-items-center" style="margin-top:50px;width:100%;padding-top:3%;height:25%;background-color: #e5e7e9;">
    <div class="d-flex justify-content-center" style="width: 75%;margin-left: 12.5%;">
      <div class="d-flex justify-content-center" style="width: 100%;">
        <div class="form-container" style="display: flex; align-items: center; width: 100%;">
          <form class="form" style="width: 100%;">
            <label for="search" style="width: 100%;">
              <input class="input" type="text" required="" placeholder="Search for itemsServlets" id="search" style="width: 100%;">
              <div class="fancy-bg"></div>
              <div class="search">
                <svg viewBox="0 0 24 24" aria-hidden="true" class="r-14j79pv r-4qtqp9 r-yyyyoo r-1xvli5t r-dnmrzs r-4wgw6l r-f727ji r-bnwqim r-1plcrui r-lrvibr">
                  <g>
                    <path d="M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.215 0 4.24-.804 5.808-2.13l3.66 3.66c.147.146.34.22.53.22s.385-.073.53-.22c.295-.293.295-.767.002-1.06zM3.5 11c0-4.135 3.365-7.5 7.5-7.5s7.5 3.365 7.5 7.5-3.365 7.5-7.5 7.5-7.5-3.365-7.5-7.5z"></path>
                  </g>
                </svg>
              </div>
              <button class="close-btn" type="reset">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor"></svg>
                <path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path>
                </svg>
              </button>
            </label>
          </form>
          <button class="btn-search" style="margin-left: 10px; margin-top: -3%;">search</button>
        </div>
      </div>
      <div class="d-flex justify-content-start"  style="display: flex;width:100%;">
        <button class="btn-search" style="flex: 1; width:30%; margin: 0 40px;">
          location
        </button>
        <button class="btn-search" style="flex: 1; width:30%; margin: 0 40px;">
          name
        </button>
        <button class="btn-search" style="flex: 1; width:30%; margin: 0 40px;">
        category
        </button>
      </div>
    </div>
  </div>
  <script>
    let currentPage = 1;
    $(document).ready(function() {
      fetchItems(currentPage);
    });
    function fetchItems(page) {
      $.ajax({
        url: 'http://localhost:8080/allItems', // URL of the servlet
        type: 'GET',
        data: {
          page: page,
          pageSize: 6
        },
        dataType: 'json',
       success: function(data) {
        console.log(data);
        renderItems(data.itemsServlets);
        renderPagination(data.currentPage,data.totalPages);
      },
      error: function(xhr,status,error) {
        console.log(error);
      }
    });}
    function renderItems(items){
      var cardItems=document.getElementById("cardItems");
      cardItems.innerHTML="";
      items.forEach(function(item) {
        var cardItem=document.createElement("div");
        cardItem.classList.add("card");
        cardItem.innerHTML=`
              <div class="card-img">`+item.image+`</div>
              <div class="card-info">
              <p class="text-title">`+item.category+ `<br>`+item.name+`</p>
              <p class="text-body">`+item.description+`

             </p><p>`+item.datefound+`<br>`+item.location+`</p>
              <div>

      <p><strong>`+item.type+`</strong></p>
</div>
              </div>
              </div>
              <div class="light-button" style="display: flex;justify-content: center;">
  <button class="bt">
    <div class="light-holder">
      <div class="dot"></div>
      <div class="light"></div>
    </div>
    <div class="button-holder" onclick="viewPoster(item.userid)">
      <p>view poster infos</p>
    </div>
  </button>
</div>
</div>
          `;
        cardItems.appendChild(cardItem);
           });
    }
    function renderPagination(currentPage,totalPages){
      var pagination = document.getElementById("pagination");
      pagination.innerHTML = ""; // Clear previous pagination controls
      if (currentPage > 1) {
        pagination.innerHTML += '<button onclick="fetchItems(' + (currentPage - 1) + ')">Previous</button>';
      }

      for (let i = 1; i <= totalPages; i++) {
        pagination.innerHTML += '<button ' + (i === currentPage ? 'disabled' : '') + ' onclick="fetchItems(' + i + ')">' + i + '</button>';
      }

      if (currentPage < totalPages) {
        pagination.innerHTML += '<button onclick="fetchItems(' + (currentPage + 1) + ')">Next</button>';
      }
    }

  </script>

  <div id="cardItems" class="mt-5 " style="margin-top: 10%;margin-left: 10%; " >
  </div>
  <div class="d-flex justify-content-center" style="width:80%;margin-left: 20%; margin-top:5%;">
  <div id="pagination" class="pagination"></div></div>

  </body>
</html>
