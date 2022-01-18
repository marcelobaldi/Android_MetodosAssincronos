package marcelobaldi.callbackbase01.api.callback;
import java.util.ArrayList;

import marcelobaldi.callbackbase01.model.ClienteModel;

//Métodos Assíncronos - Interface                               //Quem Implementar Terá Que Ter, e
public interface ClienteApiCallback {                           //os Parâmetros é o Que Vão Receber
    void buscarTodosApi(ArrayList<ClienteModel> listaClientes);
    void buscarPorIdApi(ClienteModel cliente);
    void buscarPorEmailApi(Boolean existe);
    void salvarApi(Integer resultado);
    void atualizarApi(Boolean atualizado);
    void deletarApi(Boolean deletado);
}


