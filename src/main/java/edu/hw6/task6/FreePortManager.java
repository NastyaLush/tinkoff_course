package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class FreePortManager {

    private static final Integer MAX_PORT = 49151;

    public void printBusyPorts(Printer printer) {
        ReservedPortsDescriptionService reservedPort = new ReservedPortsDescriptionService();

        List<Port> busyPorts = getBusyPorts();
        printer.print("Протокол", "Порт", "Сервис");
        for (Port port : busyPorts) {
            printer.print(port.protocol()
                .toString(), port.number()
                .toString(), reservedPort.getPortDescription(port));
        }
    }

    @SuppressWarnings("EmptyBlock")
    private List<Port> getBusyPorts() {
        List<Port> busyPorts = new ArrayList<>();
        for (int i = 0; i <= MAX_PORT; i++) {
            try (ServerSocket serverSocket = new ServerSocket(i)) {
            } catch (IOException e) {
                busyPorts.add(new Port(ProtocolType.TCP, i));
            }
            try (DatagramSocket datagramSocket = new DatagramSocket(i)) {
            } catch (IOException e) {
                busyPorts.add(new Port(ProtocolType.UDP, i));
            }
        }
        return busyPorts;
    }
}
