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
        formData.forEach(function(value, key){
            object[key] = value;
        });
        return object;
    }
}