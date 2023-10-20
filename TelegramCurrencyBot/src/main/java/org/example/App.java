package org.example;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;


import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
//    private static final ResourceBundle setting=ResourceBundle.getBundle("setting");
    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final ThreadLocal<TelegramBotUpdateHandler> telegramBotHandler = ThreadLocal.withInitial(TelegramBotUpdateHandler::new);

    public static void main( String[] args ) {
        TelegramBot tgBot=new TelegramBot("5941600481:AAFPFhapxBh7T-FS06sH-wDEr2e6DYABIB0");
        tgBot.setUpdatesListener((updates)->{
            for(Update update:updates){
                CompletableFuture.runAsync(()-> {
                    try {
                        telegramBotHandler.get().handle(update);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                },executorService);
            }   return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, Throwable::printStackTrace);
    }
}
