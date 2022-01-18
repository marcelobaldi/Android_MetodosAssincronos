package marcelobaldi.callbackbase01;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import marcelobaldi.callbackbase01.view.ClienteActivity;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentX = new Intent(this, ClienteActivity.class);
        startActivity(intentX);
        //startActivity(new Intent(this, ClienteListaActivity.class));
    }

}

//falta confirmação do bd, se salvou mesmo ou se deu erro;

//id string ou integer ?!? esta integer!!!

// estou repetindo código (no salvar estou verificando de novo se se existe, sendo que eu poderia
//aproveitar tal método.
// como controller, para fazer esta regra ?
