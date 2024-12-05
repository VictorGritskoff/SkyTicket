function showDeleteAirlineModal(airlineID) {
    if (confirm('Вы уверены, что хотите удалить эту авиалинию?')) {
        fetch('/dashboard/airlines/delete?id=' + airlineID, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert('Авиалиния успешно удалена');
                    location.reload();
                } else {
                    response.text().then(text => alert('Ошибка: ' + text));
                }
            })
            .catch(error => {
                console.error('Ошибка при удалении:', error);
                alert('Ошибка при удалении');
            });
    }
}

function openAirlineModal(action, airline = null) {
    const airlineIdField = document.getElementById('airlineId');
    const airlineNameField = document.getElementById('airlineName');
    const countryField = document.getElementById('country');
    const modalTitle = document.getElementById('addAirlineModalLabel');

    if (action === 'add') {
        modalTitle.textContent = 'Добавить авиалинию';
        airlineIdField.value = '';
        airlineNameField.value = '';
        countryField.value = '';
    } else if (action === 'edit' && airline) {
        modalTitle.textContent = 'Редактировать авиалинию';
        airlineIdField.value = airline.id;
        airlineNameField.value = airline.airlineName;
        countryField.value = airline.country;
    }

    const modal = new bootstrap.Modal(document.getElementById('addAirlineModal'));
    modal.show();
}

function showDeleteAirportModal(airportID) {
    if (confirm('Вы уверены, что хотите удалить этот аэропорт?')) {
        fetch('/dashboard/airport/delete?id=' + airportID, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert('Аэропорт успешно удален');
                    location.reload();
                } else {
                    response.text().then(text => alert('Ошибка: ' + text));
                }
            })
            .catch(error => {
                console.error('Ошибка при удалении:', error);
                alert('Ошибка при удалении');
            });
    }
}

function openAirportModal(action, airport = null) {
    const airportIdField = document.getElementById('airportId');
    const airportNameField = document.getElementById('airportName');
    const cityField = document.getElementById('city');
    const countryField = document.getElementById('country');
    const modalTitle = document.getElementById('addAirportModalLabel');

    if (action === 'add') {
        modalTitle.textContent = 'Добавить аэропорт';
        airportIdField.value = '';
        airportNameField.value = '';
        cityField.value = '';
        countryField.value = '';
    } else if (action === 'edit' && airport) {
        modalTitle.textContent = 'Редактировать аэропорт';
        airportIdField.value = airport.id;
        airportNameField.value = airport.airportName;
        cityField.value = airport.city;
        countryField.value = airport.country;
    }

    const modal = new bootstrap.Modal(document.getElementById('addAirportModal'));
    modal.show();
}

function showDeleteFlightModal(flightID) {
    if (confirm('Вы уверены, что хотите удалить этот рейс?')) {
        fetch('/dashboard/flights/delete?id=' + flightID, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert('Рейс успешно удален');
                    location.reload();
                } else {
                    response.text().then(text => alert('Ошибка: ' + text));
                }
            })
            .catch(error => {
                console.error('Ошибка при удалении:', error);
                alert('Ошибка при удалении');
            });
    }
}

function openFlightModal(action, data = null) {
    const modalTitle = document.getElementById('addFlightModalLabel');
    const flightNumberField = document.getElementById('flightNumber');
    const departureTimeField = document.getElementById('departureTime');
    const arrivalTimeField = document.getElementById('arrivalTime');
    const departureAirportField = document.getElementById('departureAirport');
    const arrivalAirportField = document.getElementById('arrivalAirport');
    const airlineField = document.getElementById('airline');

    if (action === 'add') {
        modalTitle.textContent = 'Добавить рейс';
        flightNumberField.value = '';
        departureTimeField.value = '';
        arrivalTimeField.value = '';

    } else if (action === 'edit' && data) {
        modalTitle.textContent = 'Редактировать рейс';
        flightNumberField.value = data.flightNumber;
        departureTimeField.value = data.departureTime;
        arrivalTimeField.value = data.arrivalTime;

        populateSelect(departureAirportField, data.departureAirport);
        populateSelect(arrivalAirportField, data.arrivalAirport);
        populateSelect(airlineField, data.airline);
    }

    const modal = new bootstrap.Modal(document.getElementById('addFlightModal'));
    modal.show();
}

function populateSelect(selectElement, data) {
    selectElement.innerHTML = '';
    const option = document.createElement('option');
    option.value = data.id;
    option.textContent = data.name;
    selectElement.appendChild(option);
}