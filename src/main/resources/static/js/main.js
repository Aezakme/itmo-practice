'use strict';

let airports;
const suggestionsElement = document.getElementsByClassName('suggested')[0];
const inputElement = document.getElementsByClassName('location_input')[0];
const recentAirports = new Set();

fetch('./js/data/airports.json')
  .then(responce => responce.json())
  .then(airportsJson => {
    airports = airportsJson;
  });

function onInput(inputClass) {
  clearSuggested();
  const input = inputClass.value;
  makeSuggested(input);
}

function suggestRecent() {
  clearSuggested();
  if (inputElement.value !== '') {
    return;
  }

  for (const airport of recentAirports) {
    addSuggest(airport);
  }
}

function makeSuggested(input) {
  if (input === '') {
    return;
  }
  const keys = Object.keys(airports);
  let cnt = 0;
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i];
    const airport = airports[key];
    if ((
      airport.city.toUpperCase().startsWith(input.toUpperCase()) ||
      airport.name.toUpperCase().startsWith(input.toUpperCase()) ||
      airport.icao.toUpperCase().startsWith(input.toUpperCase())) &&
      (cnt < 10)
    ) {
      addSuggest(airport);
      cnt++;
    }
  }
}

function clearSuggested() {
  while (suggestionsElement.firstChild) {
    suggestionsElement.removeChild(suggestionsElement.firstChild);
  }
  suggestionsElement.style.display = 'none';
}

async function getOffices() {

  var elementById = document.getElementById('officesSelector');

  let response = await fetch("/office/all");

  if (response.ok) { // если HTTP-статус в диапазоне 200-299
    // получаем тело ответа (см. про этот метод ниже)
    let json = await response.json();
    for (let office of json) {
      let htmlOptionElement = document.createElement('option');
      // htmlOptionElement.value = "heellllo";
      htmlOptionElement.text = office.name + ', ' + office.address;
      htmlOptionElement.value = office.id
      elementById.appendChild(htmlOptionElement)
    }

    // alert(json[0].name + ', ' + json[0].address)
  } else {
    alert("Ошибка HTTP: " + response.status);
  }
}

function addPhoto(htmlOptionElement) {
  let photoElement = document.createElement('div');
  photoElement.className = 'photo';
  let img = document.createElement('img');
  img.src = "img/book.png";
  photoElement.appendChild(img);
  htmlOptionElement.appendChild(photoElement);
}


async function getRandomBook() {

  var elementById = document.getElementById('random_books');
  let response = await fetch("/book/all");

  if (response.ok) { // если HTTP-статус в диапазоне 200-299
    // получаем тело ответа (см. про этот метод ниже)
    let json = await response.json();
    for (let book of json.slice(0, 8)) {

      let htmlOptionElement = document.createElement('div');
      let link = '/book/' + book.id;

      htmlOptionElement.className = 'photo_wrap onclick=""';

      let title = document.createElement('h3');
      title.className = 'title';
      title.innerText = book.title;

      let author = document.createElement('h');
      author.className = 'author';
      author.innerText = book.author;

      addPhoto(htmlOptionElement);
      htmlOptionElement.appendChild(title);
      htmlOptionElement.appendChild(author);
      htmlOptionElement.onclick = function () {
        location.href = link;
      };
      elementById.appendChild(htmlOptionElement)
    }

    // alert(json[0].name + ', ' + json[0].address)
  } else {
    alert("Ошибка HTTP: " + response.status);
  }
}


async function goToRandomBook() {
  var elementById = document.getElementById('random_books');
  let response = await fetch("/book/all");

  if (response.ok) { // если HTTP-статус в диапазоне 200-299
    // получаем тело ответа (см. про этот метод ниже)
    let json = await response.json();
    let number = Math.floor(Math.random() * Math.floor(json.length));
    window.location.href = "book/" + json[number].id;
  } else {
    alert("Ошибка HTTP: " + response.status);
  }
}


function addSuggest(airport) {
  suggestionsElement.style.display = 'block';
  const liElement = document.createElement('li');
  const navElement = document.createElement('button');
  const textElement = document.createElement('div');
  textElement.innerText = airport.name;
  const codeElement = document.createElement('span');
  codeElement.innerText = airport.icao;
  navElement.appendChild(textElement);
  navElement.appendChild(codeElement);
  liElement.appendChild(navElement);
  liElement.onclick = function () {
    inputElement.value = airport.name;
    clearSuggested();
    makeSuggested(airport.name);
    recentAirports.add(airport);
    clearSuggested();
  };
  suggestionsElement.appendChild(liElement);
}

