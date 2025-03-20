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
        script:
            $jsapi.startSession();
            
        random:
            a: {{ $client.name }}, здравствуйте! Артур из Just Tour на связи. Рад снова видеть вас в чате!
            a: {{ $client.name }}, приветствую! На связи Артур из Just Tour, лучшей в мире туристической компании. Рад снова пообщаться с вами!
            
        go!: /WaitFile