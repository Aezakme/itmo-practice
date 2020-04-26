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
  liElement.onclick = function() {
    inputElement.value = airport.name;
    clearSuggested();
    makeSuggested(airport.name);
    recentAirports.add(airport);
    clearSuggested();
  };
  suggestionsElement.appendChild(liElement);
}
