package com.fabian.iacob.MockTest;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.cert.X509Certificate;

public class LoginController {

	private Connection connection;

	public LoginController(Connection connection) {
		this.connection = connection;
	}

	public boolean login(X509Certificate certificado) {
		boolean resultado = false;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			Principal subjectDN = certificado.getSubjectDN();
			prepareStatement = connection.prepareStatement("SELECT count(*) FROM usuarios where login = ?");
			prepareStatement.setString(1, subjectDN.getName());
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				resultado = resultSet.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

}