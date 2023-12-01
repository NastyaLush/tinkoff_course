package edu.hw8.task1;

import edu.hw8.task1.server.ClientService;
import edu.hw8.task1.server.QuatotationService;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientServiceTest {

    private Socket mockSocket;
    private QuatotationService mockQuotationService;
    private BufferedReader mockReader;
    private PrintWriter mockWriter;
    private ClientService clientService;

    @BeforeEach
    void setUp() throws IOException {
        mockSocket = mock(Socket.class);
        mockQuotationService = mock(QuatotationService.class);
        mockReader = mock(BufferedReader.class);
        mockWriter = mock(PrintWriter.class);

        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("TestInput\n".getBytes()));

        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("TestInput\n".getBytes()));

        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("TestInput\n".getBytes()));

        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("TestInput\n".getBytes()));

        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("TestInput\n".getBytes()));

        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("TestInput\n".getBytes()));

        clientService = new ClientService(mockSocket, mockQuotationService);

    }

    @Test
    void testRun() throws IOException {
        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("TestInput\n".getBytes()));

        clientService.run();

        verify(mockQuotationService, times(1)).getQuote("TestInput");
        verify(mockSocket, times(1)).close();
    }

}

