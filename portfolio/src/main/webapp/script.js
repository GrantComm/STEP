// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomFact() {
  const facts = [
    'My favorite color is orange',
    'My favorite tv show is \'The X-Files\' ',
    'My zodiac sign is Scorpio',
    'I love fishing and soccer!',
    'My favorite movie is Star Wars: The Empire Strikes Back',
    'My favorite music artists are Anderson .Paak, Logic, and Esperanza Spalding'
  ];
  // Pick a random fact
  const fact = facts[Math.floor(Math.random() * facts.length)];

  return fact;
}

function showModal() {
  // Variable for the comment button
  let commentButton = document.getElementById('commentButton');
  // Variables for the about me row
  let eduButton = document.getElementById('eduButton');
  let interestsButton = document.getElementById('interestsButton');
  let funFactsButton = document.getElementById('funFactsButton');
  funFactsButton.innerText = 'Click for a Random Fact';

  // Variables for the projects row
  let mobileButton = document.getElementById('mobileButton');
  let webButton = document.getElementById('webButton');
  let githubButton = document.getElementById('githubButton');

  // Variables for the more row
  let skillsButton = document.getElementById('skillsButton');
  let mapButton = document.getElementById('mapButton');
  let mapElement = document.getElementById('map'); 
  mapElement.style.display = 'none'; 
  let donateButton = document.getElementById('donateButton');
  let chart = document.getElementById('chart-container'); 

  // Variables for the modal body and header
  let modal = document.getElementById('myModal');
  let modalHeader = document.getElementById('modalHeader');
  let modalBody = document.getElementById('modalBody');
  let modalImage = document.getElementById('modalImage');
  
  // Variables for the forms
  let mapForm = document.getElementById('mapForm');
  mapForm.style.display = 'none'; 
  let commentForm = document.getElementById('commentForm');
  commentForm.style.display = 'none'; 

  // When the user clicks the button, open the modal 
  eduButton.onclick = function () {
    modal.style.display = 'block';
    modalImage.style.display = 'inline';
    modalHeader.innerText = 'Education';
    modalImage.src = 'images/profile.jpg';
    modalBody.innerText = 'I am a rising sophomore at Morehouse College, majoring in Computer Science and minoring in Mathematics';
  }

  interestsButton.onclick = function () {
    modal.style.display = 'block';
    modalImage.style.display = 'inline';
    modalHeader.innerText = 'Interests';
    modalImage.src = 'images/grantSoccer.jpg';
    modalBody.innerText = 'I am interested in backend and frontend mobile app development, gesture-based computing (Tony Stark-like interfaces), and bluetooth technology';
  }

  funFactsButton.onclick = function () {
    modal.style.display = 'block';
    modalImage.style.display = 'inline';
    modalHeader.innerText = 'Fun Facts';
    modalImage.src = 'images/grantStarWars.jpg';
    modalBody.innerText = addRandomFact();
  }

  mobileButton.onclick = function () {
    modal.style.display = 'block';
    modalImage.style.display = 'inline';
    modalImage.src = 'images/grantShades.jpg';
    modalHeader.innerText = 'Mobile Development Projects';
    modalBody.innerHTML = 'I made a basic mobile app for Destined for Greatness Youth Outreach Center, a youth mentorship organization.' + '\n DFG Mobile'.link('https://apps.apple.com/us/app/dfg-mobile/id1501790483?ign-mpt=uo%3D4');
  }

  webButton.onclick = function () {
    modal.style.display = 'block';
    modalImage.style.display = 'inline';
    modalImage.src = 'images/grantFishing.jpg';
    modalHeader.innerText = 'Web Development Projects';
    modalBody.innerHTML = 'I made the Morehouse23 website as a resource for students in my class.' + '\n Morehouse Class of 2023 Website'.link('https://morehouse23.info/');
  }

  githubButton.onclick = function () {
    modal.style.display = 'block';
    modalImage.style.display = 'inline';
    modalHeader.innerText = 'Github';
    modalImage.src = 'images/grantStanding.jpg';
    modalBody.innerHTML = 'View more of my projects on my ' + 'Github profile'.link('https://github.com/GrantComm');
  }

  skillsButton.onclick = function () {
    modal.style.display = 'block';
    modalImage.style.display = 'inline';
    modalHeader.innerText = 'Technologies';
    modalImage.src = 'images/grantWalking.jpg';
    modalBody.innerText = 'I have working experience using Java, C++, and webtechnologies';
  }

  commentButton.onclick = function () {
    modal.style.display = 'block';
    modalImage.style.display = 'none';
    modalHeader.innerText = 'Submit a Comment';
    modalBody.innerText = 'Comment';
    commentForm.style.display = 'block';  
    modalBody.appendChild(commentForm);
  }

  mapButton.onclick = function () {
    modalBody.innerHTML= '';
    modal.style.display = 'block';
    modalImage.style.display = 'none';
    modalHeader.innerText = 'Intern College Map'; 
    mapForm.style.display = 'block';  
    mapElement.style.display = 'block';
    getUserStatus();
    loadMapMarkers(createMap(mapElement));
    modalBody.appendChild(mapElement);
    modalBody.appendChild(mapForm); 
  }
  
    donateButton.onclick = function () {
    modalBody.innerHTML= '';
    modal.style.display = 'block';
    modalImage.style.display = 'none';
    modalHeader.innerText = 'Make a Difference';
    chart.style.display = 'block';
    drawChart();  
    modalBody.appendChild(chart);
    //modalBody.appendChild(mapForm); 
  }

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = 'none';
    }
  }
}

