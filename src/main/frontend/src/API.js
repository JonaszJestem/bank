export default class API {
    static async login(data) {
        return {
            status: 200,
            message: "Logged in!",
            payload: "TOKEN"
        };
        const response = await fetch("https://localhost:8443/user/login", {body: data, method: "POST", mode: "no-cors"});
        return await response.json();
    }

    static async signup(data) {
        const response = await fetch("https://localhost:8443/user/register", {body: data, method: "POST", mode: "no-cors"});
        return await response.json();
    }
}