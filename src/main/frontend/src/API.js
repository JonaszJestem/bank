export default class API {
    static async login(data) {
        data = this.convertToObject(data);
        console.log(data);
        const response = await fetch("https://localhost:8443/user/login", {
            body: JSON.stringify(data),
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            }
        });
        console.log(response);
        return await response.json();
    }

    static async signup(data) {
        data = this.convertToObject(data);
        console.log(data);
        const response = await fetch("https://localhost:8443/user/register", {
            body: JSON.stringify(data),
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            }
        });
        console.log(response);
        return await response.json();
    }

    static convertToObject(formData) {
        var object = {};
        formData.forEach(function (value, key) {
            object[key] = value;
        });
        return object;
    }

    static async history(data) {
        const response = {
            json: () => ({history})
        };

        return new Promise((resolve) => {
            return response
        })
    }
}

const history = [{
    title: "Tytuł 1",
    amount: 1000,
    date: "10/12/2018",
    author: "osoba1",
}, {
    title: "Tytuł 2",
    amount: 500,
    date: "10/12/2018",
    author: "osoba1",
}, {
    title: "Tytuł 3",
    amount: 200,
    date: "10/12/2018",
    author: "osoba1",
}, {
    title: "Tytuł 4",
    amount: 1200,
    date: "10/12/2018",
    author: "osoba1",
}];