package com.subwaymonitor.integrations.google;

import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class responsible for performing authentication/authorization operations to enable the
 * communication with Google APIs. Operations require the credentials of a service account.
 */
@Component
class GoogleServicesAuthorizer {

  private final GoogleServicesProperties properties;

  @Autowired
  GoogleServicesAuthorizer(final GoogleServicesProperties properties) {
    this.properties = properties;
  }

  /**
   * Reads the service account credentials from the application configurations and provide a set of
   * credentials with the passed scopes.
   *
   * @param scopes A list of strings representing the scopes of Google APIs. Scopes are available as
   *     constants in Google's library. e.g {@link SheetsScopes}.
   */
  Credentials authorize(final List<String> scopes) throws IOException {
    final var credentials = properties.getServiceAccount().getCredentials();
    final var inputStream = new ByteArrayInputStream(credentials.getBytes());
    return GoogleCredentials.fromStream(inputStream).createScoped(scopes);
  }
}
