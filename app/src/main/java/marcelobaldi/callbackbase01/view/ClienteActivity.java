package marcelobaldi.callbackbase01.view;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import marcelobaldi.callbackbase01.R;
import marcelobaldi.callbackbase01.api.async.ClienteApi;
import marcelobaldi.callbackbase01.api.callback.ClienteApiCallback;
import marcelobaldi.callbackbase01.model.ClienteModel;

public class ClienteActivity extends AppCompatActivity{
    //Atributos
    ClienteApi clienteApi = new ClienteApi();

    //Método Inicial
    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
    }

    //Botões Comando
    public void btnBuscarTodos (View view){
        clienteApi.buscarTodos();
        clienteApi.setClienteApiCallback(new ClienteApiCallback() {
            @Override public void buscarTodosApi(ArrayList<ClienteModel> listaClientesV) {
                Log.d("meuLog.BuscarTodos", String.valueOf(listaClientesV.size()));
            }

            //Não Aplicável Neste Caso
            @Override public void buscarPorIdApi(ClienteModel cliente) { }
            @Override public void buscarPorEmailApi(Boolean existe) { }
            @Override public void salvarApi(Integer resultado) { }
            @Override public void atualizarApi(Boolean atualizado) { }
            @Override public void deletarApi(Boolean deletado) { }
        });
    }
    public void btnBuscarPorId(View view) {
        clienteApi.buscarPorId(2);
        clienteApi.setClienteApiCallback(new ClienteApiCallback() {
            @Override public void buscarPorIdApi(ClienteModel clienteV) {
                Log.d("meuLog.BuscarPorId", clienteV.toString());
            }

            //Não Aplicável Neste Caso
            @Override public void buscarTodosApi(ArrayList<ClienteModel> listaClientesV) { }
            @Override public void buscarPorEmailApi(Boolean existe) { }
            @Override public void salvarApi(Integer resultado) { }
            @Override public void atualizarApi(Boolean atualizado) { }
            @Override public void deletarApi(Boolean deletado) { }
        });
    }
    public void btnBuscarPorEmail(View view) {
        clienteApi.buscarPorEmail("NaT@ig.cOM");
        clienteApi.setClienteApiCallback(new ClienteApiCallback() {
            @Override public void buscarPorEmailApi(Boolean existeV) {
                Log.d("meuLog.BuscarPorEmail", String.valueOf(existeV));
            }

            //Não Aplicável Neste Caso
            @Override public void buscarTodosApi(ArrayList<ClienteModel> listaClientesV) { }
            @Override public void buscarPorIdApi(ClienteModel clienteV) { }
            @Override public void salvarApi(Integer resultado) { }
            @Override public void atualizarApi(Boolean atualizado) { }
            @Override public void deletarApi(Boolean deletado) { }
        });
    }
    public void btnSalvar(View view) {
        ClienteModel clienteNovo = new ClienteModel("KB", "kle@ig.com9", 50);
        clienteApi.salvar(clienteNovo);
        clienteApi.setClienteApiCallback(new ClienteApiCallback() {
            @Override public void salvarApi(Integer resultado) {
                if(resultado == 0){
                    Log.d("meuLog.Salvar", "Este Email Já Existe");
                }else{
                    Log.d("meuLog.Salvar", "Id Salvo: " + String.valueOf(resultado));
                }
            }

            //Não Aplicável Neste Caso
            @Override public void buscarTodosApi(ArrayList<ClienteModel> listaClientesV) { }
            @Override public void buscarPorIdApi(ClienteModel clienteV) { }
            @Override public void buscarPorEmailApi(Boolean existeV) {
                Log.d("meuLog.BuscarTodos", String.valueOf(existeV));
            }
            @Override public void atualizarApi(Boolean atualizado) { }
            @Override public void deletarApi(Boolean deletado) { }
        });
    }
    public void btnAtualizar(View view) {
        ClienteModel cliente = new ClienteModel(1, "Xuxa", "xu@ig.com", 7);
        clienteApi.atualizar(cliente);
        clienteApi.setClienteApiCallback(new ClienteApiCallback() {
            @Override public void atualizarApi(Boolean atualizado) {
                Log.d("meuLog.Atualizar", String.valueOf(atualizado));
            }

            //Não Aplicável Neste Caso
            @Override public void buscarTodosApi(ArrayList<ClienteModel> listaClientesV) { }
            @Override public void buscarPorIdApi(ClienteModel clienteV) { }
            @Override public void buscarPorEmailApi(Boolean existeV) {
                Log.d("meuLog.BuscarTodos", String.valueOf(existeV));
            }
            @Override public void salvarApi(Integer resultado) { }
            @Override public void deletarApi(Boolean deletado) { }
        });
    }
    public void btnDeletar(View view) {
        clienteApi.deletar(2);
        clienteApi.setClienteApiCallback(new ClienteApiCallback() {
            @Override public void deletarApi(Boolean deletado) {
                Log.d("meuLog.Deletar", String.valueOf(deletado));
            }

            //Não Aplicável Neste Caso
            @Override public void buscarTodosApi(ArrayList<ClienteModel> listaClientesV) { }
            @Override public void buscarPorIdApi(ClienteModel clienteV) { }
            @Override public void buscarPorEmailApi(Boolean existeV) {
                Log.d("meuLog.BuscarTodos", String.valueOf(existeV));
            }
            @Override public void salvarApi(Integer resultado) { }
            @Override public void atualizarApi(Boolean atualizado) { }
        });
    }
}

