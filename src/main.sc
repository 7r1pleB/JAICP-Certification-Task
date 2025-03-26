# Репозиторий с полезными сценариями с использованием ES6: https://github.com/just-ai/jaicp-es6-examples

require: modules.js
    type = scriptEs6
    name = modules

require: patterns.sc
  module = sys.zb-common

init:
    bind("onAnyError", function() {
        $reactions.answer("Извините, произошла техническая ошибка. Пожалуйста, напишите в чат позже.");
    });

theme: /

  state: Start
    q!: $regex</start>
    scriptEs6:
        $jsapi.startSession();
        // Извлечение chat_id из объекта request
        let chatId = $request.telegram?.message?.chat?.id;
        let botToken = "8028377959:AAE4Sx1b1kOo6iTGYmRw50b0kBm8hwuEbZE";
        let url = `https://api.telegram.org/bot${botToken}/getChat?chat_id=${chatId}`;
        
        // Выполнение HTTP-запроса к Telegram API
        let response = $http.get(url);
        let userName = "Друг"; // Значение по умолчанию
        if (response.isOk) {
            let result = JSON.parse(response.body).result;
            userName = result.first_name || "Друг";
        }
        $client.name = userName;

    if: $session.new
        random: 
            a: Здравствуйте! Меня зовут Артур, бот-помощник компании Just Tour. Расскажу все о погоде в городах мира и помогу с оформлением заявки на подбор тура.
            a: Приветствую вас! Я Артур, работаю виртуальным ассистентом в Just Tour, лучшем туристическом агентстве. Проинформирую вас о погоде в разных городах и соберу все необходимые данные для запроса на подбор путевки.
    else:
        random: 
            a: {{ $client.name }}, здравствуйте! Артур из Just Tour на связи. Рад снова видеть вас в чате!
            a: {{ $client.name }}, приветствую! На связи Артур из Just Tour, лучшей в мире туристической компании. Рад снова пообщаться с вами!