window.onscroll = function () { scrollFunction() };

// Expands or contracts the navbar and displays to top button on scroll
let pixelsScrolled = 310; 
function scrollFunction() {
  let scrollToTopButton = document.getElementById('scrollToTopButton');
  let navigationMenu = document.getElementById('navigationMenu');
  // When the user scrolls down 310px from the top of the document, show the button
  if (document.body.scrollTop > pixelsScrolled || document.documentElement.scrollTop > pixelsScrolled) {
    navigationMenu.classList.add('extendedBar');
    scrollToTopButton.style.display = 'block';
  } else {
    scrollToTopButton.style.display = 'none';
    navigationMenu.classList.remove('extendedBar');
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}

let pageNumber = 1;
function loadComments() {
  let languageCode = document.getElementById('selectedLanguage').value;
  document.getElementById('commentsList').innerHTML = '';
  fetch(`/comments-list?pageNumber=${pageNumber}&languageCode=${languageCode}`).then(response => response.json()).then((comments) => {
    const commentListElement = document.getElementById('commentsList');
    comments.forEach((comment) => {
      commentListElement.appendChild(createCommentElement(comment));
    })
  });
}

function nextCommentsGroup() {
  pageNumber++;
  loadComments();
}

function previousCommentsGroup() {
  if (pageNumber <= 1) {
    return;
  }
  pageNumber--;
  loadComments();
}

function deleteComments() {
  fetch('/delete-comments', { method: 'delete' }).then(response => loadComments());
}

function createCommentElement(comment) {
  const commentElement = document.createElement('li');
  commentElement.className = 'comment';

  const commentHeader = document.createElement('div');
  const nameElement = document.createElement('h3');
  nameElement.innerText = comment.author;
  const dateElement = document.createElement('p');
  dateElement.innerText = String(comment.currentDate);
  commentHeader.appendChild(nameElement)
  commentHeader.appendChild(dateElement);
  const commentContent = document.createElement('div');
  const contentElement = document.createElement('p1');
  contentElement.innerText = String(comment.content);
  commentContent.appendChild(contentElement);

  commentElement.appendChild(commentHeader);
  commentElement.appendChild(commentContent);
  return commentElement;
}

function getNumberOfComments() {
  const numberOfCommentsMenu = document.getElementById('numberOfComments');
  return numberOfCommentsMenu.options[numberOfCommentsMenu.selectedIndex].value;
}

function addLocation(map, lat, lng, collegeName, internName) {
  const marker = new google.maps.Marker({
    position: {lat: lat, lng: lng}, 
    map: map,
    title: collegeName
  });

  const info = new google.maps.InfoWindow({content: internName});
  marker.addListener('click', () => {
    info.open(map, marker); 
  });
}

function getUserStatus() {
  fetch('/login-user').then(response => response.json()).then(userStatus => {
      displayForm(userStatus.loggedIn);
  }); 
}


function displayForm(isUserLoggedIn) {
  if (isUserLoggedIn) {
    console.log("Show the Form");
  } else {
    console.log("Dont do it"); 
  }
}

function createMap(mapElement) {
  const latitude = 33.745972;
  const longitude = -84.413879; 
  const zoomSize = 3;  
  const map = new google.maps.Map(mapElement, {center: {lat: latitude, lng: longitude}, zoom: zoomSize});
  return map; 
}

function loadMapMarkers(map) {
  fetch(`/map-markers-list`).then(response => response.json()).then((markers) => {
    markers.forEach((mapMarker) => {
      addLocation(
        map, 
        mapMarker.latitude, 
        mapMarker.longitude, 
        mapMarker.collegeName, 
        mapMarker.internName); 
    })
  });
}

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
  const data = new google.visualization.DataTable();
  data.addColumn('string', 'Animal');
  data.addColumn('number', 'Count');
        data.addRows([
          ['Lions', 10],
          ['Tigers', 5],
          ['Bears', 15]
        ]);

  const options = {
    'title': 'Zoo Animals',
    'width':500,
    'height':400
  };

  const chart = new google.visualization.PieChart(
      document.getElementById('chart-container'));
  chart.draw(data, options);
}
