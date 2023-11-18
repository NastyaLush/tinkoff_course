package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FreePortManager {

    private static final Integer MAX_PORT = 49151;

    public void printBusyPorts(Printer printer) {
        ReservedPortManager reservedPort = new ReservedPortManager();
        HashMap<Port, String> portTreeSet = reservedPort.getReservedPort();

        List<Port> busyPort = getBusyPort();
        printer.print("Протокол", "Порт", "Сервис");
        for (Port port : busyPort) {
            if (portTreeSet.containsKey(port)) {
                printer.print(port.protocol()
                                  .toString(), port.number()
                                                   .toString(), portTreeSet.get(port));

            } else {
                printer.print(port.protocol()
                                  .toString(), port.number()
                                                   .toString(), "");
            }
        }
    }

    @SuppressWarnings("EmptyBlock")
    private List<Port> getBusyPort() {
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
