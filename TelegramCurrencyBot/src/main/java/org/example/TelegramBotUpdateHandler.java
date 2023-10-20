package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;


import java.io.IOException;
import java.lang.reflect.Type;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TelegramBotUpdateHandler {
    private final TelegramBot bot = new TelegramBot("5941600481:AAFPFhapxBh7T-FS06sH-wDEr2e6DYABIB0");

    public void handle(Update update) throws IOException, InterruptedException {
        Message message = update.message();
        CallbackQuery callbackQuery = update.callbackQuery();
        if(message!=null){
            Chat chat = message.chat();
            Long chatId = chat.id();
            String text = message.text();
            if(text.equals("/start")){
                SendMessage sendMessage = new SendMessage(chatId, "Welcome to our currency Bot! \n" +
                        "If you want to calculate currencies enter command /calculate ");
                bot.execute(sendMessage);
            } else if (text.equals("/calculate")) {
                SendMessage sendMessage = new SendMessage(chatId, "Choose one of them!");
                sendMessage.replyMarkup(currencyTypeButtons());
                bot.execute(sendMessage);
            }
        }else{
            String data = callbackQuery.data();
            Chat chat = callbackQuery.message().chat();
            Long charId = chat.id();
            if(data.equals("usd => sum")) {
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://cbu.uz/oz/arkhiv-kursov-valyut/json/USD/2023-01-01/")).GET().build();
                HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                String body = httpResponse.body();
                Type type=TypeToken.getParameterized(List.class,Jsontype.class).getType();
                Gson gson = new Gson();
                List<Jsontype> jsontypes = gson.fromJson(body, type);
                jsontypes.forEach((x)->{
                    SendMessage sendMessage = new SendMessage(charId, "1usd=" + x.getRate() + "sum" + "\n enter /start to restart bot");
                    bot.execute(sendMessage);
                });
            }
            else if(data.equals("rub => sum")) {
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://cbu.uz/oz/arkhiv-kursov-valyut/json/RUB/2023-01-01/")).GET().build();
                HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                String body = httpResponse.body();
                Type type=TypeToken.getParameterized(List.class,Jsontype.class).getType();
                Gson gson = new Gson();
                List<Jsontype> jsontypes = gson.fromJson(body, type);
                jsontypes.forEach((x)->{
                    SendMessage sendMessage = new SendMessage(charId, "1RUB=" + x.getRate() + "SUM" + "\n enter /start to restart bot");
                    bot.execute(sendMessage);
                });
            }

        }

    }
    private Keyboard currencyTypeButtons(){
        InlineKeyboardButton button1=new InlineKeyboardButton("rub => sum");
        InlineKeyboardButton button2=new InlineKeyboardButton("usd => sum");
        button1.callbackData("rub => sum");
        button2.callbackData("usd => sum");
        InlineKeyboardMarkup keyboardMarkup=new InlineKeyboardMarkup();
        keyboardMarkup.addRow(button1);
        keyboardMarkup.addRow(button2);
        return keyboardMarkup;
    }
}
