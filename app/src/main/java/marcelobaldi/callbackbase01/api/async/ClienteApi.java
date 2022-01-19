package marcelobaldi.callbackbase01.api.async;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import marcelobaldi.callbackbase01.api.callback.ClienteApiCallback;
import marcelobaldi.callbackbase01.model.ClienteModel;

public class ClienteApi {
    //Atributos
    private String  email;
    private Boolean existe;
    private ClienteModel clienteModel = new ClienteModel();
    private List<ClienteModel> listaClientes = new ArrayList<ClienteModel>();
    private ClienteApiCallback clienteApiCallback;                              //Classe API

    //Construtor (Conter a Lista Estática)
    public ClienteApi() {
        listaClientes.add(new ClienteModel(1, "Marcelo", "mar@ig.com", 40));
        listaClientes.add(new ClienteModel(2, "Klebers", "kle@ig.com", 38));
        listaClientes.add(new ClienteModel(3, "Natalia", "nat@ig.com", 35));
        listaClientes.add(new ClienteModel(4, "Lukas",   "luk@ig.com", 12));
    }

    //Receber da Activity (A Interface Manipulada) e Passar Para a Interface (Quem a Esta Manipulando)
    public  void setClienteApiCallback(ClienteApiCallback clienteApiCallbackV){
        clienteApiCallback = clienteApiCallbackV;
    }

    //Métodos Assíncronos - Envia Para Interface
    public void buscarTodos(){
        new AsyncTask<Void, Integer, ArrayList<ClienteModel>>() {
            @Override protected ArrayList<ClienteModel> doInBackground(Void... voids) {
                return (ArrayList<ClienteModel>) listaClientes;
            }
            @Override protected void onPostExecute(ArrayList<ClienteModel> clienteModels) {
                super.onPostExecute(clienteModels);
                clienteApiCallback.buscarTodosApi((ArrayList<ClienteModel>) listaClientes);
            }
        }.execute();
    }
    public void buscarPorId(Integer idV) {
        new AsyncTask<Integer, Integer, ClienteModel>() {
            @Override protected ClienteModel doInBackground(Integer... integers) {
                for(int i = 0; i < listaClientes.size(); i++){
                    if(listaClientes.get(i).getId() == idV){                 // ==       Não String
                        String  nome  = listaClientes.get(i).getNome();      // Equals   String
                        String  email = listaClientes.get(i).getEmail();
                        Integer idade = listaClientes.get(i).getIdade();
                        clienteModel = new ClienteModel(idV, nome, email, idade);
                        break;
                    }
                }
                return clienteModel;
            }

            @Override protected void onPostExecute(ClienteModel clienteModelXY) {
                super.onPostExecute(clienteModel);
                clienteApiCallback.buscarPorIdApi(clienteModel);
            }
        }.execute();
    }
    public void buscarPorEmail(String emailV) {
        email  = "";
        existe = Boolean.FALSE;


        new AsyncTask<String, Integer, Boolean>() {
            @Override protected Boolean doInBackground(String... strings) {
                email = emailV.toLowerCase();
                for(int i = 0; i < listaClientes.size(); i++){
                    if(listaClientes.get(i).getEmail().equalsIgnoreCase(email)){
                        existe = Boolean.TRUE;
                        break;
                    }                                                        // ==       Não String
                }                                                            // Equals   String
                return existe;
            }

            @Override protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                clienteApiCallback.buscarPorEmailApi(existe);
            }
        }.execute();
    }
    public void salvar(ClienteModel clienteV) {
        existe = Boolean.FALSE;

        new AsyncTask<ClienteModel, Integer, Integer>(){
            @Override protected Integer doInBackground(ClienteModel... clienteModels) {
                //Verificar Se Já Existe - Manual
                for (ClienteModel item : listaClientes) {
                    if(clienteV.getEmail().toLowerCase().equalsIgnoreCase(item.getEmail())){
                        existe = Boolean.TRUE;
                        break;
                    }
                }

                //Salvar, Se Não Existir o Email
                if(existe){
                    return 0;
                }else{
                    clienteModel.setId(10);
                    clienteModel.setNome(clienteV.getNome());
                    clienteModel.setEmail(clienteV.getEmail().toLowerCase());   //***
                    clienteModel.setIdade(clienteV.getIdade());
                    return clienteModel.getId();
                }
            }

            @Override protected void onPostExecute(Integer retornoX) {
                super.onPostExecute(retornoX);
                clienteApiCallback.salvarApi(retornoX);
            }
        }.execute();
    }
    public  void atualizar(ClienteModel clienteV) {
        new AsyncTask<ClienteModel, Integer, Boolean>(){
            @Override protected Boolean doInBackground(ClienteModel... clienteModels) {
                Log.d("meuLog", "ClienteRecebido: " + clienteV.toString());
                return true;
            }
            @Override protected void onPostExecute(Boolean retornoX) {
                super.onPostExecute(retornoX);
                clienteApiCallback.atualizarApi(retornoX);
            }
        }.execute();
    }
    public  void deletar(Integer idV) {
        new AsyncTask<Integer, Integer, Boolean>() {
            @Override protected Boolean doInBackground(Integer... integers) {
                Log.d("meuLog", "IdRecebido: " + idV.toString());
                return true;
            }
            @Override protected void onPostExecute(Boolean retornoX) {
                super.onPostExecute(retornoX);
                clienteApiCallback.deletarApi(retornoX);
            }
        }.execute();
    }

}

//Etapas
//- Criar:          Obeter Dados, Executar Tarefas, Etc;
//- Interfade:      Receber Dados do OnPostExecute;
//- Setar:          Chamar Interface;
//* Static:         Funciona Sem, mas Aparece Alertas da IDE;
//* Interface:      Poderia Numa Classe Separada;
//* Parâmetros:     Para Receber Parâmetros é um Pouco Diferente (vide outros exemplos);

////////////////////////////////////////////////////////////////////////////////////////////////////

//AsyncTask
//Parâmetros:           Pede 3 Parâmetros Obrigatórios;
//- 1o. Param:          Parâmetros do Método. Se Não Tiver, Então Colocar Void;
//- 2o. Param:          Objeto Utilizado no OnProgress. Normalmente Integer;
//- 3o. Param:          Objeto de Retorno do Método;

//Métodos:              Possui 4 Métodos Principais;
//- Obrigatórios:       DoInBackGround e OnPostExecute;
//- OnPreExecute:       Executado Antes do Método Principal (DoInBackGround);
//- DoInBackGround:     Método Principal (Aqui Que é a Obtenção dos Dados, Tarefas, Etc);
//- OnProgressUpdate:   Executado Juntamente com o DoInBackGround. Normalmente Para um ProgressBar;
//- OnPostExecute:      Executado Após o Método Principal;


