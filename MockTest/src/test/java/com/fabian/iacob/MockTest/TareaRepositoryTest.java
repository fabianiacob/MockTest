package com.fabian.iacob.MockTest;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TareaRepositoryTest {

	@Test
	public void testLoginConCertificadoMock() throws CertificateException, SQLException {
		Tarea tarea = new Tarea("Tarea 1");
		
		Connection connection = Mockito.mock(Connection.class);
		PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
		
		Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
		
		TareaRepository tareaRepository = new TareaRepository(connection);
		tareaRepository.save(tarea);
		
		Mockito.verify(preparedStatement).executeUpdate();
		Mockito.verify(preparedStatement).setString(1, "Tarea 1");
		

	}
	
	@Test
	public void testLoginConCertificadoMockSpy() throws CertificateException, SQLException {
		Tarea tarea = new Tarea("Tarea 1");
		Tarea spy = Mockito.spy(tarea);
		Connection connection = Mockito.mock(Connection.class);
		PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
		
		Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
		
		TareaRepository tareaRepository = new TareaRepository(connection);
		tareaRepository.save(spy);
		
		Mockito.verify(spy).getNombre();
		

	}

}
