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
        HttpRequest:
            url = https://api.telegram.org/bot8028377959:AAE4Sx1b1kOo6iTGYmRw50b0kBm8hwuEbZE/getChat
            method = GET
            dataType = JSON
            vars = [
                {"name": "chat_id", "value": "{{ $request.telegram?.message?.chat?.id }}"}
            ]
        script:
            // Обработка ответа от Telegram API
            if ($httpResponse.isOk) {
                let result = $httpResponse.body.result;
                $client.name = result.first_name || "Друг";
            } else {
                $client.name = "Друг";
            }

        if: $session.new
            random:            
                a: "Здравствуйте! Меня зовут Артур, бот-помощник компании Just Tour. Расскажу все о погоде в городах мира и помогу с оформлением заявки на подбор тура."
                a: "Приветствую вас! Я Артур, работаю виртуальным ассистентом в Just Tour, лучшем туристическом агентстве. Проинформирую вас о погоде в разных городах и соберу все необходимые данные для запроса на подбор путевки."
        else:
            random:
                a: "{{ $client.name }}, здравствуйте! Артур из Just Tour на связи. Рад снова видеть вас в чате!"
                a: "{{ $client.name }}, приветствую! На связи Артур из Just Tour, лучшей в мире туристической компании. Рад снова пообщаться с вами!"
            
        go!: /WaitFile