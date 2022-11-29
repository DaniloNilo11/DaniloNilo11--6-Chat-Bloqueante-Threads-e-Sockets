import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ServerApp {
    public static final int PORT = 8080;
    private final ServerSocket serverSocket;
    private List<ClienteSocket> clienteSocketList;

    public static void main(String[] args) {

        new ServerApp().start();

    }

    public ServerApp() {
        try {
            this.clienteSocketList = new LinkedList<>();
            this.serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado porta: " + PORT);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void start() {
        while (true) {
            try {
                System.out.print("conexão realizada");
                Socket socket = serverSocket.accept();
                var clienteSocket = new ClienteSocket(socket);
                this.clienteSocketList.add(clienteSocket);
                new Thread(()-> messagemloop(clienteSocket)).start();
            } catch (IOException e) {
                System.err.println(
                        "Não foi Possivel estabelecer a conexão com cliente: "
                                + e.getMessage());
            }
        }
    }

    private void messagemloop(ClienteSocket clienteSocket) {
        String msg;
        do {
            msg =clienteSocket.getMessage();

            System.out.println(
                    "Mensagem enviada: "+msg);

        }while (!"".equals(msg));
            {
            }

        }
    }