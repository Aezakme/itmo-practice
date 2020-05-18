async function setBookInfo() {
    let href = window.location.href;
    let id = href.split("/").pop();

    let response = await fetch("/book/value/" + id);

    if (response.ok) { // если HTTP-статус в диапазоне 200-299
        // получаем тело ответа (см. про этот метод ниже)
        let book = await response.json();
        document.getElementById('author').innerText = book.author;
        document.getElementById('description').innerText = book.description;
        document.getElementById('title').innerText = book.title;
        document.getElementById('year').innerText = book.year;
    }
}

async function setOfficeInfo() {
    var elementById = document.getElementById('availability_table');


    let href = window.location.href;
    let id = href.split("/").pop();

    let response = await fetch("/availability/" + id);
    if (response.ok) { // если HTTP-статус в диапазоне 200-299

        let officeResponse = await fetch("/office/all");
        if (officeResponse.ok) {

            let offices = await officeResponse.json();
            let availabilities = await response.json();

            for (let availability of availabilities) {
                let office = offices[availability.office_id - 1];

                let tr = document.createElement('tr');

                let officeElement = document.createElement('td');
                officeElement.className = 'office';
                officeElement.innerText = office.name + ', ' + office.address
                let number = document.createElement('td');
                number.className = 'number';
                number.innerText = availability.amount + ' шт.';

                tr.appendChild(officeElement);
                tr.appendChild(number);
                elementById.appendChild(tr)
            }
        }
    }
}