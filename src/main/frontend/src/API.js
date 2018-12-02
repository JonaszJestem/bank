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
        return await response.json();
    }

    static async signup(data) {
        data = this.convertToObject(data);
        console.log(data);
        const response = await fetch("https://localhost:8443/user/signup", {
            body: JSON.stringify(data),
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            }
        });
        console.log(response);
        return await response.json();
    }


    static async saveForConfirmation(data) {
        data = this.convertToObject(data);
        console.log(data);
        const response = await fetch("https://localhost:8443/transfer/saveForConfirmation", {
            body: JSON.stringify(data),
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + sessionStorage.getItem("token")
            }
        });
        console.log(response);
        return await response.json();
    }


    static async confirm() {
        const response = await fetch("https://localhost:8443/transfer/perform", {
            method: "POST",
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem("token")
            }
        });
        return await response.json();
    }


    static async history() {
        const response = await fetch("https://localhost:8443/transfer", {
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem("token")
            }
        });
        return await response.json();
    }

    static convertToObject(formData) {
        var object = {};
        formData.forEach(function(value, key){
            object[key] = value;
        });
        return object;
    }
}
