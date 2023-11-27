package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class FreePortManagerPrinterTest {

    @Test
    @DisplayName("check if exist at least one port from busy ports is busy")
    public void printBusyPorts_shouldPrintBusyPorts() {
        FreePortManager freePortManager = new FreePortManager();
        ArrayList<PortForTest> busyPorts = new ArrayList<>();
        freePortManager.printBusyPorts((String... args) -> {
            if (args[0] != "Протокол") {
                busyPorts.add(new PortForTest(args[0], args[1], args[2]));
            }
        });
        for (int i = 1; i < busyPorts.size(); i++) {
            if (checkIfBusy(busyPorts.get(i))) {
                assertTrue(true);
                return;
            }
        }
        fail();
    }

    @Test
    @DisplayName("check if port service is correct")
    public void printBusyPorts_shouldCorrectlyDefineService() {
        FreePortManager freePortManager = new FreePortManager();
        ArrayList<PortForTest> busyPorts = new ArrayList<>();
        ReservedPortsDescription reservedPortsDescription = new ReservedPortsDescription();
        freePortManager.printBusyPorts((String... args) -> {
            if (args[0] != "Протокол") {
                busyPorts.add(new PortForTest(args[0], args[1], args[2]));
            }
        });
        for (int i = 1; i < busyPorts.size(); i++) {
            if (!Objects.equals(busyPorts.get(1).service, "")) {
                String service = reservedPortsDescription.getPortDescription(new Port(
                    busyPorts.get(i).protocol,
                    busyPorts.get(i).number
                ));
                assertEquals(service, busyPorts.get(i).service);
            }
        }
    }

    private boolean checkIfBusy(PortForTest portForTest) {
        if (Objects.equals(portForTest.protocol, "TCP")) {
            try (ServerSocket serverSocket = new ServerSocket(portForTest.number)) {
            } catch (IOException e) {
                return true;
            }
        } else {
            try (DatagramSocket datagramSocket = new DatagramSocket(portForTest.number)) {
            } catch (IOException e) {
                return true;
            }
        }
        return false;
    }

    class PortForTest {

        ProtocolType protocol;
        Integer number;
        String service;

        public PortForTest(String protocol, String number, String service) {
            this.protocol = ProtocolType.valueOf(protocol);
            this.number = Integer.valueOf(number);
            this.service = service;
        }
    }

}
