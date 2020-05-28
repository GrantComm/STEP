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
    // Pick a random greeting.
    const fact = facts[Math.floor(Math.random() * facts.length)];

    // Add it to the page.
    const factContainer = document.getElementById('fact-container');
    factContainer.innerText = fact;
}

function showModal() {

    //Variables for the about me row
    var educationModal = document.getElementById("educationModal");
    var educationDiv = document.getElementById("education");
    var interestsModal = document.getElementById("interestsModal");
    var interestsDiv = document.getElementById("interests");
    var funFactsModal = document.getElementById("funFactsModal");
    var eduDiv = document.getElementById("funFacts");

    //Variables for the projects row
    var mobileModal = document.getElementById("mobileModal");
    var mobileDiv = document.getElementById("mobile");
    var webModal = document.getElementById("webModal");
    var webDiv = document.getElementById("web");
    var generalModal = document.getElementById("generalModal");
    var generalDiv = document.getElementById("general");

    var span = document.getElementsByClassName("exit")[0];

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

}

window.onscroll = function() { scrollFunction() };

//Expands or contracts the navbar and displays to top button on scroll

function scrollFunction() {
    var mybutton = document.getElementById("myBtn");
    var myNavBar = document.getElementById("nav");
    // When the user scrolls down 20px from the top of the document, show the button
    if (document.body.scrollTop > 310 || document.documentElement.scrollTop > 310) {
        myNavBar.classList.add("extendedBar");
        mybutton.style.display = "block";

    } else {
        mybutton.style.display = "none";
        myNavBar.classList.remove("extendedBar");
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}


