const axios = require('axios');
$jsapi.startSession();


let chatId = $request.telegram?.message?.chat?.id;
let botToken = "8377959:AAE4Sx1b1kOo6iTGYmRw50b0kBm8hwuEbZE";
let url = `https://api.telegram.org/bot${botToken}/getChat?chat_id=${chatId}`;

// Выполнение HTTP-запроса через axios
let userName = "Друг";

async function getClientName () {
    try {
        let response = await axios.get(url); 
        if (response.data.ok) {
            return response.data.result.first_name || "Друг"; // Извлечение имени пользователя
        }
    } catch (error) {
       return ("Ошибка при выполнении запроса к Telegram API: " + error);
    }
}