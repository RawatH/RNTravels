package rn.travels.in.rntravels;

import android.content.Context;
import android.util.Log;

// Imports the Google Cloud client library
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 * Created by demo on 26/05/18.
 */

public class TranslationManager {
    public static TranslationManager INSTANCE;
    private Context context;
    private Translate translate ;

    private TranslationManager(Context ctx){
        this.context = ctx;
        translate = TranslateOptions.getDefaultInstance().getService();
    }

    public static TranslationManager getINSTANCE(Context ctx){
        if(INSTANCE == null){
            INSTANCE = new TranslationManager(ctx);
        }
        return INSTANCE;
    }

    public String translate(String txtToTranslate ){
        // Translates some text into Russian
        Translation translation = translate.translate(
                txtToTranslate,
                        TranslateOption.sourceLanguage("en"),
                        TranslateOption.targetLanguage("ru"));


        Log.d("translate","Text: "+ txtToTranslate);
        Log.d("translate","Translation: "+ translation.getTranslatedText());
        return null;

    }
}
