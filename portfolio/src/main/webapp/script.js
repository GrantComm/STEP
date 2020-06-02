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
  let commentButton = document.getElementById("commentButton");
  // Variables for the about me row
  let eduButton = document.getElementById("eduButton");
  let interestsButton = document.getElementById("interestsButton");
  let funFactsButton = document.getElementById("funFactsButton");

  // Variables for the projects row
  let mobileButton = document.getElementById("mobileButton");
  let webButton = document.getElementById("webButton");
  let githubButton = document.getElementById("githubButton");

  // Variables for the skills row
  let skillsButton = document.getElementById("skillsButton");

  // Variables for the modal body and header
  let modal = document.getElementById("myModal");
  let modalHeader = document.getElementById("modalHeader");
  let modalBody = document.getElementById("modalBody");
  let modalImage = document.getElementById("modalImage");

  // When the user clicks the button, open the modal 
  eduButton.onclick = function() {
    modal.style.display = "block";
    modalImage.style.display = "inline";
    modalHeader.innerText = "Education";
    modalImage.src = 'images/profile.jpg';
    modalBody.innerText = "Morehouse College, Class of 2023";
  }

  interestsButton.onclick = function() {
    modal.style.display = "block";
    modalImage.style.display = "inline";
    modalHeader.innerText = "Interests";
    modalImage.src = 'images/grantSoccer.jpg';
    modalBody.innerText = "Backend and Frontend Mobile App Development \n Gesture-Based Computing \n Bluetooth Technology";
  }

  funFactsButton.onclick = function() {
    modal.style.display = "block";
    modalImage.style.display = "inline";
    modalHeader.innerText = "Fun Facts";
    modalImage.src = 'images/grantStarWars.jpg';
    modalBody.innerText = addRandomFact();
  }

  mobileButton.onclick = function() {
    modal.style.display = "block";
    modalImage.style.display = "inline";
    modalImage.src = 'images/grantShades.jpg';
    modalHeader.innerText = "Mobile Development Projects";
    modalBody.innerHTML = "DFG_Mobile".link("https://apps.apple.com/us/app/dfg-mobile/id1501790483?ign-mpt=uo%3D4");
  }

  webButton.onclick = function() {
    modal.style.display = "block";
    modalImage.style.display = "inline";
    modalImage.src = 'images/grantFishing.jpg';
    modalHeader.innerText = "Web Development Projects";
    modalBody.innerHTML = "Morehouse Class of 2023 Website".link("https://morehouse23.info/");
  }

  githubButton.onclick = function() {
    modal.style.display = "block";
    modalImage.style.display = "inline";
    modalHeader.innerText = "Github";
    modalImage.src = 'images/grantStanding.jpg';
    modalBody.innerHTML = "MyGithub Profile".link("https://github.com/GrantComm");
  }

  skillsButton.onclick = function() {
    modal.style.display = "block";
    modalImage.style.display = "inline";
    modalHeader.innerText = "Skills";
    modalImage.src = 'images/grantWalking.jpg';
    modalBody.innerText = "Java, Webtechnologies, Python, C++, ....";
  }

  commentButton.onclick = function() {
    modal.style.display = "block";
    modalImage.style.display = "none";
    modalHeader.innerText = "Submit a Comment";
    modalBody.innerText = "Comment";

    let commentForm = document.createElement("form");
    commentForm.setAttribute('method', "post");
    commentForm.setAttribute('action', "/data");
    let commentText = document.createElement("input");
    commentText.setAttribute('type', "text");
    commentText.setAttribute('name', "commentText");
    let submitButton = document.createElement("input");
    submitButton.setAttribute('type', "submit");
    submitButton.setAttribute('value', "Submit");

    commentForm.appendChild(commentText);
    commentForm.appendChild(submitButton);

    modalBody.appendChild(commentForm);
  }

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  }
}

window.onscroll = function() { scrollFunction() };

// Expands or contracts the navbar and displays to top button on scroll

function scrollFunction() {
  let scrollToTopButton = document.getElementById("scrollToTopButton");
  let navigationMenu= document.getElementById("navigationMenu");
  // When the user scrolls down 20px from the top of the document, show the button
  if (document.body.scrollTop > 310 || document.documentElement.scrollTop > 310) {
    navigationMenu.classList.add("extendedBar");
    scrollToTopButton.style.display = "block";
  } else {
    scrollToTopButton.style.display = "none";
    navigationMenu.classList.remove("extendedBar");
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}

async function tester() {
  const serverReq = await fetch("/data");
  const result = await serverReq.json();
  console.log(result[0]);
  console.log(result[1]);
  console.log(result[2]);
  document.getElementById("modalBody").innerText = result[0];
}


