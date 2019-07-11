package com.fabian.iacob.MockTest;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class LoginControllerTest {

	@Test
	public void testLoginConCertificado() throws CertificateException, NullPointerException {

		byte[] certData = new byte[0];
		X509Certificate certificate = X509Certificate.getInstance(certData);
		Connection connection = null;

		LoginController controller = new LoginController(connection);
		boolean login = controller.login(certificate);

		Assert.assertTrue(login);

	}

	@Test
	public void testLoginConCertificadoMock() throws CertificateException, SQLException {

		X509Certificate certificate = Mockito.mock(X509Certificate.class);
		Connection connection = Mockito.mock(Connection.class);
		
		Principal principal = Mockito.mock(Principal.class);
		Mockito.when(certificate.getSubjectDN()).thenReturn(principal);
		Mockito.when(principal.getName()).thenReturn("usuario");
		
		PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
		Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
		
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
		
		Mockito.when(resultSet.next()).thenReturn(true);
		Mockito.when(resultSet.getInt(1)).thenReturn(1);
		
		
		
		LoginController controller = new LoginController(connection);
		boolean login = controller.login(certificate);
		

		Assert.assertTrue(login);

	}

}
