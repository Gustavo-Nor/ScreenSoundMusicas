package com.ScreenSoundMusicas.ScreenSoundMusicas.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {

    static GetKeyFromProperties getKeyFromProperties = new GetKeyFromProperties();

    public String pesquisarSobre(String texto) {

        String apiKey = getKeyFromProperties.obterKeyValue("CHATGPT_APIKEY");

        OpenAiService service = new OpenAiService(apiKey);
        CompletionRequest requisicao = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("Escreva 2 linhas sobre o artista: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}